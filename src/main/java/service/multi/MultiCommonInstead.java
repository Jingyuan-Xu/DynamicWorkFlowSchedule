package service.multi;

import controller.PopulationController;
import controller.impl.NSGAIIPopulationController;
import entity.Chromosome;
import entity.DataPool;
import service.crash.PartialFixedCrash;
import service.io.Output;
import service.io.impl.ConsoleOutputImpl;
import utils.DataUtils;
import utils.InitUtils;
import utils.WriterUtils;

import java.util.List;
import java.util.Random;

public class MultiCommonInstead {
    public static void main(String[] args) {
    }

    public static void runCommonInstead(int i) {
        DataPool.clear();
        DataPool.random = new Random(i);
        InitUtils.init();
        NSGAIIPopulationController controller = new NSGAIIPopulationController();
        controller.crash = new PartialFixedCrash();
        List<List<Chromosome>> list = controller.iterate();
//            String str = DataUtils.operateHV(DataPool.all);
//            WriterUtils.write("src\\main\\resources\\output\\Common_Instead_Crash_" + i + ".txt", str);
    }
}
