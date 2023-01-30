package site.moheng.mfui.binding.attribute;

import site.moheng.mfui.binding.AbsWidgetAttribute;
import site.moheng.mfui.binding.source.BooleanObservable;
import site.moheng.mfui.widget.AbsWidget;

public final class BooleanWidgetAttribute<W extends AbsWidget> extends AbsWidgetAttribute<BooleanObservable, W> {
	public BooleanWidgetAttribute(W widget) {
		super(widget);
		binding(new BooleanObservable());
	}

	public boolean get() {
		return binding.getValue();
	}

	public W set(boolean value) {
		binding.set(value);
		return widget;
	}
}
