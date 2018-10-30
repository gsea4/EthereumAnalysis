public class DFSPrintObject{
    private String node;
    private int discoveryTime;
    private int finishTime;

    public DFSPrintObject(String node, int dTime, int fTime){
        this.node = node;
        this.discoveryTime = dTime;
        this.finishTime = fTime;
    }

    public String getNode(){
        return this.node;
    }

    public int getDiscoveryTime(){
        return this.discoveryTime;
    }

    public int getFinishTime(){
        return this.finishTime;
    }
}