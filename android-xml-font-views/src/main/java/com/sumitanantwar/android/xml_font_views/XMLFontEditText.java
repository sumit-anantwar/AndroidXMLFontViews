package com.sumitanantwar.android.xml_font_views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by Sumit Anantwar on 12/22/16.
 */

public class XMLFontEditText extends EditText
{
    private final Context mContext;

    private String mFontFile;

    public XMLFontEditText(Context context)
    {
        this(context, null);
    }

    public XMLFontEditText(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        mContext = context;
        initWithAttributes(attrs, 0);
    }

    public XMLFontEditText(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initWithAttributes(attrs, defStyleAttr);
    }

    // Common Initializer
    private void initWithAttributes(AttributeSet attrs, int defStyleAttr)
    {
        final TypedArray attributes = mContext.getTheme().obtainStyledAttributes(attrs, R.styleable.XMLFontEditText, defStyleAttr, 0);

        String fontFilename = attributes.getString(R.styleable.XMLFontEditText_font_file);
        if (fontFilename != null) setFontFile(fontFilename);

        attributes.recycle();
    }

    public void setFontFile(String filename)
    {
        mFontFile = filename;

        setTypeface(FontManager.getTypeFaceForFont(mContext, filename));
    }

    public String getFontFile()
    {
        return mFontFile;
    }
}
