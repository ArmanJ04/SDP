package patterns.factory;

import patterns.strategy.DosageStrategy;
import patterns.singleton.Medicine;

public class MedicineFactory {
    public static Medicine createMedicine(String name, DosageStrategy dosageStrategy) {
        return new Medicine(name, dosageStrategy);
    }
}
