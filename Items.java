
/**
 * Write a description of class Items here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Items
{
    public String name;
    public String description;
    public int weight;

    /**
     * Constructor for objects of class Items
     */
    public Items(String name, String description, int weight)
    {
        this.name = name;
        this.description = description;
        this.weight = weight;
    }

    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }

    public int getWeight(){
        return weight;
    }



}
