import java.util.HashMap;
import java.util.Set;
import java.util.ArrayList;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */
public class Room 
{
    private String description;
    private HashMap<String, Room> exits;
    private ArrayList<Item> items;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<String, Room>();
        items = new ArrayList<Item>();
    }

    /**
     * Define the exits of this room.  Every direction either leads
     * to another room or is null (no exit there).
     * @param north The north exit.
     * @param east The east east.
     * @param south The south exit.
     * @param west The west exit.
     */
    public void setExits(String direction, Room exit) 
    {
        exits.put(direction, exit);
    }

    public void setItems(String description, double weight) {
        items.add(new Item(description, weight));
    }
    
    public void setItems(Item item) {
        items.add(item);
    }

    public Room getExit(String direction) {
        return exits.get(direction);
    }

    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }

    public String getExitString() {
        String exitString = "Exits: ";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            exitString += " " + exit;
        }
           return exitString;
    }

    public String getLongDescription() {
        String roomDescription = "Your are " + description + ".\n" + getExitString();
        roomDescription += "\n" + getItems();
        return roomDescription;
    }

    public String getItems() {
        String itemString = "Items: ";
        for(Item item : items) {
            itemString += "\n" + item.toString();
        }
        return itemString;
    }

    public Item pickUpItem(String itemID) {
        Item pickedUp = null;
        boolean cookieFound = false;
        for(Item item : items) {
            if(itemID.equals(item.getDescription())) {
                pickedUp = item;
                if(itemID.equals("Rock")) {
                    cookieFound = true;
                }
            }
        }
        if(cookieFound) {
            this.setItems("Cookie", 1.5);
        }
        items.remove(pickedUp);
        return pickedUp;
    }

}
