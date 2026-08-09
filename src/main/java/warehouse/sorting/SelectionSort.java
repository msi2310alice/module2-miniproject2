package warehouse.sorting;

import java.util.List;

import warehouse.exception.InvalidSortDataException;
import warehouse.model.FoodMaterial;

public class SelectionSort extends AbstractSorter{
    public SelectionSort(){
    }

    @Override
    public void sort(List<FoodMaterial> materials,
                    SortCriterion criterion,
                    SortDirection direction
    ) throws InvalidSortDataException {

        validate(materials, criterion, direction);

        int n = materials.size();

        for(int i = 0; i < n-1; i++){
            int selectedIndex = i;
            for(int j = i + 1; j < n; j++) {
                int result = compare(materials.get(selectedIndex), materials.get(j), criterion, direction);
                if(result > 0) {
                    selectedIndex = j;
                }
            }
            if(selectedIndex != i) {
                swap(materials, i, selectedIndex);
            }
        }
    }

    
}
