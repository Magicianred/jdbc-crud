package conexaojdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnection {
    private static String url = "jdbc:postgresql://localhost:5432/posjava";
    private static String password = "postgres";
    private static String user = "postgres";
    private static Connection connection = null;

    // Bloco static roda assim que a classe é carregada, setando o connection
    static {
        connect();
    }

    private static void connect() {
        try {
            if (connection == null) {
                Class.forName("org.postgresql.Driver"); // Carrega o Driver
                connection = DriverManager.getConnection(url, user, password);
                connection.setAutoCommit(false); // Não salvar automaticamente os dados no banco
                
                System.out.println("Successful connection");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}