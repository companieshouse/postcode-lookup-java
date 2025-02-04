package uk.gov.companieshouse.postcodelookup;

import java.util.HashMap;
import java.util.Map;

public class PostcodeHelper {

    private static final Map<String, String> postcodePrefixes = new HashMap<>();

    static {
        postcodePrefixes.put("AB", "GB-SCT");
        postcodePrefixes.put("DD", "GB-SCT");
        postcodePrefixes.put("EH", "GB-SCT");
        postcodePrefixes.put("FK", "GB-SCT");
        postcodePrefixes.put("G1", "GB-SCT");
        postcodePrefixes.put("G2", "GB-SCT");
        postcodePrefixes.put("G3", "GB-SCT");
        postcodePrefixes.put("G4", "GB-SCT");
        postcodePrefixes.put("G5", "GB-SCT");
        postcodePrefixes.put("G6", "GB-SCT");
        postcodePrefixes.put("G7", "GB-SCT");
        postcodePrefixes.put("G8", "GB-SCT");
        postcodePrefixes.put("G9", "GB-SCT");
        postcodePrefixes.put("HS", "GB-SCT");
        postcodePrefixes.put("IV", "GB-SCT");
        postcodePrefixes.put("KA", "GB-SCT");
        postcodePrefixes.put("KW", "GB-SCT");
        postcodePrefixes.put("KY", "GB-SCT");
        postcodePrefixes.put("ML", "GB-SCT");
        postcodePrefixes.put("PA", "GB-SCT");
        postcodePrefixes.put("PH", "GB-SCT");
        postcodePrefixes.put("ZE", "GB-SCT");
        postcodePrefixes.put("DG", "border");
        postcodePrefixes.put("NE", "border");
        postcodePrefixes.put("CA", "border");
        postcodePrefixes.put("TD", "border");
        postcodePrefixes.put("CH", "border");
        postcodePrefixes.put("SY", "border");
        postcodePrefixes.put("HR", "border");
        postcodePrefixes.put("LL", "GB-WLS");
        postcodePrefixes.put("NP", "GB-WLS");
        postcodePrefixes.put("LD", "GB-WLS");
        postcodePrefixes.put("CF", "GB-WLS");
        postcodePrefixes.put("SA", "GB-WLS");
        postcodePrefixes.put("GY", "Channel Island");
        postcodePrefixes.put("JE", "Channel Island");
        postcodePrefixes.put("BT", "GB-NIR");
        postcodePrefixes.put("IM", "Isle of Man");
    }

    public static String getCountryFromPostcode(String postcode) {
        String postcodePrefix = postcode.substring(0, 2);
        return postcodePrefixes.getOrDefault(postcodePrefix, "GB-ENG");
    }
}
