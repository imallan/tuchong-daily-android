package uk.co.imallan.tuchongdaily.ui.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

public class CircularImageView extends ImageView {

	private int shadow;

	private int shadowColor;

	public CircularImageView(Context context) {
		this(context, null);
	}

	public CircularImageView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public CircularImageView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		this.shadow = 0;
		this.shadowColor = -1;
	}

	@Override
	public void setImageDrawable(Drawable drawable) {
		RoundedDrawable roundedDrawable = null;
		if (drawable instanceof BitmapDrawable) {
			Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
			if (bitmap != null) {
				roundedDrawable = new RoundedDrawable(bitmap);
			}
		}
		if (roundedDrawable != null) {
			super.setImageDrawable(roundedDrawable);
		} else {
			super.setImageDrawable(drawable);
		}
	}


	class RoundedDrawable extends Drawable {

		private final BitmapShader bitmapShader;

		private final Paint paint;

		private final int x;

		private final int y;

		private final int radius;

		RoundedDrawable(Bitmap bitmap) {
			int width = bitmap.getWidth();
			int height = bitmap.getHeight();
			bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
			paint = new Paint();
			paint.setAntiAlias(true);
			x = width / 2;
			y = height / 2;
			radius = Math.min(width, height) / 2;
		}

		@Override
		public void draw(Canvas canvas) {
			int width = getWidth();
			int height = getHeight();
			if (shadowColor != -1) {
				paint.setShader(null);
				paint.setColor(shadowColor);
				canvas.drawCircle(width / 2, height / 2, Math.min(width, height / 2), paint);
			}
			paint.setShader(bitmapShader);
			float xScale = (float) (width - shadow * 2) / (radius * 2);
			float yScale = (float) (height - shadow * 2) / (radius * 2);
			canvas.translate((radius - x) * xScale, (radius - y) * yScale);
			canvas.scale(xScale, yScale);
			canvas.drawCircle(x + shadow / xScale, y + shadow / yScale, radius, paint);
		}

		@Override
		public int getOpacity() {
			return PixelFormat.TRANSLUCENT;
		}

		@Override
		public void setAlpha(int alpha) {
			paint.setAlpha(alpha);
		}

		@Override
		public void setColorFilter(ColorFilter cf) {
			paint.setColorFilter(cf);
		}
	}
}
