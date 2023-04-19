package test;

import entity.Chromosome;
import org.uma.jmetal.qualityindicator.impl.hypervolume.Hypervolume;
import org.uma.jmetal.qualityindicator.impl.hypervolume.impl.WFGHypervolume;
import utils.DataUtils;

import java.util.ArrayList;
import java.util.List;

public class HVTest {
    public static void main(String[] args) {
        Chromosome chromosome=new Chromosome();
        chromosome.setMakeSpan(1);
        chromosome.setCost(2);
        Chromosome chromosome1 = new Chromosome();
        chromosome1.setMakeSpan(2);
        chromosome1.setCost(1);
        Chromosome chromosome2=new Chromosome();
        chromosome2.setMakeSpan(1.5);
        chromosome2.setCost(1.5);
        List<Chromosome> chromosomes = new ArrayList<>();
        chromosomes.add(chromosome);
        chromosomes.add(chromosome1);
        chromosomes.add(chromosome2);
        List<List<Chromosome>> list=new ArrayList<>();
        list.add(chromosomes);
        System.out.println(DataUtils.operateHV(list));
    }
}
