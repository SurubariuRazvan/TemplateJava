package com.jderu.domain;

import javax.persistence.*;
import javax.persistence.Entity;

@Entity
@Table(name = "Task")
public class Task implements com.jderu.domain.Entity<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Column(name = "description")
    private String description;

    public Task() {
    }

    public Task(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
