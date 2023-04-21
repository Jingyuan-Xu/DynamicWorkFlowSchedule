package service.crash;

import controller.impl.AbstractPopulationController;
import entity.Chromosome;
import utils.CrashUtils;

import java.util.List;

public class PartialFixedCrash implements Crash{
    @Override
    public void crash(int generation, List<Chromosome> list, AbstractPopulationController controller) {
        CrashUtils.commonCrash(generation,list);
    }
}
