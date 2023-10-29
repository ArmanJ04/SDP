import java.util.Scanner;

interface Drink {
    double cost();
}

class SimpleTea implements Drink {
    public double cost() {
        return 3.0;
    }
}

class SimpleCoffee implements Drink {
    public double cost() {
        return 5.0;
    }
}

class MilkDecorator implements Drink {
    private Drink drink;

    public MilkDecorator(Drink drink) {
        this.drink = drink;
    }

    public double cost() {
        return drink.cost() + 1.5;
    }
}

class SugarDecorator implements Drink {
    private Drink drink;

    public SugarDecorator(Drink drink) {
        this.drink = drink;
    }

    public double cost() {
        return drink.cost() + 1.0;
    }
}

public class ASS2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose your drink (1 for Tea, 2 for Coffee):");
        int choice = scanner.nextInt();
        Drink drink;

        if (choice == 1) {
            drink = new SimpleTea();
        } else if (choice == 2) {
            drink = new SimpleCoffee();
        } else {
            System.out.println("Invalid choice. Defaulting to Tea.");
            drink = new SimpleTea();
        }

        System.out.println("Do you want to add milk? (1 for yes, 0 for no):");
        int addMilk = scanner.nextInt();
        if (addMilk == 1) {
            drink = new MilkDecorator(drink);
        }

        System.out.println("Do you want to add sugar? (1 for yes, 0 for no):");
        int addSugar = scanner.nextInt();
        if (addSugar == 1) {
            drink = new SugarDecorator(drink);
        }

        System.out.println("Your Beverage: ");
        System.out.println("Cost: $" + drink.cost());
    }
}
