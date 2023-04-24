package ca.mcgill.ecse.flexibook.view;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.WindowConstants;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;

import javax.swing.GroupLayout;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;

import ca.mcgill.ecse.flexibook.application.FlexiBookApplication;
import ca.mcgill.ecse.flexibook.controller.*;
import ca.mcgill.ecse.flexibook.model.Appointment;
import ca.mcgill.ecse.flexibook.model.BookableService;
import ca.mcgill.ecse.flexibook.model.BusinessHour;
import ca.mcgill.ecse.flexibook.model.Customer;
import ca.mcgill.ecse.flexibook.model.FlexiBook;
import ca.mcgill.ecse.flexibook.model.Service;
import ca.mcgill.ecse.flexibook.model.TimeSlot;
import ca.mcgill.ecse.flexibook.model.User;

public class FbsPage extends JFrame {

	// UI elements
	private JLabel errorMessage;
	private JLabel errorAppt;
	private JTabbedPane jTabbedPane;
	private JPanel servicesTab;
	private JPanel appointmentTab;
	private JPanel customerTab;
	private JPanel businessTab;
	private JPanel loginTab;

	// ==== Cecile's Fields =============

	// Business Information
	private JLabel businessNameLabel;
	private JLabel addressLabel;
	private JLabel phoneNumberLabel;
	private JLabel emailLabel;
	private JLabel contactInfoLabel;
	private JButton updateBusinessInfoButton;
	private JOptionPane updateBusinessInfoPane;
	private JPanel updateBusinessInfoPanel;
	private JLabel updateBusinessNameLabel;
	private JLabel updateAddressLabel;
	private JLabel updatePhoneNumberLabel;
	private JLabel updateEmailLabel;
	private JTextField updateBusinessNameField;
	private JTextField updateAddressField;
	private JTextField updatePhoneNumberField;
	private JTextField updateEmailField;

	// Times overview tables
	private JTable businessHoursTable;
	private JTable holidaysTable;
	private JTable vacationsTable;
	private JScrollPane businessHoursScrollPane;
	private JScrollPane holidaysScrollPane;
	private JScrollPane vacationsScrollPane;
	private DefaultTableModel overviewDtm;
	private String businessHoursColumnName[] = { "Business Hours" };
	private String holidaysColumnName[] = { "Holidays" };
	private String vacationsColumnName[] = { "Vacations" };
	private JPanel timesOverviewPanel;
	private JPanel addButtonsPanel;
	private static final int HEIGHT_OVERVIEW_TABLE = 200;

	// Business Hours
	private JLabel businessHoursLabel;
	// Add business hour
	private JButton addBusinessHourButton;
	private JOptionPane addBusinessHourPane;
	private JPanel addBusinessHourPanel;
	private JLabel setWeekDayLabel;
	private JLabel setBHStartTimeLabel;
	private JLabel setBHEndTimeLabel;
	private JComboBox<String> weekDaysComboBox;
	private JTextField setWeekDayField;
	private JTextField setBHStartTimeField;
	private JTextField setBHEndTimeField;
	// Update and remove business hour
	private String[] businessHoursArray;
	private JLabel removeBusinessHourLabel;
	private JLabel updateBusinessHourLabel;
	private JButton removeBusinessHourButton;
	private JButton updateBusinessHourButton;
	private JComboBox<String> removeBusinessHourComboBox;
	private JComboBox<String> updateBusinessHourComboBox;
	private JOptionPane removeBusinessHourPane;
	private JPanel removeBusinessHourPanel;
	private JOptionPane updateBusinessHourPane;
	private JPanel updateBusinessHourPanel;
	private JLabel setNewWeekDayLabel;
	private JComboBox<String> newWeekDaysComboBox;
	private JLabel setNewBHStartTimeLabel;
	private JLabel setNewBHEndTimeLabel;
	private JTextField setNewBHStartTimeField;
	private JTextField setNewBHEndTimeField;

	// Holidays
	private JLabel holidaysLabel;
	// Add holiday
	private JButton addHolidayButton;
	private JOptionPane addHolidayPane;
	private JPanel addHolidayPanel;
	private JLabel setHolidayStartDateLabel;
	private JLabel setHolidayStartTimeLabel;
	private JLabel setHolidayEndDateLabel;
	private JLabel setHolidayEndTimeLabel;
	private JTextField setHolidayStartTimeField;
	private JTextField setHolidayEndTimeField;
	private JDatePickerImpl holidayStartDatePicker;
	private JDatePickerImpl holidayEndDatePicker;
	private JLabel holidayStartDatePickerLabel;
	private JLabel holidayEndDatePickerLabel;
	// Update and remove holiday
	private String[] holidaysArray;
	private JButton removeHolidayButton;
	private JButton updateHolidayButton;
	private JLabel removeHolidayLabel;
	private JLabel updateHolidayLabel;
	private JComboBox<String> removeHolidayComboBox;
	private JComboBox<String> updateHolidayComboBox;
	private JOptionPane removeHolidayPane;
	private JPanel removeHolidayPanel;
	private JOptionPane updateHolidayPane;
	private JPanel updateHolidayPanel;
	private JLabel setNewHolidayStartTimeLabel;
	private JLabel setNewHolidayEndTimeLabel;
	private JTextField setNewHolidayStartTimeField;
	private JTextField setNewHolidayEndTimeField;
	private JDatePickerImpl newHolidayStartDatePicker;
	private JDatePickerImpl newHolidayEndDatePicker;
	private JLabel newHolidayStartDatePickerLabel;
	private JLabel newHolidayEndDatePickerLabel;
	private JLabel setNewHolidayStartDateLabel;
	private JLabel setNewHolidayEndDateLabel;

	// Vacations
	private JLabel vacationsLabel;
	// Add vacation
	private JButton addVacationButton;
	private JOptionPane addVacationPane;
	private JPanel addVacationPanel;
	private JLabel setVacationStartDateLabel;
	private JLabel setVacationStartTimeLabel;
	private JLabel setVacationEndDateLabel;
	private JLabel setVacationEndTimeLabel;
	private JTextField setVacationStartTimeField;
	private JTextField setVacationEndTimeField;
	private JDatePickerImpl vacationStartDatePicker;
	private JDatePickerImpl vacationEndDatePicker;
	private JLabel vacationStartDatePickerLabel;
	private JLabel vacationEndDatePickerLabel;
	// Update and remove vacation
	private String[] vacationsArray;
	private JButton removeVacationButton;
	private JButton updateVacationButton;
	private JLabel removeVacationLabel;
	private JLabel updateVacationLabel;
	private JComboBox<String> removeVacationComboBox;
	private JComboBox<String> updateVacationComboBox;
	private JOptionPane removeVacationPane;
	private JPanel removeVacationPanel;
	private JOptionPane updateVacationPane;
	private JPanel updateVacationPanel;
	private JLabel setNewVacationStartTimeLabel;
	private JLabel setNewVacationEndTimeLabel;
	private JTextField setNewVacationStartTimeField;
	private JTextField setNewVacationEndTimeField;
	private JDatePickerImpl newVacationStartDatePicker;
	private JDatePickerImpl newVacationEndDatePicker;
	private JLabel newVacationStartDatePickerLabel;
	private JLabel newVacationEndDatePickerLabel;
	private JLabel setNewVacationStartDateLabel;
	private JLabel setNewVacationEndDateLabel;

	// ==================== Ervin's Fields ===========================//

	private JTable sOverviewTable;

	// Service
	private JLabel AddServiceLabel;
	private JTextField serviceNameTextField;
	private JLabel serviceNameLabel;
	private JTextField serviceDurationTextField;
	private JLabel serviceDurationLabel;
	private JTextField serviceDownTimeStartTextField;
	private JLabel serviceDownTimeStartLabel;
	private JTextField serviceDownTimeTextField;
	private JLabel serviceDownTimeLabel;
	private JButton addServiceButton;

	private JLabel UpdateServiceLabel;
	private JComboBox<String> serviceList;;
	private JLabel selectServiceLabel;
	private JTextField newServiceNameTextField;
	private JLabel newServiceNameLabel;
	private JTextField newServiceDurationTextField;
	private JLabel newServiceDurationLabel;
	private JTextField newServiceDownTimeStartTextField;
	private JLabel newServiceDownTimeStartLabel;
	private JTextField newServiceDownTimeTextField;
	private JLabel newServiceDownTimeLabel;
	private JButton updateServiceButton;

	private JLabel DeleteServiceLabel;
	private JComboBox<String> deleteServiceList;;
	private JLabel deleteServiceListLabel;
	private JButton deleteServiceButton;

	private JScrollPane serviceScrollPane;
	// private DefaultTableModel overviewDtm;
	// private Vector<?> overviewColumnNames;
	// private static final int HEIGHT_OVERVIEW_TABLE = 200;

	// services
	private HashMap<Integer, String> availableServices;

	// services overview
	private DefaultTableModel serviceOverviewDtm;
	private String overviewColumnNames[] = { "Name", "Duration", "Downtime Start", "Downtime Duration" };

	// =================Natalia's fields===========================//
	private JDatePickerImpl appointmentDatePicker;
	private JLabel appointmentDateLabel;
	private JComboBox<String> apointmentToStartList;
	private JLabel apointmentToStartLabel;
	private JLabel selectStartAppointmentLabel;
	private JComboBox<String> selectStartAppointmentList;
	private JButton endAppointmentButton;
	private JComboBox<String> selectEndNoShowAppointmentList;
	private JButton startAppointmentButton;
	private JLabel selectEndNoShowAppointmentLabel;
	private JButton noShowAppointmentButton;
	private JLabel appointmentStartedLabel;
	private JLabel appointmentNoShowLabel;
	private JLabel appointmentEndedLabel;
	private JLabel appointmentStartedReturn;
	private JLabel appointmentEndedReturn;
	private JLabel appointmentNoShowReturn;
	private JLabel cancelAppointmentLabel;
	private JLabel makeAppointmentLabel;
	private JLabel updateAppointmentLabel;
	private JTable overviewTable;
	private JScrollPane appointmentScrollPane;
	private DefaultTableModel apptDtm;
	private String apptColumnNames[];
	private HashMap<Integer, TOAppointment> appointmentsToStartMap;
	private HashMap<Integer, TOAppointment> appointmentsToEndNoShowMap;

	// Error
//	private JLabel errorMessage;
	private String error = null;
	private String apptError = null;

	// ========================

	// ================= Karim's Fields ================//

	private JLabel usernameLabel;
	private JLabel passwordLabel;
	private JTextField usernameField;
	private JTextField passwordField;
	private JOptionPane deleteWarning;
	private JLabel customerManageInstructions;
	private JButton saveChangesButton;
	private JButton deleteCustomerAccountButton;

	// ==========================================//

	// =============== Mihir's Variables ====================

	private JTable appointmentTable;
//	private JScrollPane appointmentScrollPane;
	private DefaultTableModel appointmentDtm;
//	private static final int HEIGHT_OVERVIEW_TABLE = 200;
	private JLabel lblUsername, lblPassword;
	private JTextField textField;
	private JPasswordField passwordFieldM;
	private JButton LogInButton, LogoutButton;
//	private String error;
	private String appointmentColumnNames[] = { "Customer", "Service" ,  "Date", "Start Time", "End Time", "Status"};
//	private JDatePickerImpl appointmentDatePicker;
//	private JLabel appointmentDateLabel;

	// ===================================

	// ===================Rak Fields=========================//

	// =======================================================================================
	// added

	// for make update cancel

	private JButton makeAppointmentButton;
	private JButton updateAppointmentButton;
	private JButton cancelAppointmentButton;

	private JLabel makeAppointmentTimeLabel;
	private JLabel makeAppointmentDateLabel;
	private JLabel makeAppointmentServiceLabel;
	private JLabel updateAppointmentDateLabel;
	private JLabel updateAppointmentServiceLabel;
	private JLabel updateAppointmentNewTimeLabel;
	private JLabel updateAppointmentNewDateLabel;
	private JLabel cancelAppointmentDateLabel;

	private JTextField makeAppointmentTime;
	private JTextField makeAppointmentDate;
	private JTextField updateAppointmentNewTime;
	private JTextField updateAppointmentNewDate;

	private JComboBox<String> makeAppointmentServiceList;
	private JComboBox<String> updateAppointmentDateList;
	private JComboBox<String> updateAppointmentServiceList;
	private JComboBox<String> cancelAppointmentDateList;
	private HashMap<Integer, TOAppointment> appointmentsToUpdateMap;

	// =======================================================================================

	// =====================================

	public FbsPage() {

		final long serialVersionUID = -4426310869335015542L;

		initComponents();
		refreshData();

		// Business hours, holidays, and vacations overview table
		refreshBusinessHoursTable();
		refreshHolidaysTable();
		refreshVacationsTable();
	}

