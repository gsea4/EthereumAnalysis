import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.*; 
import java.util.*; 

public class Main{
    public static void main(String[] args){
        String blockFilePath = "blocks (10172018).csv";
        String transFilePath = "transactions (10172018).csv";
        BufferedReader bReader = null;
        String row = "";
        String cvsSplitBy = ",";

        // ArrayList<Block> blocks = new ArrayList();
        // try {
        //     bReader = new BufferedReader(new FileReader(blockFilePath));
        //     bReader.readLine();
        //     while ((row = bReader.readLine()) != null) {
        //         // use comma as separator
        //         String[] block_input = row.split(cvsSplitBy);
        //         blocks.add(new Block(
        //             block_input[0], Integer.parseInt(block_input[1]), Integer.parseInt(block_input[2]), 
        //             block_input[3], Integer.parseInt(block_input[4]), block_input[5],
        //             Integer.parseInt(block_input[6]), Integer.parseInt(block_input[7]))
        //         );
        //     }
        // } catch (FileNotFoundException e) {
        //     e.printStackTrace();
        // } catch (IOException e) {
        //     e.printStackTrace();
        // } finally {
        //     if (bReader != null) {
        //         try {
        //             bReader.close();
        //         } catch (IOException e) {
        //             //bReader.printStackTrace();
        //         }
        //     }
        // }
        int count = 0;
        ArrayList<Transaction> transactions = new ArrayList();
        try {
            bReader = new BufferedReader(new FileReader(transFilePath));
            bReader.readLine();
            while ((row = bReader.readLine()) != null) {
                String[] trans_input = row.split(cvsSplitBy);
                System.out.println(count);
                count++;
                transactions.add(new Transaction(
                    trans_input[0], Integer.parseInt(trans_input[1]), trans_input[2], 
                    trans_input[3], trans_input[4], (long)Double.parseDouble(trans_input[7]),
                    trans_input[6], Integer.parseInt(trans_input[7]), (long)Double.parseDouble(trans_input[8]))
                );
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bReader != null) {
                try {
                    bReader.close();
                } catch (IOException e) {
                    //bReader.printStackTrace();
                }
            }
        }

        String t = "1.00E+11";
        System.out.println((long)Double.parseDouble(t));

        long startTime = System.nanoTime();
        //Collections.sort(blocks);
        long endTime = System.nanoTime();

        System.out.printf("%-66s %-7s %5s %-7s %-10s %-42s %-9s %-8s\n", "Block Hash", "Block #", "Size", "Time Stamp", "Difficulty", "Miner", "Gas Limit", "Gas Used");
        // for (Block b : blocks) {
        //     b.printBlock();
        // }
        System.out.println((endTime - startTime)/1000000);
    }
}