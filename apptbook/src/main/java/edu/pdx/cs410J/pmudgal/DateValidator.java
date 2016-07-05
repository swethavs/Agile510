package edu.pdx.cs410J.pmudgal;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateValidator
{
    private static final DateFormat DEFAULT_FORMATTER;

    static
    {
        DEFAULT_FORMATTER = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        DEFAULT_FORMATTER.setLenient(false);
    }

    public static void main(String[] args)
    {
//        for (String dateString : args)
//        {
            try
            {
convertDateString("02/03/2016 16:06");
            }
            catch (ParseException e)
            {
                System.out.println("could not parse " );
            }
//        }
    }

    public static Date convertDateString(String dateString) throws ParseException
    {
        if (dateString.matches("^\\d{1,2}/\\d{1,2}/\\d{4} \\d{1,2}:\\d{2}$")) {
            System.out.println("Not matched" +DEFAULT_FORMATTER.parse(dateString));
            return DEFAULT_FORMATTER.parse(dateString);

        }else{
            System.out.println("else");
        }
        return new Date();
    }
}