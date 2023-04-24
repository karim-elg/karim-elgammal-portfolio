/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse.flexibook.controller;

// line 83 "../../../../../FlexibookTransferObject.ump"
public class TOAppointmentCalendarItem
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TOAppointmentCalendarItem Attributes
  private String date;
  private String startTime;
  private String endTime;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TOAppointmentCalendarItem(String aDate, String aStartTime, String aEndTime)
  {
    date = aDate;
    startTime = aStartTime;
    endTime = aEndTime;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setDate(String aDate)
  {
    boolean wasSet = false;
    date = aDate;
    wasSet = true;
    return wasSet;
  }

  public boolean setStartTime(String aStartTime)
  {
    boolean wasSet = false;
    startTime = aStartTime;
    wasSet = true;
    return wasSet;
  }

  public boolean setEndTime(String aEndTime)
  {
    boolean wasSet = false;
    endTime = aEndTime;
    wasSet = true;
    return wasSet;
  }

  public String getDate()
  {
    return date;
  }

  public String getStartTime()
  {
    return startTime;
  }

  public String getEndTime()
  {
    return endTime;
  }

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+
            "date" + ":" + getDate()+ "," +
            "startTime" + ":" + getStartTime()+ "," +
            "endTime" + ":" + getEndTime()+ "]";
  }
}