/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse.flexibook.model;

// line 3 "../../../../../FlexiBookStateMachine.ump"
public class AppointmentManagement
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //AppointmentManagement State Machines
  public enum Status { AppointmentProposed, AppointmentProcessing, NoShow, AppointmentCancelled, End }
  public enum StatusAppointmentProcessing { Null, AppointmentPending, AppointmentBooked, AppointmentArrived, AppointmentFullfilled }
  private Status status;
  private StatusAppointmentProcessing statusAppointmentProcessing;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public AppointmentManagement()
  {
    setStatusAppointmentProcessing(StatusAppointmentProcessing.Null);
    setStatus(Status.AppointmentProposed);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public String getStatusFullName()
  {
    String answer = status.toString();
    if (statusAppointmentProcessing != StatusAppointmentProcessing.Null) { answer += "." + statusAppointmentProcessing.toString(); }
    return answer;
  }

  public Status getStatus()
  {
    return status;
  }

  public StatusAppointmentProcessing getStatusAppointmentProcessing()
  {
    return statusAppointmentProcessing;
  }

  public boolean makeAppointment()
  {
    boolean wasEventProcessed = false;
    
    Status aStatus = status;
    switch (aStatus)
    {
      case AppointmentProposed:
        setStatus(Status.AppointmentProcessing);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean then()
  {
    boolean wasEventProcessed = false;
    
    Status aStatus = status;
    StatusAppointmentProcessing aStatusAppointmentProcessing = statusAppointmentProcessing;
    switch (aStatus)
    {
      case NoShow:
        setStatus(Status.End);
        wasEventProcessed = true;
        break;
      case AppointmentCancelled:
        setStatus(Status.End);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    switch (aStatusAppointmentProcessing)
    {
      case AppointmentFullfilled:
        exitStatus();
        setStatus(Status.End);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean ownerAvailableStatusisTrue()
  {
    boolean wasEventProcessed = false;
    
    StatusAppointmentProcessing aStatusAppointmentProcessing = statusAppointmentProcessing;
    switch (aStatusAppointmentProcessing)
    {
      case AppointmentPending:
        exitStatusAppointmentProcessing();
        setStatusAppointmentProcessing(StatusAppointmentProcessing.AppointmentBooked);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean ownerAvailableStatusisFalse()
  {
    boolean wasEventProcessed = false;
    
    StatusAppointmentProcessing aStatusAppointmentProcessing = statusAppointmentProcessing;
    switch (aStatusAppointmentProcessing)
    {
      case AppointmentBooked:
        exitStatusAppointmentProcessing();
        setStatusAppointmentProcessing(StatusAppointmentProcessing.AppointmentPending);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean CustomerAvailableStatusIsTrue()
  {
    boolean wasEventProcessed = false;
    
    StatusAppointmentProcessing aStatusAppointmentProcessing = statusAppointmentProcessing;
    switch (aStatusAppointmentProcessing)
    {
      case AppointmentBooked:
        exitStatusAppointmentProcessing();
        setStatusAppointmentProcessing(StatusAppointmentProcessing.AppointmentArrived);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean CustomerAvailableStatusIsFalse()
  {
    boolean wasEventProcessed = false;
    
    StatusAppointmentProcessing aStatusAppointmentProcessing = statusAppointmentProcessing;
    switch (aStatusAppointmentProcessing)
    {
      case AppointmentBooked:
        exitStatus();
        setStatus(Status.NoShow);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean CancelAppointment()
  {
    boolean wasEventProcessed = false;
    
    StatusAppointmentProcessing aStatusAppointmentProcessing = statusAppointmentProcessing;
    switch (aStatusAppointmentProcessing)
    {
      case AppointmentBooked:
        exitStatus();
        setStatus(Status.AppointmentCancelled);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean UpdateAppointment()
  {
    boolean wasEventProcessed = false;
    
    StatusAppointmentProcessing aStatusAppointmentProcessing = statusAppointmentProcessing;
    switch (aStatusAppointmentProcessing)
    {
      case AppointmentArrived:
        exitStatusAppointmentProcessing();
        setStatusAppointmentProcessing(StatusAppointmentProcessing.AppointmentArrived);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean appointmentSucessfullyEnds()
  {
    boolean wasEventProcessed = false;
    
    StatusAppointmentProcessing aStatusAppointmentProcessing = statusAppointmentProcessing;
    switch (aStatusAppointmentProcessing)
    {
      case AppointmentArrived:
        exitStatusAppointmentProcessing();
        setStatusAppointmentProcessing(StatusAppointmentProcessing.AppointmentFullfilled);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  private void exitStatus()
  {
    switch(status)
    {
      case AppointmentProcessing:
        exitStatusAppointmentProcessing();
        break;
    }
  }

  private void setStatus(Status aStatus)
  {
    status = aStatus;

    // entry actions and do activities
    switch(status)
    {
      case AppointmentProcessing:
        if (statusAppointmentProcessing == StatusAppointmentProcessing.Null) { setStatusAppointmentProcessing(StatusAppointmentProcessing.AppointmentPending); }
        break;
    }
  }

  private void exitStatusAppointmentProcessing()
  {
    switch(statusAppointmentProcessing)
    {
      case AppointmentPending:
        setStatusAppointmentProcessing(StatusAppointmentProcessing.Null);
        break;
      case AppointmentBooked:
        setStatusAppointmentProcessing(StatusAppointmentProcessing.Null);
        break;
      case AppointmentArrived:
        setStatusAppointmentProcessing(StatusAppointmentProcessing.Null);
        break;
      case AppointmentFullfilled:
        setStatusAppointmentProcessing(StatusAppointmentProcessing.Null);
        break;
    }
  }

  private void setStatusAppointmentProcessing(StatusAppointmentProcessing aStatusAppointmentProcessing)
  {
    statusAppointmentProcessing = aStatusAppointmentProcessing;
    if (status != Status.AppointmentProcessing && aStatusAppointmentProcessing != StatusAppointmentProcessing.Null) { setStatus(Status.AppointmentProcessing); }
  }

  public void delete()
  {}

}