package site.moheng.mfui.binding.attribute;

import site.moheng.mfui.binding.AbsValuedObservable;
import site.moheng.mfui.binding.AbsWidgetAttribute;
import site.moheng.mfui.widget.AbsWidget;

public abstract class GetterWidgetAttribute<V, W extends AbsWidget> extends AbsWidgetAttribute<AbsValuedObservable<V>, W> {
	protected GetterWidgetAttribute(W widget) {
		super(widget);
	}

	@Override
	public void change() {
		set(binding.get());
		super.change();
	}

	public abstract W set(V value);

	public abstract V get();

}