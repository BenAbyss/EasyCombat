public class Attack {
    private String toHitOrDC;
    private String damage;
    private String additionalInfo;
    private String name;

    //Basic setters
    public void setToHitOrDC(String data) {toHitOrDC = data;}
    public void setDamage(String data) {damage = data;}
    public void setAdditionalInfo(String data) {additionalInfo = data;}
    public void setName(String data) {name = data;}
    //Basic getters
    public String getName() {return name;}
    public String[] getData() {
        return new String[]{name, toHitOrDC, damage, additionalInfo};
    }
}
