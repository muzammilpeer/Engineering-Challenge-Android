package com.muzammilpeer.utilitylayer.date;

import android.content.Context;
import android.content.res.Resources;

import com.muzammilpeer.utilitylayer.R;
import com.muzammilpeer.utilitylayer.enums.DateFormatsEnums;
import com.muzammilpeer.utilitylayer.enums.DateTimeFormatEnums;
import com.muzammilpeer.utilitylayer.enums.TimeFormatEnums;
import com.muzammilpeer.utilitylayer.logger.Log4a;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by muzammilpeer on 15/07/2015.
 */
public class DateUtil {

    /*
     * see http://drdobbs.com/java/184405382
     */
    private static final ThreadLocal<DateFormat> ISO8601Format = new ThreadLocal<DateFormat>() {
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.US);
        }
    };

    private DateUtil() {
        throw new AssertionError();
    }

    /*
     * returns the current UTC date
     */
    public static Date nowUTC() {
        Date dateTimeNow = new Date();
        return localDateToUTC(dateTimeNow);
    }

    public static Date localDateToUTC(Date dtLocal) {
        if (dtLocal == null)
            return null;
        TimeZone tz = TimeZone.getDefault();
        int currentOffsetFromUTC = tz.getRawOffset()
                + (tz.inDaylightTime(dtLocal) ? tz.getDSTSavings() : 0);
        return new Date(dtLocal.getTime() - currentOffsetFromUTC);
    }

    /*
     * routines to return a diff between two dates - always return a positive
     * number
     */
    public static int minutesBetween(Date dt1, Date dt2) {
        long msDiff = millisecondsBetween(dt1, dt2);
        if (msDiff == 0)
            return 0;
        return (int) (msDiff / 60000);
    }

    public static int secondsBetween(Date dt1, Date dt2) {
        long msDiff = millisecondsBetween(dt1, dt2);
        if (msDiff == 0) {
            return 0;
        }
        return (int) (msDiff / 1000);
    }

    public static long millisecondsBetween(Date dt1, Date dt2) {
        if (dt1 == null || dt2 == null)
            return 0;
        return Math.abs(dt1.getTime() - dt2.getTime());
    }

    /*
     * routines involving Unix timestamps (GMT assumed)
     */
    public static Date timestampToDate(long timeStamp) {
        return new java.util.Date(timeStamp * 1000); //conver time to miliseconds of unix
    }

    public static String timestampToTime(Date timeStamp) {
        String formattedDate;
        DateFormat dateFormat = new SimpleDateFormat("yyyy MM dd HH:mm");

        formattedDate = dateFormat.format(timeStamp);
        formattedDate.replace("-", " ");
        return formattedDate;

    }

    public static long timeStampConvert(String date) {
        return timeStampConvert(date, DateTimeFormatEnums.FORMAT_YYYY_MM_DD_hh_mm);
    }

    public static long timeStampConvert(String pDate, DateTimeFormatEnums format) {
        long timestamp = 0;
        try {
            SimpleDateFormat dateFormatter = new SimpleDateFormat(format.getDatetimeFormat());
            Date date = null;
            date = dateFormatter.parse(pDate);
            timestamp = date.getTime() / 1000; // fetch the time as seconds
        } catch (Exception e) {
            Log4a.printException(e);
        }
        return timestamp;
    }

    public static String formatDate(Date date, DateFormatsEnums format) {
        return formatDate(date, format, Locale.ENGLISH);
    }

    public static String formatDate(Date date, DateFormatsEnums format, Locale locale) {
        String dateString = "";
        try {
            SimpleDateFormat dateFormatter = new SimpleDateFormat(format.getDateFormat(),
                    locale);
            dateString = dateFormatter.format(date);
        } catch (Exception e) {
            Log4a.printException(e);
        }
        return dateString;
    }


    public static String formatTime(Date date, TimeFormatEnums format) {
        return formatTime(date, format, Locale.ENGLISH);
    }

    public static String formatTime(Date date, TimeFormatEnums format, Locale locale) {
        String timeString = "";
        try {
            SimpleDateFormat dateFormatter = new SimpleDateFormat(format.getTimeFormat(), locale);
            timeString = dateFormatter.format(date);
        } catch (Exception e) {
            Log4a.printException(e);
        }
        return timeString;
    }


    public static String timeAgo(Date date, Context context) {
        return timeAgo(date.getTime(), context);
    }

    public static String timeAgo(long millis, Context context) {
        StringBuilder sb = new StringBuilder();
        try {
            long diff = new Date().getTime() - millis;
            Resources r = context.getResources();
            String prefix = r.getString(R.string.time_ago_prefix);
            String suffix = r.getString(R.string.time_ago_suffix);
            double seconds = Math.abs(diff) / 1000;
            double minutes = seconds / 60;
            double hours = minutes / 60;
            double days = hours / 24;
            double years = days / 365;
            String words;
            if (seconds < 45) {
                words = r.getString(R.string.time_ago_seconds,
                        (int) Math.round(seconds));
            } else if (seconds < 90) {
                words = r.getString(R.string.time_ago_minute, 1);
            } else if (minutes < 45) {
                words = r.getQuantityString(R.plurals.time_ago_minutes,
                        (int) Math.round(minutes), (int) Math.round(minutes));
            } else if (minutes < 90) {
                words = r.getString(R.string.time_ago_hour, 1);
            } else if (hours < 24) {
                words = r.getQuantityString(R.plurals.time_ago_hours,
                        (int) Math.round(hours), (int) Math.round(hours));
            } else if (hours < 42) {
                words = r.getString(R.string.time_ago_day, 1);
            } else if (days < 30) {
                words = r.getQuantityString(R.plurals.time_ago_days,
                        (int) Math.round(days), (int) Math.round(days));
            } else if (days < 45) {
                words = r.getString(R.string.time_ago_month, 1);
            } else if (days < 365) {
                words = r.getQuantityString(R.plurals.time_ago_months,
                        (int) Math.round(days / 30), (int) Math.round(days / 30));
            } else if (years < 1.5) {
                words = r.getString(R.string.time_ago_year, 1);
            } else {
                words = r.getQuantityString(R.plurals.time_ago_years,
                        (int) Math.round(years), (int) Math.round(years));
            }
            if (prefix != null && prefix.length() > 0) {
                sb.append(prefix).append(" ");
            }
            sb.append(words);
            if (suffix != null && suffix.length() > 0) {
                sb.append(" ").append(suffix);
            }
        } catch (Exception e) {
            Log4a.printException(e);
        }
        return sb.toString().trim();
    }
}
