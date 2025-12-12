package com.intuit.dao;

import com.intuit.model.Student;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component(value = "mapDao")
public class MapBasedRegistrationDAO implements com.intuit.dao.RegistrationDAO {

    private Map<Long, Student> studentMap = new HashMap<>();
    static Long counter = 0L;

    @Override
    public Long persistStudent(Student student) {
        if (student.getId() == null) {
            student.setId(++counter);
        }
        System.out.println("Using MapBasedRegistrationDAO");
        studentMap.put(student.getId(), student);
        return student.getId();
    }

    @Override
    public Student findById(Long id) {
        return studentMap.get(id);
    }
}