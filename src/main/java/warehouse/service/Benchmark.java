package warehouse.service;

import java.util.List;

import warehouse.exception.InvalidSortDataException;
import warehouse.model.FoodMaterial;
import warehouse.sorting.SortCriterion;
import warehouse.sorting.SortDirection;
import warehouse.sorting.SortingStrategy;

public class Benchmark {
    public long measure(
        SortingStrategy sorter,
        List<FoodMaterial> materials,
        SortCriterion criterion,
        SortDirection direction
    ) throws InvalidSortDataException{
        long starTime = System.nanoTime();

        sorter.sort(materials, criterion, direction);

        long endTime = System.nanoTime();

        return endTime - starTime;
    }
}
