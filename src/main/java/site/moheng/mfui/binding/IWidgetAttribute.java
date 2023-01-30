package site.moheng.mfui.binding;

import site.moheng.mfui.widget.AbsWidget;

/**
 * 小部件属性的接口类
 * @param <S> 属性类型
 * @param <W> 用于链式调用的小部件类型
 */
public interface IWidgetAttribute<S extends IObservable, W extends AbsWidget> extends IObservable {
	W binding(S source);

	S getBinding();
}