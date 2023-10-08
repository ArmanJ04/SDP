import java.util.Scanner;

interface Beverage {
    double cost();
}

class SimpleTea implements Beverage {
    public double cost() {
        return 3.0;
    }
}

class SimpleCoffee implements Beverage {
    public double cost() {
        return 5.0;
    }
}

class MilkDecorator implements Beverage {
    private Beverage beverage;

    public MilkDecorator(Beverage beverage) {
        this.beverage = beverage;
    }

    public double cost() {
        return beverage.cost() + 1.5;
    }
}

class SugarDecorator implements Beverage {
    private Beverage beverage;

    public SugarDecorator(Beverage beverage) {
        this.beverage = beverage;
    }

    public double cost() {
        return beverage.cost() + 1.0;
    }
}

public class ASS2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose your beverage (1 for Tea, 2 for Coffee):");
        int choice = scanner.nextInt();
        Beverage beverage;

        if (choice == 1) {
            beverage = new SimpleTea();
        } else if (choice == 2) {
            beverage = new SimpleCoffee();
        } else {
            System.out.println("Invalid choice. Defaulting to Tea.");
            beverage = new SimpleTea();
        }

        System.out.println("Do you want to add milk? (1 for yes, 0 for no):");
        int addMilk = scanner.nextInt();
        if (addMilk == 1) {
            beverage = new MilkDecorator(beverage);
        }

        System.out.println("Do you want to add sugar? (1 for yes, 0 for no):");
        int addSugar = scanner.nextInt();
        if (addSugar == 1) {
            beverage = new SugarDecorator(beverage);
        }

        System.out.println("Your Beverage: ");
        System.out.println("Cost: $" + beverage.cost());
    }
}
