import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Lab11Prob02 {

	public static void main(String[] args) {
		File fileInput = new File("src/people.dat");
		File fileOutput = new File("src/people-copy.dat");

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
		return String.format("Age: %d%n" + "Name: %s%n" + "Address: %s%n" + "Zip Code: %d%n" + "Salary: $%.2f%n",
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