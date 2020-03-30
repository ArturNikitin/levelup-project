package webjava;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@Configuration
public class AppConfiguration {
    @Bean
    public EntityManager getEntityManager(EntityManagerFactory factory){
        return factory.createEntityManager();
    }
}
