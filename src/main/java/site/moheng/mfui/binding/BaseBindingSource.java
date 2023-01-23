package site.moheng.mfui.binding;

import java.util.ArrayList;
import java.util.List;

public class BaseBindingSource<S> implements IBindingSource<S> {
	protected List<IEvent<S>> listeners = new ArrayList<>();
	protected S data;

	@Override
	public List<IEvent<S>> getListeners() {
		return listeners;
	}

	@Override
	public void set(S data) {
		this.data = data;
		submit();
	}

	@Override
	public S get() {
		return data;
	}
}
