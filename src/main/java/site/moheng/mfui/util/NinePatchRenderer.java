package site.moheng.mfui.util;

import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class NinePatchRenderer implements RectDrawable {
	private final Identifier texture;
	private final int u, v;
	private final Size cornerPatchSize;
	private final Size centerPatchSize;
	private final Size textureSize;
	private final boolean repeat;

	private final BatchDrawable batch;

	public NinePatchRenderer(Identifier texture, Size patchSize, Size textureSize, boolean repeat) {
		this(texture, 0, 0, patchSize, textureSize, repeat);
	}

	public NinePatchRenderer(Identifier texture, int u, int v, Size patchSize, Size textureSize, boolean repeat) {
		this(texture, u, v, patchSize, patchSize, textureSize, repeat);
	}

	public NinePatchRenderer(Identifier texture, int u, int v, Size cornerPatchSize, Size centerPatchSize, Size textureSize, boolean repeat) {
		this.texture = texture;
		this.u = u;
		this.v = v;
		this.textureSize = textureSize;
		this.cornerPatchSize = cornerPatchSize;
		this.centerPatchSize = centerPatchSize;
		this.repeat = repeat;

		batch = new BatchDrawable(texture);
	}

	public void draw(MatrixStack matrices, int x, int y, int width, int height) {
		int rightEdge = this.cornerPatchSize.width() + this.centerPatchSize.width();
		int bottomEdge = this.cornerPatchSize.height() + this.centerPatchSize.height();

		batch.begin();

		batch.drawTexture(matrices, x, y, this.u, this.v, this.cornerPatchSize.width(), this.cornerPatchSize.height(), this.textureSize.width(), this.textureSize.height());
		batch.drawTexture(matrices, x + width - this.cornerPatchSize.width(), y, this.u + rightEdge, this.v, this.cornerPatchSize.width(), this.cornerPatchSize.height(), this.textureSize.width(), this.textureSize.height());
		batch.drawTexture(matrices, x, y + height - this.cornerPatchSize.height(), this.u, this.v + bottomEdge, this.cornerPatchSize.width(), this.cornerPatchSize.height(), this.textureSize.width(), this.textureSize.height());
		batch.drawTexture(matrices, x + width - this.cornerPatchSize.width(), y + height - this.cornerPatchSize.height(), this.u + rightEdge, this.v + bottomEdge, this.cornerPatchSize.width(), this.cornerPatchSize.height(), this.textureSize.width(), this.textureSize.height());

		if (this.repeat) {
			this.drawRepeated(matrices, x, y, width, height);
		} else {
			this.drawStretched(matrices, x, y, width, height);
		}

		batch.draw();
	}

	protected void drawRepeated(MatrixStack matrices, int x, int y, int width, int height) {
		int doubleCornerHeight = this.cornerPatchSize.height() * 2;
		int doubleCornerWidth = this.cornerPatchSize.width() * 2;

		int rightEdge = this.cornerPatchSize.width() + this.centerPatchSize.width();
		int bottomEdge = this.cornerPatchSize.height() + this.centerPatchSize.height();

		if (width > doubleCornerWidth && height > doubleCornerHeight) {
			int leftoverHeight = height - doubleCornerHeight;
			while (leftoverHeight > 0) {
				int drawHeight = Math.min(this.centerPatchSize.height(), leftoverHeight);

				int leftoverWidth = width - doubleCornerWidth;
				while (leftoverWidth > 0) {
					int drawWidth = Math.min(this.centerPatchSize.width(), leftoverWidth);
					batch.drawTexture(matrices, x + this.cornerPatchSize.width() + leftoverWidth - drawWidth, y + this.cornerPatchSize.height() + leftoverHeight - drawHeight, drawWidth, drawHeight, this.u + this.cornerPatchSize.width() + this.centerPatchSize.width() - drawWidth, this.v + this.cornerPatchSize.height() + this.centerPatchSize.height() - drawHeight, drawWidth, drawHeight, this.textureSize.width(), this.textureSize.height());

					leftoverWidth -= this.centerPatchSize.width();
				}
				leftoverHeight -= this.centerPatchSize.height();
			}
		}

		if (width > doubleCornerWidth) {
			int leftoverWidth = width - doubleCornerWidth;
			while (leftoverWidth > 0) {
				int drawWidth = Math.min(this.centerPatchSize.width(), leftoverWidth);

				batch.drawTexture(matrices, x + this.cornerPatchSize.width() + leftoverWidth - drawWidth, y, drawWidth, this.cornerPatchSize.height(), this.u + this.cornerPatchSize.width() + this.centerPatchSize.width() - drawWidth, this.v, drawWidth, this.cornerPatchSize.height(), this.textureSize.width(), this.textureSize.height());
				batch.drawTexture(matrices, x + this.cornerPatchSize.width() + leftoverWidth - drawWidth, y + height - this.cornerPatchSize.height(), drawWidth, this.cornerPatchSize.height(), this.u + this.cornerPatchSize.width() + this.centerPatchSize.width() - drawWidth, this.v + bottomEdge, drawWidth, this.cornerPatchSize.height(), this.textureSize.width(), this.textureSize.height());

				leftoverWidth -= this.centerPatchSize.width();
			}
		}

		if (height > doubleCornerHeight) {
			int leftoverHeight = height - doubleCornerHeight;
			while (leftoverHeight > 0) {
				int drawHeight = Math.min(this.centerPatchSize.height(), leftoverHeight);
				batch.drawTexture(matrices, x, y + this.cornerPatchSize.height() + leftoverHeight - drawHeight, this.cornerPatchSize.width(), drawHeight, this.u, this.v + this.cornerPatchSize.height() + this.centerPatchSize.height() - drawHeight, this.cornerPatchSize.width(), drawHeight, this.textureSize.width(), this.textureSize.height());
				batch.drawTexture(matrices, x + width - this.cornerPatchSize.width(), y + this.cornerPatchSize.height() + leftoverHeight - drawHeight, this.cornerPatchSize.width(), drawHeight, this.u + rightEdge, this.v + this.cornerPatchSize.height() + this.centerPatchSize.height() - drawHeight, this.cornerPatchSize.width(), drawHeight, this.textureSize.width(), this.textureSize.height());

				leftoverHeight -= this.centerPatchSize.height();
			}
		}
	}

	protected void drawStretched(MatrixStack matrices, int x, int y, int width, int height) {
		int doubleCornerHeight = this.cornerPatchSize.height() * 2;
		int doubleCornerWidth = this.cornerPatchSize.width() * 2;

		int rightEdge = this.cornerPatchSize.width() + this.centerPatchSize.width();
		int bottomEdge = this.cornerPatchSize.height() + this.centerPatchSize.height();

		if (width > doubleCornerWidth && height > doubleCornerHeight) {
			batch.drawTexture(matrices, x + this.cornerPatchSize.width(), y + this.cornerPatchSize.height(), width - doubleCornerWidth, height - doubleCornerHeight, this.u + this.cornerPatchSize.width(), this.v + this.cornerPatchSize.height(), this.centerPatchSize.width(), this.centerPatchSize.height(), this.textureSize.width(), this.textureSize.height());
		}

		if (width > doubleCornerWidth) {
			batch.drawTexture(matrices, x + this.cornerPatchSize.width(), y, width - doubleCornerWidth, this.cornerPatchSize.height(), this.u + this.cornerPatchSize.width(), this.v, this.centerPatchSize.width(), this.cornerPatchSize.height(), this.textureSize.width(), this.textureSize.height());
			batch.drawTexture(matrices, x + this.cornerPatchSize.width(), y + height - this.cornerPatchSize.height(), width - doubleCornerWidth, this.cornerPatchSize.height(), this.u + this.cornerPatchSize.width(), this.v + bottomEdge, this.centerPatchSize.width(), this.cornerPatchSize.height(), this.textureSize.width(), this.textureSize.height());
		}

		if (height > doubleCornerHeight) {
			batch.drawTexture(matrices, x, y + this.cornerPatchSize.height(), this.cornerPatchSize.width(), height - doubleCornerHeight, this.u, this.v + this.cornerPatchSize.height(), this.cornerPatchSize.width(), this.centerPatchSize.height(), this.textureSize.width(), this.textureSize.height());
			batch.drawTexture(matrices, x + width - this.cornerPatchSize.width(), y + this.cornerPatchSize.height(), this.cornerPatchSize.width(), height - doubleCornerHeight, this.u + rightEdge, this.v + this.cornerPatchSize.height(), this.cornerPatchSize.width(), this.centerPatchSize.height(), this.textureSize.width(), this.textureSize.height());
		}
	}
}
