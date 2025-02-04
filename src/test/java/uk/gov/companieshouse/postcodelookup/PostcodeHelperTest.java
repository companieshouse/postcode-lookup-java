package uk.gov.companieshouse.postcodelookup;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PostcodeHelperTest {

    @Test
    public void testGetCountryFromPostcode() {
        String scPostcode = "IV12HJ";
        String borderPostcode = "SY115XD";
        String defaultPostcode = "SW75KG";

        // Run the method
        String country1 = GetCountryFromPostcode(scPostcode);
        String country2 = GetCountryFromPostcode(borderPostcode);
        String country3 = GetCountryFromPostcode(defaultPostcode);

        // Evaluate outcome
        assertEquals("GB-SCT", country1);
        assertEquals("border", country2);
        assertEquals("GB-ENG", country3);
    }

    // Assuming this method is defined somewhere in your codebase
    private String GetCountryFromPostcode(String postcode) {
        // Implement the logic to get the country from postcode
        return ""; // Placeholder return statement
    }
}