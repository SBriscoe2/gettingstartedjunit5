package patientintake;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("BMI Calculator should")
class BmiCalculatorTest {

    @Test
    @DisplayName("calculate BMI to two places correctly via inches and pounds")
    void calculateCorrectly(){
        //BmiCalculator calculateBmi = new BmiCalculator();
        assertEquals(19.2, BmiCalculator.calculateBmi(69, 130));
        assertEquals(21.52, BmiCalculator.calculateBmi(70, 150));
    }
}