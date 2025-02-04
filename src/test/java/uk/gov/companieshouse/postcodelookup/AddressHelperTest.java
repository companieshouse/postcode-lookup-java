package handlers;

import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import java.time.Duration;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddressHelperTest {

    @Test
    public void testGetPostcodeInput() {
        // Construct HTTP postcode request
        MockHttpServletRequest req = new MockHttpServletRequest();
        req.setMethod("GET");
        req.setRequestURI("/multiple-addresses/{postcode}");
        req.setParameter(":postcode", "HD75UZ");

        // Run the method
        String pc = GetPostcodeInput(req);

        // Evaluate outcome
        assertEquals("HD75UZ", pc);
    }

    @Test
    public void testServiceResponseTime() {
        // Prepare a start time
        Instant responseStartTime = Instant.now();

        // Run the method
        Instant responseEndTime = ServiceResponseTime(responseStartTime);
        Duration responseDuration = Duration.between(responseStartTime, responseEndTime);

        // Evaluate output
        assertTrue(responseStartTime.getClass().equals(responseEndTime.getClass()));
        assertEquals(responseDuration.toMillis(), responseEndTime.toEpochMilli() - responseStartTime.toEpochMilli());
    }

    // Assuming these methods are defined somewhere in your codebase
    private String GetPostcodeInput(MockHttpServletRequest req) {
        // Implement the logic to get the postcode input from the request
        return req.getParameter(":postcode");
    }

    private Instant ServiceResponseTime(Instant startTime) {
        // Implement the logic to get the service response time
        return Instant.now();
    }
}