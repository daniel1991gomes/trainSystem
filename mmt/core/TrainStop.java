package mmt.core;


import java.time.LocalTime;


public class TrainStop {

    //departureTime of this Train Stop
    private LocalTime _departureTime;

    //Station that this Train Stop is attached to
    private Station _station;



    TrainStop(LocalTime departureTime, Station s){
        _departureTime = departureTime;
        _station = s;
    }


    LocalTime getDepartureTime() {
        return _departureTime;
    }

    public String getStation(){
        return _station.getStationName();
    }

    String printStation(){
        return _departureTime.toString() + " " + _station.toString() + "\n";
    }



}
