package site.moheng.mfui.widget.enums;



import site.moheng.mfui.widget.AbsWidget;

import static org.lwjgl.util.yoga.Yoga.*;

public enum WidgetJustify {
    FlexStart(YGJustifyFlexStart),
    Center(YGJustifyCenter),
    FlexEnd(YGJustifyFlexEnd),
    SpaceBetween(YGJustifySpaceBetween),
    SpaceAround(YGJustifySpaceAround),
    SpaceEvenly(YGJustifySpaceEvenly);

    private final int number;

    WidgetJustify(int number) {
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

    public static WidgetJustify getJustifyContent(AbsWidget widget) {
        return WidgetJustify.get(YGNodeStyleGetJustifyContent(widget.getYGNode()));
    }
    public final void setJustifyContent(AbsWidget widget) {
        YGNodeStyleSetJustifyContent(widget.getYGNode(), get());
    }
}
