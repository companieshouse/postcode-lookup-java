package uk.gov.companieshouse.postcodelookup;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
public class PostcodeLookupHandler {

    private final DaoStore dao;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public PostcodeLookupHandler(DaoStore dao) {
        this.dao = dao;
    }

    @GetMapping("/postcode/{postcode}")
    public ResponseEntity<String> handleRequest(@PathVariable String postcode) {
        Instant responseStartTime = Instant.now();
        try {
            Address address = dao.getSingleAddress(postcode);
            JSONAddress jsonAddress = formatJSON(address, postcode);

            String jsonResponse = objectMapper.writeValueAsString(jsonAddress);
            Instant responseEndTime = Instant.now();
            long responseDuration = responseEndTime.toEpochMilli() - responseStartTime.toEpochMilli();

            // Log response details (assuming a logging mechanism is in place)
            System.out.println("Finished postcode response: " + responseDuration + "ms");

            return ResponseEntity.ok(jsonResponse);
        } catch (Exception e) {
            // Log error details (assuming a logging mechanism is in place)
            System.err.println("Error querying database for postcode: " + postcode);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    private JSONAddress formatJSON(Address address, String postcodeInput) {
        String addressLine1;
        if (address.getThoroughfareDescriptor() != null && !address.getThoroughfareDescriptor().isEmpty()) {
            addressLine1 = address.getThoroughfareName() + " " + address.getThoroughfareDescriptor();
        } else {
            addressLine1 = address.getThoroughfareName();
        }

        String postcodeCountry = getCountryFromPostcode(postcodeInput);

        JSONAddress jsonAddress = new JSONAddress();
        jsonAddress.setPostcode(address.getPostcode());
        jsonAddress.setPostTown(address.getPostTown());

        if (addressLine1.isEmpty()) {
            if (address.getDependentLocality() != null && !address.getDependentLocality().isEmpty()) {
                jsonAddress.setAddressLine1(address.getDependentLocality());
                jsonAddress.setAddressLine2("");
            } else {
                jsonAddress.setAddressLine1("");
                jsonAddress.setAddressLine2(address.getDependentLocality());
            }
        } else {
            jsonAddress.setAddressLine1(addressLine1);
            jsonAddress.setAddressLine2(address.getDependentLocality());
        }

        if (!"border".equals(postcodeCountry)) {
            jsonAddress.setCountry(postcodeCountry);
        } else {
            jsonAddress.setCountry(null);
        }

        return jsonAddress;
    }

    private String getCountryFromPostcode(String postcode) {
        // Implement the logic to get the country from postcode
        return "GB-ENG"; // Placeholder return statement
    }
}
