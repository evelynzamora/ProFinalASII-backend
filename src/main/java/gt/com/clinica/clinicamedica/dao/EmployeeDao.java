package gt.com.clinica.clinicamedica.dao;

import gt.com.clinica.clinicamedica.entity.EmployeeEntity;
import gt.com.clinica.clinicamedica.service.ConectionService;
import gt.com.clinica.clinicamedica.service.ICrudEmployee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class EmployeeDao implements ICrudEmployee {

    @Override
    public List<EmployeeEntity> listAll() {
        final String selectAllEmployee = "SELECT e.IdEmpleado,e.DPI,e.Nombre,e.Apellido,e.Direccion,e.Telefono,e.FechaNacimiento,e.TelefonoContacto,p.Puesto as Puesto " +
                "FROM empleado as e,puesto as p where p.IdPuesto = e.IdPuesto order by Nombre limit 100;";
        Date date = null;
        List<EmployeeEntity> listEmployee = new LinkedList<>();
        EmployeeEntity emp = new EmployeeEntity();
        Connection conexion = null;

        try{
            ConectionService con= ConectionService.getInstance();
            conexion = con.getConnection();
            PreparedStatement statement = conexion.prepareStatement(selectAllEmployee);
            ResultSet consulta=statement.executeQuery();
            while (consulta.next()) {
                emp = new EmployeeEntity();
                emp.setIdEmpmloyee(consulta.getInt("IdEmpleado"));
                emp.setDpi(consulta.getInt("DPI"));
                emp.setName(consulta.getString("Nombre"));
                emp.setSurname(consulta.getString("Apellido"));
                emp.setAddress(consulta.getString("Direccion"));
                emp.setPhone(consulta.getInt("Telefono"));
                emp.setBirthdate(consulta.getDate("FechaNacimiento"));
                emp.setContactphone(consulta.getInt("TelefonoContacto"));
                emp.setJob(consulta.getString("Puesto"));
                listEmployee.add(emp);
            }
            consulta.close();
            return listEmployee;
        }catch (SQLException e){
            System.out.println("no vamos bien");
            return null;
        }finally {
            try {
                if(conexion !=null) {
                    conexion.close();
                }else{
                    System.out.println("no hay conexion y no se cierra");
                    return null;
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    @Override
    public boolean deleteemployee(int id){
        Connection conexion = null;
        try{
            ConectionService con= ConectionService.getInstance();
            conexion = con.getConnection();
            PreparedStatement statement = conexion.prepareStatement("DELETE FROM empleado WHERE IdEmpleado = ?;");
            statement.setInt(1,id);
            ResultSet consulta = statement.executeQuery();
            if(!consulta.wasNull()){
                conexion.close();
                return true;
            }else{
                conexion.close();
                return false;
            }
        }catch (SQLException sqlException){
             sqlException.getSQLState();
        }
        return false;
    }

    @Override
    public boolean updateemployee(EmployeeEntity emp) {
        Connection conexion = null;
        try{
            ConectionService con= ConectionService.getInstance();
            conexion = con.getConnection();
            PreparedStatement statement = conexion.prepareStatement("UPDATE empleado SET " +
                    "DPI=?,Nombre=?,Apellido=?,Direccion=?,Telefono=?,FechaNacimiento=?,TelefonoContacto=?,IdPuesto=? WHERE IdEmpleado = ?;");
            statement.setInt(1,emp.getDpi());
            statement.setString(2,emp.getName());
            statement.setString(3,emp.getSurname());
            statement.setString(4,emp.getAddress());
            statement.setInt(5,emp.getPhone());
            statement.setDate(6,java.sql.Date.valueOf(emp.getBirthdate()));
            statement.setInt(7,emp.getContactphone());
            statement.setInt(8,emp.getIdJob());
            statement.setInt(9,emp.getIdEmpmloyee());
            ResultSet consulta = statement.executeQuery();
            if(!consulta.wasNull()){
                conexion.close();
                return true;
            }else{
                conexion.close();
                return false;
            }

        }catch (SQLException sqlException){
            sqlException.getSQLState();
        }
        return false;
    }

    @Override
    public boolean addemployee(EmployeeEntity emp) {
        Connection conexion = null;
        try{
            ConectionService con= ConectionService.getInstance();
            conexion = con.getConnection();
            PreparedStatement statement = conexion.prepareStatement("INSERT INTO empleado (DPI, Nombre, Apellido, Direccion, Telefono, FechaNacimiento, TelefonoContacto, IdPuesto) " +
                    "VALUES (?,?,?,?,?,?,?,?);");
            statement.setInt(1,emp.getDpi());
            statement.setString(2,emp.getName());
            statement.setString(3,emp.getSurname());
            statement.setString(4,emp.getAddress());
            statement.setInt(5,emp.getPhone());
            statement.setDate(6,java.sql.Date.valueOf(emp.getBirthdate()));
            statement.setInt(7,emp.getContactphone());
            statement.setInt(8,emp.getIdJob());
            ResultSet consulta = statement.executeQuery();
            if(!consulta.wasNull()){
                conexion.close();
                return true;
            }else{
                conexion.close();
                return false;
            }

        }catch (SQLException sqlException){
            sqlException.getSQLState();
        }
        return false;
    }
}
