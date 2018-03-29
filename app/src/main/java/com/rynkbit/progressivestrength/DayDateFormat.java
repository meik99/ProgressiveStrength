package com.rynkbit.progressivestrength;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by michael on 27.03.18.
 */

public class DayDateFormat extends SimpleDateFormat {
    public DayDateFormat() {
        super("EEEE dd-MMMM-yyyy", Locale.getDefault());
    }
}
