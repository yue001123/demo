package org.example.optional;


import java.util.Optional;

/**
 * 可选测试
 * Optional
 * @author xiaomage
 * @date 2022/12/22
 */
public class OptionalTest {

    public static void main(String[] args) throws Exception {

        Student student = new Student("haha");
        student = null;

        if (student != null) {
            String name = student.getName();
        }

        //直接判空 不推荐使用 代码依旧冗余
        Optional<Student> studentB = Optional.ofNullable(student);
        if (studentB.isPresent()) {
            System.out.println(studentB.get().getName());
        }

        //正确的操作 用流式操作
        Optional.ofNullable(student).ifPresent(stu -> System.out.println(stu.getName()));


//        if (student != null) {
//            if (student.getAddress() != null) {
//                Address address = student.getAddress();
//                if (address.getCity() != null) {
//                    System.out.println(address.getCity());
//                }
//            }
//        }
//        throw new RuntimeException("有空值");

        Optional.ofNullable(student)
                .map(Student::getAddress)
                .map(Address::getCity)
                .orElseThrow(()->new Exception("有空值"));

    }

    public Student getStudent(Student student) throws RuntimeException {
        if (student != null) {
            String name = student.getName();
            if ("xiaomage".equals(name)) {
                return student;
            }
            student = new Student();
            student.setName("xiaomage");
            return student;
        } else {
            student = new Student();
            student.setName("xiaomage");
            return student;
        }
    }


    public Student getStudent2(Student student) {
        return Optional.ofNullable(student)
                .filter(stu -> "xiaomage".equals(stu.getName()))
                .orElseGet(() -> {
                    Student studentNew = new Student();
                    studentNew.setName("xiaomage");
                    return studentNew;
                });
    }


}
