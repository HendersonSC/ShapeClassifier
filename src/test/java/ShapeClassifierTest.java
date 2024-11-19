import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ShapeClassifierTest {

    /**
     * This is an example test 
     */
    @DisplayName("Example Test")
    @Test
    public void example() {
        ShapeClassifier s = new ShapeClassifier();
        String answer = s.evaluateGuess("Equilateral,Large,Yes,100,100,100");
        assertNotEquals("Yes", answer);
    }

    @DisplayName("Can you write a branch coverage test for CalculateRectanglePerimeter")
    @Test
    public void testCalculateRectanglePerimeter_BranchCoverage() {
        ShapeClassifier classifier = new ShapeClassifier();

        // Test case 1: side1 == side2 (First branch)
    // Of course the main problem is this method is private!
        int result1 = classifier.calculateRectanglePerimeter(4, 4, 6, 6);
        assertEquals(20, result1, "Branch 1: Perimeter should be 20");

        // Test case 2: side2 == side3 (Second branch)
    // Of course the main problem is this method is private!
        int result2 = classifier.calculateRectanglePerimeter(5, 7, 7, 10);
        assertEquals(24, result2, "Branch 2: Perimeter should be 24");

        // Test case 3: No sides match (Third branch)
    // Of course the main problem is this method is private!
        int result3 = classifier.calculateRectanglePerimeter(4, 5, 6, 7);
        assertEquals(0, result3, "Branch 3: Perimeter should be 0");
    }


    @Test
    @DisplayName("Can you write a Boundary Value Analysis test for the same method? Pay attention to things that shouldn't happen like negative volume.")
    public void testCalculateRectanglePerimeter_BoundaryValueAnalysis() {
        ShapeClassifier classifier = new ShapeClassifier();

        // Test case 1: Minimum valid sides (all zeros)
        int result1 = classifier.calculateRectanglePerimeter(0, 0, 0, 0);
        assertEquals(0, result1, "Boundary Test: Perimeter of degenerate rectangle should be 0");

        // Test case 2: One side is zero (degenerate rectangle)
        int result2 = classifier.calculateRectanglePerimeter(0, 4, 4, 4);
        assertEquals(0, result2, "Boundary Test: Perimeter should be 0 when one side is zero");

        // Test case 3: Maximum valid side length (4095)
        int result3 = classifier.calculateRectanglePerimeter(4095, 4095, 4095, 4095);
        assertEquals(16380, result3, "Boundary Test: Perimeter for max side length should be 16380");

        // Test case 4: Mixed sides - one pair equal (valid rectangle)
        int result4 = classifier.calculateRectanglePerimeter(5, 5, 7, 7);
        assertEquals(24, result4, "Boundary Test: Perimeter should be 24 for valid rectangle");

        // Test case 5: Negative side length (invalid input, should return 0)
        int result5 = classifier.calculateRectanglePerimeter(-5, 5, 7, 7);
        assertEquals(0, result5, "Boundary Test: Negative side length should result in 0");

        // Test case 6: Large mixed sides - no pairs equal
        int result6 = classifier.calculateRectanglePerimeter(1000, 1500, 2000, 2500);
        assertEquals(0, result6, "Boundary Test: Invalid rectangle with no equal sides should return 0");
    }

    @Test
    @DisplayName("Write a test for extremely large values.")
    public void testCalculateRectanglePerimeterExtremelyLargeValues() {
        ShapeClassifier classifier = new ShapeClassifier();
        
        // Define extremely large sides
        int side1 = Integer.MAX_VALUE / 2; // Largest possible value without risking immediate overflow
        int side2 = Integer.MAX_VALUE / 2;
        int side3 = Integer.MAX_VALUE / 2; 
        int side4 = Integer.MAX_VALUE / 2;
        
        // Expected result
        long expectedPerimeter = 2L * side1 + 2L * side2; // Use long to avoid overflow in calculation
        
        // Call the method
    // This will overflow
        int actualPerimeter = classifier.calculateRectanglePerimeter(side1, side2, side3, side4);

        // Assert correctness
    // This will no longer make sense
        assertEquals("Perimeter calculation for extremely large values should match expected", 
                     expectedPerimeter, (long) actualPerimeter);
    }

}
