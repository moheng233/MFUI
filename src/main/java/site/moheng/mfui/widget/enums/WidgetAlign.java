package site.moheng.mfui.widget.enums;


import site.moheng.mfui.widget.AbsWidget;

import static org.lwjgl.util.yoga.Yoga.*;

public enum WidgetAlign {
	Auto(YGAlignAuto),
	FlexStart(YGAlignFlexStart),
	Center(YGAlignCenter),
	FlexEnd(YGAlignFlexEnd),
	Stretch(YGAlignStretch),
	Baseline(YGAlignBaseline),
	SpaceBetween(YGAlignSpaceBetween),
	SpaceAround(YGAlignSpaceAround);

	private final int number;

	WidgetAlign(int number) {
		this.number = number;
	}

	public static WidgetAlign getAlignContent(AbsWidget widget) {
		return WidgetAlign.get(YGNodeStyleGetAlignContent(widget.getYGNode()));
	}

	public static WidgetAlign get(int number) {
		return switch (number) {
			case YGAlignAuto -> Auto;
			case YGAlignFlexStart -> FlexStart;
			case YGAlignCenter -> Center;
			case YGAlignFlexEnd -> FlexEnd;
			case YGAlignStretch -> Stretch;
			case YGAlignBaseline -> Baseline;
			case YGAlignSpaceBetween -> SpaceBetween;
			default -> SpaceAround;
		};
	}

	public static WidgetAlign getAlignItems(AbsWidget widget) {
		return WidgetAlign.get(YGNodeStyleGetAlignItems(widget.getYGNode()));
	}

	public static WidgetAlign getAlignSelf(AbsWidget widget) {
		return WidgetAlign.get(YGNodeStyleGetAlignSelf(widget.getYGNode()));
	}

	public final void setAlignContent(AbsWidget widget) {
		YGNodeStyleSetAlignContent(widget.getYGNode(), get());
	}

	public int get() {
		return number;
	}

	public final void setAlignItems(AbsWidget widget) {
		YGNodeStyleSetAlignItems(widget.getYGNode(), get());
	}

	public final void setAlignSelf(AbsWidget widget) {
		YGNodeStyleSetAlignSelf(widget.getYGNode(), get());
	}
}
