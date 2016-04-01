package com.firebaseapp.animesh.swipebutton;

import android.graphics.Color;

public abstract class SwipeCallbacks {
    public int buttonLeftColor = Color.parseColor("#009F9D");
    public int buttonRightColor = Color.parseColor("#2fc4c2");
    public double swipeConfirmLeftDistance = 0.85;
    public int swipePartitionWidth = 50;
    public String buttonPressText = ">>   SWIPE TO CONFIRM   >> ";

    public SwipeCallbacks (){

    }

    public int getButtonLeftColor() {
        return this.buttonLeftColor;
    }

    public SwipeCallbacks setButtonLeftColor(int buttonLeftColor) {
        this.buttonLeftColor = buttonLeftColor;
        return this;
    }

    public int getButtonRightColor() {
        return this.buttonRightColor;
    }

    public SwipeCallbacks setButtonRightColor(int buttonRightColor) {
        this.buttonRightColor = buttonRightColor;
        return this;
    }

    public double getSwipeConfirmLeftDistance() {
        return this.swipeConfirmLeftDistance;
    }

    public SwipeCallbacks setSwipeConfirmLeftDistance(double swipeConfirmLeftDistance) {
        this.swipeConfirmLeftDistance = swipeConfirmLeftDistance;
        return this;
    }

    public int getSwipePartitionWidth() {
        return this.swipePartitionWidth;
    }

    public SwipeCallbacks setSwipePartitionWidth(int swipePartitionWidth) {
        this.swipePartitionWidth = swipePartitionWidth;
        return this;
    }

    public String getButtonPressText() {
        return buttonPressText;
    }

    public SwipeCallbacks setButtonPressText(String buttonPressText) {
        this.buttonPressText = buttonPressText;
        return this;
    }

    public void onButtonPress(){

    }

    public void onSwipeCancel(){

    }
    abstract public void onSwipeConfirm();

}