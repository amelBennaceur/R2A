package org.re18.fmfm;

import org.re18.generic.Component;

public class Ingredient implements Component {

    private String name = null;

    public Ingredient(String name){
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Ingredient))
            return false;
        Ingredient t = (Ingredient) o;
        return this.name == t.name;
    }

    @Override
    public String toString() {
        return name;
    }

    public String getName() {
        return name;
    }
}
