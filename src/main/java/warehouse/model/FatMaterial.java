package warehouse.model;

public class FatMaterial extends FoodMaterial {
    private double fatPercent;
    private double meltingPoint;
    private String fatSource;

    public FatMaterial(String id, String name, double price,
                    double quantity, String unit, String supplier,
                    double moisturePercent, double fatPercent, 
                    double meltingPoint, String fatSource){
        
        super(id, name, price, quantity, unit, supplier, moisturePercent);
        this.fatPercent = fatPercent;
        this.meltingPoint = meltingPoint;
        this.fatSource = fatSource;
    }
    
    public double getFatPercent(){
        return this.fatPercent;
    }
    public double getMeltingPoint(){
        return this.meltingPoint;
    }
    public String getFatSource(){
        return this.fatSource;
    }

    public void setFatPercent(double value){
        this.fatPercent = value;
    }
    public void setMeltingPoint(double value){
        this.meltingPoint = value;
    }
    public void setFatSource(String value){
        this.fatSource = value;
    }

    @Override
    public String toString(){
        return super.toString() 
                + ", fatPercent: "
                + fatPercent
                + ", meltingPoint: "
                + meltingPoint
                + ", fatSource: "
                + fatSource;
    }

    @Override
    public String toTableRow(){
        return super.toTableRow()
                + String.format(" %-20.2f %-20.2f %-20s",
                    fatPercent, meltingPoint, fatSource);
    }
}
