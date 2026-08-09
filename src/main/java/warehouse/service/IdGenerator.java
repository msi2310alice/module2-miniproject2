package warehouse.service;

public class IdGenerator {
    private int dairySequence;
    private int fatSequence;
    private int additiveSequence;

    public IdGenerator(){
        this.dairySequence = 0;
        this.fatSequence = 0;
        this.additiveSequence = 0;
    }

    public String generateDairyId(){
        dairySequence++;
        return "DA" + String.format("%08d",dairySequence);
    } 
    public String generateFatId(){
        fatSequence++;
        return "FA" + String.format("%08d", fatSequence);
    }
    public String generateAdditiveId(){
        additiveSequence++;
        return "AD" + String.format("%08d", additiveSequence);
    }
}
