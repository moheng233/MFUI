package site.moheng.mfui.util;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Rect2i;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector4f;

import java.util.ArrayDeque;
import java.util.Deque;

public final class ScissorStack {
	public static final ScissorStack STACK = new ScissorStack();
	private final Deque<Rect2i> stack = new ArrayDeque<>();

	public void push(int x, int y, int width, int height, MatrixStack matrices) {
		stack.push(withGlTransform(x, y, width, height, matrices));
		apply();
	}

	public static Rect2i withGlTransform(int x, int y, int width, int height, @Nullable MatrixStack matrices) {
		assert matrices != null;

		matrices.push();
		matrices.multiplyPositionMatrix(RenderSystem.getModelViewMatrix());

		var root = new Vector4f(x, y, 0, 1);
		var end = new Vector4f(x + width, y + height, 0, 1);

		root.mul(matrices.peek().getPositionMatrix());
		end.mul(matrices.peek().getPositionMatrix());

		x = (int) root.x;
		y = (int) root.y;

		width = (int) Math.ceil(end.x - root.x);
		height = (int) Math.ceil(end.y - root.y);

		matrices.pop();

		return new Rect2i(x, y, width, height);
	}

	public void apply() {
		var windows = MinecraftClient.getInstance().getWindow();
		var scale = windows.getScaleFactor();
		var newFrame = stack.peek();

		if (newFrame == null) {
			RenderSystem.enableScissor(0, 0, windows.getFramebufferWidth(), windows.getFramebufferHeight());
		} else {
			RenderSystem.enableScissor(
					(int) (newFrame.getX() * scale),
					(int) (newFrame.getY() * scale),
					(int) (newFrame.getWidth() * scale),
					(int) (newFrame.getHeight() * scale));
		}
	}

	public void pop() {
		stack.pop();
		apply();
	}
}
