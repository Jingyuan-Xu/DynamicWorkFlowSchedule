package service.multi;

import controller.impl.NSGAIIPopulationController;
import entity.Chromosome;
import entity.DataPool;
import service.crash.SimilarityFixedCrash;
import service.io.Output;
import service.io.impl.ChartOutputImpl;
import service.io.impl.ConsoleOutputImpl;
import service.io.impl.FileOutputImpl;
import utils.DataUtils;
import utils.InitUtils;
import utils.WriterUtils;

import java.util.List;
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
        List<List<Chromosome>> list = controller.iterate();
        Output output = new FileOutputImpl();
        output.output(list);
//            Output output = new ConsoleOutputImpl();
//            output.output(list);
//            String str = DataUtils.operateHV(DataPool.all);
//            WriterUtils.write("src\\main\\resources\\output\\Similarity_Crash_" + i + ".txt", str);
    }
}
