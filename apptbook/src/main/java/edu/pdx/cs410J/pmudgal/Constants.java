package edu.pdx.cs410J.pmudgal;

/**
 * Created by Vijay on 6/27/2016.
 */
public class Constants {
    public static final String PRINT="-print";
    public static final String TEXTFILE="-textfile";
    public static final String README="-README";
    public static final String PROVIDE_ARG="Please provide the arguments in order: owner description beginDateTime endDateTime";

    public static final String README_DESC="This is PROJECT1 written by PRIYANKA MUDGAL.\n" +
            " The motive of this project is to prepare an appointment book containing the owner name, description, begintime and endTime.\n" +
            " The project provides an additional functionality to print the description or just print the README.\n";

    public static final String usage=" java edu.pdx.cs410J.pmudgal.Project1 [options] <args>\n" +
            "args are (in this order):\n" +
            "owner: The person whose owns the appt book\n" +
            "description: A description of the appointment\n" +
            "beginTime: When the appt begins (24-hour time)\n" +
            "endTime: When the appt ends (24-hour time)\n" +
            "options are (options may appear in any order):\n" +
            "-print Prints a description of the new appointment\n" +
            "-README Prints a README for this project and exits\n" +
            "Date and time should be in the format: mm/dd/yyyy hh:mm\n";
}
