package mmt.core;

import java.io.*;
import java.util.LinkedList;

import mmt.core.exceptions.BadDateSpecificationException;
import mmt.core.exceptions.BadTimeSpecificationException;
import mmt.core.exceptions.ImportFileException;
import mmt.core.exceptions.MissingFileAssociationException;
import mmt.core.exceptions.NoSuchPassengerIdException;
import mmt.core.exceptions.NoSuchServiceIdException;
import mmt.core.exceptions.NoSuchStationNameException;
import mmt.core.exceptions.NoSuchItineraryChoiceException;
import mmt.core.exceptions.NonUniquePassengerNameException;
import pt.tecnico.po.ui.Input;

//FIXME import other classes if necessary

/**
 * Façade for handling persistence and other functions.
 */
public class TicketOffice {

  /**
   * The object doing most of the actual work.
   */
  private TrainCompany _trainCompany;
  private String f;

  public void reset() {
    _trainCompany.getPassengerList().clear();
    //_trainCompany.itineraryList().clear();

  }

  public void save(String fileName) throws IOException {

    ObjectOutputStream outStuff = new ObjectOutputStream(new FileOutputStream(fileName));
    outStuff.writeObject(_trainCompany);
    outStuff.close();
    f = fileName;
  }

  public void load(String filename) throws FileNotFoundException, IOException, ClassNotFoundException {

    ObjectInputStream inStuff = new ObjectInputStream(new FileInputStream(filename));
    _trainCompany = (TrainCompany)inStuff.readObject();

  }

  public void importFile(String datafile) throws ImportFileException {
    _trainCompany.importFile(datafile);
  }


  // -------------------------------------------------------------------------------------------
  // Passenger

  public String showPassengerByID(int id) throws NoSuchPassengerIdException {
      return _trainCompany.getPassengerById(id);
  }

  public LinkedList<String> showAllPassengers(){
      return _trainCompany.getAllPassengers();
  }

  public void changePassengerName(int id, String newName) throws NoSuchPassengerIdException{
    _trainCompany.changePassengerName(id, newName);
  }

  public void registerPassenger(String name){
      _trainCompany.registerPassenger(name);
  }


  //--------------------------------------------------------------------------------------------
  //Services

  public LinkedList<StringBuilder> showAllServices(){
      return _trainCompany.getAllServices();
  }

  public String showServiceByID(int id) throws NoSuchServiceIdException{
      return _trainCompany.getServiceByID(id);
  }

  public LinkedList<String> showServicesArrivingAt(String sName) throws NoSuchStationNameException{
      return _trainCompany.getServiceArrivintAt(sName);
  }

    public LinkedList<String> showServicesDepartingFrom(String sName) throws NoSuchStationNameException{
        return _trainCompany.getServiceDepartingFrom(sName);
    }


}
  //**
  //FIXME complete and implement the itinerary search (and pre-commit store) method
  //*public /*FIXME choose return type */ searchItineraries(int passengerId, String departureStation, String arrivalStation, String departureDate,
  //                                            String departureTime) /*FIXME define thrown exceptions */ {
    //FIXME implement method
  //}
  //FIXME complete and implement the itinerary commit method
  //public /*FIXME choose return type */ commitItinerary(int passengerId, int itineraryNumber) /*FIXME define thrown exceptions */ {
    //FIXME implement method



  //FIXME add other functions if necessary


