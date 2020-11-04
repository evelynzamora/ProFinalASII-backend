package gt.com.clinica.clinicamedica.service;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectionService {
    Connection conn = null;
    private static Connection dataSource;
    private static ConectionService instance;
    public String driver = "com.mysql.jdbc.Driver";
    public String database = "desarrollo";
    public String hostname = "52.186.140.66";
    public String port = "3306";
    public String url = "jdbc:mysql://" + hostname + ":" + port + "/" + database;
    public String username = "user";
    public String password = "Aa1234567+";

    private ConectionService(){
    }

    public static ConectionService getInstance(){
        if(instance==null){
            instance=new ConectionService();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {

        try {
           // dataSource = (DataSource) new InitialContext().lookup("jdbc/final");
            Class.forName(driver);
            dataSource = DriverManager.getConnection(url, username, password);
        }
        catch (ClassNotFoundException e) {
        }
        return dataSource;
    }
}