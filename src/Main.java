import patterns.factory.MedicineFactory;
import patterns.observer.DosageChangeObserver;
import patterns.observer.DosageObserver;
import patterns.strategy.LowDosageStrategy;
import patterns.strategy.MediumDosageStrategy;
import patterns.decorator.FlavorDecorator;
import patterns.adapter.LegacyMedicineSystem;
import patterns.adapter.MedicineAdapter;

public class Main {
    public static void main(String[] args) {
        patterns.singleton.Medicine medicine = MedicineFactory.createMedicine("Aspirin", new LowDosageStrategy());

        DosageObserver observer1 = new DosageChangeObserver("Observer1");
        DosageObserver observer2 = new DosageChangeObserver("Observer2");

        medicine.addObserver(observer1);
        medicine.addObserver(observer2);

        System.out.println(medicine.getName());
        System.out.println(medicine.getDosage());

        medicine.setDosageStrategy(new MediumDosageStrategy());

        patterns.decorator.MedicineDecorator flavorDecorator = new FlavorDecorator("Mint");
        medicine.addDecorator(flavorDecorator);

        System.out.println(medicine.getDosage());

        LegacyMedicineSystem legacySystem = new MedicineAdapter(medicine);
        legacySystem.displayInfo();
    }
}
