package edu.pdx.cs410J.pmudgal;

import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Priyanka on 7/6/2016.
 */
public class Project2 {
    public static void main(String[] args){
        Project1 project1=new Project1();
        AppointmentBook appointmentBook = new AppointmentBook();
        try {
            ArrayList<String> arrayList= new ArrayList<String>(Arrays.asList(args));
            if(arrayList.contains("-textfile")) {
                int index=arrayList.indexOf("-textfile");

                arrayList.remove(index+1);
                arrayList.remove("-textfile");

                String[] stockArr = new String[arrayList.size()];
                stockArr=  arrayList.toArray(new String[arrayList.size()]);
                System.out.println(stockArr.length);
                appointmentBook = dumpTheContentsToFile(stockArr);
                writeToFile("/hardCode.txt",appointmentBook);
            }else{
                project1.prepareAppointmentBook(args,appointmentBook);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void writeToFile(String filename, AppointmentBook appointmentBook) {
        try {
            //Whatever the file path is.
            File statText = new File(filename);
            if(statText.exists()) {
                FileOutputStream is = new FileOutputStream(statText);
                OutputStreamWriter osw = new OutputStreamWriter(is);
                Writer w = new BufferedWriter(osw);
                w.write(appointmentBook.getAppointments().toString());
                w.close();
            }else{
                System.out.println("File does not exist");
            }
        } catch (IOException e) {
            System.err.println("Problem writing to the file statsTest.txt");
            e.printStackTrace();
        }
    }

    public static AppointmentBook dumpTheContentsToFile(String[] args) throws ParseException, IOException {
        Project1 project1 =new Project1();
        AppointmentBook appointmentBook = new AppointmentBook();
//        appointmentBook = readFromFile(appointmentBook);
        appointmentBook = project1.prepareAppointmentBook(args, appointmentBook);
        return appointmentBook;
    }

    public static AppointmentBook readFromFile(AppointmentBook appointmentBook) throws IOException {
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

