/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package gui;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Chamod
 */
public class Employee_SignInTest {

    private Employee_SignIn employeeSignIn;

    public Employee_SignInTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        employeeSignIn = new Employee_SignIn();
    }

    @After
    public void tearDown() {
        employeeSignIn = null;
    }

    /**
     * Test cases for userIDValidate method
     */
    @Test
    public void testUserIDValidate_EmptyInput() {
        System.out.println("Testing userIDValidate with empty input");
        String value = "";
        boolean result = employeeSignIn.userIDValidate(value);
        assertTrue("Empty user ID should return true", result);
    }

    @Test
    public void testUserIDValidate_WhitespaceInput() {
        System.out.println("Testing userIDValidate with whitespace input");
        String value = "   ";
        boolean result = employeeSignIn.userIDValidate(value);
        assertTrue("Whitespace-only user ID should return true", result);
    }

    @Test
    public void testUserIDValidate_ValidLength() {
        System.out.println("Testing userIDValidate with valid length");
        String value = "EMP001";
        boolean result = employeeSignIn.userIDValidate(value);
        assertFalse("Valid length user ID should return false", result);
    }

    @Test
    public void testUserIDValidate_TooLong() {
        System.out.println("Testing userIDValidate with too long input");
        String value = "EMP0012345";  // More than 8 characters
        boolean result = employeeSignIn.userIDValidate(value);
        assertTrue("Too long user ID should return true", result);
    }

    /**
     * Test cases for passwordValidate method
     */
    @Test
    public void testPasswordValidate_EmptyInput() {
        System.out.println("Testing passwordValidate with empty input");
        String value = "";
        boolean result = employeeSignIn.passwordValidate(value);
        assertTrue("Empty password should return true", result);
    }

    @Test
    public void testPasswordValidate_ValidPassword() {
        System.out.println("Testing passwordValidate with valid password");
        String value = "Pass@word123";  // Meets all criteria
        boolean result = employeeSignIn.passwordValidate(value);
        assertFalse("Valid password should return false", result);
    }

    @Test
    public void testPasswordValidate_NoUpperCase() {
        System.out.println("Testing passwordValidate with no uppercase");
        String value = "pass@word123";
        boolean result = employeeSignIn.passwordValidate(value);
        assertTrue("Password without uppercase should return true", result);
    }

    @Test
    public void testPasswordValidate_NoLowerCase() {
        System.out.println("Testing passwordValidate with no lowercase");
        String value = "PASS@WORD123";
        boolean result = employeeSignIn.passwordValidate(value);
        assertTrue("Password without lowercase should return true", result);
    }

    @Test
    public void testPasswordValidate_NoSpecialChar() {
        System.out.println("Testing passwordValidate with no special character");
        String value = "Password123";
        boolean result = employeeSignIn.passwordValidate(value);
        assertTrue("Password without special character should return true", result);
    }

    @Test
    public void testPasswordValidate_NoNumber() {
        System.out.println("Testing passwordValidate with no number");
        String value = "Pass@wordABC";
        boolean result = employeeSignIn.passwordValidate(value);
        assertTrue("Password without number should return true", result);
    }

    @Test
    public void testPasswordValidate_TooShort() {
        System.out.println("Testing passwordValidate with too short password");
        String value = "Pa@1";  // Less than 8 characters
        boolean result = employeeSignIn.passwordValidate(value);
        assertTrue("Too short password should return true", result);
    }

    @Test
    public void testPasswordValidate_TooLong() {
        System.out.println("Testing passwordValidate with too long password");
        String value = "Pass@word123456";  // More than 12 characters
        boolean result = employeeSignIn.passwordValidate(value);
        assertTrue("Too long password should return true", result);
    }
}
