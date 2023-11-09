package patterns.observer;

public class DosageChangeObserver implements DosageObserver {
    private String medicineName;

    public DosageChangeObserver(String medicineName) {
        this.medicineName = medicineName;
    }

    @Override
    public void updateDosage(String newDosage) {
        System.out.println("Dosage of " + medicineName + " changed to: " + newDosage);
    }
}
