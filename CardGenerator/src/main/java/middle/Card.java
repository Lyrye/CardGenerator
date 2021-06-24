package middle;

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
    public String getInpactInGame(){
        return impactInGame;
    }
    public String getToRemember(){
        return toRemember;
    }
}

