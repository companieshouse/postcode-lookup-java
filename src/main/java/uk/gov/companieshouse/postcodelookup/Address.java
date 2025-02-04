package uk.gov.companieshouse.postcodelookup;

public class Address {
    private String postcode;
    private String thoroughfareName;
    private String thoroughfareDescriptor;
    private String dependentLocality;
    private String postTown;
    private String country;

    // Constructor, getters, and setters
    public Address(String postcode, String thoroughfareName, String thoroughfareDescriptor, String dependentLocality, String postTown, String country) {
        this.postcode = postcode;
        this.thoroughfareName = thoroughfareName;
        this.thoroughfareDescriptor = thoroughfareDescriptor;
        this.dependentLocality = dependentLocality;
        this.postTown = postTown;
        this.country = country;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getThoroughfareName() {
        return thoroughfareName;
    }

    public String getThoroughfareDescriptor() {
        return thoroughfareDescriptor;
    }

    public String getDependentLocality() {
        return dependentLocality;
    }

    public String getPostTown() {
        return postTown;
    }

    public String getCountry() {
        return country;
    }
}

