package warehouse.sorting;

import java.util.List;

import warehouse.exception.InvalidSortDataException;
import warehouse.model.FoodMaterial;

public class BubbleSort extends AbstractSorter{
    public BubbleSort(){
    }
    @Override
    public void sort(
        List<FoodMaterial> materials,
        SortCriterion criterion,
        SortDirection direction
    ) throws InvalidSortDataException{

        validate(materials, criterion, direction);
        int n = materials.size();

        for(int i = 0; i < n-1; i++) {
            for(int j = 0; j < n-1-i; j++) {
                int result = compare(materials.get(j), materials.get(j+1), criterion, direction);
                if(result > 0) {
                    swap(materials, j, j+1);
                }
            }
        }
    }
    
}
