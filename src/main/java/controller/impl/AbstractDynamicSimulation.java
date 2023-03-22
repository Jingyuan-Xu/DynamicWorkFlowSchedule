package controller.impl;

import controller.DynamicSimulation;
import entity.Chromosome;
import service.io.Output;
import service.io.impl.ChartOutputImpl;
import utils.DynamicUtils;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDynamicSimulation implements DynamicSimulation {
    @Override
    public List<List<Chromosome>> sim(List<Chromosome> list1,List<Chromosome> list2) {
        try {
            List<Chromosome> temp1 = new ArrayList<>();
            List<Chromosome> temp2 = new ArrayList<>();
            for(Chromosome chromosome:list1){
                temp1.add(chromosome.clone());
            }
            for(Chromosome chromosome:list2){
                temp2.add(chromosome.clone());
            }
            doSim(temp1);
            doSim(temp2);
            Output output = new ChartOutputImpl();
            List<List<Chromosome>> ans = new ArrayList<>();
            ans.add(temp1);
            ans.add(temp2);
//            System.out.println("HV{before: "+ beforeHV + " | after: " + afterHV + " | " + "reduce: " + (beforeHV - afterHV)/beforeHV + "}");
            output.output(ans);
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
    abstract void doSim(List<Chromosome> list);
}
