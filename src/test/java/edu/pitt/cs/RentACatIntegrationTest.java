package edu.pitt.cs;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class RentACatIntegrationTest {

    RentACat rentACat; // RentACat instance
    Cat c1, c2, c3; // Cat objects

    @Before
    public void setUp() throws Exception {
        // Initialize RentACatImpl for integration testing
        rentACat = new RentACatImpl();

        // Create and add Cat instances to RentACat
        c1 = Cat.createInstance(InstanceType.IMPL, 1, "Jennyanydots");
        c2 = Cat.createInstance(InstanceType.IMPL, 2, "Old Deuteronomy");
        c3 = Cat.createInstance(InstanceType.IMPL, 3, "Mistoffelees");

        // Add cats to the RentACat instance
        rentACat.addCat(c1);
        rentACat.addCat(c2);
        rentACat.addCat(c3);
    }

    @After
    public void tearDown() throws Exception {
        // Clean up resources
        rentACat = null;
        c1 = null;
        c2 = null;
        c3 = null;
    }

    /**
     * Integration test for listing cats when no cats are rented.
     */
    @Test
    public void testListCatsAllAvailable() {
        String expected = "ID 1. Jennyanydots\nID 2. Old Deuteronomy\nID 3. Mistoffelees";
        assertEquals(expected, rentACat.listCats().trim());
    }

    /**
     * Integration test for renting a cat.
     */
    @Test
    public void testRentCatIntegration() {
        // Rent cat with ID 2
        assertTrue(rentACat.rentCat(2));
        // Verify that cat 2 is now rented
        assertTrue(c2.getRented());

        // Ensure the rented cat is no longer listed
        String expected = "ID 1. Jennyanydots\nID 3. Mistoffelees";
        assertEquals(expected, rentACat.listCats().trim());
    }

    /**
     * Integration test for returning a cat.
     */
    @Test
    public void testReturnCatIntegration() {
        // Rent and then return cat with ID 2
        rentACat.rentCat(2);
        assertTrue(rentACat.returnCat(2));
        // Verify that cat 2 is no longer rented
        assertFalse(c2.getRented());

        // Ensure all cats are listed again after return
        String expected = "ID 1. Jennyanydots\nID 2. Old Deuteronomy\nID 3. Mistoffelees";
        assertEquals(expected, rentACat.listCats().trim());
    }

    /**
     * Integration test for renaming a cat.
     */
    @Test
    public void testRenameCatIntegration() {
        // Rename cat with ID 2
        assertTrue(rentACat.renameCat(2, "Garfield"));

        // Verify that cat 2's name has changed
        assertEquals("Garfield", c2.getName());

        // Ensure list reflects the updated name
        String expected = "ID 1. Jennyanydots\nID 2. Garfield\nID 3. Mistoffelees";
        assertEquals(expected, rentACat.listCats().trim());
    }

    /**
     * Integration test for attempting to rent an already rented cat.
     */
    @Test
    public void testRentAlreadyRentedCatIntegration() {
        // Rent cat with ID 2
        rentACat.rentCat(2);
        // Try to rent the same cat again, which should fail
        assertFalse(rentACat.rentCat(2));
    }

    /**
     * Integration test for returning a cat that wasn't rented.
     */
    @Test
    public void testReturnNonRentedCatIntegration() {
        // Try to return a cat that has not been rented, which should fail
        assertFalse(rentACat.returnCat(2));
    }

}