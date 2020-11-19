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

    public boolean login(LoginEntity user){
        Connection conexion = null;
        String query1 = "SELECT name, pass FROM users WHERE name =? and pass =?";
        try{
            ConectionService con= ConectionService.getInstance();
            conexion = con.getConnection();
            PreparedStatement statement = conexion.prepareStatement(query1);
            statement.setString(1,user.getName());
            statement.setString(2,user.getPass());
            ResultSet consulta = statement.executeQuery();
            if(consulta.next()){
                conexion.close();
                return true;
            }else{
                conexion.close();
                return false;
            }
        }catch (Exception e){
            e.getCause();
        }
        return false;
    }
}
