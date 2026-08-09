package warehouse.sorting;

import java.util.List;

import warehouse.exception.InvalidSortDataException;
import warehouse.model.FoodMaterial;

public interface SortingStrategy {
    void sort(
        List<FoodMaterial> materials,
        SortCriterion criterion,
        SortDirection direction
    ) throws InvalidSortDataException;
}
