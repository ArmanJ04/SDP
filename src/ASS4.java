import java.util.ArrayList;
import java.util.List;
interface Patient {
    void update(String medicineName);
}

class MedicalPatient implements Patient {
    private String name;

    public MedicalPatient(String name) {
        this.name = name;
    }

    @Override
    public void update(String medicineName) {
        System.out.println(name + " received medicine: " + medicineName);
    }
}

interface MedicineProvider {
    void subscribePatient(Patient patient);
    void unsubscribePatient(Patient patient);
    void prescribeMedicine(String medicineName);
}

class Doctor implements MedicineProvider {
    private List<Patient> patients = new ArrayList<>();

    @Override
    public void subscribePatient(Patient patient) {
        patients.add(patient);
    }

    @Override
    public void unsubscribePatient(Patient patient) {
        patients.remove(patient);
    }

    @Override
    public void prescribeMedicine(String medicineName) {
        for (Patient patient : patients) {
            patient.update(medicineName);
        }
    }
}

public class ASS4 {
    public static void main(String[] args) {
        Doctor doctor = new Doctor();
        Patient patient1 = new MedicalPatient("Patient 1");
        Patient patient2 = new MedicalPatient("Patient 2");
        doctor.subscribePatient(patient1);
        doctor.subscribePatient(patient2);
        doctor.prescribeMedicine("Aspirin");
        doctor.unsubscribePatient(patient2);
        doctor.prescribeMedicine("Antibiotics");
    }
}
