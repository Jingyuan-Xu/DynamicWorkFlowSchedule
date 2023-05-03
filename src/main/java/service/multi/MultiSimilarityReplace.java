package service.multi;

import controller.impl.NSGAIIPopulationController;
import entity.DataPool;
import service.crash.SimilarityFixedCrash;
import utils.InitUtils;

import java.util.Random;

public class MultiSimilarityReplace {
    public static void main(String[] args) {
        runSimilarityReplace(0);
    }

    public static void runSimilarityReplace(int i) {
        DataPool.clear();
        DataPool.random = new Random(i);
        InitUtils.init();
        NSGAIIPopulationController controller = new NSGAIIPopulationController();
        controller.crash = new SimilarityFixedCrash();
        controller.iterate();
//            Output output = new ConsoleOutputImpl();
//            output.output(list);
//            String str = DataUtils.operateHV(DataPool.all);
//            WriterUtils.write("src\\main\\resources\\output\\Similarity_Crash_" + i + ".txt", str);
    }
}
