package com.bn.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;

import com.bn.util.Exit;
import com.example.chat.R;

public class LoadActivity extends Activity {
    private ImageView welcomeImg = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main_lost);
        welcomeImg = (ImageView) this.findViewById(R.id.welcome);
        AlphaAnimation anima = new AlphaAnimation(0.3f, 1.0f);
        anima.setDuration(500);
        welcomeImg.startAnimation(anima);
        Exit.getInstance().addActivities(this);
        anima.setAnimationListener(new AnimationImpl());
    }

    private class AnimationImpl implements AnimationListener {

        @Override
        public void onAnimationStart(Animation animation) {
            welcomeImg.setBackgroundResource(R.drawable.load);
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            skip(); 
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }

    }

    private void skip() {
        startActivity(new Intent(this,LoginActivity.class));
        //finish();
    }
}
