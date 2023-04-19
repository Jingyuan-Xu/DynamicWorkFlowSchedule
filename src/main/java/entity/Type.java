package entity;

public class Type {
    public int id;
    public double cu;
    public double bw;
    public double p;
    public double feature;
    public Type(int i, double cu, double bw, double p){
        this.id = i;
        this.cu = cu;
        this.bw = bw;
        this.p = p;
        feature = DataPool.weightVector[0]*cu + DataPool.weightVector[1]*bw + DataPool.weightVector[2]*p;
    }
}