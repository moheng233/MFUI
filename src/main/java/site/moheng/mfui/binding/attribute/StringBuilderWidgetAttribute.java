package site.moheng.mfui.binding.attribute;

import site.moheng.mfui.binding.AbsWidgetAttribute;
import site.moheng.mfui.binding.source.IntObservable;
import site.moheng.mfui.binding.source.StringBuilderObservable;
import site.moheng.mfui.widget.AbsWidget;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;

public class StringBuilderWidgetAttribute<W extends AbsWidget> extends AbsWidgetAttribute<StringBuilderObservable, W> {
	public StringBuilderWidgetAttribute(W widget) {
		super(widget);
		binding(new StringBuilderObservable());
	}

	public String getString() {
		return binding.getString();
	}

	public W set(String str) {
		var b = getBinding().data;
		b.setLength(0);
		b.append(str);
		return widget;
	}
}
