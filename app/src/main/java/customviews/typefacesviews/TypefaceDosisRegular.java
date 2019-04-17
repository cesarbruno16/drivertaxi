package customviews.typefacesviews;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by samirgoel3@gmail.com on 6/3/2017.
 */

public class TypefaceDosisRegular extends TextView {

    public TypefaceDosisRegular(Context context) {
        super(context);
        applyCustomFont(context);
    }

    public TypefaceDosisRegular(Context context, AttributeSet attrs) {
        super(context, attrs);
        applyCustomFont(context);
    }

    public TypefaceDosisRegular(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        applyCustomFont(context);
    }

    private void applyCustomFont(Context context) {
        Typeface customFont = FontCache.getTypeface("Dosis-Regular.ttf", context);
        setTypeface(customFont);
    }

}
