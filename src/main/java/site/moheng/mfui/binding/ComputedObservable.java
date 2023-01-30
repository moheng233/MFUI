package site.moheng.mfui.binding;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * 使用计算类对值类型的观察类进行转换
 *
 * @param <S> 源类型
 * @param <T> 目标类型
 */
public class ComputedObservable<S extends IObservable, T> extends AbsValuedObservable<T> implements IObserver {
	public final S sourceBinding;
	protected IComputed<S, T> computed;

	@Nullable
	protected T cached = null;

	public ComputedObservable(S source, IComputed<S, T> computed) {
		this.sourceBinding = source;
		this.computed = computed;

		source.addObserver(this);
	}

	public void change() {
		cached = null;
		setChange();
	}

	@Override
	@NotNull
	public T get() {
		if (cached == null) {
			cached = computed.accept(sourceBinding);
		}

		return cached;
	}

	@Override
	public void set(T value) {

	}

	@FunctionalInterface
	public interface IComputed<S, T> {
		T accept(S source);
	}
}
