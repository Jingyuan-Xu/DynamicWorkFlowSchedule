package utils;

import controller.impl.AbstractPopulationController;
import entity.Chromosome;
import entity.DataPool;
import entity.Type;
import io.jenetics.util.ProxySorter;

import java.util.*;

public class CrashUtils {
    private static final HashSet<Integer> generations=new HashSet<>();
    private static final double severity;
    static {
        String generation = ConfigUtils.get("ins.crash.generation");
        if(!generation.equals("-1")) {
            String[] generations = generation.split(",");
            for(String num:generations){
                CrashUtils.generations.add(Integer.parseInt(num));
            }
        }
        severity = Double.parseDouble(ConfigUtils.get("ins.crash.severity"));
    }

    public static void commonCrash(int generation, List<Chromosome> list){
        if(!generations.contains(generation)) return;
        randomShutdownMachine();

        for(int x=0;x<list.size();++x){
            Chromosome chromosome = list.get(x);
            for(int i=0;i<chromosome.getTask2ins().length;++i){
                if(DataPool.disabledIns.contains(chromosome.getTask2ins()[i])){
                    chromosome.getTask2ins()[i]=DataPool.accessibleIns.get(DataPool.random.nextInt(DataPool.accessibleIns.size()));
                }
            }
            DataUtils.refresh(chromosome);
        }
    }

    public static void totalInsteadCrash(int generation, List<Chromosome> list){
        if(!generations.contains(generation)) return;
        randomShutdownMachine();
        for(int x=0;x<list.size();++x){
            Chromosome chromosome = list.get(x);
            for(int i=0;i<chromosome.getTask2ins().length;++i){
                if(DataPool.disabledIns.contains(chromosome.getTask2ins()[i])){
                    list.set(x,DataUtils.getInitialChromosome());
                    break;
                }
            }
            DataUtils.refresh(list.get(x));
        }
    }

    public static void restartCrash(int generation, List<Chromosome> list, AbstractPopulationController controller){
        if(!generations.contains(generation)) return;
        randomShutdownMachine();
        controller.getFa().clear();
        controller.getSon().clear();
        controller.getRank().clear();
        try {
            controller.doInitial();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void similarityCrash(int generation, List<Chromosome> list){
        if(!generations.contains(generation)) return;
        randomShutdownMachine();
        for(int x=0;x<list.size();++x){
            Chromosome chromosome = list.get(x);
            for(int i=0;i<chromosome.getTask2ins().length;++i){
                if(DataPool.disabledIns.contains(chromosome.getTask2ins()[i])){
                    chromosome.getTask2ins()[i] = getMostSimilarIns(chromosome.getTask2ins()[i],chromosome);
                }
            }
            DataUtils.refresh(list.get(x));
        }
    }

    public static int getMostSimilarIns(int ins,Chromosome chromosome){
        int type = DataPool.insToType.get(ins);
        Type currType = DataPool.types[type];
        double feature = currType.cu*DataPool.weightVector[0] + currType.bw*DataPool.weightVector[1] + currType.p*DataPool.weightVector[2];
        List<Pair> list = new ArrayList<>();
        for(int num:DataPool.accessibleIns){
            Type insType = DataPool.types[DataPool.insToType.get(num)];
            Pair pair = new Pair();
            pair.distance = Math.abs(insType.feature - feature);
            pair.ins = num;
            int workLoad = 0;
            for(int x:chromosome.getTask2ins()){
                if(x==num) workLoad++;
            }
            pair.workload = workLoad;
            list.add(pair);
        }
        double x = 1;
        double y = 0.1;
        list.sort(Comparator.comparingDouble(o->(o.distance*x + y*o.workload)));
        return list.get(0).ins;
    }

    public static int getMinLoadIns(Chromosome chromosome){
        List<Pair> list = new ArrayList<>();
        for(int num:DataPool.accessibleIns){
            int workLoad = 0;
            Pair pair=new Pair();
            for(int x:chromosome.getTask2ins()){
                if(x==num) workLoad++;
            }
            pair.ins = num;
            pair.workload = workLoad;
            list.add(pair);
        }
        list.sort(Comparator.comparingDouble(o->o.workload));
        return list.get(0).ins;
    }

    private static void randomShutdownMachine() {
        int num  = (int) (DataPool.accessibleIns.size() *severity);
        for(int i=0;i<num;++i){
            int index = DataPool.random.nextInt(DataPool.accessibleIns.size());
            int ins = DataPool.accessibleIns.get(index);
            DataPool.accessibleIns.remove(index);
            DataPool.disabledIns.add(ins);
        }
    }

    static class Pair{
        int ins;
        double distance;
        int workload;

    }
}
