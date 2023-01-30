package site.moheng.mfui.binding;

import java.util.List;

/**
 * 动态数据的绑定源
 */
public interface IObservable<S> {
	/**
	 * 将当前观察对象设置为改变
	 */
	default void setChange() {
		for (var listener : getListeners()) {
			listener.change(this);
		}
	}

	List<IObserver<S>> getListeners();

	void set(S data);

	S get();

	default void addListener(IObserver<S> listener) {
		getListeners().add(listener);
	}

	default void removeListener(IObserver<S> listener) {
		getListeners().remove(listener);
	}

	default <T> ComputedObservable<S, T> computed(ComputedObservable.IComputed<S, T> computed) {
		return new ComputedObservable<>(this, computed);
	}

	interface IObserver<S> {
		void change(IObservable<S> source);
	}
}