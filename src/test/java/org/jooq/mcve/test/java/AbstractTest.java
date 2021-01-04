package org.jooq.mcve.test.java;

import java.sql.Connection;
import java.sql.DriverManager;
import org.flywaydb.core.Flyway;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.conf.RenderQuotedNames;
import org.jooq.conf.Settings;
import org.jooq.impl.DefaultDSLContext;
import org.junit.After;
import org.junit.Before;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public abstract class AbstractTest {

  private static String USERNAME = "jooq";
  private static String PASSWORD = "jooq";

  public Connection connection;
  public DSLContext ctx;

  @Container
  private PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer("postgres:12.4")
      .withUsername(USERNAME)
      .withPassword(PASSWORD);

  @Before
  public void setup() throws Exception {
    postgreSQLContainer.start();

    Flyway flyway =
        Flyway.configure().dataSource(postgreSQLContainer.getJdbcUrl(), USERNAME, PASSWORD)
            .locations("db/migration")
            .load();
    flyway.migrate();

    connection = DriverManager
        .getConnection(postgreSQLContainer.getJdbcUrl(), USERNAME, PASSWORD);

    ctx = new DefaultDSLContext(connection,
        SQLDialect.POSTGRES, new Settings()
        .withRenderCatalog(false)
        .withRenderSchema(false)
        .withRenderQuotedNames(RenderQuotedNames.NEVER));
  }

  @After
  public void after() throws Exception {
    ctx = null;
    connection.close();
    connection = null;
  }
}
