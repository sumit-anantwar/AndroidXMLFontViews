package com.sumitanantwar.android.android_xml_font_views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Sumit Anantwar on 12/17/16.
 */

public class XMLFontTextView extends TextView
{
    private final Context mContext;

    private String mFontName;

    // Designated Initializers
    public XMLFontTextView(Context context)
    {
        this(context, null);
    }

    public XMLFontTextView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        mContext = context;
        initWithAttributes(attrs, 0);
    }

    public XMLFontTextView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initWithAttributes(attrs, defStyleAttr);
    }

    // Common Initializer
    private void initWithAttributes(AttributeSet attrs, int defStyleAttr)
    {
        final TypedArray attributes = mContext.getTheme().obtainStyledAttributes(attrs, R.styleable.XMLFontTextView, defStyleAttr, 0);

        String fontName = attributes.getString(R.styleable.XMLFontTextView_font_file);
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
