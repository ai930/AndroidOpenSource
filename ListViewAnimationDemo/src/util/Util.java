package util;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.util.DisplayMetrics;

public class Util {

	public static enum ScalingLogic {
		CROP, FIT
	}

	/**
	 * �����ֻ��ķֱ��ʴ� dp �ĵ�λ ת��Ϊ px(����)
	 */
	public static int dip2px(float dpValue, Activity context) {
		return (int) (dpValue * getWindowDensity(context) + 0.5f);
	}

	/**
	 * ��ȡ�豸��Ļ�ܶ�
	 * 
	 * @return
	 */
	public static float getWindowDensity(Activity context) {
		DisplayMetrics dm = new DisplayMetrics();
		context.getWindowManager().getDefaultDisplay().getMetrics(dm);
		return dm.density;
	}

	/**
	 * ����һ��ָ���ߴ��ͼ,���������ʡ�ڴ�
	 * 
	 * @param res
	 * @param resId
	 * @param reqWidth
	 * @param reqHeight
	 * @return
	 */
	public static Bitmap decodeSampledBitmapFromResource(Resources res,
			int resId, int reqWidth, int reqHeight) {
		// First decode with inJustDecodeBounds=true to check dimensions
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeResource(res, resId, options); // ֻ����ͼƬ��Ϣ,û��ʵ������
		// Calculate inSampleSize
		options.inSampleSize = calculateSampleSize(options.outWidth,
				options.outHeight, reqWidth, reqHeight, ScalingLogic.FIT);
		// Decode bitmap with inSampleSize set
		options.inJustDecodeBounds = false;
		options.inPreferredConfig = Bitmap.Config.RGB_565;
		options.inPurgeable = true;
		options.inInputShareable = true;
		return BitmapFactory.decodeResource(res, resId, options); // ���������ڴ�ȥ����ͼƬ
	}

	public static Bitmap decodeSampledBitmapFromResource(Resources res,
			int resId, int dstWidth, int dstHeight, ScalingLogic scalingLogic) {
		Options options = new Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeResource(res, resId, options);
		options.inJustDecodeBounds = false;
		options.inSampleSize = calculateSampleSize(options.outWidth,
				options.outHeight, dstWidth, dstHeight, scalingLogic);
		Bitmap unscaledBitmap = BitmapFactory.decodeResource(res, resId,
				options);

		return unscaledBitmap;
	}

	public static int calculateSampleSize(int srcWidth, int srcHeight,
			int dstWidth, int dstHeight, ScalingLogic scalingLogic) {
		if (scalingLogic == ScalingLogic.FIT) {
			final float srcAspect = (float) srcWidth / (float) srcHeight;
			final float dstAspect = (float) dstWidth / (float) dstHeight;

			if (srcAspect > dstAspect) {
				return srcWidth / dstWidth;
			} else {
				return srcHeight / dstHeight;
			}
		} else {
			final float srcAspect = (float) srcWidth / (float) srcHeight;
			final float dstAspect = (float) dstWidth / (float) dstHeight;

			if (srcAspect > dstAspect) {
				return srcHeight / dstHeight;
			} else {
				return srcWidth / dstWidth;
			}
		}
	}

	/**
	 * ����Ӧ�ó���Ļ����С
	 */
	public static int memoryCacheSize() {
		return (int) Runtime.getRuntime().maxMemory() / 8;
	}
}
