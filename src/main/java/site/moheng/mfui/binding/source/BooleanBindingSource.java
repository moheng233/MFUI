package site.moheng.mfui.binding.source;

import site.moheng.mfui.binding.IBindingSource;

import java.util.ArrayList;
import java.util.List;

public class BooleanBindingSource implements IBindingSource<Boolean> {
	protected List<IEvent<Boolean>> listeners = new ArrayList<>();
	protected boolean data = false;

	public boolean getValue() {
		return data;
	}

	public void set(boolean data) {
		this.data = data;
		submit();
	}

	@Override
	public List<IEvent<Boolean>> getListeners() {
		return listeners;
	}

	public void set(Boolean data) {
		this.data = data;
		submit();
	}

	public Boolean get() {
		return data;
	}
}