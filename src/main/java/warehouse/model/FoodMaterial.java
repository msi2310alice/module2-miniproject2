package warehouse.model;

public class FoodMaterial implements Comparable<FoodMaterial> {
    private String id;
    private String name;
    private double price;
    private double quantity;
    private String unit;
    private String supplier;
    private double moisturePercent;

    public FoodMaterial(
        String id, 
        String name,
        double price,
        double quantity,
        String unit,
        String supplier,
        double moisturePercent
    ){
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.unit = unit;
        this.supplier = supplier;
        this.moisturePercent = moisturePercent;
    }

    public String getId(){
        return this.id;
    }
    public String getName(){
        return this.name;
    }
    public double getPrice(){
        return this.price;
    }
    public double getQuantity(){
        return this.quantity;
    }
    public String getUnit(){
        return this.unit;
    }
    public String getSupplier(){
        return this.supplier;
    }
    public double getMoisturePercent(){
        return this.moisturePercent;
    }
    
    public void setName(String name){
        this.name = name;
    }
    public void setPrice(double price){
        this.price = price;
    }
    public void setQuantity(double quantity){
        this.quantity = quantity;
    }
    public void setUnit(String unit){
        this.unit = unit;
    }
    public void setSupplier(String supplier){
        this.supplier = supplier;
    }
    public void setMoisturePercent(double moisturePercent){
        this.moisturePercent = moisturePercent;
    }

    public String toString(){
        return "ID: "
            + id
            + ",name: "
            + name
            + ",price: "
            + price
            + ",quantity: "
            + quantity
            + ",unit: "
            + unit
            + ",supplier: "
            + supplier
            + ", moisturePercent: "
            + moisturePercent;
    }
    public String toTableRow(){
        return String.format("%-10s %-20s %-10.2f %-10.2f %-5s %-10s %-10.2f",
            id,  name, price, quantity, unit, supplier, moisturePercent
        );

    }

    @Override
    public int compareTo(FoodMaterial other){
        return this.id.compareTo(other.getId());
    }
}
