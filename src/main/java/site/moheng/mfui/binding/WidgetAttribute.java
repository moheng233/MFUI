package site.moheng.mfui.binding;

import site.moheng.mfui.widget.AbsWidget;

import java.util.ArrayList;
import java.util.List;

/**
 * 小部件属性的标准实现类
 *
 * @param <S> 属性类型
 * @param <W> 用于链式调用的小部件类型
 */
public class WidgetAttribute<S, W extends AbsWidget> implements IWidgetAttribute<S, W>, IObservable.IObserver<S> {
	protected final W widget;
	protected List<IObserver<S>> listeners = new ArrayList<>();
	protected IObservable<S> binding;
	protected S defaultData;

	public WidgetAttribute(S defaultData, W widget) {
		this.defaultData = defaultData;
		this.widget = widget;
	}

	public IObservable<S> getBinding() {
		return binding;
	}

	public W binding(IObservable<S> source) {
		cleanBinding();
		binding = source;
		binding.addListener(this);

		return widget;
	}

	public void cleanBinding() {
		if (binding != null) {
			binding.removeListener(this);
			binding = null;
		}
	}

	public W put(S data) {
		if (binding == null) {
			defaultData = data;
			setChange();
		} else {
			binding.set(data);
		}

		return widget;
	}

	@Override
	public void set(S data) {
		put(data);
	}

	/**
	 * 当没有绑定时直接触发自身的改变事件
	 * <p>
	 * 当有绑定时先触发绑定的可观察类的改变事件，由绑定的可观察类触发自身的改变事件
	 */
	@Override
	public void setChange() {
		if (binding != null) {
			binding.setChange();
		} else {
			IWidgetAttribute.super.setChange();
		}
	}

	@Override
	public List<IObserver<S>> getListeners() {
		return listeners;
	}

	@Override
	public S get() {
		if (binding == null) {
			return defaultData;
		}
		return binding.get();
	}

	public void change(IObservable<S> source) {
		IWidgetAttribute.super.setChange();
	}
}
