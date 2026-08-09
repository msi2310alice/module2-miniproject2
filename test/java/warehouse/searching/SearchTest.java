package warehouse.searching;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import warehouse.exception.InvalidSearchDataException;
import warehouse.exception.UnsortedDataException;
import warehouse.model.FoodMaterial;

class SearchTest {
    private final BinarySearch binarySearch = new BinarySearch();
    private final NameSearch nameSearch = new NameSearch();

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "\t"})
    @DisplayName("Equivalence partitions: null and blank search values are invalid")
    void rejectsInvalidSearchText(String value) {
        List<FoodMaterial> materials = sortedMaterials();

        assertThrows(InvalidSearchDataException.class,
            () -> binarySearch.searchById(materials, value));
        assertThrows(InvalidSearchDataException.class,
            () -> nameSearch.searchByName(materials, value));
    }

    @Test
    @DisplayName("Equivalence partition: null material list is invalid")
    void rejectsNullList() {
        assertThrows(InvalidSearchDataException.class,
            () -> binarySearch.searchById(null, "A"));
        assertThrows(InvalidSearchDataException.class,
            () -> nameSearch.searchByName(null, "milk"));
    }

    @Test
    @DisplayName("Boundary values: binary search finds first, middle and last items")
    void findsItemsAtSearchBoundaries() throws Exception {
        List<FoodMaterial> materials = sortedMaterials();

        assertEquals("A", binarySearch.searchById(materials, "A").getId());
        assertEquals("B", binarySearch.searchById(materials, "B").getId());
        assertEquals("C", binarySearch.searchById(materials, "C").getId());
        assertNull(binarySearch.searchById(materials, "D"));
    }

    @Test
    @DisplayName("Decision table: binary search rejects an unsorted list")
    void rejectsUnsortedList() {
        List<FoodMaterial> unsorted = List.of(material("B", "Butter"), material("A", "Milk"));

        assertThrows(UnsortedDataException.class,
            () -> binarySearch.searchById(unsorted, "A"));
    }

    @Test
    @DisplayName("Equivalence partition: name search is case-insensitive and supports partial text")
    void searchesNamesCaseInsensitively() throws InvalidSearchDataException {
        List<FoodMaterial> results = nameSearch.searchByName(sortedMaterials(), "mIlK");

        assertEquals(List.of("A", "C"), results.stream().map(FoodMaterial::getId).toList());
    }

    @Test
    @DisplayName("Boundary value: a valid search in an empty list returns no result")
    void searchesEmptyList() throws InvalidSearchDataException, UnsortedDataException {
        assertNull(binarySearch.searchById(List.of(), "A"));
        assertEquals(List.of(), nameSearch.searchByName(List.of(), "milk"));
    }

    private static List<FoodMaterial> sortedMaterials() {
        return List.of(material("A", "Fresh Milk"), material("B", "Butter"), material("C", "Milk Powder"));
    }

    private static FoodMaterial material(String id, String name) {
        return new FoodMaterial(id, name, 10, 10, "kg", "Supplier", 1);
    }
}
