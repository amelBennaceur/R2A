package org.re18.fmfm;

public class Substitute {
    public Ingredient sub;
    public double distance;

    public Substitute(Ingredient sub, double distance){
        this.sub = sub;
        this.distance = distance;
    }


    @Override
    public String toString() {
        return "-> "+sub+"["+distance+"]";
    }
}
