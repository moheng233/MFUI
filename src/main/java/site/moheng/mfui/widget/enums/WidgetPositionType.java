package site.moheng.mfui.widget.enums;



import site.moheng.mfui.widget.AbsWidget;

import static org.lwjgl.util.yoga.Yoga.*;

public enum WidgetPositionType {
	Static(YGPositionTypeStatic),
	Relative(YGPositionTypeRelative),
	Absolute(YGPositionTypeAbsolute);

	private final int number;

	WidgetPositionType(int number) {
		this.number = number;
	}

	public final int get() {
		return number;
	}

	public static WidgetPositionType get(int number) {
		return switch (number) {
			case YGPositionTypeStatic -> Static;
			case YGPositionTypeRelative -> Relative;
			default -> Absolute;
		};
	}

	public static WidgetPositionType getPositionType(AbsWidget widget) {
		return WidgetPositionType.get(YGNodeStyleGetPositionType(widget.getYGNode()));
	}
	public final void setPositionType(AbsWidget widget) {
		YGNodeStyleSetPositionType(widget.getYGNode(), get());
	}
}
