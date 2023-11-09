package net.exotia.plugins.calendar.database;

import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.injector.annotation.PostConstruct;
import org.bukkit.plugin.Plugin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseService {
    @Inject private Plugin plugin;

    @PostConstruct
    public void onConstruct() {
        this.makeSchemaMigration();
    }

    void makeQuery(ThrowingConsumer<Connection, SQLException> operation) {
        Connection connection = null;
        try {
            String dbUrl = String.format("jdbc:sqlite:%s/storage.db", this.plugin.getDataFolder().getPath());
            connection = DriverManager.getConnection(dbUrl);
            operation.accept(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println("Błąd przy zamykaniu połączenia: " + e.getMessage());
            }
        }
    }

    private void makeSchemaMigration() {
        this.makeQuery(connection -> {
            Statement statement = connection.createStatement();
            statement.execute(
                    "CREATE TABLE IF NOT EXISTS calendar (" +
                            "uniqueId TEXT PRIMARY KEY," +
                            "step INTEGER," +
                            "lastObtained TEXT," +
                            "streak INTEGER," +
                            "obtainedRewards TEXT);"
            );
            statement.close();
        });
    }
}
