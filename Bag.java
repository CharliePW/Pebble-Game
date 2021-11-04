import java.util.ArrayList;

public class Bag {

    // attributes
    private ArrayList<Pebble> pebbles = new ArrayList<>();
    private static ArrayList<Bag> blackBags = new ArrayList<>();
    private static ArrayList<Bag> whiteBags = new ArrayList<>();

    private String colour;

    /**
     * Constructor of the bag class
     * @param colour
     */
    public Bag(String colour) {
        this.colour = colour;
    }

    /**
     * Gets the colour of the bag
     * @return colour
     */
    public String getColour() {return colour;}

    /**
     * This method adds a pebble to the bag
     * @param pebble
     */
    public void addPebble(Pebble pebble) {
        pebbles.add(pebble);
    }

    /**
     * This method sets the attribute pebbles
     * @param pebbles
     */
    public void setPebbles(ArrayList<Pebble> pebbles) {this.pebbles = pebbles;}

    /**
     * This method remove a pebble from the bag
     * @param pebble
     */
    public void removePebble(Pebble pebble) {pebbles.remove(pebble);}
    
    /**
     * This method returns an ArrayList of all the pebbles the bag contains
     * @return pebbles
     */
    public ArrayList<Pebble> getPebbles() {return pebbles;}

    /**
     * Gets the arraylist of black bags
     * @return blackBags
     */
    public static ArrayList<Bag> getBlackBags() {return blackBags;}

    /**
     * Gets the arraylist of white bags
     * @return whiteBags
     */
    public static ArrayList<Bag> getWhiteBags() {return whiteBags;}

}