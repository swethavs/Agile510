package edu.pdx.cs410J.pmudgal;

import edu.pdx.cs410J.AbstractAppointmentBook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * The main class for the CS410J appointment book Project
 */
public class Project1 {

    private static AppointmentBook appointmentBook = new AppointmentBook();


    public static void main(String[] args) {
//    System.out.println("Inside main");
        Class c = AbstractAppointmentBook.class;  // Refer to one of Dave's classes so that we can be sure it is on the classpath
        try {
            prepareAppointmentBook(args);
        } catch (ParseException e) {
            System.out.println("Please enter the correct date and time format mm/dd/yyyy hh:mm");
        }
        System.exit(1);
    }

    private static void prepareAppointmentBook(String[] args) throws ParseException {


        prepareAppointmentBook1(args, appointmentBook);
//        System.out.println(appointmentBook.getAppointments().size());
        try {
            checkDateTimeFormat("12/12/2016 1:12");
        } catch (ParseException e) {
            System.out.println("Please enter the correct date and time format mm/dd/yyyy hh:mm");
        }
    }

    public static void prepareAppointmentBook1(String[] args, AppointmentBook appointmentBook) throws ParseException {

        Appointment appointment = new Appointment();
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-print")) {

                System.out.println("Printing the Appointment : "+ args[2]);
                i++;
            } else if (args[i].equals("-README")) {
                //do something for readme
                System.out.println("This programs is preparing an appointment book containing the owner name, description, begintime and endTime");
                System.exit(1);
            }
            if (args[i].equals("-README")) {
                //do something for readme
                System.out.println("This programs is preparing an appointment book containing the owner name, description, begintime and endTime");
                System.exit(1);
            }

            appointment.setOwner(checkNull(args[i]));
            appointment.setDescription(checkNull(args[++i]));
            appointment.setBeginTimeString(checkDateTimeFormat(checkNull(args[++i]).concat(" ").concat(checkNull(args[++i]))));
            appointment.setEndTimeString(checkDateTimeFormat(checkNull(args[++i]).concat(" ").concat(checkNull(args[++i]))));
            appointmentBook.addAppointment(appointment);

            System.out.println(appointment.getEndTimeString());
            System.out.println(appointment.getBeginTimeString());
            System.out.println(appointment.getDescription());
            System.out.println(appointment.getOwner());
        }





    }

    private static String checkNull(String string){
        if(string!=null){
            return string;
        }else {
            return new String();
        }
    }



    private static String checkDateTimeFormat(String value) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("mm/dd/yyyy hh:mm");
        Date date =null;
        date = sdf.parse(value);
        System.out.println("It is valid date : "+ date);
        return value;
    }
}