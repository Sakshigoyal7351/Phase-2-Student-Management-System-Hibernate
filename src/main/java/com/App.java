package com;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {

		int ch = 0;
		System.out.println("Project Started...!");
		do {

			Configuration cfg = new Configuration();
			cfg.configure("hibernate.cfg.xml");

			SessionFactory factory = cfg.buildSessionFactory();

			Session session = factory.openSession();
			Transaction tx = session.beginTransaction();

			Scanner sc = new Scanner(System.in);

			System.out.println("____________________*******________________________");

			System.out.println("1. Save the Student  ");
			System.out.println("2. Update the Student");
			System.out.println("3. Display the Student data ");
			System.out.println("4. Display all student data");
			System.out.println("5. Delete the student data");
			System.out.println("6. exit");

			System.out.println("____________________*******________________________");

			System.out.println("enter your choice !");
			ch = sc.nextInt();

			long id;
			String fname;
			String lname;
			String email;

			switch (ch) {
			case 1:
				System.out.println("____________________*******________________________");

				System.out.println("Enter the Student id: ");
				id = sc.nextLong();

				System.out.println("enter the student first name ");
				fname = sc.next();

				System.out.println("enter the student last name ");
				lname = sc.next();

				System.out.println("enter the student email");
				email = sc.next();

				Student st = new Student();
				st.setId(id);
				st.setFirstName(fname);
				st.setLastName(lname);
				st.setEmail(email);

				session.save(st);
				tx.commit();
				session.close();

				System.out.println("Student data inserted successfully !!");

				System.out.println("____________________*******________________________");

				break;
			case 2:
				System.out.println("____________________*******________________________");

				System.out.println("enter the id whose data you wanna update!!");
				id = sc.nextLong();

				System.out.println("enter the new first name ");
				fname = sc.next();

				System.out.println("enter the new last name ");
				lname = sc.next();

				System.out.println("enter the new email");
				email = sc.next();

				Student student = session.get(Student.class, id);

				student.setFirstName(fname);
				student.setLastName(lname);
				student.setEmail(email);

				session.saveOrUpdate(student);

				tx.commit();
				session.close();

				System.out.println("Student data updated succesfully !!");

				System.out.println("____________________*******________________________");

				break;
			case 3:
				System.out.println("____________________*******________________________");

				System.out.println("enter the student id whose data you wanna display ");
				id = sc.nextLong();

				Student st3 = session.get(Student.class, id);
				System.out.println("____________________*******________________________");

				System.out.println("Id :" + st3.getId());
				System.out.println("first name :" + st3.getFirstName());
				System.out.println("last name :" + st3.getLastName());
				System.out.println("email :" + st3.getEmail());

				System.out.println("____________________*******________________________");

				tx.commit();
				session.close();

				System.out.println("____________________*******________________________");

				break;
			case 4:

				System.out.println("____________________*******________________________");

				List<Student> students = (List<Student>) session.createQuery("from Student").list();

				for (Student s5 : students) {
					System.out.println("____________________*******________________________");

					System.out.println("Id :" + s5.getId());
					System.out.println("First Name : " + s5.getFirstName());
					System.out.println("Last Name :" + s5.getLastName());
					System.out.println("Email : " + s5.getEmail());
					System.out.println("____________________*******________________________");

				}

				System.out.println("____________________*******________________________");

				break;
			case 5:
				System.out.println("____________________*******________________________");

				System.out.println("Enter the student id whose data you wanted to delete !");
				id=sc.nextLong();
				
				Student s5=session.get(Student.class, id);
				session.delete(s5);
				
				System.out.println("Student data deleted successfully !!");
				
				tx.commit();
				session.close();
				
				System.out.println("____________________*******________________________");

				break;
			case 6:
				System.exit(0);
				
				break;
			}

		} while (ch != 9);
	}
}
