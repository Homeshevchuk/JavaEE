package entity;


import javax.persistence.*;
import java.util.List;

@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;
    @Column(name = "registration_tag")
    public String registrationTag;
    @ManyToOne
    @JoinColumn(name = "park")
    public Park park;
    @ManyToMany(mappedBy = "cars")
    List<Driver> drivers;
    public Car() {
    }

    public Car(String registrationTag, Park park) {
        this.registrationTag = registrationTag;
        this.park = park;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRegistrationTag() {
        return registrationTag;
    }

    public void setRegistrationTag(String registrationTag) {
        this.registrationTag = registrationTag;
    }

    public Park getPark() {
        return park;
    }

    public void setPark(Park park) {
        this.park = park;
    }

    public List<Driver> getDrivers() {
        return drivers;
    }

    public void setDrivers(List<Driver> drivers) {
        this.drivers = drivers;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", registrationTag='" + registrationTag + '\'' +
                ", park=" + park +
                ", drivers=" + drivers +
                '}';
    }
}
