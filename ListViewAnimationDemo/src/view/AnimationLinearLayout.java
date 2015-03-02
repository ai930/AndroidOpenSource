package view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.animation.BounceInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.LinearLayout;

import com.example.listviewanimationdemo.R;

/**
 * @author renzhiqiang,�Դ��볡������LiearLayout
 *
 */
public class AnimationLinearLayout extends LinearLayout {

	/**
	 * �Դ�����
	 */
	private ObjectAnimator anim;
	private int interploatorType;
	/**
	 * ��������ʱ��
	 */
	private int animDurations;

	public AnimationLinearLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		TypedArray a = context.obtainStyledAttributes(attrs,
				R.styleable.linearlayout_animation);
		animDurations = a.getInteger(
				R.styleable.linearlayout_animation_animation_duration, 2000);
		interploatorType = a.getInteger(
				R.styleable.linearlayout_animation_animation_interpolator, -1);
		a.recycle();
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		initInterpolator();
	}

	@Override
	protected void onDetachedFromWindow() {
		super.onDetachedFromWindow();
		anim = null;
	}

	private void initInterpolator() {

		Interpolator interpolator = null;
		if (interploatorType == 1) {
			interpolator = new OvershootInterpolator();
			anim = ObjectAnimator.ofFloat(this, "X", -getWidth(), 0);

		}
		if (interploatorType == 2) {
			interpolator = new BounceInterpolator();
			anim = ObjectAnimator.ofFloat(this, "X", getWidth(), 0);
		}
		anim.setInterpolator(interpolator);
		anim.setDuration(animDurations);
		anim.start();
	}
}
