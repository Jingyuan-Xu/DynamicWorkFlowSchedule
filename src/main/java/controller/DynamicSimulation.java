package controller;

import entity.Chromosome;

import java.util.List;

public interface DynamicSimulation {
    List<List<Chromosome>> sim(List<Chromosome> list);
}
