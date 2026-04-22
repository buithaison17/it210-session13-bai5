package org.example.bai5.service;

import org.example.bai5.model.dto.PrescriptionDTO;
import org.example.bai5.model.entity.Prescription;
import org.example.bai5.repository.PrescriptionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrescriptionService {
    private final PrescriptionRepository prescriptionRepository;

    public PrescriptionService(PrescriptionRepository prescriptionRepository) {
        this.prescriptionRepository = prescriptionRepository;
    }

    public List<Prescription> findAll() {
        return prescriptionRepository.findAll();
    }

    private Prescription convertToEntity(PrescriptionDTO prescriptionDTO) {
        return new Prescription(prescriptionDTO.getId(), prescriptionDTO.getDoctorName(), prescriptionDTO.getPatientName(), prescriptionDTO.getDescription(), prescriptionDTO.getQuantity());
    }

    public void save(PrescriptionDTO prescriptionDTO) {
        prescriptionDTO.setId(System.currentTimeMillis());
        Prescription prescription = convertToEntity(prescriptionDTO);
        prescriptionRepository.addPrescription(prescription);
    }

    public List<Prescription> findByPatientName(String patientName) {
        return prescriptionRepository.findByPatientName(patientName);
    }
}
