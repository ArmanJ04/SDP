import java.util.ArrayList;
import java.util.List;

// Strategy Pattern
interface MedicationStrategy {
    void administerMedication(Patient patient);
}

class OralMedication implements MedicationStrategy {
    @Override
    public void administerMedication(Patient patient) {
        System.out.println("Administering oral medication to " + patient.getName());
    }
}

class InjectionMedication implements MedicationStrategy {
    @Override
    public void administerMedication(Patient patient) {
        System.out.println("Administering injection medication to " + patient.getName());
    }
}

// Singleton Pattern
class Hospital {
    private static Hospital instance;
    private List<Doctor> doctors;
    private List<Nurse> nurses;
    private Pharmacy pharmacy;

    private Hospital() {
        doctors = new ArrayList<>();
        nurses = new ArrayList<>();
        pharmacy = new Pharmacy();
    }

    public static Hospital getInstance() {
        if (instance == null) {
            instance = new Hospital();
        }
        return instance;
    }

    public void addDoctor(Doctor doctor) {
        doctors.add(doctor);
    }

    public void addNurse(Nurse nurse) {
        nurses.add(nurse);
    }

    public void prescribeMedication(Doctor doctor, Patient patient, MedicationStrategy medication) {
        System.out.println(doctor.getName() + " prescribes medication for " + patient.getName());
        pharmacy.administerMedication(patient, medication);
    }

    public void notifyNurses(String message) {
        for (Nurse nurse : nurses) {
            nurse.update(message);
        }
    }
}

class Patient {
    private String name;

    public Patient(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

// Observer Pattern
interface MedicationObserver {
    void update(String message);
}

class Nurse implements MedicationObserver {
    private String name;

    public Nurse(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        System.out.println(name + " received a medication update: " + message);
    }
}

class Doctor {
    private String name;

    public Doctor(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

// Adapter Pattern
class MedicationAdapter {
    private MedicationStrategy medication;

    public MedicationAdapter(MedicationStrategy medication) {
        this.medication = medication;
    }

    public void administerMedication(Patient patient) {
        medication.administerMedication(patient);
    }
}

// Decorator Pattern
class PainkillerDecorator implements MedicationStrategy {
    private MedicationStrategy medication;

    public PainkillerDecorator(MedicationStrategy medication) {
        this.medication = medication;
    }

    @Override
    public void administerMedication(Patient patient) {
        medication.administerMedication(patient);
        System.out.println("Adding painkiller to the medication for " + patient.getName());
    }
}

// Factory Pattern
class MedicationFactory {
    public MedicationStrategy createMedication(String type) {
        if (type.equalsIgnoreCase("oral")) {
            return new OralMedication();
        } else if (type.equalsIgnoreCase("injection")) {
            return new InjectionMedication();
        } else {
            return null;
        }
    }
}

class Pharmacy {
    public void administerMedication(Patient patient, MedicationStrategy medication) {
        System.out.println("Pharmacy administers medication to " + patient.getName());
        medication.administerMedication(patient);
    }
}

public class Final {
    public static void main(String[] args) {
        Hospital hospital = Hospital.getInstance();

        Patient patient1 = new Patient("Patient 1");
        Patient patient2 = new Patient("Patient 2");
        Doctor doctor1 = new Doctor("Doctor Smith");
        Doctor doctor2 = new Doctor("Doctor Johnson");
        Nurse nurse1 = new Nurse("Nurse Alice");
        Nurse nurse2 = new Nurse("Nurse Bob");

        hospital.addDoctor(doctor1);
        hospital.addDoctor(doctor2);
        hospital.addNurse(nurse1);
        hospital.addNurse(nurse2);

        MedicationFactory medicationFactory = new MedicationFactory();
        MedicationStrategy oralMedication = medicationFactory.createMedication("oral");
        MedicationStrategy injectionMedication = medicationFactory.createMedication("injection");
        MedicationStrategy oralMedicationWithPainkiller = new PainkillerDecorator(oralMedication);

        hospital.prescribeMedication(doctor1, patient1, oralMedication);
        hospital.prescribeMedication(doctor2, patient2, injectionMedication);
        hospital.prescribeMedication(doctor2, patient1, oralMedicationWithPainkiller);
    }
}
