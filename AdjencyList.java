import java.util.*;
public class AdjencyList{
    private String baseAddress;
    private HashMap<String, Double> adjencyList;

    public AdjencyList(String baseAddress, String toAddress, double token){
        this.baseAddress = baseAddress;
        adjencyList = new HashMap<String, Double>();
        adjencyList.put(toAddress, token);
    }

    public HashMap<String, Double> getAdjencyList() {
        return adjencyList;
    }

    public String getBaseAddress() {
        return baseAddress;
    }
}