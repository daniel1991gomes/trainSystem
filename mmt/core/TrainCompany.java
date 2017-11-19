package mmt.core;

import mmt.core.exceptions.*;

import java.util.LinkedList;

//FIXME import other classes if necessary

/**
 * A train company has schedules (services) for its trains and passengers that
 * acquire itineraries based on those schedules.
 */
public class TrainCompany implements java.io.Serializable {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 201708301010L;
  private int _nextPassengerID;
  private LinkedList<Passenger> _passengerList = new LinkedList<>();
  private LinkedList<Station> _stationList = new LinkedList<>();
  private LinkedList<Service> _serviceList = new LinkedList<>();

  //FIXME define fields


  private NewParser _parser = new NewParser();

  LinkedList<Station> getStationList() {
    return _stationList;
  }

  LinkedList<Passenger> getPassengerList() {
    return _passengerList;
  }

  LinkedList<Service> getServiceList() {
    return _serviceList;
  }

  void importFile(String filename) throws ImportFileException {
    _parser.parseFile(filename);
  }

//-----------------------------------------------------------------------
// Passenger info


  void registerPassenger(String pName){
    Passenger p = new Passenger(pName, _nextPassengerID++);
    _passengerList.add(p);

  }

  void changePassengerName(int id, String newName){
    for (Passenger p: _passengerList){
         if(p.getID() == id){
           p.changeName(newName);
           break;
         }
    }
  }

  String getPassengerById(int id) throws NoSuchPassengerIdException{
    try {
        return _passengerList.get(id).getPName() ;
    }catch (IndexOutOfBoundsException oob){
        throw new NoSuchPassengerIdException(id);
    }
  }

  LinkedList<String> getAllPassengers(){
      LinkedList<String> s = new LinkedList<>();
      for (Passenger p: _passengerList) {
          s.add(p.getPName());
      }
      return  s;
  }

 //----------------------------------------------------------------------
 // Service info

    LinkedList<StringBuilder> getAllServices(){
      LinkedList<StringBuilder> s = new LinkedList<>();
      for (Service serv: _serviceList){
          s.add(serv.printServices());
      }
      return s;
    }

    String getServiceByID(int id) throws NoSuchServiceIdException{
        if(id < _serviceList.size()){
            return _serviceList.get(id).printServices().toString();
        }else {
            throw new NoSuchServiceIdException(id);
        }
    }

    LinkedList<String> getServiceArrivintAt(String sName) throws NoSuchStationNameException{
        LinkedList<String> sList = new LinkedList<>();
        for(Service s: _serviceList){
            if(sName.equals(s.getDestination().toString())){
                sList.add(s.printServices().toString());
            }
        }
        return  sList;
    }

    LinkedList<String> getServiceDepartingFrom(String sName) throws NoSuchStationNameException{
        LinkedList<String> sList = new LinkedList<>();
        for(Service s: _serviceList){
            if(sName.equals(s.getOrigin().toString())){
                sList.add(s.printServices().toString());
            }
        }
        return  sList;
    }



  /*FIXME
   * add methods for
   *   registerPassenger, changePassengerName
   *   searchItineraries, commitItinerary
   */

  //FIXME implement other functions if necessary

}
