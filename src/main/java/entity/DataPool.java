package entity;


import service.algorithm.impl.NSGAII;

import java.util.LinkedList;
import java.util.List;

public class DataPool {
    public static Task[] tasks;
    public static TaskGraph graph;
    public static List<int[]> edges;

    public static NSGAII nsgaii = new NSGAII();
    public static int insNum;
    public static int typeNum;
    public static Type[] types;

    public static double sigma = 0.1;

    public static List<List<Chromosome>> all = new LinkedList<>();

}
