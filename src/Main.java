import java.util.Scanner;

class PaymentProcessorSingleton {
    private static PaymentProcessorSingleton instance = null;
    private PaymentStrategy paymentStrategy;

    private PaymentProcessorSingleton() {
        initialize();
    }

    public static PaymentProcessorSingleton getInstance() {
        if (instance == null) {
            instance = new PaymentProcessorSingleton();
        }
        return instance;
    }

    private void initialize() {
        paymentStrategy = null;
    }

    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public String processPayment(double amount) {
        if (paymentStrategy != null) {
            return paymentStrategy.processPayment(amount);
        } else {
            throw new IllegalArgumentException("Payment strategy is not set");
        }
    }
}

interface PaymentStrategy {
    String processPayment(double amount);
}

class CreditCardPayment implements PaymentStrategy {
    @Override
    public String processPayment(double amount) {
        int authorizationCode = authorizeCreditCard(amount);
        String captureStatus = capturePayment(amount);
        return String.format("Paid $%.2f via Credit Card (Authorization Code: %d, Capture Status: %s)", amount, authorizationCode, captureStatus);
    }

    private int authorizeCreditCard(double amount) {
        return (int) (Math.random() * 9000) + 1000;
    }

    private String capturePayment(double amount) {
        return "Success";
    }
}

class PayPalPayment implements PaymentStrategy {
    @Override
    public String processPayment(double amount) {
        String paymentId = initiatePayPalPayment(amount);
        String paymentStatus = checkPayPalPaymentStatus(paymentId);
        return String.format("Paid $%.2f via PayPal (Payment ID: %s, Status: %s)", amount, paymentId, paymentStatus);
    }

    private String initiatePayPalPayment(double amount) {
        return "PAYPAL-" + (int) (Math.random() * 90000) + 10000;
    }

    private String checkPayPalPaymentStatus(String paymentId) {
        return "Completed";
    }
}

public class Main {
    public static void main(String[] args) {
        PaymentProcessorSingleton paymentProcessor = PaymentProcessorSingleton.getInstance();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the payment amount: ");
        double amount = scanner.nextDouble();

        System.out.println("Select a payment method:");
        System.out.println("1. Credit Card");
        System.out.println("2. PayPal");
        System.out.print("Enter 1 or 2: ");
        int choice = scanner.nextInt();

        if (choice == 1) {
            paymentProcessor.setPaymentStrategy(new CreditCardPayment());
        } else if (choice == 2) {
            paymentProcessor.setPaymentStrategy(new PayPalPayment());
        } else {
            System.out.println("Invalid choice. Please select 1 or 2.");
            System.exit(1);
        }

        String paymentResult = paymentProcessor.processPayment(amount);
        System.out.println(paymentResult);
    }
}
