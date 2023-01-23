package site.moheng.mfui.widget.logic;

import site.moheng.mfui.binding.WidgetAttribute;
import site.moheng.mfui.widget.BaseWidget;

/**
 * 根据功能显示或隐藏子小部件
 */
public class IfWidget extends BaseWidget {
	public final WidgetAttribute<Boolean, IfWidget> basis = new WidgetAttribute<>(true, this);


	public IfWidget() {
		super();
	}
}