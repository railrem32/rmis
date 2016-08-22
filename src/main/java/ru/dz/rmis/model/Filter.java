package ru.dz.rmis.model;

import javax.persistence.*;

@Entity
@Table(name = "Filter")
public class Filter {

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
}
