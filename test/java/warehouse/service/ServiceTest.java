package warehouse.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import warehouse.model.FoodMaterial;

class ServiceTest {

    @Test
    @DisplayName("Boundary values: each ID sequence starts at 1 and increments independently")
    void generatesIndependentSequentialIds() {
        IdGenerator generator = new IdGenerator();

        assertEquals("DA00000001", generator.generateDairyId());
        assertEquals("DA00000002", generator.generateDairyId());
        assertEquals("FA00000001", generator.generateFatId());
        assertEquals("AD00000001", generator.generateAdditiveId());
    }

    @Test
    @DisplayName("Boundary values: generating zero and one material")
    void generatesBoundaryAmounts() {
        Inventory inventory = new Inventory();
        IdGenerator generator = new IdGenerator();

        inventory.generateRandomMaterials(0, generator);
        assertEquals(0, inventory.size());

        inventory.generateRandomMaterials(1, generator);
        assertEquals(1, inventory.size());
        assertTrue(inventory.getMaterials().get(0).getId().matches("(DA|FA|AD)\\d{8}"));
    }

    @Test
    @DisplayName("Equivalence partition: adding a valid material increases inventory size")
    void addsMaterial() {
        Inventory inventory = new Inventory(new ArrayList<>());
        FoodMaterial material = new FoodMaterial("A", "Milk", 10, 2, "kg", "S", 1);

        inventory.addMaterial(material);

        assertEquals(1, inventory.size());
        assertEquals(material, inventory.getMaterials().get(0));
    }
}
