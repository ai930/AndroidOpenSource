package view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.OverScroller;
import android.widget.ScrollView;

/**********************************************************
 * @�ļ����ƣ�AutoScrollView.java
 * @�ļ����ߣ�renzhiqiang
 * @����ʱ�䣺2015��2��26�� ����11:25:29
 * @�ļ�����������ScrolView��������ʵ���Զ����»���
 * @�޸���ʷ��2015��2��26�մ�����ʼ�汾
 **********************************************************/
public class AutoScrollView extends ScrollView {
	private Context context;
	private OverScroller scroller;
	/**
	 * ֻ��ǰ����Ҫ����ƫ��ֵ
	 */
	private int flag = 0;
	/**
	 * �Զ��������
	 */
	private int deltaY = 100;

	public AutoScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		initView();
	}

	private void initView() {
		Interpolator polator = new AccelerateInterpolator();
		scroller = new OverScroller(context, polator);
	}

	// �ƶ��ŵĶ���
	public void startAnim(int startY, int dY, int duration) {
		flag++;
		scroller.startScroll(0, startY, 0, dY, duration); // ����Scroller���ﵽƽ���ƶ���Ч��
		invalidate();
	}

	/**
	 * ������ʱ���Ż�������
	 */
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		startAnim(this.getScrollY(), deltaY, 500);
	}

	@Override
	public void computeScroll() {
		/**
		 * true,δ������
		 */
		if (scroller.computeScrollOffset()) {
			if (flag <= 2) {
				scrollTo(scroller.getCurrX(), scroller.getCurrY());
				// ��Ҫ���Ǹ��½���
				postInvalidate();
			}
		} else {
			startAnim(AutoScrollView.this.getScrollY(), -deltaY, 500);
		}
	}
}
