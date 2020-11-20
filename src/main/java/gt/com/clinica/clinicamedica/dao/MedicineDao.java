package gt.com.clinica.clinicamedica.dao;

import gt.com.clinica.clinicamedica.entity.EmployeeEntity;
import gt.com.clinica.clinicamedica.entity.MedicineEntity;
import gt.com.clinica.clinicamedica.service.ConectionService;
import gt.com.clinica.clinicamedica.service.ICrudMedicine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class MedicineDao implements ICrudMedicine {
    final String selectAllMedicine = "SELECT IdMedicamento,Nombre,Laboratorio,ViaAdministracion,Descripcion,FechaVencimiento,Lote " +
            "FROM medicamentos ORDER BY FechaVencimiento LIMIT 100;";
    @Override
    public List<MedicineEntity> listAll() {
        Date date = null;
        List<MedicineEntity> listMedicine = new LinkedList<>();
        MedicineEntity med = new MedicineEntity();
        Connection conexion = null;

        try{
            ConectionService con= ConectionService.getInstance();
            conexion = con.getConnection();
            PreparedStatement statement = conexion.prepareStatement(selectAllMedicine);
            ResultSet consulta=statement.executeQuery();
            while (consulta.next()) {
                med = new MedicineEntity();
                med.setIdMedicine(consulta.getInt("IdMedicamento"));
                med.setName(consulta.getString("Nombre"));
                med.setLab(consulta.getString("Laboratorio"));
                med.setAdminway(consulta.getString("ViaAdministracion"));
                med.setDescription(consulta.getString("Descripcion"));
                med.setExpirationdate(consulta.getDate("FechaVencimiento"));
                med.setLots(consulta.getInt("Lote"));
                listMedicine.add(med);
            }
            consulta.close();
            return listMedicine;
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
    public boolean deletemedicine(int id) {

        Connection conexion = null;
        try{
            ConectionService con= ConectionService.getInstance();
            conexion = con.getConnection();
            PreparedStatement statement = conexion.prepareStatement("DELETE FROM medicamentos WHERE IdMedicamento = ?;");
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
    public boolean updatemedicine(MedicineEntity med) {
        Connection conexion = null;
        try{
            ConectionService con= ConectionService.getInstance();
            conexion = con.getConnection();
            PreparedStatement statement = conexion.prepareStatement("UPDATE medicamentos SET " +
                    "Nombre=?,Laboratorio=?,ViaAdministracion=?,Descripcion=?,FechaVencimiento=?,Lote=? WHERE IdMedicamento = ?;");
            statement.setString(1,med.getName());
            statement.setString(2,med.getLab());
            statement.setString(3,med.getAdminway());
            statement.setString(4,med.getDescription());
            statement.setDate(5,java.sql.Date.valueOf(med.getExpirationdate()));
            statement.setInt(6,med.getLots());
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
    public boolean addmedicine(MedicineEntity med) {
        Connection conexion = null;
        try{
            ConectionService con= ConectionService.getInstance();
            conexion = con.getConnection();
            PreparedStatement statement = conexion.prepareStatement("INSERT INTO medicamentos " +
                    "(Nombre, Laboratorio, ViaAdministracion, Descripcion, FechaVencimiento, Lote) " +
                    "VALUES (?,?,?,?,?,?);");
            statement.setString(1,med.getName());
            statement.setString(2,med.getLab());
            statement.setString(3,med.getAdminway());
            statement.setString(4,med.getDescription());
            statement.setDate(5,java.sql.Date.valueOf(med.getExpirationdate()));
            statement.setInt(6,med.getLots());
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