	private void refreshData() {

		// ============== Cecile's Refresh Data ===============

		// Error
		errorMessage.setText(error);

		if (error == null || error.length() == 0) {

//		System.out.println(FlexiBookController.viewBasicBusinessInfo().getName());
//		System.out.println(FlexiBookApplication.getFlexiBook().getBusiness().getName());

//			businessNameLabel = new JLabel();/
			businessNameLabel.setText("Business Name: " + FlexiBookController.viewBasicBusinessInfo().getName());
//			addressLabel = new JLabel();
			addressLabel.setText("Address: " + FlexiBookController.viewBasicBusinessInfo().getAddress());
//			phoneNumberLabel = new JLabel();
			phoneNumberLabel.setText("Phone Number: " + FlexiBookController.viewBasicBusinessInfo().getPhoneNumber());
//			emailLabel = new JLabel();
			emailLabel.setText("Email: " + FlexiBookController.viewBasicBusinessInfo().getEmail());

			updateBusinessNameField.setText("");
			updateAddressField.setText("");
			updatePhoneNumberField.setText("");
			updateEmailField.setText("");
			setBHStartTimeField.setText("");
			setBHEndTimeField.setText("");
			setHolidayStartTimeField.setText("");
			setHolidayEndTimeField.setText("");
			setVacationStartTimeField.setText("");
			setVacationEndTimeField.setText("");

			if (FlexiBookController.viewBusinessHours().size() != 0) {
				businessHoursArray = new String[FlexiBookController.viewBusinessHours().size()];
				for (int i = 0; i < FlexiBookController.viewBusinessHours().size(); i++) {
					TOBusinessHour current = FlexiBookController.viewBusinessHours().get(i);

					TOBusinessHour.DayOfWeek day = current.getDayOfWeek();
					String weekDay = null;

					switch (day) {
					case Monday:
						weekDay = "Monday";
						break;
					case Tuesday:
						weekDay = "Tuesday";
						break;
					case Wednesday:
						weekDay = "Wednesday";
						break;
					case Thursday:
						weekDay = "Thursday";
						break;
					case Friday:
						weekDay = "Friday";
						break;
					case Saturday:
						weekDay = "Saturday";
						break;
					case Sunday:
						weekDay = "Sunday";
						break;
					}

					String startTime = current.getStartTime().toString().substring(0, 5);
					String endTime = current.getEndTime().toString().substring(0, 5);

					String bhText = weekDay + " : " + startTime + " - " + endTime;
					businessHoursArray[i] = bhText;
				}
			}

			updateBusinessHourComboBox = new JComboBox<String>(businessHoursArray);
//			System.out.println("length is" + businessHoursArray.length);
			setNewBHStartTimeField.setText("");
			setNewBHEndTimeField.setText("");
//			if(businessHoursArray.length != 0) {
//				System.out.println(businessHoursArray[0]);
//			}
			updateBusinessHourPanel = new JPanel(new GridLayout(0, 1));
			updateBusinessHourPanel.add(updateBusinessHourLabel);
			updateBusinessHourPanel.add(updateBusinessHourComboBox);
			updateBusinessHourPanel.add(setNewWeekDayLabel);
			updateBusinessHourPanel.add(newWeekDaysComboBox);
			updateBusinessHourPanel.add(setNewBHStartTimeLabel);
			updateBusinessHourPanel.add(setNewBHStartTimeField);
			updateBusinessHourPanel.add(setNewBHEndTimeLabel);
			updateBusinessHourPanel.add(setNewBHEndTimeField);

			removeBusinessHourComboBox = new JComboBox<String>(businessHoursArray);

			removeBusinessHourPanel = new JPanel(new GridLayout(0, 1));
			removeBusinessHourPanel.add(removeBusinessHourLabel);
			removeBusinessHourPanel.add(removeBusinessHourComboBox);

			holidayStartDatePicker.getModel().setValue(null);
			holidayEndDatePicker.getModel().setValue(null);
			vacationStartDatePicker.getModel().setValue(null);
			vacationEndDatePicker.getModel().setValue(null);
			newHolidayStartDatePicker.getModel().setValue(null);
			newHolidayEndDatePicker.getModel().setValue(null);
			newVacationStartDatePicker.getModel().setValue(null);
			newVacationEndDatePicker.getModel().setValue(null);

			// Update holiday
//			ArrayList<TOTimeSlot> holidaysList = new ArrayList<>();
//			for(TOTimeSlot ts : FlexiBookController.viewVacations()) {
//				holidaysList.add(ts);
//			}

			if (FlexiBookController.viewHolidays().size() != 0) {
				String[] holidaysArray = new String[FlexiBookController.viewHolidays().size()];
				for (int i = 0; i < FlexiBookController.viewHolidays().size(); i++) {
					String times = "";
					TOTimeSlot current = FlexiBookController.viewHolidays().get(i);
					String startTime = current.getStartTime().toString().substring(0, 5);
					String endTime = current.getEndTime().toString().substring(0, 5);
					times = toEasyDate(current.getStartDate()) + " at " + startTime + " to "
							+ toEasyDate(current.getEndDate()) + " at " + endTime;
//				holidaysArray[i] = fromEasyDate(times).substring(0,10);
					holidaysArray[i] = times;
//				System.out.println(holidaysArray[i]);
				}
				updateHolidayComboBox = new JComboBox<String>(holidaysArray);
				removeHolidayComboBox = new JComboBox<String>(holidaysArray);
			} else {
				holidaysArray = new String[0];
				updateHolidayComboBox = new JComboBox<String>(holidaysArray);
				removeHolidayComboBox = new JComboBox<String>(holidaysArray);
			}

//			if(holidaysArray.length == 0) {
////				System.out.println("first array element is: " + holidaysArray[0]);
//				System.out.println("empty array");
//			}

//			updateHolidayComboBox = new JComboBox<String>(holidaysArray);
			setNewHolidayStartTimeField.setText("");
			;
			setNewHolidayEndTimeField.setText("");
			;

			updateHolidayPanel = new JPanel(new GridLayout(0, 2));
			updateHolidayPanel.add(updateHolidayLabel);
			updateHolidayPanel.add(updateHolidayComboBox);
			updateHolidayPanel.add(setNewHolidayStartDateLabel);
			updateHolidayPanel.add(setNewHolidayEndDateLabel);
			updateHolidayPanel.add(newHolidayStartDatePicker);
			updateHolidayPanel.add(newHolidayEndDatePicker);
			updateHolidayPanel.add(setNewHolidayStartTimeLabel);
			updateHolidayPanel.add(setNewHolidayStartTimeField);
			updateHolidayPanel.add(setNewHolidayEndTimeLabel);
			updateHolidayPanel.add(setNewHolidayEndTimeField);

//			removeHolidayComboBox = new JComboBox<String>(holidaysArray);

			removeHolidayPanel = new JPanel(new GridLayout(0, 1));
			removeHolidayPanel.add(removeHolidayLabel);
			removeHolidayPanel.add(removeHolidayComboBox);

			if (FlexiBookController.viewVacations().size() != 0) {
				String[] vacationsArray = new String[FlexiBookController.viewVacations().size()];
				for (int i = 0; i < FlexiBookController.viewVacations().size(); i++) {
					String times = "";
					TOTimeSlot current = FlexiBookController.viewVacations().get(i);
					String startTime = current.getStartTime().toString().substring(0, 5);
					String endTime = current.getEndTime().toString().substring(0, 5);
					times = toEasyDate(current.getStartDate()) + " at " + startTime + " to "
							+ toEasyDate(current.getEndDate()) + " at " + endTime;
//				holidaysArray[i] = fromEasyDate(times).substring(0,10);
					vacationsArray[i] = times;
//				System.out.println(holidaysArray[i]);
				}
				updateVacationComboBox = new JComboBox<String>(vacationsArray);
				removeVacationComboBox = new JComboBox<String>(vacationsArray);
			} else {
				vacationsArray = new String[0];
				updateVacationComboBox = new JComboBox<String>(vacationsArray);
				removeVacationComboBox = new JComboBox<String>(vacationsArray);
			}

			setNewVacationStartTimeField = new JTextField();
			setNewVacationEndTimeField = new JTextField();

			updateVacationPanel = new JPanel(new GridLayout(0, 2));
			updateVacationPanel.add(updateVacationLabel);
			updateVacationPanel.add(updateVacationComboBox);
			updateVacationPanel.add(setNewVacationStartDateLabel);
			updateVacationPanel.add(setNewVacationEndDateLabel);
			updateVacationPanel.add(newVacationStartDatePicker);
			updateVacationPanel.add(newVacationEndDatePicker);
			updateVacationPanel.add(setNewVacationStartTimeLabel);
			updateVacationPanel.add(setNewVacationStartTimeField);
			updateVacationPanel.add(setNewVacationEndTimeLabel);
			updateVacationPanel.add(setNewVacationEndTimeField);

			// remove vacation

			removeVacationPanel = new JPanel(new GridLayout(0, 1));
			removeVacationPanel.add(removeVacationLabel);
			removeVacationPanel.add(removeVacationComboBox);

			refreshBusinessHoursTable();
			refreshHolidaysTable();
			refreshVacationsTable();

//			String[] updateAppointmentArray = null;
//			if (FlexiBookApplication.getFlexiBook().getAppointments().size() > 0) {
//				updateAppointmentArray = new String[FlexiBookApplication.getFlexiBook().getAppointments().size()];
//				for (int i = 0; i < FlexiBookApplication.getFlexiBook().getAppointments().size(); i++) {
//					Appointment app = FlexiBookApplication.getFlexiBook().getAppointment(i);
//					String thisApp = app.getBookableService().getName() + " : " + app.getTimeSlot().getStartDate()
//							+ " at " + app.getTimeSlot().getStartTime();
//					updateAppointmentArray[i] = thisApp;
//				}
//				cancelAppointmentDateList = new JComboBox<String>(updateAppointmentArray);
//				updateAppointmentDateList = new JComboBox<String>(updateAppointmentArray);
//			} else {
//				updateAppointmentDateList = new JComboBox<String>(new String[0]);
//				cancelAppointmentDateList = new JComboBox<String>(new String[0]);
//			}

			// =============== Ervin's Refresh =====================//

			// populate page with data
			serviceNameTextField.setText("");
			serviceDurationTextField.setText("");
			serviceDownTimeStartTextField.setText("");
			serviceDownTimeTextField.setText("");
			newServiceNameTextField.setText("");
			newServiceDurationTextField.setText("");
			newServiceDownTimeStartTextField.setText("");
			newServiceDownTimeTextField.setText("");
			// serviceList.setSelectedIndex(-1);
			// deleteServiceList.setSelectedIndex(-1);

			// service list
			int index = 0;
			availableServices = new HashMap<Integer, String>();
			serviceList.removeAllItems();
			deleteServiceList.removeAllItems();
			makeAppointmentServiceList.removeAllItems();
			updateAppointmentServiceList.removeAllItems();

			index = 0;
			for (TOService service : FlexiBookController.getAvailableServices()) {
				availableServices.put(index, service.getName());
				serviceList.addItem(service.getName());
				deleteServiceList.addItem(service.getName());
				makeAppointmentServiceList.addItem(service.getName());
				updateAppointmentServiceList.addItem(service.getName());
				index++;
			}
			;
			serviceList.setSelectedIndex(-1);
			deleteServiceList.setSelectedIndex(-1);
			makeAppointmentServiceList.setSelectedIndex(-1);
			updateAppointmentServiceList.setSelectedIndex(-1);
		}
		refreshServiceTable();

		// refresh appointment table

		// ===========Natalia's refresh=========================//
		try {
			refreshStartEndNoShowData();
			refreshUpdateApptData();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// ================= Mihir's refresh ==================//
		textField.setText("");
		passwordFieldM.setText("");

		refreshAppointmentTable();
		// ==================================================

		pack();

	}

	private void refreshAppointmentTable() {
		// TODO Mihir

		appointmentDtm = new DefaultTableModel(0, 0);
		appointmentDtm.setColumnIdentifiers(appointmentColumnNames);
		appointmentTable.setModel(appointmentDtm);

		if (appointmentDatePicker.getModel().getValue() != null) {
			for (TOAppointment a : FlexiBookController.getApptsForDay((Date)appointmentDatePicker.getModel().getValue())) {
				String customer = a.getCustomer().getUsername();
				String service = a.getBookableService().getName();
				String date = a.getTimeSlot().getStartDate().toString();
				String starttime = a.getTimeSlot().getStartTime().toString().substring(0,5);
				String status = FlexiBookController.getStatusOfApptDto(a);
				String endTime = a.getTimeSlot().getEndTime().toString().substring(0,5);

				Object[] obj = {customer, service, date, starttime,endTime, status};
				appointmentDtm.addRow(obj);
			}
		}

		Dimension d = appointmentTable.getPreferredSize();
		appointmentScrollPane.setPreferredSize(new Dimension(d.width, HEIGHT_OVERVIEW_TABLE));

	}

	private void refreshUpdateApptData() throws ParseException {
		errorAppt.setText(apptError);
		if (apptError == null || apptError.length() == 0) {
			appointmentsToUpdateMap =  new HashMap<Integer, TOAppointment>();
			updateAppointmentDateList.removeAllItems();
			//added cancel appointment date list here
			cancelAppointmentDateList.removeAllItems();
			Integer updateAppointmentIndex = 0;
			
			for (TOAppointment a : FlexiBookController.showAppointmentsToUpdate()) {
				appointmentsToUpdateMap.put(updateAppointmentIndex, a);
				updateAppointmentDateList.addItem(a.getBookableService().getName() + " - " + a.getCustomer().getUsername() + " - "
						+ a.getTimeSlot().getStartTime() + " - " + a.getTimeSlot().getEndTime());
				//added cancel appointment below
				cancelAppointmentDateList.addItem(a.getBookableService().getName() + " - " + a.getCustomer().getUsername() + " - "
						+ a.getTimeSlot().getStartTime() + " - " + a.getTimeSlot().getEndTime());
				updateAppointmentIndex++;
			}
			
			updateAppointmentDateList.setSelectedIndex(-1);
			cancelAppointmentDateList.setSelectedIndex(-1);
		}

		
	}
	
	
	private void refreshStartEndNoShowData() throws ParseException {

		errorAppt.setText(apptError);
		
		if (apptError == null || apptError.length() == 0) {
			// start appt
			appointmentsToStartMap = new HashMap<Integer, TOAppointment>();
			selectStartAppointmentList.removeAllItems();
			Integer startAppointmentIndex = 0;

			for (TOAppointment a : FlexiBookController.showAppointmentsToStart()) {

				appointmentsToStartMap.put(startAppointmentIndex, a);
				if (a.getBookableService() instanceof TOService) {
					selectStartAppointmentList
							.addItem(a.getBookableService().getName() + " - " + a.getCustomer().getUsername() + " - "
									+ a.getTimeSlot().getStartTime() + " - " + a.getTimeSlot().getEndTime());
				} else {
					String chosen = "";
					for (TOComboItem ci : FlexiBookController.showChosenItems(a)) {
						chosen += ci.getService().getName() + ", ";
					}
					selectStartAppointmentList.addItem(
							a.getBookableService().getName() + " - " + a.getCustomer().getUsername() + " - " + chosen
									+ " - " + a.getTimeSlot().getStartTime() + " - " + a.getTimeSlot().getEndTime());
				}
				startAppointmentIndex++;

			}
			selectStartAppointmentList.setSelectedIndex(-1);

			// end appt
			appointmentsToEndNoShowMap = new HashMap<Integer, TOAppointment>();
			selectEndNoShowAppointmentList.removeAllItems();
			Integer endNoShowAppointmentIndex = 0;
			for (TOAppointment aToEndNoShow : FlexiBookController.showAppointmentsToEndNoShow()) {
				appointmentsToEndNoShowMap.put(endNoShowAppointmentIndex, aToEndNoShow);
				if (aToEndNoShow.getBookableService() instanceof TOService) {
					selectEndNoShowAppointmentList.addItem(aToEndNoShow.getBookableService().getName() + " - "
							+ aToEndNoShow.getCustomer().getUsername() + " - "
							+ aToEndNoShow.getTimeSlot().getStartTime() + " - "
							+ aToEndNoShow.getTimeSlot().getEndTime());
				} else {
					String chosen = "";
					for (TOComboItem ci : FlexiBookController.showChosenItems(aToEndNoShow)) {
						chosen += ci.getService().getName() + ", ";
					}
					selectEndNoShowAppointmentList.addItem(aToEndNoShow.getBookableService().getName() + " - "
							+ aToEndNoShow.getCustomer().getUsername() + " - " + chosen + " - "
							+ aToEndNoShow.getTimeSlot().getStartTime() + " - "
							+ aToEndNoShow.getTimeSlot().getEndTime());
				}
				startAppointmentIndex++;

			}
			selectEndNoShowAppointmentList.setSelectedIndex(-1);
			// no show appt

			pack();
		}

	}

	private void initComponents() {
		// tabs variables
		jTabbedPane = new javax.swing.JTabbedPane();
		servicesTab = new javax.swing.JPanel();
		appointmentTab = new javax.swing.JPanel();
		customerTab = new javax.swing.JPanel();
		businessTab = new javax.swing.JPanel();
		loginTab = new javax.swing.JPanel();

		// global configuration
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("FlexiBook Application System");
		this.setSize(800, 500);

		initLoginFrame();
		jTabbedPane.addTab("Log in/out", loginTab);

		initServicesFrame();
		jTabbedPane.addTab("Services Management", servicesTab);

		initAppointmentFrame();
		jTabbedPane.addTab("Appointment Management", appointmentTab);

		initCustomerFrame();
		jTabbedPane.addTab("Customer Management", customerTab);

		initBusinessFrame();
		jTabbedPane.addTab("Business Management", businessTab);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jTabbedPane));
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jTabbedPane));

	}

	/**
	 * Initializes the business management frame
	 * 
	 * @author Cecile Dai
	 */
	private void initBusinessFrame() {

		JFrame businessFrame = new JFrame();
//		JPanel panel = new JPanel();

		// elements for error message
		errorMessage = new JLabel();
		errorMessage.setForeground(Color.RED);

		// Business information
		businessNameLabel = new JLabel();
//		businessNameLabel.setText("Business Name: " + FlexiBookController.viewBasicBusinessInfo().getName());
		businessNameLabel.setText("Business Name: ");

		addressLabel = new JLabel();
//		addressLabel.setText("Address: " + FlexiBookController.viewBasicBusinessInfo().getAddress());
		addressLabel.setText("Address: ");
		phoneNumberLabel = new JLabel();
//		phoneNumberLabel.setText("Phone Number: " + FlexiBookController.viewBasicBusinessInfo().getPhoneNumber());
		phoneNumberLabel.setText("Phone Number: ");
		emailLabel = new JLabel();
//		emailLabel.setText("Email: " + FlexiBookController.viewBasicBusinessInfo().getEmail());
		emailLabel.setText("Email: ");
		contactInfoLabel = new JLabel();
		contactInfoLabel.setText("Contact Information");
		updateBusinessInfoButton = new JButton();
		updateBusinessInfoButton.setText("Update Business Information");

		// Business hours
		businessHoursLabel = new JLabel(); // needed?
		businessHoursLabel.setText("Business Hours"); // needed?
		addBusinessHourButton = new JButton();
//		addBusinessHourButton.setPreferredSize(new Dimension(40, 40));
		addBusinessHourButton.setText("Add Business Hours");
		updateBusinessHourButton = new JButton();
		updateBusinessHourButton.setText("Update Business Hours");
		removeBusinessHourButton = new JButton();
		removeBusinessHourButton.setText("Remove Business Hours");

		// Holidays
		addHolidayButton = new JButton();
//		addHolidayButton.setPreferredSize(new Dimension(40, 40));
		addHolidayButton.setText("Add Holiday");
		updateHolidayButton = new JButton();
		updateHolidayButton.setText("Update Holiday");
		removeHolidayButton = new JButton();
		removeHolidayButton.setText("Remove Holiday");

		// Vacations
		addVacationButton = new JButton();
//		addHolidayButton.setPreferredSize(new Dimension(40, 40));
		addVacationButton.setText("Add Vacation");
		updateVacationButton = new JButton();
		updateVacationButton.setText("Update Vacation");
		removeVacationButton = new JButton();
		removeVacationButton.setText("Remove Vacation");

		businessHoursTable = new JTable() {
//		 private static final long serialVersionUID = 1L;

			@Override
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
				Component c = super.prepareRenderer(renderer, row, column);
				c.setBackground(Color.WHITE);
//				        if (!c.getBackground().equals(getSelectionBackground())) {
//				            Object obj = getModel().getValueAt(row, column);
//				            if (obj instanceof java.lang.String) {
//				                String str = (String)obj;
//				                c.setBackground(str.endsWith("sick)") ? Color.RED : 
//				                    str.endsWith("repair)") ? Color.YELLOW : Color.WHITE);
//				            }
//				            else {
//				                c.setBackground(Color.WHITE);
//				            }
//				        }
				if (!c.getBackground().equals(getSelectionBackground())) {
					Object obj = getModel().getValueAt(row, column);
					c.setBackground(Color.WHITE);
				}
				return c;
			}
		};

		holidaysTable = new JTable() {

			@Override
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
				Component c = super.prepareRenderer(renderer, row, column);
				c.setBackground(Color.WHITE);
				if (!c.getBackground().equals(getSelectionBackground())) {
					Object obj = getModel().getValueAt(row, column);
					c.setBackground(Color.WHITE);
				}
				return c;
			}

		};

		vacationsTable = new JTable() {

			@Override
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
				Component c = super.prepareRenderer(renderer, row, column);
				c.setBackground(Color.WHITE);
				if (!c.getBackground().equals(getSelectionBackground())) {
					Object obj = getModel().getValueAt(row, column);
					c.setBackground(Color.WHITE);
				}
				return c;
			}

		};

		businessHoursScrollPane = new JScrollPane(businessHoursTable);
		this.add(businessHoursScrollPane);
		Dimension d1 = businessHoursTable.getPreferredSize();
		businessHoursScrollPane.setPreferredSize(new Dimension(d1.width, HEIGHT_OVERVIEW_TABLE));
		businessHoursScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		holidaysScrollPane = new JScrollPane(holidaysTable);
		this.add(holidaysScrollPane);
		Dimension d2 = holidaysTable.getPreferredSize();
		holidaysScrollPane.setPreferredSize(new Dimension(d2.width, HEIGHT_OVERVIEW_TABLE));
		holidaysScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		vacationsScrollPane = new JScrollPane(vacationsTable);
		this.add(vacationsScrollPane);
		Dimension d3 = vacationsTable.getPreferredSize();
		vacationsScrollPane.setPreferredSize(new Dimension(d3.width, HEIGHT_OVERVIEW_TABLE));
		vacationsScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		addButtonsPanel = new JPanel(new GridLayout(0, 3));
		addButtonsPanel.add(addBusinessHourButton);
		addButtonsPanel.add(addHolidayButton);
		addButtonsPanel.add(addVacationButton);
		addButtonsPanel.add(updateBusinessHourButton);
		addButtonsPanel.add(updateHolidayButton);
		addButtonsPanel.add(updateVacationButton);
		addButtonsPanel.add(removeBusinessHourButton);
		addButtonsPanel.add(removeHolidayButton);
		addButtonsPanel.add(removeVacationButton);

		timesOverviewPanel = new JPanel(new GridLayout(0, 3));
		timesOverviewPanel.add(businessHoursScrollPane);
		timesOverviewPanel.add(holidaysScrollPane);
		timesOverviewPanel.add(vacationsScrollPane);
