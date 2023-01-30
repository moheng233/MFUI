package site.moheng.mfui.binding.source;

import site.moheng.mfui.binding.IObservable;

import java.util.ArrayList;
import java.util.List;

public class BooleanObservable implements IObservable<Boolean> {
	protected List<IObserver<Boolean>> listeners = new ArrayList<>();
	protected boolean data = false;

	public boolean getValue() {
		return data;
	}

	public void set(boolean data) {
		this.data = data;
		setChange();
	}

	@Override
	public List<IObserver<Boolean>> getListeners() {
		return listeners;
	}

	public void set(Boolean data) {
		this.data = data;
		setChange();
	}

	public Boolean get() {
		return data;
	}
}