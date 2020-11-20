package gt.com.clinica.clinicamedica.dao;

import gt.com.clinica.clinicamedica.entity.EmployeeEntity;
import gt.com.clinica.clinicamedica.entity.PersonEntity;
import gt.com.clinica.clinicamedica.service.ConectionService;
import gt.com.clinica.clinicamedica.service.ICrudPatient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class PatientDao implements ICrudPatient {
    final String selectAllPatients = "SELECT id_person,direccion,fecha_nacimiento,telefono_contacto,dpi,nombre,telefono,apellido " +
            "FROM pacientes ORDER BY nombre limit 100;";
    @Override
    public List<PersonEntity> listAll() {
        Date date = null;
        List<PersonEntity> listEmployee = new LinkedList<>();
        PersonEntity emp = new PersonEntity();
        Connection conexion = null;

        try{
            ConectionService con= ConectionService.getInstance();
            conexion = con.getConnection();
            PreparedStatement statement = conexion.prepareStatement(selectAllPatients);
            ResultSet consulta=statement.executeQuery();
            while (consulta.next()) {
                emp = new PersonEntity();
                emp.setIdPerson(consulta.getInt("idPaciente"));
                emp.setDpi(consulta.getInt("DPI"));
                emp.setName(consulta.getString("Nombre"));
                emp.setSurname(consulta.getString("Apellido"));
                emp.setAddress(consulta.getString("Direccion"));
                emp.setPhone(consulta.getInt("Telefono"));
                emp.setBirthdate(consulta.getDate("FechaNacimiento"));
                emp.setContactphone(consulta.getInt("TelefonoContacto"));
                listEmployee.add(emp);
            }
            consulta.close();
            return listEmployee;
        }catch (SQLException e){
            System.err.println("ERROR FATAL! ");
            System.err.println(e.getMessage());
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
    public boolean deletepatient(int id) {
        Connection conexion = null;
        try{
            ConectionService con= ConectionService.getInstance();
            conexion = con.getConnection();
            PreparedStatement statement = conexion.prepareStatement("DELETE FROM pacientes WHERE id_person = ?;");
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
    public boolean updatepatient(PersonEntity patient) {
        Connection conexion = null;
        try{
            ConectionService con= ConectionService.getInstance();
            conexion = con.getConnection();
            PreparedStatement statement = conexion.prepareStatement("UPDATE pacientes SET " +
                    "direccion=?,fecha_nacimiento=?,telefono_contacto=?,dpi=?,nombre=?,telefono=?,apellido=? " +
                    "WHERE id_person = ?;");
            statement.setString(1,patient.getAddress());
            statement.setString(2,patient.getBirthdate());
            statement.setInt(3,patient.getContactphone());
            statement.setInt(4,patient.getDpi());
            statement.setString(5,patient.getName());
            statement.setInt(6,patient.getPhone());
            statement.setString(7,patient.getSurname());
            statement.setInt(8,patient.getIdPerson());
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
    public boolean addepatient(PersonEntity patient) {
        Connection conexion = null;
        try{
            ConectionService con= ConectionService.getInstance();
            conexion = con.getConnection();
            PreparedStatement statement = conexion.prepareStatement("INSERT INTO pacientes " +
                    "(direccion, fecha_nacimiento, telefono_contacto, dpi, nombre, telefono, apellido) " +
                    "VALUES (?,?,?,?,?,?,?);");
            statement.setString(1,patient.getAddress());
            statement.setString(2,patient.getBirthdate());
            statement.setInt(3,patient.getContactphone());
            statement.setInt(4,patient.getDpi());
            statement.setString(5,patient.getName());
            statement.setInt(6,patient.getPhone());
            statement.setString(7,patient.getSurname());
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
