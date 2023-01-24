package site.moheng.mfui.widget.features;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.sound.SoundEvents;
import org.joml.Vector2i;
import site.moheng.mfui.binding.WidgetAttribute;
import site.moheng.mfui.event.WidgetEvent;
import site.moheng.mfui.util.RectDrawable;
import site.moheng.mfui.widget.AbsWidget;
import site.moheng.mfui.widget.enums.WidgetAlign;
import site.moheng.mfui.widget.enums.WidgetEdge;
import site.moheng.mfui.widget.enums.WidgetJustify;

public class ButtonWidget extends AbsWidget {
	public final WidgetAttribute<Boolean, ButtonWidget> disabled = new WidgetAttribute<>(false, this);
	public final WidgetEvent<Vector2i, ButtonWidget> click = new WidgetEvent<>(this);

	public ButtonWidget() {
		super();
		border(WidgetEdge.All, 4);
		justifyContent(WidgetJustify.Center);
		alignItems(WidgetAlign.Center);

		click.on((mouse) -> playDownSound());
	}

	public void playDownSound() {
		MinecraftClient.getInstance().getSoundManager().play(PositionedSoundInstance.master(SoundEvents.UI_BUTTON_CLICK, 1.0F));
	}

	@Override
	public void draw(MatrixStack matrices, int mouseX, int mouseY, float partialTicks, float delta) {
		(disabled.get() ?
				RectDrawable.BUTTON_DISABLED :
				(isMouseOver(mouseX, mouseY) ?
						RectDrawable.HOVERED_BUTTON :
						RectDrawable.ACTIVE_BUTTON))
				.draw(matrices, layoutX(), layoutY(), layoutWidth(), layoutHeight());
		super.draw(matrices, mouseX, mouseY, partialTicks, delta);
	}

	@Override
	public boolean mouseClicked(double mouseX, double mouseY, int button) {
		if (super.mouseClicked(mouseX, mouseY, button)) {
			return false;
		}

		if (isMouseOver(mouseX, mouseY)) {
			click.emit(new Vector2i((int) mouseX, (int) mouseY));
			return true;
		}

		return false;
	}
}