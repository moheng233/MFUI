package site.moheng.mfui.binding;

import java.util.List;

/**
 * 动态数据的绑定源
 */
public interface IObservable {
	/**
	 * 将当前观察对象设置为改变
	 */
	void setChange();

	List<IObserver> getObservers();

	default void addObserver(IObserver listener) {
		getObservers().add(listener);
	}

	default void removeObserver(IObserver listener) {
		getObservers().remove(listener);
	}

	default <S extends IObservable, T> ComputedObservable<S, T> computed(ComputedObservable.IComputed<S, T> computed) {
		//noinspection unchecked
		return new ComputedObservable<>((S)this, computed);
	}

}