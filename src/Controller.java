import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Controller {
    private static ArrayList<NPCTemplate> templates = new ArrayList<>();
    private static ArrayList<NPC> npcs = new ArrayList<>();
    private static ArrayList<PlayerCharacter> pcs = new ArrayList<>();
    private static ArrayList<Character> initOrder = new ArrayList<>();
    private static Scanner in = new Scanner(System.in);

    /**
     * Creates a specified number of NPCs from a chosen template.
     */
    public static void createMultiple() {
        int counter;
        NPCTemplate nextTemplate;
        int templateUses;

        System.out.println("\nPlease select which template to use: ");
        listTemplates();
        nextTemplate = templates.get(in.nextInt() - 1);
        in.nextLine();
        templateUses = nextTemplate.getTemplateUses();
        System.out.print("\nHow many of these NPCs would you like to create? ");
        counter = in.nextInt() + templateUses;
        in.nextLine();
        for (int x = templateUses; x < counter; x++) {
            npcs.add(nextTemplate.useTemplate());
        }
        System.out.println("\nNPCs created!\n");
    }

    /**
     * Lists and numbers all the templates saved.
     */
    public static void listTemplates() {
        int counter = 1;
        for (NPCTemplate template : templates) {
            System.out.println(counter + ". " + template.getName());
            counter++;
        }
    }

    /**
     * Orders all the initiatives of PCs and NPCs
     */
    public static void orderInitiatives() {
        int pos;
        for (NPC npc : npcs) {
            pos = findPos(0, initOrder.size() - 1, npc);
            initOrder.add(pos, npc);
        }
        for (PlayerCharacter pc : pcs) {
            pos = findPos(0, initOrder.size() - 1, pc);
            initOrder.add(pos, pc);
        }
    }

    /**
     * Recursively finds the position of a chr in hte initiative order list.
     * @param start - the start index of the part of the list it might be in
     * @param end - the end index of the part of the list it might be in
     * @param chr - the character to be inserted
     * @return the position the character should be placed in
     */
    private static int findPos(int start, int end, Character chr) {
        int init = chr.getInitiative();
        int mid = (start+end)/2;
        if (end == -1) {
            return 0;
        } else if (init > initOrder.get(mid).getInitiative()){
            if (start == end) {
                return mid;
            }
            return findPos(start, mid-1, chr);
        } else if (init < initOrder.get(mid).getInitiative()) {
            if (start == end) {
                return mid + 1;
            }
            return findPos(mid+1, end, chr);
        } else {
            return mid + rollOff(initOrder.get(mid), chr);
        }
    }

    /**
     * Produces a roll off between the two characters, returning 0 if chr2 wins, and 1 if chr1 wins.
     * @param chr1 - the character already in the initiative order
     * @param chr2 - the character entering the initiative order
     * @return 0 or 1, stating true or false respectively for if chr2 won
     */
    private static int rollOff(Character chr1, Character chr2) {
        int result, chr1roll, chr2roll;
        Random r = new Random();

        System.out.println("Would you like to roll off for " + chr1.getName() + "(1), "
                + chr2.getName() + "(2), both(3) or neither(4)");
        result = in.nextInt();
        in.nextLine();

        //generates rolls for both chrs, and replaces the selected ones with entered values
        chr1roll = r.nextInt(20) + 1;
        chr2roll = r.nextInt(20) + 1;
        switch (result) {
            case 3:
            case 1:
                System.out.println("Please enter the roll for " + chr1.getName());
                chr1roll = in.nextInt();
                in.nextLine();
                if (result == 1) {
                    break;
                }
            case 2:
                System.out.println("Please enter the roll for " + chr2.getName());
                chr2roll = in.nextInt();
                in.nextLine();
                break;
        }

        //returns the appropriate value comparing the rolls
        if (chr1roll == chr2roll) {
            return rollOff(chr1, chr2);
        } else if (chr1roll > chr2roll) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * Add a new template to the list of templates.
     * @param newTemplate - the new template object
     */
    public static void addTemplate(NPCTemplate newTemplate) {
        templates.add(newTemplate);
    }

    /**
     * Adds one or more templates to the save list.
     */
    public static void setTemplates() {
        boolean loop = true;
        while (loop) {
            templates.add(new NPCTemplate());
            System.out.println("Do you want to add another template? (Y/N)");
            loop = in.next().substring(0,1).equalsIgnoreCase("Y");
            in.nextLine();
        }
    }

    /**
     * Asks the user to enter the initiave for every PC, and sets them accordingly.
     */
    private static void setPlayerInitiatives() {
        for (PlayerCharacter player : pcs) {
            System.out.print("Enter the initiative for " + player.getName() + ": ");
            player.setInitiative(in.nextInt());
            in.nextLine();
        }
    }

    //Basic setters
    public static void setPcs(ArrayList<PlayerCharacter> newPcs) {pcs = newPcs;}
    //Basic getters
    public static ArrayList<Character> getInitOrder() {return initOrder;}
    public static ArrayList<NPCTemplate> getTemplates() {return templates;}
}
