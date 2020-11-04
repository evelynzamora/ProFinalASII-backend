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
    final String selectAllPatients = "SELECT * FROM Pacientes";
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
    public int deletepatient(int dpi) {
        return 0;
    }

    @Override
    public int updatepatient(PersonEntity patient) {
        return 0;
    }

    @Override
    public int addepatient(PersonEntity patient) {
        return 0;
    }
}
