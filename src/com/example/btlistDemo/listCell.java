package com.example.btlistDemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import android.util.AttributeSet;

/**
 * Created with IntelliJ IDEA.
 * User: LHC
 * Date: 13-7-24
 * Time: 下午10:10
 * A demo project with a list of button and textview.
 * The buttons are hidden and if click on the item in the list the button will show.
 * A simple demo.
 * This can easily change to the same style of the project;
 */

public class listCell extends LinearLayout {

    private Button button;
    private TextView textView;

    public listCell(Context context){
        super(context);
    }

    public listCell(Context context, AttributeSet attrs){
        super(context,attrs);
        LayoutInflater.from(context).inflate(R.layout.list_cell_layout, this, true);
        button = (Button)findViewById(R.id.btn);
        textView = (TextView)findViewById(R.id.tv);
    }

    public void initCell(){
            button.setVisibility(View.GONE);
    }

    void showButton(){
        final Animation animation = (Animation) AnimationUtils.loadAnimation(this.getContext(), R.anim.button_show);
        animation.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationStart(Animation animation) {
                button.setVisibility(View.VISIBLE);
            }
            public void onAnimationRepeat(Animation animation) {}
            public void onAnimationEnd(Animation animation) {
                animation.cancel();
            }
        });
        this.startAnimation(animation);
    }

    void hideButton(){
        final Animation animation = (Animation) AnimationUtils.loadAnimation(this.getContext(), R.anim.button_hide);
        animation.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationStart(Animation animation) {
                button.setVisibility(View.VISIBLE);
            }
            public void onAnimationRepeat(Animation animation) {}
            public void onAnimationEnd(Animation animation) {
                button.setVisibility(View.GONE);
                animation.cancel();
            }
        });
        this.startAnimation(animation);
    }

    public void setText(String text){
        textView.setText(text);
    }

}