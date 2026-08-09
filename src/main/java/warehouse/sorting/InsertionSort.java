package warehouse.sorting;

import java.util.List;

import warehouse.exception.InvalidSortDataException;
import warehouse.model.FoodMaterial;

public class InsertionSort extends AbstractSorter {
    public InsertionSort(){
    };

    @Override
    public void sort(List<FoodMaterial> materials,
                    SortCriterion criterion,
                    SortDirection direction
    ) throws InvalidSortDataException {
        
        validate(materials, criterion, direction);
        int n = materials.size();
        
        for(int i = 1; i < n; i++){
            int indexTemp = i;
            FoodMaterial current = materials.get(i);

            while(indexTemp > 0 && 
                compare(
                    materials.get(indexTemp-1), 
                    current, 
                    criterion, 
                    direction
                ) > 0){
                materials.set(indexTemp, materials.get(indexTemp-1));
                indexTemp--;
            }

            materials.set(indexTemp, current);

        }
    }
    
}
