package service.crash;

import controller.impl.AbstractPopulationController;
import entity.Chromosome;

import java.util.List;

public interface Crash {

    void crash(int generation, List<Chromosome> list, AbstractPopulationController controller);
}
