package site.moheng.mfui.widget.enums;

import org.lwjgl.util.yoga.YGValue;
import org.lwjgl.util.yoga.Yoga;
import site.moheng.mfui.widget.AbsWidget;

public record WidgetValue(float value, WidgetUnit unit) {
	private static final WidgetValue undefined = new WidgetValue(0, WidgetUnit.UNDEFINED);
	private static final WidgetValue auto = new WidgetValue(0, WidgetUnit.AUTO);

	public static WidgetValue undefined() {
		return undefined;
	}

	public static WidgetValue point(float value) {
		return new WidgetValue(value, WidgetUnit.POINT);
	}

	public static WidgetValue percent(float value) {
		return new WidgetValue(value, WidgetUnit.PERCENT);
	}

	public static WidgetValue auto() {
		return auto;
	}

	public static WidgetValue from(YGValue value) {
		return switch (value.unit()) {
			case Yoga.YGUnitAuto -> auto();
			case Yoga.YGUnitUndefined -> undefined();
			default -> new WidgetValue(value.value(), WidgetUnit.get(value.unit()));
		};
	}

	public void setWidth(AbsWidget widget) {
		switch (unit) {
			case POINT -> Yoga.YGNodeStyleSetWidth(widget.getYGNode(), value);
			case PERCENT -> Yoga.YGNodeStyleSetWidthPercent(widget.getYGNode(), value);
			case AUTO -> Yoga.YGNodeStyleSetWidthAuto(widget.getYGNode());
		}
	}

	public void setHeight(AbsWidget widget) {
		switch (unit) {
			case POINT -> Yoga.YGNodeStyleSetHeight(widget.getYGNode(), value);
			case PERCENT -> Yoga.YGNodeStyleSetHeightPercent(widget.getYGNode(), value);
			case AUTO -> Yoga.YGNodeStyleSetHeightAuto(widget.getYGNode());
		}
	}
}