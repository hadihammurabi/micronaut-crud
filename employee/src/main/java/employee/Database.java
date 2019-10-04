package employee;

import java.util.HashMap;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

import io.micronaut.context.ApplicationContext;
import io.micronaut.context.env.Environment;

public class Database {
  private static Connection connection;
  private static HashMap<String, String> config;

  private static void configure() {
    Environment env = ApplicationContext.run().getEnvironment();
    String host = env.getProperty("micronaut.database.host", String.class).orElse("localhost");
    String user = env.getProperty("micronaut.database.user", String.class).orElse("root");
    String pass = env.getProperty("micronaut.database.pass", String.class).orElse("");
    String name = env.getProperty("micronaut.database.name", String.class).orElse("");

    config = new HashMap();

    config.put("host", host);
    config.put("user", user);
    config.put("pass", pass);
    config.put("name", name);
  }

  public static Connection getConnection() throws SQLException {
    if (connection == null) {
      configure();
      connection = DriverManager.getConnection(
        "jdbc:mariadb://"+config.get("host")+"/"+config.get("name"), 
        config.get("user"),
        config.get("pass")
      );
    }
    return connection;
  }
}
