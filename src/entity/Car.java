package entity;

import annotations.Column;
import annotations.Id;
import annotations.Table;

/**
 * Created by PC on 01.12.2016.
 */
@Table("car")
public class Car {
    @Id
    @Column("id")
    public long id;
    @Column("registration_tag")
    public String registrationTag;
    @Column("park")
    public long parkId;

    public Car() {
    }

    public Car(long id, String registrationTag, long parkId) {
        this.id = id;
        this.registrationTag = registrationTag;
        this.parkId = parkId;
    }

    public Car(String registrationTag, long parkId) {
        this.registrationTag = registrationTag;
        this.parkId = parkId;
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

    public long getParkId() {
        return parkId;
    }

    public void setParkId(long parkId) {
        this.parkId = parkId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Car car = (Car) o;

        if (id != car.id) return false;
        if (parkId != car.parkId) return false;
        return registrationTag != null ? registrationTag.equals(car.registrationTag) : car.registrationTag == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (registrationTag != null ? registrationTag.hashCode() : 0);
        result = 31 * result + (int) (parkId ^ (parkId >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", registrationTag='" + registrationTag + '\'' +
                ", parkId=" + parkId +
                '}';
    }
}
