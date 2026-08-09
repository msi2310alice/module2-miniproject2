package warehouse.view;

import java.util.List;
import java.util.Scanner;

import warehouse.exception.InvalidSearchDataException;
import warehouse.exception.InvalidSortDataException;
import warehouse.exception.UnsortedDataException;
import warehouse.model.AdditiveMaterial;
import warehouse.model.DairyMaterial;
import warehouse.model.FatMaterial;
import warehouse.model.FoodMaterial;
import warehouse.searching.BinarySearch;
import warehouse.searching.NameSearch;
import warehouse.service.Benchmark;
import warehouse.service.IdGenerator;
import warehouse.service.Inventory;
import warehouse.sorting.BubbleSort;
import warehouse.sorting.InsertionSort;
import warehouse.sorting.SelectionSort;
import warehouse.sorting.SortCriterion;
import warehouse.sorting.SortDirection;
import warehouse.sorting.SortingStrategy;

public class ConsoleMenu {
    private final Inventory inventory;
    private final IdGenerator idGenerator;

    private final BinarySearch binarySearch;
    private final NameSearch nameSearch;
    private final Benchmark benchmark;

    private final Scanner scanner;

    public ConsoleMenu(
        Inventory inventory,
        IdGenerator idGenerator
    ){
        this.inventory = inventory;
        this.idGenerator = idGenerator;
        
        this.binarySearch = new BinarySearch();
        this.nameSearch = new NameSearch();
        this.benchmark = new Benchmark();

        this.scanner = new Scanner(System.in);
    }

