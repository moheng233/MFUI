package site.moheng.mfui.binding.source;


import site.moheng.mfui.binding.IBindingSource;

import java.util.ArrayList;
import java.util.List;

public class StringBindingSource implements IBindingSource<StringBindingSource> {
	protected List<IEvent<StringBindingSource>> listeners = new ArrayList<>();
	protected String data = "";

	public String get() {
		return data;
	}

	public void set(String data) {
		this.data = data;
		submit();
	}

	public void submit() {
		for (var consumer : listeners) {
			consumer.accept(this);
		}
	}

	@Override
	public List<IEvent<StringBindingSource>> getListeners() {
		return listeners;
	}
}
