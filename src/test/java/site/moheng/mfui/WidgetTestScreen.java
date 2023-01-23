package site.moheng.mfui;

import net.minecraft.text.Text;
import net.minecraft.util.math.MathHelper;
import site.moheng.mfui.binding.source.IntBindingSource;
import site.moheng.mfui.binding.source.TextBindingSource;
import site.moheng.mfui.widget.BoxWidget;
import site.moheng.mfui.widget.LabelWidget;
import site.moheng.mfui.widget.ScreenWidget;
import site.moheng.mfui.widget.WidgetScreen;
import site.moheng.mfui.widget.enums.WidgetAlign;
import site.moheng.mfui.widget.enums.WidgetEdge;
import site.moheng.mfui.widget.enums.WidgetFlexDirection;
import site.moheng.mfui.widget.enums.WidgetJustify;


public class WidgetTestScreen extends WidgetScreen {
	protected IntBindingSource count = new IntBindingSource();

	public WidgetTestScreen() {
		super(Text.of("test"));
	}

	@Override
	public void tick() {
		super.tick();
		count.set((count.get() + 20) % 765);
	}

	@Override
	public void widget(ScreenWidget root) {
		root.flexDirection(WidgetFlexDirection.Row)
				.justifyContent(WidgetJustify.Center)
				.alignItems(WidgetAlign.Center)
				.child(new BoxWidget()
						.child(new LabelWidget()
								.text.binding(
										count.computed(TextBindingSource::new,
												((source, target) -> target.set(Text.of("Count:" + source.get())))))
								.color.binding(
										count.computed(IntBindingSource::new,
												((source, target) -> target.set(MathHelper.packRgb(count.get() % 255, MathHelper.clamp(count.get() - 255, 0, 255), MathHelper.clamp(count.get() - 510, 0, 255)))))
								)
								.margin(WidgetEdge.All, 4))
				);
	}
}
