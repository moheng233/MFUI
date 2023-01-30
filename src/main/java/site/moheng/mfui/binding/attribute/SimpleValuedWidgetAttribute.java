package site.moheng.mfui.binding.attribute;

import site.moheng.mfui.binding.AbsValuedObservable;
import site.moheng.mfui.binding.AbsWidgetAttribute;
import site.moheng.mfui.binding.source.SimpleValuedObservable;
import site.moheng.mfui.widget.AbsWidget;

public final class SimpleValuedWidgetAttribute<V, W extends AbsWidget> extends AbsWidgetAttribute<AbsValuedObservable<V>, W> {

	public SimpleValuedWidgetAttribute(V defaultValue, W widget) {
		super(widget);
		binding(new SimpleValuedObservable<>(defaultValue));
	}


	public V get() {
		return binding.get();
	}


	public W set(V value) {
		binding.set(value);
		return widget;
	}
}
