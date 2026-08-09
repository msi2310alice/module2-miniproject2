package warehouse.searching;

import java.util.ArrayList;
import java.util.List;

import warehouse.exception.InvalidSearchDataException;
import warehouse.model.FoodMaterial;

public class NameSearch {
    public NameSearch(){
    }
    public List<FoodMaterial> searchByName(
        List<FoodMaterial> materials,
        String keyword
    ) throws InvalidSearchDataException{

        validate(materials, keyword);

        List<FoodMaterial> searchList = new ArrayList<>();
        int n = materials.size();
        for(int i = 0; i < n; i++){
            String nameOfMaterial = materials.get(i).getName();
            boolean result = nameOfMaterial.toLowerCase().contains(keyword.toLowerCase());
            if(result) {
                searchList.add(materials.get(i));
            }
        }
        return searchList;
    }
    private void validate(
        List<FoodMaterial> materials,
        String keyword
    ) throws InvalidSearchDataException{
        if(materials == null) {
            throw new InvalidSearchDataException("Material list cannot be null");
        }
        if(keyword == null || keyword.isBlank()){
            throw new InvalidSearchDataException("Search keyword cannot be null or blank");
        }
    }
}
