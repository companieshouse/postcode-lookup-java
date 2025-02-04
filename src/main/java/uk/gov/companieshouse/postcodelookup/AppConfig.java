package uk.gov.companieshouse.postcodelookup;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public DaoStore daoStore() {
        return new DaoStore() {
            @Override
            public Address getSingleAddress(String postcode) {
                // Implement your logic here
                return new Address(postcode, "ThoroughfareName", "ThoroughfareDescriptor", "DependentLocality", "PostTown", "Country");
            }
        };
    }
}
