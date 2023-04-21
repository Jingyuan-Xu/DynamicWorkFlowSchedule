package service.multi;

import controller.impl.NSGAIIPopulationController;
import entity.Chromosome;
import entity.DataPool;
import service.crash.ColdStartCrash;
import service.io.Output;
import service.io.impl.ConsoleOutputImpl;
import utils.DataUtils;
import utils.InitUtils;
import utils.WriterUtils;

import java.util.List;
import java.util.Random;

public class MultiColdStart {
    public static void main(String[] args) {
    }

    public static void runColdStart(int i) {
        DataPool.clear();
        DataPool.random = new Random(i);
        InitUtils.init();
        NSGAIIPopulationController controller = new NSGAIIPopulationController();
        controller.crash = new ColdStartCrash();
        List<List<Chromosome>> list = controller.iterate();
//            String str = DataUtils.operateHV(DataPool.all);
//            WriterUtils.write("src\\main\\resources\\output\\Cold_Start_" + i + ".txt", str);
    }
}
