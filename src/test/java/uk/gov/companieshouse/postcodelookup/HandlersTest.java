package uk.gov.companieshouse.postcodelookup;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

public class HandlersTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testPostcodeLookupWithValidPostcode() throws Exception {
        String rightPostcode = "HD75UZ";
        MockHttpServletRequest req = new MockHttpServletRequest();
        req.setMethod("GET");
        req.setRequestURI("/postcode/{postcode}");
        req.setParameter(":postcode", rightPostcode);

        // Mock the DAO
        DaoStore dao = Mockito.mock(DaoStore.class);
        Address address = buildReturnAddress();
        when(dao.getSingleAddress(eq(rightPostcode))).thenReturn(address);

        // Run the handler
        PostcodeLookupHandler handler = new PostcodeLookupHandler(dao);
        ResponseEntity<String> response = handler.handleRequest(rightPostcode);

        // Evaluate outcome
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    public void testPostcodeLookupWithEmptyPostcode() throws Exception {
        String emptyPostcode = "";
        MockHttpServletRequest req = new MockHttpServletRequest();
        req.setMethod("GET");
        req.setRequestURI("/postcode/{postcode}");
        req.setParameter(":postcode", emptyPostcode);

        // Mock the DAO
        DaoStore dao = Mockito.mock(DaoStore.class);
        when(dao.getSingleAddress(eq(emptyPostcode))).thenThrow(new RuntimeException("my error"));

        // Run the handler
        PostcodeLookupHandler handler = new PostcodeLookupHandler(dao);
        ResponseEntity<String> response = handler.handleRequest(emptyPostcode);

        // Evaluate outcome
        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    public void testPostcodeLookupReturnsCorrectAddress() throws Exception {
        String postcodeInput = "HD75UZ";
        MockHttpServletRequest req = new MockHttpServletRequest();
        req.setMethod("GET");
        req.setRequestURI("/postcode/{postcode}");
        req.setParameter(":postcode", postcodeInput);

        // Mock the DAO
        DaoStore dao = Mockito.mock(DaoStore.class);
        Address address = buildReturnAddress();
        when(dao.getSingleAddress(eq(postcodeInput))).thenReturn(address);

        // Run the handler
        PostcodeLookupHandler handler = new PostcodeLookupHandler(dao);
        ResponseEntity<String> response = handler.handleRequest(postcodeInput);

        // Evaluate outcome
        PremiseAddress expectedRes = new PremiseAddress("HD7 5UZ", "GARDEN CITY", "RHYMNEY", "TREDEGAR", "GB-ENG");
        PremiseAddress compareRes = objectMapper.readValue(response.getBody(), PremiseAddress.class);

        assertEquals(expectedRes, compareRes);
    }

    private Address buildReturnAddress() {
        return new Address("HD7 5UZ", "GARDEN CITY", "", "RHYMNEY", "TREDEGAR", "GB-ENG");
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