import java.io.*;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        
        //String csvFilePath = "C:\\Users\\Code2care\\Desktop\\file\\data.csv";
        String line = null;
        BufferedReader bufferedReader = null;

        try {
            System.out.println("Enter the filename:\n");
            Scanner sc = new Scanner(System.in);
            String fileName = sc.nextLine();
            sc.close();
            File csvFile = new File(fileName);
            FileReader fileReader = new FileReader(csvFile);
            bufferedReader = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {

                String[] csvLineElements = line.split(",");
                
                for (int i = 0; i < csvLineElements.length; i++) {
                    System.out.println(csvLineElements[i]);
                }
            }
        }

        catch (IOException e) {
            System.out.println("Error Occured while parsing csv file.");
            e.printStackTrace();
        }
    }
}
