package com.intuit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.intuit.dao.RegistrationDAO;
import com.intuit.model.Student;


@Component
public class RegistrationService {

    // this is our INTERFACE - our CONTRACT saying that i agree
    // to implement persist and find  - ie i agree to act as a database implementation

    private final RegistrationDAO registrationDAO;

    // Autowired tells Spring to automatically inject a
    // object into this constructor when a RegistrationService
    // is created, more on this later

    // When there’s only one constructor with args, @autowired
    // is optional

    @Autowired
    public RegistrationService(RegistrationDAO registrationDAO) {
        this.registrationDAO = registrationDAO;
    }

    public Long registerStudent(Student student) throws
            StudentAlreadyRegisteredException {

        if (this.registrationDAO.findById(student.getId())
                != null) {
            throw new StudentAlreadyRegisteredException();
        }

        return registrationDAO.persistStudent(student);
    }
}

