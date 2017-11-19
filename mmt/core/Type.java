package mmt.core;

public class  Type {


    Special s = new Special();
    Frequent f = new Frequent();
    Normal n = new Normal();

    public double setType(double value) {
        if (value > 2500) {
            return s.getDiscountValue();
        } else if (value > 250) {
            return f.getDiscountValue();
        } else {
            return n.getDiscountValue();
        }
    }

}

