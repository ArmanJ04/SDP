package patterns.adapter;

public class LegacyMedicineSystemImpl implements LegacyMedicineSystem {
    private String info;

    public LegacyMedicineSystemImpl(String info) {
        this.info = info;
    }

    @Override
    public void displayInfo() {
        System.out.println("Legacy Medicine System: " + info);
    }
}
