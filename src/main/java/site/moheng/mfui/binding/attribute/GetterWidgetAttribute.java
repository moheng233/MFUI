package site.moheng.mfui.binding.attribute;

import site.moheng.mfui.binding.IObservable;
import site.moheng.mfui.binding.IWidgetAttribute;
import site.moheng.mfui.widget.AbsWidget;

import java.util.ArrayList;
import java.util.List;

public abstract class GetterWidgetAttribute<S, W extends AbsWidget> implements IWidgetAttribute<S, W> {
	protected final W widget;
	protected List<IObserver<S>> listeners = new ArrayList<>();
	protected IObservable<S> binding;

	protected GetterWidgetAttribute(W widget) {
		this.widget = widget;
	}

	@Override
	public List<IObserver<S>> getListeners() {
		return listeners;
	}

	@Override
	public W binding(IObservable<S> source) {
		cleanBinding();
		return widget;
	}

	public void cleanBinding() {
		if (binding != null) {
			binding.removeListener(this::change);
			binding = null;
		}
	}

	public void change(IObservable<S> source) {
		put(source.get());
		setChange();
	}
}