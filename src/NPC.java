import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class NPC extends Character {
    protected static Random r = new Random();
    protected Attack[] attacks;
    protected boolean multiattack;
    protected int health;
    protected int dexMod;
    protected int AC;

    /**
     * This simply exists for NPCTemplate to have a constructor with no parameters.
     */
    public NPC(){}

    /**
     * Creates an NPC and rolls it's initiative.
     * @param name - the name to represent the NPC
     * @param dexMod - the modifier for initiative
     * @param multiattack - if the enemy has multiattack
     * @param health - the initial health of the NPC
     * @param AC - the AC to hit the NPC
     */
    public NPC(String name, int dexMod, boolean multiattack, int health, int AC) {
        super(name, 1);
        this.multiattack = multiattack;
        this.health = health;
        this.dexMod = dexMod;
        this.AC = AC;
        rollInitiative();
    }

    /**
     * Rolls the initiate, adding their modifier to it. Rolling a natural adds or subtracts 100 to represent it.
     */
    public void rollInitiative() {
        int roll = r.nextInt(20) + 1;
        if (roll == 1) {
            roll -= 100;
        } else if (roll == 20) {
            roll += 100;
        }
        initiative = roll + dexMod;
    }

    /**
     * Lets the user set the attack or attacks of the NPC, by entering the data pieces of it.
     */
    public void setAttacks() {
        boolean moreAttacks = true;
        Scanner in = new Scanner(System.in);
        ArrayList<Attack> attacks = new ArrayList<>();

        System.out.println("\nThe next step is to set it's attack(s). Start with its main or first listed attack");
        while (moreAttacks) {
            Attack nextAttack = new Attack();
            System.out.println("What is the to-hit or DC? For the DC, include the appropriate saving throw");
            nextAttack.setToHitOrDC(in.nextLine());
            System.out.println("What is the damage? Include the types");
            nextAttack.setDamage(in.nextLine());
            System.out.println("Write any additional info. If there is none, simply press enter");
            nextAttack.setAdditionalInfo(in.nextLine());

            attacks.add(nextAttack);
            System.out.println("Add another attack? (Y/N)");
            if (!in.nextLine().substring(0,1).equalsIgnoreCase("Y")) {
                moreAttacks = false;
            }
        }
        this.attacks = attacks.toArray(new Attack[0]);
    }

    //Basic setters
    public void setAttacks(Attack[] attacks) {this.attacks = attacks;}
    //Basic getters
    public boolean getMultiattack(){return multiattack;}
    public Attack[] getAttacks(){return attacks;}
    public int getHealth(){return health;}
    public int getDexMod() {return dexMod;}
    public int getAC() {return AC;}
}
