import java.util.ArrayList;
import java.util.List;

// Strategy Pattern
interface MedicationStrategy {
    void administerMedication();
}

class OralMedication implements MedicationStrategy {
    @Override
    public void administerMedication() {
        System.out.println("Administering oral medication.");
    }
}

class InjectionMedication implements MedicationStrategy {
    @Override
    public void administerMedication() {
        System.out.println("Administering injection medication.");
    }
}

// Singleton Pattern
class Hospital {
    private static Hospital instance;
    private List<Patient> patients;
    private List<Doctor> doctors;
    private List<Nurse> nurses;  // List of nurses
    private Pharmacy pharmacy;

    private Hospital() {
        patients = new ArrayList<>();
        doctors = new ArrayList<>();
        nurses = new ArrayList<>();  // Initialize the list of nurses
        pharmacy = new Pharmacy();
    }

    public static Hospital getInstance() {
        if (instance == null) {
            instance = new Hospital();
        }
        return instance;
    }

    public void admitPatient(Patient patient) {
        patients.add(patient);
    }

    public void assignDoctor(Doctor doctor, Patient patient) {
        doctors.add(doctor);
        patient.setDoctor(doctor);
    }

    public void prescribeMedication(Doctor doctor, Patient patient, MedicationStrategy medication) {
        System.out.println(doctor.getName() + " prescribes medication for " + patient.getName());
        pharmacy.administerMedication(patient, medication);
    }

    // New method to notify nurses
    public void notifyNurses(String message) {
        for (Nurse nurse : nurses) {
            nurse.update(message);
        }
    }

    public void addNurse(Nurse nurse) {
        nurses.add(nurse);
    }
}


class Patient {
    private String name;
    private Doctor doctor;

    public Patient(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
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
class MedicationAdapter implements MedicationStrategy {
    private MedicationStrategy medication;

    public MedicationAdapter(MedicationStrategy medication) {
        this.medication = medication;
    }

    @Override
    public void administerMedication() {
        medication.administerMedication();
    }
}

// Decorator Pattern
class PainkillerDecorator implements MedicationStrategy {
    private MedicationStrategy medication;

    public PainkillerDecorator(MedicationStrategy medication) {
        this.medication = medication;
    }

    @Override
    public void administerMedication() {
        medication.administerMedication();
        System.out.println("Adding painkiller.");
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

class Pharmacy {
    public void administerMedication(Patient patient, MedicationStrategy medication) {
        System.out.println("Pharmacy administers medication to " + patient.getName());
        medication.administerMedication();
    }
}

// Factory Pattern
class MedicationFactory {
    public MedicationStrategy createMedication(String type) {
        if (type.equalsIgnoreCase("oral")) {
            return new MedicationAdapter(new OralMedication());
        } else if (type.equalsIgnoreCase("injection")) {
            return new MedicationAdapter(new InjectionMedication());
        } else {
            return null;
        }
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

        hospital.admitPatient(patient1);
        hospital.admitPatient(patient2);
        hospital.assignDoctor(doctor1, patient1);
        hospital.assignDoctor(doctor2, patient2);

        hospital.addNurse(nurse1);
        hospital.addNurse(nurse2);

        MedicationFactory medicationFactory = new MedicationFactory();

        MedicationStrategy oralMedication = medicationFactory.createMedication("oral");
        MedicationStrategy injectionMedication = medicationFactory.createMedication("injection");

        hospital.prescribeMedication(doctor1, patient1, oralMedication);
        hospital.prescribeMedication(doctor2, patient2, injectionMedication);

        hospital.notifyNurses("Medication administered to patients.");
    }
}
