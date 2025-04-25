import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;

/**
 * File: Lab11Prob03.java Class: CSCI 1302 Author: Lab11EspinosaHubbard Created
 * on: Apr 25, 2025 Last Modified: Apr 25, 2025 Description: Prob 03 -
 * Serializable Objects
 */

public class Lab11Prob03 {

	public static void main(String[] args) {
		File fileInput = new File("src/people.dat");
		File fileOutput = new File("src/people-salary-sorted-objects.dat");

		ArrayList<Person> people = new ArrayList<>();

		try (DataInputStream input = new DataInputStream(new FileInputStream(fileInput))) {
			int age;
			String fullName;
			String address;
			int zipCode;
			double salary;

			while (true) {
				age = input.readInt();
				fullName = input.readUTF();
				address = input.readUTF();
				zipCode = input.readInt();
				salary = input.readDouble();

				Person p = new Person();
				p.setAge(age);
				p.setName(fullName);
				p.setAddress(address);
				p.setZipCode(zipCode);
				p.setSalary(salary);

				people.add(p);

				System.out.printf("%d %s %s %d %.2f%n", age, fullName, address, zipCode, salary);
			}

		} catch (EOFException ex) {
			// End of file reached
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		Collections.sort(people);

		try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(fileOutput))) {
			for (Person p : people) {
				output.writeObject(p);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}

class Person implements Comparable<Person>, java.io.Serializable {
	private int age;
	private String name;
	private String address;
	private int zipCode;
	private double salary;

	public Person() {
		setAge(0);
		setName("");
		setAddress("");
		setZipCode(0);
		setSalary(0);
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getZipCode() {
		return zipCode;
	}

	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return String.format("Age: %d%n" + "Name: %s%n" + "Address: %s%n" + "Zip Code: %d%n" + "Salary: $%,.2f%n",
				getAge(), getName(), getAddress(), getZipCode(), getSalary());
	}

	@Override
	public int compareTo(Person person) {
		if (this.salary > person.getSalary()) {
			return -1;
		} else if (this.salary < person.getSalary()) {
			return 1;
		} else {
			return 0;
		}
	}
}