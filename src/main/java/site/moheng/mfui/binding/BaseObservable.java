package site.moheng.mfui.binding;

import java.util.ArrayList;
import java.util.List;

public class BaseObservable<S> implements IObservable<S> {
	protected List<IObserver<S>> listeners = new ArrayList<>();
	protected S data;

	@Override
	public List<IObserver<S>> getListeners() {
		return listeners;
	}

	@Override
	public void set(S data) {
		this.data = data;
		setChange();
	}

	@Override
	public S get() {
		return data;
	}
}