//		timesOverviewPanel.add(addBusinessHourButton);
//		timesOverviewPanel.add(addHolidayButton);
//		timesOverviewPanel.add(addVacationButton);

		// Business info listeners
		updateBusinessInfoButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				updateBusinessInfoButtonActionPerformed(evt);
			}
		});

		// Business hours listeners
		addBusinessHourButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				addBusinessHourButtonActionPerformed(evt);
			}
		});
		updateBusinessHourButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				updateBusinessHourButtonActionPerformed(evt);
			}
		});
		removeBusinessHourButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				removeBusinessHourButtonActionPerformed(evt);
			}
		});

		// Holidays listeners
		addHolidayButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				addHolidayButtonActionPerformed(evt);
			}
		});
		updateHolidayButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				updateHolidayButtonActionPerformed(evt);
			}
		});
		removeHolidayButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				removeHolidayButtonActionPerformed(evt);
			}
		});
//		holidayStartDatePicker.addActionListener(new java.awt.event.ActionListener() {
//			public void actionPerformed(java.awt.event.ActionEvent evt) {
//				refreshHolidaysTable();
//			}
//		});
//		holidayEndDatePicker.addActionListener(new java.awt.event.ActionListener() {
//			public void actionPerformed(java.awt.event.ActionEvent evt) {
//				refreshHolidaysTable();
//			}
//		});
//		newHolidayStartDatePicker.addActionListener(new java.awt.event.ActionListener() {
//			public void actionPerformed(java.awt.event.ActionEvent evt) {
//				refreshHolidaysTable();
//			}
//		});
//		newHolidayEndDatePicker.addActionListener(new java.awt.event.ActionListener() {
//			public void actionPerformed(java.awt.event.ActionEvent evt) {
//				refreshHolidaysTable();
//			}
//		});

		// Vacations listeners
		addVacationButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				addVacationButtonActionPerformed(evt);
			}
		});
		updateVacationButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				updateVacationButtonActionPerformed(evt);
			}
		});
		removeVacationButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				removeVacationButtonActionPerformed(evt);
			}
		});
//		vacationStartDatePicker.addActionListener(new java.awt.event.ActionListener() {
//			public void actionPerformed(java.awt.event.ActionEvent evt) {
//				refreshVacationsTable();
//			}
//		});
//		vacationEndDatePicker.addActionListener(new java.awt.event.ActionListener() {
//			public void actionPerformed(java.awt.event.ActionEvent evt) {
//				refreshVacationsTable();
//			}
//		});
//		newVacationStartDatePicker.addActionListener(new java.awt.event.ActionListener() {
//			public void actionPerformed(java.awt.event.ActionEvent evt) {
//				refreshVacationsTable();
//			}
//		});
//		newVacationEndDatePicker.addActionListener(new java.awt.event.ActionListener() {
//			public void actionPerformed(java.awt.event.ActionEvent evt) {
//				refreshVacationsTable();
//			}
//		});

		// layout
		GroupLayout layout = new GroupLayout(businessFrame.getContentPane());
		businessFrame.getContentPane().setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
//		panel.setLayout(layout);

		JSeparator horizontalLine1 = new JSeparator();

		layout.setHorizontalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup().addComponent(errorMessage).addComponent(horizontalLine1)
						.addGroup(layout.createSequentialGroup()
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
										.addComponent(businessNameLabel).addComponent(addressLabel)
										.addComponent(contactInfoLabel).addComponent(phoneNumberLabel)
										.addComponent(emailLabel).addComponent(updateBusinessInfoButton)
//										.addComponent(businessHoursScrollPane)
//										.addComponent(addBusinessHourButton)
								).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
//										.addComponent(addHolidayButton)
//										.addComponent(holidaysScrollPane)
								).addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
//										.addComponent(updateBusinessInfoButton)
//										.addComponent(vacationsScrollPane)
//										.addComponent(addVacationButton)
								)).addComponent(timesOverviewPanel).addComponent(addButtonsPanel)));
		layout.setVerticalGroup(layout.createSequentialGroup().addComponent(errorMessage)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(businessNameLabel)
//						.addComponent(updateBusinessInfoButton)
				).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(addressLabel))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(contactInfoLabel))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(phoneNumberLabel))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(emailLabel))
				.addComponent(updateBusinessInfoButton).addComponent(horizontalLine1)
//				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
//						.addComponent(businessHoursScrollPane)
//						.addComponent(holidaysScrollPane)
//						.addComponent(vacationsScrollPane))
//				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
//						.addComponent(addBusinessHourButton)
//						.addComponent(addHolidayButton)
//						.addComponent(addVacationButton))
				.addComponent(timesOverviewPanel).addComponent(addButtonsPanel));

		// Update Business Info
		updateBusinessNameLabel = new JLabel();
		updateBusinessNameLabel.setText("Enter new business name: ");
		updateAddressLabel = new JLabel();
		updateAddressLabel.setText("Enter new address: ");
		updatePhoneNumberLabel = new JLabel();
		updatePhoneNumberLabel.setText("Enter new phone number: ");
		updateEmailLabel = new JLabel();
		updateEmailLabel.setText("Enter new email: ");
		updateBusinessNameField = new JTextField();
		updateAddressField = new JTextField();
		updatePhoneNumberField = new JTextField();
		updateEmailField = new JTextField();

		updateBusinessInfoPanel = new JPanel(new GridLayout(0, 1));
		updateBusinessInfoPanel.add(updateBusinessNameLabel);
		updateBusinessInfoPanel.add(updateBusinessNameField);
		updateBusinessInfoPanel.add(updateAddressLabel);
		updateBusinessInfoPanel.add(updateAddressField);
		updateBusinessInfoPanel.add(updatePhoneNumberLabel);
		updateBusinessInfoPanel.add(updatePhoneNumberField);
		updateBusinessInfoPanel.add(updateEmailLabel);
		updateBusinessInfoPanel.add(updateEmailField);

		// Add business hour
		String[] weekDays = { "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday" };
		weekDaysComboBox = new JComboBox<String>(weekDays);
		setWeekDayLabel = new JLabel();
		setBHStartTimeLabel = new JLabel();
		setBHEndTimeLabel = new JLabel();
		setWeekDayLabel.setText("Select day of week: ");
		setBHStartTimeLabel.setText("Enter start time (HH:mm): ");
		setBHEndTimeLabel.setText("Enter end time (HH:mm): ");
		setBHStartTimeField = new JTextField();
		setBHEndTimeField = new JTextField();

		addBusinessHourPanel = new JPanel(new GridLayout(0, 1));
		addBusinessHourPanel.add(setWeekDayLabel);
		addBusinessHourPanel.add(weekDaysComboBox);
		addBusinessHourPanel.add(setBHStartTimeLabel);
		addBusinessHourPanel.add(setBHStartTimeField);
		addBusinessHourPanel.add(setBHEndTimeLabel);
		addBusinessHourPanel.add(setBHEndTimeField);

		// Update business hour
//		ArrayList<TOBusinessHour> businessHoursList = new ArrayList<>();
//		for(TOBusinessHour bh : FlexiBookController.viewBusinessHours()) {
//			businessHoursList.add(bh);
//		}
		businessHoursArray = new String[0];
