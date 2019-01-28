package org.re18.fmfm;

import org.re18.generic.Requirement;

import java.util.HashSet;
import java.util.Set;

public class Recipe implements Requirement {
    String name;
    Set<Ingredient> ingredients = new HashSet<>();

    public Recipe(String name){
        this.name = name;
    }
    public Recipe(Set<Ingredient> ingredients){
        this.ingredients = ingredients;
    }

    public void addIngredient(Ingredient b) {
        ingredients.add(b);
    }

    public boolean doable(Set<Ingredient> ingredients){
        boolean bool = false;
        for (Ingredient i:ingredients) {
            for (Ingredient j : this.ingredients) {
                if (j.equals(i)) {
                    bool = true;
                    break;
                }
            }
            if (!bool){
                return false;
            }

        }
        return true;
    }

    public double measureRecipeSimilarity(Recipe recipe){
        double common = 0;
        Set<Ingredient> ing = recipe.ingredients;
        for (Ingredient i:ing) {
            for (Ingredient j : this.ingredients) {
                if (j.equals(i)) {
                    common++;
                    break;
                }
            }
        }
        if (common==0)
            return 0;
        double sim = common/recipe.ingredients.size();
        System.out.println("common = "+common+" size = "+recipe.ingredients.size()+" sim ="+sim);
        return (sim);
    }

    @Override
    public String toString() {
        return name;
    }
}
