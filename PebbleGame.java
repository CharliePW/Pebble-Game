import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.io.*;
import java.util.Random;

public class PebbleGame {
    public static void main(String[] args) {

        //create the black bags
        ArrayList<BlackBag> blackBags = new ArrayList<>();
        blackBags.add(new BlackBag());
        blackBags.add(new BlackBag());
        blackBags.add(new BlackBag());

        // create the white bags
        ArrayList<WhiteBag> whiteBags = new ArrayList<>();
        whiteBags.add(new WhiteBag());
        whiteBags.add(new WhiteBag());
        whiteBags.add(new WhiteBag());

        // the players will be added to this array after enterring how many players there will be
        ArrayList<Player> players = new ArrayList<>();

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
            players.add(new Player());
        }

        Scanner sc = new Scanner(System.in);

        for(int i=0; i<blackBags.size(); i++) {

            BlackBag bag = blackBags.get(i);
            String line = null;
            BufferedReader bufferedReader = null;
            

            try {
                //input the name of the file.
                System.out.println("Please enter the location of bag number " + i + " to load:");
                String fileName = sc.nextLine();
        
                //String filePath = "/Users/charliewright/Documents/Uni of Exeter/Year 2/Software Development/CA1/src/Example input files/";
                //filePath += fileName;

                //reading the file
                File file = new File(fileName);
                FileReader fileReader = new FileReader(file);
                bufferedReader = new BufferedReader(fileReader);

                // seperating each value in the file from the ","
                while ((line = bufferedReader.readLine()) != null) {

                    //put all the values as string in an array 
                    String[] csvLineElements;

                    // doing this because in example_file_3.csv it's [1, 2, 3, 4....] not [1,2,3,4....]
                    if(fileName.equals("example_file_3.csv")) {
                        csvLineElements = line.split(", ");   
                    } else {
                        csvLineElements = line.split(",");  
                    }
                    
                    // iterate through the array
                    for (int j=0; j < csvLineElements.length; j++) {
                        int weight = Integer.parseInt(csvLineElements[j]);
                        bag.addPebble(new Pebble(weight));
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
        }
        sc.close();
        input.close();
        
        int count = 0;
        for(Player player: players) {
            count ++;

            // choosing from a random bag
            Random random = new Random();
            int randomBagNum = random.nextInt(3);
            BlackBag blackBag = blackBags.get(randomBagNum);
            WhiteBag whiteBag = whiteBags.get(randomBagNum);

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
        for(BlackBag blackBag: blackBags) {
            System.out.println("Bag " + bag_count + ": " + blackBag.getPebbles().size());
            bag_count++;
        }
    }
}