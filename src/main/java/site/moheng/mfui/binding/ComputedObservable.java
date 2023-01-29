package site.moheng.mfui.binding;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ComputedObservable<S, T> implements IObservable<T> {
	public final IObservable<S> sourceBinding;
	protected List<IEvent<T>> listeners = new ArrayList<>();
	protected IComputed<S, T> computed;

	@Nullable
	protected T cached = null;

	public ComputedObservable(IObservable<S> source, IComputed<S, T> computed) {
		this.sourceBinding = source;
		this.computed = computed;

		source.addListener(this::change);
	}

	public void change(IObservable<S> source) {
		cached = null;
		submit();
	}

	@Override
	public List<IEvent<T>> getListeners() {
		return listeners;
	}

	@Override
	public void set(T data) {
		throw new ComputedObservableDotSetError();
	}

	@Override
	@NotNull
	public T get() {
		if(cached == null) {
			cached = computed.accept(sourceBinding.get());
		}

		return cached;
	}

	@FunctionalInterface
	public interface IComputed<S, T> {
		T accept(S source);
	}

	public static final class ComputedObservableDotSetError extends Error {

	}
}
