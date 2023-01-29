package site.moheng.mfui.binding.source;


import site.moheng.mfui.binding.IObservable;

import java.util.ArrayList;
import java.util.List;

public class StringBuilderObservable implements IObservable<StringBuilder> {
	protected List<IEvent<StringBuilder>> listeners = new ArrayList<>();
	public final StringBuilder data = new StringBuilder();
	protected String boxed = null;

	@Override
	public List<IEvent<StringBuilder>> getListeners() {
		return listeners;
	}

	@Override
	public void submit() {
		boxed = null;
		IObservable.super.submit();
	}

	public void append(String data) {
		this.data.append(data);
		submit();
	}

	public void append(char c) {
		data.append(c);
		submit();
	}

	public void insert(int offset, char c) {
		data.insert(offset, c);
		submit();
	}

	public int length() {
		return data.length();
	}

	public void delete(int start, int end) {
		data.delete(start, end);
		submit();
	}

	public void deleteAtChar(int index) {
		data.deleteCharAt(index);
		submit();
	}

	public void set(String str) {
		this.data.setLength(0);
		this.data.append(data);
		submit();
	}

	public void set(StringBuilder data) {

	}

	public StringBuilder get() {
		return data;
	}

	public String toString() {
		return data.toString();
	}

	public IObservable<String> asString() {
		return computed(StringBuilder::toString);
	}
}
