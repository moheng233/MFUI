package site.moheng.mfui.binding.source;


import site.moheng.mfui.binding.IObservable;

import java.util.ArrayList;
import java.util.List;

public class IntObservable implements IObservable<Integer> {
	protected List<IEvent<Integer>> listeners = new ArrayList<>();
	protected int data = 0;
	protected Integer boxed = null;

	public int getValue() {
		return data;
	}

	public void set(int data) {
		this.data = data;
		submit();
	}

	@Override
	public void submit() {
		boxed = null;
		IObservable.super.submit();
	}

	@Override
	public List<IEvent<Integer>> getListeners() {
		return listeners;
	}

	public void set(Integer data) {
		this.data = data;
		submit();
	}

	public Integer get() {
		if (boxed == null) {
			boxed = data;
		}

		return boxed;
	}
}
