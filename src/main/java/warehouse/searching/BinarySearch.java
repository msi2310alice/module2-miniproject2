package warehouse.searching;

import java.util.List;

import warehouse.exception.InvalidSearchDataException;
import warehouse.exception.UnsortedDataException;
import warehouse.model.FoodMaterial;

public class BinarySearch {
    public BinarySearch(){
    }
    public FoodMaterial searchById(
        List<FoodMaterial> materials,
        String id
    ) throws InvalidSearchDataException, UnsortedDataException{

        validate(materials, id);

        if(!isSortById(materials)){
            throw new UnsortedDataException("Material list must be sorted by ID before binary search.");
        }

        int left = 0;
        int right = materials.size()-1;

        while(left <= right) {
            int mid = left + (right - left)/2;
            int result = materials.get(mid).getId().compareTo(id);

            if(result == 0) {
                return materials.get(mid);
            }
            if(result > 0) {
                right = mid - 1;
            }
            if(result < 0) {
                left = mid + 1;
            }
        }
        return null;
    }

    private void validate(
        List<FoodMaterial> materials,
        String id
    ) throws InvalidSearchDataException {

        if(materials == null) {
            throw new InvalidSearchDataException("Material list cannot be null");
        }
        
        if(id == null || id.isBlank()) {
            throw new  InvalidSearchDataException("Material ID cannot be null");
        }
    }
    private boolean isSortById(
        List<FoodMaterial> materials
    ){
        for(int i = 0; i < materials.size() - 1; i++) {
            if(materials.get(i).compareTo(materials.get(i+1)) > 0){
                return false;
            }
        }
        return true;
    }
}
