package org.re18.fmfm;

import org.re18.generic.Component;
import org.re18.generic.KnowledgeBase;
import org.re18.generic.Requirement;
import org.re18.generic.ThreeTierComposition;

import java.util.HashSet;
import java.util.Set;

public class FmFmThreeTierComposition {


    public static Set<Ingredient> RequirementsDriven(Set<Ingredient> available, Set<Ingredient> requirements) {
        Set<Ingredient> comp=new HashSet<>();
        for (Ingredient i:requirements) {
            Boolean bool = false;
            for (Ingredient j : available) {
                if (j.equals(i)) {
                    comp.add(i);
                    bool = true;
                    break;
                }
            }
            if (!bool){
                return null;
            }

        }
        return  comp;
    }

    public static Set<Ingredient> Alternatives(Set<Ingredient> available, Set<Ingredient> requirements, FmFmKnowledgeBase kb) {
        Set<Ingredient> comp=new HashSet<>();
        double overallSim = 1;
        for (Ingredient i:requirements) {
            Boolean bool = false;
            for (Ingredient j : available) {
                if (j.equals(i)) {
                    comp.add(i);
                    bool = true;
                    break;
                }
            }
            if (!bool){
                Set<Substitute> subs = kb.getSubstitutes(i);
                Substitute pref = null;
                if (subs != null) {
                    for (Substitute s : subs) {
                        for (Ingredient j : available) {
                            if (j.equals(s.sub)) {
                                if (pref != null) {
                                    if (s.distance > pref.distance) {
                                        pref = s;
                                    }
                                } else {
                                    pref = s;
                                    bool = true;
                                }
                            }
                        }
                    }
                }
                if (pref!=null){
                    comp.add(pref.sub);
                    overallSim = overallSim * pref.distance;
                    System.out.println("Overall similarity = "+overallSim);
                }
            }
            if (!bool){
                overallSim = 0;
                return null;
            }

        }
        return  comp;
    }

    public static Set<Option> ResourceDriven(Set<Ingredient> available, Set<Ingredient> requirements, FmFmKnowledgeBase kb) {
        Set<Option> options=new HashSet<>();
        Recipe original = new Recipe(requirements);
        Set<Recipe> recommendations = kb.getRecommendation(requirements,available);
        for(Recipe r:recommendations){
            options.add(new Option(r,r.ingredients,r.measureRecipeSimilarity(original)));
        }
        return  options;
    }

}
