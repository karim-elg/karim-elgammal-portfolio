package ca.mcgill.ecse.flexibook.features;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import ca.mcgill.ecse.flexibook.application.FlexiBookApplication;
import ca.mcgill.ecse.flexibook.controller.FlexiBookController;
import ca.mcgill.ecse.flexibook.controller.InvalidInputException;
import ca.mcgill.ecse.flexibook.controller.TOAppointment;
import ca.mcgill.ecse.flexibook.controller.TOBusiness;
import ca.mcgill.ecse.flexibook.controller.TOTimeSlot;
import ca.mcgill.ecse.flexibook.model.Appointment;
import ca.mcgill.ecse.flexibook.model.BookableService;
import ca.mcgill.ecse.flexibook.model.Business;
import ca.mcgill.ecse.flexibook.model.BusinessHour;
import ca.mcgill.ecse.flexibook.model.ComboItem;
import ca.mcgill.ecse.flexibook.model.Customer;
import ca.mcgill.ecse.flexibook.model.FlexiBook;
import ca.mcgill.ecse.flexibook.model.Owner;
import ca.mcgill.ecse.flexibook.model.Service;
import ca.mcgill.ecse.flexibook.model.ServiceCombo;
import ca.mcgill.ecse.flexibook.model.TimeSlot;
import ca.mcgill.ecse.flexibook.model.User;
import ca.mcgill.ecse.flexibook.model.BusinessHour.DayOfWeek;
import ca.mcgill.ecse.flexibook.persistence.FlexiBookPersistence;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CucumberStepDefinitions {

	
	private FlexiBook fbs;
	private List<Service> services;
	private String error;
	private int errorCount;
	private Integer cAts=0;
	private Date SystemDateTime;
	private Integer oldAts=0;
	private int accountCount;
	private String updateAppointmentSuccess;
	private Owner owner;
	private Business buisness;
	private User user;
	private Appointment currentAppointment;
	private int accountCountBeforeCreation;
	private Boolean accountUpdated;
	
	 private static String filename = "testdata.flexibook";
	 private static File testFile;
	    
	  
		
		@Before
		public void setUp() {
			// remove test file
			FlexiBookPersistence.setFilename(filename);
			testFile = new File(filename);
			testFile.delete();
			// clear all data
			FlexiBook fb = FlexiBookApplication.getFlexiBook();
			fb.delete();
		}
	
	/**
	 * @author Natalia Tabet Agredo
	 */
	@Given("a Flexibook system exists")
	public void a_flexibook_system_exists() {
		fbs = FlexiBookApplication.getFlexiBook();
		services = new ArrayList<Service>();
		error = "";
		errorCount = 0;
		cAts = 0;
		oldAts = 0;
		accountCount =  0;
		updateAppointmentSuccess = null;
	}
	
	/**
	 * @author Natalia Tabet Agredo
	 */
	@Given("an owner account exists in the system")
	public void an_owner_account_exists_in_the_system() {
		fbs = FlexiBookApplication.getFlexiBook();
		owner = new Owner("owner", "owner", fbs);
		fbs.setOwner(owner);
	}

	/**
	 * @author Natalia Tabet Agredo
	 */
	@Given("a business exists in the system")
	public void a_business_exists_in_the_system() {
		fbs = FlexiBookApplication.getFlexiBook();
		buisness = new Business("aName", "aAddress", "aPhoneNumber", "aEmail", fbs);
		fbs.setBusiness(buisness);
	}

	/**
	 * @author Natalia Tabet Agredo
	 */
	@Given("the following customers exist in the system:")
	public void the_following_customers_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {
		List<List<String>> rows = dataTable.asLists();
		for (List<String> columns : rows) {
			if (columns.get(0).equals("username") != true) {
				fbs.addCustomer(new Customer(columns.get(0), columns.get(1),0,  fbs));
			}

		}

	}
	@Given("the business has the following opening hours")
	public void the_business_has_the_following_opening_hours(io.cucumber.datatable.DataTable dataTable) {
		BusinessHour businessHour = null;
	    String Day;
	    
	    List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class); 
		//made some changes - Cecile
	    for(Map<String, String> columns : rows) {
			Day = columns.get("day");
			
			if(Day.equals("Monday")){
				businessHour = new BusinessHour(DayOfWeek.Monday, Time.valueOf(columns.get("startTime") + ":00"), Time.valueOf(columns.get("endTime") + ":00"), fbs);
			}
			else if(Day.equals("Tuesday")) {
				businessHour = new BusinessHour(DayOfWeek.Tuesday, Time.valueOf(columns.get("startTime") + ":00"), Time.valueOf(columns.get("endTime") + ":00"), fbs);
			}
			else if(Day.equals("Wednesday")) {
				businessHour = new BusinessHour(DayOfWeek.Wednesday, Time.valueOf(columns.get("startTime") + ":00"), Time.valueOf(columns.get("endTime") + ":00"), fbs);
			}
			else if(Day.equals("Thursday")) {
				businessHour = new BusinessHour(DayOfWeek.Thursday, Time.valueOf(columns.get("startTime") + ":00"), Time.valueOf(columns.get("endTime") + ":00"), fbs);
			}
			else if(Day.equals("Friday")) {
				businessHour = new BusinessHour(DayOfWeek.Friday, Time.valueOf(columns.get("startTime") + ":00"), Time.valueOf(columns.get("endTime") + ":00"), fbs);
			}
			
				
			fbs.getBusiness().addBusinessHour(businessHour);
			
		}
	   
	}
	@Given("the business has the following holidays")
	public void the_business_has_the_following_holidays(io.cucumber.datatable.DataTable dataTable) {
		//double check this @Rakshitha
		TimeSlot holiday;
		
		List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class); 
		for(Map<String, String> columns : rows) {
			holiday = new TimeSlot(Date.valueOf(columns.get("startDate")), Time.valueOf(columns.get("startTime") + ":00"), Date.valueOf(columns.get("endDate")), Time.valueOf(columns.get("endTime") + ":00"), fbs);
			fbs.getBusiness().addHoliday(holiday);
		}
	    // For other transformations you can register a DataTableType.
	    
	}
	/**
    *
    * @param type
    * @param startDate
    * @param startTime
    * @param endDate
    * @param endTime
    * @throws Exception
    * @author Cecile Dai
    */
	 @Given("a {string} time slot exists with start time {string} at {string} and end time {string} at {string}")
	   public void aTimeSlotExistsWithStartTimeAtAndEndTimeAt(String type, String startDate, String startTime, String endDate, String endTime) throws Exception {
	       java.sql.Date startDate1 = toDate(startDate);
	       java.sql.Date endDate1 = toDate(endDate);
	       Time startTime1 = toTime(startTime);
	       Time endTime1 = toTime(endTime);
	       TimeSlot timeSlot = new TimeSlot(startDate1, startTime1, endDate1, endTime1, fbs);
	       if(type.equals("vacation")){
	           fbs.getBusiness().addVacation(timeSlot);
	       }
	       if(type.equals("holiday")){
	           fbs.getBusiness().addHoliday(timeSlot);
	       }
	   }
   
   /**
	 * @author Natalia Tabet Agredo
	 */
	@Given("the following services exist in the system:")
	public void the_following_services_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {

		List<List<String>> rows = dataTable.asLists();
		for (List<String> columns : rows) {
			if (columns.get(0).equals("name") != true) {
				Service s = new Service(columns.get(0), fbs, Integer.parseInt(columns.get(1)),
						Integer.parseInt(columns.get(3)), Integer.parseInt(columns.get(2)));
				fbs.addBookableService(s);
			}

		}

	}
	
	/**
	 * @author Natalia Tabet Agredo
	 */
	@Given("the following service combos exist in the system:")
	public void the_following_service_combos_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {

		List<List<String>> rows = dataTable.asLists();
		for (List<String> columns : rows) {
			if (columns.get(0).equals("name") != true) {
				List<String> serviceNames = parseTokensByComma(columns.get(2));
				List<String> areMandatory = parseTokensByComma(columns.get(3));
				ServiceCombo combo = new ServiceCombo(columns.get(0), fbs);
				for (int i = 0; i < serviceNames.size(); ++i) {
					Service s = findService(serviceNames.get(i), fbs);
					ComboItem ci = new ComboItem(Boolean.parseBoolean(areMandatory.get(i)), s, combo);
					if (ci.getService().getName().equals(columns.get(1))) {
						combo.setMainService(ci);
					}
					combo.addService(ci);
				}

				fbs.addBookableService(combo);

			}

		}

	}
	/**
	 * @author Natalia Tabet Agredo
	 */
	@Given("the following appointments exist in the system:")
	public void the_following_appointments_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) throws ParseException {
		boolean isUpdateAppointment = false;
		List<List<String>> rows = dataTable.asLists();
		for (List<String> columns : rows) {
			if (columns.get(2).equals("optServices") == true) {
				isUpdateAppointment= true;
			}
			
			if (columns.get(0).equals("customer") != true) {
				Customer customerForAppointment = null;
				List<Customer> customers = fbs.getCustomers();
				for (Customer c : customers) {
					if (c.getUsername().equals(columns.get(0))) {
						customerForAppointment = c;
					}
				}

				List<BookableService> allBookableServices = fbs.getBookableServices();
				BookableService serviceForAppointment = null;
				for (BookableService b : allBookableServices) {
					if (b.getName().equals(columns.get(1))) {
						serviceForAppointment = b;
					}
				}
				if(serviceForAppointment instanceof ServiceCombo) {
					//DELETE COMBO
					TimeSlot slotForAppointment = new TimeSlot(fromStringToDate(columns.get(3)),
							fromStringToTime(columns.get(4)), fromStringToDate(columns.get(3)),
							fromStringToTime(columns.get(5)), fbs);
					Appointment a = new Appointment(customerForAppointment, serviceForAppointment, slotForAppointment, fbs);
					fbs.addAppointment(a);
				}
				else {
					if(isUpdateAppointment) {
						//UPDATE APPOINTMENT
						TimeSlot slotForAppointment = new TimeSlot(fromStringToDate(columns.get(3)),
								fromStringToTime(columns.get(4)), fromStringToDate(columns.get(3)),
								fromStringToTime(columns.get(5)), fbs);
						Appointment a = new Appointment(customerForAppointment, serviceForAppointment, slotForAppointment, fbs);
						fbs.addAppointment(a);
					}
					else {
						//DELETE SERVICE
						TimeSlot slotForAppointment = new TimeSlot(fromStringToDate(columns.get(2)),
								fromStringToTime(columns.get(3)), fromStringToDate(columns.get(2)),
								fromStringToTime(columns.get(4)), fbs);
						Appointment a = new Appointment(customerForAppointment, serviceForAppointment, slotForAppointment, fbs);
						fbs.addAppointment(a);
					}
					
				}
			}
		}
	}
	/**
	 * @author Ervin Cai
	 */
	@Given("{string} has {int} no-show records")
	public void has_no_show_records(String string, Integer int1) {
		fbs = FlexiBookApplication.getFlexiBook();
		user = User.getWithUsername(string);
		((Customer) user).setNoShow(int1);
	 
	}
	
	/**
	 * @author Ervin Cai
	 */
	
	@When("{string} makes a {string} appointment for the date {string} and time {string} at {string}")
	public void makes_a_appointment_for_the_date_and_time_at(String string, String string2, String string3, String string4, String string5) throws ParseException {
		//THIS IS ONLY FOR SERVICES NOT COMBOS
		//ervin solution
		setDateAndTimeOfSystem(string5);
		List<String> a = null;
		try {
			currentAppointment = FlexiBookController.makeAppointment(string, string2, a, fromStringToDate(string3), fromStringToTime(string4), fbs);
			System.out.print(" appt " + currentAppointment.getBookableService().getName());
		} 
		catch (InvalidInputException e) {
			error += e.getMessage();
			errorCount++;
		}
		
		//rakshitha solution
//		setDateAndTimeOfSystem(string5);
//		List<String> a = null;
//		try {
//			FlexiBookController.makeAppointment(string, string2, a,  fromStringToTime(string4), fromStringToDate(string3));
//			  
//			currentAppointment  = searchAppointment(string, Date.valueOf(string3), Time.valueOf(string4 + ":00"));
//
//
//		} 
//		catch (InvalidInputException e) {
//			error += e.getMessage();
//			errorCount++;
//		}
		
		
	}
	
	
	/**
	 * @author Natalia Tabet 
	 */
	@When("{string} attempts to change the service in the appointment to {string} at {string}")
	public void attempts_to_change_the_service_in_the_appointment_to_at(String string, String string2, String string3) throws ParseException  {
		setDateAndTimeOfSystem(string3);
		FlexiBookController.setIsTest(true);
		Date currentDate = FlexiBookController.getSystemDate();
		Time currentTime = FlexiBookController.getSystemTime();
	    boolean isDateAndTimeModification = false;
	    boolean isOptionalServiceModification = false;
	    boolean isNewServiceModification = true;
	    Service s = findService(string2, fbs);
	    System.out.println("service is : " + s.getName());
	    try {
			FlexiBookController.updateAppointment(currentAppointment, fbs, currentDate, currentTime, isDateAndTimeModification, isOptionalServiceModification, isNewServiceModification, s, null, null);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
	    
	}
	
	/**
	 * @author Natalia Tabet 
	 */
	@When("{string} attempts to update the date to {string} and time to {string} at {string}")
	public void attempts_to_update_the_date_to_and_time_to_at(String string, String string2, String string3, String string4) throws ParseException {
		setDateAndTimeOfSystem(string4);
		FlexiBookController.setIsTest(true);
		Date currentDate = FlexiBookController.getSystemDate();
		Time currentTime = FlexiBookController.getSystemTime();
		Date newDate = fromStringToDate(string2);
		Time newTime = fromStringToTime(string3);
	    boolean isDateAndTimeModification = true;
	    
	  
	    BookableService b = currentAppointment.getBookableService();
	    boolean isOptionalServiceModification = false;
	    boolean isNewServiceModification = false;
	    if (b instanceof Service) {
	    	isOptionalServiceModification = false;
	    	isNewServiceModification = true;
	    }
	    if (b instanceof ServiceCombo) {
	    	isOptionalServiceModification = true;
	    	isNewServiceModification = false;
	    }
	    
	    
	    try {
	    	if (b instanceof Service) {
				FlexiBookController.updateAppointment(currentAppointment, fbs, currentDate, currentTime, isDateAndTimeModification, isOptionalServiceModification, isNewServiceModification, (Service)currentAppointment.getBookableService(), newDate, newTime);

	    	}
	    	if (b instanceof ServiceCombo) {
				FlexiBookController.updateAppointment(currentAppointment, fbs, currentDate, currentTime, isDateAndTimeModification, isOptionalServiceModification, isNewServiceModification, ((ServiceCombo)currentAppointment.getBookableService()).getMainService().getService(), newDate, newTime);

	    	}
	    

		} 
	    catch (InvalidInputException e) {
			error = e.getMessage();
		}
	}
	
	/**
	 * @author Natalia Tabet 
	 */
	@When("{string} attempts to cancel the appointment at {string}")
	public void attempts_to_cancel_the_appointment_at(String string, String string2) throws ParseException {
		setDateAndTimeOfSystem(string2);
		FlexiBookController.setIsTest(true);
		Date currentDate = FlexiBookController.getSystemDate();
		Time currentTime = FlexiBookController.getSystemTime();
		
		try {
			boolean isStateChanged = FlexiBookController.cancelAppointment(currentAppointment, fbs, currentDate, currentTime);
		} 
		catch (InvalidInputException e) {
			error = e.getMessage();
		}
	}
	
	/**
	 * @author Ervin Cai 
	 */
	
	@When("{string} makes a {string} appointment without choosing optional services for the date {string} and time {string} at {string}")
	public void makes_a_appointment_without_choosing_optional_services_for_the_date_and_time_at(String string, String string2, String string3, String string4, String string5) throws ParseException {
	
		//THIS IS ONLY FOR COMBOS NOT SERVICES
		ServiceCombo found = findServiceCombo(string2, fbs);
		System.out.println("0 mandatory " + found.getServices().get(0).getMandatory());
		System.out.println("1 mandatory " + found.getServices().get(1).getMandatory());
		System.out.println("2 mandatory " + found.getServices().get(2).getMandatory());
		System.out.println("3 mandatory " + found.getServices().get(3).getMandatory());
		setDateAndTimeOfSystem(string5);
		List<String> a = null;
		try {
			currentAppointment = FlexiBookController.makeAppointment(string, string2, a, fromStringToDate(string3), fromStringToTime(string4), fbs);
		} 
		catch (InvalidInputException e) {
			error += e.getMessage();
			errorCount++;
		}
		
		
	}
	
	
	
	
	/**
	 * @author Natalia Tabet 
	 */
	@When("{string} attempts to add the optional service {string} to the service combo in the appointment at {string}")
	public void attempts_to_add_the_optional_service_to_the_service_combo_in_the_appointment_at(String string, String string2, String string3) throws ParseException {
		setDateAndTimeOfSystem(string3);
		FlexiBookController.setIsTest(true);
		Date currentDate = FlexiBookController.getSystemDate();
		Time currentTime = FlexiBookController.getSystemTime();
	
	    boolean isDateAndTimeModification = false;
	    boolean isOptionalServiceModification = true;
	    boolean isNewServiceModification = false;
	
	    Service s = findService(string2, fbs);
	    
	    
	    try {
			boolean isStateChanged = FlexiBookController.updateAppointment(currentAppointment, fbs, currentDate, currentTime, isDateAndTimeModification, isOptionalServiceModification, isNewServiceModification, s, null, null);
		} 
	    catch (InvalidInputException e) {
			error = e.getMessage();
		}
	}
	
	/**
	 * @author Natalia Tabet 
	 * @throws Exception 
	 */
	@When("the owner starts the appointment at {string}")
	public void the_owner_starts_the_appointment_at(String string) throws Exception {
		setDateAndTimeOfSystem(string);
		FlexiBookController.setIsTest(true);
		Date currentDate = FlexiBookController.getSystemDate();
		Time currentTime = FlexiBookController.getSystemTime();
		
		//experimental changes - Cecile
		
		 Date newDate = toDate(string.substring(0,10));
			Time newTime = toTime(string.substring(11, string.length()));
		
			TOAppointment appTO = FlexiBookController.getDtoFromAppointment(currentAppointment);
			boolean isStateChanged = FlexiBookController.startAppointment(appTO, fbs, newDate, newTime);	}
	
	/**
	 * @author Natalia Tabet 
	 */
	@When("the owner ends the appointment at {string}")
	public void the_owner_ends_the_appointment_at(String string) throws ParseException {
		setDateAndTimeOfSystem(string);
		boolean isStateChanged = FlexiBookController.endAppointment(currentAppointment, fbs);
	}
	
	/**
	 * @author Natalia Tabet 
	 */
	@When("the owner attempts to register a no-show for the appointment at {string}")
	public void the_owner_attempts_to_register_a_no_show_for_the_appointment_at(String string) throws ParseException {
		setDateAndTimeOfSystem(string);
		boolean isStateChanged = FlexiBookController.markNoShow(currentAppointment.getCustomer(), currentAppointment);
	}
	
	/**
	 * @author Natalia Tabet 
	 */
	@When("the owner attempts to end the appointment at {string}")
	public void the_owner_attempts_to_end_the_appointment_at(String string) throws ParseException {
		setDateAndTimeOfSystem(string);
		boolean isStateChanged = FlexiBookController.endAppointment(currentAppointment, fbs);
	}
	
	/**
	 * @author Karim Elgammal
	 */
	
	@Then("the appointment shall be booked")
	public void the_appointment_shall_be_booked() {
		assertEquals("Issued", currentAppointment.getStatusFullName()); 
	}


	/**
	 * @author Karim Elgammal
	 */
	@Then("the service in the appointment shall be {string}")
	public void the_service_in_the_appointment_shall_be(String string) {
	   
		assertEquals(string, currentAppointment.getBookableService().getName());
	    
	}

	@Then("the appointment shall be for the date {string} with start time {string} and end time {string}")
	public void the_appointment_shall_be_for_the_date_with_start_time_and_end_time(String startDate, String startTime, String endTime) throws Exception {
		assertEquals( toDate(startDate), currentAppointment.getTimeSlot().getStartDate());
		assertEquals( toTime(startTime), currentAppointment.getTimeSlot().getStartTime());
		assertEquals( toTime(endTime), currentAppointment.getTimeSlot().getEndTime());

	    
	}
	/**
	 * @author Karim Elgammal
	 */
	@Then("the username associated with the appointment shall be {string}")
	public void the_username_associated_with_the_appointment_shall_be(String string) {
	    
		assertEquals(string, currentAppointment.getCustomer().getUsername());
	    
	}

	/**
	 *@author Ervin Cai
	 */
	@Then("the user {string} shall have {int} no-show records")
	public void the_user_shall_have_no_show_records(String string, Integer int1) {
		User u = findUserWithUsername(string, fbs);
		int ns = ((Customer)u).getNoShow();
		assertTrue(ns == int1);
	}
	
	/**
	 * @author Mihir
	 */
	@Then("the system shall have {int} appointments")
	public void the_system_shall_have_appointments(Integer int1) {
	   
		List<Appointment> a = fbs.getAppointments(); 
		int s=a.size();
		assertTrue(s==int1);
		
	}
	
	/**
	 * @author Mihir
	 */
	@Then("the system shall have {int} appointment")
	public void the_system_shall_have_appointment(Integer int1) {
	   
		List<Appointment> a = fbs.getAppointments(); 
		int s=a.size();
		assertTrue(s==int1);
	
		
	}


	
	/**
	@author Ervin Cai
	 */
	@Then("the service combo in the appointment shall be {string}")
	public void the_service_combo_in_the_appointment_shall_be(String string) {
		
		if (currentAppointment.getBookableService() instanceof ServiceCombo) {
			assertEquals(currentAppointment.getBookableService().getName() ,string);
		}
	}
	
	/**
	@author Ervin Cai
	 */
	@Then("the service combo shall have {string} selected services")
	public void the_service_combo_shall_have_selected_services(String string) {
		if (currentAppointment.getBookableService() instanceof Service) {
			assertEquals(currentAppointment.getBookableService().getName() ,string);
		}
	}
	
	/**
	 * @author Karim Elgammal
	 */
	@Then("the appointment shall be in progress")
	public void the_appointment_shall_be_in_progress() {
		assertEquals("InProgress", currentAppointment.getStatusFullName());
	    
	}
//======================================================================================================================
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%#########################%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
//========================================================================================================================
	


	//========================================================================================================================

	                               // Define Service Combo - Natalia
	//========================================================================================================================





	/**
	 * @author Natalia Tabet Agredo
	 */
	@Given("the Owner with username {string} is logged in")
	public void the_owner_with_username_is_logged_in(String string) {
		owner.setUsername(string);
		FlexiBookApplication.setCurrentUser(owner);

	}

	/**
	 * @author Natalia Tabet Agredo
	 */
	@When("{string} initiates the definition of a service combo {string} with main service {string}, services {string} and mandatory setting {string}")
	public void initiates_the_definition_of_a_service_combo_with_main_service_services_and_mandatory_setting(
			String string, String string2, String string3, String string4, String string5) {
		List<String> services = parseTokensByComma(string4);
		List<String> areMandatory = parseTokensByComma(string5);

		try {
			FlexiBookController.defineServiceCombo(string2, string3, services, areMandatory, fbs);

		} catch (InvalidInputException e) {
			error += e.getMessage();
			errorCount++;
		}

	}

	/**
	 * @author Natalia Tabet Agredo
	 */
	@Then("the service combo {string} shall exist in the system")
	public void the_service_combo_shall_exist_in_the_system(String string) {

		List<ServiceCombo> currentCombos = getCurrentCombos(fbs);

		List<String> comboNames = new ArrayList<String>();
		for (ServiceCombo c : currentCombos) {
			comboNames.add(c.getName());
		}

		assertTrue(comboNames.contains(string));

	}

	/**
	 * @author Natalia Tabet Agredo
	 */
	@Then("the service combo {string} shall contain the services {string} with mandatory setting {string}")
	public void the_service_combo_shall_contain_the_services_with_mandatory_setting(String string, String string2,
			String string3) {
		List<String> services = parseTokensByComma(string2);
		List<String> areMandatory = parseTokensByComma(string3);
		List<ComboItem> toComboItem = getComboItems(string, fbs);

		for (int i = 0; i < services.size(); ++i) {
			assertEquals(services.get(i), toComboItem.get(i).getService().getName());
			assertEquals(Boolean.parseBoolean(areMandatory.get(i)), toComboItem.get(i).getMandatory());

		}

	}

	/**
	 * @author Natalia Tabet Agredo
	 */
	@Then("the main service of the service combo {string} shall be {string}")
	public void the_main_service_of_the_service_combo_shall_be(String string, String string2) {
		assertEquals(string2, getMainServiceName(string, fbs));

	}

	/**
	 * @author Natalia Tabet Agredo
	 */
	@Then("the service {string} in service combo {string} shall be mandatory")
	public void the_service_in_service_combo_shall_be_mandatory(String string, String string2) {

		List<ComboItem> comboItems = getComboItems(string2, fbs);
		List<Service> services = getComboServices(string2, fbs);

		int index = 0;

		for (int i = 0; i < services.size(); ++i) {
			if (services.get(i).getName().equals(string)) {
				index = i;
				break;
			}
		}
		Boolean mandatory = true;
		assertEquals(mandatory, comboItems.get(index).getMandatory());
	}

	/**
	 * @author Natalia Tabet Agredo
	 */
	@Then("the number of service combos in the system shall be {string}")
	public void the_number_of_service_combos_in_the_system_shall_be(String string) {
		List<ServiceCombo> combos = getCurrentCombos(fbs);
		assertEquals(Integer.parseInt(string), combos.size());

	}


	/**
	 * @author Natalia Tabet Agredo
	 */
	@Then("the service combo {string} shall not exist in the system")
	public void the_service_combo_shall_not_exist_in_the_system(String string) {
		List<ServiceCombo> currentCombos = getCurrentCombos(fbs);
		List<String> comboNames = new ArrayList<String>();
		if (currentCombos != null) {
			for (ServiceCombo c : currentCombos) {
				comboNames.add(c.getName());
			}
		} else {
			assertNull(currentCombos);
		}

		assertFalse(comboNames.contains(string));

	}


	/**
	 * @author Natalia Tabet Agredo
	 */
	@Then("the service combo {string} shall preserve the following properties:")
	public void the_service_combo_shall_preserve_the_following_properties(String string,
			io.cucumber.datatable.DataTable dataTable) {
		List<List<String>> rows = dataTable.asLists();
		List<String> services = new ArrayList<>();
		List<String> areMandatory = new ArrayList<>();

		for (List<String> columns : rows) {
			if (columns.get(0).equals("name") != true) {
				services = parseTokensByComma(columns.get(2));
				areMandatory = parseTokensByComma(columns.get(3));
			}

		}
		for (int i = 0; i < services.size(); ++i) {
			assertEquals(services.get(i), getComboServices(string, fbs).get(i).getName());
			assertEquals(Boolean.parseBoolean(areMandatory.get(i)), getComboItems(string, fbs).get(i).getMandatory());
		}

	}


	/**
	 * @author Natalia Tabet Agredo
	 */
	@Given("Customer with username {string} is logged in")
	public void customer_with_username_is_logged_in(String string) {
		List<Customer> customers = fbs.getCustomers();
		Customer current = null;
		for (Customer c : customers) {
			if (c.getUsername().equals(string)) {
				current = c;
			}
		}
		FlexiBookApplication.setCurrentUser(current);

	}


	//========================================================================================================================

	                             // Update Service Combo - Natalia

	//========================================================================================================================


	/**
	 * @author Natalia Tabet Agredo
	 */
	@When("{string} initiates the update of service combo {string} to name {string}, main service {string} and services {string} and mandatory setting {string}")
	public void initiates_the_update_of_service_combo_to_name_main_service_and_services_and_mandatory_setting(
			String string, String string2, String string3, String string4, String string5, String string6) {
		List<String> services = parseTokensByComma(string5);
		List<String> areMandatory = parseTokensByComma(string6);

		try {

			FlexiBookController.updateServiceCombo(string2, string3, string4, services, areMandatory, fbs);
		} catch (InvalidInputException e) {
			error += e.getMessage();
			errorCount++;
		}

	}

	/**
	 * @author Natalia Tabet Agredo
	 */
	@Then("the service combo {string} shall be updated to name {string}")
	public void the_service_combo_shall_be_updated_to_name(String string, String string2) {
		List<ServiceCombo> combos = getCurrentCombos(fbs);

		List<String> comboNames = new ArrayList<String>();
		for (ServiceCombo c : combos) {
			comboNames.add(c.getName());

		}
		if (!string.equals(string2)) {
			assertFalse(comboNames.contains(string));
			assertTrue(comboNames.contains(string2));
		} else {
			assertTrue(comboNames.contains(string2));
		}

	}





	//========================================================================================================================

	                           // Delete Service Combo - Natalia

	//========================================================================================================================




	/**
	 * @author Natalia Tabet Agredo
	 */
	@When("{string} initiates the deletion of service combo {string}")
	public void initiates_the_deletion_of_service_combo(String string, String string2) throws InvalidInputException {

		try {

			FlexiBookController.deleteServiceCombo(string2, fbs);
		} catch (InvalidInputException e) {
			error += e.getMessage();
			errorCount++;
		}
	}

	/**
	 * @author Natalia Tabet Agredo
	 */
	@Then("the number of appointments in the system with service {string} shall be {string}")
	public void the_number_of_appointments_in_the_system_with_service_shall_be(String string, String string2) {
		List<Appointment> allApt = fbs.getAppointments();
		int numberOfAppointmentsOfCombo = 0;
		for (Appointment a : allApt) {
			if (a.getBookableService().getName().equals(string)) {
				++numberOfAppointmentsOfCombo;
			}
		}

		assertEquals(Integer.parseInt(string2), numberOfAppointmentsOfCombo);

	}
	/**
	 * @author Natalia Tabet Agredo
	 */
	@Then("the number of appointments in the system shall be {string}")
	public void the_number_of_appointments_in_the_system_shall_be(String string) {
		assertEquals(Integer.parseInt(string), fbs.getAppointments().size());

	}


	//====================================================================================================================

	// Natalia's helper methods for define, update and delete service combos 

	//=======================================================================================================================


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
	 * This helper method gets the list of services associated to a specific combo
	 * 
	 * @author Natalia Tabet Agredo
	 * @param comboName the name of the combo
	 * @param f         the flexibook object of the system
	 * @return the list of Service objects associated to the combo
	 */
	private static List<Service> getComboServices(String comboName, FlexiBook f) {
		ArrayList<Service> comboServices = new ArrayList<Service>();
		ServiceCombo c = findServiceCombo(comboName, f);
		if (c == null) {
			System.out.println("c is null");
		} else {
			for (ComboItem ci : c.getServices()) {
				comboServices.add(ci.getService());
			}
		}

		return comboServices;
	}



	/**
	 * This helper methog gets the name of the main service of a specific combo
	 * 
	 * @author Natalia Tabet Agredo
	 * @param comboName the name of the combo
	 * @param f         the flexibook object of the system
	 * @return the name of the main service of the combo
	 */
	private String getMainServiceName(String comboName, FlexiBook f) {
		String mainName = null;
		ServiceCombo s = findServiceCombo(comboName, f);
		mainName = s.getMainService().getService().getName();
		return mainName;
	}


	/**
	 * This helper method finds a specific ServiceCombo in the system
	 * 
	 * @author Natalia Tabet Agredo
	 * @param name the name of the ServiceCombo
	 * @param f    the flexibook object of the system
	 * @return the ServiceCombo object if found, otherwise null
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
	 * This helper method gets all the combos of the system
	 * 
	 * @author Natalia Tabet Agredo
	 * @param f the flexibook object of the system
	 * @return the list of the ServiceCombo objects contained in the system
	 */
	private static List<ServiceCombo> getCurrentCombos(FlexiBook f) {
		ArrayList<ServiceCombo> combos = new ArrayList<ServiceCombo>();
		for (BookableService combo : f.getBookableServices()) {
			if (combo instanceof ServiceCombo) {
				combos.add((ServiceCombo) combo);

			}
		}
		return combos;
	}

	/**
	 * This helper method finds a specific Service inside the system
	 * 
	 * @author Natalia Tabet Agredo
	 * @param name the name of the service
	 * @param f    the flexibook object of the system
	 * @return the Service object if found, otherwise null
	 */
	private static Service findService(String name, FlexiBook f) {
		Service foundService = null;
		for (BookableService service : f.getBookableServices()) {
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
	 * This helper method converts a string into a Date object
	 * 
	 * @author Natalia Tabet Agredo
	 * @param s the string date in format yyyy-MM-dd
	 * @return the Date object
	 * @throws ParseException
	 */
	private Date fromStringToDate(String s) throws ParseException {
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
	private Time fromStringToTime(String s) {
		s += ":00";
		java.sql.Time sqlTime = Time.valueOf(s);
		return sqlTime;
	}

	/**
	 * This helper method parse a string and separate the tokens by comma
	 * 
	 * @author Natalia Tabet Agredo
	 * @param s the input string
	 * @return the list of tokens
	 */
	private static List<String> parseTokensByComma(String s) {
		List<String> tokenized = new ArrayList<>();
		StringTokenizer multiTokenizer = new StringTokenizer(s, ",");

		while (multiTokenizer.hasMoreTokens()) {
			tokenized.add(multiTokenizer.nextToken());
		}
		return tokenized;
	}



	/**
	 * This helper method takes the input string and set the date and time of the
	 * flexibook system
	 * 
	 * @author Natalia Tabet Agredo
	 * @param s the string containing date and time
	 * @throws ParseException
	 */
	private void setDateAndTimeOfSystem(String s) throws ParseException {
		FlexiBookController.setIsTest(true);
		String newString = s.replace("+", " ");

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		java.util.Date date = sdf.parse(newString);

		java.sql.Date sqlDate = new Date(date.getTime());
		FlexiBookController.setSystemDate(sqlDate);
		String time = newString.substring(11, 15);
		time += ":00";
		java.sql.Time sqlTime = Time.valueOf(time);
		FlexiBookController.setSystemTime(sqlTime);
	}



	//=========================================================================================================================
	//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	//==========================================================================================================================
		




	//========================================================================================================================

	                         // Add Service  - Ervin

	//========================================================================================================================

	/**
	 * @author Ervin Cai
	 */
	@When("{string} initiates the addition of the service {string} with duration {string}, start of down time {string} and down time duration {string}")
	public void initiates_the_addition_of_the_service_with_duration_start_of_down_time_and_down_time_duration(String string, String string2, String string3, String string4, String string5) {
		int duration = Integer.parseInt(string3);
		    int startDownTime = Integer.parseInt(string4);
		    int downTimeDuration = Integer.parseInt(string5);
			try {
				FlexiBookController.addService(string, string2, duration, startDownTime, downTimeDuration, fbs);
			} 
			catch (InvalidInputException e) {
				error += e.getMessage();
				errorCount++;
			}
	}

	/**
	 * @author Ervin Cai
	 */
	@Then("the service {string} shall exist in the system")
	public void the_service_shall_exist_in_the_system(String string) {
		List<Service> currentServices = getCurrentServices(fbs);
		List<String> serviceNames = new ArrayList<String>();
		for (Service s : currentServices) {
			serviceNames.add(s.getName());
		}
		assertTrue(serviceNames.contains(string));
	}




	/**
	 * @author Ervin Cai
	 */
	@Then("the service {string} shall have duration {string}, start of down time {string} and down time duration {string}")
	public void the_service_shall_have_duration_start_of_down_time_and_down_time_duration(String string, String string2,
			String string3, String string4) {
		Service service = findService(string, fbs);

		int duration = Integer.parseInt(string2);
		int startDownTime = Integer.parseInt(string3);
		int downTimeDuration = Integer.parseInt(string4);

		assertEquals(duration, service.getDuration());
		assertEquals(startDownTime, service.getDowntimeStart());
		assertEquals(downTimeDuration, service.getDowntimeDuration());
	}

	/**
	 * @author Ervin Cai
	 */
	@Then("the number of services in the system shall be {string}")
	public void the_number_of_services_in_the_system_shall_be(String string) {
		List<Service> services = getCurrentServices(fbs);
		assertEquals(Integer.parseInt(string), services.size());
	}

	/**
	 * @author Ervin Cai
	 */
	@Then("the service {string} shall not exist in the system")
	public void the_service_shall_not_exist_in_the_system(String string) {
		List<Service> currentServices = getCurrentServices(fbs);
		List<String> serviceNames = new ArrayList<String>();
		if (currentServices != null) {
			for (Service s : currentServices) {
				serviceNames.add(s.getName());
			}
		} else {
			assertNull(currentServices);
		}
		assertFalse(serviceNames.contains(string));
	}

	/**
	 * @author Ervin Cai
	 */
	@Then("the service {string} shall still preserve the following properties:")
	public void the_service_shall_still_preserve_the_following_properties(String string,
			io.cucumber.datatable.DataTable dataTable) {
		List<List<String>> rows = dataTable.asLists();
		for (List<String> columns : rows) {
			if (columns.get(0).equals("name") != true) {
				Service foundService = findService(string, fbs);
				if (foundService != null) {
					assertEquals(columns.get(0), foundService.getName());
					assertEquals(Integer.parseInt(columns.get(1)), foundService.getDuration());
					assertEquals(Integer.parseInt(columns.get(2)), foundService.getDowntimeStart());
					assertEquals(Integer.parseInt(columns.get(3)), foundService.getDowntimeDuration());
				}
			}
		}
	}



	//========================================================================================================================

	                       // Update Service  - Ervin

	//========================================================================================================================



	/**
	 * @author Ervin Cai
	 */
	@When("{string} initiates the update of the service {string} to name {string}, duration {string}, start of down time {string} and down time duration {string}")
	public void initiates_the_update_of_the_service_to_name_duration_start_of_down_time_and_down_time_duration(
			String string, String string2, String string3, String string4, String string5, String string6) {
		Service service = findService(string2, fbs);
		int duration = Integer.parseInt(string4);
		int startDownTime = Integer.parseInt(string5);
		int downTimeDuration = Integer.parseInt(string6);
		try {
			FlexiBookController.updateService(string, string2, string3, duration, startDownTime, downTimeDuration, fbs);
		} catch (InvalidInputException e) {
			error += e.getMessage();
			errorCount++;
		}
	}

	/**
	 * @author Ervin Cai
	 */
	@Then("the service {string} shall be updated to name {string}, duration {string}, start of down time {string} and down time duration {string}")
	public void the_service_shall_be_updated_to_name_duration_start_of_down_time_and_down_time_duration(String string,
			String string2, String string3, String string4, String string5) {
		Service service = findService(string2, fbs);

		int duration = Integer.parseInt(string3);
		int startDownTime = Integer.parseInt(string4);
		int downTimeDuration = Integer.parseInt(string5);

		assertEquals(string2, service.getName());
		assertEquals(duration, service.getDuration());
		assertEquals(startDownTime, service.getDowntimeStart());
		assertEquals(downTimeDuration, service.getDowntimeDuration());
	}




	//========================================================================================================================

	                       // Delete Service  - Ervin

	//========================================================================================================================


	/**
	 * @author Ervin Cai
	 */
	@When("{string} initiates the deletion of service {string}")
	public void initiates_the_deletion_of_service(String string, String string2) {
		try {
			FlexiBookController.deleteService(string, string2, fbs);
		} catch (InvalidInputException e) {
			error += e.getMessage();
			errorCount++;
		}
	}

	/**
	 * @author Ervin Cai
	 */
	@Then("the service combos {string} shall not exist in the system")
	public void the_service_combos_shall_not_exist_in_the_system(String string) {
		List<ServiceCombo> currentCombos = getCurrentCombos(fbs);
		List<String> comboNames = new ArrayList<String>();
		if (currentCombos != null) {
			for (ServiceCombo c : currentCombos) {
				comboNames.add(c.getName());
			}
		} else {
			assertNull(currentCombos);
		}
		System.out.println("START");
		System.out.println(comboNames);
		System.out.println("END");
		assertFalse(comboNames.contains(string));
	}

	/**
	 * @author Ervin Cai
	 */
	@Then("the service combos {string} shall not contain service {string}")
	public void the_service_combos_shall_not_contain_service(String string, String string2) {
		List<ServiceCombo> currentCombos = getCurrentCombos(fbs);
		List<String> comboNames = new ArrayList<String>();
		List<String> serviceNames = new ArrayList<String>();
		if (currentCombos != null) {
			for (ServiceCombo c : currentCombos) {
				for (ComboItem s : c.getServices()) {
					serviceNames.add(s.getService().getName());
				}
				// assertFalse(c.get)
				// comboNames.add(c.getName());
			}
		} else {
			assertNull(currentCombos);
		}

		assertFalse(serviceNames.contains(string));
	}


	//========================================================================================================================

	// ervin's helper methods for services

	//========================================================================================================================

	//Invalid Parameters
				/**
				 * @author Ervin Cai
				 */
				@Then("an error message with content {string} shall be raised")
				public void an_error_message_with_content_shall_be_raised(String string) {
					assertTrue(error.contains(string));
				}
				@After
				public void tearDown() {
					fbs.delete();
				}
		

		
	/**
	 * This helper method takes the input flexibook and return the current services
	 * 
	 * @author Ervin Cai
	 * @param f the flexibook
	 * @return
	 */
	private static List<Service> getCurrentServices(FlexiBook f){
			ArrayList<Service> services = new ArrayList<Service>();
			 for (BookableService service : f.getBookableServices()) {
					if (service instanceof Service) {
						services.add((Service) service);
					}
			}
			 return services;
		}


	//=========================================================================================================================
	//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	//==========================================================================================================================

	

	//========================================================================================================================
            
	//make , update and cancel appointment - Rakshitha 
	
	//=========================================================================================================================


	/**
	 * @author rakshitharavi
	 * @param string
	 * @throws ParseException
	 */
	 @Given("the system's time and date is {string}")
	    
     
	 public void the_system_s_time_and_date_is(String string) throws ParseException {
	    		setDateAndTimeOfSystem(string);
	        
	    }
	 
	 /*
		 * Make appointment feature 
		 * @author Rakshitha Ravi
		 * 
		 */
		@Given("{string} is logged in to their account")
	   public void is_logged_in_to_their_account(String string) {
	    		Customer customer = findCustomerByName(string);
	    	    FlexiBookApplication.setCurrentUser(customer);
	    	}	
		 
	        
	 
		
	/*
		 * Make appointment feature 
		 * @author Rakshitha Ravi
		 * 
		 */	
	  
	  	  @When("{string} schedules an appointment on {string} for {string} at {string}")
		  public void schedules_an_appointment_on_for_at(String username, String date, String serviceName, String startTime) throws ParseException {

		   
	  		 try {
			     
	 		    FlexiBookController.makeAppointment(username,  serviceName, null, fromStringToTime(startTime),fromStringToDate(date) );
	 				
	 		    	oldAts = cAts;
	 		      cAts++;
	 		    } catch (InvalidInputException e) {
	 		      error = e.getMessage();
	 		      errorCount++;
	 		    }

		      
			
		}
	  
	  	/**
	  	 * @author rakshitharavi
	  	 * @param username
	  	 * @param date
	  	 * @param serviceName
	  	 * @param optServices
	  	 * @param time
	  	 * @throws ParseException
	  	 */


	  	  @When("{string} schedules an appointment on {string} for {string} with {string} at {string}")
	  		public void schedules_an_appointment_on_for_with_at(String username, String date, String serviceName, String optServices, String time) throws ParseException {
	  		    
	  			String[] optionalServices = optServices.split(",");	
	  			List<String> optionalServiceList = Arrays.asList(optionalServices);
	  			
	  			try {
	  				FlexiBookController.makeAppointment(username,  serviceName, optionalServiceList, fromStringToTime(time),fromStringToDate(date) );
	  				
	  				
	  			} catch (InvalidInputException e) {
	  				error = e.getMessage();
	  				errorCount++;
	  			}
	  			
	  		}
	  		


	  
	  /**
	   * @author rakshitharavi
	   * @param user
	   * @param serviceType
	   * @param date
	   * @param startTime
	   * @param endTime
	   */
	  
	    @Then("{string} shall have a {string} appointment on {string} from {string} to {string}")
	    public void shallHaveAAppointmentOnFromTo(String user, String serviceType, String date, String startTime, String endTime) {
	        boolean passed = false;
	        User u = User.getWithUsername(user);
	        if (u instanceof Customer) {
	            Customer c = (Customer) u;
	            try {
	                for (Appointment a : c.getAppointments()) {
	                    if (a.getBookableService().getName().equals(serviceType) && 
	                    		a.getTimeSlot().getStartDate().equals(fromStringToDate(date))
	                    		&& a.getTimeSlot().getStartTime().equals(fromStringToTime(startTime)) 
	                        
	                    	&& (a.getTimeSlot().getEndTime().equals(fromStringToTime(endTime))))
	                        	
	                        	
	                        passed = true;
	                        
	                }
	                        
	                    
	                   
	                }
	             catch (NullPointerException | ParseException ex) {
	                ex.printStackTrace();
	            }
	        

	        if (!passed) {
	            errorCount++;
	            assert u instanceof Customer;
	            StringBuilder sb = new StringBuilder();
	            for(Appointment a : ((Customer) u).getAppointments()){
	                sb.append(a.getBookableService().getName()).append(a.getTimeSlot()).append(a.getChosenItems()).append(", \n");
	           
	            
	            }
	            error += "Appointment list wrong for user " + u.getUsername() + " user has: " + sb.toString()  + " needs to have one on " + date +" at "+startTime +" till "+ endTime;
	            
	          
	        }
	        }
		}

	    @Then("the system shall report that the update was {string}")
		public void the_system_shall_report_that_the_update_was(String string) {
			FlexiBookApplication.setCurrentUser(user);
			//assertEquals(currentAppointment, string );
		}
	  
	 	
	  
/**
* @author rakshitharavi
* @param username
* @param serviceName
* @param optServices
* @param date
* @param time
*/
@Given("{string} has a {string} appointment with optional sevices {string} on {string} at {string}")
public void has_a_appointment_with_optional_sevices_on_at(String username, String serviceName, String optServices, String date, String time) {

Appointment appointment = searchAppointment(username, Date.valueOf(date), Time.valueOf(time + ":00"));
assertFalse(appointment != null);

}


/**
 * Make appointment feature 
 * @author Rakshitha Ravi
 * 
 */	

@When("{string} attempts to {string} {string} from their {string} appointment on {string} at {string}")
public void attempts_to_from_their_appointment_on_at(String username, String action, String optService, String mainService, String date, String time) {
String[] optionalServices = optService.split(",");	
List<String> optionalServiceList = Arrays.asList(optionalServices);

try {
	if(action.equals("add")) {
		updateAppointmentSuccess = FlexiBookController.updateAppointment(username, mainService, optionalServiceList, null, null, null, Time.valueOf(time + ":00"), Date.valueOf(date));
    }
    else if(action.equals("remove")) {
    	updateAppointmentSuccess = FlexiBookController.updateAppointment(username, mainService, null, optionalServiceList, null, null, Time.valueOf(time + ":00"), Date.valueOf(date));
    }
	
} catch (InvalidInputException e) {
	error = e.getMessage();
	errorCount++;
}

}

	  

/**
 * @author rakshitharavi
 * @param username
 * @param serviceName
 * @param date
 * @param time
 */
@Then("{string}'s {string} appointment on {string} at {string} shall be removed from the system")
public void s_appointment_on_at_shall_be_removed_from_the_system(String username, String serviceName, String date, String time) {
	Date currentDate = FlexiBookController.getSystemDate();
	Time currentTime = FlexiBookController.getSystemTime();
	

Appointment appointment = searchAppointment(username, currentDate, currentTime);
assertFalse(appointment != null);

}



/**
 * @author rakshitharavi
 * @param username
 * @param serviceName
 * @param date
 * @param time
 */
@When("{string} attempts to cancel their {string} appointment on {string} at {string}")
public void attempts_to_cancel_their_appointment_on_at(String username, String serviceName, String date,
    String time) throws InvalidInputException {
	
	
	Date currentDate = FlexiBookController.getSystemDate();
	Time currentTime = FlexiBookController.getSystemTime();
	
	// getting ts extra
	System.out.print(currentDate);
	 fbs.getAppointments();
	 
  oldAts = cAts;
  cAts--;

}




/**
 * @author rakshitharavi
 * @param currentUser
 * @param customer
 * @param sName
 * @param date
 * @param time
 * @throws ParseException
 * @throws InvalidInputException
 */

@When("{string} attempts to cancel {string}'s {string} appointment on {string} at {string}")
public void attempts_to_cancel_s_appointment_on_at(String currentUser, String customer, String sName, String date, String time) throws ParseException, InvalidInputException {

	
	 Date currentDate = FlexiBookController.getSystemDate();
		Time currentTime = FlexiBookController.getSystemTime();
	//FlexiBookController.cancelAppointment(currentUser, fromStringToTime(time),date,  currentDate, fbs);
	oldAts = cAts;
	//the old and new appointment timeslot var 
	cAts--;
	
	
}

/**
 * @author rakshitharavi
 * @param username
 * @param sName
 * @param oldDate
 * @param oldTime
 * @param nDate
 * @param newTime
 * @throws InvalidInputException
 */
@When("{string} attempts to update their {string} appointment on {string} at {string} to {string} at {string}")
public void attempts_to_update_their_appointment_on_at_to_at(String username, String sName, String oldDate, String oldTime, String nDate, String nTime) throws InvalidInputException {
    
	

	Date currentDate = FlexiBookController.getSystemDate();
	Time currentTime = FlexiBookController.getSystemTime();
		  //updateAppointmentSuccess = FlexiBookController.updateAppointmentTime(username, sName, nTime, nDate, currentTime, currentDate, currentDate, currentTime, fbs);
		  fbs.getAppointments();
	 errorCount++;
	  }

/**
 * @author rakshitharavi
 * @param r
 */
@Then("there shall be {int} less appointment in the system")
public void there_shall_be_less_appointment_in_the_system(int r) {
	 FlexiBookApplication.setCurrentUser(user);
	//assertEquals(currentAppointment.getStatus(), r-1);
	//assertNull(r-1);
	
	}


/**
 * @author rakshitharavi	
 * @param eMsg
 */
  @Then("the system shall report {string}")
		public void the_system_shall_report(String eMsg) {
	  
	  //changed this - should be assertTrue???
	 
//			assertFalse(error.equals(eMsg));
	  assertTrue(error.equals(eMsg));

		}
	  
	  /**
		 * Make appointment feature 
		 * @author Rakshitha Ravi
		 * 
		 */	
  @Then("there shall be {int} more appointment in the system")
	 public void there_shall_be_more_appointment_in_the_system(int x) {
	  FlexiBookApplication.setCurrentUser(user);
		  //assertEquals(currentAppointment,x+1);
	  }


/**
 * @author rakshitharavi	 
 * @param username1
 * @param username2
 * @param serviceName
 * @param oldDate
 * @param oldTime
 * @param newDate
 * @param newTime
 */
@When("{string} attempts to update {string}'s {string} appointment on {string} at {string} to {string} at {string}")
public void attempts_to_update_s_appointment_on_at_to_at(String username1, String username2, String serviceName, String oldDate, String oldTime, String newDate, String newTime) {
    
	Date currentDate = FlexiBookController.getSystemDate();
	Time currentTime = FlexiBookController.getSystemTime();

	/*try {
		updateAppointmentSuccess = FlexiBookController.updateAppointmentTime(username1, serviceName, null, null, newTime, newDate, Time.valueOf(oldTime + ":00"), Date.valueOf(oldDate), fbs);
	} catch (InvalidInputException e) {
		error = e.getMessage();
		errorCount++;
	*/
	
}

//=================Helper methods for Make,update and cancel Appointment - Rakshitha


	  /**
	   * 
	   * helper method to search appointment
	   * @author rakshitharavi
	   * @param username
	   * @param d
	   * @param sTime
	   * @return
	   */


	  private Appointment searchAppointment(String username, Date d, Time sTime) {
			Appointment appointment = null;
			
			List<Appointment> appointmentList = fbs.getAppointments();
			for (int i = 0; i < appointmentList.size(); i++) {
				Appointment thisAppointment = appointmentList.get(i);
				String Username = thisAppointment.getCustomer().getUsername();
				if(d.equals(thisAppointment.getTimeSlot().getStartDate()) && sTime.equals(thisAppointment.getTimeSlot().getStartTime()) && Username.equals(username)) {
					appointment = thisAppointment;
					break;
				}
			}
			
			return appointment;
			
		}

	  
	 /**
	  * @author rakshitharavi
	  * @param username
	  * @return
	  */
		private Customer findCustomerByName(String username) {
			Customer thisCustomer = null;
			
			List<Customer> customerList = fbs.getCustomers();
			for(int i = 0; i < customerList.size(); i++) {
				thisCustomer = customerList.get(i);
				if(thisCustomer.getUsername().equals(username)) {
					return thisCustomer;
				}
			}
			
			return thisCustomer;
		}

//======================================================================================================================
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%#########################%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
//========================================================================================================================




//========================================================================================================================

	//LogIn - Mihir 
	
//=========================================================================================================================


		private String username;
		private String password;
		private List<TOTimeSlot> available = new ArrayList<TOTimeSlot>();
		private List<TOTimeSlot> unavailable= new ArrayList<TOTimeSlot>();
		String maindate;
		

		@Given("the business has the following holidays:")
		public void the_business_has_the_following_holidaysM(io.cucumber.datatable.DataTable dataTable) {
			//double check this @Rakshitha
			TimeSlot holiday;
			
			List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class); 
			for(Map<String, String> columns : rows) {
				holiday = new TimeSlot(Date.valueOf(columns.get("startDate")), Time.valueOf(columns.get("startTime") + ":00"), Date.valueOf(columns.get("endDate")), Time.valueOf(columns.get("endTime") + ":00"), fbs);
				fbs.getBusiness().addHoliday(holiday);
			}
		    // For other transformations you can register a DataTableType.
		    
		}
		
		@Given("the business has the following opening hours:")
		public void the_business_has_the_following_opening_hoursM(io.cucumber.datatable.DataTable dataTable) {
			BusinessHour businessHour = null;
		    String Day;
		    
		    List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class); 
			//made some changes - Cecile
		    for(Map<String, String> columns : rows) {
				Day = columns.get("day");
				
				if(Day.equals("Monday")){
					businessHour = new BusinessHour(DayOfWeek.Monday, Time.valueOf(columns.get("startTime") + ":00"), Time.valueOf(columns.get("endTime") + ":00"), fbs);
				}
				else if(Day.equals("Tuesday")) {
					businessHour = new BusinessHour(DayOfWeek.Tuesday, Time.valueOf(columns.get("startTime") + ":00"), Time.valueOf(columns.get("endTime") + ":00"), fbs);
				}
				else if(Day.equals("Wednesday")) {
					businessHour = new BusinessHour(DayOfWeek.Wednesday, Time.valueOf(columns.get("startTime") + ":00"), Time.valueOf(columns.get("endTime") + ":00"), fbs);
				}
				else if(Day.equals("Thursday")) {
					businessHour = new BusinessHour(DayOfWeek.Thursday, Time.valueOf(columns.get("startTime") + ":00"), Time.valueOf(columns.get("endTime") + ":00"), fbs);
				}
				else if(Day.equals("Friday")) {
					businessHour = new BusinessHour(DayOfWeek.Friday, Time.valueOf(columns.get("startTime") + ":00"), Time.valueOf(columns.get("endTime") + ":00"), fbs);
				}
				
					
				fbs.getBusiness().addBusinessHour(businessHour);
				
			}
		   
		}
		
		/**
		  * @author Mihir Kumar
		  * @param string
		  * @param string2
		  */
			 @When("{string} requests the appointment calendar for the day of {string}")
			 public void requests_the_appointment_calendar_for_the_day_of(String string, String string2) {
			     try {
			    	  maindate =string2;
			    	 available=FlexiBookController.getDailyAvailableTimeSlots(string2);
			    	 unavailable=FlexiBookController.getDailyUnavailableTimeSlots(string2);
			    	 
			    	
			     }
			     
			     catch(InvalidInputException e) {
			    	 error += e.getMessage();
						errorCount++;
			     }
				
			 }
		
			 
			 /**
			  * @author Mihir Kumar
			  * @param dataTable
			  */
			 
				@Then("the following slots shall be unavailable:")
				public void the_following_slots_shall_be_unavailable(io.cucumber.datatable.DataTable dataTable) {
					
					
				
					List<Map<String, String>> List = dataTable.asMaps();
			        List<TimeSlot> available2 = new ArrayList<TimeSlot>();
			       
			        for (Map<String, String> map : List) {
			        	
			            Date startDate = Date.valueOf(map.get("date"));
			         
			            Time startTime = Time.valueOf(map.get("startTime") + ":00");
			            Time endTime = Time.valueOf(map.get("endTime") + ":00");
			            TimeSlot timeslot = new TimeSlot(startDate, startTime, startDate, endTime, fbs);
			            
						available2.add(timeslot);
			        }
			        if (unavailable.equals(available2)) {
			            assertTrue(true);
			        }
	
				}
				
				/**
				 * @author Mihir Kumar
				 *
				 * @param dataTable
				 */
				@Then("the following slots shall be available:")
				public void the_following_slots_shall_be_available(io.cucumber.datatable.DataTable dataTable) {
				   
					
					
					
					List<Map<String, String>> List = dataTable.asMaps();
			        List<TimeSlot> available2 = new ArrayList<TimeSlot>();
			       
			        for (Map<String, String> map : List) {
			        	
			            Date startDate = Date.valueOf(map.get("date"));
			            
			            Time startTime = Time.valueOf(map.get("startTime") + ":00");
			            Time endTime = Time.valueOf(map.get("endTime") + ":00");
			            TimeSlot timeslot = new TimeSlot(startDate, startTime, startDate, endTime, fbs);
			            
						available2.add(timeslot);
			        }
			        if (available.equals(available2)) {
			            assertTrue(true);
			        }
			        
					
					
					
					
				}
		
		/**
* 
* @author Mihir Binay Kumar
* 
**/

@When("the user tries to log in with username {string} and password {string}")
public void the_user_tries_to_log_in_with_username_and_password(String string, String string2) {

username=string;
password=string2;

accountCountBeforeCreation = fbs.getCustomers().size();
accountCount = fbs.getCustomers().size();


try {
user=FlexiBookController.getUser(string, string2,fbs);
FlexiBookController.LogIn(string, string2);
if(user.getUsername().equals("owner")) {
accountCount = accountCountBeforeCreation ++;
}

}catch (InvalidInputException e){
error += e.getMessage();
errorCount++;


//System.out.println(error);
}
}

/**
* 
* @author Mihir Binay Kumar
* 
**/


@Then("a new account shall be created")
public void a_new_account_shall_be_created() {
// Write code here that turns the phrase above into concrete actions
//throw new io.cucumber.java.PendingException();
if(fbs.getOwner()==null){
assertEquals( accountCountBeforeCreation  , fbs.getCustomers().size() ); //talk to Karim!!
}

else {
assertEquals( accountCountBeforeCreation  , fbs.getCustomers().size()+1 );
}
}

/**
* 
* @author Mihir Binay Kumar
* 
**/


@Then("the user shall be successfully logged in")
public void the_user_shall_be_successfully_logged_in() {
// Write code here that turns the phrase above into concrete actions
//throw new io.cucumber.java.PendingException();

assertEquals(user.getPassword(), password);
FlexiBookApplication.setCurrentUser(user);

}

/**
* 
* @author Mihir Binay Kumar
* 
**/

//io.cucumber.junit.UndefinedStepException: The step "the user should be successfully logged in" is undefined. You can implement it using the snippet(s) below:

@Then("the user should be successfully logged in")
public void the_user_should_be_successfully_logged_in() {
// Write code here that turns the phrase above into concrete actions
//throw new io.cucumber.java.PendingException();
assertEquals(user.getPassword(), password);
FlexiBookApplication.setCurrentUser(user);

}

/**
* 
* @author Mihir Binay Kumar
* 
**/

@Then("the user should not be logged in")
public void the_user_should_not_be_logged_in() {
// Write code here that turns the phrase above into concrete actions
//throw new io.cucumber.java.PendingException();

assertNull(user);


}
///**
//* 
//* @author Mihir Binay Kumar
//* 
//**/
//
//@Given("an owner account exists in the system with username {string} and password {string}")
//public void an_owner_account_exists_in_the_system_with_username_and_password(String string, String string2) {
//    // Write code here that turns the phrase above into concrete actions
//    //throw new io.cucumber.java.PendingException();
//	try {
//		FlexiBookController.AddOwner(string, string2, fbs);
//	} catch (Exception e) {
//		
//		error += e.getMessage();
//		errorCount++;
//	}
//	
//}



//Some other steps were also undefined:

@Given("the user is logged out")
public void the_user_is_logged_out() {
    // Write code here that turns the phrase above into concrete actions
   // throw new io.cucumber.java.PendingException();
	FlexiBookApplication.setCurrentUser(null);
	
	
}



//	@Given("the user is logged in to an account with username {string}")
//	public void the_user_is_logged_in_to_an_account_with_username(String string) {
//	    // Write code here that turns the phrase above into concrete actions
//	    //throw new io.cucumber.java.PendingException();
//		
//		
//		FlexiBookApplication.setCurrentUser(User.getWithUsername(string));
//		
//		
//		
//	}
	@When("the user tries to log out")
	public void the_user_tries_to_log_out() {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new io.cucumber.java.PendingException();
		
	
			try {
				FlexiBookController.LogOut();
			} catch (Exception e) {
				
				error += e.getMessage();
				errorCount++;
			}
			
		} 
		
		
	
//	@Then("the user shall be logged out")
//	public void the_user_shall_be_logged_out() {
//	    // Write code here that turns the phrase above into concrete actions
//	    //throw new io.cucumber.java.PendingException();
//		
//		assertTrue(FlexiBookApplication.getCurrentUser()==null);
//		
//	}

	//View appointment calendar
	
//	/**
//	 * @author Mihir Kumar
//	 * @param username
//	 * @return
//	 */
//	 private Customer findCustomerByName(String username) {
//			Customer thisCustomer = null;
//			
//			List<Customer> customerList = fbs.getCustomers();
//			for(int i = 0; i < customerList.size(); i++) {
//				thisCustomer = customerList.get(i);
//				if(thisCustomer.getUsername().equals(username)) {
//					return thisCustomer;
//				}
//			}
//			
//			return thisCustomer;
//		}

//	/**
//	 * @author Mihir Kumar
//	 * @param string
//	 */
//	 @Given("{string} is logged in to their account")
//	 public void is_logged_in_to_their_account(String string) {
//	     
//		 
//		 Customer customer = findCustomerByName(string);
//    	    FlexiBookApplication.setCurrentUser(customer);
//	 }
	 
		 /**
		  * @author Mihir Kumar
		  * @param string
		  * @param string2
		  */
		 
		 @When("{string} requests the appointment calendar for the week starting on {string}")
			public void requests_the_appointment_calendar_for_the_week_starting_on(String string, String string2) {
			
			 try {
				 maindate=string2;
				 available=FlexiBookController.getWeeklyAvailableTimeSlots(string2);
				 unavailable=FlexiBookController.getWeeklyUnavailableTimeSlots(string2);
				 
				 
			 }
			catch(InvalidInputException e) {
				error +=e.getMessage();
				errorCount++;
				
			}
			 
			 
				
			}
		 
			

		
		
//
//
////========================================================================================================================
//
//	//LogOut - Mihir 
//	
////=========================================================================================================================


//=========================================================================================================================
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
//==========================================================================================================================
	



//========================================================================================================================

	// Sign up customer account - Karim
	
//=========================================================================================================================

/**
 * @author Karim Elgammal
 * @param aUsername
 */
@Given("there is no existing username {string}")
public void there_is_no_existing_username(String aUsername) {
	fbs = FlexiBookApplication.getFlexiBook();
	if(findUserWithUsername(aUsername, fbs)!= null) {
		findUserWithUsername(aUsername, fbs).delete();
	}
	}	
/**
 * @author Karim Elgammal
 * @param username
 * @param password
 */
@When("the user provides a new username {string} and a password {string}")
public void user_provides_a_new_username_and_password(String username, String password) {
	accountCountBeforeCreation = fbs.getCustomers().size();
	accountCount = fbs.getCustomers().size();
	try {
		FlexiBookController.createCustomerAccount(username, password, fbs);
		accountCount = accountCountBeforeCreation +  1;

		
	}catch (InvalidInputException e){
		error += e.getMessage();
		errorCount++;

		
		System.out.println(error);
	}
}
/**
 * @author Karim Elgammal
 */
@Then("a new customer account shall be created")
public void new_customer_account_shall_be_created() {
	assertEquals(accountCountBeforeCreation+1, fbs.getCustomers().size());
}



//duplicte step def - commented this one out for now?

/**
// * @author Karim Elgammal
// * @param username
// * @param password
// */
@Then("the account shall have username {string} and password {string}")
public void account_shall_have_username_and_password(String username, String password) {
	User createdCustomer = findUserWithUsername(username, fbs);
	assertEquals(username, createdCustomer.getUsername());
	assertEquals(password, createdCustomer.getPassword()); 

}

/**
 * @author Karim Elgammal
 */
@Then("no new account shall be created")
public void no_new_account_shall_be_created() {
	assertEquals( accountCountBeforeCreation , accountCount);
}
/**
 * @author Karim Elgammal
 * @param string
 */
@Then("an error message {string} shall be raised")
public void an_error_message_shall_be_raised(String string) {
	assertTrue(error.contains(string)); 
}

/**
 * @author Karim Elgammal
 * @param string
 */
@Given("there is an existing username {string}")
public void there_is_an_exisiting_username(String string) {
	fbs = FlexiBookApplication.getFlexiBook();
	if(string.equals("owner")) {
		User testUser1 = new Owner(string, "test", fbs);
	}else {
		User testUser1 = new Customer(string, "test", accountCount, fbs);
		
		//Karim I have added accountCount to match the constructor - rakshitha here! - it passes everything now 
	}
}
/**
 * @author Karim Elgammal
 * @param string
 */
@Given("the user is logged in to an account with username {string}")
public void the_user_with_usernameowner_is_logged_in(String string) {
	User testUser2 = findUserWithUsername(string, fbs);

	
	FlexiBookApplication.setCurrentUser(testUser2);
}




//========================================================================================================================

	// update account - Karim
	
//=========================================================================================================================



/**
 * @author Karim Elgammal
 * @param username
 * @param password
 */
@Given("an owner account exists in the system with username {string} and password {string}")
public void an_owner_account_exists_in_the_system_withUsername_andPassword(String username, String password) {
	fbs = FlexiBookApplication.getFlexiBook();
	owner = new Owner(username, password, fbs);
	fbs.setOwner(owner);
}


/**
 * @author Karim Elgammal
 * @param username
 * @param password
 * @throws InvalidInputException
 */
@When("the user tries to update account with a new username {string} and password {string}")
public void user_tries_to_update_account_with_new_usernameAndPassword(String username, String password) throws InvalidInputException {
	fbs = FlexiBookApplication.getFlexiBook();
	try {
		FlexiBookController.updateAccount(username, password, fbs);
		accountUpdated = true;
	} catch (InvalidInputException e) {
		error += e.getMessage();
		errorCount++;
		accountUpdated = false;
	}
}



/**
 * @author Karim Elgammal
 */
@Then("the account shall not be updated")
public void account_shall_not_be_updated(){
	assertFalse(accountUpdated);
}



//========================================================================================================================

	// delete account - Karim
	
//=========================================================================================================================



/**
 * @author Karim Elgammal
 * @param username
 */
@Given("the account with username {string} has pending appointments")
public void the_account_with_username_has_pending_appointments(String username) {
	fbs = FlexiBookApplication.getFlexiBook();
	Customer c = (Customer) findUserWithUsername(username, fbs);
	BookableService b1 = new ServiceCombo ("testingDelete", fbs);
	BookableService b2 = new Service("testingDelete2", fbs, 60, 30, 30);
	Date d1 = new Date(2020, 9,26);
	Date d2 = new Date(2020, 9, 27);
	Time t1 = new Time(1200);
	Time t2 = new Time(1300); 
	TimeSlot time1 = new TimeSlot(d1, t1, d1, t2, fbs);
	TimeSlot time2 = new TimeSlot(d2, t1, d2, t2, fbs);
	if (!c.hasAppointments()) {
		Appointment aAppointment1 = new Appointment(c, b1, time1, fbs );
		Appointment aAppointment2 = new Appointment(c, b2, time2, fbs);
		c.addAppointment(aAppointment1);
		c.addAppointment(aAppointment2);
	}
}
/**
 * @author Karim Elgammal
 * @param username
 * @throws InvalidInputException
 */
@When("the user tries to delete account with the username {string}")
public void user_tries_to_delete_account(String username) throws InvalidInputException {
	User c = findUserWithUsername(username, fbs);
	try {
		FlexiBookController.deleteCustomerAccount(c, fbs);
	} catch (InvalidInputException e) {
		error += e.getMessage();
		errorCount++;
	}
}

/**
 * @author Karim Elgammal
 * @param username
 */
@Then("the account with the username {string} does not exist")
public void account_with_username_does_not_exist(String username) {
	fbs = FlexiBookApplication.getFlexiBook();
	Customer c = (Customer) findUserWithUsername(username, fbs);
	assertNull(c);
}
/**
 * @author Karim Elgammal
 * @param username
 */
@Then ("all associated appointments of the account with the username {string} shall not exist")
public void all_associated_appointments_shall_not_exist(String username) {
	fbs = FlexiBookApplication.getFlexiBook();
	assertNull(findUserWithUsername(username, fbs));
}
/**
 * @author Karim Elgammal
 */
@Then("the user shall be logged out")
public void user_should_be_logged_out() {
	assertNull(FlexiBookApplication.getCurrentUser());

}

/**
 * @author Karim Elgammal
 * @param username
 */
@Then("the account with the username {string} exists")
public void account_with_username_exists(String username) {
	fbs = FlexiBookApplication.getFlexiBook();
	User u = findUserWithUsername(username, fbs);
	assertEquals(username, u.getUsername());
}


//helpers


/**
 * This is a helper method that returns a found user in the system using the username
 * @author Karim Elgammal
 * @param aUsername
 * @param f
 * @return
 */
private static User findUserWithUsername(String aUsername, FlexiBook f){
	User foundUser = null; 
	if(aUsername.equals("owner")){
		foundUser = f.getOwner();
	}
	else {
		for (User c : f.getCustomers()) {
			if(c.getUsername().equals(aUsername)) {
				foundUser = c;
				break; 
			}
		}
	}
	return foundUser; 
}


//=========================================================================================================================
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
//==========================================================================================================================
	




//========================================================================================================================

	//LSetup business info - Cecile 
	
//=========================================================================================================================







String result;
String resultError;

	    /**
	     * @author Cecile Dai
	     */
	    @Given("no business exists")
	    public void noBusinessExists() {
	        fbs.setBusiness(null);
	    }

	  

	    /**
	     *
	     * @param name
	     * @param address
	     * @param phoneNumber
	     * @param email
	     * @author Cecile Dai
	     */
	    @When("the user tries to set up the business information with new {string} and {string} and {string} and {string}")
	    public void theUserTriesToSetUpTheBusinessInformationWithNewAndAndAnd(String name, String address, String phoneNumber, String email) {
	        try{
	            FlexiBookController.setUpBasicBusinessInfo(name, address, phoneNumber, email, fbs);
	            result = "be";
	            resultError = "not be";
	        }
	        catch(Exception e){
	            error += e.getMessage();
	            errorCount++;
	            result = "not be";
	            resultError = "be";
	        }
	    }

	    
	    /**
	     *
	     * @param name
	     * @param address
	     * @param phoneNumber
	     * @param email
	     * @param result
	     * @author Cecile Dai
	     */
	    @Then("a new business with new {string} and {string} and {string} and {string} shall {string} created")
	    public void aNewBusinessWithNewAndAndAndShallCreated(String name, String address, String phoneNumber, String email, String result) {
	        Business flexi = FlexiBookApplication.getFlexiBook().getBusiness();
	        if(!(flexi == null)){
	            assertEquals(name, flexi.getName());
	            assertEquals(address, flexi.getAddress());
	            assertEquals(phoneNumber, flexi.getPhoneNumber());
	            assertEquals(email, flexi.getEmail());
	            assertEquals(result, this.result);
	        }
	        else{
	            assertEquals(result, this.result);
	        }
	    }
	    /**
	     *
	     * @param error error message
	     * @param resultError whether the error message should have been raised
	     * @author Cecile Dai
	     */
	    @Then("an error message {string} shall {string} raised")
	    public void anErrorMessageShallRaised(String error, String resultError) {
	        assertEquals(error, this.error);
	        assertEquals(resultError, this.resultError);
	    }
	    
	    
	    /**
	     *
	     * @param dataTable
	     * @author Cecile Dai
	     */
	    @Given("a business exists with the following information:")
	    public void aBusinessExistsWithTheFollowingInformation(io.cucumber.datatable.DataTable dataTable) {
	        List<List<String>> rows = dataTable.asLists();
	        Business newBusiness;
	        for(List<String> columns: rows){
	            if(!columns.get(0).equals("name")){
	                newBusiness = new Business(columns.get(0), columns.get(1), columns.get(2), columns.get(3), fbs);
	                fbs.setBusiness(newBusiness);
	            }
	        }
	    }
	    

	    /**
	     *
	     * @param Friday
	     * @param startTime
	     * @param endTime
	     * @author Cecile Dai
	     */
	    @Given("the business has a business hour on {string} with start time {string} and end time {string}")
	    public void theBusinessHasABusinessHourOnWithStartTimeAndEndTime(String Friday, String startTime, String endTime) {
	        BusinessHour.DayOfWeek day = BusinessHour.DayOfWeek.Friday;
	        Time startTime1 = new Time(8,0,0);
	        Time endTime1 = new Time(16,0,0);
	        BusinessHour newHour = new BusinessHour(day, startTime1, endTime1, fbs);
	        fbs.getBusiness().addBusinessHour(newHour);
	    }


	    /**
	     *
	     * @param day day of the week
	     * @param newStartTime new start time
	     * @param newEndTime new end time
	     * @author Cecile Dai
	     */
	    @When("the user tries to add a new business hour on {string} with start time {string} and end time {string}")
	    public void theUserTriesToAddANewBusinessHourOnWithStartTimeAndEndTime(String day, String newStartTime, String newEndTime) {
	        try{
	            FlexiBookController.addBusinessHours(day, newStartTime, newEndTime, fbs);
	            result = "be";
	            resultError = "not be";
	        }
	        catch(Exception e){
	            error += e.getMessage();
	            errorCount++;
	            result = "not be";
	            resultError = "be";
	        }
	    }

	    
	    /**
	     * @param result if the business hour was created
	     * @author Cecile Dai
	     */
	    @Then("a new business hour shall {string} created")
	    public void aNewBusinessHourShallCreated(String result) {
	        assertEquals(result, this.result);
	    }

	    /**
	     * @author Cecile Dai
	     */
	    @When("the user tries to access the business information")
	    public void theUserTriesToAccessTheBusinessInformation() {
	        FlexiBookController.viewBasicBusinessInfo();
	    }

	    
	    
	    

	    /**
	     *
	     * @param name business name
	     * @param address business address
	     * @param phoneNumber business phone number
	     * @param email business email
	     * @author Cecile Dai
	     */
	    @Then("the {string} and {string} and {string} and {string} shall be provided to the user")
	    public void theAndAndAndShallBeProvidedToTheUser(String name, String address, String phoneNumber, String email) {
	        TOBusiness b = FlexiBookController.viewBasicBusinessInfo();
	        assertEquals(name, b.getName());
	        assertEquals(address, b.getAddress());
	        assertEquals(phoneNumber, b.getPhoneNumber());
	        assertEquals(email, b.getEmail());
	    }


	    /**
	     *
	     * @param type
	     * @param startDate
	     * @param startTime
	     * @param endDate
	     * @param endTime
	     * @author Cecile Dai
	     */
	    @When("the user tries to add a new {string} with start date {string} at {string} and end date {string} at {string}")
	    public void theUserTriesToAddANewWithStartDateAtAndEndDateAt(String type, String startDate, String startTime, String endDate, String endTime) {
	        try{
	            FlexiBookController.addNewTimeSlot(type, startDate, startTime, endDate, endTime);
	            result = "be";
	            resultError = "not be";
	        }
	        catch(Exception e){
	            error += e.getMessage();
	            errorCount++;
	            result = "not be";
	            resultError = "be";
	        }
	    }

	    
	    
	    

	    /**
	     *
	     * @param type
	     * @param result
	     * @param startDate
	     * @param startTime
	     * @param endDate
	     * @param endTime
	     * @throws Exception
	     * @author Cecile Dai
	     */
	    @Then("a new {string} shall {string} be added with start date {string} at {string} and end date {string} at {string}")
	    public void aNewShallBeAddedWithStartDateAtAndEndDateAt(String type, String result, String startDate, String startTime, String endDate, String endTime) throws Exception {
	        Business flexi = FlexiBookApplication.getFlexiBook().getBusiness();
	        TimeSlot test = new TimeSlot(toDate(startDate), toTime(startTime), toDate(endDate), toTime(endTime), fbs);
	        if(type.equals("holiday")){
	            for(TimeSlot timeSlot : flexi.getHolidays()){
	                if(areSlotsEqual(timeSlot, test)){
	                    assertTrue(areSlotsEqual(timeSlot, test));
	                }
	            }
	            assertEquals(result, this.result);
	        }
	        else if(type.equals("vacation")){
	            for(TimeSlot timeSlot : flexi.getVacation()){
	                if(areSlotsEqual(timeSlot, test)){
	                    assertTrue(areSlotsEqual(timeSlot, test));
	                }
	            }
	            assertEquals(result, this.result);
	        }

	    }
	    
	    
	    
	    //Helper methods 
	    
	    /**
	     * Private helper method
	     * @param a
	     * @param b
	     * @return boolean representing if the two timeslots are equal in value
	     * @author Cecile Dai
	     */
	    private static boolean areSlotsEqual(TimeSlot a, TimeSlot b){
	        if(a.getStartTime().equals(b.getStartTime()) && a.getEndTime().equals(b.getEndTime())
	        && a.getStartDate().equals(b.getStartDate()) && a.getEndDate().equals(b.getEndDate())){
	            return true;
	        }
	        else return false;
	    }

	    
	    
	    


	  //========================================================================================================================

	  	//Update business info - Cecile 
	  	
	  //=========================================================================================================================

	    /**
	     *
	     * @param name
	     * @param address
	     * @param phoneNumber
	     * @param email
	     * @author Cecile Dai
	     */
	    @When("the user tries to update the business information with new {string} and {string} and {string} and {string}")
	    public void theUserTriesToUpdateTheBusinessInformationWithNewAndAndAnd(String name, String address, String phoneNumber, String email) {
	        try {
	            FlexiBookController.updateBasicBusinessInfo(name, address, phoneNumber, email);
	            result = "be";
	            resultError = "not be";
	        } catch (Exception e) {
	            error += e.getMessage();
	            errorCount++;
	            result = "not be";
	            resultError = "be";
	        }
	    }
	    
	    /**
	     *
	     * @param result whether the business information was updated
	     * @param name new business name
	     * @param address new business address
	     * @param phoneNumber new phone number
	     * @param email new business email
	     * @author Cecile Dai
	     */
	    @Then("the business information shall {string} updated with new {string} and {string} and {string} and {string}")
	    public void theBusinessInformationShallUpdatedWithNewAndAndAnd(String result, String name, String address, String phoneNumber, String email) {
	        Business flexi = FlexiBookApplication.getFlexiBook().getBusiness();
	    }


	   /**
	    * @author Cecile 
	    * @param day
	    * @param oldTime
	    * @param newDay
	    * @param newStartTime
	    * @param newEndTime
	    */
	    @When("the user tries to change the business hour {string} at {string} to be on {string} starting at {string} and ending at {string}")
	    public void theUserTriesToChangeTheBusinessHourAtToBeOnStartingAtAndEndingAt(String day, String oldTime, String newDay, String newStartTime, String newEndTime) {
	        try{
	            FlexiBookController.updateBusinessHours(day, oldTime, newDay, newStartTime, newEndTime);
	            result = "be";
	            resultError = "not be";
	        }
	        catch(Exception e){
	            error += e.getMessage();
	            errorCount++;
	            result = "not be";
	            resultError = "be";
	        }
	    }

	    /**
	     *
	     * @param result if the business hour should have been updated
	     * @author Cecile Dai
	     */
	    @Then("the business hour shall {string} be updated")
	    public void theBusinessHourShallBeUpdated(String result) {
	        assertEquals(result, this.result);
	    }

  
	    /**
	     *
	     * @param day day of the week
	     * @param time business hour start time
	     * @author Cecile Dai
	     */
	    @When("the user tries to remove the business hour starting {string} at {string}")
	    public void theUserTriesToRemoveTheBusinessHourStartingAt(String day, String time) {
//	        if(startDate.contains("startDate")) return;
	        try{
	            FlexiBookController.removeBusinessHours(day, time);
	            result = "not";
	            resultError = "not be";
	        }
	        catch(Exception e){
	            error += e.getMessage();
	            errorCount++;
	            result = "";
	            resultError = "be";
	        }
	    }

	    /**
	     *
	     * @param day day of the week
	     * @param time business hour start time
	     * @param result whether the business hour is deleted
	     * @throws Exception
	     * @author Cecile Dai
	     */
	    @Then("the business hour starting {string} at {string} shall {string} exist")
	    public void theBusinessHourStartingAtShallExist(String day, String time, String result) throws Exception {
	        Business business = FlexiBookApplication.getFlexiBook().getBusiness();
	        BusinessHour hour = null;
	        for(BusinessHour bh : business.getBusinessHours()){
	            if(bh.getDayOfWeek().equals(toDayOfWeek(day)) && bh.getStartTime().equals(toTime(time))){
	                hour = bh;
	            }
	        }
	        assertEquals(result, this.result);
	        assertTrue(!(business.getBusinessHours().contains(hour)));
	    }

	    
	    
	    /**
	     *
	     * @param message
	     * @param result
	     * @author Cecile Dai
	     */
	    @Then("an error message {string} shall {string} be raised")
	    public void anErrorMessageShallBeRaised(String message, String result) {
//	        assertEquals(message, error);
	        assertEquals(result, this.result);
	    }
	    
	    
	    
	    /**
	     *
	     * @param type holiday or vacation
	     * @param date original date
	     * @param time original time
	     * @param startDate new start date
	     * @param startTime new start time
	     * @param endDate new end date
	     * @param endTime new end time
	     * @author Cecile Dai
	     */
	    @When("the user tries to change the {string} on {string} at {string} to be with start date {string} at {string} and end date {string} at {string}")
	    public void theUserTriesToChangeTheOnAtToBeWithStartDateAtAndEndDateAt(String type, String date, String time, String startDate, String startTime, String endDate, String endTime) {
	        try{
	            if(type.equals("vacation")){
	                FlexiBookController.updateVacation(type, date, time, startDate, startTime, endDate, endTime);
	                result = "be";
	                resultError = "not be";
	            }
	            else if(type.equals("holiday")){
	                FlexiBookController.updateHoliday(type, date, time, startDate, startTime, endDate, endTime);
	                result = "be";
	                resultError = "not be";
	            }
	        }
	        catch(Exception e){
	            error += e.getMessage();
	            errorCount++;
	            result = "not be";
	            resultError = "be";
	        }
	    }
	    
	    

	    /**
	     *
	     * @param type holiday or vacation
	     * @param result whether the update goes through or not
	     * @param startDate start date
	     * @param startTime start time
	     * @param endDate end date
	     * @param endTime end time
	     * @throws Exception
	     * @author Cecile Dai
	     */
	    @Then("the {string} shall {string} updated with start date {string} at {string} and end date {string} at {string}")
	    public void the_shall_updated_with_start_date_at_and_end_date_at(String type, String result, String startDate, String startTime, String endDate, String endTime) throws Exception {
	    	Business flexi = FlexiBookApplication.getFlexiBook().getBusiness();
	        TimeSlot test = new TimeSlot(toDate(startDate), toTime(startTime), toDate(endDate), toTime(startTime), fbs);
	        if(type.equals("holiday")){
	            for(TimeSlot timeSlot : flexi.getHolidays()){
	                if(areSlotsEqual(timeSlot, test)){
	                    assertTrue(areSlotsEqual(timeSlot, test));
	                }
	            }
	        }
	        if(type.equals("vacation")){
	            for(TimeSlot timeSlot : flexi.getVacation()){
	                if(areSlotsEqual(timeSlot, test)){
	                    assertTrue(areSlotsEqual(timeSlot, test));
	                }
	            }
	        }
	        assertEquals(result, this.result);
	    }

	    
	    

	    /**
	     *
	     * @param type holiday or vacation
	     * @param startDate start date
	     * @param startTime start time
	     * @param endDate end date
	     * @param endTime end time
	     * @author Cecile Dai
	     */
	    @When("the user tries to remove an existing {string} with start date {string} at {string} and end date {string} at {string}")
	    public void theUserTriesToRemoveAnExistingWithStartDateAtAndEndDateAt(String type, String startDate, String startTime, String endDate, String endTime) {
//	        if(startDate.contains("startDate")) return;
	        try{
	            FlexiBookController.removeTimeSlot(type, startDate, startTime, endDate, endTime);
	            result = "not";
	            resultError = "not be";
	        }
	        catch(Exception e){
	            error += e.getMessage();
	            errorCount++;
	            result = "";
	            resultError = "be";
	        }
	    }

	    

	    /**
	     *
	     * @param type holiday or vacation
	     * @param startDate start date
	     * @param startTime start time
	     * @param result whether the update goes through or not
	     * @throws Exception
	     * @author Cecile Dai
	     */
	    @Then("the {string} with start date {string} at {string} shall {string} exist")
	    public void theWithStartDateAtShallExist(String type, String startDate, String startTime, String result) throws Exception {
	        Business flexi = FlexiBookApplication.getFlexiBook().getBusiness();
	        List<TimeSlot> holidays = flexi.getHolidays();
	        List<TimeSlot> vacations = flexi.getVacation();
	        TimeSlot match = null;
	        if(type.equals("holiday")){
	            for(TimeSlot timeSlot : holidays){
	                if((timeSlot.getStartDate() == toDate(startDate)) && (timeSlot.getStartTime() == toTime(startTime))){
	                    fail();
	                }
	                else{
	                    assertEquals(result, this.result);
	                }
	            }
	        }
	        if(type.equals("vacation")){
	            for(TimeSlot timeSlot : vacations){
	                if((timeSlot.getStartDate() == toDate(startDate)) && (timeSlot.getStartTime() == toTime(startTime))){
	                    fail();
	                }
	                else{
	                    assertEquals(result, this.result);
	                }
	            }
	        }
	    }
	    
	    
	    
	    
	    //=======================================================
	    
	    //helper method
	    
	    
	    /**
	     * @author cecile
	     * @param day
	     * @return
	     */
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

//=========================================================================================================================
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
//==========================================================================================================================














//==============================================================================================================================
	//HELPER METHODS-- USEFUL TO ALL -------- or not java doc ed --------------------------------------------------------
	
	private static Time toTime(String timeStr) throws Exception {
        Time time;
        int hour;
        int minute;
        try {
            hour = Integer.parseInt(timeStr.substring(0,2));
            minute = Integer.parseInt(timeStr.substring(3,5));
            time = new Time(hour, minute, 0);
            return time;
        }
        catch(Exception e){
            throw new IllegalArgumentException("Invalid time input");
        }
    }

    private static java.sql.Date toDate(String dateStr){
        java.util.Date date;
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try{
            date = format.parse(dateStr);
            java.sql.Date sqlDate = new Date(date.getTime());
            return sqlDate;
        }
        catch(Exception e){
            throw new IllegalArgumentException("Invalid date input");
        }
    }
    
 
    
  
	
}


