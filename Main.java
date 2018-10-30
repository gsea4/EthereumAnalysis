import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.*;
import java.util.*;

public class Main {
    public static int time = 0;
    public static List<String> visitedNodes = new ArrayList<String>();
    public static void main(String[] args) {
        String blockFilePath = "blocks (10172018).csv";
        String transFilePath = "transactions (10172018).csv";

        List<Block> blocks = readBlocks(blockFilePath);
        List<Transaction> transactions = readTransactions(transFilePath);

        BufferedWriter fileWriter = null;
        try {
            fileWriter = new BufferedWriter(new FileWriter("my_output.txt"));
            
            // // QUESTION 1
            double startTime = System.nanoTime();
            Collections.sort(blocks);
            double endTime = System.nanoTime();

            fileWriter.write("Question 1: List up all the blocks by their gas used in an increasing order\n");
            fileWriter.write(String.format("%-66s %-7s %5s %-7s %-10s %-42s %-9s %-8s\n", "Block Hash", "Block #",
                    "Size", "Time Stamp", "Difficulty", "Miner", "Gas Limit", "Gas Used"));
            System.out.print("Question 1: List up all the blocks by their gas used in an increasing order\n");
            System.out.print(String.format("%-66s %-7s %5s %-7s %-10s %-42s %-9s %-8s\n", "Block Hash", "Block #",
            "Size", "Time Stamp", "Difficulty", "Miner", "Gas Limit", "Gas Used"));

            for (int i = 0; i < 100; i++) {
                System.out.print(blocks.get(i).printBlock());
            }
            for(Block b : blocks){
                fileWriter.write(b.printBlock());
            }

            fileWriter.write("Execution time: " + (endTime - startTime) / 1000000 + "ms\n\n");
            System.out.print("Execution time: " + (endTime - startTime) / 1000000 + "ms\n\n");      

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
            System.out.print("\n\nQuestion 2: List up all the blocks by their # transactions in an increasing order\n");
            System.out.print(String.format("%-66s %-7s %5s %-7s %-10s %-42s %-9s %-8s %-7s\n", "Block Hash", "Block #",
            "Size", "Time Stamp", "Difficulty", "Miner", "Gas Limit", "Gas Used", "# Trans"));

            for (int i = 0; i < 100; i++) {
                System.out.print(blocks.get(i).printBlockWithTrans());
            }

            for (Block b : blocks) {
                fileWriter.write(b.printBlockWithTrans());
            }

            fileWriter.write("Execution time: " + (endTime - startTime) / 1000000 + "ms\n\n");
            System.out.print("Execution time: " + (endTime - startTime) / 1000000 + "ms\n\n");

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
            System.out.print("\n\nQuestion 3: List up all the transactions by their transaction fee in an increasing order\n");
            System.out.print(String.format("%-66s %-7s %-42s %-42s %-42s %-15s %-66s %-5s %-18s\n", "Block Hash", "Block #", "From",
            "To", "Contract ID", "Gas Price", "Txn Hash", "Index", "Token"));

            for (int i = 0; i < 100; i++) {
                System.out.print(transactions.get(i).printTransaction());
                fileWriter.write(transactions.get(i).printTransaction());
            }
            fileWriter.write("Execution time: " + (endTime - startTime) / 1000000 + "ms\n\n");
            System.out.print("Execution time: " + (endTime - startTime) / 1000000 + "ms\n\n");
            

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
            System.out.print("\n\nQuestion 4: List up all the transactions per block in an increasing order of gas fees\n");
            System.out.print(String.format("%-66s %-7s %-42s %-42s %-42s %-15s %-66s %-5s %-18s\n", "Block Hash", "Block #", "From",
            "To", "Contract ID", "Gas Price", "Txn Hash", "Index", "Token"));

            for (int i = 0; i < 100; i++) {
                System.out.print(transactions.get(i).printTransaction());
            }

            for (Transaction t : transactions) {
                fileWriter.write(t.printTransaction());
            }

            fileWriter.write("Execution time: " + (endTime - startTime) / 1000000 + "ms\n\n");
            System.out.print("Execution time: " + (endTime - startTime) / 1000000 + "ms\n\n");

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
            System.out.print("\n\nQuestion 5: List up all the transactions in groups per contract address in an increasing order of the block#\n");
            System.out.print(String.format("%-66s %-7s %-42s %-42s %-42s %-15s %-66s %-5s %-18s\n", "Block Hash", "Block #", "From",
            "To", "Contract ID", "Gas Price", "Txn Hash", "Index", "Token"));

            for (int i = 0; i < 100; i++) {
                System.out.print(transactions.get(i).printTransaction());
            }
            for (Transaction t : transactions) {
                fileWriter.write(t.printTransaction());
            }

            fileWriter.write("Execution time: " + (endTime - startTime) / 1000000 + "ms\n\n");
            System.out.print("Execution time: " + (endTime - startTime) / 1000000 + "ms\n\n");

            // QUESTION 6
            int searchBlockNumber = 3110014;
            startTime = System.nanoTime();
            ArrayList<Transaction> listOfTrans = blockTable.get(searchBlockNumber).getTransactionList();
            endTime = System.nanoTime();
            fileWriter.write("\n\nQuestion 6: Search for a particular block# (3110014 in this case) and then display its transactions list\n");
            fileWriter.write(String.format("%-66s %-7s %-42s %-42s %-42s %-15s %-66s %-5s %-18s\n", "Block Hash", "Block #", "From",
                    "To", "Contract ID", "Gas Price", "Txn Hash", "Index", "Token"));
            System.out.print("\n\nQuestion 6: Search for a particular block# (3110014 in this case) and then display its transactions list\n");
            System.out.print(String.format("%-66s %-7s %-42s %-42s %-42s %-15s %-66s %-5s %-18s\n", "Block Hash", "Block #", "From",
            "To", "Contract ID", "Gas Price", "Txn Hash", "Index", "Token"));

            for(Transaction t : listOfTrans){
                fileWriter.write(t.printTransaction());
                // System.out.print(listOfTrans.get(i).printTransaction());
                System.out.print(t.printTransaction());
            }

            // for(int i = 0; i < 100; i++){
            //     System.out.print(listOfTrans.get(i).printTransaction());
            // }

            fileWriter.write("Execution time: " + (endTime - startTime) / 1000000 + "ms\n\n");
            System.out.print("Execution time: " + (endTime - startTime) / 1000000 + "ms\n\n");

            // QUESTION 7
            fileWriter.write("\n\nQuestion 7: Search for a particular transaction hash code for an index within a block# and then display transaction fee and block#\n");
            fileWriter.write("Look for : 0xa44d655c8bbf8a2b7ca44a65fb52bfa9d7eadbb6da8e551bdd466abf8c253c0a \n");
            fileWriter.write(String.format("%-66s %-7s %-15s\n", "Txn Hash", "Block #", "Gas Price"));
            System.out.print("\n\nQuestion 7: Search for a particular transaction hash code for an index within a block# and then display transaction fee and block#\n");
            System.out.print("Look for : 0xa44d655c8bbf8a2b7ca44a65fb52bfa9d7eadbb6da8e551bdd466abf8c253c0a \n");
            System.out.print(String.format("%-66s %-7s %-15s\n", "Txn Hash", "Block #", "Gas Price"));

            String transCode = "0xa44d655c8bbf8a2b7ca44a65fb52bfa9d7eadbb6da8e551bdd466abf8c253c0a";
            startTime = System.nanoTime();
            for (Transaction t : transactions) {
                if (transCode.equals(t.getTxnHash())) {
                    fileWriter.write(String.format("%66s %7d %15d\n",t.getTxnHash(), t.getBlockNumber(), t.getGasPrice()));
                    System.out.print(String.format("%66s %7d %15d\n",t.getTxnHash(), t.getBlockNumber(), t.getGasPrice()));
                }
            }
            endTime = System.nanoTime();
            fileWriter.write("Execution time: " + (endTime - startTime) / 1000000 + "ms\n\n");
            System.out.print("Execution time: " + (endTime - startTime) / 1000000 + "ms\n\n");

            // QUESTION 8
            fileWriter.write("\n\nQuestion 8: Identify and list up all the transactions originated from a particular public-key (node) and their total transaction fee\n");
            fileWriter.write("Look for : 0xa455232135b2d6f2e50d7a1131d7e5752f9cb77d \n");
            fileWriter.write(String.format("%-66s %-7s %-42s %-42s %-42s %-15s %-66s %-5s %-18s\n", "Block Hash", "Block #", "From",
                    "To", "Contract ID", "Gas Price", "Txn Hash", "Index", "Token"));
            System.out.print("\n\nQuestion 8: Identify and list up all the transactions originated from a particular public-key (node) and their total transaction fee\n");
            System.out.print("Look for : 0xa455232135b2d6f2e50d7a1131d7e5752f9cb77d \n");
            System.out.print(String.format("%-66s %-7s %-42s %-42s %-42s %-15s %-66s %-5s %-18s\n", "Block Hash", "Block #", "From",
            "To", "Contract ID", "Gas Price", "Txn Hash", "Index", "Token"));

            String originateFrom = "0xa455232135b2d6f2e50d7a1131d7e5752f9cb77d";
            startTime = System.nanoTime();
            long totalGasPriceFrom = 0;
            for (Transaction t : transactions) {
                if (originateFrom.equals(t.getFrom())) {
                    totalGasPriceFrom += t.getGasPrice();
                    fileWriter.write(t.printTransaction());
                    System.out.print(t.printTransaction());
                }
            }
            endTime = System.nanoTime();
            fileWriter.write("Total fee: " + totalGasPriceFrom + "\n");
            fileWriter.write("Execution time: " + (endTime - startTime) / 1000000 + "ms\n\n");
            System.out.print("Total fee: " + totalGasPriceFrom + "\n");
            System.out.print("Execution time: " + (endTime - startTime) / 1000000 + "ms\n\n");

            // QUESTION 9
            fileWriter.write("\n\nQuestion 9: Identify and list up all the transactions destined to a particular public-key (node) and their total transaction fee\n");
            fileWriter.write("Look for : 0x7b36d8be6f92818dc30f532ae2a67128b4b92b21 \n");
            fileWriter.write(String.format("%-66s %-7s %-42s %-42s %-42s %-15s %-66s %-5s %-18s\n", "Block Hash", "Block #", "From",
                    "To", "Contract ID", "Gas Price", "Txn Hash", "Index", "Token"));
            System.out.print("\n\nQuestion 9: Identify and list up all the transactions destined to a particular public-key (node) and their total transaction fee\n");
            System.out.print("Look for : 0x7b36d8be6f92818dc30f532ae2a67128b4b92b21 \n");
            System.out.print(String.format("%-66s %-7s %-42s %-42s %-42s %-15s %-66s %-5s %-18s\n", "Block Hash", "Block #", "From",
            "To", "Contract ID", "Gas Price", "Txn Hash", "Index", "Token"));

            String goTo = "0x7b36d8be6f92818dc30f532ae2a67128b4b92b21";
            startTime = System.nanoTime();
            long totalGasPriceTo = 0;
            for (Transaction t : transactions) {
                if (goTo.equals(t.getTo())) {
                    totalGasPriceTo += t.getGasPrice();
                    fileWriter.write(t.printTransaction());
                    //System.out.print(t.printTransaction());
                }
            }
            endTime = System.nanoTime();

            int counter = 0;
            for (Transaction t : transactions) {
                if(counter == 50) break;
                if (goTo.equals(t.getTo())) {
                    //totalGasPriceTo += t.getGasPrice();
                    //fileWriter.write(t.printTransaction());
                    System.out.print(t.printTransaction());
                    counter++;
                }
            }

            fileWriter.write("Total fee: " + totalGasPriceTo + "\n");
            fileWriter.write("Execution time: " + (endTime - startTime) / 1000000 + "ms\n\n");
            System.out.print("Total fee: " + totalGasPriceTo + "\n");
            System.out.print("Execution time: " + (endTime - startTime) / 1000000 + "ms\n\n");

            // QUESTION 10
            fileWriter.write("\n\nQuestion 10: Identify the transaction id in a particular node (contract id) with the largest (smallest) value of tokens\n");
            fileWriter.write("Look for : 0xc25b3dbbfe2d84c1195b8b73dc61a306720cbfc6 \n");
            System.out.print("\n\nQuestion 10: Identify the transaction id in a particular node (contract id) with the largest (smallest) value of tokens\n");
            System.out.print("Look for : 0xc25b3dbbfe2d84c1195b8b73dc61a306720cbfc6 \n");

            String contractID = "0xc25b3dbbfe2d84c1195b8b73dc61a306720cbfc6";
            startTime = System.nanoTime();
            String largestTran = "";
            double largestToken = -1;
            double smallestToken = -1;
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
            System.out.print("Largest: " + largestTran + "\n");
            System.out.print("Smallest " + smallestTran + "\n");
            System.out.print("Execution time: " + (endTime - startTime) / 1000000 + "ms\n\n");

            // QUESTION 11
            fileWriter.write("\n\nQuestion 11: Build a graph from the list of \"from\"s and \"to\"s and traversa in DFS and BFS\n");
            System.out.print("\n\nQuestion 11: Build a graph from the list of \"from\"s and \"to\"s and traversa in DFS and BFS\n");

            startTime = System.nanoTime();
            HashMap<String, AdjencyList> addressList = new HashMap<String, AdjencyList>();
            for(Transaction t : transactions){
                if(addressList.containsKey(t.getFrom())){
                    HashMap<String, Double> adjencyList = addressList.get(t.getFrom()).getAdjencyList();
                    if(adjencyList.containsKey(t.getTo())){
                        adjencyList.replace(t.getTo(), adjencyList.get(t.getTo()) + t.getToken());
                        continue;
                    }
                    if(!"null".equals(t.getTo()))
                        adjencyList.put(t.getTo(), t.getToken());  
                    continue;
                }
                if(!"null".equals(t.getTo()))
                    addressList.put(t.getFrom(), new AdjencyList(t.getFrom(),t.getTo(), t.getToken()));
            }
            endTime = System.nanoTime();
            fileWriter.write("Execution time for building adjacency list: " + (endTime - startTime) / 1000000 + "ms\n\n");
            System.out.print("Execution time for building adjacency list: " + (endTime - startTime) / 1000000 + "ms\n\n");

            for(AdjencyList item : addressList.values()){
                fileWriter.write(item.getBaseAddress() + " -> ");
                for(Map.Entry<String, Double> entry : item.getAdjencyList().entrySet()){
                    fileWriter.write("| " + entry.getKey() + " (" + entry.getValue() +") ");
                }
                fileWriter.write("\n");
            }

            counter = 0;
            for(AdjencyList item : addressList.values()){
                if(counter == 50) break;
                System.out.print(item.getBaseAddress() + " -> ");
                for(Map.Entry<String, Double> entry : item.getAdjencyList().entrySet()){
                    System.out.print("| " + entry.getKey() + " (" + entry.getValue() +") ");
                }
                System.out.println();
                counter++;
            }

            fileWriter.write("\nPerform BFS with starting node at 0x2976924b350bcee8263f36d86cebd584d2363c1f\n");
            System.out.print("\nPerform BFS with starting node at 0x2976924b350bcee8263f36d86cebd584d2363c1f\n");
            startTime = System.nanoTime();
            BFS("0x2976924b350bcee8263f36d86cebd584d2363c1f", addressList, fileWriter);
            endTime = System.nanoTime();
            fileWriter.write("Execution time for BFS: " + (endTime - startTime) / 1000000 + "ms\n\n");
            System.out.print("Execution time for BFS: " + (endTime - startTime) / 1000000 + "ms\n\n");

            fileWriter.write("\nPerform DFS\n");
            fileWriter.write(String.format("%-42s | %-6s|%-6s\n","Node","D.Time", "F.Time"));
            System.out.print("\nPerform DFS\n");
            System.out.print(String.format("%-42s | %-6s|%-6s\n","Node","D.Time", "F.Time"));

            LinkedList<DFSPrintObject> result = new LinkedList<DFSPrintObject>();

            startTime = System.nanoTime();
            DFS(addressList, result);
            endTime = System.nanoTime();

            while(result.size() != 0){
                DFSPrintObject o = result.poll();
                System.out.print(String.format("%42s | %6s|%6s \n",o.getNode(), o.getDiscoveryTime() ,o.getFinishTime()));
                fileWriter.write(String.format("%42s | %6s|%6s \n",o.getNode(), o.getDiscoveryTime() ,o.getFinishTime()));
            }

            fileWriter.write("Execution time for DFS: " + (endTime - startTime) / 1000000 + "ms\n\n");
            System.out.print("Execution time for DFS: " + (endTime - startTime) / 1000000 + "ms\n\n");
            
        }catch (IOException e) {
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
                        Integer.parseInt(trans_input[7]), Double.parseDouble(trans_input[8])));
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

