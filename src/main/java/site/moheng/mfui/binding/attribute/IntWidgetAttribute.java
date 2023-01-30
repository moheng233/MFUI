package site.moheng.mfui.binding.attribute;

import site.moheng.mfui.binding.AbsWidgetAttribute;
import site.moheng.mfui.binding.source.IntObservable;
import site.moheng.mfui.widget.AbsWidget;

public final class IntWidgetAttribute<W extends AbsWidget> extends AbsWidgetAttribute<IntObservable, W> {
	public IntWidgetAttribute(W widget) {
		super(widget);
		binding(new IntObservable());
	}

	public int get() {
		return binding.getValue();
	}

	public W set(int value) {
		binding.set(value);
		return widget;
	}
}
