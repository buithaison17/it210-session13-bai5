package org.example.bai5.repository;

import org.example.bai5.model.entity.Prescription;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class PrescriptionRepository {
    private final SessionFactory sessionFactory;

    public PrescriptionRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Prescription> findAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Prescription", Prescription.class).list();
    }

    public void addPrescription(Prescription prescription) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(prescription);
    }

    public List<Prescription> findByPatientName(String patientName) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Prescription where patientName = :patientName", Prescription.class)
                .setParameter("patientName", "%" + patientName + "%")
                .list();
    }
}
