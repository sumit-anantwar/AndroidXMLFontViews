package com.sumitanantwar.android.xml_font_views;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Sumit Anantwar on 12/20/16.
 */

public class XMLFontIconButton extends FrameLayout
{
    private final Context context;

    private CharSequence text;
    private String       fontFile;

    private CharSequence iconCharacter;
    private String       iconFontFile;

    private XMLFontTextView topIconView;
    private XMLFontTextView leftIconView;
    private XMLFontTextView rightIconView;
    private XMLFontTextView bottomIconView;
    private XMLFontTextView mainTextView;

    @IntDef({
            ICON_POSITION_LEFT,
            ICON_POSITION_RIGHT,
            ICON_POSITION_TOP,
            ICON_POSITION_BOTTOM
    })
    @Retention(RetentionPolicy.SOURCE)
    public @interface IconPosition{}

    public static final int ICON_POSITION_LEFT = 0;
    public static final int ICON_POSITION_RIGHT = 1;
    public static final int ICON_POSITION_TOP = 2;
    public static final int ICON_POSITION_BOTTOM = 3;

    public XMLFontIconButton(Context context)
    {
        this(context, null);
    }

    public XMLFontIconButton(Context context, AttributeSet attrs)
    {
        this(context, attrs, 0);
    }

    public XMLFontIconButton(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);

        this.context = context;
        initWithAttributes(attrs, defStyleAttr);
    }

    // Common Initializer
    @SuppressWarnings("ResourceType")
    private void initWithAttributes(AttributeSet attrs, int defStyleAttr)
    {
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        View view       = inflate(context, R.layout.layout_xml_font_icon_button, null);

        leftIconView = (XMLFontTextView) view.findViewById(R.id.FIB_LeftIcon);
        rightIconView = (XMLFontTextView) view.findViewById(R.id.FIB_RightIcon);
        topIconView = (XMLFontTextView) view.findViewById(R.id.FIB_TopIcon);
        bottomIconView = (XMLFontTextView) view.findViewById(R.id.FIB_BottomIcon);
        mainTextView = (XMLFontTextView) view.findViewById(R.id.FIB_MainText);

        view.setLayoutParams(params);
        addView(view);

        int textSize = 15;
        int iconSize = 25;
        int iconPosition = ICON_POSITION_LEFT;
        int iconPadding = 10;

        final TypedArray attributes = context.getTheme().obtainStyledAttributes(attrs, R.styleable.XMLFontIconButton, defStyleAttr, 0);

        String mainText = attributes.getString(R.styleable.XMLFontIconButton_text);
        setText(mainText);

        String fontFilename = attributes.getString(R.styleable.XMLFontIconButton_font_file);
        if (fontFilename != null) setFontFile(fontFilename);

        textSize = attributes.getDimensionPixelSize(R.styleable.XMLFontIconButton_text_size, textSize);
        setTextSize(textSize);

        ColorStateList textColor = attributes.getColorStateList(R.styleable.XMLFontIconButton_text_color);
        if (textColor != null) setTextColor(textColor);

        String iconCharacter = attributes.getString(R.styleable.XMLFontIconButton_icon_character);
        setIconCharacter(iconCharacter);

        String iconFontFilename = attributes.getString(R.styleable.XMLFontIconButton_icon_font_file);
        if (iconFontFilename != null) setIconFontFile(iconFontFilename);

        iconSize = attributes.getDimensionPixelSize(R.styleable.XMLFontIconButton_icon_size, iconSize);
        setIconSize(iconSize);

        ColorStateList iconColor = attributes.getColorStateList(R.styleable.XMLFontIconButton_icon_color);
        if (iconColor != null) setIconColor(iconColor);

        iconPosition = attributes.getInt(R.styleable.XMLFontIconButton_icon_position, iconPosition);
        setIconPosition(iconPosition);

        iconPadding = attributes.getDimensionPixelSize(R.styleable.XMLFontIconButton_icon_padding, iconPadding);
        setIconPadding(iconPadding);

        attributes.recycle();
    }

    public void setFontFile(String filename)
    {
        fontFile = filename;
        mainTextView.setFontFile(filename);

        invalidate();
    }

    public String getFontFile()
    {
        return fontFile;
    }

    public void setTextSize(float size)
    {
        mainTextView.setTextSize(size);

        invalidate();
    }

    public void setText(CharSequence text)
    {
        this.text = text;

        mainTextView.setText(text);
        invalidate();
    }

    public CharSequence getText()
    {
        return text;
    }

    public void setTextColor(@NonNull ColorStateList color)
    {
        mainTextView.setTextColor(color);
        invalidate();
    }

    public void setIconCharacter(CharSequence character)
    {
        iconCharacter = character;

        leftIconView.setText(character);
        rightIconView.setText(character);
        topIconView.setText(character);
        bottomIconView.setText(character);

        invalidate();
    }

    public CharSequence getIconCharacter()
    {
        return iconCharacter;
    }

    public void setIconColor(@NonNull ColorStateList color)
    {
        leftIconView.setTextColor(color);
        rightIconView.setTextColor(color);
        topIconView.setTextColor(color);
        bottomIconView.setTextColor(color);

        invalidate();
    }


    public void setIconFontFile(String filename)
    {
        iconFontFile = filename;
        leftIconView.setFontFile(filename);
        rightIconView.setFontFile(filename);
        topIconView.setFontFile(filename);
        bottomIconView.setFontFile(filename);

        invalidate();
    }

    public void setIconSize(float size)
    {
        topIconView.setTextSize(size);
        leftIconView.setTextSize(size);
        rightIconView.setTextSize(size);
        bottomIconView.setTextSize(size);

        invalidate();
    }

    public void setIconPosition(@IconPosition int position)
    {
        leftIconView.setVisibility(GONE);
        rightIconView.setVisibility(GONE);
        topIconView.setVisibility(GONE);
        bottomIconView.setVisibility(GONE);

        switch (position)
        {
            case ICON_POSITION_LEFT:
                leftIconView.setVisibility(VISIBLE);
                break;

            case ICON_POSITION_RIGHT:
                rightIconView.setVisibility(VISIBLE);
                break;

            case ICON_POSITION_TOP:
                topIconView.setVisibility(VISIBLE);
                break;

            case ICON_POSITION_BOTTOM:
                bottomIconView.setVisibility(VISIBLE);
                break;
        }

        invalidate();
    }

    public void setIconPadding(int padding)
    {
        leftIconView.setPadding(padding, padding, padding, padding);
        rightIconView.setPadding(padding, padding, padding, padding);
        topIconView.setPadding(padding, padding, padding, padding);
        bottomIconView.setPadding(padding, padding, padding, padding);

        invalidate();
    }

}
