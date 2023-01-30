package site.moheng.mfui.binding.attribute;

import site.moheng.mfui.binding.AbsWidgetAttribute;
import site.moheng.mfui.binding.source.StringBuilderObservable;
import site.moheng.mfui.widget.AbsWidget;

public class StringBuilderWidgetAttribute<W extends AbsWidget> extends AbsWidgetAttribute<StringBuilderObservable, W> {
	public StringBuilderWidgetAttribute(W widget) {
		super(widget);
		binding(new StringBuilderObservable());
	}

	public String getString() {
		return binding.getString();
	}
}
