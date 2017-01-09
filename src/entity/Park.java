package entity;

import annotations.Column;
import annotations.Id;
import annotations.Table;

/**
 * Created by PC on 01.12.2016.
 */
@Table("park")
public class Park {
    @Id
    @Column("id")
    public long id;
    @Column("name")
    public String name;
    @Column("owner")
    public long owner;

    public Park() {
    }

    public Park(String name, long owner) {
        this.name = name;
        this.owner = owner;
    }

    public Park(long id, String name, long owner) {
        this.id = id;
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

    public long getOwner() {
        return owner;
    }

    public void setOwner(long owner) {
        this.owner = owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Park park = (Park) o;

        if (id != park.id) return false;
        if (owner != park.owner) return false;
        return name != null ? name.equals(park.name) : park.name == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (int) (owner ^ (owner >>> 32));
        return result;
    }
}
