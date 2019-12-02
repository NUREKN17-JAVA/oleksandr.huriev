package ua.nure.cs.huriev.usermanagement;


import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class User implements Serializable {


    private static final long serialVersionUID = -9093246790936431023L;
    private Long id;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;

    public User(Long id, String firstName, String lastName, Date dateOfBirth) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }
    public User(String firstName, String lastName, Date dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }
    public User() { }
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

    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (this == o) {
            return true;
        }
        if (this.getId() == null && ((User) o).getId() == null) {
            return true;
        }
        return this.getId().equals(((User) o).getId());
    }

    public int hashCode() {
        if (this.getId() == null) {
            return 0;
        }
        return this.getId().hashCode();
    }


    public String getFullName() {
        return getLastName() + ", " + getFirstName();
    }
    public int getAge() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int currentYear = calendar.get(Calendar.YEAR);
        calendar.setTime(getDateOfBirth());
        int birthYear = calendar.get(Calendar.YEAR);
        return currentYear - birthYear;
    }
}