import java.util.*;
public class AdjencyList{
    private String baseAddress;
    private HashMap<String, Long> adjencyList;

    public AdjencyList(String baseAddress, String toAddress, long token){
        this.baseAddress = baseAddress;
        adjencyList = new HashMap<String, Long>();
        adjencyList.put(toAddress, token);
    }

    public HashMap<String, Long> getAdjencyList() {
        return adjencyList;
    }

    public String getBaseAddress() {
        return baseAddress;
    }
}