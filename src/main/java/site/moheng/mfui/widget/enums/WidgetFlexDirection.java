package site.moheng.mfui.widget.enums;


import site.moheng.mfui.widget.IWidget;

import static org.lwjgl.util.yoga.Yoga.*;

public enum WidgetFlexDirection {
    Column(YGFlexDirectionColumn),
    ColumnReverse(YGFlexDirectionColumnReverse),
    Row(YGFlexDirectionRow),
    RowReverse(YGFlexDirectionRowReverse);

    private final int number;

    private WidgetFlexDirection(int number) {
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

    public static WidgetFlexDirection getFlexDirect(IWidget widget) {
        return WidgetFlexDirection.get(YGNodeStyleGetFlexDirection(widget.getYGNode()));
    }
    public final void setFlexDirection(IWidget widget) {
        YGNodeStyleSetFlexDirection(widget.getYGNode(), this.get());
    }

}
