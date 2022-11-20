package Entities;

public class Barbarian {
    public String name;
    public long lands;
    
    public Barbarian(String name, long lands) {
        this.name = name;
        this.lands = lands;
    }
    
    public String getName() {
        return name;
    }
    public long getLands() {
        return lands;
    }
}
