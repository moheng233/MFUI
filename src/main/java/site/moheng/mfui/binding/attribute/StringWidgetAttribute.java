package site.moheng.mfui.binding.attribute;

import site.moheng.mfui.binding.IBindingSource;
import site.moheng.mfui.binding.IWidgetAttribute;
import site.moheng.mfui.binding.source.StringBindingSource;
import site.moheng.mfui.widget.AbsWidget;

import java.util.ArrayList;
import java.util.List;

public class StringWidgetAttribute<W extends AbsWidget> implements IWidgetAttribute<String, W> {
	protected final W widget;
	protected List<IEvent<String>> listeners = new ArrayList<>();
	protected IBindingSource<String> binding = new StringBindingSource();

	public StringWidgetAttribute(W widget) {
		this.widget = widget;
	}

	@Override
	public List<IEvent<String>> getListeners() {
		return listeners;
	}

	@Override
	public String get() {
		return binding.get();
	}

	@Override
	public W binding(IBindingSource<String> source) {
		this.binding = source;
		return widget;
	}

	@Override
	public W put(String data) {
		this.binding.set(data);
		return widget;
	}

	public W append(char data) {
		if (binding instanceof StringBindingSource source) {
			source.append(data);
		} else {
			put(get() + data);
		}

		return widget;
	}

	public W insert(int offset, char c) {
		if (binding instanceof StringBindingSource source) {
			source.insert(offset, c);
		} else {
			put(new StringBuilder(get()).insert(offset, c).toString());
		}

		return widget;
	}

	public W delete(int start, int end) {
		if (binding instanceof StringBindingSource source) {
			source.delete(start, end);
		} else {
			put(new StringBuilder(get()).delete(start, end).toString());
		}

		return widget;
	}

	public int length() {
		if (binding instanceof StringBindingSource source) {
			return source.length();
		} else {
			return get().length();
		}
	}

	public W deleteAtChar(int index) {
		if (binding instanceof StringBindingSource source) {
			source.deleteAtChar(index);
		} else {
			put(new StringBuilder(get()).deleteCharAt(index).toString());
		}

		return widget;
	}
}
