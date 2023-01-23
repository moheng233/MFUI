package site.moheng.mfui.binding.source;


import site.moheng.mfui.binding.IBindingSource;

import java.util.ArrayList;
import java.util.List;

public class IntBindingSource implements IBindingSource<IntBindingSource> {
	protected List<IEvent<IntBindingSource>> listeners = new ArrayList<>();
	protected int data = 0;

	public int get() {
		return data;
	}

	public void set(int data) {
		this.data = data;
		submit();
	}

	public void submit() {
		for (var consumer : listeners) {
			consumer.accept(this);
		}
	}

	@Override
	public List<IEvent<IntBindingSource>> getListeners() {
		return listeners;
	}
}
