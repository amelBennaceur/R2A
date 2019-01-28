package org.re18.fmfm;

import org.re18.generic.Component;
import org.re18.generic.KnowledgeBase;
import org.re18.generic.Requirement;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class FmFmKnowledgeBase implements KnowledgeBase {
    HashMap<String,Set<Substitute>>  substituteHashMap = new HashMap<>();
    Set<Recipe> allReq = new HashSet<>();


    @Override
    public Set<Set<Component>> getSubstitute(Set<Component> substituee) {
        return null;
    }

    @Override
    public Set<Requirement> getRecommendation(Requirement originalRequirement, Integer k) {
        return null;
    }

    public Set<Recipe> getRecommendation(Set<Ingredient> originalRequirement, Set<Ingredient> available) {
        Set<Recipe> recipes = new HashSet<>();
        for(Recipe rec:allReq){
            if (rec.doable(available)){
                recipes.add(rec);
            }
        }
        return recipes;
    }


    public void addSubstitute(Ingredient i1, Ingredient i2, double similarity) {
        Set<Substitute> subs = substituteHashMap.get(i1.getName());
        if (subs == null){
            subs = new HashSet<>();
            substituteHashMap.put(i1.getName(),subs);
        }
        //TODO: check if the substitute does not already exist
        subs.add(new Substitute(i2,similarity));
        //TODO: Is it a commutative relationship
    }

    public Set<Substitute> getSubstitutes(Ingredient i1) {
        return substituteHashMap.get(i1.getName());
    }

    public void addRequirements(Recipe recipe) {
        allReq.add(recipe);
    }
}
