package com.example.cruddemo;

import com.example.cruddemo.entity.Student;
import com.example.cruddemo.repository.StudentRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CrudDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudDemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner (StudentRepo studentRepo){
		return runner -> {
			//createStudent(studentRepo);
			//createStudents(studentRepo);
			//findStudent(studentRepo);
			//findStudents(studentRepo);
			//findStudentsByLastName(studentRepo);
			//updateStudent(studentRepo);
			//deleteStudent(studentRepo);
			deleteStudents(studentRepo);
		};
	}

	private void createStudent(StudentRepo studentRepo){

		System.out.println("Creating student...");
		Student student = new Student("Fn1", "Ln1", "Email1");

		System.out.println("Saving student...");
		studentRepo.create(student);

		System.out.println("Saved student generated id: " + student.getId());
	}

	private void createStudents(StudentRepo studentRepo){

		System.out.println("Creating students...");
		Student student7 = new Student("Fn7", "LnTest", "Email7");
		Student student8 = new Student("Fn8", "LnTest", "Email8");

		System.out.println("Saving students...");
		studentRepo.create(student7);
		studentRepo.create(student8);

		System.out.println("Saved student generated id: " + student7.getId());
		System.out.println("Saved student generated id: " + student8.getId());
	}

	private void findStudent(StudentRepo studentRepo){

		Student student = studentRepo.findById(1);
		System.out.println(student.toString());
	}

	private void findStudents(StudentRepo studentRepo){
		List<Student> students = studentRepo.findAll();

		for (Student student : students) {
			System.out.println(student);
		}
	}

	private void findStudentsByLastName(StudentRepo studentRepo){
		List<Student> students = studentRepo.findByLastName("LnTest");

		for (Student student : students) {
			System.out.println(student);
		}
	}

	private void updateStudent(StudentRepo studentRepo){

		int studentID = 6;
		Student studentToUpdate = studentRepo.findById(studentID);
		studentToUpdate.setFirstName("Fn6");
		studentToUpdate.setLastName("Ln6");
		studentToUpdate.setEmail("Email6");

		studentRepo.update(studentToUpdate);

		System.out.println(studentToUpdate);
	}

	private void deleteStudent(StudentRepo studentRepo){
		int studentID = 7;
		studentRepo.delete(studentID);
	}

	private void deleteStudents(StudentRepo studentRepo){
		int studentsDeleted = studentRepo.deleteAll();
		System.out.printf("%s students deleted%n", studentsDeleted);
	}
}
