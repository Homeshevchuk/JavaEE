package entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Driver {
    @Id
    @GeneratedValue
    private int id;
    @Column(name = "name")
    private String name;
    @ManyToOne
    @JoinColumn(name = "Park_Id")
    private Park park;
    @Column(name = "salary")
    private int Salary;
    @ManyToMany
    @JoinTable(
            name = "car_driver",
            joinColumns = @JoinColumn(name = "car_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "driver_id", referencedColumnName = "id"))
    private List<Car> cars;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Park getPark() {
        return park;
    }

    public void setPark(Park park) {
        this.park = park;
    }

    public int getSalary() {
        return Salary;
    }

    public void setSalary(int salary) {
        Salary = salary;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", park=" + park +
                ", Salary=" + Salary +
                ", cars=" + cars +
                '}';
    }
}
