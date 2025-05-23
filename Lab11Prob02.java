import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

/**
 * File: Lab11Prob01.java
 * Class: CSCI 1302
 * Author: Lab11EspinosaHubbard
 * Created on: Apr 25, 2025
 * Last Modified: Apr 25, 2025
 * Description: Prob 03
 */

public class Lab11Prob02 {

	public static void main(String[] args) {
		File fileInput = new File("src/people.dat");
		File fileOutput = new File("src/people-salary.dat");

		ArrayList<Person> people = new ArrayList<>();

		try (DataInputStream input = new DataInputStream(new FileInputStream(fileInput));
				DataOutputStream output = new DataOutputStream(new FileOutputStream(fileOutput));) {

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

				System.out.printf("%d %s %s %d% .2f%n", age, fullName, address, zipCode, salary);
				output.writeInt(age);
				output.writeUTF(fullName);
				output.writeUTF(address);
				output.writeInt(zipCode);
				output.writeDouble(salary);
			}

		} catch (EOFException ex) {

		} catch (IOException ex) {

		}

		Collections.sort(people);

		try (DataOutputStream output = new DataOutputStream(new FileOutputStream(fileOutput))) {
			for (Person p : people) {
				output.writeUTF(p.toString());
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

}

class Person implements Comparable<Person> {
	private int age;
	private String name;
	private String address;
	private int zipCode;
	private double salary;

	public Person() {
		this.age = 0;
		this.name = "";
		this.address = "";
		this.zipCode = 0;
		this.salary = 0.0;
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
