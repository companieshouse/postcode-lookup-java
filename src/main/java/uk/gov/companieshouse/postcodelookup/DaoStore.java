package uk.gov.companieshouse.postcodelookup;

public interface DaoStore {
    Address getSingleAddress(String postcode) throws Exception;
}
