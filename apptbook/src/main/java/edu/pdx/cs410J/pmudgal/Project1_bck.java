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
public class Project1_bck {

    private static AppointmentBook appointmentBook = new AppointmentBook();


    public static void main(String[] args) {
//    System.out.println("Inside main");
        Class c = AbstractAppointmentBook.class;  // Refer to one of Dave's classes so that we can be sure it is on the classpath
        prepareAppointmentBook(args);
        System.exit(1);
    }

    private static void prepareAppointmentBook(String[] args) {


        prepareAppointmentBook1(args, appointmentBook);
//        System.out.println(appointmentBook.getAppointments().size());
        try {
            checkDateTimeFormat("12/12/2016 1:12");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static void prepareAppointmentBook1(String[] args, AppointmentBook appointmentBook) {

        Appointment appointment = new Appointment();
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-print")) {
                System.out.println("Print option");
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

            appointment.setOwner(args[i]);
            appointment.setDescription(args[++i]);
            appointment.setBeginTimeString(args[++i]);
            appointment.setBeginTimeString(appointment.getBeginTimeString().concat(" ").concat(args[++i]));
            appointment.setEndTimeString(args[++i]);
            appointment.setEndTimeString(appointment.getEndTimeString().concat(" ").concat(args[++i]));
            appointmentBook.addAppointment(appointment);

            System.out.println(appointment.getEndTimeString());
            System.out.println(appointment.getBeginTimeString());
            System.out.println(appointment.getDescription());
            System.out.println(appointment.getOwner());
        }

        try {
            boolean flag=true;
            BufferedReader br = getAppointmentFromUser(appointment);
            String accStr;

            System.out.println("Do you have more appointments? ");
            accStr = br.readLine();
            while(flag) {
                if (accStr.equalsIgnoreCase("Y")) {
                    br = getAppointmentFromUser(appointment);
                    System.out.println("Do you have more appointments? ");
                    accStr =br.readLine();
                    flag=true;
                }else{
                    flag=false;
                }
            }
            System.out.println(appointmentBook.getAppointments().size());
        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    private static BufferedReader getAppointmentFromUser(Appointment appointment) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String accStr;
        System.out.println("Do you have more appointments? ");
        accStr =br.readLine();
        System.out.println("Enter Owner Name: ");
        accStr = br.readLine();
        appointment.setOwner(accStr);

        System.out.println("Enter Description: ");
        accStr = br.readLine();
        appointment.setDescription(accStr);

        System.out.println("Enter Begin Date and Time ");
        accStr = br.readLine();
        appointment.setBeginTimeString(accStr);

        System.out.println("Enter End Date Time: ");
        accStr = br.readLine();
        appointment.setOwner(accStr);
        appointmentBook.addAppointment(appointment);
        return br;
    }

    private static void checkDateTimeFormat(String value) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("mm/dd/yyyy hh:mm");
        Date date =null;
        date = sdf.parse(value);
        System.out.println("It is valid date : "+ date);
    }
}