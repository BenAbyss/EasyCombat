public class Character {
    protected String name;
    protected int initiative;
    protected boolean conscious = true;

    /**
     * This simply exists for NPCTemplate to have a constructor with no parameters.
     */
    public Character(){}

    /**
     * Sets the name and initiative of the character.
     * @param name - the name to identify the character
     * @param initiative - the initial initiative of the character
     */
    public Character(String name, int initiative) {
        this.name = name;
        this.initiative = initiative;
    }

    /**
     * Inverts the state of conscience of a character. This allows for characters getting picked up.
     */
    public void invertConscience() {
        conscious = !conscious;
    }

    //Basic setters
    public void setName(String name) {this.name = name;}
    public void setInitiative(int initiative) {this.initiative = initiative;}
    //Basic getters
    public String getName(){return name;}
    public int getInitiative(){return initiative;}
    public boolean getConscious(){return conscious;}
}
