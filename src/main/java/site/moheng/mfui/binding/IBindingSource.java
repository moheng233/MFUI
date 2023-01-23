package site.moheng.mfui.binding;

import java.util.List;
import java.util.function.Supplier;

/**
 * 动态数据的绑定源
 */
public interface IBindingSource<S extends IBindingSource<S>> {
	List<IEvent<S>> getListeners();

	default void addListener(IEvent<S> listener) {
		getListeners().add(listener);
	}

	default void removeListener(IEvent<S> listener) {
		getListeners().remove(listener);
	}

	@SuppressWarnings("unchecked")
	default <T extends IBindingSource<T>> T computed(Supplier<T> target, ComputedDataBindingSource.IComputed<S, T> computed) {
		return new ComputedDataBindingSource<>((S) this, target, computed).targetBinding;
	}

	interface IEvent<S extends IBindingSource<?>> {
		void accept(S source);
	}
}