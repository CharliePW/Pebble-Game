import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.io.*;
import java.util.Random;

public class PebbleGame {

    public static class Player {

        private ArrayList<Pebble> pebbles = new ArrayList<>();
        private static ArrayList<Player> players = new ArrayList<>();
        private int id;

        public Player(int id){
            this.id = id;
        }

        public int getID() {return id;}
        public ArrayList<Player> getPlayers() {return players;}
        public void pickPebbble() {}    
        public ArrayList<Pebble> getPebbles() {return pebbles;}
        public void addPebble(Pebble pebble) {pebbles.add(pebble);}
    }

    /**
     * adds a player and stores the player in the arraylist of players
     * @param id
     */
    public static void addPlayer(int id) {
        PebbleGame.Player.players.add(new Player(id));
    }

    /**
     * reads the file and returns an array of the weights
     * @param filename
     * @return weights
     */
    public static String[] addFile(String filename) {

        String line = null;
        BufferedReader bufferedReader = null;
        String[] weights = null;

        try {
            //reading the file
            File file = new File(filename);
            FileReader fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);

            // seperating each value in the file from the ","
            while ((line = bufferedReader.readLine()) != null) {                

                // doing this because in example_file_3.csv it's [1, 2, 3, 4....] not [1,2,3,4....]
                if(filename.equals("example_file_3.csv")) {
                    // store the weights in the array
                    weights = line.split(", ");   
                } else {
                    // store the weigths in the array
                    weights = line.split(",");  
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch(NoSuchElementException e) {
            e.printStackTrace();
        } finally {
            // close the buffered reader
            //bufferedReader.close();
        }

        return weights;
    }

    public static void fillBag(Bag bag, String[] weights) {
        
        // iterate through the array
        for (int i=0; i < weights.length; i++) {
            int weight = Integer.parseInt(weights[i]);
            bag.addPebble(new Pebble(weight));
        }   
    }

    public static void main(String[] args) {

        //create the black bags
        ArrayList<Bag> blackBags = new ArrayList<>();
        blackBags.add(new Bag("black"));
        blackBags.add(new Bag("black"));
        blackBags.add(new Bag("black"));

        // create the white bags
        ArrayList<Bag> whiteBags = new ArrayList<>();
        whiteBags.add(new Bag("white"));
        whiteBags.add(new Bag("white"));
        whiteBags.add(new Bag("white"));

        System.out.println("\nWelcome to the PebbleGame!!");
        System.out.println("You will be asked to enter the number of players.");
        System.out.println("and then for the location of three files in turn containing comma seperated integer values for the pebble weights.");
        System.out.println("The integer values must be strictly positive.");
        System.out.println("The game will then be simulated, and written to files in this directory.\n");

        // input the amount of players
        Scanner input = new Scanner(System.in);  
        System.out.println("Please enter the number of players:");
        int number = input.nextInt();

        // create the number of players as inputted and add them to the list of players
        for(int i=0; i<number; i++) {
            PebbleGame.addPlayer(i+1);
        }

        Scanner sc = new Scanner(System.in);

        for(int i=0; i<blackBags.size(); i++) {

            Bag bag = blackBags.get(i);
            
            // input the file
            System.out.println("Please enter the location of bag number " + i + " to load:");
            String filename = sc.nextLine();

            // store the weights
            String[] weights = PebbleGame.addFile(filename);
            
            // fill the bag with the weigths
            PebbleGame.fillBag(bag, weights);       
        }
        sc.close();
        input.close();
        
        int count = 0;
        for(Player player: Player.players) {
            count ++;

            // choosing from a random bag
            Random random = new Random();
            int randomBagNum = random.nextInt(3);
            Bag blackBag = blackBags.get(randomBagNum);
            Bag whiteBag = whiteBags.get(randomBagNum);

            // check if the bag isn't empty
            if(blackBag.getPebbles().size() > 0) {

                // get 10 random pebbles
                for(int i=0; i<10; i++) {
                    // pick the pebble at random
                    int randomPebbleNum = random.nextInt(blackBag.getPebbles().size());
                    Pebble pebble = blackBag.getPebbles().get(randomPebbleNum);
                    // the player recieves the pebbles
                    player.addPebble(pebble);
                    // the pebble is removed from the black bag
                    blackBag.removePebble(pebble);
                }

            } else {
                // empty a white bag into the black bag.

            }   

            int total_weight = 0;
            System.out.println("Player " + count + ":\n");
            for(Pebble pebble: player.getPebbles()) {
                System.out.println("Pebble weight: " + pebble.getWeight());   
                total_weight += pebble.getWeight();
            }

            System.out.println("\nPlayer " + count + ": Total weight: " + total_weight + "\n");
        }

        int bag_count = 0;
        for(Bag blackBag: blackBags) {
            System.out.println("Bag " + bag_count + ": " + blackBag.getPebbles().size());
            bag_count++;
        }
    }
}