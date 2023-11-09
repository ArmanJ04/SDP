<!-- Title Section -->
<h1 align="center">
  Medicine System
</h1>

<!-- Badges Section -->
<p align="center">
  <img src="https://img.shields.io/badge/License-MIT-blue.svg" alt="License">
  <!-- Add more badges as needed -->
</p>

<!-- Introduction Section -->
## Introduction

Welcome to the Medicine System project! This Java-based system showcases the implementation of various design patterns to create a flexible and modular solution for representing medical products. The project emphasizes the application of essential design principles to achieve a well-organized and extensible codebase.

<!-- Project Aim Section -->
## Project Aim

The primary aim of the Medicine System project is to demonstrate the practical application of popular design patterns in real-world scenarios. By creating a medicine system with dynamic dosage calculation, feature addition, dosage observation, and legacy system integration, this project serves as an educational resource for software developers seeking to understand and implement design patterns effectively.

<!-- Design Patterns Section -->
## Design Patterns Used

### 1. Singleton Pattern

- **Description:** The Singleton pattern ensures that there is only one instance of the `Medicine` class throughout the application.
- **Usage in Project:** The `Medicine` class is designed as a singleton, guaranteeing a single representation of a medicine in the system.

### 2. Strategy Pattern

- **Description:** The Strategy pattern defines a family of algorithms, encapsulates each algorithm, and makes them interchangeable.
- **Usage in Project:** The `DosageStrategy` interface and its implementations (`LowDosageStrategy`, `MediumDosageStrategy`, `HighDosageStrategy`) allow dynamic calculation of medicine dosage based on different strategies.

### 3. Decorator Pattern

- **Description:** The Decorator pattern attaches additional responsibilities to an object dynamically.
- **Usage in Project:** The `MedicineDecorator` interface and its implementation (`FlavorDecorator`) enable the dynamic addition of features, such as flavor, to a medicine.

### 4. Observer Pattern

- **Description:** The Observer pattern defines a one-to-many dependency between objects so that when one object changes state, all its dependents are notified and updated automatically.
- **Usage in Project:** The `DosageObserver` interface and its implementation (`DosageChangeObserver`) provide a mechanism to observe and react to changes in the dosage of a medicine.

### 5. Adapter Pattern

- **Description:** The Adapter pattern allows the interface of an existing class to be used as another interface.
- **Usage in Project:** The `LegacyMedicineSystem` interface and its implementations (`LegacyMedicineSystemImpl`, `MedicineAdapter`) demonstrate the adaptation of the system to work with a legacy interface.

### 6. Factory Pattern

- **Description:** The Factory pattern defines an interface for creating an object but leaves the choice of its type to the subclasses.
- **Usage in Project:** The `MedicineFactory` class serves as a factory for creating instances of the `Medicine` class with different dosage strategies.

<!-- Getting Started Section -->
## Getting Started

### Prerequisites

- Java Development Kit (JDK) installed
- Any Java IDE (e.g., IntelliJ, Eclipse) or a text editor
- Git (optional)

### Running the Program

1. Clone the repository:

    ```bash
    git clone https://github.com/your-username/medicine-system.git
    ```

2. Open the project in your preferred Java IDE.

3. Run the `Main` class to see the example usage of the medicine system.
![dia](https://github.com/ArmanJ04/SDP/assets/57132784/8df95ff7-d736-44ec-b2f1-0b5dfb95e53e)
<!-- Example Usage Section -->
## Example Usage

To demonstrate the usage of each design pattern, consider the following example:

```java
// Create a medicine instance with a low dosage strategy
Medicine medicine = MedicineFactory.createMedicine("Aspirin", new LowDosageStrategy());

// Add dosage observers
DosageObserver observer1 = new DosageChangeObserver("Observer1");
DosageObserver observer2 = new DosageChangeObserver("Observer2");

medicine.addObserver(observer1);
medicine.addObserver(observer2);

// Display medicine information
System.out.println(medicine.getName());  // Output: Aspirin
System.out.println(medicine.getDosage());  // Output: Low dosage

// Change dosage strategy and notify observers
medicine.setDosageStrategy(new MediumDosageStrategy());

// Add a flavor decorator
MedicineDecorator flavorDecorator = new FlavorDecorator("Mint");
medicine.addDecorator(flavorDecorator);

// Display updated medicine information
System.out.println(medicine.getDosage());  // Output: Medium dosage, Flavor: Mint


