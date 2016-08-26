package ru.dz.rmis.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Filter")
public class Filter implements Serializable {

    private static final long serialVersionUID = 8768686754105491583L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "class_name", nullable = false)
    private String className;

    public Filter() {
    }

    public Filter(String description, String className) {
        this.description = description;
        this.className = className;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Filter filter = (Filter) o;

        if (!id.equals(filter.id)) {
            return false;
        }
        if (!description.equals(filter.description)) {
            return false;
        }
        return className.equals(filter.className);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + className.hashCode();
        return result;
    }
}
