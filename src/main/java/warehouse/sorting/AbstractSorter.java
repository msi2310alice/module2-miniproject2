package warehouse.sorting;

import java.util.List;

import warehouse.exception.InvalidSortDataException;
import warehouse.model.FoodMaterial;
import warehouse.sorting.comparator.PriceComparator;
import warehouse.sorting.comparator.QuantityComparator;

public abstract class AbstractSorter implements SortingStrategy {

    public AbstractSorter(){
    }

    protected void swap(
        List<FoodMaterial> materials,
        int i, 
        int j
    ){
        FoodMaterial temp = materials.get(i);
        materials.set(i, materials.get(j));
        materials.set(j, temp);
    }

    protected int compare(
        FoodMaterial first, 
        FoodMaterial second,
        SortCriterion criterion,
        SortDirection direction
    ) {
        int result = 0;
        switch(criterion) {
            case ID:
                result = first.compareTo(second); 
                break;
            case PRICE:
                result = new PriceComparator().compare(first, second); 
                break;
            case QUANTITY: 
                result = new QuantityComparator().compare(first, second);
                break;
            default:
                throw new IllegalArgumentException("Invalid sort criterion");
        }
        if(direction == SortDirection.DESCENDING) {
            result = - result;
        }
        return result;
    }

    protected void validate(
        List<FoodMaterial> materials,
        SortCriterion criterion,
        SortDirection direction
    ) throws InvalidSortDataException {

        if(materials == null) {
            throw new InvalidSortDataException("Material list cannot be null");
        }
        if(criterion == null) {
            throw new InvalidSortDataException("Sort criterion cannot be null");
        }
        if(direction == null){
            throw new InvalidSortDataException("Sort direction cannot be null");
        }

    }
}
