package site.moheng.mfui.widget.enums;


import site.moheng.mfui.widget.AbsWidget;

import static org.lwjgl.util.yoga.Yoga.*;

public enum WidgetFlexDirection {
    Column(YGFlexDirectionColumn),
    ColumnReverse(YGFlexDirectionColumnReverse),
    Row(YGFlexDirectionRow),
    RowReverse(YGFlexDirectionRowReverse);

    private final int number;

    WidgetFlexDirection(int number) {
        this.number = number;
    }

    private static WidgetFlexDirection get(int number) {
        return switch (number) {
            case YGFlexDirectionColumnReverse -> ColumnReverse;
            case YGFlexDirectionRow -> Row;
            case YGFlexDirectionRowReverse -> RowReverse;
            default -> Column;
        };
    }

    public final int get() {
        return number;
    }

    public static WidgetFlexDirection getFlexDirect(AbsWidget widget) {
        return WidgetFlexDirection.get(YGNodeStyleGetFlexDirection(widget.getYGNode()));
    }
    public final void setFlexDirection(AbsWidget widget) {
        YGNodeStyleSetFlexDirection(widget.getYGNode(), this.get());
    }

}
