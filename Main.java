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

        ArrayList<Block> blocks = new ArrayList();
        try {
            bReader = new BufferedReader(new FileReader(blockFilePath));
            bReader.readLine();
            while ((row = bReader.readLine()) != null) {
                // use comma as separator
                String[] block_input = row.split(cvsSplitBy);
                blocks.add(new Block(
                    block_input[0], Integer.parseInt(block_input[1]), Integer.parseInt(block_input[2]), 
                    block_input[3], Integer.parseInt(block_input[4]), block_input[5],
                    Integer.parseInt(block_input[6]), Integer.parseInt(block_input[7]))
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
        ArrayList<Transaction> transactions = new ArrayList();
        try {
            bReader = new BufferedReader(new FileReader(transFilePath));
            bReader.readLine();
            while ((row = bReader.readLine()) != null) {
                String[] trans_input = row.split(cvsSplitBy);
                if(trans_input.length < 9){
                    continue;
                }
                transactions.add(new Transaction(
                    trans_input[0], Integer.parseInt(trans_input[1]), trans_input[2], 
                    trans_input[3], trans_input[4], (long)Double.parseDouble(trans_input[5]),
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

        // QUESTION 1
        long startTime = System.nanoTime();
        Collections.sort(blocks);
        long endTime = System.nanoTime();

        //System.out.printf("%-66s %-7s %5s %-7s %-10s %-42s %-9s %-8s\n", "Block Hash", "Block #", "Size", "Time Stamp", "Difficulty", "Miner", "Gas Limit", "Gas Used");
        for (int i = 0; i < 100; i++) {
            //blocks.get(i).printBlock();
        }
        System.out.println("Execution time: " + (endTime - startTime)/1000000);

        // QUESTION 2
        startTime = System.nanoTime();
        HashMap<Integer, Block> blockTable = new HashMap<Integer, Block>();
        for(Block b : blocks){
            blockTable.put(b.getBlockNumber(), b);
        }

        for(Transaction t : transactions){
            blockTable.get(t.getBlockNumber()).increseTransactionCount();
            blockTable.get(t.getBlockNumber()).getTransactionList().add(t);
        }

        Collections.sort(blocks, new Comparator<Block>(){
            public int compare(Block b1, Block b2){
                int comp1 = b1.getNumberOfTransactions() - b2.getNumberOfTransactions();
                if(comp1 != 0){
                    return comp1;
                }
                int comp2 = b1.getBlockNumber() - b2.getBlockNumber();
                return comp2;
            }
        });
        endTime = System.nanoTime();

        //System.out.printf("%-66s %-7s %5s %-7s %-10s %-42s %-9s %-8s\n", "Block Hash", "Block #", "Size", "Time Stamp", "Difficulty", "Miner", "Gas Limit", "Gas Used");
        for (int i = 0; i < 100; i++) {
            //System.out.print(blocks.get(i).getNumberOfTransactions() + " ");
            //blocks.get(i).printBlock();           
        }
        System.out.println("Execution time: " + (endTime - startTime)/1000000);

        // QUESTION 3
        Collections.sort(transactions, new Comparator<Transaction>() {
            public int compare(Transaction t1, Transaction t2){
                return Long.compare(t1.getGasPrice(),t2.getGasPrice());
            }
        });

        System.out.printf("%-66s %-7s %-42s %42s %-66s %-15s %-66s %-3s %-15s\n", "Block Hash", "Block #", "From", "To", "Contract ID", "Gas Price", "Txn Hash", "Index" ,"Token");
        // for (int i = 0; i < 100; i++) {
        //     transactions.get(i).printTransaction();           
        // }


        // QUESTION 4
        System.out.printf("%-66s %-7s %-42s %42s %-66s %-15s %-66s %-3s %-15s\n", "Block Hash", "Block #", "From", "To", "Contract ID", "Gas Price", "Txn Hash", "Index" ,"Token");
        Collections.sort(transactions, new Comparator<Transaction>() {
            public int compare(Transaction t1, Transaction t2){
                // Sort by block number
                int comp1 = t1.getBlockNumber() - t2.getBlockNumber();
                if(comp1 != 0){
                    return comp1;
                }
                // Sort by gas price
                return Long.compare(t1.getGasPrice(),t2.getGasPrice());
            }
        });

        // for (int i = 0; i < 100; i++) {
        //     transactions.get(i).printTransaction();           
        // }

        // QUESTION 5
        Collections.sort(transactions, new Comparator<Transaction>() {
            public int compare(Transaction t1, Transaction t2){
                // Sort by contract address
                int comp1 = t1.getContractID().compareTo(t2.getContractID());
                if(comp1 != 0){
                    return comp1;
                }

                // Sort by block number
                return t1.getBlockNumber() - t2.getBlockNumber();
            }                        
        });

        System.out.printf("%-66s %-7s %-42s %42s %-66s %-15s %-66s %-3s %-15s\n", "Block Hash", "Block #", "From", "To", "Contract ID", "Gas Price", "Txn Hash", "Index" ,"Token");
        for (int i = 0; i < 100; i++) {
            // transactions.get(i).printTransaction();
            // System.out.println(transactions.get(i).getContractID() + " | " + transactions.get(i).getBlockNumber());           
        }

        // QUESTION 6
        int searchBlockNumber = 3110014;
        ArrayList<Transaction> listOfTrans = blockTable.get(searchBlockNumber).getTransactionList();
        for(Transaction t : listOfTrans){
            t.printTransaction();
        }

        // QUESTION 7
    }
}