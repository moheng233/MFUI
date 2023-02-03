package site.moheng.mfui;

import net.minecraft.text.Text;
import site.moheng.mfui.binding.source.BooleanObservable;
import site.moheng.mfui.binding.source.StringBuilderObservable;
import site.moheng.mfui.util.RectDrawable;
import site.moheng.mfui.widget.BoxWidget;
import site.moheng.mfui.widget.ScreenWidget;
import site.moheng.mfui.widget.WidgetScreen;
import site.moheng.mfui.widget.enums.*;
import site.moheng.mfui.widget.features.ButtonWidget;
import site.moheng.mfui.widget.features.LabelWidget;
import site.moheng.mfui.widget.features.TextBoxWidget;
import site.moheng.mfui.widget.logic.IfWidget;


public class WidgetTestScreen extends WidgetScreen {
	protected StringBuilderObservable text = new StringBuilderObservable();
	protected BooleanObservable show = new BooleanObservable();

	public WidgetTestScreen() {
		super(Text.of("test"));
	}

	@Override
	public void widget(ScreenWidget root) {
		root.flexDirection.set(WidgetFlexDirection.Row)
				.justifyContent.set(WidgetJustify.Center)
				.alignItems.set(WidgetAlign.Center)
				.child(new BoxWidget()
						.background.set(RectDrawable.LIGHT_PANEL)
						.child(new LabelWidget()
								.text.binding(text.computed((StringBuilderObservable text) -> Text.of(text.getString())))
								.margin(WidgetEdge.All, 4))
						.width.set(WidgetValue.point(200))
						.child(new IfWidget()
								.basis.binding(show)
								.child(new TextBoxWidget()
										.text.binding(text))
						)
						.child(new ButtonWidget()
								.click.on((mouse) -> show.set(!show.getValue()))
								.child(new LabelWidget()
										.text.set(Text.of("切换显示"))))
				);
	}
}
