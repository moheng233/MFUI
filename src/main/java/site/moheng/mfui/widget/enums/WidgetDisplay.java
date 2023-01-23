package site.moheng.mfui.widget.enums;


import site.moheng.mfui.widget.IWidget;

import static org.lwjgl.util.yoga.Yoga.*;

public enum WidgetDisplay {
	Flex(YGDisplayFlex),
	None(YGDisplayNone);

	private final int number;

	WidgetDisplay(int number) {
		this.number = number;
	}

	public static WidgetDisplay get(int number) {
		if(number == YGDisplayFlex) {
			return Flex;
		}

		return None;
	}

	public int get() {
		return number;
	}

	public static WidgetDisplay getDisplay(IWidget widget) {
		return WidgetDisplay.get(YGNodeStyleGetDisplay(widget.getYGNode()));
	}
	public void setDisplay(IWidget widget) {
		YGNodeStyleSetDisplay(widget.getYGNode(), this.get());
	}
}
