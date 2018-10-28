public class Block implements Comparable{
    private String blockHash;
    private int blockNumber;
    private int size;
    private String timeStamp;
    private int difficulty;
    private String miner;
    private int gasLimit;
    private int gasUsed;

    @Override
    public String toString() {
        return getBlockHash() + getBlockNumber() + getSize() + getTimeStamp() + + getDifficulty() + getMiner() + getGasLimit() +  getGasUsed();
    }

    public void printBlock(){
        System.out.printf("%66s %7d %5d %10s %10d %42s %9d %8d", getBlockHash(), getBlockNumber(), getSize(), getTimeStamp(), getDifficulty(), getMiner(), getGasLimit(), getGasUsed());
        System.out.println();
    }
    
    public Block(String blockHash, int blockNumber, int size, String timeStamp, int difficulty, String miner, int gasLimit, int gasUsed){
        this.blockHash = blockHash;
        this.blockNumber = blockNumber;
        this.size = size;
        this.timeStamp = timeStamp;
        this.difficulty = difficulty;
        this.miner = miner;
        this.gasLimit = gasLimit;
        this.gasUsed = gasUsed;
    }
    
    public String getBlockHash() {
        return this.blockHash;
    }

    public int getBlockNumber() {
        return this.blockNumber;
    }

    public int getSize() {
        return this.size;
    }

    public String getTimeStamp() {
        return this.timeStamp;
    }

    public int getDifficulty() {
        return this.difficulty;
    }

    public String getMiner() {
        return this.miner;
    }

    public int getGasLimit() {
        return this.gasLimit;
    }

    public int getGasUsed() {
        return this.gasUsed;
    }

    @Override
    public int compareTo(Object compareBlock){
        int comp = ((Block)compareBlock).getGasUsed();
        return this.gasUsed - comp;
    }
}