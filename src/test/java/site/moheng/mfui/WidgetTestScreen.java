package site.moheng.mfui;

import net.minecraft.text.Text;
import site.moheng.mfui.binding.source.StringBuilderObservable;
import site.moheng.mfui.util.RectDrawable;
import site.moheng.mfui.widget.BoxWidget;
import site.moheng.mfui.widget.features.LabelWidget;
import site.moheng.mfui.widget.ScreenWidget;
import site.moheng.mfui.widget.WidgetScreen;
import site.moheng.mfui.widget.enums.WidgetAlign;
import site.moheng.mfui.widget.enums.WidgetEdge;
import site.moheng.mfui.widget.enums.WidgetFlexDirection;
import site.moheng.mfui.widget.enums.WidgetJustify;
import site.moheng.mfui.widget.features.ButtonWidget;
import site.moheng.mfui.widget.features.TextBoxWidget;


public class WidgetTestScreen extends WidgetScreen {
	protected StringBuilderObservable text = new StringBuilderObservable();

	public WidgetTestScreen() {
		super(Text.of("test"));
	}

	@Override
	public void widget(ScreenWidget root) {
		root.flexDirection.put(WidgetFlexDirection.Row)
				.justifyContent.put(WidgetJustify.Center)
				.alignItems.put(WidgetAlign.Center)
				.child(new BoxWidget()
						.background.put(RectDrawable.LIGHT_PANEL)
						.child(new LabelWidget()
								.text.binding(text.asString().computed(Text::of))
								.margin(WidgetEdge.All, 4))
						.child(new TextBoxWidget()
								.text.binding(text))
						.child(new ButtonWidget()
								.click.on((mouse) -> close())
								.child(new LabelWidget()
										.text.put(Text.of("取消"))))
				);
	}
}
