package site.moheng.mfui.binding;

import java.util.ArrayList;
import java.util.List;

public class ComputedDataBindingSource<S, T> implements IBindingSource<T> {
	public final IBindingSource<S> sourceBinding;
	protected List<IEvent<T>> listeners = new ArrayList<>();
	protected IComputed<S, T> computed;

	public ComputedDataBindingSource(IBindingSource<S> source, IComputed<S, T> computed) {
		this.sourceBinding = source;
		this.computed = computed;

		source.addListener(this::change);
	}

	public void change(IBindingSource<S> source) {
		submit();
	}

	@Override
	public List<IEvent<T>> getListeners() {
		return listeners;
	}

	@Override
	public void set(T data) {
		throw new Error();
	}

	@Override
	public T get() {
		return computed.accept(sourceBinding.get());
	}

	@FunctionalInterface
	public interface IComputed<S, T> {
		T accept(S source);
	}
}
