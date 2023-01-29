package site.moheng.mfui.binding;

import java.util.List;

/**
 * 动态数据的绑定源
 */
public interface IObservable<S> {
	default void submit() {
		for (var listener : getListeners()) {
			listener.accept(this);
		}
	}

	List<IEvent<S>> getListeners();

	void set(S data);

	S get();

	default void addListener(IEvent<S> listener) {
		getListeners().add(listener);
	}

	default void removeListener(IEvent<S> listener) {
		getListeners().remove(listener);
	}

	default <T> ComputedObservable<S, T> computed(ComputedObservable.IComputed<S, T> computed) {
		return new ComputedObservable<>(this, computed);
	}

	interface IEvent<S> {
		void accept(IObservable<S> source);
	}
}