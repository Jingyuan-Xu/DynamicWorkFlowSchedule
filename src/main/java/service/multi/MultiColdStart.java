package service.multi;

import controller.impl.NSGAIIPopulationController;
import entity.DataPool;
import service.crash.ColdStartCrash;
import utils.InitUtils;

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
        controller.iterate();
//            String str = DataUtils.operateHV(DataPool.all);
//            WriterUtils.write("src\\main\\resources\\output\\Cold_Start_" + i + ".txt", str);
    }
}
