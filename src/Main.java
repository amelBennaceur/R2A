import org.re18.fmfm.*;

import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) {


        Set<Ingredient> requirements=new HashSet<>();
        Set<Ingredient> available=new HashSet<>();

        System.out.println("\nCase 1: Exact matching");
        BuildCase1(available,requirements);
        Set<Ingredient> comp=FmFmThreeTierComposition.RequirementsDriven(available,requirements);
        System.out.println("result = "+comp);


        available=new HashSet<>();
        FmFmKnowledgeBase kb = new FmFmKnowledgeBase();
        System.out.println("\nCase 2a: Simple substitution");
        BuildCase2a(available,requirements,kb);
        comp=FmFmThreeTierComposition.Alternatives(available,requirements,kb);
        System.out.println("result = "+comp);


        available=new HashSet<>();
        kb = new FmFmKnowledgeBase();
        System.out.println("\nCase 2b: substitution with conflict");
        BuildCase2b(available,requirements,kb);
        comp=FmFmThreeTierComposition.Alternatives(available,requirements,kb);
        System.out.println("result = "+comp);


        available=new HashSet<>();
        kb = new FmFmKnowledgeBase();
        System.out.println("\nCase 3a: changing requirements/recipe");
        BuildCase3a(available,requirements,kb);
        comp=FmFmThreeTierComposition.Alternatives(available,requirements,kb);
        System.out.println("result Alternatives = "+comp);
        Set<Option> options=FmFmThreeTierComposition.ResourceDriven(available,requirements,kb);
        System.out.println("result options = "+options);


        available=new HashSet<>();
        kb = new FmFmKnowledgeBase();
        System.out.println("\nCase 3b: changing requirements and");
        BuildCase3b(available,requirements,kb);
        comp=FmFmThreeTierComposition.Alternatives(available,requirements,kb);
        System.out.println("result Alternatives = "+comp);
        options=FmFmThreeTierComposition.ResourceDriven(available,requirements,kb);
        System.out.println("result options = "+options);

    }

    private static void BuildCase1(Set<Ingredient> available, Set<Ingredient> requirements) {
        requirements.add(new Ingredient("Chocolate"));
        requirements.add(new Ingredient("BrownSugar"));
        requirements.add(new Ingredient("AlmondFlour"));


        available.add(new Ingredient("Chocolate"));
        available.add(new Ingredient("BrownSugar"));
        available.add(new Ingredient("AlmondFlour"));
    }

    private static void BuildCase2a(Set<Ingredient> available, Set<Ingredient> requirements,FmFmKnowledgeBase kb) {
        available.add(new Ingredient("Chocolate"));
        available.add(new Ingredient("BrownSugar"));
        available.add(new Ingredient("HazelnutFlour"));
        available.add(new Ingredient("CornFlour"));

        kb.addSubstitute(new Ingredient("AlmondFlour"),new Ingredient("HazelnutFlour"),0.8);
        kb.addSubstitute(new Ingredient("AlmondFlour"),new Ingredient("CornFlour"),0.4);
    }

    private static void BuildCase2b(Set<Ingredient> available, Set<Ingredient> requirements,FmFmKnowledgeBase kb) {
        available.add(new Ingredient("Cocoa"));
        available.add(new Ingredient("BrownSugar"));
        available.add(new Ingredient("HazelnutFlour"));
        available.add(new Ingredient("CornFlour"));


        kb.addSubstitute(new Ingredient("AlmondFlour"),new Ingredient("HazelnutFlour"),0.8);
        kb.addSubstitute(new Ingredient("AlmondFlour"),new Ingredient("CornFlour"),0.4);
        kb.addSubstitute(new Ingredient("Chocolate"),new Ingredient("Cocoa"),0.6);
        kb.addSubstitute(new Ingredient("Cocoa"),new Ingredient("HazelnutFlour"),0);
    }

    private static void BuildCase3a(Set<Ingredient> available, Set<Ingredient> requirements,FmFmKnowledgeBase kb) {
        available.add(new Ingredient("BrownSugar"));
        available.add(new Ingredient("HazelnutFlour"));
        available.add(new Ingredient("CornFlour"));

        kb.addSubstitute(new Ingredient("AlmondFlour"),new Ingredient("HazelnutFlour"),0.8);
        kb.addSubstitute(new Ingredient("AlmondFlour"),new Ingredient("CornFlour"),0.4);
        kb.addSubstitute(new Ingredient("Chocolate"),new Ingredient("Cocoa"),0.6);
        kb.addSubstitute(new Ingredient("Cocoa"),new Ingredient("HazelnutFlour"),0);

        Recipe recipe = new Recipe("Brondie");
        recipe.addIngredient(new Ingredient("BrownSugar"));
        recipe.addIngredient(new Ingredient("AlmondFlour"));

        kb.addRequirements(recipe);

        recipe = new Recipe("Orange Cake");
        recipe.addIngredient(new Ingredient("BrownSugar"));
        recipe.addIngredient(new Ingredient("Orange"));
        recipe.addIngredient(new Ingredient("Butter"));

        kb.addRequirements(recipe);
    }

    private static void BuildCase3b(Set<Ingredient> available, Set<Ingredient> requirements,FmFmKnowledgeBase kb) {
        available.add(new Ingredient("BrownSugar"));
        available.add(new Ingredient("HazelnutFlour"));
        available.add(new Ingredient("CornFlour"));

        kb.addSubstitute(new Ingredient("AlmondFlour"),new Ingredient("HazelnutFlour"),0.8);
        kb.addSubstitute(new Ingredient("AlmondFlour"),new Ingredient("CornFlour"),0.4);
        kb.addSubstitute(new Ingredient("Chocolate"),new Ingredient("Cocoa"),0.6);
        kb.addSubstitute(new Ingredient("Cocoa"),new Ingredient("HazelnutFlour"),0);

        Recipe recipe = new Recipe("Brondie");
        recipe.addIngredient(new Ingredient("BrownSugar"));
        recipe.addIngredient(new Ingredient("HazelnutFlour"));

        kb.addRequirements(recipe);

        recipe = new Recipe("Orange Cake");
        recipe.addIngredient(new Ingredient("BrownSugar"));
        recipe.addIngredient(new Ingredient("Orange"));
        recipe.addIngredient(new Ingredient("Butter"));

        kb.addRequirements(recipe);
    }


}