//		if(FlexiBookController.viewBusinessHours().size() != 0) {
//		for(int i = 0; i < FlexiBookController.viewBusinessHours().size(); i++) {
//			TOBusinessHour current = FlexiBookController.viewBusinessHours().get(i);
//			
//			TOBusinessHour.DayOfWeek day = current.getDayOfWeek();
//	    	String weekDay = null;
//	    	
//	    	switch(day){
//            case Monday:
//                weekDay = "Monday";
//                break;
//            case Tuesday:
//                weekDay = "Tuesday";
//                break;
//            case Wednesday:
//                weekDay = "Wednesday";
//                break;
//            case Thursday:
//                weekDay = "Thursday";
//                break;
//            case Friday:
//                weekDay = "Friday";
//                break;
//            case Saturday:
//                weekDay = "Saturday";
//                break;
//            case Sunday:
//                weekDay = "Sunday";
//                break;
//        	}
//	    	
//	    	String startTime = current.getStartTime().toString().substring(0, 5);
//	    	String endTime = current.getEndTime().toString().substring(0,5);
//	    	
//	    	String bhText = weekDay + " : " + startTime + " - " + endTime;
//			businessHoursArray[i] = bhText;
//		}}

		updateBusinessHourLabel = new JLabel();
		updateBusinessHourLabel.setText("Select business hour: ");
		updateBusinessHourComboBox = new JComboBox<String>(businessHoursArray);
		
		setNewWeekDayLabel = new JLabel();
		setNewWeekDayLabel.setText("Select new day of week: ");
		newWeekDaysComboBox = new JComboBox<String>(weekDays);
		setNewBHStartTimeLabel = new JLabel();
		setNewBHStartTimeLabel.setText("Enter new start time (HH:mm): ");
		setNewBHEndTimeLabel = new JLabel();
		setNewBHEndTimeLabel.setText("Enter new end time (HH:mm): ");
		setNewBHStartTimeField = new JTextField();
		setNewBHEndTimeField = new JTextField();

		updateBusinessHourPanel = new JPanel(new GridLayout(0, 1));
		updateBusinessHourPanel.add(updateBusinessHourLabel);
		updateBusinessHourPanel.add(updateBusinessHourComboBox);
		updateBusinessHourPanel.add(setNewWeekDayLabel);
		updateBusinessHourPanel.add(newWeekDaysComboBox);
		updateBusinessHourPanel.add(setNewBHStartTimeLabel);
		updateBusinessHourPanel.add(setNewBHStartTimeField);
		updateBusinessHourPanel.add(setNewBHEndTimeLabel);
		updateBusinessHourPanel.add(setNewBHEndTimeField);

		// Remove business hour
		removeBusinessHourLabel = new JLabel();
		removeBusinessHourLabel.setText("Select business hour: ");
		removeBusinessHourComboBox = new JComboBox<String>(businessHoursArray);

		removeBusinessHourPanel = new JPanel(new GridLayout(0, 1));
		removeBusinessHourPanel.add(removeBusinessHourLabel);
		removeBusinessHourPanel.add(removeBusinessHourComboBox);

		// Add holiday
		setHolidayStartDateLabel = new JLabel();
		setHolidayStartDateLabel.setText("Select start date: ");
		setHolidayEndDateLabel = new JLabel();
		setHolidayEndDateLabel.setText("Select end date: ");
		setHolidayStartTimeLabel = new JLabel();
		setHolidayStartTimeLabel.setText("Enter start time (HH:mm): ");
		setHolidayEndTimeLabel = new JLabel();
		setHolidayEndTimeLabel.setText("Enter end time (HH:mm): ");
		setHolidayStartTimeField = new JTextField();
		setHolidayEndTimeField = new JTextField();

		SqlDateModel modelH1 = new SqlDateModel();
		Properties pH1 = new Properties();
		pH1.put("text.today", "Today");
		pH1.put("text.month", "Month");
		pH1.put("text.year", "Year");
		JDatePanelImpl datePanelH1 = new JDatePanelImpl(modelH1, pH1);
		holidayStartDatePicker = new JDatePickerImpl(datePanelH1, new DateLabelFormatter());
		holidayStartDatePickerLabel = new JLabel();
		holidayStartDatePickerLabel.setText("Date:");
		SqlDateModel modelH2 = new SqlDateModel();
		Properties pH2 = new Properties();
		pH2.put("text.today", "Today");
		pH2.put("text.month", "Month");
		pH2.put("text.year", "Year");
		JDatePanelImpl datePanelH2 = new JDatePanelImpl(modelH2, pH2);
		holidayEndDatePicker = new JDatePickerImpl(datePanelH2, new DateLabelFormatter());
		holidayEndDatePickerLabel = new JLabel();
		holidayEndDatePickerLabel.setText("Date:");

		addHolidayPanel = new JPanel(new GridLayout(0, 2));
		addHolidayPanel.add(setHolidayStartDateLabel);
		addHolidayPanel.add(setHolidayEndDateLabel);
		addHolidayPanel.add(holidayStartDatePicker);
		addHolidayPanel.add(holidayEndDatePicker);
		addHolidayPanel.add(setHolidayStartTimeLabel);
		addHolidayPanel.add(setHolidayStartTimeField);
		addHolidayPanel.add(setHolidayEndTimeLabel);
		addHolidayPanel.add(setHolidayEndTimeField);

		// Update holiday
//		ArrayList<TOTimeSlot> holidaysList = new ArrayList<>();
//		for(TOTimeSlot ts : FlexiBookController.viewVacations()) {
//			holidaysList.add(ts);
//		}
		String[] holidaysArray = new String[0];
//		if(holidaysList.size() != 0) {
//		for(int i = 0; i <= holidaysList.size(); i++) {
//			TOTimeSlot current = holidaysList.get(i);
//			String startTime = current.getStartTime().toString().substring(0, 5);
//	    	String endTime = current.getEndTime().toString().substring(0,5);
//			holidaysArray[i] = toEasyDate(current.getStartDate()) + " at " + startTime + " to " + toEasyDate(current.getEndDate())
//			+ " at " + endTime;
//		}}
		updateHolidayLabel = new JLabel();
		updateHolidayLabel.setText("Select holiday: ");
		updateHolidayComboBox = new JComboBox<String>(holidaysArray);
		setNewHolidayStartDateLabel = new JLabel();
		setNewHolidayStartDateLabel.setText("Select new start date: ");
		setNewHolidayEndDateLabel = new JLabel();
		setNewHolidayEndDateLabel.setText("Select new end date: ");
		setNewHolidayStartTimeLabel = new JLabel();
		setNewHolidayStartTimeLabel.setText("Enter new start time (HH:mm): ");
		setNewHolidayEndTimeLabel = new JLabel();
		setNewHolidayEndTimeLabel.setText("Enter new end time (HH:mm): ");
		setNewHolidayStartTimeField = new JTextField();
		setNewHolidayEndTimeField = new JTextField();

		SqlDateModel modelUH1 = new SqlDateModel();
		Properties pUH1 = new Properties();
		pH1.put("text.today", "Today");
		pH1.put("text.month", "Month");
		pH1.put("text.year", "Year");
		JDatePanelImpl datePanelUH1 = new JDatePanelImpl(modelUH1, pUH1);
		newHolidayStartDatePicker = new JDatePickerImpl(datePanelUH1, new DateLabelFormatter());
		newHolidayStartDatePickerLabel = new JLabel();
		newHolidayStartDatePickerLabel.setText("Date:");
		SqlDateModel modelUH2 = new SqlDateModel();
		Properties pUH2 = new Properties();
		pH2.put("text.today", "Today");
		pH2.put("text.month", "Month");
		pH2.put("text.year", "Year");
		JDatePanelImpl datePanelUH2 = new JDatePanelImpl(modelUH2, pUH2);
		newHolidayEndDatePicker = new JDatePickerImpl(datePanelUH2, new DateLabelFormatter());
		newHolidayEndDatePickerLabel = new JLabel();
		newHolidayEndDatePickerLabel.setText("Date:");

		updateHolidayPanel = new JPanel(new GridLayout(0, 2));
		updateHolidayPanel.add(updateHolidayLabel);
		updateHolidayPanel.add(updateHolidayComboBox);
		updateHolidayPanel.add(setNewHolidayStartDateLabel);
		updateHolidayPanel.add(setNewHolidayEndDateLabel);
		updateHolidayPanel.add(newHolidayStartDatePicker);
		updateHolidayPanel.add(newHolidayEndDatePicker);
		updateHolidayPanel.add(setNewHolidayStartTimeLabel);
		updateHolidayPanel.add(setNewHolidayStartTimeField);
		updateHolidayPanel.add(setNewHolidayEndTimeLabel);
		updateHolidayPanel.add(setNewHolidayEndTimeField);

		// Remove holiday
		removeHolidayLabel = new JLabel();
		removeHolidayLabel.setText("Select holiday: ");
		removeHolidayComboBox = new JComboBox<String>(holidaysArray);

		removeHolidayPanel = new JPanel(new GridLayout(0, 1));
		removeHolidayPanel.add(removeHolidayLabel);
		removeHolidayPanel.add(removeHolidayComboBox);

		// Add vacation
		setVacationStartDateLabel = new JLabel();
		setVacationStartDateLabel.setText("Select start date: ");
		setVacationEndDateLabel = new JLabel();
		setVacationEndDateLabel.setText("Select end date: ");
		setVacationStartTimeLabel = new JLabel();
		setVacationStartTimeLabel.setText("Enter start time (HH:mm): ");
		setVacationEndTimeLabel = new JLabel();
		setVacationEndTimeLabel.setText("Enter end time (HH:mm): ");
		setVacationStartTimeField = new JTextField();
		setVacationEndTimeField = new JTextField();

		SqlDateModel modelV1 = new SqlDateModel();
		Properties pV1 = new Properties();
		pV1.put("text.today", "Today");
		pV1.put("text.month", "Month");
		pV1.put("text.year", "Year");
		JDatePanelImpl datePanelV1 = new JDatePanelImpl(modelV1, pV1);
		vacationStartDatePicker = new JDatePickerImpl(datePanelV1, new DateLabelFormatter());
		vacationStartDatePickerLabel = new JLabel();
		vacationStartDatePickerLabel.setText("Date:");
		SqlDateModel modelV2 = new SqlDateModel();
		Properties pV2 = new Properties();
		pV2.put("text.today", "Today");
		pV2.put("text.month", "Month");
		pV2.put("text.year", "Year");
		JDatePanelImpl datePanelV2 = new JDatePanelImpl(modelV2, pV2);
		vacationEndDatePicker = new JDatePickerImpl(datePanelV2, new DateLabelFormatter());
		vacationEndDatePickerLabel = new JLabel();
		vacationEndDatePickerLabel.setText("Date:");

		addVacationPanel = new JPanel(new GridLayout(0, 2));
		addVacationPanel.add(setVacationStartDateLabel);
		addVacationPanel.add(setVacationEndDateLabel);
		addVacationPanel.add(vacationStartDatePicker);
		addVacationPanel.add(vacationEndDatePicker);
		addVacationPanel.add(setVacationStartTimeLabel);
		addVacationPanel.add(setVacationStartTimeField);
		addVacationPanel.add(setVacationEndTimeLabel);
		addVacationPanel.add(setVacationEndTimeField);

		// update vacation
//		ArrayList<TOTimeSlot> vacationsList = new ArrayList<>();
//		for(TOTimeSlot ts : FlexiBookController.viewVacations()) {
//			vacationsList.add(ts);
//		}
		String[] vacationsArray = new String[0];
//		if(vacationsList.size() != 0) {
//		for(int i = 0; i <= vacationsList.size(); i++) {
//			TOTimeSlot current = vacationsList.get(i);
//			String startTime = current.getStartTime().toString().substring(0, 5);
//	    	String endTime = current.getEndTime().toString().substring(0,5);
//			vacationsArray[i] = toEasyDate(current.getStartDate()) + " at " + startTime + " to " + toEasyDate(current.getEndDate())
//			+ " at " + endTime;
//		}}
		updateVacationLabel = new JLabel();
		updateVacationLabel.setText("Select vacation: ");
		updateVacationComboBox = new JComboBox<String>(vacationsArray);
		setNewVacationStartDateLabel = new JLabel();
		setNewVacationStartDateLabel.setText("Select new start date: ");
		setNewVacationEndDateLabel = new JLabel();
		setNewVacationEndDateLabel.setText("Select new end date: ");
		setNewVacationStartTimeLabel = new JLabel();
		setNewVacationStartTimeLabel.setText("Enter new start time (HH:mm): ");
		setNewVacationEndTimeLabel = new JLabel();
		setNewVacationEndTimeLabel.setText("Enter new end time (HH:mm): ");
		setNewVacationStartTimeField = new JTextField();
		setNewVacationEndTimeField = new JTextField();

		SqlDateModel modelUV1 = new SqlDateModel();
		Properties pUV1 = new Properties();
		pH1.put("text.today", "Today");
		pH1.put("text.month", "Month");
		pH1.put("text.year", "Year");
		JDatePanelImpl datePanelUV1 = new JDatePanelImpl(modelUV1, pUV1);
		newVacationStartDatePicker = new JDatePickerImpl(datePanelUV1, new DateLabelFormatter());
		newVacationStartDatePickerLabel = new JLabel();
		newVacationStartDatePickerLabel.setText("Date:");
		SqlDateModel modelUV2 = new SqlDateModel();
		Properties pUV2 = new Properties();
		pH2.put("text.today", "Today");
		pH2.put("text.month", "Month");
		pH2.put("text.year", "Year");
		JDatePanelImpl datePanelUV2 = new JDatePanelImpl(modelUV2, pUV2);
		newVacationEndDatePicker = new JDatePickerImpl(datePanelUV2, new DateLabelFormatter());
		newVacationEndDatePickerLabel = new JLabel();
		newVacationEndDatePickerLabel.setText("Date:");

		updateVacationPanel = new JPanel(new GridLayout(0, 2));
		updateVacationPanel.add(updateVacationLabel);
		updateVacationPanel.add(updateVacationComboBox);
		updateVacationPanel.add(setNewVacationStartDateLabel);
		updateVacationPanel.add(setNewVacationEndDateLabel);
		updateVacationPanel.add(newVacationStartDatePicker);
		updateVacationPanel.add(newVacationEndDatePicker);
		updateVacationPanel.add(setNewVacationStartTimeLabel);
		updateVacationPanel.add(setNewVacationStartTimeField);
		updateVacationPanel.add(setNewVacationEndTimeLabel);
		updateVacationPanel.add(setNewVacationEndTimeField);

		// remove vacation
		removeVacationLabel = new JLabel();
		removeVacationLabel.setText("Select vacation: ");
		removeVacationComboBox = new JComboBox<String>(vacationsArray);

		removeVacationPanel = new JPanel(new GridLayout(0, 1));
		removeVacationPanel.add(removeVacationLabel);
		removeVacationPanel.add(removeVacationComboBox);

