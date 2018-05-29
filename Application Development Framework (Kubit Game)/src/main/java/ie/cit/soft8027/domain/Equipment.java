package ie.cit.soft8027.domain;

public class Equipment {


    private int id;
    private String name;
    private int damage;
    private int protection;
    private int level;
    private int price;
    private String type;


    public Equipment(int id, String name, int damage, int protection, int level, int price, String type) {
        this.id = id;
        this.name = name;
        this.damage = damage;
        this.protection = protection;
        this.level = level;
        this.price = price;
        this.type = type;
    }


    public Equipment() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getProtection() {
        return protection;
    }

    public void setProtection(int protection) {
        this.protection = protection;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return name + "-Lvl:" + level + " DMG:" + damage + " Protection:" + protection + " Price:" + price + " Type:" + type;
    }
}