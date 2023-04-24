/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse.flexibook.controller;
import java.util.*;

// line 73 "../../../../../FlexibookTransferObject.ump"
public class TOAppointment
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TOAppointment Associations
  private TOCustomer customer;
  private TOBookableService bookableService;
  private List<TOComboItem> chosenItems;
  private TOTimeSlot timeSlot;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TOAppointment(TOCustomer aCustomer, TOBookableService aBookableService, TOTimeSlot aTimeSlot)
  {
    boolean didAddCustomer = setCustomer(aCustomer);
    if (!didAddCustomer)
    {
      throw new RuntimeException("Unable to create tOAppointment due to customer. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddBookableService = setBookableService(aBookableService);
    if (!didAddBookableService)
    {
      throw new RuntimeException("Unable to create tOAppointment due to bookableService. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    chosenItems = new ArrayList<TOComboItem>();
    if (!setTimeSlot(aTimeSlot))
    {
      throw new RuntimeException("Unable to create TOAppointment due to aTimeSlot. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetOne */
  public TOCustomer getCustomer()
  {
    return customer;
  }
  /* Code from template association_GetOne */
  public TOBookableService getBookableService()
  {
    return bookableService;
  }
  /* Code from template association_GetMany */
  public TOComboItem getChosenItem(int index)
  {
    TOComboItem aChosenItem = chosenItems.get(index);
    return aChosenItem;
  }

  public List<TOComboItem> getChosenItems()
  {
    List<TOComboItem> newChosenItems = Collections.unmodifiableList(chosenItems);
    return newChosenItems;
  }

  public int numberOfChosenItems()
  {
    int number = chosenItems.size();
    return number;
  }

  public boolean hasChosenItems()
  {
    boolean has = chosenItems.size() > 0;
    return has;
  }

  public int indexOfChosenItem(TOComboItem aChosenItem)
  {
    int index = chosenItems.indexOf(aChosenItem);
    return index;
  }
  /* Code from template association_GetOne */
  public TOTimeSlot getTimeSlot()
  {
    return timeSlot;
  }
  /* Code from template association_SetOneToMany */
  public boolean setCustomer(TOCustomer aCustomer)
  {
    boolean wasSet = false;
    if (aCustomer == null)
    {
      return wasSet;
    }

    TOCustomer existingCustomer = customer;
    customer = aCustomer;
    if (existingCustomer != null && !existingCustomer.equals(aCustomer))
    {
      existingCustomer.removeTOAppointment(this);
    }
    customer.addTOAppointment(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setBookableService(TOBookableService aBookableService)
  {
    boolean wasSet = false;
    if (aBookableService == null)
    {
      return wasSet;
    }

    TOBookableService existingBookableService = bookableService;
    bookableService = aBookableService;
    if (existingBookableService != null && !existingBookableService.equals(aBookableService))
    {
      existingBookableService.removeTOAppointment(this);
    }
    bookableService.addTOAppointment(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfChosenItems()
  {
    return 0;
  }
  /* Code from template association_AddUnidirectionalMany */
  public boolean addChosenItem(TOComboItem aChosenItem)
  {
    boolean wasAdded = false;
    if (chosenItems.contains(aChosenItem)) { return false; }
    chosenItems.add(aChosenItem);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeChosenItem(TOComboItem aChosenItem)
  {
    boolean wasRemoved = false;
    if (chosenItems.contains(aChosenItem))
    {
      chosenItems.remove(aChosenItem);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addChosenItemAt(TOComboItem aChosenItem, int index)
  {  
    boolean wasAdded = false;
    if(addChosenItem(aChosenItem))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfChosenItems()) { index = numberOfChosenItems() - 1; }
      chosenItems.remove(aChosenItem);
      chosenItems.add(index, aChosenItem);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveChosenItemAt(TOComboItem aChosenItem, int index)
  {
    boolean wasAdded = false;
    if(chosenItems.contains(aChosenItem))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfChosenItems()) { index = numberOfChosenItems() - 1; }
      chosenItems.remove(aChosenItem);
      chosenItems.add(index, aChosenItem);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addChosenItemAt(aChosenItem, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetUnidirectionalOne */
  public boolean setTimeSlot(TOTimeSlot aNewTimeSlot)
  {
    boolean wasSet = false;
    if (aNewTimeSlot != null)
    {
      timeSlot = aNewTimeSlot;
      wasSet = true;
    }
    return wasSet;
  }

  public void delete()
  {
    TOCustomer placeholderCustomer = customer;
    this.customer = null;
    if(placeholderCustomer != null)
    {
      placeholderCustomer.removeTOAppointment(this);
    }
    TOBookableService placeholderBookableService = bookableService;
    this.bookableService = null;
    if(placeholderBookableService != null)
    {
      placeholderBookableService.removeTOAppointment(this);
    }
    chosenItems.clear();
    timeSlot = null;
  }

}