//		frame.add(panel);
		businessTab.add(businessFrame.getContentPane());
		businessFrame.pack();
		// businessFrame.setVisible(true);

	}

	// ========== Cecile's Action Listeners and Refresh Overview Table =========

	/**
	 * @author Cecile Dai
	 */
	private void refreshBusinessHoursTable() {
		overviewDtm = new DefaultTableModel(0, 0);
		overviewDtm.setColumnIdentifiers(businessHoursColumnName);
		businessHoursTable.setModel(overviewDtm);

		for (TOBusinessHour hour : FlexiBookController.viewBusinessHours()) {

			TOBusinessHour.DayOfWeek day = hour.getDayOfWeek();
			String weekDay = null;

			switch (day) {
			case Monday:
				weekDay = "Monday";
				break;
			case Tuesday:
				weekDay = "Tuesday";
				break;
			case Wednesday:
				weekDay = "Wednesday";
				break;
			case Thursday:
				weekDay = "Thursday";
				break;
			case Friday:
				weekDay = "Friday";
				break;
			case Saturday:
				weekDay = "Saturday";
				break;
			case Sunday:
				weekDay = "Sunday";
				break;
			}

			String startTime = hour.getStartTime().toString().substring(0, 5);
			String endTime = hour.getEndTime().toString().substring(0, 5);

			String bhText = weekDay + " : " + startTime + " - " + endTime;

			Object[] bhData = { bhText };
			overviewDtm.addRow(bhData);

		}
		Dimension d1 = businessHoursTable.getPreferredSize();
		businessHoursScrollPane.setPreferredSize(new Dimension(d1.width, HEIGHT_OVERVIEW_TABLE));
	}

	/**
	 * @author Cecile Dai
	 */
	private void refreshHolidaysTable() {
		overviewDtm = new DefaultTableModel(0, 0);
		overviewDtm.setColumnIdentifiers(holidaysColumnName);
		holidaysTable.setModel(overviewDtm);

		for (TOTimeSlot ts : FlexiBookController.viewHolidays()) {
			String startTime = ts.getStartTime().toString().substring(0, 5);
			String endTime = ts.getEndTime().toString().substring(0, 5);
			String holidayText = toEasyDate(ts.getStartDate()) + " at " + startTime + " to "
					+ toEasyDate(ts.getEndDate()) + " at " + endTime;
			Object[] holidayData = { holidayText };
			overviewDtm.addRow(holidayData);
			System.out.println("has holiday: " + ts.getStartDate());
		}
		Dimension d2 = holidaysTable.getPreferredSize();
		holidaysScrollPane.setPreferredSize(new Dimension(d2.width, HEIGHT_OVERVIEW_TABLE));
	}

	/**
	 * @author Cecile Dai
	 */
	private void refreshVacationsTable() {
		overviewDtm = new DefaultTableModel(0, 0);
		overviewDtm.setColumnIdentifiers(vacationsColumnName);
		vacationsTable.setModel(overviewDtm);

		for (TOTimeSlot ts : FlexiBookController.viewVacations()) {
			String startTime = ts.getStartTime().toString().substring(0, 5);
			String endTime = ts.getEndTime().toString().substring(0, 5);
			String vacationsText = toEasyDate(ts.getStartDate()) + " at " + startTime + " to "
					+ toEasyDate(ts.getEndDate()) + " at " + endTime;
			Object[] vacationsData = { vacationsText };
			overviewDtm.addRow(vacationsData);
			System.out.println("has vacation: " + ts.getStartDate());
		}
		Dimension d3 = vacationsTable.getPreferredSize();
		vacationsScrollPane.setPreferredSize(new Dimension(d3.width, HEIGHT_OVERVIEW_TABLE));
	}

	/**
	 * @author Cecile Dai
	 * @param evt
	 */
	private void updateBusinessInfoButtonActionPerformed(java.awt.event.ActionEvent evt) {
		error = "";
		String businessName = "";
		String address = "";
		String phoneNumber = "";
		String email = "";
		TOBusiness newBusinessInfo = new TOBusiness(businessName, address, phoneNumber, email);
		int result = JOptionPane.showConfirmDialog(null, updateBusinessInfoPanel, "Update Business Information",
				JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			businessName = updateBusinessNameField.getText();
			address = updateAddressField.getText();
			phoneNumber = updatePhoneNumberField.getText();
			email = updateEmailField.getText();

			try {
				FlexiBookController.updateBasicBusinessInfo(businessName, address, phoneNumber, email);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				error = e.getMessage();
			}
		} else {
			System.out.println("Changes cancelled");
		}

		refreshData();
	}

	/**
	 * @author Cecile Dai
	 * @param evt
	 */
	private void addBusinessHourButtonActionPerformed(java.awt.event.ActionEvent evt) {
		error = "";
		String weekDay = "";
		String startTime = "";
		String endTime = "";
		int result = JOptionPane.showConfirmDialog(null, addBusinessHourPanel, "Add Business Hour",
				JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			weekDay = (String) weekDaysComboBox.getSelectedItem();
			startTime = setBHStartTimeField.getText();
			endTime = setBHEndTimeField.getText();

			try {
				FlexiBookController.addBusinessHours(weekDay, startTime, endTime, FlexiBookApplication.getFlexiBook());
			} catch (Exception e) {
				System.out.println(e.getMessage());
				error = e.getMessage();
			}
		} else {
			System.out.println("Changes cancelled");
		}

		refreshData();

	}

	/**
	 * @author Cecile Dai
	 * @param evt
	 */
	private void updateBusinessHourButtonActionPerformed(java.awt.event.ActionEvent evt) {
		error = "";
		String oldBH = "";
		String oldWeekDay = "";
		String oldTime = "";
		String weekDay = "";
		String startTime = "";
		String endTime = "";
		int result = JOptionPane.showConfirmDialog(null, updateBusinessHourPanel, "Update Business Hours",
				JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			oldBH = (String) updateBusinessHourComboBox.getSelectedItem();
			String[] oldBHSections = oldBH.split(" ");
			oldWeekDay = oldBHSections[0];
			oldTime = oldBHSections[2];
			System.out.println(oldTime);
			weekDay = (String) newWeekDaysComboBox.getSelectedItem();
			startTime = setNewBHStartTimeField.getText();
			endTime = setNewBHEndTimeField.getText();

			try {
				FlexiBookController.updateBusinessHours(oldWeekDay, oldTime, weekDay, startTime, endTime);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				error = e.getMessage();
			}
		} else {
			System.out.println("Changes cancelled");
		}

		refreshData();

	}

	/**
	 * @author Cecile Dai
	 * @param evt
	 */
	private void removeBusinessHourButtonActionPerformed(java.awt.event.ActionEvent evt) {
		error = "";
		String fullBH = "";
		String weekDay = "";
		String startTime = "";
//		String endTime = "";
		int result = JOptionPane.showConfirmDialog(null, removeBusinessHourPanel, "Remove Business Hour",
				JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			fullBH = (String) removeBusinessHourComboBox.getSelectedItem();
			String[] bhSections = fullBH.split(" ");
			weekDay = bhSections[0];
			startTime = bhSections[2];
//			endTime = bhSections[4];

			try {
				FlexiBookController.removeBusinessHours(weekDay, startTime);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				error = e.getMessage();
			}
		} else {
			System.out.println("Changes cancelled");
		}

		refreshData();

	}

	/**
	 * @author Cecile Dai
	 * @param evt
	 */
	private void addHolidayButtonActionPerformed(java.awt.event.ActionEvent evt) {
		error = "";
		String startDate = "";
		String endDate = "";
		String startTime = "";
		String endTime = "";
		int result = JOptionPane.showConfirmDialog(null, addHolidayPanel, "Add Holiday", JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {

			System.out.println(((Date) holidayStartDatePicker.getModel().getValue()).toString());
			startDate = ((Date) holidayStartDatePicker.getModel().getValue()).toString();

			endDate = ((Date) holidayEndDatePicker.getModel().getValue()).toString();
			startTime = setHolidayStartTimeField.getText();
			endTime = setHolidayEndTimeField.getText();

			try {
				FlexiBookController.addNewTimeSlot("holiday", startDate, startTime, endDate, endTime);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				error = e.getMessage();
			}
		} else {
			System.out.println("Changes cancelled");
		}

		refreshData();
	}

	/**
	 * @author Cecile Dai
	 * @param evt
	 */
	private void updateHolidayButtonActionPerformed(java.awt.event.ActionEvent evt) {
		error = "";
		String dates = "";
		String oldDate = "";
		String oldTime = "";
		String startDate = "";
		String endDate = "";
		String startTime = "";
		String endTime = "";
		int result = JOptionPane.showConfirmDialog(null, updateHolidayPanel, "Update Holiday",
				JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			dates = fromEasyDate((String) updateHolidayComboBox.getSelectedItem());
			String[] datesSections = dates.split(" ");
			oldDate = datesSections[0];
			oldTime = datesSections[1];
			startDate = ((Date) newHolidayStartDatePicker.getModel().getValue()).toString();
			endDate = ((Date) newHolidayEndDatePicker.getModel().getValue()).toString();
			startTime = setNewHolidayStartTimeField.getText();
			endTime = setNewHolidayEndTimeField.getText();

			try {
				FlexiBookController.updateHoliday("holiday", oldDate, oldTime, startDate, startTime, endDate, endTime);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				error = e.getMessage();
			}
		} else {
			System.out.println("Changes cancelled");
		}

		refreshData();
	}

	/**
	 * @author Cecile Dai
	 * @param evt
	 */
	private void removeHolidayButtonActionPerformed(java.awt.event.ActionEvent evt) {
		error = "";
		String dates = "";
		String startDate = "";
		String endDate = "";
		String startTime = "";
		String endTime = "";
		int result = JOptionPane.showConfirmDialog(null, removeHolidayPanel, "Remove Holiday",
				JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			dates = fromEasyDate((String) removeHolidayComboBox.getSelectedItem());
			String[] datesSections = dates.split(" ");
			startDate = datesSections[0];
			endDate = datesSections[2];
			startTime = datesSections[1];
			endTime = datesSections[3];

			try {
				FlexiBookController.removeTimeSlot("holiday", startDate, startTime, endDate, endTime);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				error = e.getMessage();
			}
		} else {
			System.out.println("Changes cancelled");
		}

		refreshData();
	}

	/**
	 * @author Cecile Dai
	 * @param evt
	 */
	private void addVacationButtonActionPerformed(java.awt.event.ActionEvent evt) {
		error = "";
		String startDate = "";
		String endDate = "";
		String startTime = "";
		String endTime = "";
		int result = JOptionPane.showConfirmDialog(null, addVacationPanel, "Add Vacation",
				JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			startDate = ((Date) vacationStartDatePicker.getModel().getValue()).toString();
			endDate = ((Date) vacationEndDatePicker.getModel().getValue()).toString();
			startTime = setVacationStartTimeField.getText();
			endTime = setVacationEndTimeField.getText();

			try {
				FlexiBookController.addNewTimeSlot("vacation", startDate, startTime, endDate, endTime);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				error = e.getMessage();
			}
		} else {
			System.out.println("Changes cancelled");
		}
		refreshData();
	}

	/**
	 * @author Cecile Dai
	 * @param evt
	 */
	private void updateVacationButtonActionPerformed(java.awt.event.ActionEvent evt) {
		error = "";
		String dates = "";
		String oldDate = "";
		String oldTime = "";
		String startDate = "";
		String endDate = "";
		String startTime = "";
		String endTime = "";
		int result = JOptionPane.showConfirmDialog(null, updateVacationPanel, "Update Vacation",
				JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			dates = (String) updateHolidayComboBox.getSelectedItem();
			String[] datesSections = dates.split(" ");
			oldDate = datesSections[0];
			oldTime = datesSections[1];
			startDate = ((Date) newVacationStartDatePicker.getModel().getValue()).toString();
			endDate = ((Date) newVacationEndDatePicker.getModel().getValue()).toString();
			startTime = setNewHolidayStartTimeField.getText();
			endTime = setNewHolidayEndTimeField.getText();

			try {
				FlexiBookController.addNewTimeSlot("vacation", startDate, startTime, endDate, endTime);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				error = e.getMessage();
			}
		} else {
			System.out.println("Changes cancelled");
		}
		refreshData();
	}

	/**
	 * @author Cecile Dai
	 * @param evt
	 */
	private void removeVacationButtonActionPerformed(java.awt.event.ActionEvent evt) {
		error = "";
		String dates = "";
		String startDate = "";
		String endDate = "";
		String startTime = "";
		String endTime = "";
		int result = JOptionPane.showConfirmDialog(null, removeVacationPanel, "Remove Vacation",
				JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			dates = fromEasyDate((String) removeVacationComboBox.getSelectedItem());
			String[] datesSections = dates.split(" ");
			startDate = datesSections[0];
			endDate = datesSections[2];
			startTime = datesSections[1];
			endTime = datesSections[3];

			try {
				FlexiBookController.removeTimeSlot("vacation", startDate, startTime, endDate, endTime);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				error = e.getMessage();
			}
		} else {
			System.out.println("Changes cancelled");
		}
		refreshData();
	}

	/**
	 * Private method to turn dates into words
	 * 
	 * @author Cecile Dai
	 * @param date
	 * @return the date in word format
	 */

	private String toEasyDate(Date date) {
		String dateString = "";
		if (date != null) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			int month = cal.get(Calendar.MONTH);
			int day = cal.get(Calendar.DAY_OF_MONTH);
			int year = cal.get(Calendar.YEAR);

			String monthWords = null;

			switch (month) {
			case 0:
				monthWords = "January";
				break;
			case 1:
				monthWords = "February";
				break;
			case 2:
				monthWords = "March";
				break;
			case 3:
				monthWords = "April";
				break;
			case 4:
				monthWords = "May";
				break;
			case 5:
				monthWords = "June";
				break;
			case 6:
				monthWords = "July";
				break;
			case 7:
				monthWords = "August";
				break;
			case 8:
				monthWords = "September";
				break;
			case 9:
				monthWords = "October";
				break;
			case 10:
				monthWords = "November";
				break;
			case 11:
				monthWords = "December";
				break;
			}

			dateString = monthWords + " " + day + " " + year;
		}
		return dateString;
	}

	/**
	 * Private helper method to convert dates as words into dates as numbers
	 * 
	 * @author Cecile Dai
	 * @param dateString
	 * @return the date in YYYY-MM-dd format
	 */
	private String fromEasyDate(String dateString) {
		String[] sections = dateString.split(" ");

		String dateNumber = "";

		String startDayNumber = sections[1];
		String startYearNumber = sections[2];
		String startMonthNumber = sections[0];
		String startTime = sections[4];
		String endDayNumber = sections[7];
		String endYearNumber = sections[8];
		String endMonthNumber = sections[6];
		String endTime = sections[10];

		switch (startMonthNumber) {
		case "January":
			startMonthNumber = "01";
			break;
		case "February":
			startMonthNumber = "02";
			break;
		case "March":
			startMonthNumber = "03";
			break;
		case "April":
			startMonthNumber = "04";
			break;
		case "May":
			startMonthNumber = "05";
			break;
		case "June":
			startMonthNumber = "06";
			break;
		case "July":
			startMonthNumber = "07";
			break;
		case "August":
			startMonthNumber = "08";
			break;
		case "September":
			startMonthNumber = "09";
			break;
		case "October":
			startMonthNumber = "10";
			break;
		case "November":
			startMonthNumber = "11";
			break;
		case "December":
			startMonthNumber = "12";
			break;
		}

		switch (endMonthNumber) {
		case "January":
			endMonthNumber = "01";
			break;
		case "February":
			endMonthNumber = "02";
			break;
		case "March":
			endMonthNumber = "03";
			break;
		case "April":
			endMonthNumber = "04";
			break;
		case "May":
			endMonthNumber = "05";
			break;
		case "June":
			endMonthNumber = "06";
			break;
		case "July":
			endMonthNumber = "07";
			break;
		case "August":
			endMonthNumber = "08";
			break;
		case "September":
			endMonthNumber = "09";
			break;
		case "October":
			endMonthNumber = "10";
			break;
		case "November":
			endMonthNumber = "11";
			break;
		case "December":
			endMonthNumber = "12";
			break;
		}

		dateNumber = startYearNumber + "-" + startMonthNumber + "-" + startDayNumber + " " + startTime + " "
				+ endYearNumber + "-" + endMonthNumber + "-" + endDayNumber + " " + endTime;
		return dateNumber;
	}

	// ====================================

	/**
	 * @author Karim Elgammal
	 */

	private void initCustomerFrame() {

//		appointmentScrollPane = new JScrollPane(overviewTable);
//		this.add(appointmentScrollPane);
//		Dimension d = overviewTable.getPreferredSize();
//		appointmentScrollPane.setPreferredSize(new Dimension(d.width, HEIGHT_OVERVIEW_TABLE));
//		appointmentScrollPane.setVerticalScrollBarPolicy( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		// TODO KArim

		JFrame customerFrame = new JFrame();

		usernameLabel = new JLabel("Username:");
		passwordLabel = new JLabel("Password:");
		usernameField = new JTextField();
//		usernameField.setPreferredSize(new Dimension(100,100));
		passwordField = new JTextField();
//		passwordField.setPreferredSize(new Dimension(100,100));
		customerManageInstructions = new JLabel(
				"To change your username or password, please enter the desired changes and click the save changes button below");
		saveChangesButton = new JButton("Save Changes");
		deleteCustomerAccountButton = new JButton("Delete Account");
		saveChangesButton = new JButton("Save Changes");

		GroupLayout customerLayout = new GroupLayout(customerTab);
		customerTab.setLayout(customerLayout);
		customerLayout.setAutoCreateGaps(true);
		customerLayout.setAutoCreateContainerGaps(true);

//		customerLayout.setHorizontalGroup(
//				customerLayout.createParallelGroup()
//				.addComponent(usernameLabel)
//				.addComponent(passwordLabel)
//				.addComponent(customerManageInstructions)
//				.addComponent(deleteCustomerAccountButton)
//			.addGroup(customerLayout.createParallelGroup()
//					.addComponent(usernameField)
//					.addComponent(passwordField)
//					.addComponent(saveChangesButton)
//					)
//				);
//		
//		customerLayout.setVerticalGroup(
//				customerLayout.createParallelGroup()
//				.addComponent(usernameLabel)
//				.addComponent(usernameField)
//				.addGroup(customerLayout.createParallelGroup()
//						.addComponent(passwordLabel)
//						.addComponent(passwordField))
//				.addGroup(customerLayout.createParallelGroup()
//						.addComponent(customerManageInstructions)
//						)
//				.addGroup(customerLayout.createParallelGroup()
//						.addComponent(deleteCustomerAccountButton)
//						.addComponent(saveChangesButton))
//				);

		customerLayout.setHorizontalGroup(customerLayout.createSequentialGroup()
				.addGroup(customerLayout.createParallelGroup().addComponent(usernameLabel).addComponent(passwordLabel)
						.addComponent(customerManageInstructions).addComponent(deleteCustomerAccountButton))
				.addGroup(customerLayout.createParallelGroup().addComponent(usernameField).addComponent(passwordField)
						.addComponent(saveChangesButton)));
		customerLayout.setVerticalGroup(customerLayout.createSequentialGroup()
				.addGroup(customerLayout.createParallelGroup().addComponent(usernameLabel).addComponent(usernameField))

				.addGroup(customerLayout.createParallelGroup().addComponent(passwordLabel).addComponent(passwordField))

				.addComponent(customerManageInstructions)

				.addGroup(customerLayout.createParallelGroup().addComponent(deleteCustomerAccountButton)
						.addComponent(saveChangesButton))

		);
//		pack();

		deleteCustomerAccountButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				deleteCustomerAccountPerformed(evt);
			}
		});

		saveChangesButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				saveChangesButtonPerformed(evt);
			}
		});

		customerTab.add(customerFrame.getContentPane());
		customerFrame.pack();
		// customerFrame.setVisible(true);

	}

	/**
	 * 
	 * @author Karim Elgammal
	 * 
	 * @param evt
	 * 
	 */

	private void deleteCustomerAccountPerformed(ActionEvent evt) {

		// clear error message
		error = null;

		// call the controller

		try {

			FlexiBookController.deleteCustomerAccount(FlexiBookApplication.getCurrentUser(),
					FlexiBookApplication.getFlexiBook());
			JOptionPane.showMessageDialog(deleteCustomerAccountButton, "You have successfully deleted your account");

		} catch (InvalidInputException e) {
			error = e.getMessage();
			JOptionPane.showMessageDialog(deleteCustomerAccountButton, error);
		}

		// update visuals

		refreshData();

	}

	/**
	 * 
	 * @author Karim Elgammal
	 * 
	 * @param evt
	 * 
	 */

	private void saveChangesButtonPerformed(ActionEvent evt) {

		// clear error messag
		error = null;

		// call the controller
		try {

			FlexiBookController.updateAccount(usernameField.getText(), passwordField.getText(),
					FlexiBookApplication.getFlexiBook());
			JOptionPane.showMessageDialog(saveChangesButton, "You have successfully updated your account");
		} catch (InvalidInputException e) {
			error = e.getMessage();
			JOptionPane.showMessageDialog(saveChangesButton, error);
		}
		// update visuals
		refreshData();
	}

	private void initServicesFrame() {
		// TODO Ervin
		JFrame servicesFrame = new JFrame();

		servicesFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// JLabel l = new JLabel("new text ");
		// servicesTab.add(l);

		// Container contentPanel = servicesFrame.setContentPane();

		// elements for error message
		errorMessage = new JLabel();
		// errorMessage.setText(error);
		errorMessage.setForeground(Color.RED);

		// elements for add service
		AddServiceLabel = new JLabel();
		AddServiceLabel.setText("Add Service");
		AddServiceLabel.setFont(AddServiceLabel.getFont().deriveFont(18.0f));
		AddServiceLabel.setForeground(Color.gray);
		serviceNameTextField = new JTextField();
		serviceNameLabel = new JLabel();
		serviceNameLabel.setText("Service Name:");
		serviceDurationTextField = new JTextField();
		serviceDurationLabel = new JLabel();
		serviceDurationLabel.setText("Service Duration:");
		serviceDownTimeStartTextField = new JTextField();
		serviceDownTimeStartLabel = new JLabel();
		serviceDownTimeStartLabel.setText("Service DownTime Start:");
		serviceDownTimeTextField = new JTextField();
		serviceDownTimeLabel = new JLabel();
		serviceDownTimeLabel.setText("Service DownTime Duration:");
		addServiceButton = new JButton();
		addServiceButton.setText("Add Service");

		// elements for update service
		UpdateServiceLabel = new JLabel();
		UpdateServiceLabel.setText("Update Service");
		UpdateServiceLabel.setFont(AddServiceLabel.getFont().deriveFont(18.0f));
		UpdateServiceLabel.setForeground(Color.gray);
		serviceList = new JComboBox<String>(new String[0]);
		selectServiceLabel = new JLabel();
		selectServiceLabel.setText("Select Service:");
		newServiceNameTextField = new JTextField();
		newServiceNameLabel = new JLabel();
		newServiceNameLabel.setText("New Service Name:");
		newServiceDurationTextField = new JTextField();
		newServiceDurationLabel = new JLabel();
		newServiceDurationLabel.setText("New Service Duration:");
		newServiceDownTimeTextField = new JTextField();
		newServiceDownTimeLabel = new JLabel();
		newServiceDownTimeStartTextField = new JTextField();
		newServiceDownTimeStartLabel = new JLabel();
		newServiceDownTimeStartLabel.setText("New Service DownTime Start:");
		newServiceDownTimeLabel.setText("New Service DownTime:");
		updateServiceButton = new JButton();
		updateServiceButton.setText("Update Service");

		// elements for delete service
		DeleteServiceLabel = new JLabel();
		DeleteServiceLabel.setText("Update Service");
		DeleteServiceLabel.setFont(AddServiceLabel.getFont().deriveFont(18.0f));
		DeleteServiceLabel.setForeground(Color.gray);
		deleteServiceList = new JComboBox<String>(new String[0]);
		deleteServiceListLabel = new JLabel();
		deleteServiceListLabel.setText("Select Service:");
		deleteServiceButton = new JButton();
		deleteServiceButton.setText("Delete Service");

		sOverviewTable = new JTable() {
			private static final long serialVersionUID = 1L;

			@Override
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
				Component c = super.prepareRenderer(renderer, row, column);
				if (!c.getBackground().equals(getSelectionBackground())) {
					c.setBackground(Color.WHITE);
				}
				return c;
			}

		};

		serviceScrollPane = new JScrollPane(sOverviewTable);
		this.add(serviceScrollPane);
		Dimension d = sOverviewTable.getPreferredSize();
		serviceScrollPane.setPreferredSize(new Dimension(d.width, HEIGHT_OVERVIEW_TABLE));
		serviceScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		// horizontal line elements
		JSeparator horizontalLineTop = new JSeparator();
		JSeparator horizontalLineMiddle1 = new JSeparator();
		JSeparator horizontalLineMiddle2 = new JSeparator();
		JSeparator horizontalLineBottom = new JSeparator();

		GroupLayout layout = new GroupLayout(servicesTab);
		servicesTab.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

		layout.setHorizontalGroup(layout.createParallelGroup().addComponent(errorMessage)
				.addComponent(horizontalLineTop).addComponent(horizontalLineMiddle1).addComponent(horizontalLineMiddle2)
				.addComponent(serviceScrollPane)
				// .addComponent(horizontalLineBottom)
				.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup().addComponent(AddServiceLabel)
								.addComponent(serviceNameLabel).addComponent(UpdateServiceLabel)
								.addComponent(selectServiceLabel).addComponent(newServiceNameLabel)
								.addComponent(DeleteServiceLabel).addComponent(deleteServiceListLabel))
						.addGroup(layout.createParallelGroup().addComponent(serviceNameTextField, 80, 80, 80)
								.addComponent(serviceList, 130, 130, 130)
								.addComponent(newServiceNameTextField, 80, 80, 80)
								.addComponent(deleteServiceList, 130, 130, 130))
						.addGroup(layout.createParallelGroup().addComponent(serviceDurationLabel)
								.addComponent(newServiceDurationLabel).addComponent(deleteServiceButton))
						.addGroup(layout.createParallelGroup().addComponent(serviceDurationTextField, 80, 80, 80)
								.addComponent(newServiceDurationTextField, 80, 80, 80))
						.addGroup(layout.createParallelGroup().addComponent(serviceDownTimeStartLabel)
								.addComponent(newServiceDownTimeStartLabel))
						.addGroup(layout.createParallelGroup().addComponent(serviceDownTimeStartTextField, 80, 80, 80)
								.addComponent(newServiceDownTimeStartTextField, 80, 80, 80))
						.addGroup(layout.createParallelGroup().addComponent(serviceDownTimeLabel)
								.addComponent(newServiceDownTimeLabel))
						.addGroup(layout.createParallelGroup().addComponent(serviceDownTimeTextField, 80, 80, 80)
								.addComponent(newServiceDownTimeTextField, 80, 80, 80))
						.addGroup(layout.createParallelGroup().addComponent(addServiceButton, 120, 120, 120)
								.addComponent(updateServiceButton, 120, 120, 120))));

		layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] { addServiceButton, serviceNameTextField });
		layout.linkSize(SwingConstants.HORIZONTAL,new java.awt.Component[] { updateServiceButton, newServiceNameTextField });
		layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] { deleteServiceButton, deleteServiceList });

		layout.setVerticalGroup(layout.createSequentialGroup().addComponent(errorMessage)
				.addGroup(layout.createParallelGroup().addComponent(AddServiceLabel))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(serviceNameLabel)
						.addComponent(serviceNameTextField).addComponent(serviceDurationLabel)
						.addComponent(serviceDurationTextField).addComponent(serviceDownTimeStartLabel)
						.addComponent(serviceDownTimeStartTextField).addComponent(serviceDownTimeLabel)
						.addComponent(serviceDownTimeTextField).addComponent(addServiceButton))
				.addGroup(layout.createParallelGroup().addComponent(horizontalLineTop))
				.addGroup(layout.createParallelGroup().addComponent(UpdateServiceLabel))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(selectServiceLabel)
						.addComponent(serviceList))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(newServiceNameLabel)
						.addComponent(newServiceNameTextField).addComponent(newServiceDurationLabel)
						.addComponent(newServiceDurationTextField).addComponent(newServiceDownTimeStartLabel)
						.addComponent(newServiceDownTimeStartTextField).addComponent(newServiceDownTimeLabel)
						.addComponent(newServiceDownTimeTextField).addComponent(updateServiceButton))
				.addGroup(layout.createParallelGroup().addComponent(horizontalLineMiddle1))
				.addGroup(layout.createParallelGroup().addComponent(DeleteServiceLabel))
				.addGroup(
						layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(deleteServiceListLabel)
								.addComponent(deleteServiceList).addComponent(deleteServiceButton))
				.addGroup(layout.createParallelGroup().addComponent(horizontalLineMiddle2))
				.addComponent(serviceScrollPane));

		servicesFrame.pack();
		refreshServiceTable();

		// listeners for add
		addServiceButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				addServiceButtonActionPerformed(evt);
				refreshServiceTable();
			}
		});

		// listeners for update
		updateServiceButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				updateServiceButtonActionPerformed(evt);
				refreshServiceTable();
			}
		});
		
		// listeners for delete
		deleteServiceButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				deleteServiceButtonActionPerformed(evt);
				refreshServiceTable();
			}
		});

	}

	// ===================== Ervin's Methods =======================//

	private void refreshServiceTable() {
		// errorMessage.setText(error);
		serviceOverviewDtm = new DefaultTableModel(0, 0);
		serviceOverviewDtm.setColumnIdentifiers(overviewColumnNames);
		sOverviewTable.setModel(serviceOverviewDtm);
		// System.out.println(services);
		if (FlexiBookController.getAvailableServices() != null) {
			for (TOService service : FlexiBookController.getAvailableServices()) {
				// System.out.println(service.getName());

				Object[] obj = { service.getName(), service.getDuration(), service.getDowntimeStart(),
						service.getDowntimeDuration() };
				serviceOverviewDtm.addRow(obj);
			}
			// do something with DTOs of apppointments
		}
		Dimension d = sOverviewTable.getPreferredSize();
		serviceScrollPane.setPreferredSize(new Dimension(d.width, HEIGHT_OVERVIEW_TABLE));

	}

	/**
	 * @author Ervin Cai
	 */
	private void addServiceButtonActionPerformed(java.awt.event.ActionEvent evt) {
		// clear error message
		error = "";

		if (!FlexiBookApplication.getCurrentUser().getUsername().equals("owner")
				|| FlexiBookApplication.getCurrentUser().getUsername().equals(null)) {
			System.out.println("Arrived");
			error = "Only owner can add a service!";
		}
		if (serviceNameTextField.getText().equals("")) {
			error = "Service name cannot be empty!";
		}
		if (serviceDurationTextField.getText().equals("")) {
			error = "Service duration cannot be empty!";
		}
		if (error == "" && serviceDownTimeTextField.getText().equals("")) {
			serviceDownTimeTextField.setText("0");
			serviceDownTimeStartTextField.setText("0");
		}

		if (error.length() == 0) {
			// call the controller
			try {
				String username = FlexiBookApplication.getCurrentUser().getUsername();
				FlexiBookController.addService(username, serviceNameTextField.getText(),
						Integer.parseInt(serviceDurationTextField.getText()),
						Integer.parseInt(serviceDownTimeStartTextField.getText()),
						Integer.parseInt(serviceDownTimeTextField.getText()), FlexiBookApplication.getFlexiBook());
			} catch (InvalidInputException e) {
				error = e.getMessage();
			}
		}
		// System.out.println("ERROR:"+error);
		// update visuals
		refreshData();
	}

	/**
	 * @author Ervin Cai
	 */
	private void updateServiceButtonActionPerformed(java.awt.event.ActionEvent evt) {
		// clear error message
		error = "";

		if (!FlexiBookApplication.getCurrentUser().getUsername().equals("owner")
				|| FlexiBookApplication.getCurrentUser().getUsername().equals(null)) {
			System.out.println("Arrived");
			error = "Only owner can add a service!";
		}
		if (newServiceNameTextField.getText().equals("")) {
			error = "Service name cannot be empty!";
		}
		if (newServiceDurationTextField.getText().equals("")) {
			error = "Service duration cannot be empty!";
		}
		if (newServiceDownTimeTextField.getText().equals("")) {
			newServiceDownTimeTextField.setText("0");
			newServiceDownTimeStartTextField.setText("0");
		}

		if (error.length() == 0) {
			// call the controller
			try {
				String username = FlexiBookApplication.getCurrentUser().getUsername();
				String serviceName = String.valueOf(serviceList.getSelectedItem());
				FlexiBookController.updateService(username, serviceName, newServiceNameTextField.getText(),
						Integer.parseInt(newServiceDurationTextField.getText()),
						Integer.parseInt(newServiceDownTimeStartTextField.getText()),
						Integer.parseInt(newServiceDownTimeTextField.getText()), FlexiBookApplication.getFlexiBook());
			} catch (InvalidInputException e) {
				error = e.getMessage();
			}
		}
		// System.out.println("ERROR:"+error);
		// update visuals
		refreshData();
	}

	/**
	 * @author Ervin Cai
	 */
	private void deleteServiceButtonActionPerformed(java.awt.event.ActionEvent evt) {
		// clear error message
		if (!FlexiBookApplication.getCurrentUser().getUsername().equals("owner")
				|| FlexiBookApplication.getCurrentUser().getUsername().equals(null)) {
			System.out.println("Arrived");
			error = "Only owner can add a service!";
		}

		error = "";
		String serviceName = String.valueOf(deleteServiceList.getSelectedItem());
		// Check for appointments
		Service foundService = null;
		for (BookableService service : FlexiBookApplication.getFlexiBook().getBookableServices()) {
			if (service instanceof Service) {
				if (service.getName().equalsIgnoreCase(serviceName)) {
					foundService = (Service) service;
					break;
				}
			}
		}

		if (foundService.numberOfAppointments() > 0) {
			error = "Service has an appointment!";
		}

		if (error.length() == 0) {
			// call the controller
			try {
				String username = FlexiBookApplication.getCurrentUser().getUsername();

				FlexiBookController.deleteService(username, serviceName, FlexiBookApplication.getFlexiBook());
			} catch (InvalidInputException e) {
				error = e.getMessage();
			}
		}

		refreshData();
	}

	// ===============================================================//
	JButton signUpButton;
	private boolean isSuccessUpdateAppt;

	private void initLoginFrame() {
		lblUsername = new JLabel("Username:");

		textField = new JTextField();

		lblPassword = new JLabel("Password");

		passwordFieldM = new JPasswordField();

		LogInButton = new JButton("Login");

		LogoutButton = new JButton("Logout");

		signUpButton = new JButton("Sign Up");

		// layout

		GroupLayout loginTabLayout = new GroupLayout(loginTab);

		loginTab.setLayout(loginTabLayout);

		loginTabLayout.setAutoCreateGaps(true);

		loginTabLayout.setAutoCreateContainerGaps(true);

		loginTabLayout.setHorizontalGroup(

				loginTabLayout.createParallelGroup()

						.addGroup(loginTabLayout.createSequentialGroup()

								.addGroup(loginTabLayout.createParallelGroup()

										.addComponent(lblUsername)

										.addComponent(lblPassword, 70, 70, 140)

										.addGroup(loginTabLayout.createSequentialGroup()

												.addComponent(LogInButton, 110, 110, 220)

												.addComponent(LogoutButton, 110, 110, 220)

												.addComponent(signUpButton, 110, 110, 220)))

								.addGroup(loginTabLayout.createParallelGroup()

										.addComponent(textField, 70, 70, 140)

										.addComponent(passwordFieldM, 70, 70, 140))

						));

		loginTabLayout.setVerticalGroup(

				loginTabLayout.createSequentialGroup()

						.addGroup(loginTabLayout.createParallelGroup()

								.addComponent(lblUsername)

								.addComponent(textField))

						.addGroup(loginTabLayout.createParallelGroup()

								.addComponent(lblPassword)

								.addComponent(passwordFieldM))

						.addGroup(loginTabLayout.createParallelGroup()

								.addComponent(LogInButton)

								.addComponent(LogoutButton)

								.addComponent(signUpButton))

		);

		pack();

		LogInButton.addActionListener(new java.awt.event.ActionListener() {

			public void actionPerformed(java.awt.event.ActionEvent evt) {
				LogInButtonActionPerformed(evt);
			}

		});

		LogoutButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				LogoutButtonActionPerformed(evt);
			}

		});

		signUpButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				signUpButtonActionPerformed(evt);
			}

		});

	}

	private void signUpButtonActionPerformed(ActionEvent evt) {
		error = "";
		String username = textField.getText();
		String password = passwordFieldM.getText();

		try {
			FlexiBookController.createCustomerAccount(username, password, FlexiBookApplication.getFlexiBook());
			JOptionPane.showMessageDialog(signUpButton, "You have successfully signed up");
		} catch (InvalidInputException e) {

			error = e.getMessage();// where would the error occur!!!
			JOptionPane.showMessageDialog(signUpButton, error);
		}
		refreshData();
	}

	private void LogInButtonActionPerformed(ActionEvent evt) {

		error = "";
		String username = textField.getText();
		String password = passwordFieldM.getText();

		try {
			FlexiBookController.LogIn(username, password);
			JOptionPane.showMessageDialog(LogInButton, "You have successfully logged in");
		} catch (InvalidInputException e) {

			error = e.getMessage();// where would the error occur!!!
			JOptionPane.showMessageDialog(LogInButton, error);
		}
		refreshData();
	}

	private void LogoutButtonActionPerformed(ActionEvent evt) {

		error = "";
		try {
			FlexiBookController.LogOut();
		} catch (Exception e) {

			error = e.getMessage();
			JOptionPane.showMessageDialog(LogoutButton, error);
		}
		refreshData();

	}

	private void initAppointmentFrame() {
		// CREATE UPDATE CANCEL APPOINTMENT
		makeAppointmentLabel = new JLabel("Make Appointment:");
		cancelAppointmentLabel = new JLabel("Update Appointment:");
		updateAppointmentLabel = new JLabel("Cancel Appointment:");

		makeAppointmentButton = new JButton();
		makeAppointmentButton.setText("Make Appointment");

		updateAppointmentButton = new JButton();
		updateAppointmentButton.setText("Update Appointment");

		cancelAppointmentButton = new JButton();
		cancelAppointmentButton.setText("Cancel Appointment");

		// elements for make appointment
		makeAppointmentTime = new JTextField();
		makeAppointmentDate = new JTextField();
		makeAppointmentServiceList = new JComboBox<String>(new String[0]);
		makeAppointmentTimeLabel = new JLabel();
		makeAppointmentTimeLabel.setText("Appointment Time: ");
		makeAppointmentDateLabel = new JLabel();
		makeAppointmentDateLabel.setText("Appointment date: ");
		makeAppointmentServiceLabel = new JLabel();
		makeAppointmentServiceLabel.setText("Service: ");

		// elements for make appointment
		updateAppointmentDateList = new JComboBox<String>(new String[0]);
		updateAppointmentNewTime = new JTextField();
		updateAppointmentNewDate = new JTextField();
		updateAppointmentServiceList = new JComboBox<String>(new String[0]);
		updateAppointmentDateLabel = new JLabel();
		updateAppointmentDateLabel.setText("Appointment: ");
		updateAppointmentNewTimeLabel = new JLabel();
		updateAppointmentNewTimeLabel.setText("New Time: ");
		updateAppointmentNewDateLabel = new JLabel();
		updateAppointmentNewDateLabel.setText("New Date: ");
		updateAppointmentServiceLabel = new JLabel();
		updateAppointmentServiceLabel.setText("New Service: ");

		// elements for cancel appointment
		cancelAppointmentDateList = new JComboBox<String>(new String[0]);
		cancelAppointmentDateLabel = new JLabel();
		cancelAppointmentDateLabel.setText("Appointment: ");

		
		// =======================================================================================

		// VIEW APPT CALENDAR
		SqlDateModel appointmentModel = new SqlDateModel();
		LocalDate now = LocalDate.now();
		appointmentModel.setDate(now.getYear(), now.getMonthValue() - 1, now.getDayOfMonth());
		appointmentModel.setSelected(true);
		Properties pO = new Properties();
		pO.put("text.today", "Today");
		pO.put("text.month", "Month");
		pO.put("text.year", "Year");
		JDatePanelImpl appointmentDatePanel = new JDatePanelImpl(appointmentModel, pO);
		appointmentDatePicker = new JDatePickerImpl(appointmentDatePanel, new DateLabelFormatter());
		appointmentDateLabel = new JLabel();
		appointmentDateLabel.setText("Date:");

		appointmentTable = new JTable() {
			private static final long serialVersionUID = 2L;

			@Override
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
				Component c = super.prepareRenderer(renderer, row, column);
				if (!c.getBackground().equals(getSelectionBackground())) {
					c.setBackground(Color.WHITE);
				}
				return c;
			}

		};

		appointmentScrollPane = new JScrollPane(appointmentTable);
		this.add(appointmentScrollPane);
		Dimension d = appointmentTable.getPreferredSize();
		appointmentScrollPane.setPreferredSize(new Dimension(d.width, HEIGHT_OVERVIEW_TABLE));
		appointmentScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		// START END NO SHOW APPOINTMENT
		errorAppt = new JLabel();
		errorAppt.setForeground(Color.RED);
		
		selectStartAppointmentLabel = new JLabel("Select Appointment:");
		selectStartAppointmentList = new JComboBox<String>(new String[0]);
		startAppointmentButton = new JButton("Start");

		selectEndNoShowAppointmentLabel = new JLabel("Select Appointment:");
		selectEndNoShowAppointmentList = new JComboBox<String>(new String[0]);
		endAppointmentButton = new JButton("End");
		noShowAppointmentButton = new JButton("No Show");

		appointmentStartedLabel = new JLabel("Appointment In progress:");
		appointmentStartedReturn = new JLabel("None");

		appointmentEndedLabel = new JLabel("Appointment Ended:");
		appointmentEndedReturn = new JLabel("None");

		appointmentNoShowLabel = new JLabel("Appointment No Show:");
		appointmentNoShowReturn = new JLabel("None");

		JSeparator horizontalLineTop = new JSeparator();
		JSeparator horizontalLineMiddle1 = new JSeparator();
		JSeparator horizontalLineMiddle2 = new JSeparator();
		JSeparator horizontalLineBottom = new JSeparator();

		// layout
		GroupLayout appointmentLayout = new GroupLayout(appointmentTab);
		appointmentTab.setLayout(appointmentLayout);
		appointmentLayout.setAutoCreateGaps(true);
		appointmentLayout.setAutoCreateContainerGaps(true);

		appointmentLayout.setHorizontalGroup(appointmentLayout.createParallelGroup()
				.addComponent(errorAppt)
				.addComponent(horizontalLineTop)
				.addComponent(horizontalLineMiddle1)
				.addComponent(horizontalLineMiddle2)
				.addComponent(horizontalLineBottom)
				.addComponent(appointmentScrollPane)
				.addGroup(appointmentLayout.createSequentialGroup()
						.addGroup(appointmentLayout.createParallelGroup()
								.addComponent(makeAppointmentLabel)
								.addComponent(updateAppointmentLabel)
								.addComponent(cancelAppointmentLabel)
								.addComponent(selectStartAppointmentLabel)
								.addComponent(selectEndNoShowAppointmentLabel))
						.addGroup(appointmentLayout.createParallelGroup()
								.addGroup(appointmentLayout.createSequentialGroup()
										.addGroup(appointmentLayout.createParallelGroup()
												.addComponent(makeAppointmentTimeLabel)
												.addComponent(updateAppointmentDateLabel)
												.addComponent(updateAppointmentNewTimeLabel,25, 25, 100)
												.addComponent(cancelAppointmentDateLabel))
										.addGroup(appointmentLayout.createParallelGroup()
												.addComponent(makeAppointmentTime, 25, 25, 200)
												.addComponent(updateAppointmentDateList,100, 100, 200)
												.addComponent(updateAppointmentNewTime, 25, 25, 200)
												//changed cancel to update below
												.addComponent(cancelAppointmentDateList, 100, 100, 200))
										.addGroup(appointmentLayout.createParallelGroup()
												.addComponent(makeAppointmentDateLabel)
												.addComponent(makeAppointmentDate, 50, 50, 200)
												.addComponent(updateAppointmentNewDateLabel)
												.addComponent(updateAppointmentNewDate, 50, 50, 200)
												.addComponent(cancelAppointmentButton, 100, 100, 200))
										.addGroup(appointmentLayout.createParallelGroup()
												.addComponent(makeAppointmentServiceLabel)
												.addComponent(updateAppointmentServiceLabel))
										.addGroup(appointmentLayout.createParallelGroup()
												.addComponent(makeAppointmentServiceList, 150, 150, 350)
												.addComponent(updateAppointmentServiceList, 150, 150, 350))
										.addGroup(appointmentLayout.createParallelGroup()
												.addComponent(makeAppointmentButton, 100, 100, 200)
												.addComponent(updateAppointmentButton, 100, 100, 200)))
										.addGroup(appointmentLayout.createParallelGroup().
										addGroup(appointmentLayout.createSequentialGroup()
												.addGroup(appointmentLayout.createParallelGroup()
												.addComponent(selectStartAppointmentList)
												.addGroup(appointmentLayout.createSequentialGroup()
														.addComponent(startAppointmentButton)
														.addComponent(noShowAppointmentButton))
												.addComponent(selectEndNoShowAppointmentList)
												.addComponent(endAppointmentButton))
										.addGroup(appointmentLayout.createParallelGroup()
												.addComponent(appointmentStartedLabel)
												.addComponent(appointmentNoShowLabel)
												.addComponent(appointmentEndedLabel)
												.addComponent(appointmentDateLabel))
										.addGroup(appointmentLayout.createParallelGroup()
												.addComponent(appointmentStartedReturn)
												.addComponent(appointmentNoShowReturn)
												.addComponent(appointmentEndedReturn)
												.addComponent(appointmentDatePicker))))

						)));
	appointmentLayout.linkSize(SwingConstants.VERTICAL,new java.awt.Component[] { makeAppointmentTime, makeAppointmentDate, makeAppointmentServiceList });
	appointmentLayout.linkSize(SwingConstants.HORIZONTAL,new java.awt.Component[] { makeAppointmentTime, makeAppointmentDate, makeAppointmentServiceList });
