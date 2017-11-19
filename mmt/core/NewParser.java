package mmt.core;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.time.LocalTime;
import java.time.LocalDate;
import java.util.LinkedList;

import mmt.core.exceptions.ImportFileException;


public class NewParser {


    private TrainCompany _trainCompany = new TrainCompany();

    public void parseFile(String fileName) throws ImportFileException {

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;

            while ((line = reader.readLine()) != null) {
                parseLine(line);
            }
        } catch (IOException ioe) {
            throw new ImportFileException(ioe);
        }

    }

    private void parseLine(String line) throws ImportFileException {
        String[] components = line.split("\\|");

        switch (components[0]) {
            case "PASSENGER":
                parsePassenger(components);
                break;

            case "SERVICE":
                parseService(components);
                break;

            case "ITINERARY":
                parseItinerary(components);
                break;

            default:
                throw new ImportFileException("invalid type of line: " + components[0]);
        }
    }

    private void parsePassenger(String[] components) throws ImportFileException {
        if (components.length != 2)
            throw new ImportFileException("invalid number of arguments in passenger line: " + components.length);

        String passengerName = components[1];
        _trainCompany.registerPassenger(passengerName);

    }

    private void parseService(String[] components) {
        double cost = Double.parseDouble(components[2]);
        int serviceId = Integer.parseInt(components[1]);
        LinkedList <TrainStop> sList = new LinkedList<>();

        Service s = new Service(serviceId, cost, sList);

        for (int i = 3; i < components.length; i += 2) {
            String time = components[i];
            String stationName = components[i + 1];
            LocalTime ltime = LocalTime.parse(time);

            boolean stationIsNew = true;
            int counter=0;
            for (int c = 0 ; c < _trainCompany.getStationList().size(); c++){
                if(_trainCompany.getStationList().get(c).getStationName().equals(stationName)){
                    stationIsNew = false;
                    counter = c;
                    break;
                }
            }
            if (stationIsNew){
                TrainStop t = new TrainStop(ltime, _trainCompany.getStationList().get(counter));
                s.getTrainStopList().add(t);

            }else{
                Station st = new Station(stationName);
                TrainStop t = new TrainStop(ltime,st);
                s.getTrainStopList().add(t);

            }
        }
        _trainCompany.getServiceList().add(s);

    }

    private void parseItinerary(String[] components) throws ImportFileException{
        if (components.length < 4)
            throw new ImportFileException("Invalid number of elements in itinerary line: " + components.length);

        int passengerId = Integer.parseInt(components[1]);
        LocalDate date = LocalDate.parse(components[2]);

        // criar um itinerário com data indicada

        for (int i = 3; i < components.length; i++) {
            String segmentDescription[] = components[i].split("/");

            int serviceId = Integer.parseInt(segmentDescription[0]);
            String departureTrainStop = segmentDescription[1];
            String arrivalTrainStop = segmentDescription[2];

            // criar segmento com paragem em departureTrainStop e arrivalTrainStop
            // adicionar segmento ao itinerario
        }

        // adicionar o itinerário ao passageiro
    }
}