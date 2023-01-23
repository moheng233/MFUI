package site.moheng.mfui.widget.enums;



import site.moheng.mfui.widget.IWidget;

import static org.lwjgl.util.yoga.Yoga.*;

public enum WidgetType {
	Default(YGNodeTypeDefault),
	Text(YGNodeTypeText);

	private final int number;

	WidgetType(int number) {
		this.number = number;
	}

	public final int get() {
		return number;
	}

	public static WidgetType get(int number) {
		if(number == YGNodeTypeDefault) {
			return Default;
		}

		return Text;
	}

	public static  WidgetType getNodeType(IWidget widget) {
		return WidgetType.get(YGNodeGetNodeType(widget.getYGNode()));
	}
	public final void setNodeType(IWidget widget) {
		YGNodeSetNodeType(widget.getYGNode(), get());
	}
}
