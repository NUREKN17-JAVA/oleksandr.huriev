package ua.nure.cs.huriev.usermanagement;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class User implements Serializable {


    private static final long serialVersionUID = -9093246790936431023L;
    private Long id;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(dateOfBirth, user.dateOfBirth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, dateOfBirth);
    }

    public String getFullName() {
        return getLastName() + ", " + getFirstName();
    }

    public int getAge() {
        int currentYear = new Date().getYear();
        int yearOfBirth = getDateOfBirth().getYear();
        return currentYear - yearOfBirth;
    }

    public long getDay() {
        Calendar now = Calendar.getInstance();
        int day = now.get(Calendar.DAY_OF_MONTH);;
        return day;
    }
    public int getMonthLeft(){
        int currentMonth = new Date().getMonth();
        int monthOfBirth = getDateOfBirth().getMonth();
        return monthOfBirth - currentMonth;
    }
    public int getAge5() {
        int currentYear = new Date().getYear();
        int yearOfBirth = getDateOfBirth().getYear();
        return (currentYear - yearOfBirth) + 5;
    }

}
