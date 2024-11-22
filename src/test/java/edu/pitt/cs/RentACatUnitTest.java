package edu.pitt.cs;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RentACatUnitTest {

    RentACat r; // Object to test
    Cat c1; // First cat object
    Cat c2; // Second cat object
    Cat c3; // Third cat object

    ByteArrayOutputStream out; // Output stream for testing system output
    PrintStream stdout; // Print stream to hold the original stdout stream
    String newline = System.lineSeparator(); // Platform independent newline ("\n" or "\r\n") for use in assertEquals

    @Before
    public void setUp() throws Exception {
        // Use a real instance of RentACatImpl for testing
        r = new RentACatImpl();

        // Create real Cat objects
        c1 = new CatImpl(1, "Jennyanydots");
        c2 = new CatImpl(2, "Old Deuteronomy");
        c3 = new CatImpl(3, "Mistoffelees");

        // Add cats to the RentACat instance
        r.addCat(c1);
        r.addCat(c2);
        r.addCat(c3);

        // Redirect system output from stdout to the "out" stream
        stdout = System.out;
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
    }

    @After
    public void tearDown() throws Exception {
        // Restore System.out to the original stdout
        System.setOut(stdout);
    }

    @Test
    public void testGetCatNullNumCats0() throws Exception {
        // Using reflection to call private method getCat
        Method method = r.getClass().getDeclaredMethod("getCat", int.class);
        method.setAccessible(true);
        Cat result = (Cat) method.invoke(r, 2); // Corrected type casting

        // Check output and return value
        assertNotNull(result);
        assertEquals(2, result.getId());
    }

    @Test
    public void testGetCatNumCats3() throws Exception {
        // Using reflection to call private method getCat
        Method method = r.getClass().getDeclaredMethod("getCat", int.class);
        method.setAccessible(true);
        Cat result = (Cat) method.invoke(r, 2);

        // Check that the correct cat is returned
        assertNotNull(result);
        assertEquals(2, result.getId());
    }

    @Test
    public void testListCatsNumCats0() {
        // Remove all cats
        r = new RentACatImpl(); // Reset RentACat to empty
        assertEquals("", r.listCats());
    }

    @Test
    public void testListCatsNumCats3() {
        String expected = "ID 1. Jennyanydots\nID 2. Old Deuteronomy\nID 3. Mistoffelees";
        assertEquals(expected, r.listCats().trim());
    }

    @Test
    public void testRenameFailureNumCats0() {
        // Remove all cats
        r = new RentACatImpl(); // Reset RentACat to empty
        assertFalse(r.renameCat(2, "Garfield"));
        assertEquals("Invalid cat ID." + newline, out.toString());
    }

    @Test
    public void testRenameNumCat3() {
        r.renameCat(2, "Garfield");

        String expected = "ID 1. Jennyanydots\nID 2. Garfield\nID 3. Mistoffelees";
        assertEquals(expected, r.listCats().trim());
    }

    @Test
    public void testRentCatNumCats3() {
        assertTrue(r.rentCat(2));
        assertTrue(c2.getRented()); // Check that the cat is marked as rented
    }

	@Test
	public void testRentCatFailureNumCats3() {
		// Create mock for Cat
		Cat mockCat = mock(Cat.class);
		when(mockCat.getId()).thenReturn(2);
		when(mockCat.getRented()).thenReturn(true); // Simulate that the cat is already rented
	
		// Reset RentACat and add mockCat
		r = new RentACatImpl();
		r.addCat(mockCat);
	
		// Perform the rentCat operation
		assertFalse(r.rentCat(2));
	}
	

    @Test
    public void testReturnCatNumCats3() {
        r.rentCat(2); // Rent the cat first
        assertTrue(r.returnCat(2));
        assertFalse(c2.getRented()); // Check that the cat is marked as not rented
    }

    @Test
    public void testReturnFailureCatNumCats3() {
        assertFalse(r.returnCat(2));
    }
}