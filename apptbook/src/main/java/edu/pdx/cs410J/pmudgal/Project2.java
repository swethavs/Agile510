package edu.pdx.cs410J.pmudgal;

import java.io.*;
import java.text.ParseException;

/**
 * Created by Vijay on 7/6/2016.
 */
public class Project2 {
    public static void main(String[] args){
        try {
            AppointmentBook appointmentBook = dumpTheContentsToFile(args);
            writeToFile(appointmentBook);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void writeToFile(AppointmentBook appointmentBook) {
        try {
            //Whatever the file path is.
            File statText = new File("D:/statsTest.txt");
            FileOutputStream is = new FileOutputStream(statText);
            OutputStreamWriter osw = new OutputStreamWriter(is);
            Writer w = new BufferedWriter(osw);
            w.write("POTATO!!!");
            w.close();
        } catch (IOException e) {
            System.err.println("Problem writing to the file statsTest.txt");
        }
    }

    private static AppointmentBook dumpTheContentsToFile(String[] args) throws ParseException, IOException {
        Project1 project1 =new Project1();
        AppointmentBook appointmentBook = new AppointmentBook();
        appointmentBook = readFromFile(appointmentBook);
        appointmentBook = project1.prepareAppointmentBook(args, appointmentBook);
        return appointmentBook;
    }

    private static AppointmentBook readFromFile(AppointmentBook appointmentBook) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("file.txt"));
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            String everything = sb.toString();
        } finally {
            br.close();
        }
        return appointmentBook;
    }

}

