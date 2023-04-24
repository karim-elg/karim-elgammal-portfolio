package ca.mcgill.ecse.flexibook.controller;

import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse.flexibook.application.FlexiBookApplication;
import ca.mcgill.ecse.flexibook.application.SystemTime;
import ca.mcgill.ecse.flexibook.persistence.FlexiBookPersistence;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
//import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;

import ca.mcgill.ecse.flexibook.controller.InvalidInputException;
import ca.mcgill.ecse.flexibook.application.FlexiBookApplication;
import ca.mcgill.ecse.flexibook.application.SystemTime;
import ca.mcgill.ecse.flexibook.model.*;
import ca.mcgill.ecse.flexibook.model.Appointment.Status;
import ca.mcgill.ecse.flexibook.controller.TOAppointment;

public class FlexiBookController {

	public FlexiBookController() {

	}

	/**
	 * This method define a new ServiceCombo in the system 
	 * @author Natalia TabetAgredo
	 * 
	 * @param aName           the name of the new ServiceCombo
	 * @param mainServiceName the name of the main service of the ServiceCombo
	 * @param servicesNames   the list of service names that will be added to the
	 *                        combo
	 * @param isMandatory     the list of mandatory statuses of the each service
	 * @param f               the flexibook object of the system
	 * @throws InvalidInputException
	 */
	public static void defineServiceCombo(String aName, String mainServiceName, List<String> servicesNames,
			List<String> isMandatory, FlexiBook f) throws InvalidInputException {
		String error = "";
		List<Service> services = new ArrayList<Service>();
		boolean isMainValid = true;
		ServiceCombo sc = FlexiBookController.createServiceCombo(aName, mainServiceName, f);
		if (servicesNames.size() < 2) {
			error = "A service Combo must contain at least 2 services";
		}
		for (String sName : servicesNames) {
			Service aService = findService(sName, f);
			if (aService == null) {
				error = "Service " + sName + " does not exist";
				break;
			}
		}
		if (servicesNames.contains(mainServiceName) == false) {
			error = "Main service must be included in the services";
			isMainValid = false;
		}
		if (findService(mainServiceName, f) == null) {
			error = "Service " + mainServiceName + " does not exist";
			isMainValid = false;
		}

		int index = servicesNames.indexOf(mainServiceName);
		if (isMainValid) {
			if (isMandatory.get(index).equals("false")) {
				error = "Main service must be mandatory";
			}
		}

		if (error.length() > 0) {
			sc.delete();
			throw new InvalidInputException(error.trim());
		}

		try {
			for (String name : servicesNames) {
				Service s = findService(name, f);
				services.add(s);
			}
			for (int i = 0; i < services.size(); ++i) {
				ComboItem cItem = new ComboItem(Boolean.parseBoolean(isMandatory.get(i)), services.get(i), sc);
				sc.addService(cItem);
			}

			ComboItem mainService = getItemFromCombo(mainServiceName, sc);
			sc.setMainService(mainService);
			
			//saving changes to file
			FlexiBookPersistence.save(FlexiBookApplication.getFlexiBook());
			
		} catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}

	}

	
	
	
	/**
	 * This method is a helper method that is called before trying to define a
	 * ServiceCombo
	 * 
	 * @author Natalia Tabet Agredo
	 * @param aName
	 * @param userName
	 * @param mainServiceName
	 * @param f
	 * @return
	 * @throws InvalidInputException
	 */
	private static ServiceCombo createServiceCombo(String aName, String mainServiceName, FlexiBook f)
			throws InvalidInputException {
		String error = "";
		if (findServiceCombo(aName, f) != null) {
			error = "Service combo " + aName + " already exists";
		}
		if (FlexiBookApplication.getCurrentUser().getUsername().equals(f.getOwner().getUsername()) == false) {
			error = "You are not authorized to perform this operation";
		}

		if (findService(mainServiceName, f) == null) {
			error = "Service " + mainServiceName + " does not exist";
		}

		if (error.length() > 0) {
			System.out.println("entering error with errror" + error);
			throw new InvalidInputException(error.trim());

		}

		try {
			ServiceCombo sc = new ServiceCombo(aName, f);
			
			//saving changes to file
			FlexiBookPersistence.save(FlexiBookApplication.getFlexiBook());
			
			return sc;
			
		} catch (RuntimeException e) {
			error = e.getMessage();
			if (error.equals(
					"Cannot create due to duplicate name. See http://manual.umple.org?RE003ViolationofUniqueness.html")) {
				error = "Service combo " + aName + " already exists";
			}
			throw new InvalidInputException(e.getMessage());
		}

	}

	/**
	 * Feature: Create Service Create a new Service
	 * 
	 * @author Ervin Cai
	 * @param String userName for the userName
	 * @param String name for the name of the service
	 * @param int    aDuration for the duration of service
	 * @param int    aDowntimeDuration for the duration of downtime
	 * @param int    aDowntimeStart for the start of downtime
	 * @param FlexiBook flexibook for the flexibook
	 * @throws InvalidInputException
	 */
	public static void addService(String userName, String name, int aDuration, int aDowntimeStart,
			int aDowntimeDuration, FlexiBook flexibook) throws InvalidInputException {
		String error = "";
		if (findService(name, flexibook) != null) {
			error = "Service " + name + " already exists";
		}
		if (userName.equals("owner") == false) {
			error = "You are not authorized to perform this operation. ";
		}
		if (name == null) {
			error = "A service must have a name. ";
		}
		if (aDuration == 0 || aDuration < 0) {
			error = "Duration must be positive";
		}
		if (aDowntimeStart > 0 && aDowntimeDuration <= 0) {
			error = "Downtime duration must be positive";
		}
		if (aDowntimeStart == 0 && aDowntimeDuration < 0) {
			error = "Downtime duration must be 0";
		}
		if (aDowntimeDuration == 0) {
			aDowntimeStart = 0;
		}
		if (aDowntimeDuration != 0 && aDowntimeDuration > 0) {
			if (aDowntimeStart < 0) {
				error = "Downtime must not start before the beginning of the service";
			} else if (aDowntimeStart == 0) {
				error = "Downtime must not start at the beginning of the service";
			} else if (aDowntimeStart > aDuration) {
				error = "Downtime must not start after the end of the service";
			} else if (aDowntimeStart + aDowntimeDuration > aDuration) {
				error = "Downtime must not end after the service";
			}
		}
		System.out.println(error);
		if (error.length() > 0) {
			throw new InvalidInputException(error.trim());
		}
		try {
			Service newService = new Service(name, flexibook, aDuration, aDowntimeDuration, aDowntimeStart);

			//saving changes to file
			FlexiBookPersistence.save(FlexiBookApplication.getFlexiBook());
			
		} catch (RuntimeException e) {
			error = e.getMessage();
			if (error.equals(
					"Cannot create due to duplicate name. See http://manual.umple.org?RE003ViolationofUniqueness.html")) {
				error = "Service" + name + "already exists";
			} else if (error.equals(
					"Unable to create bookableService due to flexiBook. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html")) {
				error = "Unable to add service due to flexibook.";
			}
			throw new InvalidInputException(error);
		}
	}

	/**
	 * This method updates a ServiceCombo
	 * 
	 * @author Natalia Tabet Agredo
	 * @param currentName     the current name of the ServiceCombo
	 * @param newName         the new name of the ServiceCombo
	 * @param newMainName     the name of the new main service of the ServiceCombo
	 * @param newServices     the list of the updated services
	 * @param newAreMandatory the list of the mandatory statuses for the new list of
	 *                        services
	 * @param f               the flexibook object of this application
	 * @throws InvalidInputException
	 */
	public static void updateServiceCombo(String currentName, String newName, String newMainName,
			List<String> newServices, List<String> newAreMandatory, FlexiBook f) throws InvalidInputException {
		ServiceCombo sc = findServiceCombo(currentName, f);
		String error = "";
		List<Service> newServicesArray = new ArrayList<Service>();

		if (findServiceCombo(newName, f) != null && (!currentName.equalsIgnoreCase(newName))) {
			error = "Service combo " + newName + " already exists";
		}

		boolean isMainValid = true;
		if (newServices.size() < 2) {
			error = "A service Combo must have at least 2 services";
		}
		if (newServices.contains(newMainName) == false) {
			error = "Main service must be included in the services";
			isMainValid = false;
		}
		if (findService(newMainName, f) == null) {
			error = "Service " + newMainName + " does not exist";
		}

		int index = newServices.indexOf(newMainName);
		if (isMainValid) {
			if (newAreMandatory.get(index).equals("false")) {
				error = "Main service must be mandatory";
			}
		}
		for (String sName : newServices) {
			Service aService = findService(sName, f);
			if (aService == null) {
				error = "Service " + sName + " does not exist";
				break;
			}
		}
		if (FlexiBookApplication.getCurrentUser().getUsername().equals(f.getOwner().getUsername()) == false) {
			error = "You are not authorized to perform this operation";
		}

		if (error.length() > 0) {
			throw new InvalidInputException(error.trim());
		}

		try {
			for (String name : newServices) {
				Service s = findService(name, f);
				newServicesArray.add(s);
			}
			for (int i = 0; i < sc.getServices().size(); ++i) {
				if (!newServicesArray.contains(sc.getService(i).getService())) {
					sc.getService(i).delete();
				}
			}

			ArrayList<Service> comboServices = new ArrayList<Service>();
			ServiceCombo c = sc;

			for (ComboItem ci : c.getServices()) {
				Service aService = ci.getService();
				comboServices.add(aService);
			}
			for (int i = 0; i < newServicesArray.size(); ++i) {
				if (!comboServices.contains(newServicesArray.get(i))) {
					ComboItem cItem = new ComboItem(Boolean.parseBoolean(newAreMandatory.get(i)),
							newServicesArray.get(i), sc);
					sc.addService(cItem);

				}

			}

			// update mandatory status
			for (int i = 0; i < sc.getServices().size(); ++i) {
				sc.getService(i).setMandatory(Boolean.parseBoolean(newAreMandatory.get(i)));
			}
			sc.setName(newName);
			ComboItem mainService = getItemFromCombo(newMainName, sc);
			sc.setMainService(mainService);

			//saving changes to file
			FlexiBookPersistence.save(FlexiBookApplication.getFlexiBook());
			
		} catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}

	}

	/**
	 * Feature: Update Service Update an existing service
	 * 
	 * @author Ervin Cai
	 * @param String userName for the userName
	 * @param String name for the name of the service
	 * @param int    aDuration for the duration of service
	 * @param int    aDowntimeDuration for the duration of downtime
	 * @param int    aDowntimeStart for the start of downtime
	 * @throws InvalidInputException
	 */
	public static void updateService(String userName, String s, String name, int aDuration, int aDowntimeStart,
			int aDowntimeDuration, FlexiBook flexibook) throws InvalidInputException {
		
		Service service = findService(s, flexibook);
		
		String error = "";
		if (findService(name, flexibook) != null && findService(name, flexibook) != service) {
			error = "Service " + name + " already exists";
		}
		if (userName.equals("owner") == false) {
			error = "You are not authorized to perform this operation. ";
		}
		if (name == null) {
			error = "A service must have a name. ";
		}
		if (aDuration == 0 || aDuration < 0) {
			error = "Duration must be positive";
		}
		if (aDowntimeStart > 0 && aDowntimeDuration <= 0) {
			error = "Downtime duration must be positive";
		}
		if (aDowntimeStart == 0 && aDowntimeDuration < 0) {
			error = "Downtime duration must be 0";
		}
		if (aDowntimeDuration == 0) {
			aDowntimeStart = 0;
		}
		if (aDowntimeDuration != 0 && aDowntimeDuration > 0) {
			if (aDowntimeStart < 0) {
				error = "Downtime must not start before the beginning of the service";
			} else if (aDowntimeStart == 0) {
				error = "Downtime must not start at the beginning of the service";
			} else if (aDowntimeStart > aDuration) {
				error = "Downtime must not start after the end of the service";
			} else if (aDowntimeStart + aDowntimeDuration > aDuration) {
				error = "Downtime must not end after the service";
			}
		}
		if (service.numberOfAppointments() > 0) {
			error = "Appointments for this service exist.";
		}
		if (error.length() > 0) {
			throw new InvalidInputException(error.trim());
		}
		try {
			service.setName(name);
			service.setDuration(aDuration);
			service.setDowntimeDuration(aDowntimeDuration);
			service.setDowntimeStart(aDowntimeStart);

			//saving changes to file
			FlexiBookPersistence.save(FlexiBookApplication.getFlexiBook());
			
		} catch (RuntimeException e) {
			error = e.getMessage();
			if (error.equals(
					"Cannot create due to duplicate name. See http://manual.umple.org?RE003ViolationofUniqueness.html")) {
				error = "Service" + name + "already exists";
			}
			if (error.equals(
					"Unable to create bookableService due to flexiBook. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html")) {
				error = "Unable to add service due to flexibook.";
			}
			throw new InvalidInputException(error);
		}
	}

	/**
	 * Feature: Delete Service
	 * Delete existing service
	 * 
	 * @author Ervin Cai
	 * @param String userName for the userName
	 * @param Service service to be deleted
	 * @param FlexiBook flexibook for the flexibook
	 * @throws InvalidInputException
	 */
	public static void deleteService(String userName, String ser, FlexiBook flexibook) throws InvalidInputException {
		String error = "";
		Service service = findService(ser,flexibook);
		boolean hasFutureAppointments = false;
		List<Appointment> appointments = service.getAppointments();
		List<ServiceCombo> serviceCombos = new ArrayList<ServiceCombo> ();
		Date today = SystemTime.getDate();
		Time currentTime = SystemTime.getTime();
		for (Appointment a : appointments) {
			if (a.getTimeSlot().getStartDate().after(today)) {
				error = "The service contains future appointments";
			}
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String justDate = format.format(a.getTimeSlot().getStartDate());
			String todayFormatted = format.format(today);
			if(justDate.equals(todayFormatted) && a.getTimeSlot().getStartTime().after(currentTime)) {
				error = "The service contains future appointments";
			}
		}
		if(userName.equals("owner")== false) {
			error = "You are not authorized to perform this operation";
		}
		if(error.length() > 0) {
			System.out.println("entering error with errror" + error);
			throw new InvalidInputException(error.trim());
		}
		try {	
			service.delete(); 
			for(ServiceCombo s: getCurrentCombos(flexibook)) {
				if (s.getMainService().getService() == service) {
					deleteServiceCombo(s.getName(),flexibook);
				}
				else {
					for(ComboItem ci : s.getServices()) {
						if(ci.getService() == service) {
							s.removeService(ci);
						}
					}
				}
			}

			//saving changes to file
			FlexiBookPersistence.save(FlexiBookApplication.getFlexiBook());
			
		}
		catch (RuntimeException e){
			throw new InvalidInputException(error);
		}
	}
	/**
	 * This method deletes a ServiceCombo
	 * 
	 * @author Natalia Tabet Agredo
	 * @param comboName the name of the ServiceCombo that wants to be deleted
	 * @param f   the flexibook object of the system
	 * @throws InvalidInputException
	 */
	public static void deleteServiceCombo(String comboName, FlexiBook f) throws InvalidInputException {
		ServiceCombo combo = findServiceCombo(comboName, f);
		String error = "";
		List<Appointment> appointments = combo.getAppointments();
		Date today = getSystemDate();
		Time currentTime = getSystemTime();
		for (Appointment a : appointments) {
			if (a.getTimeSlot().getStartDate().after(today)) {
				error = "Service combo " + combo.getName() + " has future appointments";

			}
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String justDate = format.format(a.getTimeSlot().getStartDate());
			String todayFormatted = format.format(today);
			if (justDate.equals(todayFormatted) && a.getTimeSlot().getStartTime().after(currentTime)) {
				error = "Service combo " + combo.getName() + " has future appointments";
			}

		}
		if (!FlexiBookApplication.getCurrentUser().getUsername().equals(f.getOwner().getUsername())) {
			error = "You are not authorized to perform this operation";
		}
		if (error.length() > 0) {
			System.out.println("entering error with errror" + error);
			throw new InvalidInputException(error.trim());
		}

		try {
			for (int i = 0; i < appointments.size(); ++i) {
				Appointment a = appointments.get(i);
				a.delete();
			}
			combo.delete();

			//saving changes to file
			FlexiBookPersistence.save(FlexiBookApplication.getFlexiBook());
			
		} catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}

	}
	//====================================================================================================================
	
	
	
/**
 * @author rakshitharavi	
 * @param username
 * @param mainServiceName
 * @param optionalServiceNames
 * @param startTime
 * @param startDate
 * @throws InvalidInputException
 */
