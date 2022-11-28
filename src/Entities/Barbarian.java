package Entities;

public class Barbarian {
    private String name;
    private final float lands;
    private int generation;
    private float heritage;
    
    public Barbarian(String name, float lands) {
        this.name = name;
        this.lands = lands;
    }

    public void setGeneration(int generation){
        this.generation = generation;
    }

    public int getGeneration(){
        return this.generation;
    }
    
    public String getName() {
        return name;
    }
    public float getLands() {
        return lands;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getHeritage() {
        return heritage + lands;
    }

    public void setHeritage(float heritage) {
        this.heritage = heritage;
    }
    
}