//		appointmentLayout.linkSize(SwingConstants.VERTICAL,new java.awt.Component[] { updateAppointmentDateLabel, updateAppointmentDateList });
//		appointmentLayout.linkSize(SwingConstants.HORIZONTAL,new java.awt.Component[] { updateAppointmentDateLabel, updateAppointmentDateList });
	appointmentLayout.linkSize(SwingConstants.VERTICAL, new java.awt.Component[] { updateAppointmentNewTime,updateAppointmentNewDate, updateAppointmentServiceList });
		appointmentLayout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] { updateAppointmentNewTime,updateAppointmentNewDate, updateAppointmentServiceList });
//		appointmentLayout.linkSize(SwingConstants.VERTICAL,new java.awt.Component[] { cancelAppointmentDateLabel, cancelAppointmentDateList });
//		appointmentLayout.linkSize(SwingConstants.HORIZONTAL,new java.awt.Component[] { cancelAppointmentDateLabel, cancelAppointmentDateList });

		appointmentLayout.setVerticalGroup(appointmentLayout.createSequentialGroup()
				.addComponent(errorAppt)
				.addComponent(makeAppointmentLabel)
				.addComponent(makeAppointmentDateLabel)
				.addGroup(appointmentLayout.createParallelGroup()
						.addComponent(makeAppointmentTimeLabel)
						.addComponent(makeAppointmentTime)
						.addComponent(makeAppointmentDate)
						.addComponent(makeAppointmentServiceLabel)
						.addComponent(makeAppointmentServiceList)
						.addComponent(makeAppointmentButton))
				.addGroup(appointmentLayout.createParallelGroup()
						.addComponent(horizontalLineTop))
				.addComponent(cancelAppointmentLabel)
				.addGroup(appointmentLayout.createParallelGroup()
						.addComponent(updateAppointmentDateLabel)
						.addComponent(updateAppointmentDateList)
						.addComponent(updateAppointmentNewDateLabel))
				.addGroup(appointmentLayout.createParallelGroup()
						.addComponent(updateAppointmentNewTimeLabel)
						.addComponent(updateAppointmentNewTime)
						.addComponent(updateAppointmentNewDate)
						.addComponent(updateAppointmentServiceLabel)
						.addComponent(updateAppointmentServiceList)
						.addComponent(updateAppointmentButton))
				.addGroup(appointmentLayout.createParallelGroup()
						.addComponent(horizontalLineMiddle1))
				.addGroup(appointmentLayout.createParallelGroup()
						.addComponent(updateAppointmentLabel)
						.addComponent(cancelAppointmentDateLabel)
						//changed cancel to update below (date)
						.addComponent(cancelAppointmentDateList)
						.addComponent(cancelAppointmentButton))
				.addGroup(appointmentLayout.createParallelGroup()
						.addComponent(horizontalLineMiddle2))
				.addGroup(appointmentLayout.createParallelGroup()
						.addComponent(selectStartAppointmentLabel)
						.addComponent(selectStartAppointmentList)
						.addComponent(appointmentStartedLabel)
						.addComponent(appointmentStartedReturn))
				.addGroup(appointmentLayout.createParallelGroup()
						.addComponent(startAppointmentButton)
						.addComponent(noShowAppointmentButton)
						.addComponent(appointmentNoShowLabel)
						.addComponent(appointmentNoShowReturn))
				.addGroup(appointmentLayout.createParallelGroup()
						.addComponent(selectEndNoShowAppointmentLabel)
						.addComponent(selectEndNoShowAppointmentList)
						.addComponent(appointmentEndedLabel)
						.addComponent(appointmentEndedReturn))
				.addComponent(endAppointmentButton)
				.addGroup(appointmentLayout.createParallelGroup()
						.addComponent(horizontalLineBottom))
				.addGroup(appointmentLayout.createParallelGroup()
						.addComponent(appointmentDateLabel)
						.addComponent(appointmentDatePicker))
				.addComponent(appointmentScrollPane)

		);

		pack();

		// LISTENERS FOR START, END, NO SHOW APPOINTMENT
		startAppointmentButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					startButtonActionPerformed(evt);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		endAppointmentButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					endButtonActionPerformed(evt);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		noShowAppointmentButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					noShowButtonActionPerformed(evt);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		appointmentDatePicker.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(java.awt.event.ActionEvent evt) {
		        refreshAppointmentTable();
		    }
		});		


		makeAppointmentButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				makeAppointmentActionPerformed(evt);
			}
		});
		updateAppointmentButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				updateAppointmentActionPerformed(evt);
			}
		});
		cancelAppointmentButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancelAppointmentActionPerformed(e);
			}
		});
	}

	private void cancelAppointmentActionPerformed(ActionEvent evt) {
		Object error = null;
		Object success = null;
		// FlexiBook fbs;

//		Date currentDate = FlexiBookController.getSystemDate();
//		Time currentTime = FlexiBookController.getSystemTime();
		
//		try {
//
//			int selectedAppointment = cancelAppointmentDateList.getSelectedIndex();
//			if (selectedAppointment == -1) {
//				throw new InvalidInputException("Need to select an appointment to cancel!");
//			}
//
//			String username = FlexiBookApplication.getCurrentUser().getUsername();
//			// String startTime =
//			// currentAppoitment.get(selectedAppointment).getStartTime().substring(0, 5);
//			// String startDate = currentAppoitment.get(selectedAppointment).getStartDate();
//			String thisApp = (String) cancelAppointmentDateList.getSelectedItem();
//			String[] thisAppSections = thisApp.split(" ");
//			String appService = thisAppSections[0];
//			String appStartDate = thisAppSections[2];
//			String appStartTime = thisAppSections[4];
//			BookableService bs = null;
//			// Appointment toBeCancelled = null;
//			for (BookableService service : FlexiBookApplication.getFlexiBook().getBookableServices()) {
//				if (appService.equals(((Service) service).getName())) {
//					bs = service;
//					break;
//				}
//			}
//
//			Date startDate = null;
//			try {
//				startDate = fromStringToDate(appStartDate);
//			} catch (ParseException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			Time startTime = Time.valueOf(appStartTime + ":00");
//			Time endTime = new Time(startTime.getTime() + ((Service) bs).getDuration() * 60000);
//			TimeSlot timeSlot = new TimeSlot(startDate, startTime, startDate, endTime,
//					FlexiBookApplication.getFlexiBook());
//			if (FlexiBookApplication.getCurrentUser() instanceof User) {
//				error = "Only customers can make appointments";
//			} else {
//				Appointment toBeCancelled = new Appointment((Customer) FlexiBookApplication.getCurrentUser(), bs,
//						timeSlot, FlexiBookApplication.getFlexiBook());
//				for (Appointment a : FlexiBookApplication.getFlexiBook().getAppointments()) {
//					if (startDate.compareTo(a.getTimeSlot().getStartDate()) == 0 && appService.equals(bs.getName())) {
//						toBeCancelled = a;
//					}
//				}
//				FlexiBookController.cancelAppointment(toBeCancelled, FlexiBookApplication.getFlexiBook(),
//						FlexiBookController.getSystemDate(), FlexiBookController.getSystemTime());
//				// FlexiBookController.cancelAppointment(a, fbs, currentDate, currentTime);
//				success = "Appointment on " + startDate + " at " + startTime + " is cancelled.";
//			}
//		} catch (InvalidInputException e) {
//			error = e.getMessage();
//		}
		
		apptError = "";
		String serviceName = "";
		
		int selectedAppointmentToCancel = cancelAppointmentDateList.getSelectedIndex();

		
		if (selectedAppointmentToCancel < 0 ) {
			apptError = "No appointment selected to cancel";
		}
		
		if (appointmentsToUpdateMap.get(selectedAppointmentToCancel) != null) {
			if (FlexiBookController.isUserLoggedIn(appointmentsToUpdateMap.get(selectedAppointmentToCancel).getCustomer().getUsername()) == false) {
				apptError = "you dont have permission to modify this appointment";
			}
		}
		
		if (apptError.length() == 0 ) {
			
			TOAppointment aTO = appointmentsToUpdateMap.get(selectedAppointmentToCancel);
			
			try {
				boolean back = FlexiBookController.cancelAppointmentFromView(aTO);
				success = "Appointment on " + aTO.getTimeSlot().getStartDate() + " at " + aTO.getTimeSlot().getStartTime() + " is cancelled.";
				for(Appointment app : FlexiBookApplication.getFlexiBook().getAppointments()) {
				System.out.println(app.getCustomer().getUsername());
				}
			}
			catch(InvalidInputException e) {
				apptError = e.getMessage();
			}
		}
		refreshData();
		
	}

	private void updateAppointmentActionPerformed(ActionEvent evt) {
		apptError = "";
		isSuccessUpdateAppt = false;
		boolean isNewServiceModification = true;
		boolean isDateAndTimeModification = true;
		boolean isOptionalServiceModification;
		String serviceName = "";
		
		int selectedAppointmentToUpdate = updateAppointmentDateList.getSelectedIndex();
		int selectedServiceForUpdate = updateAppointmentServiceList.getSelectedIndex();
		
		if (selectedAppointmentToUpdate < 0 ) {
			apptError = "No appointment selected to update";
		}
		if (selectedServiceForUpdate < 0 && selectedAppointmentToUpdate >= 0 ) {
			TOAppointment aTO = appointmentsToUpdateMap.get(selectedAppointmentToUpdate);
			serviceName = aTO.getBookableService().getName();
		}
		else {
			serviceName = availableServices.get(selectedServiceForUpdate);
		}
		
		String newDateString = updateAppointmentNewDate.getText();
		String newTimeString = updateAppointmentNewTime.getText();
		
		if (newDateString.length() == 0 && newTimeString.length() == 0) {
			apptError = "no new date or time entered";
		}
		if (appointmentsToUpdateMap.get(selectedAppointmentToUpdate) != null) {
			if (FlexiBookController.isUserLoggedIn(appointmentsToUpdateMap.get(selectedAppointmentToUpdate).getCustomer().getUsername()) == false) {
				apptError = "you dont have permission to modify this appointment";
			}
		}
		
		if (apptError.length() == 0 ) {
			
			TOAppointment aTO = appointmentsToUpdateMap.get(selectedAppointmentToUpdate);
			Date startDate = aTO.getTimeSlot().getStartDate();
			java.sql.Time startTime = aTO.getTimeSlot().getStartTime();
		
			if (newDateString.length() > 0 ) {
				isDateAndTimeModification = true;
				startDate = Date.valueOf(newDateString);
			}
			if (newTimeString.length() > 0) {
				isDateAndTimeModification = true;
				startTime = Time.valueOf(newTimeString + ":00");
			}
			
			
			try {
				isOptionalServiceModification = false;
				boolean back = FlexiBookController.updateAppointmentFromView(aTO, isDateAndTimeModification, isOptionalServiceModification, isNewServiceModification, serviceName, startDate, startTime);
			
			}
			catch(InvalidInputException e) {
				apptError = e.getMessage();
			}
		}
		refreshData();
		
	}

	/**
	 * @author Ervin Cai
	 */
	private void makeAppointmentActionPerformed(ActionEvent evt) {
		// clear error message
		apptError = "";

		String selectedService = String.valueOf(makeAppointmentServiceList.getSelectedItem());
		if (selectedService == "") {
			apptError = "Service needs to be selected for making appointment!";
		}

		if (apptError.length() == 0) {
			try {
				// System.out.println("Arrived");
				String username = FlexiBookApplication.getCurrentUser().getUsername();

				String startTimeText = makeAppointmentTime.getText();
				String startDateText = makeAppointmentDate.getText();
				// String str="2015-03-31";
				// String t = "10:34:34";
				Date startDate = Date.valueOf(startDateText);
				Time startTime = Time.valueOf(startTimeText + ":00");
				System.out.println(startDate);
				System.out.println(startTime);

				List<String> optionalServiceNames = null;
				// FlexiBookController.createNewUser("joe","pass",
				// FlexiBookApplication.getFlexiBook());
				FlexiBookController.makeAppointment(username, selectedService, optionalServiceNames, startDate,
						startTime, FlexiBookApplication.getFlexiBook());

			} catch (InvalidInputException e) {
				apptError = e.getMessage();
			}
		}
		refreshData();
	}

	// ============================================================================

	private void noShowButtonActionPerformed(ActionEvent evt) throws ParseException {

		apptError = "";
		int selectedApptNoShow = selectStartAppointmentList.getSelectedIndex();
		if (selectedApptNoShow < 0 ) {
			apptError = "No appointment selected to Mark no show";
		}
		if (FlexiBookController.isUserLoggedIn("owner") == false) {
			apptError = "Only the owner can mark a no show";
		}
		
		if (apptError.length() == 0) {
			TOAppointment aTO = appointmentsToStartMap.get(selectedApptNoShow);
			FlexiBookController.markNoShowFromView(aTO);
			appointmentNoShowReturn.setText(aTO.getCustomer().getUsername() + "'s appointment from "
					+ aTO.getTimeSlot().getStartTime() +  " to " +
					aTO.getTimeSlot().getEndTime());
		}
		
		refreshData();
	}

	private void endButtonActionPerformed(ActionEvent evt) throws ParseException {
		apptError = "";
		int selectedAppointmentToEnd = selectEndNoShowAppointmentList.getSelectedIndex();
		if (selectedAppointmentToEnd < 0 ) {
			apptError = "No appointment selected to end";
		}
		
		if (FlexiBookController.isUserLoggedIn("owner") == false) {
			apptError = "Only the owner can end an appointment";
		}
		
		if (error.length() == 0) {
			TOAppointment aTO = appointmentsToEndNoShowMap.get(selectedAppointmentToEnd);
			FlexiBookController.endApptFromView(aTO);
			appointmentEndedReturn.setText(aTO.getCustomer().getUsername() + "'s appointment from "
					+ aTO.getTimeSlot().getStartTime() +  " to " +
					aTO.getTimeSlot().getEndTime());
			
			appointmentStartedReturn.setText("None");
		}
		
		refreshData();

	}

	private void startButtonActionPerformed(ActionEvent evt) throws ParseException {

		apptError = "";
		int selectedAppointmentToStart = selectStartAppointmentList.getSelectedIndex();
		if (selectedAppointmentToStart < 0) {
			apptError = "No appointment selected to start";
		}
		
		if (FlexiBookController.isUserLoggedIn("owner") == false) {
			apptError = "Only the owner can start an appointment";
		}

		if (apptError.length() == 0) {
			FlexiBookController.startAppointmentFromView(appointmentsToStartMap.get(selectedAppointmentToStart));
			appointmentStartedReturn
					.setText(appointmentsToStartMap.get(selectedAppointmentToStart).getCustomer().getUsername()
							+ "'s appointment from "
							+ appointmentsToStartMap.get(selectedAppointmentToStart).getTimeSlot().getStartTime()
							+ " to "
							+ appointmentsToStartMap.get(selectedAppointmentToStart).getTimeSlot().getEndTime());
		}

		refreshData();
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

}
