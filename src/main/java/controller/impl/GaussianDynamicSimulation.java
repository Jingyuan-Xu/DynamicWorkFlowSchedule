package controller.impl;

import controller.DynamicSimulation;
import entity.Chromosome;
import entity.DataPool;
import entity.Task;
import utils.DynamicUtils;

import java.util.List;
import java.util.Random;

public class GaussianDynamicSimulation extends AbstractDynamicSimulation{
    @Override
    void doSim(List<Chromosome> list) {
        for(Chromosome chromosome:list){
            setDynamicTarget(chromosome);
        }
    }

    private void setDynamicTarget(Chromosome chromosome){
        Random random=new Random();

    }

}
