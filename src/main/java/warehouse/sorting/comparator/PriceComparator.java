package warehouse.sorting.comparator;

import java.util.Comparator;

import warehouse.model.FoodMaterial;

public class PriceComparator implements Comparator<FoodMaterial> {
    @Override
    public int compare(FoodMaterial first, FoodMaterial second){
        return Double.compare(first.getPrice(), second.getPrice());
    }
}
