package database.DAO.impl;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Component;
import webjava.AppConfiguration;
import webjava.ProdConfiguration;
import webjava.WebConfiguration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Component
@ComponentScan(basePackages = {"webjava", "database"},
        excludeFilters = @ComponentScan.Filter(
            type = FilterType.ASSIGNABLE_TYPE,
            classes = {ProdConfiguration.class, WebConfiguration.class, AppConfiguration.class}
        ))

public class TestConfiguration {
    @Bean
    public EntityManagerFactory getEntityManagerFactory() {
        return Persistence.createEntityManagerFactory("TestPersistenceUnit");
    }

    @Bean
    public EntityManager entityManager(EntityManagerFactory factory) {
        return factory.createEntityManager();
    }
}
