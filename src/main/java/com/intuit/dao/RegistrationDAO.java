package com.intuit.dao;

import com.intuit.model.Student;

public interface RegistrationDAO {
    public Long persistStudent(Student student);
    public Student findById(Long id);
}