public static void makeAppointment(String username, String mService, List<String> optionalServiceNames, Time sTime, Date sDate) throws InvalidInputException {
		
		try {
			FlexiBook flexiBook = FlexiBookApplication.getFlexiBook();
			Appointment appointment = null;
			Customer customer = null;
			TimeSlot timeSlot = null;
			Time previousServiceEndTime = null;
			BookableService thisService = null;
			List<BookableService> serviceList = flexiBook.getBookableServices();
			
			if(mService == null || username == null || sTime == null || sDate == null) {
				throw new InvalidInputException("Service name, Customer username, start time or start date cannot be null");
			}
			
			Owner owner = flexiBook.getOwner();
			if(username.equals(owner.getUsername())) {
				throw new InvalidInputException("Owner cant make an appointment");
			}
			

			thisService = findServiceByName(mService);
			
			customer = findCustomerByName(username);
			
			
			if(thisService.getClass().equals(Service.class)){   
				
				Service mainService = (Service) thisService;
				timeSlot = getTimeSlot(sTime, sDate, mainService);
				if(customer != null && mainService != null) {
					appointment = new Appointment(customer, mainService, timeSlot, flexiBook);
				}
				
			}
			else {			//the bookableService is a ServiceCombo
				
				ServiceCombo combo = (ServiceCombo) thisService;
				
				timeSlot = getTimeSlot(sTime, sDate, combo.getMainService().getService());
				appointment = new Appointment(customer, combo, timeSlot, flexiBook);
				ComboItem mainComboItem = new ComboItem(true, (Service)thisService, combo);
				appointment.addChosenItem(mainComboItem);
				
				//check if there are optional services
				if(optionalServiceNames != null) {
					Service optionalService;
					for(int i = 0; i < serviceList.size(); i++) {
						if(optionalServiceNames.contains(serviceList.get(i).getName())) {
							optionalService = (Service) serviceList.get(i);
							previousServiceEndTime = timeSlot.getEndTime();
							timeSlot = getTimeSlot(previousServiceEndTime, sDate, optionalService);
							ComboItem optionalComboItem = new ComboItem(false, optionalService, combo);
							appointment.addChosenItem(optionalComboItem);		
							break;
						}
					}
				}
				
				appointment.getTimeSlot().setEndTime(timeSlot.getEndTime());
				
			}
			
			//saving changes to file
			FlexiBookPersistence.save(FlexiBookApplication.getFlexiBook());
			
			if(checkDateAndTime(appointment) == false) {
				throw new InvalidInputException(" No  slots available for " + mService +  " at " + sTime.toString()+" on " + sDate.toString()  + ".");
			}
			else {
				flexiBook.addAppointment(appointment);
			}
			
			
		}
		catch(RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
		
		
	}

	
	/**
	 * 
	 * @author Ervin
	 * @throws InvalidInputException
	 *
	 */

	 
public static Appointment makeAppointment(String username, String mainServiceName, List<String> optionalServiceNames, Date startDate, Time startTime, FlexiBook fbs) throws InvalidInputException {
		//Status status = Status.Issued;
	Owner owner = fbs.getOwner();
	if(username.equals(owner.getUsername())) {
		throw new InvalidInputException("An owner cannot make an appointment");
	}
	
	try {
			BookableService b = findServiceByName(mainServiceName);
			int duration = 0;
			if (b instanceof Service) {
				duration = ((Service)b).getDuration();
			}
			else if (b instanceof ServiceCombo){
				List<ComboItem> comboItems = ((ServiceCombo) b).getServices();
				for (ComboItem ci: comboItems) {
					if (ci.getMandatory() == true) {
						duration = duration + ci.getService().getDuration();
					}
					
				}
			}
			//Time endTime = new Time(startTime.getTime() + (duration*1000));
			
			
			LocalTime localtime = startTime.toLocalTime();
			LocalTime localtime2 = localtime.plusMinutes(duration);
			Time aEndTime = Time.valueOf(localtime2);
			LocalDateTime dt = LocalDateTime.of(startDate.toLocalDate(), startTime.toLocalTime());
			LocalDateTime dtNew = dt.plusMinutes(duration);
			LocalDate localEndDate = dtNew.toLocalDate();
			Date aEndDate = Date.valueOf(localEndDate);
		
			//Date aEndDate = startDate;		
			TimeSlot t = new TimeSlot(startDate, startTime, aEndDate, aEndTime, fbs);
			Customer customer = findCustomerByName(username);
			Appointment a = null;
			if(b instanceof Service) {
				a = new Appointment(customer, (Service) b , t, fbs);
			}
			else if (b instanceof ServiceCombo) {
				a = new Appointment(customer, (ServiceCombo) b , t, fbs);
			}
			
			if (optionalServiceNames != null && b instanceof ServiceCombo) {
				for (String names: optionalServiceNames) {
					//ServiceCombo sc = findServiceCombo(mainServiceName, fbs);
					for (ComboItem ci: getComboItems(mainServiceName, fbs)) {
						if (names == ci.getService().getName()) {
							a.addChosenItem(ci);
						}
					}
				}
			}
		FlexiBookPersistence.save(FlexiBookApplication.getFlexiBook());
		return a;
			
		}
		catch(RuntimeException e){
			throw new InvalidInputException(e.getMessage());
		}
	}


	



	
//updateappointment

/**
 * @author rakshitharavi
 * @param username
 * @param serviceName
 * @param newItems
 * @param removedItems
 * @param newStartTime
 * @param newDate
 * @param oldStartTime
 * @param oldDate
 * @return
 * @throws InvalidInputException
 */


public static String updateAppointment(String username, String sName, List<String> nItems, List<String> dItems, Time nsTime, Date nDate, Time osTime, Date oDate) throws InvalidInputException {

	FlexiBook fbs = FlexiBookApplication.getFlexiBook();
	ServiceCombo sc = null;
	Customer c = null;
	Appointment appointment = null;
	
	BookableService thisService = null;
	List<BookableService> serviceList = fbs.getBookableServices();
	
	try {
		Owner owner = fbs.getOwner();
		
		
		if(sName == null || username == null || osTime == null || oDate == null) {
			throw new InvalidInputException("Service name, Customer username, old start time or old start date cannot be empty");
		}
		
		if(username.equals(owner.getUsername())) {
			throw new InvalidInputException("The owner cannot update  customer's appointment");
		}
		
		thisService = findServiceByName(sName);
		
		c = findCustomerByName(username);
	
		
		List<Appointment> appointmentList = fbs.getAppointments();
		for (int i = 0; i < appointmentList.size(); i++) {
			Appointment thisAppointment = appointmentList.get(i);
			if(oDate.equals(thisAppointment.getTimeSlot().getStartDate()) && osTime.equals(thisAppointment.getTimeSlot().getStartTime()) && sName.equals(thisAppointment.getBookableService().getName())) {
				appointment = thisAppointment;
				break;
			}
		}
		
		if(!appointment.getCustomer().getUsername().equals(username)) {
			throw new InvalidInputException("A customer can only update their own appointments");
		}
			
		
		
		
		if((nItems != null) || (dItems != null)) {		//deleted items 
			sc = (ServiceCombo) thisService;
			List<ComboItem> itemList = appointment.getChosenItems();
			
			if(nItems != null) {
				
				//add
				Service nService;
				for(int i = 0; i < serviceList.size(); i++) {
					nService = (Service) serviceList.get(i);
					if(nItems.contains(nService.getName())) {
						ComboItem nCombo = new ComboItem(false, nService, sc);
						appointment.addChosenItem(nCombo);
					}
				}
			}
			else if(dItems != null) {
				
				for(int i = 0; i < dItems.size(); i++) {		
					for(int j = 0; j < itemList.size(); j++) {		
						ComboItem cItem = itemList.get(j);
						if(cItem.getService().getName().equals(dItems.get(i))) {
							if(cItem.getMandatory() == false) {		
								appointment.removeChosenItem(cItem);		
							}	
						}
					}
				}
			}
			
			return "Success";
			
		}
		
		
		
				if(nsTime != null && nDate != null) {
			fbs.removeTimeSlot(appointment.getTimeSlot());
			
			TimeSlot newts = null;
			
			if(thisService.getClass().equals(Service.class)) {		//Service
				
				if(nDate != null && nsTime != null) {	
					newts = getTimeSlot(nsTime, nDate, (Service) thisService);
					appointment.setTimeSlot(newts);
				}
				
			}
			else {			//ServiceCombo
				
				Time preveTime;
				
				if(nDate != null && nsTime != null) {
					newts = getTimeSlot(nsTime, nDate, sc.getMainService().getService());
				}	
				
				//check if there are optional services
				List<ComboItem> comboItemList = sc.getServices();
				if(comboItemList != null) {
					Service oService;
					for(int i = 0; i < comboItemList.size(); i++) {
						oService = comboItemList.get(i).getService();
						preveTime = newts.getEndTime();
						newts = getTimeSlot(preveTime, nDate, oService);		
					}
				}
					
					appointment.getTimeSlot().setEndTime(newts.getEndTime());
			}
			
			
			if(checkDateAndTime(appointment) == false) {
				return "Fail";
			}
			else {
				appointment.setTimeSlot(newts);
				return "Success";
			}
			
		}
		
		return "Fail";
	}
	catch(RuntimeException e) {
		throw new InvalidInputException(e.getMessage());
	}
	
	
}
	
	

/**
 * @author rakshitharavi
 * @param username
 * @param startTime
 * @param startDate
 * @param todaysDate
 * @param flexiBook
 * @throws InvalidInputException
 */
public static void cancelAppointment(String u, Time t, String d, Date pd, FlexiBook fbs) throws InvalidInputException {
    
    Appointment appointment = null;
    Date appointmentDate = Date.valueOf(d);
   Time appointmentTime = Time.valueOf(t+":00");
    
    try {
      
      if(appointmentDate.equals(pd)) {
        throw new InvalidInputException("Cannot cancel an appointment on the appointment date");
      }
      
      
      
      
      if(u == null || t == null || d == null) {
        throw new InvalidInputException("Customer username, start time or start date cannot be null");
      }
      
      Owner owner = fbs.getOwner();
      if(u.equals(owner.getUsername())) {
        throw new InvalidInputException("An owner cannot cancel an appointment");
      }
      
      List<Appointment> appointmentList = fbs.getAppointments();
      for (int i = 0; i < appointmentList.size(); i++) {
        Appointment currentAppointment = appointmentList.get(i);
        String n = currentAppointment.getCustomer().getUsername();
        String s = currentAppointment.getBookableService().getName();
        if(appointmentDate.equals(currentAppointment.getTimeSlot().getStartDate()) && (appointmentTime.equals(currentAppointment.getTimeSlot().getStartTime()))) {
          appointment = currentAppointment;
        }
      }
      
      if(u.equals(appointment.getCustomer().getUsername())) {
        appointment.cancelAppointment(appointment, fbs, pd, appointmentTime);
        FlexiBookPersistence.save(fbs);
      }
      else{
        throw new InvalidInputException("You cannot cancel someone else's  appointments");
      }

      
      //check throw
      
    }
    catch(RuntimeException e) {
      throw new InvalidInputException(e.getMessage());
    }
    
  }


/**
 * @author rakshitharavi
 * @param startTime
 * @param startDate
 * @param service
 * @return
 */

private static TimeSlot getTimeSlot(Time sTime, Date sDate, Service s) {
	
	FlexiBook flexiBook = FlexiBookApplication.getFlexiBook();
	Time endTime = null;
	
	
	//CALCULATE END_TIME
	endTime = new Time(((sTime.getTime() / (1000 * 60)) + s.getDuration()) * 1000 * 60);
	TimeSlot timeSlot1 = new TimeSlot(sDate, sTime, sDate, endTime, flexiBook);
	return timeSlot1;
	
}



/**
*
* @author Natalia
* @throws InvalidInputException
*
*/
public static boolean updateAppointment(Appointment a, FlexiBook fbs, Date currentDate, Time currentTime, boolean isDateAndTimeModification, boolean isOptionalServiceModification, boolean isNewServiceModification, Service s, Date newDate, Time newTime) throws InvalidInputException {

	boolean success = a.modifyAppointment(a, fbs, currentDate, currentTime, isDateAndTimeModification, isOptionalServiceModification, isNewServiceModification, s, newDate, newTime);
	if (a.getIsUpdateSucces() == false) {
		throw new InvalidInputException("could not do update");
	}
	FlexiBookPersistence.save(FlexiBookApplication.getFlexiBook());
	return success;
}

public static String getStatusOfApptDto(TOAppointment aTO) {
	
	Appointment a = getAppointmentFromDto(aTO);
	if (a.getStatus() == Status.Issued) {
		return "Issued";
	}
	else if (a.getStatus() == Status.InProgress) {
		return "In progress";
	}
	else {
		return "---";
	}
}

/**
 * @author Natalia 
 */
public static boolean updateAppointmentFromView(TOAppointment aTO, boolean isDateAndTimeModification, boolean isOptionalServiceModification, boolean isNewServiceModification, String sTO , Date newDate, Time newTime) throws InvalidInputException {
	Date nowDate = getSystemDate();
	String dateAsString = nowDate.toString();
	Date nowToCompare = null;
	try {
		nowToCompare = fromStringToDate(nowDate.toString());
	} catch (ParseException e) {
		e.printStackTrace();
	}
	Time nowTime = getSystemTime();
	String timeAsString = nowTime.toString();
	timeAsString = timeAsString.substring(0, 5);
	Time timeToCompare = fromStringToTime(timeAsString);
	
	FlexiBook fbs = FlexiBookApplication.getFlexiBook();
	Appointment a = getAppointmentFromDto(aTO);
	Service s = findService(sTO, fbs);
	return updateAppointment(a, fbs, nowToCompare, timeToCompare, isDateAndTimeModification, false, isNewServiceModification, s, newDate, newTime);
}

public static List<TOAppointment> showAppointmentsToUpdate() throws ParseException{
	ArrayList<TOAppointment> appointmentsToUpdate = new ArrayList<TOAppointment>();

	
	for (Appointment a : FlexiBookApplication.getFlexiBook().getAppointments()) {
		if (a.getStatus() == Status.Issued) {
			System.out.println(a.getTimeSlot().getStartTime());
			TOCustomer aCustomer = new TOCustomer(a.getCustomer().getUsername(), a.getCustomer().getPassword());
			TOBookableService aBookableService = null;
			if (a.getBookableService() instanceof Service) {
				Service s = (Service)a.getBookableService();
				TOService service = new TOService(s.getName(), s.getDuration(), s.getDowntimeDuration(), s.getDowntimeStart());
				aBookableService = service;
			}
			
			
			TimeSlot ts = a.getTimeSlot();
			TOTimeSlot apptSlot = new TOTimeSlot(ts.getStartDate(), ts.getStartTime(), ts.getEndDate(), ts.getEndTime());
			TOAppointment appToStart = new TOAppointment(aCustomer, aBookableService, apptSlot);
			appointmentsToUpdate.add(appToStart);
		
		}
			
	}
	
	return appointmentsToUpdate;
	
}
	

	/**
	 * @author Rakshitha
	 * @param appointment
	 * @return  valid time slot  selection
	 */
	private static boolean checkDateAndTime(Appointment appointment) {

		FlexiBook fbs = FlexiBookApplication.getFlexiBook();
		TimeSlot ts = appointment.getTimeSlot();
		
		
		List<Appointment> existingAppointments = fbs.getAppointments();

		long startTime = ts.getStartTime().getTime();
		long endTime = ts.getEndTime().getTime();


		// slot not in holidays
		if(fbs.getBusiness().getHolidays().contains(ts) || fbs.getBusiness().getVacation().contains(ts)) {
			return false;
		}

		//slot in business hours

		for(int m = 0; m < fbs.getBusiness().getBusinessHours().size(); m++) {
			BusinessHour bh = fbs.getBusiness().getBusinessHours().get(m);
			long start = bh.getStartTime().getTime();
			long end = bh.getStartTime().getTime();
			if((startTime >= start && endTime <= end)) {
				return true;
			}
		}

		//if appointment within downtime of another
		Appointment thisAppointment;
		Service thisService;
		boolean valid = true;
		long stime;

		for(int i = 0; i < existingAppointments.size(); i++) {
			thisAppointment = existingAppointments.get(i);
			stime = thisAppointment.getTimeSlot().getStartTime().getTime();  		//in ms

			if(ts.getStartTime().getTime() > stime && ts.getEndTime().getTime() < thisAppointment.getTimeSlot().getEndTime().getTime()) {		//if overlap with another appointment

				if(appointment.getBookableService().getClass().equals(Service.class)) {			//the bookableService is a Service
					thisService = (Service)thisAppointment.getBookableService();
					if(ts.getStartTime().getTime() > (stime + (thisService.getDowntimeStart()*60000)) && ts.getEndTime().getTime() < (stime + (thisService.getDowntimeStart() + thisService.getDowntimeDuration())*60000)){
						return true;
					}
					else {
						return false;
					}
				}
				else {				//the bookableService is a ServiceCombo

					for(int j = 0; j < thisAppointment.getChosenItems().size(); j++) {
						thisService = thisAppointment.getChosenItems().get(i).getService();
						if(ts.getStartTime().getTime() > (stime + (thisService.getDowntimeStart()*60000)) && ts.getEndTime().getTime() < (stime + (thisService.getDowntimeStart() + thisService.getDowntimeDuration())*60000 )){
							return true;		//appointment during the downtime of a service
						}
						else {
							stime += thisService.getDuration() * 60000;
							valid = false;		//appointment overlapping with a service
						}
					}

				}

			}

		}

		return valid;
	}

//	/**
//	 * This method gets the User through username and password
//	 * 
//	 * @author Mihir Binay Kumar
//	 * @param username
//	 * @param password
//	 * @param fbs
//	 * @return User
//	 * @throws InvalidInputException
//	 */
//		public static User getUser(String username, String password,FlexiBook fbs) throws InvalidInputException {
//
//			String error = "";
//			User u = User.getWithUsername(username);
//			if (u == null) {
//				
//				if(username.equals("owner") && password.equals("owner")) {
//					
//				u=AddOwner(username, password,fbs);
//				
//				return u;
//					
//				}
//				
//				error = "Username/password not found";
//				throw new InvalidInputException(error.trim());
//			} 
//			else {
//
//				String pass = u.getPassword();
//				if (pass.equals(password)) {
//
//					//FlexiBookApplication.setCurrentUser(u);
//					return u;
//
//				}
//
//				u = null;
//				error = "Username/password not found";
//				throw new InvalidInputException(error.trim());
//
//			}
//
//				
//		}
	
	/**
	 * This method gets the User through username and password
	 * 
	 * @author Mihir Binay Kumar
	 * @param username
	 * @param password
	 * @param fbs
	 * @return User
	 * @throws InvalidInputException
	 */
		public static User getUser(String username, String password,FlexiBook fbs) throws InvalidInputException {

			String error = "";
			User u = User.getWithUsername(username);
			if (u == null) {
				
				if(username.equals("owner") && password.equals("owner")) {
					
				u=AddOwner(username, password,fbs);
				
				return u;
					
				}
				
				error = "Username/password not found";
				throw new InvalidInputException(error.trim());
			} 
			else {

				String pass = u.getPassword();
				if (pass.equals(password)) {

					//FlexiBookApplication.setCurrentUser(u);
					return u;

				}

				u = null;
				error = "Username/password not found";
				throw new InvalidInputException(error.trim());

			}

			
		}
//		
//
//		/**
//		 * This method adds an owner account in the system
//		 * 
//		 * @author Mihir Binay Kumar
//		 * @param username
//		 * @param password
//		 * @param f
//		 * @return
//		 * @throws InvalidInputException
//		 */
//
//		public static User AddOwner(String username, String password, FlexiBook f) throws InvalidInputException {
//
//			try {
//				Owner o = new Owner(username, password, f);
//				f.setOwner(o);
//				//saving changes to file
//				FlexiBookPersistence.save(FlexiBookApplication.getFlexiBook());
//				
//				return o;
//				
//			}
//			catch(RuntimeException e) {
//				throw new InvalidInputException(e.getMessage());
//			}
//
//		}
		
//		/**
//		 * @author Mihir Binay Kumar
//		 * @param username
//		 * @param password
//		 * @param fbs
//		 * @throws InvalidInputException
//		 */
//
//		public static void LogIn(String username, String password, FlexiBook fbs) throws InvalidInputException {
//
//			//FlexiBook fb = FlexiBookApplication.getFlexiBook();
//			User u=getUser(username, password, fbs);
//			FlexiBookApplication.setCurrentUser(u);
//
//		}
//		/**
//	     * @author Mihir Binay Kumar
//	     * @throws Exception
//	     */
//
//		public static void LogOut() throws Exception {
//
//			String error = "";
//			
//			//FlexiBook fbs = FlexiBookApplication.getFlexiBook();
//
//			if (FlexiBookApplication.getCurrentUser()== null ) {
//
//				error = "The user is already logged out";
//				throw new Exception(error.trim());
//
//			// TODO Auto-generated method stub
//			
//		}
//			 FlexiBookApplication.setCurrentUser(null);
//		
//	}
		
		public static User AddOwner(String username, String password, FlexiBook f) throws InvalidInputException {

			try {
				Owner o = new Owner(username, password, f);
				f.setOwner(o);
				//saving changes to file
				FlexiBookPersistence.save(FlexiBookApplication.getFlexiBook());
				
				return o;
				
			}
			catch(RuntimeException e) {
				throw new InvalidInputException(e.getMessage());
			}

		}
		
		/**
		 * @author Natalia
		 * @param username
		 * @return
		 */
		public static boolean isUserLoggedIn(String username) {
			
			if(FlexiBookApplication.getCurrentUser() == null) {
				return false;
			}
			else if (FlexiBookApplication.getCurrentUser().getUsername().equals(username)) {
				return true;
			}
			else {
				return false;
			}
		}
		
		/**
		 * @author Mihir Binay Kumar
		 * @param username
		 * @param password
		 * @param fbs
		 * @throws InvalidInputException
		 */

		public static void LogIn(String username, String password) throws InvalidInputException {

			FlexiBook fb = FlexiBookApplication.getFlexiBook();
			User u=getUser(username, password, fb);
			FlexiBookApplication.setCurrentUser(u);
			FlexiBookPersistence.save(FlexiBookApplication.getFlexiBook());

		}
		/**
	     * @author Mihir Binay Kumar
	     * @throws Exception
	     */

		public static void LogOut() throws Exception {

			String error = "";
			
			//FlexiBook fbs = FlexiBookApplication.getFlexiBook();

			if (FlexiBookApplication.getCurrentUser()==(null)  ) {

				error = "The user is already logged out";
				throw new Exception(error.trim());

		
			
			}
			 FlexiBookApplication.setCurrentUser(null);
			 FlexiBookPersistence.save(FlexiBookApplication.getFlexiBook());
		}

		/**
		 * This method checks if the date is valid or not
		 * @author Mihir Kumar
		 * @param date
		 * @return
		 * @throws InvalidInputException
		 */
		
		public static boolean dateValidation(String date) throws InvalidInputException {

			String[] a = date.split("-");
			
			int year = Integer.parseInt(a[0]);
			int m = Integer.parseInt(a[1]);
			int d = Integer.parseInt(a[2]);
			String ss = date + " is not a valid date";
			if (m == 2 && d > 28) {
				return false;
			} else if (m > 12 || d > 31) {
				return false;
			}
			return true;
		}
		
		/**
		 * This controller method gets the available time slots of that specific date
		 * 
		 * @author Mihir Kumar
		 * @param ATSdate
		 * @return List<TOTimeSlot>
		 * @throws InvalidInputException
		 */
		
		public static List<TOTimeSlot> getDailyAvailableTimeSlots(String ATSdate) throws InvalidInputException { 
			
			if(!dateValidation(ATSdate)){
				throw new InvalidInputException(ATSdate + " is not a valid date");
			}
			
			FlexiBook fbs = FlexiBookApplication.getFlexiBook();
			List<Appointment> appoint = fbs.getAppointments();
			//appoint.sort(ADate);
			Date obj=null;
			Time startTime=null;
			Time endTime =null;
			List<TOTimeSlot> available= new ArrayList<TOTimeSlot>();;
			try {
				 obj = fromStringToDateM(ATSdate);
				
				
			} catch (ParseException e) {
				
				e.printStackTrace();
			}
			String day = getDayStringOld(obj,Locale.CANADA);
			 BusinessHour.DayOfWeek dow = toDayOfWeek(day);
			 List<BusinessHour> bhrs = FlexiBookApplication.getFlexiBook().getBusiness().getBusinessHours();
			 for(BusinessHour bg: bhrs) {
				 
				if( bg.getDayOfWeek().compareTo(dow)==0) {
					
					 startTime=bg.getStartTime();
					 endTime=bg.getEndTime();
				
				}
				
			 }
			 	SimpleDateFormat sdf5 = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
			 	SimpleDateFormat sdf6 = new SimpleDateFormat("yyyy-dd-MM");
				
		        String dateInString1 = sdf6.format(obj);
		        String dateInString2 = sdf6.format(obj);
		        Date StartDate=null;
		        Date EndDate=null;
				StartDate = Date.valueOf(dateInString1);
				EndDate = Date.valueOf(dateInString2);
		        Calendar calendar1 = Calendar.getInstance();
		        calendar1.setTime(StartDate);
		        Calendar calendar2= Calendar.getInstance();
		        calendar2.setTime(EndDate);
		
			 
			for(Appointment a:appoint) {
				
			
						
				
				Time appointmentStartTime=null;
				Time appointmentEndTime=null;
				String dateInString3 =  a.getTimeSlot().getStartTime().toString();
		        String dateInString4 = a.getTimeSlot().getEndTime().toString();
				
		        appointmentStartTime = Time.valueOf(dateInString3);
				appointmentEndTime = Time.valueOf(dateInString4);
		        
		        Calendar calendar3 = Calendar.getInstance();
		        calendar3.setTime(appointmentStartTime);
		        Calendar calendar4= Calendar.getInstance();
		        calendar4.setTime(appointmentEndTime);
		        
		        
		        
				if(a.getTimeSlot().getStartDate().before(StartDate)) {
					continue;
					
					
				}
				
				else if(a.getTimeSlot().getStartDate().after(EndDate)){
					
					continue;
					
				}
				
				else {
					
					Time stopTime=a.getTimeSlot().getStartTime();
					
					if(a.getBookableService().getName().equals("color-basic")) {
						
						int downtimeDur= ((Service) a.getBookableService()).getDowntimeDuration();
						int downtimeStr= ((Service) a.getBookableService()).getDowntimeStart();
						
						 startTime = updateTime(startTime,downtimeStr);
						 stopTime = updateTime(startTime,downtimeDur);
						
						
					}
					
					 if(a.getTimeSlot().getStartTime().after(startTime)) {
					
					TOTimeSlot ts1 = new TOTimeSlot(javaDateTosqldate(StartDate), startTime, javaDateTosqldate(a.getTimeSlot().getEndDate()) , stopTime);
					available.add(ts1);
					startTime=appointmentEndTime;
					
					}
					
					else {
						startTime=appointmentEndTime;
						
					}
				}
				}
			FlexiBookPersistence.save(FlexiBookApplication.getFlexiBook());
			return available;
			
		}
		
		/**
		 * This method gets the unavailable time slot of a specific date
		 * 
		 * @author Mihir Kumar
		 * @param ATSdate
		 * @return
		 * @throws InvalidInputException
		 */
		
public static List<TOTimeSlot> getDailyUnavailableTimeSlots(String ATSdate) throws InvalidInputException { 
			
			if(!dateValidation(ATSdate)){
				throw new InvalidInputException(ATSdate + " is not a valid date");
			}
			
			FlexiBook fbs = FlexiBookApplication.getFlexiBook();
			List<Appointment> appoint = fbs.getAppointments();
			//appoint.sort(ADate);
			Date obj=null;
			Time startTime=null;
			Time endTime =null;
			List<TOTimeSlot> available= new ArrayList<TOTimeSlot>();;
			try {
				 obj = fromStringToDateM(ATSdate);
				
				
			} catch (ParseException e) {
				
				e.printStackTrace();
			}
			String day = getDayStringOld(obj,Locale.CANADA);
			 BusinessHour.DayOfWeek dow = toDayOfWeek(day);
			 List<BusinessHour> bhrs = FlexiBookApplication.getFlexiBook().getBusiness().getBusinessHours();
			 for(BusinessHour bg: bhrs) {
				 
				if( bg.getDayOfWeek().compareTo(dow)==0) {
					
					 startTime=bg.getStartTime();
					 endTime=bg.getEndTime();
				
				}
				
			 }
			 	SimpleDateFormat sdf5 = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
			 	SimpleDateFormat sdf6 = new SimpleDateFormat("yyyy-dd-MM");
				
		        String dateInString1 = sdf6.format(obj);
		        String dateInString2 = sdf6.format(obj);
		        Date StartDate=null;
		        Date EndDate=null;
				StartDate = Date.valueOf(dateInString1);
				EndDate = Date.valueOf(dateInString2);
		        Calendar calendar1 = Calendar.getInstance();
		        calendar1.setTime(StartDate);
		        Calendar calendar2= Calendar.getInstance();
		        calendar2.setTime(EndDate);
		
			 
			for(Appointment a:appoint) {
				
			
						
				
				Time appointmentStartTime=null;
				Time appointmentEndTime=null;
				String dateInString3 =  a.getTimeSlot().getStartTime().toString();
		        String dateInString4 = a.getTimeSlot().getEndTime().toString();
				
		        appointmentStartTime = Time.valueOf(dateInString3);
				appointmentEndTime = Time.valueOf(dateInString4);
		        
		        Calendar calendar3 = Calendar.getInstance();
		        calendar3.setTime(appointmentStartTime);
		        Calendar calendar4= Calendar.getInstance();
		        calendar4.setTime(appointmentEndTime);
		        
		        
		        
				if(a.getTimeSlot().getStartDate().before(StartDate)) {
					continue;
					
					
				}
				
				else if(a.getTimeSlot().getStartDate().after(EndDate)){
					
					continue;
					
				}
				
				else {
					
					Time stopTime=a.getTimeSlot().getStartTime();
					
					
					TOTimeSlot ts1 = new TOTimeSlot(javaDateTosqldate(StartDate), startTime, javaDateTosqldate(a.getTimeSlot().getEndDate()) , stopTime);
					available.add(ts1);
					TimeSlot ts2 = new TimeSlot(javaDateTosqldate(StartDate), startTime, javaDateTosqldate(a.getTimeSlot().getEndDate()) , stopTime,fbs);
					fbs.addTimeSlot(ts2);
					startTime=appointmentEndTime;
					
					
					
			
				}
				}
			FlexiBookPersistence.save(FlexiBookApplication.getFlexiBook());
			return available;
			
		}

/**
 * 
 * @author Mihir Kumar
 * @param ATSdate
 * @return
 * @throws InvalidInputException
 */

public static List<TOTimeSlot> getWeeklyAvailableTimeSlots(String ATSdate) throws InvalidInputException { 
	
	if(!dateValidation(ATSdate)){
		throw new InvalidInputException(ATSdate + " is not a valid date");
	}
	
	FlexiBook fbs = FlexiBookApplication.getFlexiBook();
	List<Appointment> appoint = fbs.getAppointments();
	//appoint.sort(ADate);
	Date obj=null;
	Time startTime=null;
	Time endTime =null;
	List<TOTimeSlot> available= new ArrayList<TOTimeSlot>();
	try {
		 obj = fromStringToDateM(ATSdate);
		
		
	} catch (ParseException e) {
		
		e.printStackTrace();
	}
	
	
	String day = getDayStringOld(obj,Locale.CANADA);
	 BusinessHour.DayOfWeek dow = toDayOfWeek(day);
	 if(!dow.equals(BusinessHour.DayOfWeek.Saturday)) {
		 
	 List<BusinessHour> bhrs = FlexiBookApplication.getFlexiBook().getBusiness().getBusinessHours();
	 for(BusinessHour bg: bhrs) {
		 
		if( bg.getDayOfWeek().compareTo(dow)==0) {
			
			 startTime=bg.getStartTime();
			 endTime=bg.getEndTime();
		
		}
		
	 }
	 	SimpleDateFormat sdf5 = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
	 	SimpleDateFormat sdf6 = new SimpleDateFormat("yyyy-dd-MM");
		
        String dateInString1 = sdf6.format(obj);
        String dateInString2 = sdf6.format(obj);
        Date StartDate=null;
        Date EndDate=null;
		StartDate = Date.valueOf(dateInString1);
		EndDate = Date.valueOf(dateInString2);
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(StartDate);
        Calendar calendar2= Calendar.getInstance();
        calendar2.setTime(EndDate);

	 
	for(Appointment a:appoint) {
		
	
				
		
		Time appointmentStartTime=null;
		Time appointmentEndTime=null;
		String dateInString3 =  a.getTimeSlot().getStartTime().toString();
        String dateInString4 = a.getTimeSlot().getEndTime().toString();
		
        appointmentStartTime = Time.valueOf(dateInString3);
		appointmentEndTime = Time.valueOf(dateInString4);
        
        Calendar calendar3 = Calendar.getInstance();
        calendar3.setTime(appointmentStartTime);
        Calendar calendar4= Calendar.getInstance();
        calendar4.setTime(appointmentEndTime);
        
        
        
		if(a.getTimeSlot().getStartDate().before(StartDate)) {
			continue;
			
			
		}
		
		else if(a.getTimeSlot().getStartDate().after(EndDate)){
			
			continue;
			
		}
		
		else {
			
			Time stopTime=a.getTimeSlot().getStartTime();
			
			if(a.getBookableService().getName().equals("color-basic")) {
				
				int downtimeDur= ((Service) a.getBookableService()).getDowntimeDuration();
				int downtimeStr= ((Service) a.getBookableService()).getDowntimeStart();
				
				 startTime = updateTime(startTime,downtimeStr);
				 stopTime = updateTime(startTime,downtimeDur);
				
				
			}
			
			 if(a.getTimeSlot().getStartTime().after(startTime)) {
			
			TOTimeSlot ts1 = new TOTimeSlot(javaDateTosqldate(StartDate), startTime, javaDateTosqldate(a.getTimeSlot().getEndDate()) , stopTime);
			available.add(ts1);
			TimeSlot ts2 = new TimeSlot(javaDateTosqldate(StartDate), startTime, javaDateTosqldate(a.getTimeSlot().getEndDate()) , stopTime,fbs);
			fbs.addTimeSlot(ts2);
			startTime=appointmentEndTime;
			
			}
			
			else {
				startTime=appointmentEndTime;
				
			}
		}
		}
	
	obj=nextDay(obj);
	
	 }
	 FlexiBookPersistence.save(FlexiBookApplication.getFlexiBook());
	return available;
	
}

/**
 * Converts String to Date
 * 
 * @author Mihir Kumar
 * @param s
 * @return
 * @throws ParseException
 */


private static Date fromStringToDateM(String s) throws ParseException {
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-dd-MM");
	java.util.Date date = sdf.parse(s);

	java.sql.Date sqlDate = new Date(date.getTime());
	return sqlDate;
}


/**
 * This method gives the available appointment of the week based on the date specified
 * 
 * @author Mihir Kumar
 * @param ATSdate
 * @return
 */

public static List<TOTimeSlot> getWeeklyAvailableTimeSlots(Date ATSdate) { 
	
	
	
	FlexiBook fbs = FlexiBookApplication.getFlexiBook();
	List<Appointment> appoint = fbs.getAppointments();
	//appoint.sort(ADate);
	Date obj=null;
	Time startTime=null;
	Time endTime =null;
	List<TOTimeSlot> available= new ArrayList<TOTimeSlot>();
	
		 obj = ATSdate;
		
		
	
	
	
	String day = getDayStringOld(obj,Locale.CANADA);
	 BusinessHour.DayOfWeek dow = toDayOfWeek(day);
	 if(!dow.equals(BusinessHour.DayOfWeek.Saturday)) {
		 
	 List<BusinessHour> bhrs = FlexiBookApplication.getFlexiBook().getBusiness().getBusinessHours();
	 for(BusinessHour bg: bhrs) {
		 
		if( bg.getDayOfWeek().compareTo(dow)==0) {
			
			 startTime=bg.getStartTime();
			 endTime=bg.getEndTime();
		
		}
		
	 }
	 	SimpleDateFormat sdf5 = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
	 	SimpleDateFormat sdf6 = new SimpleDateFormat("yyyy-dd-MM");
		
        String dateInString1 = sdf6.format(obj);
        String dateInString2 = sdf6.format(obj);
        Date StartDate=null;
        Date EndDate=null;
		StartDate = Date.valueOf(dateInString1);
		EndDate = Date.valueOf(dateInString2);
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(StartDate);
        Calendar calendar2= Calendar.getInstance();
        calendar2.setTime(EndDate);

	 
	for(Appointment a:appoint) {
		
	
				
		
		Time appointmentStartTime=null;
		Time appointmentEndTime=null;
		String dateInString3 =  a.getTimeSlot().getStartTime().toString();
        String dateInString4 = a.getTimeSlot().getEndTime().toString();
		
        appointmentStartTime = Time.valueOf(dateInString3);
		appointmentEndTime = Time.valueOf(dateInString4);
        
        Calendar calendar3 = Calendar.getInstance();
        calendar3.setTime(appointmentStartTime);
        Calendar calendar4= Calendar.getInstance();
        calendar4.setTime(appointmentEndTime);
        
        
        
		if(a.getTimeSlot().getStartDate().before(StartDate)) {
			continue;
			
			
		}
		
		else if(a.getTimeSlot().getStartDate().after(EndDate)){
			
			continue;
			
		}
		
		else {
			
			Time stopTime=a.getTimeSlot().getStartTime();
			
			if(a.getBookableService().getName().equals("color-basic")) {
				
				int downtimeDur= ((Service) a.getBookableService()).getDowntimeDuration();
				int downtimeStr= ((Service) a.getBookableService()).getDowntimeStart();
				
				 startTime = updateTime(startTime,downtimeStr);
				 stopTime = updateTime(startTime,downtimeDur);
				
				
			}
			
			 if(a.getTimeSlot().getStartTime().after(startTime)) {
			
			TOTimeSlot ts1 = new TOTimeSlot(javaDateTosqldate(StartDate), startTime, javaDateTosqldate(a.getTimeSlot().getEndDate()) , stopTime);
			TimeSlot ts2 = new TimeSlot(javaDateTosqldate(StartDate), startTime, javaDateTosqldate(a.getTimeSlot().getEndDate()) , stopTime,fbs);
			fbs.addTimeSlot(ts2);
			available.add(ts1);
			startTime=appointmentEndTime;
			
			}
			
			else {
				startTime=appointmentEndTime;
				
			}
		}
		}
	
	obj=nextDay(obj);
	
	 }
	 FlexiBookPersistence.save(FlexiBookApplication.getFlexiBook());
	return available;
	
}

/**
 * This method gives the unavailable time slots of the week based on the date specified
 * 
 * @author Mihir Kumar
 * @param ATSdate
 * @return
 * @throws InvalidInputException
 */

public static List<TOTimeSlot> getWeeklyUnavailableTimeSlots(String ATSdate) throws InvalidInputException { 
	
	if(!dateValidation(ATSdate)){
		throw new InvalidInputException(ATSdate + " is not a valid date");
	}
	
	FlexiBook fbs = FlexiBookApplication.getFlexiBook();
	List<Appointment> appoint = fbs.getAppointments();
	//appoint.sort(ADate);
	Date obj=null;
	Time startTime=null;
	Time endTime =null;
	List<TOTimeSlot> available= new ArrayList<TOTimeSlot>();;
	try {
		 obj = fromStringToDateM(ATSdate);
		
		
	} catch (ParseException e) {
		
		e.printStackTrace();
	}
	String day = getDayStringOld(obj,Locale.CANADA);
	 BusinessHour.DayOfWeek dow = toDayOfWeek(day);
	 
	 if(!dow.equals(BusinessHour.DayOfWeek.Saturday)) {
	 
	 List<BusinessHour> bhrs = FlexiBookApplication.getFlexiBook().getBusiness().getBusinessHours();
	 for(BusinessHour bg: bhrs) {
		 
		if( bg.getDayOfWeek().compareTo(dow)==0) {
			
			 startTime=bg.getStartTime();
			 endTime=bg.getEndTime();
		
		}
		
	 }
	 	SimpleDateFormat sdf5 = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
	 	SimpleDateFormat sdf6 = new SimpleDateFormat("yyyy-dd-MM");
		
        String dateInString1 = sdf6.format(obj);
        String dateInString2 = sdf6.format(obj);
        Date StartDate=null;
        Date EndDate=null;
		StartDate = Date.valueOf(dateInString1);
		EndDate = Date.valueOf(dateInString2);
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(StartDate);
        Calendar calendar2= Calendar.getInstance();
        calendar2.setTime(EndDate);

	 
	for(Appointment a:appoint) {
		
	
				
		
		Time appointmentStartTime=null;
		Time appointmentEndTime=null;
		String dateInString3 =  a.getTimeSlot().getStartTime().toString();
        String dateInString4 = a.getTimeSlot().getEndTime().toString();
		
        appointmentStartTime = Time.valueOf(dateInString3);
		appointmentEndTime = Time.valueOf(dateInString4);
        
        Calendar calendar3 = Calendar.getInstance();
        calendar3.setTime(appointmentStartTime);
        Calendar calendar4= Calendar.getInstance();
        calendar4.setTime(appointmentEndTime);
        
        
        
		if(a.getTimeSlot().getStartDate().before(StartDate)) {
			continue;
			
			
		}
		
		else if(a.getTimeSlot().getStartDate().after(EndDate)){
			
			continue;
			
		}
		
		else {
			
			Time stopTime=a.getTimeSlot().getStartTime();
			
			
			TOTimeSlot ts1 = new TOTimeSlot(javaDateTosqldate(StartDate), startTime, javaDateTosqldate(a.getTimeSlot().getEndDate()) , stopTime);
			TimeSlot ts2 = new TimeSlot(javaDateTosqldate(StartDate), startTime, javaDateTosqldate(a.getTimeSlot().getEndDate()) , stopTime,fbs);
			available.add(ts1);
			fbs.addTimeSlot(ts2);
			startTime=appointmentEndTime;
			
			
			
	
		}
		}
	obj=nextDay(obj);
	 }
	 FlexiBookPersistence.save(FlexiBookApplication.getFlexiBook());
	 
	return available;
}

		
/**
			 * This method returns the day of the date as an integer
			 * 
			 * @author Mihir Kumar
			 * @param date
			 * @return
			 */
			
			public static int getDayNumberOld(Date date) {
			    Calendar cal = Calendar.getInstance();
			    cal.setTime(date);
			    return cal.get(Calendar.DAY_OF_WEEK);
			}

/**
			 * This method returns the day of the date as a String
			 * @param date
			 * @param locale
			 * @return
			 */
			
			public static String getDayStringOld(Date date, Locale locale) {
			    DateFormat formatter = new SimpleDateFormat("EEEE", locale);
			    return formatter.format(date);
			}

/**
			 * This method updates the time 
			 * 
			 * @author Mihir Kumar
			 * @param t
			 * @param min
			 * @return
			 */
			
			private static Time updateTime(Time t, int min) {
				
				long timeInMilliSeconds = t.getTime();
				long durationInMillisSeconds= (long) min * 60000;
	long newTimeInMilliSeconds = timeInMilliSeconds + durationInMillisSeconds;
				
				
				 t.setTime(newTimeInMilliSeconds);
				
				
				return t;
				
				
				
			}

/**
			 * This method goes to the next day of the calendar
			 * 
			 * @author Mihir Kumar
			 * @param date
			 * @return
			 */
			public static Date nextDay(Date date) {
				Calendar c = Calendar.getInstance();
				c.setTime(date);
				c.add(Calendar.DAY_OF_MONTH, 1);
				
				return new Date(c.getTimeInMillis());
			}
			
			/**
			 * This method returns the appointment calendar items to the GUI
			 * 
			 * @author Mihir Kumar
			 * @param date
			 * @return
			 */
			public static List<TOAppointmentCalendarItem> getAppointmentCalendar(Date date){
				date = cleanDate(date);
				FlexiBook fbs = FlexiBookApplication.getFlexiBook();
				ArrayList<TOAppointmentCalendarItem> items = new ArrayList<TOAppointmentCalendarItem>();
				for(TimeSlot ts :fbs.getTimeSlots()) {
					
					TOAppointmentCalendarItem toAppointmentCalendarItem = new TOAppointmentCalendarItem(ts.getStartDate().toString(), ts.getStartTime().toString(), ts.getEndTime().toString());
					items.add(toAppointmentCalendarItem);
				}
				
				return items;
				
			}


/**
 * Cleans up the date
 * @author Mihir Kumar
 * @param date
 * @return
 */

		private static Date cleanDate(Date date) {
			Calendar cal = Calendar.getInstance();
			cal.setTimeInMillis(date.getTime());
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
			java.util.Date tempCleanedDate = cal.getTime();
			java.sql.Date cleanedDate = new java.sql.Date(tempCleanedDate.getTime());
			return cleanedDate;
			}


		public static java.sql.Date javaDateTosqldate(java.util.Date uDate){
			 java.sql.Date sDate = new java.sql.Date(uDate.getTime());
			 return sDate;
		}

		
	/**
	 * @author Natalia
	 * @throws InvalidInputException
	 *
	 */

	public static boolean cancelAppointment(Appointment a,FlexiBook fbs,Date currentDate,Time currentTime) throws InvalidInputException{
		boolean success = a.cancelAppointment(a, fbs, currentDate, currentTime);
		FlexiBookPersistence.save(FlexiBookApplication.getFlexiBook());
		return success;
	}
	
	public static boolean cancelAppointmentFromView(TOAppointment aTO) throws InvalidInputException {
		Date nowDate = getSystemDate();
		String dateAsString = nowDate.toString();
		Date nowToCompare = null;
		try {
			nowToCompare = fromStringToDate(nowDate.toString());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Time nowTime = getSystemTime();
		String timeAsString = nowTime.toString();
		timeAsString = timeAsString.substring(0, 5);
		Time timeToCompare = fromStringToTime(timeAsString);
		
		FlexiBook fbs = FlexiBookApplication.getFlexiBook();
		Appointment a = getAppointmentFromDto(aTO);
		
		//clean TO times and dates
		a.getTimeSlot().setStartDate(cleanDate(a.getTimeSlot().getStartDate()));
		a.getTimeSlot().setEndDate(cleanDate(a.getTimeSlot().getEndDate()));
		
		//clean now
		nowDate = cleanDate(nowDate);
		FlexiBookPersistence.save(FlexiBookApplication.getFlexiBook());
		return cancelAppointment(a, fbs, nowDate, nowTime);
	}
	
	
	/**
	 * @author Natalia
	 * @throws InvalidInputException
	 *
	 */

	public static boolean startAppointment(TOAppointment aTO, FlexiBook fbs, Date currentDate, Time currentTime) {
		Appointment a = getAppointmentFromDto(aTO);
		boolean success = a.startAppointment(a, fbs, currentDate, currentTime);
		FlexiBookPersistence.save(FlexiBookApplication.getFlexiBook());
		return success;
	}
	/**
	 * @author Natalia
	 * @throws InvalidInputException
	 *
	 */

	public static boolean endAppointment(Appointment a, FlexiBook fbs) {
		
		boolean success = a.finishAppointment(a, fbs);
		FlexiBookPersistence.save(FlexiBookApplication.getFlexiBook());
		return success;
	}
	
	/**
	 * @author Natalia
	 * @throws InvalidInputException
	 *
	 */

	public static boolean markNoShow(Customer c , Appointment a) {
		
		boolean success = a.customerDidNotArrive(c, a);
		FlexiBookPersistence.save(FlexiBookApplication.getFlexiBook());
		return success;
	}
	
	/**
	 * This helper method converts a string into a Date object
	 * 
	 * @author Natalia Tabet Agredo
	 * @param s the string date in format yyyy-MM-dd
	 * @return the Date object
	 * @throws ParseException
	 */
	private static Date fromStringToDate(String s) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date = sdf.parse(s);

		java.sql.Date sqlDate = new Date(date.getTime());
		return sqlDate;
	}

	/**
	 * This helper method converts a string into a Time object
	 * 
	 * @author Natalia Tabet Agredo
	 * @param s the string of the time
	 * @return the Time object
	 */
	private static Time fromStringToTime(String s) {
		s += ":00";
		java.sql.Time sqlTime = Time.valueOf(s);
		return sqlTime;
	}
	public static List <TOComboItem> showChosenItems(TOAppointment a){
		FlexiBook fbs = FlexiBookApplication.getFlexiBook();
		List <TOComboItem> chosen = new ArrayList<>();
		
		for (TOComboItem aChosen : showComboItems(a.getBookableService().getName(), fbs)) {
			if (aChosen.getMandatory() == true) {
				chosen.add(aChosen);
			}
		}
		
		if (a.getChosenItems().size() != 0 ) {
			for (TOComboItem ci : a.getChosenItems()) {
				chosen.add(ci);
			}
		}
		
		return chosen;
	}
	
	
	public static List<TOAppointment> getApptsForDay(Date date) {
		ArrayList<TOAppointment> allAppts = new ArrayList<TOAppointment>();
		date = cleanDate(date);
		for (Appointment a : FlexiBookApplication.getFlexiBook().getAppointments()) {
				Date apptDate = a.getTimeSlot().getStartDate();
				apptDate = cleanDate(apptDate);
				if (date.equals(apptDate)) {
					TOCustomer aCustomer = new TOCustomer(a.getCustomer().getUsername(), a.getCustomer().getPassword());
					TOBookableService aBookableService = null;
					Service s = (Service)a.getBookableService();
					TOService service = new TOService(s.getName(), s.getDuration(), s.getDowntimeDuration(), s.getDowntimeStart());
					aBookableService = service;
					TimeSlot ts = a.getTimeSlot();
					TOTimeSlot apptSlot = new TOTimeSlot(ts.getStartDate(), ts.getStartTime(), ts.getEndDate(), ts.getEndTime());
					TOAppointment appToStart = new TOAppointment(aCustomer, aBookableService, apptSlot);
					allAppts.add(appToStart);
				}
				
		}
		
		return allAppts;
	}

	public static List<TOAppointment> showAppointmentsToStart() throws ParseException{
		//remove this after actual appointments can be actually created in the view.
		//TESTING PURPOSES OF VIEW
		
		ArrayList<TOAppointment> appointmentsToStart = new ArrayList<TOAppointment>();
		setIsTest(false);
		Date nowDate = getSystemDate();
		String dateAsString = nowDate.toString();
		Date nowToCompare = fromStringToDate(nowDate.toString());
		Time nowTime = getSystemTime();
		String timeAsString = nowTime.toString();
		timeAsString = timeAsString.substring(0, 5);
		Time timeToCompare = fromStringToTime(timeAsString);
		
		for (Appointment a : FlexiBookApplication.getFlexiBook().getAppointments()) {
		
			if (a.getStatus() == Status.Issued && a.getTimeSlot().getStartDate().equals(nowToCompare) && a.getTimeSlot().getStartTime().before(timeToCompare) && a.getTimeSlot().getEndTime().after(timeToCompare)) {
				
				TOCustomer aCustomer = new TOCustomer(a.getCustomer().getUsername(), a.getCustomer().getPassword());
				TOBookableService aBookableService = null;
				if (a.getBookableService() instanceof Service) {
					Service s = (Service)a.getBookableService();
					TOService service = new TOService(s.getName(), s.getDuration(), s.getDowntimeDuration(), s.getDowntimeStart());
					aBookableService = service;
				}
				else {
					ServiceCombo sc = (ServiceCombo)a.getBookableService();
					TOServiceCombo combo = new TOServiceCombo(a.getBookableService().getName());
					TOService mainService = new TOService(sc.getMainService().getService().getName(), sc.getMainService().getService().getDuration(), sc.getMainService().getService().getDowntimeDuration(), sc.getMainService().getService().getDowntimeStart());
					TOComboItem aNewMainService = new TOComboItem(sc.getMainService().isMandatory(), mainService, combo);
					for (ComboItem ci : sc.getServices()) {
						if (ci.getMandatory() == true) {
							TOService aService = new TOService(ci.getService().getName(), ci.getService().getDuration(), ci.getService().getDowntimeDuration(), ci.getService().getDowntimeStart());
							TOComboItem aComboItem = new TOComboItem(true, aService, combo);
							combo.addService(aComboItem);
						}
					}
					combo.setMainService(aNewMainService);
					
					//add additional services if any
					if (a.getChosenItems().size() != 0 ) {
						for (ComboItem chosen : a.getChosenItems()) {
							TOService aService = new TOService(chosen.getService().getName(), chosen.getService().getDuration(), chosen.getService().getDowntimeDuration(), chosen.getService().getDowntimeStart());
							TOComboItem aComboItem = new TOComboItem(true, aService, combo);
							combo.addService(aComboItem);
						}
					}
					aBookableService = combo;
					//combo.add
				}
				
				
				TimeSlot ts = a.getTimeSlot();
				TOTimeSlot apptSlot = new TOTimeSlot(ts.getStartDate(), ts.getStartTime(), ts.getEndDate(), ts.getEndTime());
				TOAppointment appToStart = new TOAppointment(aCustomer, aBookableService, apptSlot);
				appointmentsToStart.add(appToStart);
			}
		}
		return appointmentsToStart;
	}
	
	public static List<TOAppointment> showAppointmentsToEndNoShow () throws ParseException{

		ArrayList<TOAppointment> appointmentsToEndNoShow = new ArrayList<TOAppointment>();

		for (Appointment a : FlexiBookApplication.getFlexiBook().getAppointments()) {
			if (a.getStatus() == Status.InProgress ) {
				TOCustomer aCustomer = new TOCustomer(a.getCustomer().getUsername(), a.getCustomer().getPassword());
				TOBookableService aBookableService = null;
				if (a.getBookableService() instanceof Service) {
					Service s = (Service)a.getBookableService();
					TOService service = new TOService(s.getName(), s.getDuration(), s.getDowntimeDuration(), s.getDowntimeStart());
					aBookableService = service;
				}
				else {
					ServiceCombo sc = (ServiceCombo)a.getBookableService();
					TOServiceCombo combo = new TOServiceCombo(a.getBookableService().getName());
					TOService mainService = new TOService(sc.getMainService().getService().getName(), sc.getMainService().getService().getDuration(), sc.getMainService().getService().getDowntimeDuration(), sc.getMainService().getService().getDowntimeStart());
					TOComboItem aNewMainService = new TOComboItem(sc.getMainService().isMandatory(), mainService, combo);
					for (ComboItem ci : sc.getServices()) {
						if (ci.getMandatory() == true) {
							TOService aService = new TOService(ci.getService().getName(), ci.getService().getDuration(), ci.getService().getDowntimeDuration(), ci.getService().getDowntimeStart());
							TOComboItem aComboItem = new TOComboItem(true, aService, combo);
							combo.addService(aComboItem);
						}
					}
					combo.setMainService(aNewMainService);
					
					//add additional services if any
					if (a.getChosenItems().size() != 0 ) {
						for (ComboItem chosen : a.getChosenItems()) {
							TOService aService = new TOService(chosen.getService().getName(), chosen.getService().getDuration(), chosen.getService().getDowntimeDuration(), chosen.getService().getDowntimeStart());
							TOComboItem aComboItem = new TOComboItem(true, aService, combo);
							combo.addService(aComboItem);
						}
					}
					aBookableService = combo;
					//combo.add
				}
				
				
				TimeSlot ts = a.getTimeSlot();
				TOTimeSlot apptSlot = new TOTimeSlot(ts.getStartDate(), ts.getStartTime(), ts.getEndDate(), ts.getEndTime());
				TOAppointment appToStart = new TOAppointment(aCustomer, aBookableService, apptSlot);
				appointmentsToEndNoShow.add(appToStart);
			}
		}
		return appointmentsToEndNoShow;
		
	}
	
	public static void createApptForTesting() throws ParseException {
		FlexiBookApplication.getFlexiBook().delete();
		FlexiBook fbs = FlexiBookApplication.getFlexiBook();
		Customer c = new Customer("natalia", "natalia", 0, fbs);
		Service s1 = new Service("cut", fbs, 40, 0, 0);
		Date aStartDate = fromStringToDate("2020-11-27");
		Time aStartTime = fromStringToTime("10:50");
		Date aEndDate = fromStringToDate("2020-11-27");
		Time aEndTime = fromStringToTime("11:30");
		
		TimeSlot t = new TimeSlot(aStartDate, aStartTime, aEndDate, aEndTime, fbs);
		Appointment appt = new Appointment(c, s1, t, fbs);
		
		
	}
	
	public static boolean markNoShowFromView (TOAppointment aTO) {
		//find customer
		Customer c = null;
		for (Customer aCustomer : FlexiBookApplication.getFlexiBook().getCustomers()) {
			if (aCustomer.getUsername().equals(aTO.getCustomer().getUsername())) {
				c= aCustomer;
			}
		}
		//saving changes to file
		FlexiBookPersistence.save(FlexiBookApplication.getFlexiBook());
		
		Appointment a = getAppointmentFromDto(aTO);
		
		return markNoShow(c, a);
	}
	
	public static Appointment getAppointmentFromDto(TOAppointment appointmentTO) {
		Appointment foundAppointment = null;
		List<Appointment> apptList = FlexiBookApplication.getFlexiBook().getAppointments();
		for (Appointment a : apptList) {
			if (a.getBookableService().getName() == appointmentTO.getBookableService().getName()
					&& a.getCustomer().getUsername() == appointmentTO.getCustomer().getUsername()
					&& a.getTimeSlot().getStartDate().equals(appointmentTO.getTimeSlot().getStartDate())
					&& a.getTimeSlot().getStartTime().equals(appointmentTO.getTimeSlot().getStartTime())) 
			{
				foundAppointment = a;
			}
		}
		return foundAppointment;
	}
	
	public static TOAppointment getDtoFromAppointment (Appointment a) {
		TOCustomer custTO = new TOCustomer(a.getCustomer().getUsername(), a.getCustomer().getPassword());
		TOBookableService aBookableService = null;
		TOServiceCombo comboTO = null;
		boolean isCombo = false;
		
		if (a.getBookableService() instanceof Service) {
			Service s = (Service)a.getBookableService();
			TOService serviceTO = new TOService(s.getName(), s.getDuration(), s.getDowntimeDuration(), s.getDowntimeStart());
			aBookableService = serviceTO;
		}
		else {
			isCombo = true;
			ServiceCombo sc = (ServiceCombo)a.getBookableService();
			comboTO= new TOServiceCombo(sc.getName());
			for (ComboItem ci : sc.getServices()) {
				TOService serviceTO = new TOService(ci.getService().getName(), ci.getService().getDuration(), ci.getService().getDowntimeDuration(), ci.getService().getDowntimeStart());
				TOComboItem itemTO = new TOComboItem(ci.getMandatory(), serviceTO, comboTO);
				comboTO.addService(itemTO);
				if (ci.getService().getName().equals(sc.getMainService().getService().getName())) {
					comboTO.setMainService(itemTO);
				}
				
			}
			aBookableService = comboTO;
			
		}
		
		TOTimeSlot slotTO = new TOTimeSlot(a.getTimeSlot().getStartDate(), a.getTimeSlot().getStartTime(), a.getTimeSlot().getEndDate(), a.getTimeSlot().getEndTime());
		TOAppointment appTO = new TOAppointment(custTO, aBookableService,slotTO ); 
		
		if (isCombo) {
			for (ComboItem aChosen :a.getChosenItems()) {
				TOService serviceTO = new TOService(aChosen.getService().getName(), aChosen.getService().getDuration(), aChosen.getService().getDowntimeDuration(), aChosen.getService().getDowntimeStart());
				TOComboItem itemTO = new TOComboItem(aChosen.getMandatory(), serviceTO, comboTO);
				appTO.addChosenItem(itemTO);
			}
		}
		
		
		return appTO;
	}
	
	public static boolean endApptFromView (TOAppointment aTO) {
		FlexiBook fbs = FlexiBookApplication.getFlexiBook();
		Appointment a = getAppointmentFromDto(aTO);
		
		//saving changes to file
		FlexiBookPersistence.save(FlexiBookApplication.getFlexiBook());
		
		return endAppointment(a, fbs);
	}
	
	public static boolean startAppointmentFromView(TOAppointment aTO) {
		FlexiBook fbs = FlexiBookApplication.getFlexiBook();
		Date nowDate = getSystemDate();
		Time nowTime = getSystemTime();
		
		//saving changes to file
		FlexiBookPersistence.save(FlexiBookApplication.getFlexiBook());
		
		return startAppointment(aTO, fbs, nowDate, nowTime);
	}
	/**
	 *@author Rakshitha 
	 * @param serviceName
	 * @return the bookable service corresponding to the service name
	 */
	private static BookableService findServiceByName(String serviceName) {
		FlexiBook flexiBook = FlexiBookApplication.getFlexiBook();
		BookableService thisService;

		List<BookableService> serviceList = flexiBook.getBookableServices();
		for(int r = 0; r < serviceList.size(); r++) 
		{
			thisService = serviceList.get(r);
			if(thisService.getName().equals(serviceName)) {
				return thisService;
			}
		}

		return null;
	}
	

	/**
	 * @author Rakshitha
	 * @param username
	 * @return the customer corresponding to the username
	 */
	private static Customer findCustomerByName(String username) {
		FlexiBook flexiBook = FlexiBookApplication.getFlexiBook();
		Customer thisCustomer;

		List<Customer> customerList = flexiBook.getCustomers();
		for(int i = 0; i < customerList.size(); i++) {
			thisCustomer = customerList.get(i);
			if(thisCustomer.getUsername().equals(username)) {
				return thisCustomer;
			}
		}
		return null;
	}

	/**
	 * This helper method gets the combo items from a specific combo
	 * 
	 * @author Natalia Tabet Agredo
	 * @param comboName the name of the combo
	 * @param f         the flexibook object of the system
	 * @return the list of combo items contained in the specific combo
	 */
	private static List<ComboItem> getComboItems(String comboName, FlexiBook f) {

		ArrayList<ComboItem> comboItems = new ArrayList<ComboItem>();
		ServiceCombo c = findServiceCombo(comboName, f);
		if (c == null) {
			System.out.println("c is null");
		} else {
			for (ComboItem ci : c.getServices()) {
				comboItems.add(ci);
			}
		}

		return comboItems;
	}

	/**
	 * This helper method finds a Service in the system by name
	 * 
	 * @author Natalia Tabet Agredo
	 * @param name the name of the Service
	 * @param f    the flexibook object of the application
	 * @return the found Service with the given name or null if not found
	 */
	private static Service findService(String name, FlexiBook flexibook) {
		Service foundService = null;
		for (BookableService service : flexibook.getBookableServices()) {
			if (service instanceof Service) {
				if (service.getName().equalsIgnoreCase(name)) {
					foundService = (Service) service;
					break;
				}
			}

		}
		return foundService;
	}

	/**
	 * This helper method finds a ServiceCombo of a given name inside the system
	 * 
	 * @author Natalia Tabet Agredo
	 * @param name the name of the ServiceCombo
	 * @param f    the flexibook object of the system
	 * @return the found object with given name or null if not found
	 */
	private static ServiceCombo findServiceCombo(String name, FlexiBook f) {
		ServiceCombo foundServiceCombo = null;
		for (BookableService serviceCombo : f.getBookableServices()) {
			if (serviceCombo instanceof ServiceCombo) {
				if (serviceCombo.getName().equalsIgnoreCase(name)) {
					foundServiceCombo = (ServiceCombo) serviceCombo;
					break;
				}
			}

		}
		return foundServiceCombo;
	}

	/**
	 * This method gets the ComboItem with the specified name from a ComboService
	 * 
	 * @author Natalia Tabet Agredo
	 * @param name the name of the ComboItem
	 * @param sc   the ServiceCombo to look in
	 * @return the ComboItem with specified name or null if not found
	 */
	private static ComboItem getItemFromCombo(String name, ServiceCombo sc) {
		ComboItem foundComboItem = null;
		for (ComboItem ci : sc.getServices()) {
			if (ci.getService().getName().equalsIgnoreCase(name)) {
				foundComboItem = ci;
				break;
			}
		}
		return foundComboItem;
	}

	/**
	 * This query method displays to the GUI the list of all available services
	 * 
	 * @author Natalia Tabet Agredo
	 * @return the transfer objects of the available services
	 */
	public static List<TOService> getAvailableServices() {
		ArrayList<TOService> services = new ArrayList<TOService>();
		for (BookableService service : FlexiBookApplication.getFlexiBook().getBookableServices()) {
			if (service instanceof Service) {
				TOService toService = new TOService(service.getName(), ((Service) service).getDuration(),
						((Service) service).getDowntimeDuration(), ((Service) service).getDowntimeStart());
				services.add(toService);

			}
		}
		return services;

	}

	/**
	 * This query method displays to the GUI all the available combos
	 * 
	 * @author Natalia Tabet Agredo
	 * @param f the flexibook object of the system
	 * @return the list of transfer objects of the available combos
	 */
	public static List<TOServiceCombo> showCurrentCombos(FlexiBook f) {
		ArrayList<TOServiceCombo> combos = new ArrayList<TOServiceCombo>();
		for (BookableService combo : f.getBookableServices()) {
			if (combo instanceof ServiceCombo) {
				TOServiceCombo toCombo = new TOServiceCombo(combo.getName());

				for (ComboItem ci : ((ServiceCombo) combo).getServices()) {
					TOService toService = new TOService(ci.getService().getName(), ci.getService().getDuration(),
							ci.getService().getDowntimeDuration(), ci.getService().getDowntimeStart());
					TOComboItem toItem = new TOComboItem(ci.getMandatory(), toService, toCombo);

				}
				ComboItem main = ((ServiceCombo) combo).getMainService();
				for (TOComboItem ci : toCombo.getServices()) {
					if (ci.getService().getName().equalsIgnoreCase(main.getService().getName())) {
						toCombo.setMainService(ci);
					}
				}

				combos.add(toCombo);

			}
		}
		return combos;
	}

	/**
	 * This query method displays to the GUI all the services of a particular combo
	 * 
	 * @author Natalia Tabet Agredo
	 * @param comboName the name of the combo
	 * @param f         the flexibook object of the system
	 * @return the transfer objects of the services of a combo
	 */
	public static List<TOService> showComboServices(String comboName, FlexiBook f) {
		ArrayList<TOService> comboServices = new ArrayList<TOService>();
		ServiceCombo c = findServiceCombo(comboName, f);

		for (ComboItem ci : c.getServices()) {
			TOService service = new TOService(ci.getService().getName(), ci.getService().getDuration(),
					ci.getService().getDowntimeDuration(), ci.getService().getDowntimeStart());
			comboServices.add(service);
		}
		return comboServices;
	}

	/**
	 * This query method displays to the GUI all he combo items of a particular
	 * combo
	 * 
	 * @author Natalia Tabet Agredo
	 * @param comboName the name of the combo
	 * @param f         the flexibook object of the system
	 * @return the transfer objects of the combo items contained in the specified
	 *         combo
	 */
	public static List<TOComboItem> showComboItems(String comboName, FlexiBook f) {

		ArrayList<TOComboItem> comboItems = new ArrayList<TOComboItem>();
		ServiceCombo c = findServiceCombo(comboName, f);
		TOServiceCombo toS = new TOServiceCombo(c.getName());

		for (ComboItem ci : c.getServices()) {
			TOService s = new TOService(ci.getService().getName(), ci.getService().getDuration(),
					ci.getService().getDowntimeDuration(), ci.getService().getDowntimeStart());
			TOComboItem item = new TOComboItem(ci.getMandatory(), s, toS);
			comboItems.add(item);
		}
		return comboItems;
	}

	
	/**
	 * This method: SingUpCustomerAccount feature 
	 * @author Karim Elgammal
	 * @param aUsername the username the customer provides 
	 * @param aPassword the password the customer provides 
	 * @param f the flexibook object of the system
	 * @throws InvalidInputException
	 */	
	public static Customer createCustomerAccount(String aUsername, String aPassword, FlexiBook f) throws InvalidInputException{
		String error = ""; 
		if(FlexiBookApplication.getCurrentUser() != null ) {
			if(FlexiBookApplication.getCurrentUser().getUsername().equals("owner") ) {
				error = "You must log out of the owner account before creating a customer account";
			}
		}
		
		if(aUsername == null || aUsername.equals("") ) {
				error = "The user name cannot be empty";
			}
		if (aPassword == null || aPassword.equals("")) {
				error = "The password cannot be empty";
			}
		if(error.length()>0){
				System.out.println("entering error with errror" + error);
				throw new InvalidInputException(error.trim());
			} 
		try{
				Customer newCustomerAccount = new Customer(aUsername, aPassword,0,  f);
			List <Customer> c = f.getCustomers();
			for(Customer c1: c) {
				System.out.println(c1.getUsername());
			}

			//saving changes to file
			FlexiBookPersistence.save(FlexiBookApplication.getFlexiBook());
//			f.addCustomer(newCustomerAccount);
			
				return newCustomerAccount; 
			} 
		
		catch (RuntimeException e){
			System.out.println("printing catch");
			error = e.getMessage();
			if (error.equals("Cannot create due to duplicate username. See http://manual.umple.org?RE003ViolationofUniqueness.html")) {
				error = "The username already exists";
			}
			throw new InvalidInputException(error); 
		}

				
	}
	/**
	 * This method takes care of the delete customer feature 
	 * @author Karim Elgammal
	 * @param aCustomer the target customer intended to be deleted
	 * @param f the flexibook object of the system
	 * @throws InvalidInputException
	 */	
	public static void deleteCustomerAccount(User aCustomer, FlexiBook f) throws InvalidInputException {
		User target = aCustomer; 
		String error = ""; 
		if(FlexiBookApplication.getCurrentUser().getUsername().equals("owner") ) {
		error = "You do not have permission to delete this account";
		}
		if(!target.getUsername().equals(FlexiBookApplication.getCurrentUser().getUsername())) {
			error = "You do not have permission to delete this account";
		}
		if (error.length()>0) {
			System.out.println("entering error with errror" + error);
			throw new InvalidInputException(error.trim());
		}
		try {
			aCustomer.delete(); 
			FlexiBookApplication.setCurrentUser(null);

			//saving changes to file
			FlexiBookPersistence.save(FlexiBookApplication.getFlexiBook());
			
		}
		catch (RuntimeException e) {
			throw new InvalidInputException(error);
		}
	}
	/**
	 * This method updates the logged in user's username and password 
	 * @author Karim Elgammal
	 * @param aNewUsername the new username that is intended to update with
	 * @param aNewPassword the new password that is intended to update with
	 * @param f flexibook object of the system
	 * @throws InvalidInputException
	 */	
	public static void updateAccount(String aNewUsername, String aNewPassword, FlexiBook f) throws InvalidInputException{
		String error = "";
		if(aNewUsername == null || aNewUsername.equals("")) {
			error = "The user name cannot be empty"; 
		}
		if (aNewPassword == null || aNewPassword.equals("")) {
			error = "The password cannot be empty";
		}
		if(findCustomer(aNewUsername, f)!= null && findCustomer(aNewUsername, f)!= FlexiBookApplication.getCurrentUser()) {
			error = "Username not available";
		}
		if(FlexiBookApplication.getCurrentUser().getUsername().equals("owner") && !aNewUsername.equals("owner")) {
			error = "Changing username of owner is not allowed";
			System.out.println("CHECKING");
		}
		if(error.length()>0){
			System.out.println("entering error with errror" + error);
			throw new InvalidInputException(error.trim());
		} 
		try {
			FlexiBookApplication.getCurrentUser().setUsername(aNewUsername);
			FlexiBookApplication.getCurrentUser().setPassword(aNewPassword);

			//saving changes to file
			FlexiBookPersistence.save(FlexiBookApplication.getFlexiBook());
			
		}catch(RuntimeException e) {
			throw new InvalidInputException(error);
		}
	}

	
	/**
	 * This is a private helper method that finds a customer based on the username and returns it
	 *@author Karim Elgammal
	 * @param aUsername
	 * @param f the flexibook object of the system
	 * @return the found customer object
	 */
	private static Customer findCustomer(String aUsername, FlexiBook f){
		Customer foundCustomer = null; 
		for (Customer c : f.getCustomers()) {
			if(c.getUsername().equals(aUsername)) {
				foundCustomer = c;
				break; 
			}
		}
		return foundCustomer; 
	}
	/**
	 * This query method displays to the GUI the main service of a particular combo
	 * 
	 * @author Natalia Tabet Agredo
	 * @param comboName the name of the combo
	 * @param f         the flexibook object of the system
	 * @return the transfer object of the main service of the specified combo
	 */
	public static TOService showMainService(String comboName, FlexiBook f) {
		TOService mainToService = null;
		ServiceCombo c = findServiceCombo(comboName, f);
		mainToService = new TOService(c.getMainService().getService().getName(),
				c.getMainService().getService().getDuration(), c.getMainService().getService().getDowntimeDuration(),
				c.getMainService().getService().getDowntimeStart());
		return mainToService;
	}

	/**
	 * This method gets the system time
	 * 
	 * @author Natalia Tabet Agredo
	 * @return the current time if isTest = false or the specified time during the
	 *         test if isTest = true
	 */
	public static Time getSystemTime() {
		return SystemTime.getTime();
	}

	/**
	 * This method gets the system date
	 * 
	 * @author Natalia Tabet Agredo
	 * @return the current date if isTest = false or the specified date during the
	 *         test if isTest = true
	 */
	public static Date getSystemDate() {
		return SystemTime.getDate();
	}

	/**
	 * This method sets the system time for testing purposes if isTest = true
	 * 
	 * @author Natalia Tabet Agredo
	 * @param t the desired time
	 */
	public static void setSystemTime(Time t) {
		SystemTime.setTime(t);
	}

	/**
	 * This method sets the system date for testing purposes if isTest = true
	 * 
	 * @author Natalia Tabet Agredo
	 * @param d the desired date
	 */
	public static void setSystemDate(Date d) {
		SystemTime.setDate(d);
	}

	/**
	 * This method sets the isTest variable of the system.
	 * 
	 * @author Natalia Tabet Agredo
	 * @param isTest
	 */
	public static void setIsTest(boolean isTest) {
		SystemTime.setTest(isTest);
	}

	public static User getUser(String username, String password) throws InvalidInputException {

		String error = "";
		User u = User.getWithUsername(username);
		if (u == null) {
			error = "Username/password not found";
			throw new InvalidInputException(error.trim());
		} else {

			String pass = u.getPassword();
			if (pass.equals(password)) {

				return u;

			}

			u = null;
			error = "Username/password not found";
			throw new InvalidInputException(error.trim());

		}

			//	return user;
	}

	public static User createNewUser(String username, String password, FlexiBook f) throws InvalidInputException {

		try {
			Owner o = new Owner(username, password, f);
			
			//saving changes to file
			FlexiBookPersistence.save(FlexiBookApplication.getFlexiBook());
			
			return o;
			
		}
		catch(RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}

	}

	public static void getLogedIn(String username, String password) throws InvalidInputException {

		FlexiBook fb = FlexiBookApplication.getFlexiBook();

	}

	public static void getLogedOut() throws InvalidInputException {

		String error = "";
		// FlexiBookApplication.setCurrentUser(null);
		FlexiBook fb = FlexiBookApplication.getFlexiBook();

		if (FlexiBookApplication.getCurrentUser() == null) {

			error = "The user is already logged out";
			throw new InvalidInputException(error.trim());

		}
		// FlexiBookApplication.setCurrentUser(null);

	}

	public static void getAppointmentCalender(User user, Date date) throws InvalidInputException {

		FlexiBook fb = FlexiBookApplication.getFlexiBook();

	}

	public static List<Appointment> getSpecificAvailableAppointment(User user, Date date) {

		FlexiBook fbs = FlexiBookApplication.getFlexiBook();

		List<Appointment> appoint = new ArrayList<Appointment>();
		List<Appointment> available = new ArrayList<Appointment>();
		List<Appointment> unavailable = new ArrayList<Appointment>();
		List<Appointment> favailable = new ArrayList<Appointment>();
		List<BusinessHour> bh = new ArrayList<BusinessHour>();

		appoint = FlexiBookApplication.getFlexiBook().getAppointments();

		ListIterator<Appointment> iterator = appoint.listIterator();

		while (iterator.hasNext()) {

			Appointment appointment = iterator.next();
			Date startDate = appointment.getTimeSlot().getStartDate();
			Date endDate = appointment.getTimeSlot().getEndDate();

			BusinessHour BHour = bh.get(0);

			TimeSlot ts = new TimeSlot(startDate, BHour.getStartTime(), endDate, BHour.getEndTime(), fbs);
			Appointment app = new Appointment(appointment.getCustomer(), appointment.getBookableService(), ts, fbs);
			available.add(app);

		}

		for (Appointment a : available) {

			if (a.getBookableService().getName().equals("color")) {

				java.sql.Time myTime = java.sql.Time.valueOf(a.getTimeSlot().getStartTime().toString());
				LocalTime localtime = myTime.toLocalTime();
				LocalTime localtime1 = localtime.plusMinutes(75);
				LocalTime localtime2 = localtime1.plusMinutes(105);
				String output = localtime1.toString();
				String output2 = localtime2.toString();

				TimeSlot slot = new TimeSlot(a.getTimeSlot().getStartDate(), a.getTimeSlot().getStartTime(),
						a.getTimeSlot().getEndDate(), Time.valueOf(output), fbs);
				Appointment appo = new Appointment(a.getCustomer(), a.getBookableService(), slot, fbs);
				unavailable.add(appo);

				/*
				 * java.sql.Time myTime2 =
				 * java.sql.Time.valueOf(a.getTimeSlot().getStartTime().toString()); LocalTime
				 * localtime3 = myTime2.toLocalTime(); LocalTime localtime4 =
				 * localtime3.minusMinutes(75); LocalTime localtime5 =
				 * localtime3.minusMinutes(105); String output3 = localtime4.toString(); String
				 * output4 = localtime5.toString();
				 */

				TimeSlot slot2 = new TimeSlot(a.getTimeSlot().getStartDate(), Time.valueOf(output),
						a.getTimeSlot().getEndDate(), Time.valueOf(output2), fbs);
				Appointment appo2 = new Appointment(a.getCustomer(), a.getBookableService(), slot2, fbs);
				favailable.add(appo2);

			}

			else {

				favailable.add(a);

			}

		}

		return favailable;

	}

	public static List<Appointment> getSpecificUnavailableAppointment(User user, Date date) {

		FlexiBook fbs = FlexiBookApplication.getFlexiBook();

		List<Appointment> appoint = new ArrayList<Appointment>();
		List<Appointment> available = new ArrayList<Appointment>();
		List<Appointment> unavailable = new ArrayList<Appointment>();
		List<Appointment> favailable = new ArrayList<Appointment>();
		List<BusinessHour> bh = new ArrayList<BusinessHour>();

		appoint = FlexiBookApplication.getFlexiBook().getAppointments();

		ListIterator<Appointment> iterator = appoint.listIterator();

		while (iterator.hasNext()) {

			Appointment appointment = iterator.next();
			Date startDate = appointment.getTimeSlot().getStartDate();
			Date endDate = appointment.getTimeSlot().getEndDate();

			BusinessHour BHour = bh.get(0);

			TimeSlot ts = new TimeSlot(startDate, BHour.getStartTime(), endDate, BHour.getEndTime(), fbs);
			Appointment app = new Appointment(appointment.getCustomer(), appointment.getBookableService(), ts, fbs);
			available.add(app);

		}

		for (Appointment a : available) {

			if (a.getBookableService().getName().equals("color")) {

				java.sql.Time myTime = java.sql.Time.valueOf(a.getTimeSlot().getStartTime().toString());
				LocalTime localtime = myTime.toLocalTime();
				LocalTime localtime1 = localtime.plusMinutes(75);
				LocalTime localtime2 = localtime1.plusMinutes(105);
				String output = localtime1.toString();
				String output2 = localtime2.toString();

				TimeSlot slot = new TimeSlot(a.getTimeSlot().getStartDate(), a.getTimeSlot().getStartTime(),
						a.getTimeSlot().getEndDate(), Time.valueOf(output), fbs);
				Appointment appo = new Appointment(a.getCustomer(), a.getBookableService(), slot, fbs);
				unavailable.add(appo);

				/*
				 * java.sql.Time myTime2 =
				 * java.sql.Time.valueOf(a.getTimeSlot().getStartTime().toString()); LocalTime
				 * localtime3 = myTime2.toLocalTime(); LocalTime localtime4 =
				 * localtime3.minusMinutes(75); LocalTime localtime5 =
				 * localtime3.minusMinutes(105); String output3 = localtime4.toString(); String
				 * output4 = localtime5.toString();
				 */

				TimeSlot slot2 = new TimeSlot(a.getTimeSlot().getStartDate(), Time.valueOf(output),
						a.getTimeSlot().getEndDate(), Time.valueOf(output2), fbs);
				Appointment appo2 = new Appointment(a.getCustomer(), a.getBookableService(), slot2, fbs);
				favailable.add(appo2);

			}

			else {

				favailable.add(a);

			}

		}

		return unavailable;

	}

	public static void getWeeklyAvailableAppointment(Date date, User user) {

		FlexiBook fbs = FlexiBookApplication.getFlexiBook();
		for (int i = 1; i <= 7; i++) {

			getSpecificAvailableAppointment(user, date);
			nextDay(date);

		}

	}

	public static void getWeeklyUnavailableAppointment(Date date, User user) {

		FlexiBook fbs = FlexiBookApplication.getFlexiBook();
		for (int i = 1; i <= 7; i++) {

			getSpecificUnavailableAppointment(user, date);
			date = nextDay(date);

		}

	}

	


	private static ComboItem getMainServiceFromCombo(String name, ServiceCombo sc) {
		ComboItem foundComboItem = null;
		for (ComboItem ci : sc.getServices()) {
			if (ci.getService().getName().equalsIgnoreCase(name)) {
				foundComboItem = ci;
				break;
			}
		}
		return foundComboItem;
	}

//some helper methods 

	
	private static List<ServiceCombo> getCurrentCombos(FlexiBook f){
		ArrayList<ServiceCombo> combos = new ArrayList<ServiceCombo>();
		 for (BookableService combo : f.getBookableServices()) {
				if (combo instanceof ServiceCombo) {
					//System.out.println(combo.getName());
					combos.add((ServiceCombo) combo);
					//System.out.println(((ServiceCombo) combo).getMainService().getService().getName());

				}
		}
		 return combos;
	}

	public static List<TOService> getService() {
		ArrayList<TOService> services = new ArrayList<TOService>();
		for (BookableService service : FlexiBookApplication.getFlexiBook().getBookableServices()) {
			TOService toService = new TOService(service.getName(), 0, 0, 0); // service.getName(),
																				// service.getDuration()?
			services.add(toService);
		}
		return services;
	}


	    /**
	     * Private helper methods
	     * @author Cecile Dai
	     **/
	    private static BusinessHour.DayOfWeek toDayOfWeek(String day){
	        BusinessHour.DayOfWeek weekDay;
	        switch(day){
	            case "Monday":
	                weekDay = BusinessHour.DayOfWeek.Monday;
	                break;
	            case "Tuesday":
	                weekDay = BusinessHour.DayOfWeek.Tuesday;
	                break;
	            case "Wednesday":
	                weekDay = BusinessHour.DayOfWeek.Wednesday;
	                break;
	            case "Thursday":
	                weekDay = BusinessHour.DayOfWeek.Thursday;
	                break;
	            case "Friday":
	                weekDay = BusinessHour.DayOfWeek.Friday;
	                break;
	            case "Saturday":
	                weekDay = BusinessHour.DayOfWeek.Saturday;
	                break;
	            case "Sunday":
	                weekDay = BusinessHour.DayOfWeek.Sunday;
	                break;
	            default:
	                weekDay = null;
	                throw new IllegalArgumentException("Invalid day");
	        }
	        return weekDay;
	    }

	    private static Time toTime(String timeStr) throws Exception {
	        Time time;
	        int hour;
	        int minute;
	        try {
	            hour = Integer.parseInt(timeStr.substring(0,2));
	            minute = Integer.parseInt(timeStr.substring(3,5));
	            time = new Time(hour, minute, 0);
	            java.sql.Time sqlTime = Time.valueOf(String.valueOf(time));
	            return time;
	        }
	        catch(Exception e){
	            throw new IllegalArgumentException("Invalid time input because " + e.getMessage());
	        }
	    }

	    private static java.sql.Date toDate(String dateStr){
	        java.util.Date date;
	        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	        try{
	            date = format.parse(dateStr);
	            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
	            return sqlDate;
	        }
	        catch(Exception e){
	            throw new IllegalArgumentException("Invalid date input" + e.getMessage());
	        }
	    }

	    /**
	     * Method to set up basic business information
	     * @param name Business name
	     * @param address Business address
	     * @param phoneNumber Business phone number
	     * @param email Business email
	     * @author Cecile
	     **/
	    public static void setUpBasicBusinessInfo(String name, String address, String phoneNumber, String email, FlexiBook flexiBook) throws Exception {
	        if(!(FlexiBookApplication.getCurrentUser().getUsername().equals(flexiBook.getOwner().getUsername()))){
	            throw new Exception("No permission to set up business information");
	        }
	        else if (name != null && address != null && phoneNumber != null && (validate(email))) {
	            Business business = new Business(name, address, phoneNumber, email, FlexiBookApplication.getFlexiBook());
	            FlexiBookApplication.getFlexiBook().setBusiness(business);	//newly added

				//saving changes to file
				FlexiBookPersistence.save(FlexiBookApplication.getFlexiBook());
				
	        }
	        else{
	            throw new Exception("Invalid email");
	        }
	    }

	    /**
	     *Method to add business hours
	     * @param Day day of the week
	     * @param newStartTime new start time
	     * @param newEndTime new end time
	     * @param flexiBook the flexibook application
	     * @throws Exception
	     * @author Cecile Dai
	     */

	    public static void addBusinessHours(String Day, String newStartTime, String newEndTime, FlexiBook flexiBook) throws Exception {
	        BusinessHour.DayOfWeek newDay = toDayOfWeek(Day);
	        Time newStartTime1 = toTime(newStartTime);
	        Time newEndTime1 = toTime(newEndTime);
	        if(!(FlexiBookApplication.getCurrentUser().getUsername().equals(flexiBook.getOwner().getUsername()))){
	            throw new Exception("No permission to update business information");
	        }
	        else if(!(businessHourOverlap(newDay, newStartTime1, newEndTime1, false))){
	            throw new Exception("The business hours cannot overlap");
	        }
	        else if(!(endTimeValid(newStartTime1, newEndTime1))){
	            throw new Exception("Start time must be before end time");
	        }
	        else if(Day != null && newStartTime != null && newEndTime != null) {
	            BusinessHour result = new BusinessHour(newDay, newStartTime1, newEndTime1, FlexiBookApplication.getFlexiBook());
	            FlexiBookApplication.getFlexiBook().getBusiness().addBusinessHour(result);

				//saving changes to file
				FlexiBookPersistence.save(FlexiBookApplication.getFlexiBook());
				
	        }
	    }

	    /**
	     * Method to view business information
	     * @author Cecile Dai
	     * @return an object holding the viewable data of the Business
	     */
	    public static TOBusiness viewBasicBusinessInfo() {
	    	TOBusiness infoTO = null;
	    	if(FlexiBookApplication.getFlexiBook().getBusiness() != null) {
	    		Business b = FlexiBookApplication.getFlexiBook().getBusiness();
	    		infoTO = new TOBusiness(b.getName(), b.getAddress(), b.getPhoneNumber(), b.getEmail());
	    	}
	    	else {
	    		infoTO = new TOBusiness("", "", "", "");
	    	}
	        return infoTO;
	    }

	    /**
	     * Query method to view business hours
	     * @return
	     * @author Cecile Dai
	     * @throws Exception 
	     */
	    public static ArrayList<TOBusinessHour> viewBusinessHours(){
	        ArrayList<TOBusinessHour> businessHours = new ArrayList<TOBusinessHour>();
	        
	        if(FlexiBookApplication.getFlexiBook().getBusiness() == null) {
//	    		FlexiBookApplication.getFlexiBook().setBusiness(new Business(name, address, phoneNumber, email, FlexiBookApplication.getFlexiBook()));
//	        	setUpBasicBusinessInfo("", "", "", "", FlexiBookApplication.getFlexiBook());	//newly added
	        	FlexiBookApplication.getFlexiBook().setBusiness(new Business("", "", "", "", FlexiBookApplication.getFlexiBook()));
//	        	System.out.println("business is null");
	        }
	        
	        for(BusinessHour bh: FlexiBookApplication.getFlexiBook().getBusiness().getBusinessHours()){
	        	BusinessHour.DayOfWeek day = bh.getDayOfWeek();
	        	TOBusinessHour.DayOfWeek weekDay = null;
	        	switch(day){
	            case Monday:
	                weekDay = TOBusinessHour.DayOfWeek.Monday;
	                break;
	            case Tuesday:
	                weekDay = TOBusinessHour.DayOfWeek.Tuesday;
	                break;
	            case Wednesday:
	                weekDay = TOBusinessHour.DayOfWeek.Wednesday;
	                break;
	            case Thursday:
	                weekDay = TOBusinessHour.DayOfWeek.Thursday;
	                break;
	            case Friday:
	                weekDay = TOBusinessHour.DayOfWeek.Friday;
	                break;
	            case Saturday:
	                weekDay = TOBusinessHour.DayOfWeek.Saturday;
	                break;
	            case Sunday:
	                weekDay = TOBusinessHour.DayOfWeek.Sunday;
	                break;
	        	}
	        	
	            TOBusinessHour TObh = new TOBusinessHour(weekDay, bh.getStartTime(), bh.getEndTime());
	            businessHours.add(TObh);
	        }
	        return businessHours;
	    }
	    
	    /**
	     * Query method to view holidays
	     * @return
	     * @author Cecile Dai
	     * @throws Exception 
	     */
	    public static ArrayList<TOTimeSlot> viewHolidays(){
	    	
	    	if(FlexiBookApplication.getFlexiBook().getBusiness() == null) {
//	    		FlexiBookApplication.getFlexiBook().setBusiness(new Business(name, address, phoneNumber, email, FlexiBookApplication.getFlexiBook()));
//	        	setUpBasicBusinessInfo("", "", "", "", FlexiBookApplication.getFlexiBook());	//newly added
	        	FlexiBookApplication.getFlexiBook().setBusiness(new Business("", "", "", "", FlexiBookApplication.getFlexiBook()));
//	        	System.out.println("business is null");
	        }
	    	
	    	ArrayList<TOTimeSlot> holidays = new ArrayList<TOTimeSlot>();
	    	if(FlexiBookApplication.getFlexiBook().getBusiness() != null) {
	    		for(TimeSlot ts: FlexiBookApplication.getFlexiBook().getBusiness().getHolidays()) {
	    			TOTimeSlot TOts = new TOTimeSlot(ts.getStartDate(), ts.getStartTime(), ts.getEndDate(), ts.getEndTime());
	    			holidays.add(TOts);
	    		}
	    	}
	    	return holidays;
	    }
	    
	    /**
	     * Query method to view vacations
	     * @return
	     * @author Cecile Dai
	     */
	    public static ArrayList<TOTimeSlot> viewVacations(){
	    	
	    	if(FlexiBookApplication.getFlexiBook().getBusiness() == null) {
//	    		FlexiBookApplication.getFlexiBook().setBusiness(new Business(name, address, phoneNumber, email, FlexiBookApplication.getFlexiBook()));
//	        	setUpBasicBusinessInfo("", "", "", "", FlexiBookApplication.getFlexiBook());	//newly added
	        	FlexiBookApplication.getFlexiBook().setBusiness(new Business("", "", "", "", FlexiBookApplication.getFlexiBook()));
//	        	System.out.println("business is null");
	        }
	    	
	    	ArrayList<TOTimeSlot> vacations = new ArrayList<TOTimeSlot>();
	    	if(FlexiBookApplication.getFlexiBook().getBusiness() != null) {
	    	for(TimeSlot ts: FlexiBookApplication.getFlexiBook().getBusiness().getVacation()) {
	    		TOTimeSlot TOts = new TOTimeSlot(ts.getStartDate(), ts.getStartTime(), ts.getEndDate(), ts.getEndTime());
	    		vacations.add(TOts);
	    	}
	    	}
	    	return vacations;
	    }


	    /**
	     * @param type timeslot type - vacation or holiday
	     * @param startDate start date
	     * @param startTime start time
	     * @param endDate end date
	     * @param endTime end time
	     * @throws Exception
	     * @author Cecile Dai
	     */
	    public static void addNewTimeSlot(String type, String startDate, String startTime, String endDate, String endTime) throws Exception {
	        Date startDate1 = toDate(startDate);
	        Time startTime1 = toTime(startTime);
	        Date endDate1 = toDate(endDate);
	        Time endTime1 = toTime(endTime);
	        if(!(FlexiBookApplication.getCurrentUser().getUsername().equals(FlexiBookApplication.getFlexiBook().getOwner().getUsername()))){
	            throw new Exception("No permission to update business information");
	        }
	        else if (!(endDateValid(startDate1, endDate1)) || (startDate1.equals(endDate1) && !endTimeValid(startTime1, endTime1))) {
	            throw new Exception("Start time must be before end time");
	        }
	        else if (!(isValidDate(startDate1, startTime1))) {
	            if (type.equals("vacation")) {
	                throw new Exception("Vacation cannot start in the past");
	            } else if (type.equals("holiday")) {
	                throw new Exception("Holiday cannot start in the past");
	            }
	        }
	        else if(!(datesOverlap(type, startDate1, startTime1, endDate1, endTime1, false))){
	            if (type.equals("vacation")) {
	                throw new Exception("Vacation times cannot overlap");
	            } else if (type.equals("holiday")) {
	                throw new Exception("Holiday times cannot overlap");
	            }
	        }
	        else if(!(VacaHoliOverlap(type, startDate1, startTime1, endDate1, endTime1))){
	            throw new Exception("Holiday and vacation times cannot overlap");
	        }
	        else {
	            TimeSlot timeSlot = new TimeSlot(startDate1, startTime1, endDate1, endTime1, FlexiBookApplication.getFlexiBook());
	            if (type.equals("vacation")) {
	                FlexiBookApplication.getFlexiBook().getBusiness().addVacation(timeSlot);
	            }
	            if (type.equals("holiday")) {
	                FlexiBookApplication.getFlexiBook().getBusiness().addHoliday(timeSlot);
	            }

				//saving changes to file
				FlexiBookPersistence.save(FlexiBookApplication.getFlexiBook());
				
	        }
	    }

	    /**
	     * Private helper method
	     * @param startTime
	     * @param endTime
	     * @return a boolean representing if the end time comes after the start time
	     * @author Cecile Dai
	     */
	    private static boolean endTimeValid(Time startTime, Time endTime) {
	        if (startTime.compareTo(endTime) > 0) {
	            return false;
	        } else return true;
	    }

	    /**
	     * Private helper method
	     * @param startDate start date
	     * @param endDate end date
	     * @return a boolean representing if start date is before the end date
	     * @author Cecile Dai
	     */
	    private static boolean endDateValid(Date startDate, Date endDate){
	        if(startDate.compareTo(endDate) > 0){
	            return false;
	        }
	        else return true;
	    }

	   
	    
	    /**
	     * Private helper method
	     * @param type vacation or holiday
	     * @param startDate
	     * @param startTime
	     * @param endDate
	     * @param endTime
	     * @return a boolean checking if the type of timeslot overlaps with any others in its list of types
	     * @author Cecile Dai
	     */
	    private static boolean datesOverlap(String type, Date startDate, Time startTime, Date endDate, Time endTime, boolean isUpdate) {
//	        for(TimeSlot ts: FlexiBookApplication.getFlexiBook().getBusiness().getHolidays()) {
//	        	System.out.println("Holidays: " + ts.getStartDate().toString());
//	        }
//	        for(TimeSlot ts: FlexiBookApplication.getFlexiBook().getBusiness().getVacation()) {
//	        	System.out.println("Vacations: " + ts.getStartDate().toString());
//	        }
	    	boolean isValid = false;
	    	
//	    	if(type.equals("vacation") && FlexiBookApplication.getFlexiBook().getBusiness().getVacation().size() == 0){
//	            isValid = true;
//	        }
//	        if(type.equals("holiday") && FlexiBookApplication.getFlexiBook().getBusiness().getHolidays().size() == 0){
//	            isValid = true;
//	        }
//	        else if (type.equals("vacation")) {
//	            for (TimeSlot timeSlot : FlexiBookApplication.getFlexiBook().getBusiness().getVacation()) {
//	                if ((timeSlot.getStartDate().compareTo(endDate) > 0) || (timeSlot.getEndDate().compareTo(startDate) < 0)) {
//	                    isValid = true;
//	                }
//	                else return false;
//	            }
//	        }
//	        else if (type.equals("holiday")) {
//	            for (TimeSlot timeSlot : FlexiBookApplication.getFlexiBook().getBusiness().getHolidays()) {
//	                if ((timeSlot.getStartDate().compareTo(endDate) > 0) || (timeSlot.getEndDate().compareTo(startDate) < 0)) {
//	                    return true;
//	                }
//	                else return false;
//	            }
//	        }
	    	
	    	int hcounter = 0;
	    	int vcounter = 0;
	    	
	    	if(type.equals("vacation") && FlexiBookApplication.getFlexiBook().getBusiness().getVacation().size() == 0){
	            isValid = true;
	        }
	        if(type.equals("holiday") && FlexiBookApplication.getFlexiBook().getBusiness().getHolidays().size() == 0){
	            isValid = true;
	        }
	        if(isUpdate == false) {
	        	if (type.equals("vacation")) {
	        		for (TimeSlot timeSlot : FlexiBookApplication.getFlexiBook().getBusiness().getVacation()) {
	        			if ((timeSlot.getStartDate().compareTo(endDate) > 0) || (timeSlot.getEndDate().compareTo(startDate) < 0)) {
	                		vcounter++;
	                	}
	        		}
	        		if(vcounter == FlexiBookApplication.getFlexiBook().getBusiness().getVacation().size()) {
	        			isValid = true;
	        		}
	        	}
	        	else if (type.equals("holiday")) {
	        		for (TimeSlot timeSlot : FlexiBookApplication.getFlexiBook().getBusiness().getHolidays()) {
	        			if ((timeSlot.getStartDate().compareTo(endDate) > 0) || (timeSlot.getEndDate().compareTo(startDate) < 0)) {
	        				hcounter++;
	        			}
	        		}
	        		if(hcounter == FlexiBookApplication.getFlexiBook().getBusiness().getHolidays().size()) {
	        			isValid = true;
	        		}
	        	}
	        }
	        else {
	        	if(type.equals("vacation")) {
	        		for (TimeSlot timeSlot : FlexiBookApplication.getFlexiBook().getBusiness().getVacation()) {
	        			if(startDate.compareTo(timeSlot.getStartDate()) != 0 && startTime.compareTo(timeSlot.getStartTime()) != 0
	        					&& endDate.compareTo(timeSlot.getEndTime()) != 0 && endTime.compareTo(timeSlot.getEndTime()) != 0) {
	        				if ((timeSlot.getStartDate().compareTo(endDate) > 0) || (timeSlot.getEndDate().compareTo(startDate) < 0)) {
	        					vcounter++;
	        				}
	        			}
	        		}
	        		if(vcounter == FlexiBookApplication.getFlexiBook().getBusiness().getVacation().size()) {
	        			isValid = true;
	        		}
	        	}
	        	else if(type.equals("holiday")) {
	        		for (TimeSlot timeSlot : FlexiBookApplication.getFlexiBook().getBusiness().getVacation()) {
	        			if(startDate.compareTo(timeSlot.getStartDate()) != 0 && startTime.compareTo(timeSlot.getStartTime()) != 0
	        					&& endDate.compareTo(timeSlot.getEndTime()) != 0 && endTime.compareTo(timeSlot.getEndTime()) != 0) {
	        				if ((timeSlot.getStartDate().compareTo(endDate) > 0) || (timeSlot.getEndDate().compareTo(startDate) < 0)) {
	        					hcounter++;
	        				}
	        			}
	        		}
	        		if(hcounter == FlexiBookApplication.getFlexiBook().getBusiness().getHolidays().size()) {
	        			isValid = true;
	        		}
	        	}
	        }
	        
	        return isValid;
	    }

	   
	    /**
	     * Private helper method
	     * @param type
	     * @param startDate
	     * @param startTime
	     * @param endDate
	     * @param endTime
	     * @return a boolean checking if vacations overlap with holidays
	     * @author Cecile Dai
	     */
	    private static boolean VacaHoliOverlap(String type, Date startDate, Time startTime, Date endDate, Time endTime) {
//	    	if(!datesOverlap(type, startDate, startTime, endDate, endTime)) {
//	    		return false;
//	    	}
	    	
//	    	for(TimeSlot ts: FlexiBookApplication.getFlexiBook().getBusiness().getHolidays()) {
//	        	System.out.println("Holidays: " + ts.getStartDate().toString());
//	        }
//	        for(TimeSlot ts: FlexiBookApplication.getFlexiBook().getBusiness().getVacation()) {
//	        	System.out.println("Vacations: " + ts.getStartDate().toString());
//	        }
	        
	    	if(FlexiBookApplication.getFlexiBook().getBusiness().getVacation().size() == 0 && type.equals("holiday")){
	            return true;
	        }
	    	else if(FlexiBookApplication.getFlexiBook().getBusiness().getHolidays().size() == 0 && type.equals("vacation")){
	            return true;
	        }
//	    	else if (FlexiBookApplication.getFlexiBook().getBusiness().getHolidays().size() == 0 && FlexiBookApplication.getFlexiBook().getBusiness().getVacation().size() == 0) {
//	    		return true;
//	    	}
	        else if(type.equals("vacation")){
	            for(TimeSlot timeSlotH: FlexiBookApplication.getFlexiBook().getBusiness().getHolidays()){
	                if(timeSlotH.getStartDate().compareTo(endDate) > 0 || timeSlotH.getEndDate().compareTo(startDate) < 0){
	                    return true;
	                }
	                else return false;
	            }
	        }
	        else if(type.equals("holiday")){
	            for(TimeSlot timeSlotV: FlexiBookApplication.getFlexiBook().getBusiness().getVacation()){
	                if(timeSlotV.getStartDate().compareTo(endDate) > 0 || timeSlotV.getEndDate().compareTo(startDate) < 0){
	                    return true;
	                }
	                else return false;
	            }
	        }
	        return false;
	    }

	    
	    /**
	     * Private helper method
	     * @param day
	     * @param newStartTime
	     * @param newEndTime
	     * @return a boolean checking if business hours overlap
	     * @author Cecile Dai
	     */
	    private static boolean businessHourOverlap(BusinessHour.DayOfWeek day, Time newStartTime, Time newEndTime, boolean isUpdate) {
//	        for (BusinessHour businessHour : FlexiBookApplication.getFlexiBook().getBusiness().getBusinessHours()) {
//	            if (!((businessHour.getStartTime().compareTo(newStartTime)) > 0 && (businessHour.getEndTime().compareTo(newEndTime)) > 0)) {
//	                if ((businessHour.getDayOfWeek().equals(day))) {
//	                    return false;
//	                }
//	            } else if (!((businessHour.getStartTime().compareTo(newStartTime)) < 0 && (businessHour.getEndTime().compareTo(newEndTime)) < 0)) {
//	                if ((businessHour.getDayOfWeek().equals(day))) {
//	                    return false;
//	                }
//	            }
//	        }
//	        return true;
	    	
	    	boolean isValid = true;
	    	int counter = 0;
	    	
	    	if(isUpdate != true) {
	    	for(BusinessHour businessHour : FlexiBookApplication.getFlexiBook().getBusiness().getBusinessHours()) {
	    		if(businessHour.getDayOfWeek().equals(day)) {
	    			if(newStartTime.compareTo(businessHour.getEndTime()) >= 0 || newEndTime.compareTo(businessHour.getStartTime()) <= 0) {
//	    				isValid = true;
	    				break;
	    			}
	    			else {
	    				isValid = false;
	    			}
	    		}
	    	}
	    	}
	    	else {
	    		for(BusinessHour businessHour : FlexiBookApplication.getFlexiBook().getBusiness().getBusinessHours()) {
		    		if(businessHour.getDayOfWeek().equals(day) && (newStartTime.compareTo(businessHour.getStartTime()) != 0
		    				&& newEndTime.compareTo(businessHour.getEndTime()) != 0)) {
		    			if(newStartTime.compareTo(businessHour.getEndTime()) >= 0 || newEndTime.compareTo(businessHour.getStartTime()) <= 0) {
//		    				isValid = true;
		    				break;
		    			}
		    			else {
		    				isValid = false;
		    			}
		    		}
		    	}
	    	}
	    	return isValid;
	    	
	    }

	    /**
	     * Private helper method
	     * @param startDate
	     * @param startTime
	     * @return a boolean representing if the relevant date is not in the past
	     * @throws Exception
	     * @author Cecile Dai
	     */
	    public static boolean isValidDate(Date startDate, Time startTime) throws Exception {
//	        Date currentDate = toDate("2020-06-01");
//	        Time currentTime = toTime("11:00");
	    	
	    	Date currentDate = SystemTime.getDate();
	    	Time currentTime = SystemTime.getTime();
	    	
	        if(startDate.compareTo(currentDate) < 0){
	            return false;
	        }
	        else if(startDate.compareTo(currentDate) > 0){
	            return true;
	        }
	        else if(startDate.compareTo(currentDate) == 0){
	            if(startTime.compareTo(currentTime) >= 0){
	                return true;
	            }
	            else return false;
	        }
	        return false;
	    }

	    /**
	     * Private helper method checking if the email address is valid
	     */
	    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
	            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

	    private static boolean validate(String emailStr) {
	        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
	        return matcher.find();
	    }

	    /**
	     *Method to update basic business information
	     * @param name Business name
	     * @param address Business address
	     * @param phoneNumber Business phone number
	     * @param email Business email
	     * @throws Exception
	     * @author Cecile Dai
	     */
	    public static void updateBasicBusinessInfo(String name, String address, String phoneNumber, String email) throws Exception {
	    	
	    	//for test
	    	
	    	if(FlexiBookApplication.getFlexiBook() == null) {
	    		System.out.println("flexibook is null");
	    	}
	    	
	    	if(FlexiBookApplication.getFlexiBook().getBusiness() == null) {
	    		FlexiBookApplication.getFlexiBook().setBusiness(new Business(name, address, phoneNumber, email, FlexiBookApplication.getFlexiBook()));
	    	}
	    	
	    	if(FlexiBookApplication.getCurrentUser() == null) {
	    		FlexiBookApplication.setCurrentUser(new Owner("owner", "owner", FlexiBookApplication.getFlexiBook()));
	    	}
	    	
	        if (!FlexiBookApplication.getCurrentUser().getUsername().equals("owner")) {
	            throw new Exception("No permission to update business information");
	        }
	        if (!validate(email)) {
	            throw new Exception("Invalid email");
	        } else if (name != null && address != null && phoneNumber != null && email != null) {
	            FlexiBookApplication.getFlexiBook().getBusiness().setName(name);
	            FlexiBookApplication.getFlexiBook().getBusiness().setAddress(address);
	            FlexiBookApplication.getFlexiBook().getBusiness().setPhoneNumber(phoneNumber);
	            FlexiBookApplication.getFlexiBook().getBusiness().setEmail(email);

				//saving changes to file
				FlexiBookPersistence.save(FlexiBookApplication.getFlexiBook());
				
	        }
	    }

	    /**
	     *Method to add new business hours
	     * @param Day day of the week
	     * @param newStartTime new start time
	     * @param newEndTime new end time
	     * @throws Exception
	     * @author Cecile Dai
	     */
	    public static void AddNewBusinessHours(String Day, String newStartTime, String newEndTime) throws Exception {
	    	
	    	//for test
	    	
	    	if(FlexiBookApplication.getFlexiBook().getBusiness() == null) {
//	    		FlexiBookApplication.getFlexiBook().setBusiness(new Business(name, address, phoneNumber, email, FlexiBookApplication.getFlexiBook()));
	    		System.out.println("business is null");
	    	}
	    	
	    	if(FlexiBookApplication.getCurrentUser() == null) {
	    		FlexiBookApplication.setCurrentUser(new Owner("owner", "owner", FlexiBookApplication.getFlexiBook()));
	    	}
	    	
	        BusinessHour.DayOfWeek weekDay = toDayOfWeek(Day);
	        Time newStartTime1 = toTime(newStartTime);
	        Time newEndTime1 = toTime(newEndTime);
	        if (!FlexiBookApplication.getCurrentUser().getUsername().equals("owner")) {
	            throw new Exception("No permission to update business information");
	        }
	        if (!businessHourOverlap(weekDay, newStartTime1, newEndTime1, false)) {
	            throw new Exception("The business hours cannot overlap");
	        } else if (weekDay != null && newStartTime != null && newEndTime != null) {
	        	BusinessHour bh = new BusinessHour(weekDay, newStartTime1, newEndTime1, FlexiBookApplication.getFlexiBook());
	        	FlexiBookApplication.getFlexiBook().getBusiness().addBusinessHour(bh);

				//saving changes to file
				FlexiBookPersistence.save(FlexiBookApplication.getFlexiBook());
				
	        }
	    }

	    /**
	     *Method to update business hours
	     * @param oldDay old business hour weekday
	     * @param oldTime old business hour time
	     * @param Day new weekday
	     * @param newStartTime new start time
	     * @param newEndTime new end time
	     * @throws Exception
	     * @author Cecile Dai
	     */
	    public static void updateBusinessHours(String oldDay, String oldTime, String Day, String newStartTime, String newEndTime) throws Exception {
	        
	    	//for test
	    	
	    	if(FlexiBookApplication.getFlexiBook().getBusiness() == null) {
//	    		FlexiBookApplication.getFlexiBook().setBusiness(new Business(name, address, phoneNumber, email, FlexiBookApplication.getFlexiBook()));
	    	}
	    	
	    	if(FlexiBookApplication.getCurrentUser() == null) {
	    		FlexiBookApplication.setCurrentUser(new Owner("owner", "owner", FlexiBookApplication.getFlexiBook()));
	    	}
	    	
	    	
	    	BusinessHour.DayOfWeek weekOldDay = toDayOfWeek(oldDay);
	        Time oldTime1 = toTime(oldTime);
	        BusinessHour.DayOfWeek weekNewDay = toDayOfWeek(Day);
	        Time newStartTime1 = toTime(newStartTime);
	        Time newEndTime1 = toTime(newEndTime);
	        if (!FlexiBookApplication.getCurrentUser().getUsername().equals("owner")) {
	            throw new Exception("No permission to update business information");
	        }
	        if (!businessHourOverlap(weekNewDay, newStartTime1, newEndTime1, true)) {
	            throw new Exception("The business hours cannot overlap");
	        }
	        if (!endTimeValid(newStartTime1, newEndTime1)) {
	            throw new Exception("Start time must be before end time");
	        }
	        else if (weekOldDay != null && oldTime1 != null && weekNewDay != null && newStartTime1 != null && newEndTime1 != null) {
	            Business bs = FlexiBookApplication.getFlexiBook().getBusiness();
	            if(bs.getBusinessHours().size() == 0){
	                BusinessHour newHour = new BusinessHour(weekNewDay, newStartTime1, newEndTime1, FlexiBookApplication.getFlexiBook());
	                bs.addBusinessHour(newHour);
	            }
	            for (BusinessHour businessHour : bs.getBusinessHours()) {
	                if (businessHour.getDayOfWeek().equals(weekOldDay) && businessHour.getStartTime().equals(oldTime1)) {
	                    BusinessHour newHour = new BusinessHour(weekNewDay, newStartTime1, newEndTime1, FlexiBookApplication.getFlexiBook());
	                    bs.removeBusinessHour(businessHour);
	                    bs.addBusinessHour(newHour);
	                    break;
	                }
	            }

				//saving changes to file
				FlexiBookPersistence.save(FlexiBookApplication.getFlexiBook());
				
	        }
	    }

	    /**
	     *Method to remove business hours
	     * @param Day day of the week
	     * @param time business hour start time
	     * @throws Exception
	     * @author Cecile Dai
	     */
	    public static void removeBusinessHours(String Day, String time) throws Exception {
	        
	    	//for test
	    	
	    	if(FlexiBookApplication.getFlexiBook().getBusiness() == null) {
//	    		FlexiBookApplication.getFlexiBook().setBusiness(new Business(name, address, phoneNumber, email, FlexiBookApplication.getFlexiBook()));
	    	}
	    	
	    	if(FlexiBookApplication.getCurrentUser() == null) {
	    		FlexiBookApplication.setCurrentUser(new Owner("owner", "owner", FlexiBookApplication.getFlexiBook()));
	    	}
	    	
	    	if (!FlexiBookApplication.getCurrentUser().getUsername().equals("owner")) {
	            throw new Exception("No permission to update business information");
	        } else {
	            BusinessHour.DayOfWeek weekDay = toDayOfWeek(Day);
	            Time time1 = toTime(time);
	            Business bs = FlexiBookApplication.getFlexiBook().getBusiness();
	            
	            System.out.println("size is : " + bs.getBusinessHours().size());
	                System.out.println(".getHours has: " + FlexiBookApplication.getFlexiBook().getHours());
	            for (BusinessHour businessHour : bs.getBusinessHours()) {
	                boolean passed = false;
	                
	                if (businessHour.getDayOfWeek().equals(weekDay) && businessHour.getStartTime().equals(time1)) {
	                    bs.removeBusinessHour(businessHour);
	                    passed = true;
	                    System.out.println("bh successfully removed");
	                }
	            }

				//saving changes to file
				FlexiBookPersistence.save(FlexiBookApplication.getFlexiBook());
				
	        }
	    }

	    /**
	     *Method to update vacation
	     * @param type vacation
	     * @param oldDate old vacation start date
	     * @param oldTime old vacation start time
	     * @param newStartDate new start date
	     * @param newStartTime new start time
	     * @param newEndDate new end date
	     * @param newEndTime new end time
	     * @throws Exception
	     * @author Cecile Dai
	     */
	    public static void updateVacation(String type, String oldDate, String oldTime, String newStartDate,
	                                      String newStartTime, String newEndDate, String newEndTime) throws Exception {
	        Business bs = FlexiBookApplication.getFlexiBook().getBusiness();
	        Date oldDate1 = toDate(oldDate);
	        Time oldTime1 = toTime(oldTime);
	        Date newStartDate1 = toDate(newStartDate);
	        Time newStartTime1 = toTime(newStartTime);
	        Date newEndDate1 = toDate(newEndDate);
	        Time newEndTime1 = toTime(newEndTime);
	        if (!FlexiBookApplication.getCurrentUser().getUsername().equals("owner")) {
	            throw new Exception("No permission to update business information");
	        }
	        else if (!VacaHoliOverlap(type, newStartDate1, newStartTime1, newEndDate1, newEndTime1)) {
	            throw new Exception("Holiday and vacation times cannot overlap");
	        }
	        else if (!(endDateValid(newStartDate1, newEndDate1)) || (newStartDate1.equals(newEndDate1) && !endTimeValid(newStartTime1, newEndTime1))) {
	            throw new Exception("Start time must be before end time");
	        }
	        else if (!isValidDate(newStartDate1, newStartTime1)) {
	            throw new Exception("Vacation cannot start in the past");
	        } else {
	            for (TimeSlot timeSlot : bs.getVacation()) {
	                boolean passed = false;
	                if (timeSlot.getStartDate().equals(oldDate1) && timeSlot.getStartTime().equals(oldTime1)) {
	                    bs.removeVacation(timeSlot);
	                    TimeSlot updatedVacation = new TimeSlot(newStartDate1, newStartTime1, newEndDate1, newEndTime1, FlexiBookApplication.getFlexiBook());
	                    bs.addVacation(updatedVacation);
	                    passed = true;
	                    break;
	                }
	            }

				//saving changes to file
				FlexiBookPersistence.save(FlexiBookApplication.getFlexiBook());
				
	        }
	    }

	    /**
	     *Method to update holidays
	     * @param type holiday
	     * @param oldDate old holiday start date
	     * @param oldTime old holiday start time
	     * @param newStartDate new start date
	     * @param newStartTime new start time
	     * @param newEndDate new end date
	     * @param newEndTime new end time
	     * @throws Exception
	     * @author Cecile Dai
	     */
	    public static void updateHoliday(String type, String oldDate, String oldTime, String newStartDate,
	                                     String newStartTime, String newEndDate, String newEndTime) throws Exception {
	        Business bs = FlexiBookApplication.getFlexiBook().getBusiness();
	        java.sql.Date oldDate1 = toDate(oldDate);
	        Time oldTime1 = toTime(oldTime);
	        java.sql.Date newStartDate1 = toDate(newStartDate);
	        Time newStartTime1 = toTime(newStartTime);
	        java.sql.Date newEndDate1 = toDate(newEndDate);
	        Time newEndTime1 = toTime(newEndTime);
	        if (!FlexiBookApplication.getCurrentUser().getUsername().equals("owner")) {
	            throw new Exception("No permission to update business information");
	        }
	        else if (!VacaHoliOverlap(type, newStartDate1, newStartTime1, newEndDate1, newEndTime1)) {
	            throw new Exception("Holiday and vacation times cannot overlap");
	        }
	        else if (!(endDateValid(newStartDate1, newEndDate1)) || (newStartDate1.equals(newEndDate1) && !endTimeValid(newStartTime1, newEndTime1))) {
	            throw new Exception("Start time must be before end time");
	        }
	        else if (!isValidDate(newStartDate1, newStartTime1)) {
	            throw new Exception("Holiday cannot be in the past");
	        } else {
	            for (TimeSlot timeSlot : bs.getHolidays()) {
	                if (timeSlot.getStartDate().equals(oldDate1) && timeSlot.getStartTime().equals(oldTime1)) {
	                    bs.removeHoliday(timeSlot);
	                    TimeSlot updatedHoliday = new TimeSlot(newStartDate1, newStartTime1, newEndDate1, newEndTime1, FlexiBookApplication.getFlexiBook());
	                    bs.addHoliday(updatedHoliday);
	                    break;
	                }
	            }

				//saving changes to file
				FlexiBookPersistence.save(FlexiBookApplication.getFlexiBook());
				
	        }
	    }

	    /**
	     *Method to remove timeslots from vacation or holiday
	     * @param type holiday or vacation
	     * @param startDate start date
	     * @param startTime start time
	     * @param endDate end date
	     * @param endTime end time
	     * @throws Exception
	     * @author Cecile Dai
	     */
	    public static void removeTimeSlot(String type, String startDate, String startTime, String endDate, String endTime) throws Exception {
	        if (!FlexiBookApplication.getCurrentUser().getUsername().equals("owner")) {
	            throw new Exception("No permission to update business information");
	        }
	        else{
	            Business bs = FlexiBookApplication.getFlexiBook().getBusiness();
	            java.sql.Date startDate1 = toDate(startDate);
	            Time startTime1 = toTime(startTime);
	            java.sql.Date endDate1 = toDate(endDate);
	            Time endTime1 = toTime(endTime);
	            if(type.equals("vacation")) {
	                for (TimeSlot timeSlot : bs.getVacation()) {
	                    if (timeSlot.getStartDate().equals(startDate1) && timeSlot.getStartTime().equals(startTime1) && timeSlot.getEndDate().equals(endDate1)
	                            && timeSlot.getEndTime().equals(endTime1)) {
	                        bs.removeVacation(timeSlot);
	                        break;
	                    }
	                }
	            }
	            else if(type.equals("holiday")) {
	                for (TimeSlot timeSlot : bs.getHolidays()) {
	                    if (timeSlot.getStartDate().equals(startDate1) && timeSlot.getStartTime().equals(startTime1) && timeSlot.getEndDate().equals(endDate1)
	                            && timeSlot.getEndTime().equals(endTime1)) {
	                        bs.removeHoliday(timeSlot);
	                        break;
	                    }
	                }
	            }

				//saving changes to file
				FlexiBookPersistence.save(FlexiBookApplication.getFlexiBook());
				
	        }
	    }
}

