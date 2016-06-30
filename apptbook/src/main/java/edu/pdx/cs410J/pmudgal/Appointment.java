package edu.pdx.cs410J.pmudgal;

import edu.pdx.cs410J.AbstractAppointment;

public class Appointment extends AbstractAppointment {


  private String description;

  private String owner;

  private String beginTimeString;

  private String endTimeString;

  @Override
  public String getBeginTimeString() {
    return beginTimeString;
//    throw new UnsupportedOperationException("This method is not implemented yet");
  }

  @Override
  public String getEndTimeString() {
    return endTimeString;
//    throw new UnsupportedOperationException("This method is not implemented yet");
  }
  @Override
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getOwner() {
    return owner;
  }

  public void setOwner(String owner) {
    this.owner = owner;
  }

  public void setBeginTimeString(String beginTimeString) {
    this.beginTimeString = beginTimeString;
  }

  public void setEndTimeString(String endTimeString) {
    this.endTimeString = endTimeString;
  }

}
