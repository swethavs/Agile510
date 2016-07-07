package edu.pdx.cs410J.pmudgal;

import edu.pdx.cs410J.AbstractAppointmentBook;

import javax.swing.plaf.synth.SynthEditorPaneUI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

/**
 * The main class for the CS410J appointment book Project
 */

public class Project1 {

    private static AppointmentBook appointmentBook = new AppointmentBook();

    /**
     *This is the main method of class which calls prepareApointmentBook
     * and adds the appointment to appointmentBook.
     * @param args : Command line arguments
     */
    public static void main(String[] args) {

        Class c = AbstractAppointmentBook.class;  // Refer to one of Dave's classes so that we can be sure it is on the classpath
        try {
                prepareAppointmentBook(args,appointmentBook);
        } catch (ParseException e) {
            System.out.println("Please provide the date and time in format mm/dd/yyyy hh:mm");
            System.exit(1);
        } catch (Exception e){
            e.getMessage();
        }
    }



    /**
     *This method takes the arguments passed form command line. It checks if the input string is options or arguments.
     * The passed arguments for sppointments are added to appointmentBook.
     * It also checks if the beginTime and endTime are in correct format.
     * In any error scenario case, and error message is shown and program exits.
     * @param args : Command line Arguments
     * @param appointmentBook : the appointment book which would contain appointments
     * @throws ParseException : In case of any exception while parsing the dates.
     */
    public static AppointmentBook prepareAppointmentBook(String[] args, AppointmentBook appointmentBook) throws ParseException {
        Appointment appointment = new Appointment();

        if(args.length==0){
            System.out.println("You did not provide any arguments. Please provide the missing arguments in format: "+Constants.usage);
        }else if(Arrays.asList(args).contains("-README")){
            displayReadme();
        } else {
            for (int i = 0; i < args.length; i++) {
                if (args[i].startsWith("-")) {
                    if (args[i].contains(Constants.PRINT)) {
                        System.out.println("Printing the Appointment : " + (args.length>2?args[2]:"No description provided in command line."));
                        i++;
                    } else {
                        System.out.println(args[i]+ ": This is not a correct option. Please provide the correct option.");
                        System.exit(1);
                    }
                }
                if ( args.length - i == 6) {
                    appointment.setOwner(validateOwnerName(args[i])); //Checks if the argument starts with "-", it is not considered as owner name
                    appointment.setDescription(checkNull(args[++i], "description")); //
                    appointment.setBeginTimeString(checkDateTimeFormat(checkNull(args[++i], "beginDateTime").concat(" ").concat(checkNull(args[++i], "beginDateTime"))));
                    appointment.setEndTimeString(checkDateTimeFormat(checkNull(args[++i], "endDateTime").concat(" ").concat(checkNull(args[++i], "endDateTime"))));
                    appointmentBook.addAppointment(appointment);
                    System.out.println(appointment.toString());
                } else if( args.length - i < 6){
                    System.out.println("Some arguments are missing. Please provide complete arguments : owner description beginDateTime endDateTime");
                    System.exit(1);
                }else if( args.length - i > 6){
                    System.out.println("There are extra arguments provided than required arguments.\n" +
                            Constants.PROVIDE_ARG);
                    System.exit(1);
                }
            }

        }
        return appointmentBook;
    }

    /**
     * This method checks if the passed argument is valid Owner
     * or it is some option and not arg.
     * @param arg : ownerName of the appointment
     * @return
     */
    private static String validateOwnerName(String arg) {
        if(!checkNull(arg, "owner name").startsWith("-")){
            checkNull(arg, "owner name");
        }else{
            System.out.println(arg+ " : This does not seem to be a valid owner name as it starts with '-'.\n"+Constants.PROVIDE_ARG);
            System.exit(1);
        }
        return arg;
    }

    /**
     *This method prints the README if -README is passed from command line
     */
    private static void displayReadme() {
        System.out.println(Constants.README_DESC);
        System.exit(1);
    }

    /**
     * This method checks if the passed string is not null
     * and not empty and also make sure that it contains data.
     * @param string
     * @param fieldName
     * @return
     */
    private static String checkNull(String string, String fieldName){
        if(string!=null && !string.trim().isEmpty() && !string.trim().equals("")){
            return string;
        }else {
            System.out.println("Please provide " + fieldName);
            System.exit(1);
            return "Please specify " + fieldName;

        }
    }

    /**
     * *This method checks for the correct format of date and time.
     *The date and time should be in format "mm/dd/yyyy hh:mm' or "m/d/yyyy hh:mm".
     * This method also check for invalid dates e.g. 13/01/2015 or 12/40/2015.
     * Also enforces the year has to pass with 4 digits.
     * @param value
     * @return
     * @throws ParseException
     */
    private static String checkDateTimeFormat(String value) throws ParseException {
        if (value == null || !value.matches("^\\d{1,2}/\\d{1,2}/\\d{4} \\d{1,2}:\\d{2}$")) {
            System.out.println("Please provide the date and time in format mm/dd/yyyy hh:mm");
            System.exit(1);
        }else{
            SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm");
            df.setLenient(false);
           df.parse(value);
        }
        return value;
    }

    /**
     * This method checks that the appointment's end date and time
     * should not be less than or same as appointment's begin date
     * and time.
     * This method is not used currently, however,
     * I have not removed this method as this may be
     * used for future functionality.
     * @param appointment : appointment is passed to compare eend dates and begin dat
     * @return
     */
    private static boolean compareEndDateBeginDate(Appointment appointment){
        if(appointment.getEndTimeString().compareTo(appointment.getBeginTimeString())<1){
            System.out.println("Appointment End date and time cannot be same or less than appointment's begin date and time.");
            System.exit(1);
        }
        return  true;
    }



}