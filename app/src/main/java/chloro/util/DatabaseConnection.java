package chloro.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

    private Connection databaseConnect;

    public Connection connect() {
        String url = "jdbc:mysql://localhost:3306/chloro";
        String username = "root";
        String password = "password";

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Gagal koneksi: " + ex);
        }

        try {
            databaseConnect = DriverManager.getConnection(url, username, password);
        } catch (Exception ex) {
            System.out.println("Gagal koneksi Database: " + ex);
        }

        return databaseConnect;
    }
}
