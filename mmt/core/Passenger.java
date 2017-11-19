package mmt.core;

import java.util.Date;
import java.util.LinkedList;

public class Passenger {

    private String _name;
    private int _id;
    private Type _category = new Type();
    private double _discount;
    private LinkedList<Double> lastTen = new LinkedList<>();

    public Passenger(String name, int id) {
        _name = name;
        _id = id;
    }

    public String getPName() {
        return _name;
    }

    public void changeName(String newName){
        _name = newName;
    }

    public int getID() {
        return _id;
    }

    public double getDiscount(){
        return _discount;
    }

    public void boughtTicket(double cost){
        if (lastTen.size() > 9){
            lastTen.remove(0);
        }
        lastTen.add(cost);
        double value = 0;
        for (Double object:lastTen) {
            value += object;
        }
        _discount = _category.setType(value);

    }

}


