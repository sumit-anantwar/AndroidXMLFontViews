package com.sumitanantwar.android.xml_font_views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.support.v7.widget.AppCompatTextView;

/**
 * Created by Sumit Anantwar on 12/17/16.
 */

public class XMLFontTextView extends AppCompatTextView
{
    private final Context context;

    private String fontFile;

    // Designated Initializers
    public XMLFontTextView(Context context)
    {
        this(context, null);
    }

    public XMLFontTextView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        this.context = context;
        initWithAttributes(attrs, 0);
    }

    public XMLFontTextView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initWithAttributes(attrs, defStyleAttr);
    }

    // Common Initializer
    private void initWithAttributes(AttributeSet attrs, int defStyleAttr)
    {
        final TypedArray attributes = context.getTheme().obtainStyledAttributes(attrs, R.styleable.XMLFontTextView, defStyleAttr, 0);

        String fontFilename = attributes.getString(R.styleable.XMLFontTextView_font_file);
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
