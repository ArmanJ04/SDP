package patterns.strategy;

public class LowDosageStrategy implements DosageStrategy {
    public String calculateDosage() {
        return "Low dosage";
    }
}
