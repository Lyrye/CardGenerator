package middle;

import util.PlaceHoldersUtil;

public class Card {

    private String role;
    private String actionName;
    private String actionDescription;
    private String impactInGame;
    private String toRemember;

    public Card(String actionName,String role, String actionDescription,String toRemember, String impactInGame ){
        this.role=role;
        this.actionDescription=actionDescription;
        this.actionName=actionName;
        this.impactInGame=impactInGame;
        this.toRemember=toRemember;
    }

    public String getRole(){
        return role;
    }
    public String getActionName(){
        return actionName;
    }
    public String getActionDescription(){
        return actionDescription;
    }
    public String getImpactInGame(){
        return impactInGame;
    }
    public String getToRemember(){
        return toRemember;
    }

    public String getText(PlaceHoldersUtil.placeHolderType type) {

        switch (type)
        {
            case ACTION_NAME -> { return this.actionName; }
            case DESCRIPTION -> { return this.actionDescription; }
            case TO_REMEMBRE -> { return this.toRemember; }
            case ROLE -> { return this.role; }
            case IMPACT_IN_GAME -> { return this.impactInGame; }
        }
        return null;
    }
}

