package com.intuit.service;

import com.intuit.dao.MapBasedRegistrationDAO;
import com.intuit.dao.SimpleRegistrationDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.intuit.dao.RegistrationDAO;
import com.intuit.model.Student;


@Component
public class RegistrationService {

    // this is our INTERFACE - our CONTRACT saying that i agree
    // to implement persist and find  - ie i agree to act as a database implementation

   //FIELD INJECTION:
    //discouraged because:
    //1. Hides dependencies - i can not immediately look at the constructor
    //and see what the class needs as a dependency, so constructor injection is more EXPLICIT!
    //2. field is also harder to test!!
    @Autowired
    private final RegistrationDAO registrationDAO;


    // WHY IS THIS LINE OF CODE A BAD IDEA?? OUR SERVICE HAS A REFERENCE TO A
    //IMPLEMENTATION OF THE DATABASE - OUR HASHMAP IMPLEMENTATION!!
    // THIS IS A PROBLEM BECAUSE NOW OUR BUSINESS LOGIC "KNOWS" ABOUT
    // THE HASHMAP IMPLEMENTATION, THIS IS A TIGHT COUPLING WHICH WE WANT TO AVOID
    // BY USING INTERFACES INSTEAD
   // private final SimpleRegistrationDAO simpleRegistrationDAO;



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

    public String findStudentName(Long id){
        Student student = registrationDAO.findById(id);
        if (student != null) {
            return student.getFirstName() + " " + student.getLastName();
        } else {
            return null;
        }
    }

//    public void setRegistrationDAO(MapBasedRegistrationDAO registrationDAO) {
//    }
}

