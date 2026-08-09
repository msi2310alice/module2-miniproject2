package warehouse.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import warehouse.model.AdditiveMaterial;
import warehouse.model.DairyMaterial;
import warehouse.model.FatMaterial;
import warehouse.model.FoodMaterial;

public class Inventory {
    private List<FoodMaterial> materials;

    public Inventory(){
        materials = new ArrayList<>();
    }
    public Inventory(List<FoodMaterial> materials){
        this.materials = materials;
    }
    
    public void addMaterial(FoodMaterial material){
        materials.add(material);
    }
    public List<FoodMaterial> getMaterials(){
        return materials;
    }
    public int size(){
        return materials.size();
    }

    public void generateRandomMaterials(
        int amount, 
        IdGenerator idGenerator
    ){

        Random random = new Random();
        for(int i = 0; i < amount; i++){
            int materialType = random.nextInt(3);
            switch(materialType){
                case 0:
                    materials.add(new DairyMaterial(
                        idGenerator.generateDairyId(),
                        "Dairy Material " + (i + 1),
                        random.nextDouble(50,500),
                        random.nextDouble(10, 1000),
                        "kg",
                        "Supplier " + random.nextInt(1, 6),
                        random.nextDouble(1, 5),
                        random.nextDouble(20, 40), 
                        random.nextDouble(0, 40),
                        random.nextDouble(30, 60)
                    ));
                    break;
                case 1: 
                    materials.add(new FatMaterial(
                        idGenerator.generateFatId(),
                        "Fat Material " + (i + 1),
                        random.nextDouble(50, 500),
                        random.nextDouble(10, 1000),
                        "kg",
                        "Supplier " + random.nextInt(1, 6),
                        random.nextDouble(0, 5),
                        random.nextDouble(80, 100),
                        random.nextDouble(20, 40),
                        "Vegetable"
                    ));
                    break;
                case 2:
                    materials.add(new AdditiveMaterial(
                        idGenerator.generateAdditiveId(),
                        "Additive Material " + (i + 1),
                        random.nextDouble(50, 500),
                        random.nextDouble(1, 100),
                        "kg",
                        "Supplier " + random.nextInt(1, 6),
                        random.nextDouble(0, 5),
                        "Stabilizing",
                        random.nextDouble(0.1, 5),
                        "Stabilizer"
                ));
                break;
                
            }
        }
    }
}
