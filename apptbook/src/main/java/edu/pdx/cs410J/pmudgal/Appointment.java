package edu.pdx.cs410J.pmudgal;

import edu.pdx.cs410J.AbstractAppointment;

/**
 * This class contains the properties required for an appointment
 * namely: description, owner, beginTimeString, endTimeString and their getter and setters
 */
public class Appointment extends AbstractAppointment {

  /**
   * The properties: description, owner, beginTimeString, endTimeString
   */
  private String description;

  private String owner;

  private String beginTimeString;

  private String endTimeString;

  /**
   * getter for beginTimeString
   * @return beginTimeString
     */
  @Override
  public String getBeginTimeString() {
    return beginTimeString;
  }

  /**
   * getter for endTimeString
   * @return endTimeString
     */
  @Override
  public String getEndTimeString() {
    return endTimeString;
  }

  /**
   * getter for description
   * @return description
     */
  @Override
  public String getDescription() {
    return description;
  }


  /**
   * setter for description
   * @param description : Description for appointment
     */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * Getter for Owner
   * @return owner : Owner name
     */
  public String getOwner() {
    return owner;
  }

  /**
   * Setter for Owner
   * @param owner : Owner name
     */
  public void setOwner(String owner) {
    this.owner = owner;
  }

  /**
   * Setter for beginTimeString
   * @param beginTimeString : Begin date and time
     */
  public void setBeginTimeString(String beginTimeString) {
    this.beginTimeString = beginTimeString;
  }

  /**
   * Setter for endTimeString
   * @param endTimeString :End Time and date
     */
  public void setEndTimeString(String endTimeString) {
    this.endTimeString = endTimeString;
  }

}
