public class PlayerCharacter extends Character{
    private String playerName;

    /**
     * Sets the basic details of the player character.
     * @param name - the name of the character
     * @param playerName - the name of the player
     * @param initiative - the initiave value of the character
     */
    public PlayerCharacter(String name, String playerName, int initiative) {
        super(name, initiative);
        this.playerName = playerName;
    }

    //Basic getters
    public String getPlayerName(){return playerName;}
}
