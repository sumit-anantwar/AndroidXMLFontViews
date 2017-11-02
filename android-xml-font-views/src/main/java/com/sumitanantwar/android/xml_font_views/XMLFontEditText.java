package com.sumitanantwar.android.xml_font_views;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;

/**
 * Created by Sumit Anantwar on 12/22/16.
 */

public class XMLFontEditText extends AppCompatEditText
{
    private final Context context;

    private String fontFile;

    public XMLFontEditText(Context context)
    {
        this(context, null);
    }

    public XMLFontEditText(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        this.context = context;
        initWithAttributes(attrs, 0);
    }

    public XMLFontEditText(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initWithAttributes(attrs, defStyleAttr);
    }

    // Common Initializer
    private void initWithAttributes(AttributeSet attrs, int defStyleAttr)
    {
        final TypedArray attributes = context.getTheme().obtainStyledAttributes(attrs, R.styleable.XMLFontEditText, defStyleAttr, 0);

        String fontFilename = attributes.getString(R.styleable.XMLFontEditText_font_file);
        if (fontFilename != null) setFontFile(fontFilename);

        attributes.recycle();
    }

    public void setFontFile(String filename)
    {
        fontFile = filename;

        setTypeface(FontManager.getTypeFaceForFont(context, filename));
    }

    public String getFontFile()
    {
        return fontFile;
    }
}
