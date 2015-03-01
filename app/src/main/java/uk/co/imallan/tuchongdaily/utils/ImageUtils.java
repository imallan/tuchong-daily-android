package uk.co.imallan.tuchongdaily.utils;

import android.graphics.Bitmap;

import com.squareup.picasso.Transformation;

/**
 * Created by allan on 15/2/28.
 */
public class ImageUtils {

	public static class LimitImageSizeTransformation implements Transformation {

		public static enum QUALITY {

			QUALITY_1440P,
			QUALITY_1080P,
			QUALITY_720P,
			QUALITY_THUMBNAILS,
			QUALITY_THUMBNAILS_TINY;
		}

		private QUALITY mQuality = QUALITY.QUALITY_1080P;

		public LimitImageSizeTransformation() {
			this(QUALITY.QUALITY_1080P);
		}

		public LimitImageSizeTransformation(QUALITY quality) {
			this.mQuality = quality;
			switch (mQuality) {
				default:
				case QUALITY_1080P:
					standardHeight = 1920;
					standardWidth = 1080;
					break;
				case QUALITY_1440P:
					standardHeight = 2560;
					standardWidth = 1440;
					break;
				case QUALITY_720P:
					standardHeight = 1280;
					standardWidth = 720;
					break;
				case QUALITY_THUMBNAILS:
					standardHeight = 640;
					standardWidth = 480;
					break;
				case QUALITY_THUMBNAILS_TINY:
					standardHeight = 320;
					standardWidth = 240;
					break;
			}
		}

		int standardWidth;

		int standardHeight;

		@Override
		public Bitmap transform(Bitmap source) {

			int width = source.getWidth();
			int height = source.getHeight();
			if (width <= standardWidth && height <= standardHeight) {
				return source;
			}
			float scaleFactor = Math.max(standardWidth / (float) width,
					standardHeight / (float) height);
			Bitmap bitmap = Bitmap.createScaledBitmap(
					source,
					(int) Math.floor(width * scaleFactor), (int) Math.floor(height * scaleFactor), false);
			if (bitmap != source) {
				source.recycle();
			}
			return bitmap;
		}

		@Override
		public String key() {
			return "limit_size_" + standardHeight + "x" + standardWidth;
		}
	}

}
