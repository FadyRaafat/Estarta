package com.fady.estarta.utils.common;

import android.animation.PropertyValuesHolder;

public class AnimatorUtils {

  public static final String ROTATION = "rotation";
  public static final String SCALE_X = "scaleX";
  public static final String SCALE_Y = "scaleY";
  public static final String TRANSLATION_X = "translationX";
  public static final String TRANSLATION_Y = "translationY";
  public static final String ALPHA = "alpha";

  private AnimatorUtils() {
    //No instances.
  }

  public static PropertyValuesHolder rotation(float... values) {
    return PropertyValuesHolder.ofFloat(ROTATION, values);
  }

  public static PropertyValuesHolder translationX(float... values) {
    return PropertyValuesHolder.ofFloat(TRANSLATION_X, values);
  }

  public static PropertyValuesHolder translationY(float... values) {
    return PropertyValuesHolder.ofFloat(TRANSLATION_Y, values);
  }

  public static PropertyValuesHolder scaleX(float... values) {
    return PropertyValuesHolder.ofFloat(SCALE_X, values);
  }

  public static PropertyValuesHolder scaleY(float... values) {
    return PropertyValuesHolder.ofFloat(SCALE_Y, values);
  }

  public static PropertyValuesHolder alpha(float... values) {
    return PropertyValuesHolder.ofFloat(ALPHA, values);
  }
}
