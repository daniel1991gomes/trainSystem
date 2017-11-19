package mmt.core;

import java.time.LocalTime;

import static java.time.temporal.ChronoUnit.MINUTES;

public class Segment {

    private double _baseCost; // cost of the service
    private double _segmentCost; //cost of the segment = cost of service * (time of segment / total time)
    private Service _service; //
    private TrainStop _origin;
    private TrainStop _destination;


    public Segment(Service service, TrainStop origin, TrainStop destination) {
        _service = service;
        _origin = origin;
        _destination = destination;
        _baseCost = _service.getTotalCost();
        _segmentCost = _baseCost * ((_origin.getDepartureTime().until(_destination.getDepartureTime(), MINUTES)) /
                _service.getOrigin().getDepartureTime().until(_service.getDestination().getDepartureTime(), MINUTES));
    }

    public double get_segmentCost() {
        return _segmentCost;
    }
}
