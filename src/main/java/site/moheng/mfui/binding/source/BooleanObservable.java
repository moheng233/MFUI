package site.moheng.mfui.binding.source;

import site.moheng.mfui.binding.AbsValuedObservable;

public final class BooleanObservable extends AbsValuedObservable<Boolean> {
	private boolean data = false;

	public boolean getValue() {
		return data;
	}

	public void set(boolean data) {
		this.data = data;
		setChange();
	}

	public Boolean get() {
		return data;
	}

	public void set(Boolean data) {
		this.data = data;
		setChange();
	}
}