package site.moheng.mfui.binding.attribute;

import site.moheng.mfui.binding.IBindingSource;
import site.moheng.mfui.binding.IWidgetAttribute;
import site.moheng.mfui.widget.IWidgetHandler;

import java.util.ArrayList;
import java.util.List;

public abstract class GetterWidgetAttribute<S, W extends IWidgetHandler> implements IWidgetAttribute<S, W> {
	protected final W widget;
	protected List<IEvent<S>> listeners = new ArrayList<>();
	protected IBindingSource<S> binding;

	protected GetterWidgetAttribute(W widget) {
		this.widget = widget;
	}

	@Override
	public List<IEvent<S>> getListeners() {
		return listeners;
	}

	public void cleanBinding() {
		if (binding != null) {
			binding.removeListener(this::change);
			binding = null;
		}
	}

	public void change(IBindingSource<S> source) {
		put(source.get());
		submit();
	}

	@Override
	public W binding(IBindingSource<S> source) {
		cleanBinding();
		return widget;
	}
}