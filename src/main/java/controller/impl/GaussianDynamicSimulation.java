package controller.impl;

import entity.Chromosome;
import entity.DataPool;
import entity.Task;
import utils.MakeSpanUtils;

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
        int[] order = chromosome.getTask();
        int[] task2ins = chromosome.getTask2ins();
        int[] ins2type = chromosome.getIns2type();

        double cost = 0;
        double makeSpan = 0;

        // available time of instances
        double[] availableTime = new double[order.length];
        //exit time
        double exitTime = 0;

        for(int taskIndex : order){
            int ins = task2ins[taskIndex];
            int type = ins2type[ins];
            Task task = DataPool.tasks[taskIndex];

            // Calculate the start time of "task"
            double startTime = 0;
            for(int preTaskIndex : task.getPredecessor()){
                Task preTask = DataPool.tasks[preTaskIndex];
                int preIns = task2ins[preTaskIndex];
                int preType = ins2type[preIns];

                double minBandwidth = Math.min(DataPool.types[type].bw, DataPool.types[preType].bw);
                double arrivalTime;
                // arrival time of preTask = end time of preTask and Communication time
                arrivalTime = chromosome.getEnd()[preTaskIndex] + MakeSpanUtils.getCommTime(preTask.getOutputSize(), minBandwidth);
                startTime = Math.max(startTime, arrivalTime);
            }
            startTime = Math.max(startTime, availableTime[ins]);
            chromosome.getStart()[taskIndex] = startTime;
            chromosome.launchTime[ins] = Math.min(chromosome.launchTime[ins], startTime);

            // Calculate the finish time of "task" and update other variables
            double processingTime = task.getReferTime() / DataPool.types[type].cu;
            processingTime = processingTime + Math.abs(random.nextGaussian(0, DataPool.sigma));
            double finishTime = startTime + processingTime;
            chromosome.getEnd()[taskIndex] = finishTime;
            chromosome.shutdownTime[ins] = Math.max(chromosome.shutdownTime[ins], finishTime);

            availableTime[ins] = finishTime;
            if(task.getSuccessor().size() == 0) {
                exitTime = Math.max(exitTime, finishTime);
            }
        }
        makeSpan = exitTime;

        // Calculate the cost
        for(int i = 0; i < task2ins.length;i++){
            double launchTime = chromosome.launchTime[i];
            double shutdownTime = chromosome.shutdownTime[i];
            int typeIndex = ins2type[i];
            if (launchTime != shutdownTime){
                double price = DataPool.types[typeIndex].p;
                cost += price * (shutdownTime - launchTime);
            }
        }
        chromosome.setMakeSpan(makeSpan);
        chromosome.setCost(cost);

    }

}
