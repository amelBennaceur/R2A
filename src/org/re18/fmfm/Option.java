package org.re18.fmfm;

import java.util.Set;

public class Option {
    public Recipe requirements;
    public Set<Ingredient> ingredients;
    public double similarity;


    public Option(Recipe requirements,Set<Ingredient> ingredients,double similarity){
        this.requirements = requirements;
        this.ingredients = ingredients;
        this.similarity = similarity;
    }

    @Override
    public String toString() {
        return "["+requirements+":"+ingredients+"-"+similarity+"]";
    }
}
