
package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private static Connection connection;

    public static Connection getConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                return connection;
            }

            Class.forName("oracle.jdbc.driver.OracleDriver");

            String url = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
            String user = System.getenv("DB_USER");
            String password = System.getenv("DB_PASSWORD");

            if (user == null || password == null) {
                throw new IllegalStateException(
                        "Configure as variáveis de ambiente DB_USER e DB_PASSWORD."
                );
            }

            connection = DriverManager.getConnection(
                    url, user, password
            );

            return connection;

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(
                    "Driver JDBC do Oracle não encontrado.", e
            );
        } catch (SQLException e) {
            throw new RuntimeException(
                    "Erro ao conectar ao banco Oracle.", e
            );
        }
    }

    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                connection = null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(
                    "Erro ao fechar a conexão com o banco.", e
            );
        }
    }
}
