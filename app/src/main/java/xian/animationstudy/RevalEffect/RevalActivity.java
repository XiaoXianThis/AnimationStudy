package xian.animationstudy.RevalEffect;

import android.animation.Animator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;

import xian.animationstudy.R;

public class RevalActivity extends AppCompatActivity implements View.OnClickListener {

    boolean visbile=false;
    View revalView;
    FloatingActionButton button;
    Animator animator;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reavlanimation);

        button = findViewById(R.id.action_btn);
        revalView = findViewById(R.id.reval);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.action_btn:
                doAnimation();
                break;
        }
    }

    private void doAnimation(){
        int [] position = new int[2];
        button.getLocationInWindow(position);
        int centerX=position[0]+button.getWidth()/2;
        int centerY=position[1]+button.getHeight()/2;
        Log.e("Xian",centerX+" , "+centerY);
        int hypotenuse = (int) Math.hypot(revalView.getWidth(), revalView.getHeight());
        if (visbile){
            animator = ViewAnimationUtils.createCircularReveal(revalView,revalView.getWidth(),revalView.getHeight(), hypotenuse,0);
            animator.setDuration(500);
            animator.setInterpolator(new DecelerateInterpolator());
            animator.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    revalView.setVisibility(View.INVISIBLE);
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
            animator.start();
            visbile=false;
        }else {
            animator = ViewAnimationUtils.createCircularReveal(revalView,revalView.getWidth(),revalView.getHeight(), 0,hypotenuse);
            animator.setDuration(500);
            animator.setInterpolator(new DecelerateInterpolator());
            revalView.setVisibility(View.VISIBLE);
            animator.start();
            visbile=true;
        }
    }
}
