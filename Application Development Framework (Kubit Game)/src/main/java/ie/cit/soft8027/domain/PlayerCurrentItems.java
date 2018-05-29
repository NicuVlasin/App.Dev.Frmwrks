package ie.cit.soft8027.domain;

public class PlayerCurrentItems {
    private int id;
    private int playerId;
    private int melee;
    private int rangeW;
    private int shield;
    private int armour;

    public PlayerCurrentItems(int id, int playerId, int melee, int rangeW, int shield, int armour) {
        this.id = id;
        this.playerId = playerId;
        this.melee = melee;
        this.rangeW = rangeW;
        this.shield = shield;
        this.armour = armour;
    }

    public PlayerCurrentItems() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public int getMelee() {
        return melee;
    }

    public void setMelee(int melee) {
        this.melee = melee;
    }

    public int getRangeW() {
        return rangeW;
    }

    public void setRangeW(int rangeW) {
        this.rangeW = rangeW;
    }

    public int getShield() {
        return shield;
    }

    public void setShield(int shield) {
        this.shield = shield;
    }

    public int getArmour() {
        return armour;
    }

    public void setArmour(int armour) {
        this.armour = armour;
    }

    @Override
    public String toString() {
        return "PlayerCurrentItems{" +
                "id=" + id +
                ", playerId=" + playerId +
                ", melee=" + melee +
                ", rangeW=" + rangeW +
                ", shield=" + shield +
                ", armour=" + armour +
                '}';
    }
}