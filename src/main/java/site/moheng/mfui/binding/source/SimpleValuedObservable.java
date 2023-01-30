package site.moheng.mfui.binding.source;

import site.moheng.mfui.binding.AbsValuedObservable;

public final class SimpleValuedObservable<V> extends AbsValuedObservable<V> {
	private V value;

	public SimpleValuedObservable(V value) {
		this.value = value;
	}

	@Override
	public V get() {
		return value;
	}

	public void set(V value) {
		if (this.value != value) {
			this.value = value;
			setChange();
		}
	}
}