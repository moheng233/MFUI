package site.moheng.mfui.binding.source;


import site.moheng.mfui.binding.AbsValuedObservable;

public final class IntObservable extends AbsValuedObservable<Integer> {
	private int data = 0;
	private Integer boxed = null;

	public int getValue() {
		return data;
	}

	public void set(int data) {
		this.data = data;
		setChange();
	}

	@Override
	public void setChange() {
		boxed = null;
		super.setChange();
	}

	public Integer get() {
		if (boxed == null) {
			boxed = data;
		}

		return boxed;
	}

	public void set(Integer data) {
		this.data = data;
		setChange();
	}
}
