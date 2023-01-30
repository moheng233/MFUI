package site.moheng.mfui.binding.source;


import site.moheng.mfui.binding.IObservable;

import java.util.ArrayList;
import java.util.List;

public class StringBuilderObservable implements IObservable<StringBuilder> {
	protected List<IObserver<StringBuilder>> listeners = new ArrayList<>();
	public final StringBuilder data = new StringBuilder();
	protected String boxed = null;

	@Override
	public List<IObserver<StringBuilder>> getListeners() {
		return listeners;
	}

	@Override
	public void setChange() {
		boxed = null;
		IObservable.super.setChange();
	}

	public void set(StringBuilder data) {

	}

	public StringBuilder get() {
		return data;
	}

	public String toString() {
		if(boxed == null) {
			boxed = data.toString();
		}
		return boxed;
	}

	public IObservable<String> asString() {
		return computed((b) -> toString());
	}
}
