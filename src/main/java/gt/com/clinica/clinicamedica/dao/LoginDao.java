package gt.com.clinica.clinicamedica.dao;

import gt.com.clinica.clinicamedica.entity.LoginEntity;
import gt.com.clinica.clinicamedica.entity.MedicineEntity;
import gt.com.clinica.clinicamedica.service.ConectionService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LoginDao {

    public List<String> dinamicMenu(LoginEntity user){
        List<String> data = new LinkedList<>();
        Connection conexion = null;
        String query1 = "SELECT * FROM users WHERE name =? and pass =?";
        String query2 = "SELECT * FROM DinamicNav WHERE urange =?";

        try {
            ConectionService con= ConectionService.getInstance();
            conexion = con.getConnection();
            PreparedStatement statement = conexion.prepareStatement(query1);
            statement.setString(1,user.getName());
            statement.setString(2,user.getPass());
            ResultSet consulta=statement.executeQuery();
            ResultSet con2;
            if(consulta.next())
            {
                user.setRange(consulta.getInt("urange"));
                PreparedStatement statement2 = conexion.prepareStatement(query2);
                statement2.setInt(1, user.getRange());
                con2=statement2.executeQuery();
                while (con2.next()) {
                    data.add(con2.getString("name"));
                    System.out.println(con2.getString("name"));
                }
                return data;
            }else{
                data.add("usuario no encontrado");
                return data;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
           data.add("null");
           return data;
        }
    }
}
