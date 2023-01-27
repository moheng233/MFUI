package site.moheng.mfui.binding.attribute;

import site.moheng.mfui.widget.AbsWidget;

public abstract class FloatGetterWidgetAttribute<W extends AbsWidget> extends GetterWidgetAttribute<Float, W> {
	protected FloatGetterWidgetAttribute(W widget) {
		super(widget);
	}

	public abstract float getValue();

	@Override
	public Float get() {
		return getValue();
	}

	public abstract W put(float data);

	@Override
	public W put(Float data) {
		return put(data.floatValue());
	}
}
