package com.firebaseapp.animesh.swipebutton;

import android.content.Context;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Button;

public class SwipeButton extends Button {
    private SwipeCallbacks callbacks;
    private float x1;
    private float y1;
    private boolean swipeTextShown;
    private boolean confirmThresholdCrossed;
    private String originalButtonText;

    public SwipeButton(Context context) {
        super(context);
    }

    public SwipeButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SwipeButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public SwipeCallbacks getCallbacks() {
        return callbacks;
    }

    public void setCallbacks(SwipeCallbacks callbacks) {
        this.callbacks = callbacks;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            // when user first touches the screen we get x and y coordinate
            case MotionEvent.ACTION_DOWN: {
                x1 = event.getX();
                y1 = event.getY();

                this.originalButtonText = this.getText().toString();

                confirmThresholdCrossed = false;

                // when user first touches the screen, change button text.
                if (!swipeTextShown) {
                    this.setText(callbacks.getButtonPressText());
                    swipeTextShown = true;
                }

                break;
            }
            case MotionEvent.ACTION_UP: {
                float x2 = event.getX();
                float y2 = event.getY();

                //when user doesn't swipe upto the confirm swipe threshold
                if (x2 <= (this.getWidth() * callbacks.getSwipeConfirmLeftDistance())) {

                    confirmThresholdCrossed = false;
                    this.setBackgroundDrawable(null);
                    this.setBackgroundColor(callbacks.buttonLeftColor);
                    this.setText(originalButtonText);
                    swipeTextShown =  false;

                }

                //if left to right sweep event on screen
                if (x1 < x2) {

                }

                // if right to left sweep event on screen
                if (x1 > x2) {
                    Log.d("swipe", "right to left");
                }

                // if UP to Down sweep event on screen
                if (y1 < y2) {
                    Log.d("swipe", "up to down");
                }

                //if Down to UP sweep event on screen
                if (y1 > y2) {
                    Log.d("swipe", "down to up");
                }
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                float x2 = event.getX();
                float y2 = event.getY();

                //if left to right sweep event on screen, and user goes beyond confirm threshold distance
                if (x1 < x2 && !confirmThresholdCrossed) {
                    this.setBackgroundDrawable(null);

                    ShapeDrawable mDrawable = new ShapeDrawable(new RectShape());
                    int x1 = 0, y1 = 0, xx2 = 0, yy2 = 0;
                    // first color appears on right, second on left
                    Shader shader = new LinearGradient(x2, y1, x2 - callbacks.getSwipePartitionWidth(), yy2, callbacks.getButtonRightColor(), callbacks.getButtonLeftColor(), Shader.TileMode.CLAMP);
                    mDrawable.getPaint().setShader(shader);


                    if (swipeTextShown == false) {
                        this.setText(">> SWIPE TO CONFIRM >> ");
                        swipeTextShown = true;
                    }

                    if (x2 > (this.getWidth() * callbacks.getSwipeConfirmLeftDistance())) {
                        //swipe confirm callback here

                        callbacks.onSwipeConfirm();

                        confirmThresholdCrossed = true;
                    }


                    this.setBackgroundDrawable(mDrawable);
                }

                break;
            }
        }

//        return false;

        return super.onTouchEvent(event);

    }

}