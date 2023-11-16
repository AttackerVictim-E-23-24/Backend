package com.ucab.cmcapp.logic.mappers;

import com.ucab.cmcapp.properties.Registry;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseMapper
{
    public static Date parseStringToDate( String date ) throws ParseException
    {
        SimpleDateFormat formatter =  new SimpleDateFormat(
                Registry.getInstance().getProperty( Registry.DATE_FORMAT ));

        return formatter.parse( date );
    }

    public static String formatDateToString( Date dateTime )
    {
        SimpleDateFormat formatter = new SimpleDateFormat(
                Registry.getInstance().getProperty( Registry.DATE_FORMAT ));

        return formatter.format( dateTime );
    }

    public static boolean isAnyBlank(final CharSequence... css)
    {
        if (css != null && css.length == 0)
        {
            return false;
        }
        for (final CharSequence cs : css)
        {
            if (isBlank(cs))
            {
                return true;
            }
        }
        return false;
    }

    public static boolean isBlank(final CharSequence cs)
    {
        int strLen;
        if (cs == null || (strLen = cs.length()) == 0)
        {
            return true;
        }
        for (int i = 0; i < strLen; i++)
        {
            if (!Character.isWhitespace(cs.charAt(i)))
            {
                return false;
            }
        }
        return true;
    }

    public static boolean isNullOrEmptyString(String string)
    {
        if(string == null)
            return true;
        return string.equals("");
    }
}
