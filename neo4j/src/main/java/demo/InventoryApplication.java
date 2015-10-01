package demo;

import demo.product.Product;
import demo.product.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.HashMap;

@SpringBootApplication
@ComponentScan({"demo.config", "demo"})
@EnableTransactionManagement
public class InventoryApplication {
    public static void main(String[] args) {
        SpringApplication.run(InventoryApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(Neo4jConfiguration neo4jConfiguration, ProductRepository productRepository) {
        return args -> {
            productRepository.save(new Product("Staples", "SKU-12345"));
            System.out.println(neo4jConfiguration.getSession().query("MATCH (n) RETURN n;", new HashMap<>()).queryResults());
        };
    }
}
