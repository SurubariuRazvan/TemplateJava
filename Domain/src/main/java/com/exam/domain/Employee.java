package com.exam.domain;

import javax.persistence.*;
import javax.persistence.Entity;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Employee")
public class Employee extends User {
    @Column(name = "employee_state")
    private EmployeeState employeeState;
    @Column(name = "last_clock_in_time")
    private Timestamp lastClockInTime;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> tasks;

    public Employee() {
        this.tasks = new ArrayList<>();
    }

    public Employee(User user) {
        super(user);
        super.setUserType(UserType.Employee);
        this.employeeState = EmployeeState.ATHOME;
        this.lastClockInTime = new Timestamp(0);
        this.tasks = new ArrayList<>();
    }

    public Employee(Integer id, String username, String password, String name) {
        super(id, username, password, name, UserType.Employee);
        this.employeeState = EmployeeState.ATHOME;
        this.lastClockInTime = new Timestamp(0);
        this.tasks = new ArrayList<>();
    }

    public Timestamp getLastClockInTime() {
        return lastClockInTime;
    }

    public void setLastClockInTime(Timestamp lastClockInTime) {
        this.lastClockInTime = lastClockInTime;
    }

    public EmployeeState getEmployeeState() {
        return employeeState;
    }

    public void setEmployeeState(EmployeeState employeeState) {
        this.employeeState = employeeState;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public void removeTask(Task task) {
        this.tasks.remove(task);
    }
}
