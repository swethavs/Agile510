package edu.pdx.cs410J.pmudgal;

import edu.pdx.cs410J.AbstractAppointmentBook;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Vijay on 6/27/2016.
 */
public class AppointmentBook extends AbstractAppointmentBook<Appointment> {

    private ArrayList<Appointment> appointments = new ArrayList<Appointment>();

    private String ownerName;

    @Override
    public String getOwnerName() {
        return ownerName;
    }

    @Override
    public Collection<Appointment> getAppointments() {
        return appointments;
    }
    @Override
    public void addAppointment(Appointment t) {
        appointments.add(t);

    }

//    public void setAppointments(ArrayList<Appointment> appointments) {

//    }
//        this.appointments = appointments;
public void setOwnerName(String ownerName) {
    this.ownerName = ownerName;
}
}
