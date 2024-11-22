package edu.pitt.cs;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CatUnitTest {

    Cat c; // cat object

    @Before
    public void setUp() throws Exception {
        // INITIALIZE THE TEST FIXTURE
        // Create a Cat with ID 1 and name "Jennyanydots", assign to c using a call to Cat.createInstance(InstanceType.IMPL, 1, "Jennyanydots").
        c = Cat.createInstance(InstanceType.IMPL, 1, "Jennyanydots");
    }

    @After
    public void tearDown() throws Exception {
        // Not necessary strictly speaking since the references will be overwritten in
        // the next setUp call anyway and Java has automatic garbage collection.
        c = null;
    }

    /**
     * Test case for int getId().
     * 
     * <pre>
     * Preconditions: c has been created with ID 1, and name "Jennyanydots".
     * Execution steps: Call c.getId().
     * Postconditions: Return value is 1.
     * </pre>
     */
    @Test
    public void testGetId() {
        assertEquals(1, c.getId());
    }

    /**
     * Test case for String getName().
     * 
     * <pre>
     * Preconditions: c has been created with ID 1, and name "Jennyanydots".
     * Execution steps: Call c.getName().
     * Postconditions: Return value is "Jennyanydots".
     * </pre>
     */
    @Test
    public void testGetName() {
        assertEquals("Jennyanydots", c.getName());
    }

    /**
     * Test case for boolean getRented().
     * 
     * <pre>
     * Preconditions: c has been created with ID 1, and name "Jennyanydots".
     * Execution steps: Call c.getRented().
     * Postconditions: Return value is false.
     * </pre>
     */
    @Test
    public void testGetRented() {
        assertFalse(c.getRented());
    }

    /**
     * Test case for String toString().
     * 
     * <pre>
     * Preconditions: c has been created with ID 1, and name "Jennyanydots".
     * Execution steps: Call c.toString().
     * Postconditions: Return value is "ID 1. Jennyanydots".
     * </pre>
     */
    @Test
    public void testToString() {
        assertEquals("ID 1. Jennyanydots", c.toString());
    }

    /**
     * Test case for void rentCat().
     * 
     * <pre>
     * Preconditions: c has been created with ID 1, and name "Jennyanydots".
     * Execution steps: Call c.rentCat().
     *                  Call c.getRented().
     * Postconditions: Return value of c.getRented() is true.
     * </pre>
     */
    @Test
    public void testRentCat() {
        c.rentCat();
        assertTrue(c.getRented());
    }

    /**
     * Test case for void returnCat().
     * 
     * <pre>
     * Preconditions: c has been created with ID 1, and name "Jennyanydots".
     *                c has been rented.
     * Execution steps: Call c.rentCat().
     *                  Call c.returnCat().
     *                  Call c.getRented().
     * Postconditions: Return value of c.getRented() is false.
     * </pre>
     */
    @Test
    public void testReturnCat() {
        c.rentCat();
        c.returnCat();
        assertFalse(c.getRented());
    }

    /**
     * Test case for void renameCat(String newName).
     * 
     * <pre>
     * Preconditions: c has been created with ID 1, and name "Jennyanydots".
     * Execution steps: Call c.renameCat("Garfield").
     * Postconditions: Return value of c.getName() is "Garfield".
     *                 Return value of c.toString() is "ID 1. Garfield".
     * </pre>
     */
    @Test
    public void testRenameCat() {
        c.renameCat("Garfield");
        assertEquals("Garfield", c.getName());
        assertEquals("ID 1. Garfield", c.toString());
    }

}