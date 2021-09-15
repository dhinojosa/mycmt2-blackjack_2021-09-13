package com.jitterted.ebp.blackjack.domain;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.function.Supplier;

public class Employee {
    private String firstName;
    private String lastName;
    private LocalDate birthdate;
    private Supplier<LocalDate> todaysDate;

    public Employee(String firstName, String lastName, LocalDate birthdate, Supplier<LocalDate> todaysDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.todaysDate = todaysDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public Supplier<LocalDate> getTodaysDate() {
        return todaysDate;
    }

    public int age() {
        return (int) ChronoUnit.YEARS.between(birthdate, todaysDate.get());
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(firstName, employee.firstName) && Objects.equals(lastName, employee.lastName) && Objects.equals(birthdate, employee.birthdate) && Objects.equals(todaysDate, employee.todaysDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, birthdate, todaysDate);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Employee.class.getSimpleName() + "[", "]")
                .add("firstName='" + firstName + "'")
                .add("lastName='" + lastName + "'")
                .add("birthdate=" + birthdate)
                .add("todaysDate=" + todaysDate)
                .toString();
    }

    public static void main(String[] args) {
        Employee employee = Employee.of("Hyunji", "Kim", LocalDate.of(10, 11, 2004));
    }

    private static Employee of(String firstName, String lastName, LocalDate birthdate) {
        return new Employee("Hyunji", "Kim", LocalDate.of(10, 11, 2004), () -> LocalDate.now());
    }
}

