import java.util.ArrayList;

public class Bag {

    private ArrayList<Pebble> pebbles = new ArrayList<>();

    public Bag() {}

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
    public void setPebbles(ArrayList<Pebble> pebbles) {
        this.pebbles = pebbles;
    }

    /**
     * This method remove a pebble from the bag
     * @param pebble
     */
    public void removePebble(Pebble pebble) {
        pebbles.remove(pebble);
    }

    /**
     * This method returns an ArrayList of all the pebbles the bag contains
     * @return pebbles
     */
    public ArrayList<Pebble> getPebbles() {
        return pebbles;
    }
}