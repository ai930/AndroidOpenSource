package util;

import android.graphics.Bitmap;

import com.example.listviewanimationdemo.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

/**********************************************************
 * @�ļ����ƣ�ImageLoaderManager.java
 * @�ļ����ߣ�renzhiqiang
 * @����ʱ�䣺2015��2��15�� ����11:41:34
 * @�ļ�������ΪImageLoader�ṩĬ�ϼ���ͼ,����ImageLoaderȥ��������򱾵�ͼ��Դ
 * @�޸���ʷ��2015��2��15�մ�����ʼ�汾
 **********************************************************/
public class ImageLoaderManager {
	private static ImageLoader imageLoader;

	static {
		imageLoader = ImageLoader.getInstance();
	}

	public static ImageLoader getImageLoader() {
		return imageLoader;
	}

	public static DisplayImageOptions getRecommendImageOptions() {
		DisplayImageOptions options = new DisplayImageOptions.Builder()
				.showImageOnLoading(R.drawable.mic_recommend_product_loading)
				.cacheInMemory(true).cacheOnDisk(true)
				.bitmapConfig(Bitmap.Config.RGB_565)
				.displayer(new FadeInBitmapDisplayer(1000)).build();
		return options;
	}
}
