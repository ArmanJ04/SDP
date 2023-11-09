package patterns.decorator;

public class FlavorDecorator implements MedicineDecorator {
    private String flavor;

    public FlavorDecorator(String flavor) {
        this.flavor = flavor;
    }

    public String addFeature() {
        return "Flavor: " + flavor;
    }
}
