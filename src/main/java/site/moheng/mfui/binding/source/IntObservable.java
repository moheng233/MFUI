package site.moheng.mfui.binding.source;


import site.moheng.mfui.binding.IObservable;

import java.util.ArrayList;
import java.util.List;

public class IntObservable implements IObservable<Integer> {
	protected List<IObserver<Integer>> listeners = new ArrayList<>();
	protected int data = 0;
	protected Integer boxed = null;

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
		IObservable.super.setChange();
	}

	@Override
	public List<IObserver<Integer>> getListeners() {
		return listeners;
	}

	public void set(Integer data) {
		this.data = data;
		setChange();
	}

	public Integer get() {
		if (boxed == null) {
			boxed = data;
		}

		return boxed;
	}
}
