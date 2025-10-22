import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;  


public class GradeJunit5Tests {
	/*
	 * Author: Fabrizio Guzzo
     * Name: Car Class Test
     * Purpose: Testing COmputation of MPG
     * tostring including all details
     * correct fuel type
     * correct traction control statemtn 
     */
	
    @Test
    void car_avgMpg_and_toString() {
        Car c = new Car("Toyota", "Camry", 2022, "Sedan", false,
                7.6, 28, 39, "Gasoline", "FWD", 28000, 87, true);
        assertEquals(33.5, c.getAvgMpg(), 1e-9);
        assertTrue(c.toString().contains("Toyota Camry 2022"));
    }
	/*
	 * Author: Fabrizio Guzzo
	 * Name: Grade Logic
	 * Purpose: Testing grade computation
	 */
    @Test
    void grade_logic() {
        Grade g = new Grade();
        g.addGrade("Fabrizio", 50, 80);
        g.addGrade("Fabrizio", 50, 100);
        assertEquals(90.0, g.getFinalGrade(), 1e-9, "wrong grade");
        assertEquals("A", g.getLetterGrade());
    }
    /*
     * Author: Fabrizio Guzzo
     * Name: Zero Grade
     * Purpose: Testing 0 grade
     */
    @Test
    void empty_grade() {
        Grade g = new Grade();
        assertEquals(0.0, g.getFinalGrade());
        assertEquals("E", g.getLetterGrade());
    }
    
    /*
     * Author; Fabrizio Guzzo
     * Name: Seter and getter test
     * Purpose: Making sure all setter and getter work properly within the class 
     * AI Help: I utilized Copiolit to help think of possible tests for the preference class.
     */

    @Test
    void set_Values() {
        Preferences p = new Preferences();
        p.setYearRange(new double[]{2015, 2025});
        p.setPriceRange(new double[]{15000, 30000});
        p.setMpgTarget(35.0);
        p.setSportyLook(true);
        p.setMaxZeroToSixty(6.0);
        p.setTractionControl(false);
        assertEquals(2015, p.getMinYear());
        assertEquals(2025, p.getMaxYear());
        assertEquals(35.0, p.getMpgTarget());
        assertTrue(p.getSportyLook());
    }
    /*
     * Author: Fabrizio Guzzo
     * Name Score detection (fuel and Sporty)
     * Purpose: Case sensitive Review along with Scoring logic
     */
    @Test
    void scorer_detects_case_sensitive_fuelType_bug_and_sporty_bug() {
        List<Car> cars = Inventory.build();
        Scorer scorer = new Scorer(cars);
        Preferences prefs = new Preferences();
        prefs.setFuelTypes(Set.of("gasoline"));
        prefs.setSportyLook(true);
        prefs.setTractionControl(true);
        List<Scorer.Result> results = scorer.scoreAndRank(prefs);
        long fuelMatches = results.stream()
                .filter(r -> r.b.fuelN == 1.0)
                .count();
        assertTrue(fuelMatches > 0, "Case Sensitive");
        Scorer.Result sportyCar = results.stream()
                .filter(r -> r.car.isSportyLook())
                .findFirst().orElse(null);
        assertNotNull(sportyCar);
        assertTrue(sportyCar.b.sportyN == 1.0, "wrong sporty preference");
    }
    
    
}
