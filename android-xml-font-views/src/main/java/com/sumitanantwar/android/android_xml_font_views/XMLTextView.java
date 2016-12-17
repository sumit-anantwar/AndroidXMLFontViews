package com.sumitanantwar.android.android_xml_font_views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by Sumit Anantwar on 12/17/16.
 */

public class XMLTextView extends TextView
{
    private final Context mContext;

    private String mFontName;

    public XMLTextView(Context context)
    {
        this(context, null);
    }

    public XMLTextView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        mContext = context;
        initWithAttributes(attrs, 0);
    }

    public XMLTextView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initWithAttributes(attrs, defStyleAttr);
    }

    private void initWithAttributes(AttributeSet attrs, int defStyleAttr)
    {
        final TypedArray attributes = mContext.getTheme().obtainStyledAttributes(attrs, R.styleable.XMLFontTextView, defStyleAttr, 0);

        String fontName = attributes.getString(R.styleable.XMLFontTextView_font_name);
        if (fontName != null) setFontName(fontName);

        attributes.recycle();
    }

    public void setFontName(String fontName)
    {
        mFontName = fontName;

        setTypeface(FontManager.getTypeFaceForFont(mContext, fontName));
    }

    public String getFontName()
    {
        return mFontName;
    }
}
