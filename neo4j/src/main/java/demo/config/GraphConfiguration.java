package demo.config;


import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.server.Neo4jServer;
import org.springframework.data.neo4j.server.RemoteServer;

/**
 * Manages the configuration for a Neo4j graph database server
 *
 * @author Kenny Bastani
 */
@EnableNeo4jRepositories(basePackages = "demo")
@Configuration
public class GraphConfiguration extends Neo4jConfiguration {

    @Value("${neo4j.uri}")
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Bean
    public Neo4jServer neo4jServer() {
        return new RemoteServer(url);
    }

    @Bean
    public SessionFactory getSessionFactory() {
        return new SessionFactory("demo.product", "demo.shipment", "demo.warehouse");
    }

    @Bean
    public Session getSession() throws Exception {
        return super.getSession();
    }
}
