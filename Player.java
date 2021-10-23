import java.util.ArrayList;

public class Player {
    
    private ArrayList<Pebble> pebbles = new ArrayList<>();

    public Player(){}

    public void pickPebbble() {}    

    public ArrayList<Pebble> getPebbles() {
        return pebbles;
    }

    public void addPebble(Pebble pebble) {
        pebbles.add(pebble);
    }
    
}
