import java.util.ArrayList;
import java.util.Stack;
/**
 * Write a description of class Player here.
 * 
 * @author Richard Shepard
 * @version 3.5.2015
 */
public class Player
{
    private String name;
    private ArrayList<Item> items;
    private Room currentRoom;
    private double maxWeight;
    private int maxItems;
    private Stack<Room> rooms;
    private int moves;

    public Player(String name) {
        this.name = name;
        items = new ArrayList<Item>();
        rooms = new Stack<Room>();
        maxWeight = 50;
        maxItems = 5;
        moves = 10;
    }

    public String getName() {
        return name;
    }

    public String getItems() {
        String itemString;
        if(!items.isEmpty()){
            itemString = "Items (" + items.size() + "):";
            for(Item item : items) {
                itemString += "\n" + item.toString();
            }
        } else {
            itemString = "You currently have no items";
        }
        return itemString;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void addItem(Item item) {
        if(items.size() > maxItems) {
           System.out.println("You are already carrying too much!!!!");
        } else if(totalWeight() > maxWeight || item.getWeight() > maxWeight){
           System.out.println("OVERWEIGHT!!!!");
        } else {
           System.out.println("You picked up " + item.getDescription());
           items.add(item);
        }
    }

    private double totalWeight() {
        double totalWeight = 0;
        for(Item item : items) {
            totalWeight += item.getWeight();
        }
        return totalWeight;
    }

    public void setCurrentRoom(Room room) {
        currentRoom = room;
        
    }

    public void pushRoom(Room room) {
        rooms.push(room);
    }

    public Room popRoom() {
        return rooms.pop();
    }

    public boolean roomsIsEmpty() {
        return rooms.isEmpty();
    }

    public void back(Command command) {
        if(command.hasSecondWord()) {
            System.out.println("just BACK nothing else");
        }else if(roomsIsEmpty()) {
            System.out.println("Your are at the begining, you can't go back any further");
        } else {
            setCurrentRoom(popRoom());
            addMove();
            printLocationInfo();
        }
    }

    public void swim() {
        System.out.println("You cannot swim inside fool");
    }
    

    public void look() {
        System.out.println(getCurrentRoom().getLongDescription());
    }

    public void printLocationInfo() {
        System.out.println(getCurrentRoom().getLongDescription());
        System.out.println("You have " + moves + " moves");
    }

    public void pickup(Command command) {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Pickup what?");
            return;
        } else {
            String item = command.getSecondWord();
            addItem(getCurrentRoom().pickUpItem(item));
        }
    }

    public void drop(Command command) {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Drop what?");
            return;
        } else {
            String itemString = command.getSecondWord();
            Item item = findItem(itemString);
            currentRoom.setItems(item);
            System.out.println("You dropped the " + item.getDescription() + " in the " + currentRoom.getDescription());
            items.remove(item);
        }
    }

    public void lookInSatchel() {
        System.out.println("You have...");
        System.out.println(getItems());
    }

    private Item findItem(String itemString) {
        Item pickedUp = null;
        for(Item item : items) {
            if(itemString.equals(item.getDescription())) {
                pickedUp = item;
            }
        }
        
        return pickedUp;
    }

    public void eatCookie(Command command) {
        if(!command.hasSecondWord())  {
            System.out.println("eat what?");
        } else if (command.getSecondWord().equals("Cookie")){
            Item cookie = findItem("Cookie");
                if(cookie.getDescription().equals("Cookie")) {
                    items.remove(cookie);
                    maxWeight += 50;
                    moves = 10;
                    System.out.println("You can now carry 50 more lbs and your moves have been reset. ");
                }
        }
    }

    public int getMoves() {
        return moves;
    }

    public void addMove() {
        moves -= 1;
    }

}
