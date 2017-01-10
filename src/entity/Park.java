package entity;


import javax.persistence.*;
import java.util.List;

@Entity
public class Park {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;
    @Column(name = "name")
    public String name;
    @OneToOne
    @JoinColumn(name = "owner")
    public User owner;
    @OneToMany(mappedBy = "park")
    List<Car> carList;
    public Park() {
    }

    public Park(String name, User owner) {
        this.name = name;
        this.owner = owner;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Park{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", owner=" + owner +
                ", carList=" + carList +
                '}';
    }
}
