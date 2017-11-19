package mmt.core;

import java.util.*;

public class Service {

    private int _id;
    private double _totalCost;
    private LinkedList<TrainStop> _trainStopList;

    //private TrainStop _origin;
    //private TrainStop _destination;


    // Gets this information from file
    public Service(int id, double totalCost, LinkedList<TrainStop> tsList) {
        _id = id;
        _totalCost = totalCost;
        _trainStopList = tsList;
    }

    double getTotalCost() {
        return _totalCost;
    }

    int get_id() {
        return _id;
    }

    TrainStop getOrigin() {
        return _trainStopList.get(0);
    }

    TrainStop getDestination() {
        return _trainStopList.get(_trainStopList.size()-1);
    }

    LinkedList<TrainStop> getTrainStopList() {
        return _trainStopList;
    }

    StringBuilder printServices() {
        StringBuilder s = new StringBuilder("Serviço #" + _id + " @ " + String.format(Locale.US, "%.2f", _totalCost) + "\n");
        for (TrainStop ts : _trainStopList) {
            s.append(ts.printStation());
        }
        return s;
    }



}
