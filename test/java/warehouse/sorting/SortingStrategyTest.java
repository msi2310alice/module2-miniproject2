package warehouse.sorting;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import warehouse.exception.InvalidSortDataException;
import warehouse.model.FoodMaterial;

class SortingStrategyTest {

    static Stream<Arguments> decisionTableCases() {
        List<Supplier<SortingStrategy>> algorithms = List.of(
            BubbleSort::new, SelectionSort::new, InsertionSort::new
        );
        List<Arguments> cases = new ArrayList<>();
        for (Supplier<SortingStrategy> algorithm : algorithms) {
            cases.add(Arguments.of(algorithm, SortCriterion.ID, SortDirection.ASCENDING,
                List.of("DA00000001", "FA00000001", "FA00000002")));
            cases.add(Arguments.of(algorithm, SortCriterion.ID, SortDirection.DESCENDING,
                List.of("FA00000002", "FA00000001", "DA00000001")));
            cases.add(Arguments.of(algorithm, SortCriterion.PRICE, SortDirection.ASCENDING,
                List.of("FA00000001", "FA00000002", "DA00000001")));
            cases.add(Arguments.of(algorithm, SortCriterion.PRICE, SortDirection.DESCENDING,
                List.of("DA00000001", "FA00000002", "FA00000001")));
            cases.add(Arguments.of(algorithm, SortCriterion.QUANTITY, SortDirection.ASCENDING,
                List.of("FA00000002", "DA00000001", "FA00000001")));
            cases.add(Arguments.of(algorithm, SortCriterion.QUANTITY, SortDirection.DESCENDING,
                List.of("FA00000001", "DA00000001", "FA00000002")));
        }
        return cases.stream();
    }

    @ParameterizedTest(name = "{0}, {1}, {2}")
    @MethodSource("decisionTableCases")
    @DisplayName("Decision table: every algorithm supports every criterion and direction")
    void sortsUsingEveryDecisionCombination(
        Supplier<SortingStrategy> factory,
        SortCriterion criterion,
        SortDirection direction,
        List<String> expectedIds
    ) throws InvalidSortDataException {
        List<FoodMaterial> materials = sampleMaterials();

        factory.get().sort(materials, criterion, direction);

        assertEquals(expectedIds, materials.stream().map(FoodMaterial::getId).toList());
    }

    static Stream<Supplier<SortingStrategy>> algorithms() {
        return Stream.of(BubbleSort::new, SelectionSort::new, InsertionSort::new);
    }

    @ParameterizedTest
    @MethodSource("algorithms")
    @DisplayName("Equivalence partition: null list is rejected")
    void rejectsNullList(Supplier<SortingStrategy> factory) {
        assertThrows(InvalidSortDataException.class,
            () -> factory.get().sort(null, SortCriterion.ID, SortDirection.ASCENDING));
    }

    @ParameterizedTest
    @MethodSource("algorithms")
    @DisplayName("Equivalence partition: null criterion is rejected")
    void rejectsNullCriterion(Supplier<SortingStrategy> factory) {
        assertThrows(InvalidSortDataException.class,
            () -> factory.get().sort(new ArrayList<>(), null, SortDirection.ASCENDING));
    }

    @ParameterizedTest
    @MethodSource("algorithms")
    @DisplayName("Equivalence partition: null direction is rejected")
    void rejectsNullDirection(Supplier<SortingStrategy> factory) {
        assertThrows(InvalidSortDataException.class,
            () -> factory.get().sort(new ArrayList<>(), SortCriterion.ID, null));
    }

    @Test
    @DisplayName("Boundary values: zero, one and two elements")
    void handlesBoundaryListSizes() throws InvalidSortDataException {
        SortingStrategy sorter = new BubbleSort();
        List<FoodMaterial> empty = new ArrayList<>();
        List<FoodMaterial> one = new ArrayList<>(List.of(material("A", 1, 1)));
        List<FoodMaterial> two = new ArrayList<>(List.of(
            material("B", 1, 1), material("A", 1, 1)));

        sorter.sort(empty, SortCriterion.ID, SortDirection.ASCENDING);
        sorter.sort(one, SortCriterion.ID, SortDirection.ASCENDING);
        sorter.sort(two, SortCriterion.ID, SortDirection.ASCENDING);

        assertEquals(0, empty.size());
        assertEquals(List.of("A"), one.stream().map(FoodMaterial::getId).toList());
        assertEquals(List.of("A", "B"), two.stream().map(FoodMaterial::getId).toList());
    }

    private static List<FoodMaterial> sampleMaterials() {
        return new ArrayList<>(List.of(
            material("FA00000002", 20, 5),
            material("DA00000001", 30, 10),
            material("FA00000001", 10, 15)
        ));
    }

    private static FoodMaterial material(String id, double price, double quantity) {
        return new FoodMaterial(id, "Material " + id, price, quantity, "kg", "Supplier", 1);
    }
}
