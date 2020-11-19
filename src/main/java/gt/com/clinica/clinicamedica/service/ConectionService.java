package gt.com.clinica.clinicamedica.service;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConectionService {
    Connection conn = null;
    private static Connection dataSource;
    private static ConectionService instance;

    private ConectionService(){
    }

    public static ConectionService getInstance(){
        if(instance==null){
            instance=new ConectionService();
        }
        return instance;
    }

    public Connection getConnection(){

        try {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource)ctx.lookup("JDBC/clinicaweb");
            dataSource = ds.getConnection();
        }
        catch (NamingException | SQLException e) {
        }
        return dataSource;
    }
}