package site.moheng.mfui.widget;

import static org.lwjgl.util.yoga.Yoga.YGNodeCalculateLayout;

public interface IWidgetScreen {
    float getScreenWidth();
    float getScreenHeight();

    int getScreenOwnerDirection();

    ScreenWidget root();

    /**
     * 打开Screen时生成组件
     */
    void widget(ScreenWidget root);

    default void layout() {
        YGNodeCalculateLayout(root().getYGNode(), getScreenWidth(), getScreenHeight(), getScreenOwnerDirection());
    }
}
