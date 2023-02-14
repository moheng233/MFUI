package site.moheng.mfui.widget.logic;

import site.moheng.mfui.binding.IObservable;
import site.moheng.mfui.binding.IObserver;
import site.moheng.mfui.binding.attribute.ListWidgetAttribute;
import site.moheng.mfui.widget.AbsWidget;

import java.util.List;
import java.util.function.Function;

public class ForeachWidget<ITEM extends IObservable> extends AbsWidget implements IObserver {
	public final ListWidgetAttribute<ITEM, ForeachWidget<ITEM>> data = new ListWidgetAttribute<>(this);
	public Function<ITEM, AbsWidget> builder = null;

	public ForeachWidget(Function<ITEM, AbsWidget> builder) {
		this.builder = builder;
		data.addObserver(this);
	}

	@Override
	public void change() {
		if (builder != null) {
			removeChildAll();
			for (var item : data.getBinding()) {
				var widget = builder.apply(item);
				if (widget != null) {
					child(widget);
				}
			}

		}
	}
}