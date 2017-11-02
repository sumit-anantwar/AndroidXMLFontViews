package com.sumitanantwar.android.xml_font_views;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;
import android.util.Log;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.security.InvalidParameterException;

/**
 * Created by Sumit Anantwar on 11/1/17.
 */

public class XMLFontIconEditText extends AppCompatEditText
{
    private static final String LOG_TAG = "XMLFontIconEditText";

    private final Context context;

    private String fontFile;

    private String iconCharacter = "Alpha";
    private String iconFontFile;
    private @IconPosition int iconPosition;
    private Paint iconPaint = new Paint();

    // ======= Icon Position =======
    @IntDef({
            ICON_POSITION_LEFT,
            ICON_POSITION_RIGHT
    })
    @Retention(RetentionPolicy.SOURCE)
    public @interface IconPosition{}

    public static final int ICON_POSITION_LEFT = 0;
    public static final int ICON_POSITION_RIGHT = 1;

    private static final float  ICON_X_OFFSET = 25.0f;

    // ======= Designated Initializers =======
    public XMLFontIconEditText(Context context)
    {
        this(context, null);
    }

    public XMLFontIconEditText(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        this.context = context;
        initWithAttributes(attrs, 0);
    }

    public XMLFontIconEditText(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initWithAttributes(attrs, defStyleAttr);
    }

    // ======= Common Initializer =======
    private void initWithAttributes(AttributeSet attrs, int defStyleAttr)
    {
        int iconSize = 25;

        // Initialize default values
        iconPaint.setTextAlign(Paint.Align.CENTER);
        iconPaint.setColor(Color.BLACK);
        iconPaint.setTextSize(iconSize);


        // Read the attributes
        final TypedArray attributes = context.getTheme().obtainStyledAttributes(attrs, R.styleable.XMLFontIconEditText, defStyleAttr, 0);

        String fontFilename = attributes.getString(R.styleable.XMLFontIconEditText_font_file);
        if (fontFilename != null) setFontFile(fontFilename);

        String iconCharacter = attributes.getString(R.styleable.XMLFontIconEditText_icon_character);
        setIconCharacter(iconCharacter);

        String iconFontFilename = attributes.getString(R.styleable.XMLFontIconEditText_icon_font_file);
        if (iconFontFilename != null) setIconFontFile(iconFontFilename);

        iconSize = attributes.getDimensionPixelSize(R.styleable.XMLFontIconEditText_icon_size, iconSize);
        setIconSize(iconSize);

        ColorStateList iconColor = attributes.getColorStateList(R.styleable.XMLFontIconEditText_icon_color);
        if (iconColor != null) setIconColor(iconColor);

        iconPosition = attributes.getInt(R.styleable.XMLFontIconEditText_icon_position, iconPosition);
        setIconPosition(iconPosition);

        attributes.recycle();

//        invalidate();
    }

    // ======= Getters & Setters =======
    // Main Font file
    public String getFontFile() { return fontFile; }
    public void setFontFile(String filename)
    {
        fontFile = filename;

        setTypeface(FontManager.getTypeFaceForFont(context, filename));
    }

    public String getIconCharacter() { return iconCharacter; }
    public void setIconCharacter(String iconCharacter)
    {
        this.iconCharacter = iconCharacter;
        invalidate();
    }

    // Icon Font File
    public String getIconFontFile() { return iconFontFile; }
    public void setIconFontFile(String iconFontFile)
    {
        this.iconFontFile = iconFontFile;

        iconPaint.setTypeface(FontManager.getTypeFaceForFont(context, iconFontFile));
        invalidate();
    }

    public void setIconSize(float iconSize)
    {
        iconPaint.setTextSize(iconSize);
        invalidate();
    }

    public void setIconColor(@NonNull ColorStateList color)
    {
        iconPaint.setColor(color.getDefaultColor());
        invalidate();
    }

    // Icon Position
    public void setIconPosition(@IconPosition int position)
    {
        int iconPadding = (int) (iconPaint.getTextSize() * 1.5);

        switch (position) {
            case ICON_POSITION_LEFT:
                setPadding((getPaddingLeft() + iconPadding), getPaddingTop(), getPaddingRight(), getPaddingBottom());
                break;

            case ICON_POSITION_RIGHT:
                setPadding(getPaddingLeft(), getPaddingTop(), (getPaddingRight()  + iconPadding), getPaddingBottom());
                break;

            default:
                throw new InvalidParameterException("Only Left and Right positions are supported");
        }
    }

    // ======= Drawing =======


    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        float iconSize = iconPaint.getTextSize();
        float iconOffset = iconSize * 0.75f;

        float cX = (iconPosition == ICON_POSITION_LEFT) ? iconOffset : (getMeasuredWidth() - iconOffset);
        float cY = ((getMeasuredHeight() / 2) + getScrollY());

        if (iconCharacter ==  null) iconCharacter = "Beta";
        canvas.drawText(iconCharacter, cX, (cY + (iconPaint.getTextSize() * 0.35f)), iconPaint);
        Log.i(LOG_TAG, "onDraw: Drawing Icon Character");
    }
}
