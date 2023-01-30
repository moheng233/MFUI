package site.moheng.mfui.binding;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * 使用计算类进行可观察类的转换
 *
 * @param <S> 源类型
 * @param <T> 目标类型
 */
public class ComputedObservable<S, T> implements IObservable<T>, IObservable.IObserver<S> {
	public final IObservable<S> sourceBinding;
	protected List<IObserver<T>> listeners = new ArrayList<>();
	protected IComputed<S, T> computed;

	@Nullable
	protected T cached = null;

	public ComputedObservable(IObservable<S> source, IComputed<S, T> computed) {
		this.sourceBinding = source;
		this.computed = computed;

		source.addListener(this);
	}

	public void change(IObservable<S> source) {
		cached = null;
		setChange();
	}

	@Override
	public List<IObserver<T>> getListeners() {
		return listeners;
	}

	@Override
	public void set(T data) {
		// 计算函数的设置函数不具有实际效用
	}

	@Override
	@NotNull
	public T get() {
		if (cached == null) {
			cached = computed.accept(sourceBinding.get());
		}

		return cached;
	}

	@FunctionalInterface
	public interface IComputed<S, T> {
		T accept(S source);
	}
}
