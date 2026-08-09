package warehouse.model;

public class AdditiveMaterial extends FoodMaterial {
    private String function;
    private double recommendedDosage;
    private String additiveType;

    public AdditiveMaterial(
            String id, String name, double price, double quantity,
            String unit, String supplier, double moisturePercent,
            String function, double recommendeDosage, String additiveType) {

        
        super(id, name, price, quantity, unit, supplier, moisturePercent);
        this.function = function;
        this.recommendedDosage = recommendeDosage;
        this.additiveType = additiveType;
    }
    public String getFunction(){
        return this.function;
    }
    public double getRecommendedDosage(){
        return this.recommendedDosage;
    }
    public String getAdditiveType(){
        return this.additiveType;
    }

    public void setFunction(String value){
        this.function = value;
    }
    public void setRecommendedDosage(double value){
        this.recommendedDosage = value;
    }
    public void setAdditiveType(String value){
        this.additiveType = value;
    }

    @Override 
    public String toString(){
        return super.toString() 
                + ", function: "
                + function
                + ", recommendedDosage: "
                + recommendedDosage
                + ", additiveType: "
                + additiveType;
    }

    @Override
    public String toTableRow(){
        return super.toTableRow() 
                + String.format(" %-10s %-10.2f %-10s",
                    function, recommendedDosage, additiveType);
    }
    
}
