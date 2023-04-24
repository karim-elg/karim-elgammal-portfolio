/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse.flexibook.controller;
import java.util.*;

// line 43 "../../../../../FlexibookTransferObject.ump"
public class TOCustomer extends TOUser
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TOCustomer Associations
  private List<TOAppointment> tOAppointments;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TOCustomer(String aUsername, String aPassword)
  {
    super(aUsername, aPassword);
    tOAppointments = new ArrayList<TOAppointment>();
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetMany */
  public TOAppointment getTOAppointment(int index)
  {
    TOAppointment aTOAppointment = tOAppointments.get(index);
    return aTOAppointment;
  }

  public List<TOAppointment> getTOAppointments()
  {
    List<TOAppointment> newTOAppointments = Collections.unmodifiableList(tOAppointments);
    return newTOAppointments;
  }

  public int numberOfTOAppointments()
  {
    int number = tOAppointments.size();
    return number;
  }

  public boolean hasTOAppointments()
  {
    boolean has = tOAppointments.size() > 0;
    return has;
  }

  public int indexOfTOAppointment(TOAppointment aTOAppointment)
  {
    int index = tOAppointments.indexOf(aTOAppointment);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfTOAppointments()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public TOAppointment addTOAppointment(TOBookableService aBookableService, TOTimeSlot aTimeSlot)
  {
    return new TOAppointment(this, aBookableService, aTimeSlot);
  }

  public boolean addTOAppointment(TOAppointment aTOAppointment)
  {
    boolean wasAdded = false;
    if (tOAppointments.contains(aTOAppointment)) { return false; }
    TOCustomer existingCustomer = aTOAppointment.getCustomer();
    boolean isNewCustomer = existingCustomer != null && !this.equals(existingCustomer);
    if (isNewCustomer)
    {
      aTOAppointment.setCustomer(this);
    }
    else
    {
      tOAppointments.add(aTOAppointment);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeTOAppointment(TOAppointment aTOAppointment)
  {
    boolean wasRemoved = false;
    //Unable to remove aTOAppointment, as it must always have a customer
    if (!this.equals(aTOAppointment.getCustomer()))
    {
      tOAppointments.remove(aTOAppointment);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addTOAppointmentAt(TOAppointment aTOAppointment, int index)
  {  
    boolean wasAdded = false;
    if(addTOAppointment(aTOAppointment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTOAppointments()) { index = numberOfTOAppointments() - 1; }
      tOAppointments.remove(aTOAppointment);
      tOAppointments.add(index, aTOAppointment);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveTOAppointmentAt(TOAppointment aTOAppointment, int index)
  {
    boolean wasAdded = false;
    if(tOAppointments.contains(aTOAppointment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTOAppointments()) { index = numberOfTOAppointments() - 1; }
      tOAppointments.remove(aTOAppointment);
      tOAppointments.add(index, aTOAppointment);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addTOAppointmentAt(aTOAppointment, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    for(int i=tOAppointments.size(); i > 0; i--)
    {
      TOAppointment aTOAppointment = tOAppointments.get(i - 1);
      aTOAppointment.delete();
    }
    super.delete();
  }

}