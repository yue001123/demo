package org.example.optional;


import lombok.Data;

/**
 * 学生
 *
 * @author xiaomage
 * @date 2022/12/22
 */
@Data
public class Student {

    public Student() {
    }

    public Student(String name) {
        this.name = name;
    }

    private String name;
    private Address address;

}
