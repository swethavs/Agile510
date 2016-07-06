package edu.pdx.cs410J.pmudgal;

import edu.pdx.cs410J.AbstractAppointmentBook;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Priyanka on 6/27/2016.
 * Appointment Book which contains the appointment's list
 */
public class AppointmentBook extends AbstractAppointmentBook<Appointment> {
    /**
     * List of Appointment
     */
    private ArrayList<Appointment> appointments = new ArrayList<Appointment>();

    private String ownerName;

    /**
     * Getter for ownerName
     * @return ownerName
     */
    @Override
    public String getOwnerName() {
        return ownerName;
    }

    /**
     * Getter for the list of Appointments
     * @return appointments
     */
    @Override
    public Collection<Appointment> getAppointments() {
        return appointments;
    }

    /**
     * Adds the appointments to the appointment List
     * @param t : Appointment
     */
    @Override
    public void addAppointment(Appointment t) {
        appointments.add(t);

    }

    /**
     * Set of OwnerName
     * @param ownerName : Owner name
     */
    public void setOwnerName(String ownerName) {
    this.ownerName = ownerName;
}
}
