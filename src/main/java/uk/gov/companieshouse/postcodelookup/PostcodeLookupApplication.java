package uk.gov.companieshouse.postcodelookup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PostcodeLookupApplication {

    public static void main(String[] args) {
        SpringApplication.run(PostcodeLookupApplication.class, args);
    }

    @Bean
    public PostcodeLookupHandler postcodeLookupHandler(DaoStore daoStore) {
        return new PostcodeLookupHandler(daoStore);
    }
}