package com.ottsolution.demo.customviews;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.ottsolution.demo.R;
import com.ottsolution.demo.ui.base.MyApp;

public class AutoResizeTextView extends TextView {
    private static final int DEFAULT_TRIM_LENGTH = 150;
    //TODO: Get Line Count instead of word counts
    int lines;
    private CharSequence originalText;
    private CharSequence trimmedText;
    private BufferType bufferType;
    private boolean trim = true;
    private int trimLength;

    public AutoResizeTextView(Context context) {
        this(context, null);
    }

    public AutoResizeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (isInEditMode()) {
            return;
        }
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ExpandableTextView);
        this.trimLength = typedArray.getInt(R.styleable.ExpandableTextView_trimLength, DEFAULT_TRIM_LENGTH);
//        String fontName = typedArray.getString(R.styleable.ExpandableTextView_expandFontName);
//        Typeface myTypeface = null;
//        if (fontName != null) {
//            myTypeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/" + fontName);
//            setTypeface(myTypeface);
//        } else {
//            myTypeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/Sky_Reg.ttf");
//            setTypeface(myTypeface);
//        }
        typedArray.recycle();

        setOnClickListener(v -> {
            if (getDisplayableText().length() < DEFAULT_TRIM_LENGTH) return;
            trim = !trim;
            setText();
//                requestFocusFromTouch();
        });

    }

    private void setText() {
        super.setText(getDisplayableText(), bufferType);

    }

    private CharSequence getDisplayableText() {
        return trim ? trimmedText : originalText;
    }

    @Override
    public void setText(CharSequence text, BufferType type) {

        if (isInEditMode()) {
            return;
        }

        String LESS = getContext().getString(R.string.minus_less);
        Spannable wordtoSpan = new SpannableString(text + LESS);
        wordtoSpan.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), wordtoSpan.length() - 6, wordtoSpan.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        wordtoSpan.setSpan(new ForegroundColorSpan(ContextCompat.getColor(MyApp.Companion.getContext(), R.color.cool_blue)), wordtoSpan.length() - 6, wordtoSpan.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        originalText = wordtoSpan;
        trimmedText = getTrimmedText(text);
        bufferType = type;
        setText();
    }


    private CharSequence getTrimmedText(CharSequence text) {
        String MORE = getContext().getString(R.string.plus_more);
        Spannable wordtoSpan = new SpannableString(MORE);
        wordtoSpan.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, wordtoSpan.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        wordtoSpan.setSpan(new ForegroundColorSpan(ContextCompat.getColor(MyApp.Companion.getContext(), R.color.cool_blue)), 0, wordtoSpan.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        if (text != null && text.length() > trimLength) {
            return new SpannableStringBuilder(text, 0, trimLength +1).append(wordtoSpan);
        } else {
            return originalText.subSequence(0, originalText.length() - 6);
        }
    }

    public CharSequence getOriginalText() {
        return originalText;
    }

    public void setTrimLength(int trimLength) {
        this.trimLength = trimLength;
        trimmedText = getTrimmedText(originalText);
        setText();
    }

    public int getTrimLength() {
        return trimLength;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        Editable editable = getEditableText();
        if (editable == null) {
            return;
        }
        int width = getWidth() - getPaddingLeft() - getPaddingRight();
        if (width == 0) {
            return;
        }

        Paint p = getPaint();
        float[] widths = new float[editable.length()];
        p.getTextWidths(editable.toString(), widths);
        float curWidth = 0.0f;
        int lastWSPos = -1;
        int strPos = 0;
        final char newLine = '\n';
        final String newLineStr = "\n";
        boolean reset = false;
        int insertCount = 0;

        /*
         * Traverse the string from the start position, adding each character's width to the total
         * until: 1) A whitespace character is found. In this case, mark the whitespace position. If
         * the width goes over the max, this is where the newline will be inserted. 2) A newline
         * character is found. This resets the curWidth counter. curWidth > width. Replace the
         * whitespace with a newline and reset the counter.
         */

        while (strPos < editable.length()) {
            curWidth += widths[strPos];

            char curChar = editable.charAt(strPos);

            if (curChar == newLine) {
                reset = true;
            } else if (Character.isWhitespace(curChar)) {
                lastWSPos = strPos;
            } else if (curWidth > width && lastWSPos >= 0) {
                editable.replace(lastWSPos, lastWSPos + 1, newLineStr);
                insertCount++;
                strPos = lastWSPos;
                lastWSPos = -1;
                reset = true;
            }

            if (reset) {
                curWidth = 0.0f;
                reset = false;
            }

            strPos++;
        }

        if (insertCount != 0) {
            setText(editable);
        }
    }
}
