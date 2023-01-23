package site.moheng.mfui.widget.enums;

import org.lwjgl.util.yoga.YGValue;
import site.moheng.mfui.widget.IWidget;


import static org.lwjgl.util.yoga.Yoga.*;

public enum WidgetEdge {
    Left(YGEdgeLeft),
    Top(YGEdgeTop),
    Right(YGEdgeRight),
    Bottom(YGEdgeBottom),
    Start(YGEdgeStart),
    End(YGEdgeEnd),
    Horizontal(YGEdgeHorizontal),
    Vertica(YGEdgeVertical),
    All(YGEdgeAll);

    private static final YGValue VALUE = YGValue.create();

    private final int number;

    /**
     * @param number
     */
    WidgetEdge(int number) {
        this.number = number;
    }

    
    public final int get() {
        return number;
    }

    public final float getPosition(IWidget widget) {
        VALUE.clear();
        return YGNodeStyleGetPosition(widget.getYGNode(), get(), VALUE).value();
    }
    public final void setPosition(IWidget widget, float value) {
        YGNodeStyleSetPosition(widget.getYGNode(), get(), value);
    }

    public final void setMargin(IWidget widget, float margin) {
        YGNodeStyleSetMargin(widget.getYGNode(), get(), margin);
    }

    public final float getMargin(IWidget widget) {
        VALUE.clear();
        return YGNodeStyleGetMargin(widget.getYGNode(), get(), VALUE).value();
    }

    public final void setPadding(IWidget widget, float padding) {
        YGNodeStyleSetPadding(widget.getYGNode(), get(), padding);
    }

    public final float getPadding(IWidget widget) {
        VALUE.clear();
        return YGNodeStyleGetPadding(widget.getYGNode(), get(), VALUE).value();
    }

    public final void setBorder(IWidget widget, float border) {
        YGNodeStyleSetBorder(widget.getYGNode(), get(), border);
    }

    public final float getBorder(IWidget widget) {
        return YGNodeStyleGetBorder(widget.getYGNode(), get());
    }
}
