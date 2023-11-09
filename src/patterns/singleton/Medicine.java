package patterns.singleton;

import java.util.ArrayList;
import java.util.List;

public class Medicine {
    private static Medicine instance;
    private String name;
    private patterns.strategy.DosageStrategy dosageStrategy;
    private List<patterns.decorator.MedicineDecorator> decorators;
    private List<patterns.observer.DosageObserver> observers;

    public Medicine(String name, patterns.strategy.DosageStrategy dosageStrategy) {
        this.name = name;
        this.dosageStrategy = dosageStrategy;
        this.decorators = new ArrayList<>();
        this.observers = new ArrayList<>();
    }

    public static Medicine getInstance(String name, patterns.strategy.DosageStrategy dosageStrategy) {
        if (instance == null) {
            instance = new Medicine(name, dosageStrategy);
        }
        return instance;
    }

    public void addDecorator(patterns.decorator.MedicineDecorator decorator) {
        decorators.add(decorator);
    }

    public void addObserver(patterns.observer.DosageObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(patterns.observer.DosageObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        String newDosage = dosageStrategy.calculateDosage();
        for (patterns.observer.DosageObserver observer : observers) {
            observer.updateDosage(newDosage);
        }
    }

    public void setDosageStrategy(patterns.strategy.DosageStrategy dosageStrategy) {
        this.dosageStrategy = dosageStrategy;
        notifyObservers(); // Notify observers when dosage changes
    }

    public String getName() {
        return name;
    }

    public String getDosage() {
        StringBuilder dosageInfo = new StringBuilder(dosageStrategy.calculateDosage());

        for (patterns.decorator.MedicineDecorator decorator : decorators) {
            dosageInfo.append(", ").append(decorator.addFeature());
        }

        return dosageInfo.toString();
    }

    // Factory pattern
    public static class MedicineFactory {
        public static Medicine createMedicine(String name, patterns.strategy.DosageStrategy dosageStrategy) {
            return new Medicine(name, dosageStrategy);
        }
    }
}
