package com.intuit.dao;

import com.intuit.model.Student;
import org.springframework.stereotype.Component;


public interface RegistrationDAO {
    public Long persistStudent(Student student);
    public Student findById(Long id);
}