    public void start(){
        boolean running = true;
        while(running) {
            showMenu();
            int choice = Integer.parseInt(scanner.nextLine());
            switch(choice){
                case 1: 
                    handleAddMaterial();
                    break;
                case 2: 
                    handleDisplayMaterials();
                    break;
                case 3: 
                    handleGenerateRandom();
                    break;
                case 4: 
                    hanleSort();
                    break;
                case 5: 
                    handleSearchById();
                    break;
                case 6: 
                    handleSearchByName();
                    break;
                case 0: 
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
    private void showMenu(){
        System.out.println("===== WAREHOUSE MENU =====");
        System.out.println("1. Add material");
        System.out.println("2. Display materials");
        System.out.println("3. Generate random materials");
        System.out.println("4. Sort materials");
        System.out.println("5. Search by ID");
        System.out.println("6. Search by name");
        System.out.println("0. Exit");

    }
    private void handleAddMaterial(){
        System.out.println("---------------------------------------");
        System.out.println("Choose material type: ");
        System.out.println("1. Dairy Material");
        System.out.println("2. Fat Material");
        System.out.println("3. Additive Material");

        int typeChoice = Integer.parseInt(scanner.nextLine());

        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Price: ");
        double price = Double.parseDouble(scanner.nextLine());

        System.out.print("Quantity: ");
        double quantity = Double.parseDouble(scanner.nextLine());

        System.out.print("Unit: ");
        String unit = scanner.nextLine();

        System.out.print("Supplier: ");
        String supplier = scanner.nextLine();

        System.out.print("Moisture percent: ");
        double moisturePercent = Double.parseDouble(scanner.nextLine());

        FoodMaterial material = null;

        switch (typeChoice) {

            case 1:
                System.out.print("Protein percent: ");
                double proteinPercent =
                        Double.parseDouble(scanner.nextLine());

                System.out.print("Fat percent: ");
                double dairyFatPercent =
                        Double.parseDouble(scanner.nextLine());

                System.out.print("Lactose percent: ");
                double lactosePercent =
                        Double.parseDouble(scanner.nextLine());

                material = new DairyMaterial(
                        idGenerator.generateDairyId(),
                        name,
                        price,
                        quantity,
                        unit,
                        supplier,
                        moisturePercent,
                        proteinPercent,
                        dairyFatPercent,
                        lactosePercent
                );

                break;

            case 2:
                System.out.print("Fat percent: ");
                double fatPercent =
                        Double.parseDouble(scanner.nextLine());

                System.out.print("Melting point: ");
                double meltingPoint =
                        Double.parseDouble(scanner.nextLine());

                System.out.print("Fat source: ");
                String fatSource = scanner.nextLine();

                material = new FatMaterial(
                        idGenerator.generateFatId(),
                        name,
                        price,
                        quantity,
                        unit,
                        supplier,
                        moisturePercent,
                        fatPercent,
                        meltingPoint,
                        fatSource
                );

                break;

            case 3:
                System.out.print("Function: ");
                String function = scanner.nextLine();

                System.out.print("Recommended dosage: ");
                double recommendedDosage =
                        Double.parseDouble(scanner.nextLine());

                System.out.print("Additive type: ");
                String additiveType = scanner.nextLine();

                material = new AdditiveMaterial(
                        idGenerator.generateAdditiveId(),
                        name,
                        price,
                        quantity,
                        unit,
                        supplier,
                        moisturePercent,
                        function,
                        recommendedDosage,
                        additiveType
                );

                break;

            default:
                System.out.println("Invalid material type.");
                return;
        }

        inventory.addMaterial(material);

        System.out.println("Material added successfully.");
        System.out.println("Generated ID: " + material.getId());
    }
    
    private void handleDisplayMaterials(){
        if(inventory.getMaterials().isEmpty()){
            System.out.println("Inventory is empty");
            return;
        }
        for(FoodMaterial material : inventory.getMaterials()){
            System.out.println(material.toTableRow());
        }
    }

    private void handleGenerateRandom(){
        System.out.print("Enter number of materials to generate: ");

        int amount = Integer.parseInt(scanner.nextLine());

        inventory.generateRandomMaterials(amount,idGenerator);

        System.out.println(
                amount + " materials generated successfully."
        );
    }

    private void hanleSort(){
        System.out.println("Choose sorting algorithm:");
        System.out.println("1. Bubble Sort");
        System.out.println("2. Selection Sort");
        System.out.println("3. Insertion Sort");

        int algorithmChoice = Integer.parseInt(scanner.nextLine());

        SortingStrategy sorter = null;
        
        switch (algorithmChoice) {
            case 1:
                sorter = new BubbleSort();
                break;

            case 2:
                sorter = new SelectionSort();
                break;

            case 3:
                sorter = new InsertionSort();
                break;

            default:
                System.out.println("Invalid sorting algorithm.");
                return;
        }

        System.out.println("---------------------------------------");
        System.out.println("Choose sort criterion:");
        System.out.println("1. ID");
        System.out.println("2. Price");
        System.out.println("3. Quantity");

        int criterionChoice = Integer.parseInt(scanner.nextLine());
        SortCriterion criterion = null;
        switch (criterionChoice) {
            case 1:
                criterion = SortCriterion.ID;
                break;

            case 2:
                criterion = SortCriterion.PRICE;
                break;

            case 3:
                criterion = SortCriterion.QUANTITY;
                break;

            default:
                System.out.println("Invalid sort criterion.");
                return;
        }

        System.out.println("---------------------------------------");
        System.out.println("Choose sort direction:");
        System.out.println("1. Ascending");
        System.out.println("2. Descending");

        int directionChoice = Integer.parseInt(scanner.nextLine());

        SortDirection direction = null;

        switch (directionChoice) {
            case 1:
                direction = SortDirection.ASCENDING;
                break;

            case 2:
                direction = SortDirection.DESCENDING;
                break;

            default:
                System.out.println("Invalid sort direction.");
                return;
        }

        try {
            long executionTime = benchmark.measure(sorter, inventory.getMaterials(), criterion, direction);

            System.out.println("Sort completed.");
            System.out.println("Execution time: " + executionTime + "nanoseconds");

        }catch (InvalidSortDataException exception) {
            System.out.println("Sorting error: " + exception.getMessage());
        }

    }
    private void handleSearchById(){
        System.out.println("Enter material ID: ");
        String id = scanner.nextLine();

        try{
            FoodMaterial result = binarySearch.searchById(inventory.getMaterials(), id);
            if(result == null) {
                System.out.println("Material not found");
            } else {
                System.out.println("Material found");
                System.out.println(result.toTableRow());
            }

        }catch(UnsortedDataException exception){
            System.out.println("Search error: " + exception.getMessage());
        }catch(InvalidSearchDataException exception){
            System.out.println("Search error: " + exception.getMessage());
        }
    }
    private void handleSearchByName(){
        System.out.print("Enter material name keyword: ");
        String keyword = scanner.nextLine();

        try {
            List<FoodMaterial> results = nameSearch.searchByName(
                    inventory.getMaterials(),
                    keyword
            );

            if (results.isEmpty()) {
                System.out.println("No materials found.");
                return;
            }

            System.out.println("Search results:");

            for (FoodMaterial material : results) {
                System.out.println(material.toTableRow());
            }

        } catch (InvalidSearchDataException e) {
            System.out.println(
                    "Search error: " + e.getMessage()
            );
        }
    }
    
}
