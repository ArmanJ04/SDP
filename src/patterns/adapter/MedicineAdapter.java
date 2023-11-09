package patterns.adapter;

public class MedicineAdapter implements patterns.adapter.LegacyMedicineSystem {
    private patterns.singleton.Medicine medicine;

    public MedicineAdapter(patterns.singleton.Medicine medicine) {
        this.medicine = medicine;
    }
    @Override
    public void displayInfo() {
        System.out.println("Adapter: " + medicine.getName() + ", " + medicine.getDosage());
    }
}