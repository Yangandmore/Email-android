package com.yang.email.util;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.yang.email.R;

/**
 * Created by shiq_yang on 2017/8/31.
 * 动画Util
 */

public class AnimotionUtil {

    public static void startAppAnimotion(Context context, final ImageView imageView, final RelativeLayout relativeLayout) {

        Animation animation = AnimationUtils.loadAnimation(context, R.anim.alpha_startapp);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                imageView.setVisibility(View.GONE);
                relativeLayout.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        imageView.startAnimation(animation);

    }

}
