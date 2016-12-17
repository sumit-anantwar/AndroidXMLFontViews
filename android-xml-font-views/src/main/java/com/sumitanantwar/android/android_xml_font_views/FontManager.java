package com.sumitanantwar.android.android_xml_font_views;

import android.content.Context;
import android.graphics.Typeface;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sumit Anantwar on 12/12/16.
 */

public class FontManager
{
    private static Map<String, Typeface> FONT_MAP = new HashMap<>();

    public static Typeface getTypeFaceForFont(Context context, String fontName)
    {
        if (!FONT_MAP.containsKey(fontName))
        {
            try
            {
                Typeface typeface = Typeface.createFromAsset(context.getAssets(), "fonts/"+fontName);
                FONT_MAP.put(fontName, typeface);
            }
            catch (Exception e)
            {
                throw new RuntimeException(String.format("Font file not found.\nMake sure that %s exists under assets/fonts/ folder", fontName));
            }
        }

        return FONT_MAP.get(fontName);
    }
}
