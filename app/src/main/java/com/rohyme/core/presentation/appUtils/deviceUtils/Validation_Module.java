package com.rohyme.core.presentation.appUtils.deviceUtils;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.databinding.BindingAdapter;
import android.support.annotation.IntDef;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.BounceInterpolator;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * Created By Mahmoud Abd Elaal
 */

public class Validation_Module {
    private ValidationCallback callback;


    @BindingAdapter({"validation:setError", "validation:setType"})
    public static void setError(View view, int id, int type) {
        ErrorValidation errorValidation = new ErrorValidation();
        errorValidation.errorText = view.getContext().getString(id);
        errorValidation.viewType = type;
        view.setTag(errorValidation);
    }

    @BindingAdapter({"validation:setError", "validation:setType", "validation:isIgnore"})
    public static void setError(View view, int id, int type, boolean isIgnore) {
        ErrorValidation errorValidation = new ErrorValidation();
        errorValidation.errorText = view.getContext().getString(id);
        errorValidation.viewType = type;
        errorValidation.isIgnore = isIgnore;
        view.setTag(errorValidation);
    }

    @BindingAdapter({"validation:setValidationLength"})
    public static void setError(View view, int lenght) {
        ErrorValidation errorValidation = new ErrorValidation();
        errorValidation.errorText = "invalid Length" + Integer.toString(lenght);
        errorValidation.viewType = TYPE.EDITTEXT;
        errorValidation.length = lenght;
        view.setTag(errorValidation);
    }

    @BindingAdapter({"validation:setError", "validation:setType", "validation:isIgnore", "validation:module"})
    public static void setError(final EditText view, int id, int type, boolean isIgnore, final Validation_Module validation_module) {
        final ErrorValidation errorValidation = new ErrorValidation();
        errorValidation.errorText = view.getContext().getString(id);
        errorValidation.viewType = type;
        errorValidation.isIgnore = isIgnore;
        view.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(final View v, boolean hasFocus) {
                if (!hasFocus) {
                    view.setError(errorValidation.errorText);
                    validation_module.validate(view.getContext(), view, validation_module.callback);
                }
            }
        });
        view.setTag(errorValidation);
    }

    public boolean validate(final Context context, final View v, ValidationCallback callback) {
        if (v.getVisibility() == View.VISIBLE) {
            try {
                if (checkIfIgnore(v)) return true;

                if (v instanceof ViewGroup) {
                    ViewGroup vg = (ViewGroup) v;
                    for (int i = 0; i < vg.getChildCount(); i++) {
                        View child = vg.getChildAt(i);
                        if (v.getVisibility() == View.VISIBLE) {
                            if (!propgateError(v, callback)) {
                                return false;
                            }
                        }
                        if (v.getVisibility() == View.VISIBLE) {
                            if (!validate(context, child, callback)) {
                                return false;
                            }
                        }
                    }
                } else {
                    if (v.getVisibility() == View.VISIBLE) {
                        if (!propgateError(v, callback)) {
                            return false;
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    private boolean checkIfIgnore(View v) {
        ErrorValidation validation = (ErrorValidation) v.getTag();
        if (validation != null) {
            return validation.isIgnore;
        }
        return false;
    }

    public boolean propgateError(View view, ValidationCallback callback) {
        if (view.getTag() instanceof ErrorValidation) {
            ErrorValidation errorValidation = (ErrorValidation) view.getTag();
            String errorText = errorValidation.errorText;
            Log.i("Validation", errorValidation.viewType + "");
            boolean isValid = true;
            switch (errorValidation.viewType) {
                case TYPE.EDITTEXT:
                    isValid = Validation_Utils.isValidData(((EditText) view).getText().toString());
                    int txtLength = ((EditText) view).getText().toString().length();
                    if (errorValidation.length != txtLength && errorValidation.length != -1) {
                        isValid = false;
                    }
                    if (!isValid) {
                        Validation_Utils.focus((EditText) view);
                    }
                    break;
                case TYPE.EMAIL:
                    isValid = Validation_Utils.isValidEmail(((EditText) view).getText().toString());
                    if (!isValid) {
                        Validation_Utils.focus((EditText) view);
                    }
                    break;
                case TYPE.PHONE:
                    isValid = Validation_Utils.isValidSadiPhone(((EditText) view).getText().toString());
                    if (!isValid) {
                        Validation_Utils.focus((EditText) view);
                    }
                    break;
                case TYPE.USERNAME:
                    isValid = Validation_Utils.isValidUsername(((EditText) view).getText().toString());
                    if (!isValid) {
                        Validation_Utils.focus((EditText) view);
                    }
                    break;
                case TYPE.USERNAMEWITHCHAR:
                    isValid = Validation_Utils.isValidUserName(((EditText) view).getText().toString());
                    if (!isValid) {
                        Validation_Utils.focus((EditText) view);
                    }
                    break;
                case TYPE.CHECKBOX:
                    isValid = ((CheckBox) view).isChecked();
                    if (!isValid) {
                        view.requestFocus();
                    }
                    break;
                case TYPE.SPINNER:
                    isValid = ((Spinner) view).getSelectedItemPosition() > 0;
                    if (!isValid) {
                        view.setFocusable(true);
                        view.requestFocusFromTouch();
                        view.requestFocus();
                        ((Spinner) view).performClick();
                    }

                    break;
                case TYPE.RADIOGROUP:
                    isValid = ((RadioGroup) view).getCheckedRadioButtonId() != 0;
                    if (!isValid) {
                        view.requestFocus();
                    }
                    break;
                case TYPE.IMAGEVIEW:
                    isValid = errorValidation.isValid;
                    if (!isValid) {
                        if (view instanceof CircleImageView) {
                            ((CircleImageView) view).setBorderColor(ContextCompat.getColor(view.getContext(), android.R.color.holo_red_dark));
                            ((CircleImageView) view).setBorderWidth(3);
                            ObjectAnimator anim = ObjectAnimator.ofInt(((CircleImageView) view), "BorderWidth", 7, 0);
                            anim.setDuration(2000);                  // Duration in milliseconds
                            anim.setInterpolator(new BounceInterpolator());  // E.g. Linear, Accelerate, Decelerate
                            anim.setRepeatCount(3);
                            anim.start();
                        }
                        view.setFocusable(true);
                        view.requestFocusFromTouch();
                        view.requestFocus();
//                        view.performClick();
                    }
                    break;
            }
            if (!isValid) {
                callback.showCallbackAs(view, errorValidation.viewType, errorText);
                return false;
            }
        }
        return true;
    }

    public void setCallback(ValidationCallback callback) {
        this.callback = callback;
    }


    public static interface ValidationCallback {
        public void showCallbackAs(View view, int viewType, String error);
    }

    @IntDef({TYPE.EDITTEXT, TYPE.SPINNER, TYPE.RADIOGROUP, TYPE.CHECKBOX})
    @Retention(RetentionPolicy.SOURCE)
    public static @interface TYPE {
        int EDITTEXT = 1;
        int SPINNER = 2;
        int RADIOGROUP = 3;
        int CHECKBOX = 4;
        int IMAGEVIEW = 5;
        int EMAIL = 6;
        int PHONE = 7;
        int USERNAME = 8;
        int USERNAMEWITHCHAR = 9;

    }

    public static class ErrorValidation {
        @TYPE
        public int viewType;
        public String errorText;
        public boolean isValid = false;
        public boolean isIgnore = false;
        public int length = -1;
    }

}
