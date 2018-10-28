public class Transaction{
    private String blockHash;
    private int blockNumber;
    private String from;
    private String to;
    private String contractID;
    private long gasPrice;
    private String txnHash;
    private int index;
    private long token;

    public Transaction(String blockHash, int blockNumber, String from, String to, String contractID, long gasPrice, String txnHash, int index, long token) {
        this.blockHash = blockHash;
        this.blockNumber = blockNumber;
        this.from = from;
        this.to = to;
        this.contractID = contractID;
        this.gasPrice = gasPrice;
        this.txnHash = txnHash;
        this.index = index;
        this.token = token;
    }
    
    public String getBlockHash() {
        return this.blockHash;
    }

    public int getBlockNumber() {
        return this.blockNumber;
    }

    public String getFrom() {
        return this.from;
    }

    public String getTo() {
        return this.to;
    }

    public String getContractID() {
        return this.contractID;
    }

    public long getGasPrice() {
        return this.gasPrice;
    }

    public String getTxnHash() {
        return this.txnHash;
    }

    public int getIndex() {
        return this.index;
    }

    public long getToken() {
        return this.token;
    }
}