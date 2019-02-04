package spring.test;

import org.h2.tools.Server;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootApplication
@Configuration
@ComponentScan(excludeFilters = @ComponentScan.Filter(pattern = ".*Test.*", type = FilterType.REGEX))
class TestConfiguration {

    @Primary
    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setName("pvqms")
                .setType(EmbeddedDatabaseType.H2)
                .build();
    }

    @Primary
    @Bean
    Server h2Server() throws SQLException {
        return null;
    }

    @Primary
    @Bean
    Server h2WebServer() throws SQLException {
        return null;
    }

    @Bean
    SomeBean someBean() {
        return new SomeBean();
    }
}