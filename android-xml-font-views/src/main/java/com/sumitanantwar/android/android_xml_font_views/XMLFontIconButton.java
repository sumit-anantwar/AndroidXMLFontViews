package com.sumitanantwar.android.android_xml_font_views;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.ColorRes;
import android.support.annotation.Dimension;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Sumit Anantwar on 12/20/16.
 */

public class XMLFontIconButton extends FrameLayout
{
    private final Context mContext;

    private CharSequence mText;
    private String mFontFile;

    private CharSequence mIconCharacter;
    private String mIconFontFile;

    private XMLFontTextView mTopIconView;
    private XMLFontTextView mLeftIconView;
    private XMLFontTextView mRightIconView;
    private XMLFontTextView mBottomIconView;
    private XMLFontTextView mMainTextView;

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

        mContext = context;
        initWithAttributes(attrs, defStyleAttr);
    }

    // Common Initializer
    @SuppressWarnings("ResourceType")
    private void initWithAttributes(AttributeSet attrs, int defStyleAttr)
    {
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        View view       = inflate(mContext, R.layout.layout_xml_font_icon_button, null);

        mLeftIconView   = (XMLFontTextView) view.findViewById(R.id.FIB_LeftIcon);
        mRightIconView  = (XMLFontTextView) view.findViewById(R.id.FIB_RightIcon);
        mTopIconView    = (XMLFontTextView) view.findViewById(R.id.FIB_TopIcon);
        mBottomIconView = (XMLFontTextView) view.findViewById(R.id.FIB_BottomIcon);
        mMainTextView   = (XMLFontTextView) view.findViewById(R.id.FIB_MainText);

        view.setLayoutParams(params);
        addView(view);

        int textSize = 15;
        int iconSize = 25;
        int iconPosition = ICON_POSITION_LEFT;
        int iconPadding = 10;

        final TypedArray attributes = mContext.getTheme().obtainStyledAttributes(attrs, R.styleable.XMLFontIconButton, defStyleAttr, 0);

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
        mFontFile = filename;
        mMainTextView.setFontFile(filename);

        invalidate();
    }

    public String getFontFile()
    {
        return mFontFile;
    }

    public void setTextSize(float size)
    {
        mMainTextView.setTextSize(size);

        invalidate();
    }

    public void setText(CharSequence text)
    {
        mText = text;

        mMainTextView.setText(text);
        invalidate();
    }

    public CharSequence getText()
    {
        return mText;
    }

    public void setTextColor(@NonNull ColorStateList color)
    {
        mMainTextView.setTextColor(color);
        invalidate();
    }

    public void setIconCharacter(CharSequence character)
    {
        mIconCharacter = character;

        mLeftIconView.setText(character);
        mRightIconView.setText(character);
        mTopIconView.setText(character);
        mBottomIconView.setText(character);

        invalidate();
    }

    public CharSequence getIconCharacter()
    {
        return mIconCharacter;
    }

    public void setIconColor(@NonNull ColorStateList color)
    {
        mLeftIconView.setTextColor(color);
        mRightIconView.setTextColor(color);
        mTopIconView.setTextColor(color);
        mBottomIconView.setTextColor(color);

        invalidate();
    }


    public void setIconFontFile(String filename)
    {
        mIconFontFile = filename;
        mLeftIconView.setFontFile(filename);
        mRightIconView.setFontFile(filename);
        mTopIconView.setFontFile(filename);
        mBottomIconView.setFontFile(filename);

        invalidate();
    }

    public void setIconSize(float size)
    {
        mTopIconView.setTextSize(size);
        mLeftIconView.setTextSize(size);
        mRightIconView.setTextSize(size);
        mBottomIconView.setTextSize(size);

        invalidate();
    }

    public void setIconPosition(@IconPosition int position)
    {
        mLeftIconView.setVisibility(GONE);
        mRightIconView.setVisibility(GONE);
        mTopIconView.setVisibility(GONE);
        mBottomIconView.setVisibility(GONE);

        switch (position)
        {
            case ICON_POSITION_LEFT:
                mLeftIconView.setVisibility(VISIBLE);
                break;

            case ICON_POSITION_RIGHT:
                mRightIconView.setVisibility(VISIBLE);
                break;

            case ICON_POSITION_TOP:
                mTopIconView.setVisibility(VISIBLE);
                break;

            case ICON_POSITION_BOTTOM:
                mBottomIconView.setVisibility(VISIBLE);
                break;
        }

        invalidate();
    }

    public void setIconPadding(int padding)
    {
        mLeftIconView.setPadding(padding, padding, padding, padding);
        mRightIconView.setPadding(padding, padding, padding, padding);
        mTopIconView.setPadding(padding, padding, padding, padding);
        mBottomIconView.setPadding(padding, padding, padding, padding);

        invalidate();
    }

}
