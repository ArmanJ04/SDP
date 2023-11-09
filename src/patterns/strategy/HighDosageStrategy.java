// patterns.strategy
package patterns.strategy;

public class HighDosageStrategy implements DosageStrategy {
    public String calculateDosage() {
        return "High dosage";
    }
}
