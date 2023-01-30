package site.moheng.mfui.binding;

import java.util.ArrayList;
import java.util.List;

public abstract class AbsObservable implements IObservable {

	protected List<IObserver> listeners = new ArrayList<>();

	@Override
	public void setChange() {
		for (var observer : getObservers()) {
			observer.change();
		}
	}

	@Override
	public List<IObserver> getObservers() {
		return listeners;
	}
}
