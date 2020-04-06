package webjava;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Configuration
@ComponentScan(basePackages = {"webjava", "database"})
public class ProdConfiguration {

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
        bean.setPersistenceUnitName("PostgresPersistenceUnit");
        return bean;
    }
}
 /*@Bean
    public EntityManagerFactory getEntityManagerFactory(){
        return Persistence.createEntityManagerFactory("PostgresPersistenceUnit");
    }*/