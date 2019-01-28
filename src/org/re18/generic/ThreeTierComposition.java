package org.re18.generic;

import java.util.Set;

public interface ThreeTierComposition {
    Set<Component> RequirementsDriven(Set<Component> available, Set<Requirement> requirements);

    Set<Component> Alternatives(Set<Component> available, Set<Requirement> requirements, KnowledgeBase kb);

    Set<Component> ResourceDriven(Set<Component> available, Set<Requirement> requirements, KnowledgeBase kb);
}
