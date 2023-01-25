package site.moheng.mfui.binding.source;


import site.moheng.mfui.binding.IBindingSource;

import java.util.ArrayList;
import java.util.List;

public class StringBindingSource implements IBindingSource<String> {
	protected List<IEvent<String>> listeners = new ArrayList<>();
	public final StringBuilder data = new StringBuilder();
	protected String boxed = null;

	@Override
	public List<IEvent<String>> getListeners() {
		return listeners;
	}

	@Override
	public void submit() {
		boxed = null;
		IBindingSource.super.submit();
	}

	public void append(String data) {
		this.data.append(data);
		submit();
	}

	public void append(char c) {
		data.append(c);
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

	public void set(String data) {
		this.data.setLength(0);
		this.data.append(data);
		submit();
	}

	public String get() {
		if (boxed == null) {
			boxed = data.toString();
		}

		return boxed;
	}
}
