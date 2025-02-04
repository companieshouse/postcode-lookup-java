package uk.gov.companieshouse.postcodelookup;

public class PremiseAddress {
    private String postcode;
    private String addressLine1;
    private String addressLine2;
    private String postTown;
    private String country;

    // Default constructor
    public PremiseAddress() {
    }

    // Constructor with parameters
    public PremiseAddress(String postcode, String addressLine1, String addressLine2, String postTown, String country) {
        this.postcode = postcode;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.postTown = postTown;
        this.country = country;
    }

    // Getters and setters
    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getPostTown() {
        return postTown;
    }

    public void setPostTown(String postTown) {
        this.postTown = postTown;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    // Override equals and hashCode for comparison in tests
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PremiseAddress that = (PremiseAddress) o;

        if (!postcode.equals(that.postcode)) return false;
        if (!addressLine1.equals(that.addressLine1)) return false;
        if (!addressLine2.equals(that.addressLine2)) return false;
        if (!postTown.equals(that.postTown)) return false;
        return country.equals(that.country);
    }

    @Override
    public int hashCode() {
        int result = postcode.hashCode();
        result = 31 * result + addressLine1.hashCode();
        result = 31 * result + addressLine2.hashCode();
        result = 31 * result + postTown.hashCode();
        result = 31 * result + country.hashCode();
        return result;
    }
}