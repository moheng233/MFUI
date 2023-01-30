package site.moheng.mfui.widget.logic;

import site.moheng.mfui.binding.attribute.BooleanWidgetAttribute;
import site.moheng.mfui.widget.AbsWidget;

/**
 * 根据功能显示或隐藏子小部件
 */
public class IfWidget extends AbsWidget {
	public final BooleanWidgetAttribute<IfWidget> basis = new BooleanWidgetAttribute<>(this);


	public IfWidget() {
		super();
	}
}