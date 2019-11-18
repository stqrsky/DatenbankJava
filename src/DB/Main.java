package DB;


import java.sql.*;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        // laden des Treibers der in Modulen hinzugefügt werden muss
        Class.forName("com.mysql.jdbc.Driver");
        //Aufbau der Verbindung ?characterEncoding=Latin1 steht für das zeichenformat
        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test?characterEncoding=Latin1", Daten.User, Daten.PW);
        Statement statement = connection.createStatement();
        //Einfügen eines Tupels
        statement.executeUpdate("insert into kunde values (67,'Koch','Heinz','Hamburg','22111')");
        dbout(statement);
        statement.close();
        connection.close();
    }

    private static void dbout(Statement statement) throws SQLException {
        //ResultSet mit dem Abfrage Statment ausführen
        //todo Wichtig * nicht in abfrage verwenden da die DB die anfrage auuflösen muss
        ResultSet result = statement.executeQuery("select * from kunde");
        while (result.next()) {
            //Variablen den Werten aus der datenbank zuweisen
            int knr = result.getInt("knr");
            String nachname = result.getString("nachname");
            String vorname = result.getString("vorname");
            System.out.println(knr + " " + nachname + " " + vorname);
        }
        result.close();

    }
}