public class Person implements Comparable<Person>
{
    private int age;
    private String name;
    private String address;
    private int zipCode;
    private double salary;

    public Person()
    {
        this.age = 0;
        this.name = "";
        this.address = "";
        this.zipCode = 0;
        this.salary = 0.0;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public int getZipCode()
    {
        return zipCode;
    }

    public void setZipCode(int zipCode)
    {
        this.zipCode = zipCode;
    }

    public double getSalary()
    {
        return salary;
    }

    public void setSalary(double salary)
    {
        this.salary = salary;
    }

    @Override
    public String toString()
    {
        return "Name: " + getName() + "\n"
             + "Address: " + getAddress() + "\n"
             + "Age: " + getAge() + "\n"
             + "Zip Code: " + getZipCode() + "\n"
             + "Salary: " + getSalary();
    }

    @Override
    public int compareTo(Person other)
    {
        if (this.salary > other.salary)
        {
            return -1;
        }
        else if (this.salary < other.salary)
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }
}
