package site.moheng.mfui.binding;

import site.moheng.mfui.widget.IWidgetHandler;

import java.util.ArrayList;
import java.util.List;


public class WidgetAttribute<S, W extends IWidgetHandler> implements IWidgetAttribute<S, W> {
	protected final W widget;
	protected List<IEvent<S>> listeners = new ArrayList<>();
	protected IBindingSource<S> binding;
	protected S defaultData;
	public WidgetAttribute(S defaultData, W widget) {
		this.defaultData = defaultData;
		this.widget = widget;
	}

	public IBindingSource<S> getBinding() {
		return binding;
	}

	public W binding(IBindingSource<S> source) {
		cleanBinding();
		binding = source;
		binding.addListener(this::change);

		return widget;
	}

	public void cleanBinding() {
		if (binding != null) {
			binding.removeListener(this::change);
			binding = null;
		}
	}

	public void change(IBindingSource<S> source) {
		submit();
	}

	@Override
	public List<IEvent<S>> getListeners() {
		return listeners;
	}

	@Override
	public void set(S data) {
		put(data);
	}

	public W put(S data) {
		if (binding == null) {
			defaultData = data;
			submit();
		} else {
			binding.set(data);
		}

		return widget;
	}

	@Override
	public S get() {
		if (binding == null) {
			return defaultData;
		}
		return binding.get();
	}
}
