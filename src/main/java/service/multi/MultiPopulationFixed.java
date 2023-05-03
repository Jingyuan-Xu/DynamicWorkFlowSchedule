package service.multi;

import controller.impl.NSGAIIPopulationController;
import entity.Chromosome;
import entity.DataPool;
import service.crash.SimilarityFixedCrash;
import service.io.Output;
import service.io.impl.FileOutputImpl;
import utils.*;

import java.util.List;
import java.util.Random;

public class MultiPopulationFixed {
    public static NSGAIIPopulationController DA;
    public static void main(String[] args) {
        for(int i=0;i<30;++i){
            runPopulationFixed(i);
        }
    }

    public static void runPopulationFixed(int i) {
        DataPool.clear();
        DataPool.random = new Random(i);
        InitUtils.init();
        NSGAIIPopulationController controller = new NSGAIIPopulationController();
        controller.crash = new SimilarityFixedCrash();
        int generation = Integer.parseInt(ConfigUtils.get("evolution.population.generation"));
        controller.doInitial();

        for(int k=0;k<generation;++k){
            List<List<Chromosome>> list = controller.iterateACycle(k,DataPool.all);
            if(CrashUtils.generations.contains(k)){
                controller.isChanged = true;
                DA = new NSGAIIPopulationController();
                DA.doInitial();
            }
            if(DA!=null) DA.iterateACycle(0,null);
        }

//        Output output = new FileOutputImpl();
//        output.output(list);
//            Output output = new ConsoleOutputImpl();
//            output.output(list);
//            String str = DataUtils.operateHV(DataPool.all);
//            WriterUtils.write("src\\main\\resources\\output\\Similarity_Crash_" + i + ".txt", str);
    }

    public static NSGAIIPopulationController getDA(){
        return DA;
    }
}
