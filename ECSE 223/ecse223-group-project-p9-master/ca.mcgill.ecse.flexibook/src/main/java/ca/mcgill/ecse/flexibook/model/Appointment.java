/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse.flexibook.model;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.util.*;

// line 57 "../../../../../FlexiBookPersistence.ump"
// line 3 "../../../../../FlexiBookStateMachine.ump"
// line 93 "../../../../../FlexiBook.ump"
public class Appointment implements Serializable
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Appointment Attributes
  private boolean isUpdateSucces;

  //Appointment State Machines
  public enum Status { Issued, InProgress, NoShow, Cancelled, Completed }
  private Status status;

  //Appointment Associations
  private Customer customer;
  private BookableService bookableService;
  private List<ComboItem> chosenItems;
  private TimeSlot timeSlot;
  private FlexiBook flexiBook;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Appointment(Customer aCustomer, BookableService aBookableService, TimeSlot aTimeSlot, FlexiBook aFlexiBook)
  {
    isUpdateSucces = true;
    boolean didAddCustomer = setCustomer(aCustomer);
    if (!didAddCustomer)
    {
      throw new RuntimeException("Unable to create appointment due to customer. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddBookableService = setBookableService(aBookableService);
    if (!didAddBookableService)
    {
      throw new RuntimeException("Unable to create appointment due to bookableService. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    chosenItems = new ArrayList<ComboItem>();
    if (!setTimeSlot(aTimeSlot))
    {
      throw new RuntimeException("Unable to create Appointment due to aTimeSlot. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddFlexiBook = setFlexiBook(aFlexiBook);
    if (!didAddFlexiBook)
    {
      throw new RuntimeException("Unable to create appointment due to flexiBook. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    setStatus(Status.Issued);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setIsUpdateSucces(boolean aIsUpdateSucces)
  {
    boolean wasSet = false;
    isUpdateSucces = aIsUpdateSucces;
    wasSet = true;
    return wasSet;
  }

  public boolean getIsUpdateSucces()
  {
    return isUpdateSucces;
  }

  public String getStatusFullName()
  {
    String answer = status.toString();
    return answer;
  }

  public Status getStatus()
  {
    return status;
  }

  public boolean modifyAppointment(Appointment a,FlexiBook fbs,Date currentDate,Time currentTime,boolean isDateAndTimeModification,boolean isOptionalServiceModification,boolean isNewServiceModification,Service s,Date newDate,Time newTime)
  {
    boolean wasEventProcessed = false;
    
    Status aStatus = status;
    switch (aStatus)
    {
      case Issued:
        if (isAppointmentBeforeOneDay(a,currentDate,currentTime)&&isAppointmentTimeValid(a,fbs,isDateAndTimeModification,isOptionalServiceModification,isNewServiceModification,s,newDate,newTime))
        {
        // line 10 "../../../../../FlexiBookStateMachine.ump"
          doModifyAppointment( a,  fbs,  currentDate,  currentTime,  isDateAndTimeModification,  isOptionalServiceModification,  isNewServiceModification,  s, newDate, newTime);
          setStatus(Status.Issued);
          wasEventProcessed = true;
          break;
        }
        if (!(isAppointmentBeforeOneDay(a,currentDate,currentTime)))
        {
        // line 11 "../../../../../FlexiBookStateMachine.ump"
          rejectModifyAppointment();
          setStatus(Status.Issued);
          wasEventProcessed = true;
          break;
        }
        if (!(isAppointmentTimeValid(a,fbs,isDateAndTimeModification,isOptionalServiceModification,isNewServiceModification,s,newDate,newTime)))
        {
        // line 12 "../../../../../FlexiBookStateMachine.ump"
          rejectModifyAppointment();
          setStatus(Status.Issued);
          wasEventProcessed = true;
          break;
        }
        break;
      case InProgress:
        if (isOptionalService(isOptionalServiceModification)&&isAppointmentTimeValid(a,fbs,isDateAndTimeModification,isOptionalServiceModification,isNewServiceModification,s,newDate,newTime))
        {
        // line 27 "../../../../../FlexiBookStateMachine.ump"
          doModifyAppointment(a,  fbs,  currentDate,  currentTime,  isDateAndTimeModification,  isOptionalServiceModification,  isNewServiceModification,  s, newDate, newTime);
          setStatus(Status.InProgress);
          wasEventProcessed = true;
          break;
        }
        if (isDateTimeModification(isDateAndTimeModification)&&isAppointmentTimeValid(a,fbs,isDateAndTimeModification,isOptionalServiceModification,isNewServiceModification,s,newDate,newTime))
        {
        // line 28 "../../../../../FlexiBookStateMachine.ump"
          rejectModifyAppointment();
          setStatus(Status.InProgress);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean cancelAppointment(Appointment a,FlexiBook fbs,Date currentDate,Time currentTime)
  {
    boolean wasEventProcessed = false;
    
    Status aStatus = status;
    switch (aStatus)
    {
      case Issued:
        if (isAppointmentBeforeOneDay(a,currentDate,currentTime))
        {
          setStatus(Status.Cancelled);
          wasEventProcessed = true;
          break;
        }
        if (!(isAppointmentBeforeOneDay(a,currentDate,currentTime)))
        {
        // line 15 "../../../../../FlexiBookStateMachine.ump"
          rejectCancel();
          setStatus(Status.Issued);
          wasEventProcessed = true;
          break;
        }
        break;
      case InProgress:
        // line 34 "../../../../../FlexiBookStateMachine.ump"
        rejectCancel();
        setStatus(Status.InProgress);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean startAppointment(Appointment a,FlexiBook fbs,Date currentDate,Time currentTime)
  {
    boolean wasEventProcessed = false;
    
    Status aStatus = status;
    switch (aStatus)
    {
      case Issued:
        if (isAppointmentAfterNow(a,fbs,currentDate,currentTime))
        {
          setStatus(Status.InProgress);
          wasEventProcessed = true;
          break;
        }
        if (!(isAppointmentAfterNow(a,fbs,currentDate,currentTime)))
        {
          setStatus(Status.Issued);
          wasEventProcessed = true;
          break;
        }
        break;
      case InProgress:
        setStatus(Status.InProgress);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean customerDidNotArrive(Customer c,Appointment a)
  {
    boolean wasEventProcessed = false;
    
    Status aStatus = status;
    switch (aStatus)
    {
      case Issued:
        // line 20 "../../../../../FlexiBookStateMachine.ump"
        incrementCustomerNoShow( c);
        setStatus(Status.NoShow);
        wasEventProcessed = true;
        break;
      case InProgress:
        setStatus(Status.InProgress);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean finishAppointment(Appointment a,FlexiBook fbs)
  {
    boolean wasEventProcessed = false;
    
    Status aStatus = status;
    switch (aStatus)
    {
      case Issued:
        setStatus(Status.Issued);
        wasEventProcessed = true;
        break;
      case InProgress:
        setStatus(Status.Completed);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  private void setStatus(Status aStatus)
  {
    status = aStatus;

    // entry actions and do activities
    switch(status)
    {
      case NoShow:
        // line 40 "../../../../../FlexiBookStateMachine.ump"
        this.delete();
        break;
      case Cancelled:
        // line 44 "../../../../../FlexiBookStateMachine.ump"
        delete();
        break;
      case Completed:
        // line 48 "../../../../../FlexiBookStateMachine.ump"
        delete();
        break;
    }
  }
  /* Code from template association_GetOne */
  public Customer getCustomer()
  {
    return customer;
  }
  /* Code from template association_GetOne */
  public BookableService getBookableService()
  {
    return bookableService;
  }
  /* Code from template association_GetMany */
  public ComboItem getChosenItem(int index)
  {
    ComboItem aChosenItem = chosenItems.get(index);
    return aChosenItem;
  }

  public List<ComboItem> getChosenItems()
  {
    List<ComboItem> newChosenItems = Collections.unmodifiableList(chosenItems);
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

  public int indexOfChosenItem(ComboItem aChosenItem)
  {
    int index = chosenItems.indexOf(aChosenItem);
    return index;
  }
  /* Code from template association_GetOne */
  public TimeSlot getTimeSlot()
  {
    return timeSlot;
  }
  /* Code from template association_GetOne */
  public FlexiBook getFlexiBook()
  {
    return flexiBook;
  }
  /* Code from template association_SetOneToMany */
  public boolean setCustomer(Customer aCustomer)
  {
    boolean wasSet = false;
    if (aCustomer == null)
    {
      return wasSet;
    }

    Customer existingCustomer = customer;
    customer = aCustomer;
    if (existingCustomer != null && !existingCustomer.equals(aCustomer))
    {
      existingCustomer.removeAppointment(this);
    }
    customer.addAppointment(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setBookableService(BookableService aBookableService)
  {
    boolean wasSet = false;
    if (aBookableService == null)
    {
      return wasSet;
    }

    BookableService existingBookableService = bookableService;
    bookableService = aBookableService;
    if (existingBookableService != null && !existingBookableService.equals(aBookableService))
    {
      existingBookableService.removeAppointment(this);
    }
    bookableService.addAppointment(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfChosenItems()
  {
    return 0;
  }
  /* Code from template association_AddUnidirectionalMany */
  public boolean addChosenItem(ComboItem aChosenItem)
  {
    boolean wasAdded = false;
    if (chosenItems.contains(aChosenItem)) { return false; }
    chosenItems.add(aChosenItem);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeChosenItem(ComboItem aChosenItem)
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
  public boolean addChosenItemAt(ComboItem aChosenItem, int index)
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

  public boolean addOrMoveChosenItemAt(ComboItem aChosenItem, int index)
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
  public boolean setTimeSlot(TimeSlot aNewTimeSlot)
  {
    boolean wasSet = false;
    if (aNewTimeSlot != null)
    {
      timeSlot = aNewTimeSlot;
      wasSet = true;
    }
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setFlexiBook(FlexiBook aFlexiBook)
  {
    boolean wasSet = false;
    if (aFlexiBook == null)
    {
      return wasSet;
    }

    FlexiBook existingFlexiBook = flexiBook;
    flexiBook = aFlexiBook;
    if (existingFlexiBook != null && !existingFlexiBook.equals(aFlexiBook))
    {
      existingFlexiBook.removeAppointment(this);
    }
    flexiBook.addAppointment(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Customer placeholderCustomer = customer;
    this.customer = null;
    if(placeholderCustomer != null)
    {
      placeholderCustomer.removeAppointment(this);
    }
    BookableService placeholderBookableService = bookableService;
    this.bookableService = null;
    if(placeholderBookableService != null)
    {
      placeholderBookableService.removeAppointment(this);
    }
    chosenItems.clear();
    timeSlot = null;
    FlexiBook placeholderFlexiBook = flexiBook;
    this.flexiBook = null;
    if(placeholderFlexiBook != null)
    {
      placeholderFlexiBook.removeAppointment(this);
    }
  }

  // line 56 "../../../../../FlexiBookStateMachine.ump"
   private boolean isAppointmentBeforeOneDay(Appointment a, Date currentDate, Time currentTime){
    //TODO mihir
    	
    	Date appointment = a.getTimeSlot().getStartDate();
    	Time Tappointment = a.getTimeSlot().getStartTime();
        
        System.out.println(appointment +  " minus " + currentDate);
        System.out.println(appointment.getTime() - currentDate.getTime());
    	if(appointment.getTime() - currentDate.getTime() >= 86400000) {
    		return true;
    	}
    		
    	return false;
  }

  // line 79 "../../../../../FlexiBookStateMachine.ump"
   private boolean isAppointmentAfterNow(Appointment a, FlexiBook fbs, Date currentDate, Time currentTime){
    //TODO cecile
    	boolean isAfterNow = false;
    	if((currentDate.compareTo(a.getTimeSlot().getStartDate()) >= 0) && (currentTime.compareTo(a.getTimeSlot().getStartTime()) >= 0)){
    		isAfterNow = true;
    	}
    	return isAfterNow;
  }

  // line 89 "../../../../../FlexiBookStateMachine.ump"
   private boolean isAppointmentTimeValid(Appointment a, FlexiBook fbs, boolean isDateAndTimeModification, boolean isOptionalServiceModification, boolean isNewServiceModification, Service s, Date newDate, Time newTime){
    TimeSlot finalSlot = new TimeSlot(newDate, newTime, newDate, newTime, fbs);
	  	 if (isDateAndTimeModification && isNewServiceModification) {
		   BookableService b = a.getBookableService();
		   int duration = ((Service)b).getDuration();
		   Time endTime = new Time(newTime.getTime() + duration*60000);
		  
		   finalSlot = new TimeSlot(newDate, newTime, newDate, endTime, fbs);
	   }
	   //appointment for combo and modify date and time
	   else if (isDateAndTimeModification && isOptionalServiceModification) {
		   boolean areThereOptional = false;
		   int duration = 0;
		   
		   List<ComboItem> chosen = a.getChosenItems();
		   if (chosen.size() > 0) {
			   areThereOptional = true;
		   }
		   if (areThereOptional) {
			   for (ComboItem c : chosen) {
				   duration+= c.getService().getDuration();
			   }
		   }
		   ServiceCombo c = ((ServiceCombo)a.getBookableService());
		   List<ComboItem> current = c.getServices();
		  
		   for (ComboItem ci : current) {
			   if (ci.getMandatory() ==  true) {
				   duration+=ci.getService().getDuration();
			   }
			  
		   }
		   
		   Time endTime = new Time(newTime.getTime() + duration*60000);
		   finalSlot = new TimeSlot(newDate, newTime, newDate, endTime, fbs);
		
		   
	   }
	   //appointment for combo and modify optional service
	   else if (isOptionalServiceModification) {
		   ServiceCombo c = ((ServiceCombo)a.getBookableService());
		   int duration = 0;
		   List<ComboItem> current = c.getServices();
		   String chosenName = s.getName();
		   ComboItem chosenItem = null;
		   for (ComboItem item : current) {
			   if (item.getService().getName().equals(chosenName)) {
				   chosenItem = item;
			   }
		   }
		   for (ComboItem ci : current) {
			   if (ci.getMandatory() ==  true) {
				   duration+=ci.getService().getDuration();
			   }
			  
		   }
		   
		   duration+=chosenItem.getService().getDuration();
		   
		   Time endTime = new Time(a.getTimeSlot().getStartTime().getTime() + duration*60000);
		   finalSlot = new TimeSlot(a.getTimeSlot().getStartDate(), a.getTimeSlot().getStartTime(), a.getTimeSlot().getEndDate(), endTime, fbs);

	   }
	   //appointment for service and modify the service
	   else if (isNewServiceModification) {
		   int duration =  0;
		  // a.setBookableService(s);  //CHANGE HEREEEEEE
		   duration += s.getDuration();
		   
		   Time endTime = new Time(a.getTimeSlot().getStartTime().getTime() + duration*60000);
		   finalSlot = new TimeSlot(a.getTimeSlot().getStartDate(),  a.getTimeSlot().getStartTime(), a.getTimeSlot().getStartDate(), endTime, fbs);
		
	   }
	   
	   	Date startDate = finalSlot.getStartDate();
	   	Date endDate = finalSlot.getEndDate();
   		Time startTime = finalSlot.getStartTime();
   		Time endTime = finalSlot.getEndTime();
   		BusinessHour.DayOfWeek weekDay;
   	
    	boolean isValid = false;
    	boolean businessValid = false;
    	boolean holidayValid = false;
    	boolean vacationValid = false;
    	boolean appointmentValid = false;
    	
    	Calendar c = Calendar.getInstance();
		c.setTime(startDate);
		int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
    	
	        switch(dayOfWeek){
	            case 2:
	                weekDay = BusinessHour.DayOfWeek.Monday;
	                break;
	            case 3:
	                weekDay = BusinessHour.DayOfWeek.Tuesday;
	                break;
	            case 4:
	                weekDay = BusinessHour.DayOfWeek.Wednesday;
	                break;
	            case 5:
	                weekDay = BusinessHour.DayOfWeek.Thursday;
	                break;
	            case 6:
	                weekDay = BusinessHour.DayOfWeek.Friday;
	                break;
	            case 7:
	                weekDay = BusinessHour.DayOfWeek.Saturday;
	                break;
	            case 1:
	                weekDay = BusinessHour.DayOfWeek.Sunday;
	                break;
	            default:
	                weekDay = null;
	        }
    	for(BusinessHour bh : fbs.getBusiness().getBusinessHours()){

    		if(weekDay == bh.getDayOfWeek()){
    			if((startTime.compareTo(bh.getStartTime()) >= 0) && (endTime.compareTo(bh.getEndTime()) <= 0)){
    				businessValid = true;
    				break;
    			}
    		}
    	}
    	
    	int holidayPass = 0;
    	
    	for(TimeSlot timeSlot : fbs.getBusiness().getHolidays()){
    		if(endDate.compareTo(timeSlot.getStartDate()) < 0){
    			holidayPass++;
    		}
    		else if(startDate.compareTo(timeSlot.getEndDate()) > 0){
    			holidayPass++;
    		}
    		else if(endDate.compareTo(timeSlot.getStartDate()) == 0){
    			if(endTime.compareTo(timeSlot.getStartTime()) < 0){
    				holidayPass++;
    			}
    		}
    		else if(startDate.compareTo(timeSlot.getEndDate()) == 0){
    			if(startTime.compareTo(timeSlot.getEndTime()) > 0){
    				holidayPass++;
    			}
    		}
    	}
    	
    	if(holidayPass == fbs.getBusiness().getHolidays().size()) {
    		holidayValid = true;
    	}
    	
    	int vacationPass = 0;
    	
    	for(TimeSlot timeSlot : fbs.getBusiness().getVacation()){
    	
    		if(endDate.compareTo(timeSlot.getStartDate()) < 0){
    			vacationPass++;
    		}
    		else if(startDate.compareTo(timeSlot.getEndDate()) > 0){
    			vacationPass++;
    		}
    		else if(endDate.compareTo(timeSlot.getStartDate()) == 0){
    			if(endTime.compareTo(timeSlot.getStartTime()) <= 0){
        			vacationPass++;
    			}
    		}
    		else if(startDate.compareTo(timeSlot.getEndDate()) == 0){
    			if(startTime.compareTo(timeSlot.getEndTime()) >= 0){
        			vacationPass++;
    			}
    		}
    	}
    	
    	if(vacationPass == fbs.getBusiness().getVacation().size()){
    		vacationValid = true;
    	}
    	
    	int appointmentPass = 0;
    	
    	for(Appointment ap : fbs.getAppointments()){
    		if (ap != a) {
	    		if(endDate.compareTo(ap.getTimeSlot().getStartDate()) < 0){
	    			appointmentPass++;
	    		}
	    		else if(startDate.compareTo(ap.getTimeSlot().getEndDate()) > 0){
	    			appointmentPass++;
	    		}
	
	    		else if(startDate.compareTo(ap.getTimeSlot().getStartDate()) == 0){
	    			if(endTime.compareTo(ap.getTimeSlot().getStartTime()) <= 0 || startTime.compareTo(ap.getTimeSlot().getEndTime()) >= 0){
	    				appointmentPass++;
	    			}
	    				
	    		}
    		}
    		
    	}
		
    	if(appointmentPass == (fbs.getAppointments().size()-1)) {
    		appointmentValid = true;
    	}
    	
    	if(businessValid == true && holidayValid == true && vacationValid == true && appointmentValid == true){
    		isValid = true;
    	}
    	
    	return isValid;
  }

  // line 298 "../../../../../FlexiBookStateMachine.ump"
   private void rejectCancel(){
    
  }

  // line 302 "../../../../../FlexiBookStateMachine.ump"
   private boolean isOptionalService(boolean isOptionalServiceModification){
    return isOptionalServiceModification;
  }

  // line 307 "../../../../../FlexiBookStateMachine.ump"
   private boolean isDateTimeModification(boolean isDateAndTimeModification){
    return isDateAndTimeModification;
  }

  // line 311 "../../../../../FlexiBookStateMachine.ump"
   private boolean isOwnerAvailable(){
    //TODO rik
    	return true;
  }

  // line 317 "../../../../../FlexiBookStateMachine.ump"
   private void incrementCustomerNoShow(Customer c){
    c.setNoShow( c.getNoShow() +1);
  }

  // line 321 "../../../../../FlexiBookStateMachine.ump"
   private Appointment doModifyAppointment(Appointment a, FlexiBook fbs, Date currentDate, Time currentTime, boolean isDateAndTimeModification, boolean isOptionalServiceModification, boolean isNewServiceModification, Service s, Date newDate, Time newTime){
    //appointment for service and modify date and time
    	 isUpdateSucces = true;
	   if (isDateAndTimeModification && isNewServiceModification) {
		   BookableService b = a.getBookableService();
		   int duration = ((Service)b).getDuration();
		   Time endTime = new Time(newTime.getTime() + duration*60000);
		   
		   TimeSlot t = new TimeSlot(newDate, newTime, newDate, endTime, fbs);
		   a.setTimeSlot(t);
		   
		   
	   }
	   //appointment for combo and modify date and time
	   if (isDateAndTimeModification && isOptionalServiceModification) {
		   boolean areThereOptional = false;
		   int duration = 0;
		   
		   List<ComboItem> chosen = a.getChosenItems();
		   if (chosen.size() > 0) {
			   areThereOptional = true;
		   }
		   if (areThereOptional) {
			   for (ComboItem c : chosen) {
				   duration+= c.getService().getDuration();
			   }
		   }
		   ServiceCombo c = ((ServiceCombo)a.getBookableService());
		   List<ComboItem> current = c.getServices();
		  
		   for (ComboItem ci : current) {
			   if (ci.getMandatory() ==  true) {
				   duration+=ci.getService().getDuration();
			   }
			  
		   }
		   
		   Time endTime = new Time(newTime.getTime() + duration*60000);
		   TimeSlot t = new TimeSlot(newDate, newTime, newDate, endTime, fbs);
		   a.setTimeSlot(t);
		   
		   
	   }
	   //appointment for combo and modify optional service
	   if (isOptionalServiceModification && !isDateAndTimeModification) {
		   ServiceCombo c = ((ServiceCombo)a.getBookableService());
		   int duration = 0;
		   List<ComboItem> current = c.getServices();
		   String chosenName = s.getName();
		   ComboItem chosenItem = null;
		   for (ComboItem item : current) {
			   if (item.getService().getName().equals(chosenName)) {
				   chosenItem = item;
			   }
		   }
		   for (ComboItem ci : current) {
			   if (ci.getMandatory() ==  true) {
				   duration+=ci.getService().getDuration();
			   }
			  
		   }
		   
		   duration+=chosenItem.getService().getDuration();
		   
		   Time endTime = new Time(a.getTimeSlot().getStartTime().getTime() + duration*60000);
		   TimeSlot t = new TimeSlot(a.getTimeSlot().getStartDate(), a.getTimeSlot().getStartTime(), a.getTimeSlot().getEndDate(), endTime, fbs);
		   a.setTimeSlot(t);
		   
		   
	   }
	  //appointment for service and modify the service
	   if (isNewServiceModification) {
		   int duration =  0;
		   a.setBookableService(s);
		   duration += s.getDuration();
		   
		   Time endTime = new Time(a.getTimeSlot().getStartTime().getTime() + duration*60000);
		   TimeSlot t = new TimeSlot(a.getTimeSlot().getStartDate(),  a.getTimeSlot().getStartTime(), a.getTimeSlot().getStartDate(), endTime, fbs);
		   a.setTimeSlot(t);
		   
		   
	   }
	   
    	return a;
  }

  // line 407 "../../../../../FlexiBookStateMachine.ump"
   private void rejectModifyAppointment(){
    isUpdateSucces = false;
  }


  public String toString()
  {
    return super.toString() + "["+
            "isUpdateSucces" + ":" + getIsUpdateSucces()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "customer = "+(getCustomer()!=null?Integer.toHexString(System.identityHashCode(getCustomer())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "bookableService = "+(getBookableService()!=null?Integer.toHexString(System.identityHashCode(getBookableService())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "timeSlot = "+(getTimeSlot()!=null?Integer.toHexString(System.identityHashCode(getTimeSlot())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "flexiBook = "+(getFlexiBook()!=null?Integer.toHexString(System.identityHashCode(getFlexiBook())):"null");
  }  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 60 "../../../../../FlexiBookPersistence.ump"
  private static final long serialVersionUID = 386717977557499839L ;

  
}