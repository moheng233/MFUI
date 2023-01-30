package site.moheng.mfui.binding;

import site.moheng.mfui.widget.AbsWidget;

/**
 * 小部件属性的接口类
 * @param <S> 属性类型
 * @param <W> 用于链式调用的小部件类型
 */
public interface IWidgetAttribute<S, W extends AbsWidget> extends IObservable<S> {
	W binding(IObservable<S> source);
	W put(S data);

	@Override
	default void set(S data) {
		put(data);
	}
}
