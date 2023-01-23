package site.moheng.mfui.binding.source;

import site.moheng.mfui.binding.IBindingSource;

import java.util.ArrayList;
import java.util.List;

public class BooleanBindingSource implements IBindingSource<BooleanBindingSource> {
	protected List<IEvent<BooleanBindingSource>> listeners = new ArrayList<>();
	protected boolean data = false;

	public boolean get() {
		return data;
	}

	public void set(boolean data) {
		this.data = data;
		submit();
	}

	public void submit() {
		for (var consumer : listeners) {
			consumer.accept(this);
		}
	}

	@Override
	public List<IEvent<BooleanBindingSource>> getListeners() {
		return listeners;
	}
}