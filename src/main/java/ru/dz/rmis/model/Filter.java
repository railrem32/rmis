package ru.dz.rmis.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Filter")
public class Filter implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String className;

    public Filter() {}

    public Filter(String name, String className) {
        this.name = name;
        this.className = className;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Filter filter = (Filter) o;

        if (!id.equals(filter.id)) return false;
        if (!name.equals(filter.name)) return false;
        return className.equals(filter.className);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + className.hashCode();
        return result;
    }
}
