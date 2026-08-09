package warehouse.model;

public class DairyMaterial extends FoodMaterial{
    private double proteinPercent;
    private double fatPercent;
    private double lactosePercent;

    public DairyMaterial(
        String id, 
        String name,
        double price,
        double quantity,
        String unit,
        String supplier,
        double moisturePercent,
        double proteinPercent,
        double fatPercent,
        double lactosePercent){

        super(id, name, price, quantity, 
            unit, supplier, moisturePercent);
        
        this.proteinPercent = proteinPercent;
        this.fatPercent = fatPercent;
        this.lactosePercent = lactosePercent;
    }

    public double getProteinPercent(){
        return this.proteinPercent;
    }
    public double getFatPercent(){
        return this.fatPercent;
    }
    public double getLactosePercent(){
        return this.lactosePercent;
    }

    public void setProteinPercent(double value){
        this.proteinPercent = value;
    }
    public void setFatPercent(double value){
        this.fatPercent = value;
    }
    public void setLactosePercent(double value){
        this.lactosePercent = value;
    }

    @Override
    public String toString(){
        return super.toString() 
                + ", proteinPercent:"
                + proteinPercent
                + ", fatPercent: "
                + fatPercent
                + ", lactosePercent: "
                + lactosePercent;
    }

    @Override
    public String toTableRow(){
        return super.toTableRow() 
                + String.format(" %-10.2f %-10.2f %-10.2f",
                    proteinPercent, fatPercent, lactosePercent);
    }
}
