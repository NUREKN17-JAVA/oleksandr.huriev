package ua.nure.cs.huriev.usermanagement;

import junit.framework.TestCase;

import java.util.Calendar;

public class UserTest extends TestCase {

    public static final String FIRST_NAME = "John";
    public static final String LAST_NAME = "Doe";
    public static final String ETALON_FULL_NAME = "Doe, John";
    public static final int YEAR_OF_BIRTH = 2000;
    public static final int MONTH_OF_BIRTH = 11;
    public static final int MONTH_OF_BIRTH_2 = 10;
    public static final int DAY_OF_BIRTH = 28;
    public static final int ETALON_AGE_1 = 19;
    public static final int ETALO_AGE_2 = 20;
    public static final int AGE_AFTER_5_YEAR = 24;
    private static final int MONTH_OF_BIRTH_PASSED = 9;
    private static final int MONTH_LEFT_TO_BIRTH = 2;
    private User user;

    public void testGetFullName(){
        user.setFirstName(FIRST_NAME);
        user.setLastName(LAST_NAME);
        assertEquals(ETALON_FULL_NAME, user.getFullName());
    }

    public void testGetAgeBirthDateInTheFuture(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(YEAR_OF_BIRTH, MONTH_OF_BIRTH, DAY_OF_BIRTH);
        user.setDateOfBirth(calendar.getTime());
        assertEquals(ETALON_AGE_1,user.getAge());
    }

    public void testGetAgeBirthDateIsPassedItTheYear(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(YEAR_OF_BIRTH, MONTH_OF_BIRTH_PASSED, DAY_OF_BIRTH);
        user.setDateOfBirth(calendar.getTime());
        assertEquals(ETALON_AGE_1,user.getAge());
    }

    public void testGetDayOfBirthToday() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(YEAR_OF_BIRTH, MONTH_OF_BIRTH, DAY_OF_BIRTH);
        user.setDateOfBirth(calendar.getTime());
        assertEquals(DAY_OF_BIRTH, user.getDay());
    }

    public void  testMonthLeft(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(YEAR_OF_BIRTH, MONTH_OF_BIRTH, DAY_OF_BIRTH);
        user.setDateOfBirth(calendar.getTime());
        assertEquals(MONTH_LEFT_TO_BIRTH, user.getMonthLeft());
    }

    public void testGetAgeAfter5Year(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(YEAR_OF_BIRTH, MONTH_OF_BIRTH, DAY_OF_BIRTH);
        user.setDateOfBirth(calendar.getTime());
        assertEquals(AGE_AFTER_5_YEAR,user.getAge5());
    }

    protected void setUp() throws Exception {
        super.setUp();
        user = new User();
    }

    protected void tearDown() throws Exception{
        super.tearDown();
    }

}