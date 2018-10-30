import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        String blockFilePath = "blocks (10172018).csv";
        String transFilePath = "transactions (10172018).csv";

        List<Block> blocks = readBlocks(blockFilePath);
        List<Transaction> transactions = readTransactions(transFilePath);

        BufferedWriter fileWriter = null;
        try {
            fileWriter = new BufferedWriter(new FileWriter("my_output.txt"));
            
            // QUESTION 1
            long startTime = System.nanoTime();
            Collections.sort(blocks);
            long endTime = System.nanoTime();

            fileWriter.write("Question 1: List up all the blocks by their gas used in an increasing order\n");
            fileWriter.write(String.format("%-66s %-7s %5s %-7s %-10s %-42s %-9s %-8s\n", "Block Hash", "Block #",
                    "Size", "Time Stamp", "Difficulty", "Miner", "Gas Limit", "Gas Used"));
            for (int i = 0; i < 100; i++) {
                fileWriter.write(blocks.get(i).printBlock());
            }
            fileWriter.write("Execution time: " + (endTime - startTime) / 1000000 + "ms\n\n");           

            // QUESTION 2    
            startTime = System.nanoTime();
            HashMap<Integer, Block> blockTable = new HashMap<Integer, Block>();
            for (Block b : blocks) {
                blockTable.put(b.getBlockNumber(), b);
            }

            for (Transaction t : transactions) {
                blockTable.get(t.getBlockNumber()).increseTransactionCount();
                blockTable.get(t.getBlockNumber()).getTransactionList().add(t);
            }

            Collections.sort(blocks, new Comparator<Block>() {
                public int compare(Block b1, Block b2) {
                    int comp1 = b1.getNumberOfTransactions() - b2.getNumberOfTransactions();
                    if (comp1 != 0) {
                        return comp1;
                    }
                    int comp2 = b1.getBlockNumber() - b2.getBlockNumber();
                    return comp2;
                }
            });
            endTime = System.nanoTime();

            fileWriter.write("\n\nQuestion 2: List up all the blocks by their # transactions in an increasing order\n");
            fileWriter.write(String.format("%-66s %-7s %5s %-7s %-10s %-42s %-9s %-8s %-7s\n", "Block Hash", "Block #",
                    "Size", "Time Stamp", "Difficulty", "Miner", "Gas Limit", "Gas Used", "# Trans"));
            for (int i = 0; i < 100; i++) {
                fileWriter.write(blocks.get(i).printBlockWithTrans());
            }
            fileWriter.write("Execution time: " + (endTime - startTime) / 1000000 + "ms\n\n");

            // QUESTION 3
            startTime = System.nanoTime();
            Collections.sort(transactions, new Comparator<Transaction>() {
                public int compare(Transaction t1, Transaction t2) {
                    return Long.compare(t1.getGasPrice(), t2.getGasPrice());
                }
            });
            endTime = System.nanoTime();

            fileWriter.write("\n\nQuestion 3: List up all the transactions by their transaction fee in an increasing order\n");
            fileWriter.write(String.format("%-66s %-7s %-42s %-42s %-42s %-15s %-66s %-5s %-18s\n", "Block Hash", "Block #", "From",
                    "To", "Contract ID", "Gas Price", "Txn Hash", "Index", "Token"));
            for (int i = 0; i < 100; i++) {
                fileWriter.write(transactions.get(i).printTransaction());
            }
            fileWriter.write("Execution time: " + (endTime - startTime) / 1000000 + "ms\n\n");
            

            // QUESTION 4
            startTime = System.nanoTime();
            Collections.sort(transactions, new Comparator<Transaction>() {
                public int compare(Transaction t1, Transaction t2) {
                    // Sort by block number
                    int comp1 = t1.getBlockNumber() - t2.getBlockNumber();
                    if (comp1 != 0) {
                        return comp1;
                    }
                    // Sort by gas price
                    return Long.compare(t1.getGasPrice(), t2.getGasPrice());
                }
            });
            endTime = System.nanoTime();

            fileWriter.write("\n\nQuestion 4: List up all the transactions per block in an increasing order of gas fees\n");
            fileWriter.write(String.format("%-66s %-7s %-42s %-42s %-42s %-15s %-66s %-5s %-18s\n", "Block Hash", "Block #", "From",
                    "To", "Contract ID", "Gas Price", "Txn Hash", "Index", "Token"));
            for (int i = 0; i < 100; i++) {
                fileWriter.write(transactions.get(i).printTransaction());
            }
            fileWriter.write("Execution time: " + (endTime - startTime) / 1000000 + "ms\n\n");

            // QUESTION 5
            startTime = System.nanoTime();
            Collections.sort(transactions, new Comparator<Transaction>() {
                public int compare(Transaction t1, Transaction t2) {
                    // Sort by contract address
                    int comp1 = t1.getContractID().compareTo(t2.getContractID());
                    if (comp1 != 0) {
                        return comp1;
                    }

                    // Sort by block number
                    return t1.getBlockNumber() - t2.getBlockNumber();
                }
            });
            endTime = System.nanoTime();

            fileWriter.write("\n\nQuestion 5: List up all the transactions in groups per contract address in an increasing order of the block#\n");
            fileWriter.write(String.format("%-66s %-7s %-42s %-42s %-42s %-15s %-66s %-5s %-18s\n", "Block Hash", "Block #", "From",
                    "To", "Contract ID", "Gas Price", "Txn Hash", "Index", "Token"));        
            for (int i = 0; i < 100; i++) {
                fileWriter.write(transactions.get(i).printTransaction());
            }
            fileWriter.write("Execution time: " + (endTime - startTime) / 1000000 + "ms\n\n");

            // QUESTION 6
            int searchBlockNumber = 3110014;
            startTime = System.nanoTime();
            ArrayList<Transaction> listOfTrans = blockTable.get(searchBlockNumber).getTransactionList();
            endTime = System.nanoTime();
            fileWriter.write("\n\nQuestion 6: Search for a particular block# (3110014 in this case) and then display its transactions list\n");
            fileWriter.write(String.format("%-66s %-7s %-42s %-42s %-42s %-15s %-66s %-5s %-18s\n", "Block Hash", "Block #", "From",
                    "To", "Contract ID", "Gas Price", "Txn Hash", "Index", "Token"));      
            for(Transaction t : listOfTrans){
                fileWriter.write(t.printTransaction());
            }
            fileWriter.write("Execution time: " + (endTime - startTime) / 1000000 + "ms\n\n");

            // QUESTION 7
            fileWriter.write("\n\nQuestion 7: Search for a particular transaction hash code for an index within a block# and then display transaction fee and block#\n");
            fileWriter.write("Look for : 0xa44d655c8bbf8a2b7ca44a65fb52bfa9d7eadbb6da8e551bdd466abf8c253c0a \n");
            fileWriter.write(String.format("%-66s %-7s %-15s\n", "Txn Hash", "Block #", "Gas Price"));  
            String transCode = "0xa44d655c8bbf8a2b7ca44a65fb52bfa9d7eadbb6da8e551bdd466abf8c253c0a";
            startTime = System.nanoTime();
            for (Transaction t : transactions) {
                if (transCode.equals(t.getTxnHash())) {
                    // System.out.println(t.getBlockNumber());
                    fileWriter.write(String.format("%66s %7d %15d\n",t.getTxnHash(), t.getBlockNumber(), t.getGasPrice()));
                }
            }
            endTime = System.nanoTime();
            fileWriter.write("Execution time: " + (endTime - startTime) / 1000000 + "ms\n\n");
            // System.out.println("Execution time: " + (endTime - startTime) / 1000000);

            // QUESTION 8
            fileWriter.write("\n\nQuestion 8: Identify and list up all the transactions originated from a particular public-key (node) and their total transaction fee\n");
            fileWriter.write("Look for : 0xa455232135b2d6f2e50d7a1131d7e5752f9cb77d \n");
            fileWriter.write(String.format("%-66s %-7s %-42s %-42s %-42s %-15s %-66s %-5s %-18s\n", "Block Hash", "Block #", "From",
                    "To", "Contract ID", "Gas Price", "Txn Hash", "Index", "Token"));  
            String originateFrom = "0xa455232135b2d6f2e50d7a1131d7e5752f9cb77d";
            startTime = System.nanoTime();
            long totalGasPriceFrom = 0;
            for (Transaction t : transactions) {
                if (originateFrom.equals(t.getFrom())) {
                    totalGasPriceFrom += t.getGasPrice();
                    fileWriter.write(t.printTransaction());
                }
            }
            endTime = System.nanoTime();
            fileWriter.write("Total fee: " + totalGasPriceFrom + "\n");
            fileWriter.write("Execution time: " + (endTime - startTime) / 1000000 + "ms\n\n");

            // QUESTION 9
            fileWriter.write("\n\nQuestion 9: Identify and list up all the transactions destined to a particular public-key (node) and their total transaction fee\n");
            fileWriter.write("Look for : 0x7b36d8be6f92818dc30f532ae2a67128b4b92b21 \n");
            fileWriter.write(String.format("%-66s %-7s %-42s %-42s %-42s %-15s %-66s %-5s %-18s\n", "Block Hash", "Block #", "From",
                    "To", "Contract ID", "Gas Price", "Txn Hash", "Index", "Token"));  
            String goTo = "0x7b36d8be6f92818dc30f532ae2a67128b4b92b21";
            startTime = System.nanoTime();
            long totalGasPriceTo = 0;
            for (Transaction t : transactions) {
                if (goTo.equals(t.getTo())) {
                    totalGasPriceTo += t.getGasPrice();
                    fileWriter.write(t.printTransaction());
                }
            }
            endTime = System.nanoTime();
            fileWriter.write("Total fee: " + totalGasPriceTo + "\n");
            fileWriter.write("Execution time: " + (endTime - startTime) / 1000000 + "ms\n\n");

            // QUESTION 10
            fileWriter.write("\n\nQuestion 10: Identify the transaction id in a particular node (contract id) with the largest (smallest) value of tokens\n");
            fileWriter.write("Look for : 0xc25b3dbbfe2d84c1195b8b73dc61a306720cbfc6 \n");
            String contractID = "0xc25b3dbbfe2d84c1195b8b73dc61a306720cbfc6";
            startTime = System.nanoTime();
            String largestTran = "";
            long largestToken = -1;
            long smallestToken = -1;
            String smallestTran = "";
            for (Transaction t : transactions) {
                if (contractID.equals(t.getContractID())) {
                    if (smallestToken == -1) {
                        largestToken = smallestToken = t.getToken();
                        largestTran = smallestTran = t.getTxnHash();
                        continue;
                    }
                    if (t.getToken() > largestToken) {
                        largestToken = t.getToken();
                        largestTran = t.getTxnHash();
                    }
                    if (t.getToken() < smallestToken) {
                        smallestToken = t.getToken();
                        smallestTran = t.getTxnHash();
                    }
                }
            }
            endTime = System.nanoTime();
            fileWriter.write("Largest: " + largestTran + "\n");
            fileWriter.write("Smallest " + smallestTran + "\n");
            fileWriter.write("Execution time: " + (endTime - startTime) / 1000000 + "ms\n\n");
        } catch (IOException e) {
            System.out.print(e);
        }finally{
            if(fileWriter != null)
                try{
                    fileWriter.close();
                }catch(IOException e){

                }
        }
    }

    public static List<Block> readBlocks(String blockFilePath) {
        BufferedReader bReader = null;
        String row = "";
        List<Block> blocks = new ArrayList<Block>();
        try {
            bReader = new BufferedReader(new FileReader(blockFilePath));
            bReader.readLine();
            while ((row = bReader.readLine()) != null) {
                // use comma as separator
                String[] block_input = row.split(",");
                blocks.add(new Block(block_input[0], Integer.parseInt(block_input[1]), Integer.parseInt(block_input[2]),
                        block_input[3], Integer.parseInt(block_input[4]), block_input[5],
                        Integer.parseInt(block_input[6]), Integer.parseInt(block_input[7])));
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
                    // bReader.printStackTrace();
                }
            }
        }
        return blocks;
    }

    public static List<Transaction> readTransactions(String transFilePath) {
        BufferedReader bReader = null;
        String row = "";
        List<Transaction> transactions = new ArrayList<Transaction>();
        try {
            bReader = new BufferedReader(new FileReader(transFilePath));
            bReader.readLine();
            while ((row = bReader.readLine()) != null) {
                String[] trans_input = row.split(",");
                if (trans_input.length < 9) {
                    continue;
                }
                transactions.add(new Transaction(trans_input[0], Integer.parseInt(trans_input[1]), trans_input[2],
                        trans_input[3], trans_input[4], (long) Double.parseDouble(trans_input[5]), trans_input[6],
                        Integer.parseInt(trans_input[7]), (long) Double.parseDouble(trans_input[8])));
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
                    // bReader.printStackTrace();
                }
            }
        }
        return transactions;
    }
}