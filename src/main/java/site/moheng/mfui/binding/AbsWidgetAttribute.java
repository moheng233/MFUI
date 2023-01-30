package site.moheng.mfui.binding;

import site.moheng.mfui.widget.AbsWidget;

/**
 * 小部件属性的标准实现类
 *
 * @param <S> 属性类型
 * @param <W> 用于链式调用的小部件类型
 */
public abstract class AbsWidgetAttribute<S extends IObservable, W extends AbsWidget> extends AbsObservable implements IWidgetAttribute<S, W>, IObserver {
	protected final W widget;
	protected S binding;

	public AbsWidgetAttribute(W widget) {
		this.widget = widget;
	}

	@Override
	public W binding(S source) {
		cleanBinding();
		binding = source;
		binding.addObserver(this);
		return widget;
	}

	public S getBinding() {
		return binding;
	}

	public void cleanBinding() {
		if (binding != null) {
			binding.removeObserver(this);
			binding = null;
		}
	}

	@Override
	public void change() {
		setChange();
	}
}
