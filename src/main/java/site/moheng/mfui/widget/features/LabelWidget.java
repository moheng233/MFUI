package site.moheng.mfui.widget.features;

import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.util.yoga.YGMeasureFuncI;
import org.lwjgl.util.yoga.YGSize;
import org.lwjgl.util.yoga.Yoga;
import site.moheng.mfui.binding.attribute.IntWidgetAttribute;
import site.moheng.mfui.binding.attribute.SimpleValuedWidgetAttribute;
import site.moheng.mfui.widget.AbsWidget;
import site.moheng.mfui.widget.enums.WidgetType;


public class LabelWidget extends AbsWidget implements YGMeasureFuncI {
	public final SimpleValuedWidgetAttribute<Text, LabelWidget> text = new SimpleValuedWidgetAttribute<>(Text.empty(), this);
	public final IntWidgetAttribute<LabelWidget> color = new IntWidgetAttribute<>(this);

	public LabelWidget() {
		WidgetType.Text.setNodeType(this);
		Yoga.YGNodeSetMeasureFunc(getYGNode(), this);
		color.set(0xffffff);

		text.addObserver(() -> Yoga.YGNodeMarkDirty(getYGNode()));
	}

	@Override
	public void draw(MatrixStack matrices, int mouseX, int mouseY, float partialTicks, float delta) {
		super.draw(matrices, mouseX, mouseY, partialTicks, delta);
		if (textRenderer.getWidth(text.get()) > layout.width()) {
			var ts = textRenderer.wrapLines(text.get(), layout.width());
			var offset = 0;
			for (var t : ts) {
				textRenderer.draw(matrices, t, layout.x(), layout.y() + offset, color.get());
				offset += textRenderer.fontHeight;
			}
		} else {
			textRenderer.draw(matrices, text.get(), layout.x(), layout.y(), color.get());
		}
	}

	@Override
	public void invoke(long node, float width, int widthMode, float height, int heightMode, @NotNull YGSize __result) {
		__result.height(textRenderer.fontHeight);
		__result.width(width);
		var t = text.get();

		if (widthMode == Yoga.YGMeasureModeUndefined) {
			__result.width(textRenderer.getWidth(t));
		} else {
			var ts = textRenderer.wrapLines(t, (int) width);
			__result.width(ts.stream().map(textRenderer::getWidth).max(Integer::compare).orElse(0).floatValue());
			__result.height(textRenderer.fontHeight * ts.size());
		}
	}
}
