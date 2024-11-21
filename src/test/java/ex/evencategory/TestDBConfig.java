package ex.evencategory;

import static org.springframework.boot.jdbc.EmbeddedDatabaseConnection.H2;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.ANY;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@AutoConfigureTestDatabase(connection = H2, replace = ANY)
public abstract class TestDBConfig {

}