    public static void BFS(String start, HashMap<String, AdjencyList> addressList, BufferedWriter fileWriter) throws IOException{
        HashMap<String, Integer> distances = new HashMap<String, Integer>();
        for(AdjencyList item : addressList.values()){
            if(!start.equals(item.getBaseAddress())){
                distances.put(item.getBaseAddress(), -1);
            }
        }

        distances.put(start, 0);
        LinkedList<String> queue = new LinkedList<String>();
        queue.add(start);

        while(queue.size() != 0){
            String addr = queue.poll();
            System.out.println(addr + " | " + distances.get(addr));
            fileWriter.write(addr + " | " + distances.get(addr) + "\n");
            for(String node : addressList.get(addr).getAdjencyList().keySet()){
                if(distances.get(node) == null){
                    System.out.println(node + " | " + (distances.get(addr) + 1));
                    fileWriter.write(node + " | " + (distances.get(addr) + 1) + "\n");
                }else{
                    if(distances.get(node) == -1){
                        distances.replace(node, (distances.get(addr) + 1));
                        queue.add(node);
                    }
                }
            }
        }
    }

    public static void DFS(HashMap<String, AdjencyList> addressList, LinkedList<DFSPrintObject> result){
        HashMap<String, Boolean> visited = new HashMap<String, Boolean>();
        for(AdjencyList item : addressList.values()){
            visited.put(item.getBaseAddress(), false);
        }
        time = 0;
        for(AdjencyList item : addressList.values()){
            if(!visited.get(item.getBaseAddress()))
                DFSVisit(item.getBaseAddress(), visited, addressList, result);
        } 
    }

    private static void DFSVisit(String node, HashMap<String, Boolean> visited, HashMap<String, AdjencyList> addressList, LinkedList<DFSPrintObject> result){
        visited.replace(node, true);
        time++;
        int discoverTime = time;

        for(String u : addressList.get(node).getAdjencyList().keySet()){
            if(visited.get(u) == null){
                if(visitedNodes.contains(u)){
                    continue;
                }else{
                    visitedNodes.add(u);
                    time += 2;
                    //System.out.println(u + " | (" + (time-1) + "|" + time + ")");
                    result.addFirst(new DFSPrintObject(u, time-1, time));
                }
                
            }else if(!visited.get(u)){    
                DFSVisit(u, visited, addressList, result);      
            }
        }
        time++;
        //System.out.println(node + " | (" + discoverTime + "|" + time + ")");
        result.addFirst(new DFSPrintObject(node, discoverTime, time));
    }
}
