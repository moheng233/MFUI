package site.moheng.mfui.binding.attribute;

import site.moheng.mfui.binding.AbsObservable;
import site.moheng.mfui.binding.AbsWidgetAttribute;
import site.moheng.mfui.binding.IObservable;
import site.moheng.mfui.binding.source.ListObservable;
import site.moheng.mfui.widget.AbsWidget;

import java.util.List;
import java.util.stream.Stream;

public class ListWidgetAttribute<ITEM extends IObservable, W extends AbsWidget> extends AbsWidgetAttribute<ListObservable<ITEM>, W> {
	public ListWidgetAttribute(W widget) {
		super(widget);
		binding(new ListObservable<>());
	}

	public Stream<ITEM> items() {
		return getBinding().stream();
	}
}