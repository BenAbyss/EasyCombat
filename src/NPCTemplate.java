import java.util.Scanner;

public class NPCTemplate extends NPC {
    private int templateUses = 0;

    /**
     * Asks the user to enter all the details of the NPC to be templated.
     */
    public NPCTemplate() {
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter the name of the templated creature:");
        this.name = in.nextLine();
        System.out.println("Please enter the health of the templated creature:");
        this.health = in.nextInt();
        in.nextLine();
        System.out.println("Does the creature have multiattack? (Y/N)");
        multiattack = in.nextLine().substring(0, 1).equalsIgnoreCase("Y");
        System.out.println("Please enter the initiative modifier of the templated creature:");
        dexMod = in.nextInt();
        in.nextLine();
        System.out.println("Please enter the armour class of the templated creature:");
        AC = in.nextInt();
        in.nextLine();
        setAttacks();
    }

    public NPCTemplate(String name, int dexMod, boolean multiattack, int health, int AC) {
        super(name, dexMod, multiattack, health, AC);
        setAttacks();
    }

    /**
     * Creates and returns a new NPC identical to the template, but with a numbered name.
     * @return the new NPC object
     */
    public NPC useTemplate() {
        NPC newNPC = new NPC(name + (templateUses + 1), dexMod, multiattack, health, AC);
        newNPC.setAttacks(getAttacks());
        templateUses++;
        return newNPC;
    }

    //Basic getters
    public int getTemplateUses() {return templateUses;}

    public String toString() {
        String info = name + " has " + health + " health, an initiative modifier of " + dexMod + ", an AC of " + AC +
                "\nand has multiattack set to " + multiattack;
        String[] attackData;
        for (Attack a : attacks) {
            attackData = a.getData();
            info += "\nAttack: to-hit = " + attackData[0] + ", damage = " + attackData[1] + ". " + attackData[2];
        }
        return info;
    }
}
