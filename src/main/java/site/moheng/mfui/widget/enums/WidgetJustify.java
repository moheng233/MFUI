package site.moheng.mfui.widget.enums;



import site.moheng.mfui.widget.IWidget;

import static org.lwjgl.util.yoga.Yoga.*;

public enum WidgetJustify {
    FlexStart(YGJustifyFlexStart),
    Center(YGJustifyCenter),
    FlexEnd(YGJustifyFlexEnd),
    SpaceBetween(YGJustifySpaceBetween),
    SpaceAround(YGJustifySpaceAround),
    SpaceEvenly(YGJustifySpaceEvenly);

    private final int number;

    private WidgetJustify(int number) {
        this.number = number;
    }

    public final int get() {
        return number;
    }

    public static WidgetJustify get(int number) {
        return switch (number) {
            case YGJustifyFlexStart -> FlexStart;
            case YGJustifyCenter -> Center;
            case YGJustifyFlexEnd -> FlexEnd;
            case YGJustifySpaceBetween -> SpaceBetween;
            case YGJustifySpaceAround -> SpaceAround;
            default -> SpaceEvenly;
        };
    }

    public static WidgetJustify getJustifyContent(IWidget widget) {
        return WidgetJustify.get(YGNodeStyleGetJustifyContent(widget.getYGNode()));
    }
    public final void setJustifyContent(IWidget widget) {
        YGNodeStyleSetJustifyContent(widget.getYGNode(), get());
    }
}
