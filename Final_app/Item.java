/**
 * Write a description of class Item here.
 * 
 * @author Richard Shepard
 * @version 3.5.2015
 */
public class Item
{
    private double weight;
    private String description;

    /**
     * Constructor for objects of class Item
     */
    public Item(String description, double weight){
        this.weight = weight;
        this.description = description;
    }

    public double getWeight() {
        return weight;
    }

    public String getDescription() {
        return description;
    }

    public String toString() {
        return description + " Weight: " + weight;
    }
}
