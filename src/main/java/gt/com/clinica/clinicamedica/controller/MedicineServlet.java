package gt.com.clinica.clinicamedica.controller;

import com.google.gson.Gson;
import gt.com.clinica.clinicamedica.dao.EmployeeDao;
import gt.com.clinica.clinicamedica.dao.MedicineDao;
import gt.com.clinica.clinicamedica.entity.EmployeeEntity;
import gt.com.clinica.clinicamedica.entity.MedicineEntity;
import org.json.JSONObject;

import javax.json.JsonException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@WebServlet("/crudmedicine")
public class MedicineServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Date date =null;
        StringBuffer jb = new StringBuffer();
        String line = null;
        MedicineDao medicineDao = new MedicineDao();
        MedicineEntity medicine = new MedicineEntity();

        try{
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null) {
                jb.append(line);
            }
        }catch (Exception e){
            throw e;
        }

        try{
            JSONObject jsonObject = new JSONObject(jb.toString());
            medicine.setIdMedicine(jsonObject.getInt("idMedicine"));
            medicine.setName(jsonObject.getString("name"));
            medicine.setLab(jsonObject.getString("lab"));
            medicine.setAdminway(jsonObject.getString("adminway"));
            medicine.setDescription(jsonObject.getString("description"));
            try {
                date = new SimpleDateFormat("dd/MM/yyyy").parse(jsonObject.getString("expirationdate"));
            } catch (ParseException e) {
                throw new ServletException("Esta es excepcion de servlet");
            }
            medicine.setExpirationdate(date);
            medicine.setLots(jsonObject.getInt("lots"));


            if(medicineDao.addmedicine(medicine) == false){
                throw new ServletException("Algo salio mal");
            }
        }catch (JsonException e) {
            throw new IOException("Error parsing JSON request");
        }
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        Date date =null;
        StringBuffer jb = new StringBuffer();
        String line = null;
        MedicineDao medicineDao = new MedicineDao();
        MedicineEntity medicine = new MedicineEntity();

        try{
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null) {
                jb.append(line);
            }
        }catch (Exception e){
            throw e;
        }

        try{
            JSONObject jsonObject = new JSONObject(jb.toString());
            medicine.setIdMedicine(jsonObject.getInt("idMedicine"));
            medicine.setName(jsonObject.getString("name"));
            medicine.setLab(jsonObject.getString("lab"));
            medicine.setAdminway(jsonObject.getString("adminway"));
            medicine.setDescription(jsonObject.getString("description"));
            try {
                date = new SimpleDateFormat("dd/MM/yyyy").parse(jsonObject.getString("expirationdate"));
            } catch (ParseException e) {
                throw new ServletException("Esta es excepcion de servlet");
            }
            medicine.setExpirationdate(date);
            medicine.setLots(jsonObject.getInt("lots"));


            if(medicineDao.updatemedicine(medicine) == false){
                throw new ServletException("Algo salio mal");
            }
        }catch (JsonException e) {
            throw new IOException("Error parsing JSON request");
        }
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        Date date =null;
        StringBuffer jb = new StringBuffer();
        String line = null;
        MedicineDao medicineDao = new MedicineDao();
        MedicineEntity medicine = new MedicineEntity();

        try{
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null) {
                jb.append(line);
            }
        }catch (Exception e){
            throw e;
        }

        try{
            JSONObject jsonObject = new JSONObject(jb.toString());
            if(medicineDao.deletemedicine(jsonObject.getInt("idMedicine")) == false){
                throw new ServletException("Algo salio mal");
            }
        }catch (JsonException e) {
            throw new IOException("Error parsing JSON request");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        MedicineDao dao = new MedicineDao();
        List<MedicineEntity> listMedicine = dao.listAll();
        List <String> json = new LinkedList<>();
        Gson gson = new Gson();
        try (PrintWriter out = response.getWriter()){
            if(listMedicine !=null) {
                for(MedicineEntity emp : listMedicine) {
                    json.add(gson.toJson(emp));
                }
                out.println(json);
            }else {
                out.println("error");
            }
        }
    }
}
