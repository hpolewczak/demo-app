package hadrian.demo.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import org.bson.UuidRepresentation;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@Configuration
@EnableReactiveMongoRepositories(basePackages = "hadrian.demo.domain")
public class ReactiveDatabaseConfiguration extends AbstractReactiveMongoConfiguration {

    @Override
    protected MongoClientSettings mongoClientSettings() {
        MongoClientSettings.Builder builder = MongoClientSettings.builder();
        builder.applyConnectionString(new ConnectionString("mongodb://localhost:27017"));
        builder.uuidRepresentation(UuidRepresentation.JAVA_LEGACY);
        this.configureClientSettings(builder);
        return builder.build();
    }

    @Override
    protected String getDatabaseName() {
        return "demo-app";
    }
}
