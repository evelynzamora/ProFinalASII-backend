package gt.com.clinica.clinicamedica.controller;

import com.google.gson.Gson;
import gt.com.clinica.clinicamedica.dao.EmployeeDao;
import gt.com.clinica.clinicamedica.dao.PatientDao;
import gt.com.clinica.clinicamedica.entity.EmployeeEntity;
import gt.com.clinica.clinicamedica.entity.PersonEntity;
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

@WebServlet("/crudpatient")
public class PatientServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Date birth =null;
        StringBuffer jb = new StringBuffer();
        String line = null;
        PatientDao patientDao = new PatientDao();
        PersonEntity person = new PersonEntity();

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
            person.setDpi(jsonObject.getInt("dpi"));
            person.setName(jsonObject.getString("name"));
            person.setSurname(jsonObject.getString("lastname"));
            person.setAddress(jsonObject.getString("address"));
            person.setPhone(jsonObject.getInt("phone"));
            try {
                birth = new SimpleDateFormat("dd/MM/yyyy").parse(jsonObject.getString("birthdate"));
            } catch (ParseException e) {
                throw new ServletException("Esta es excepcion de servlet");
            }
            person.setBirthdate(birth);
            person.setContactphone(jsonObject.getInt("contact_phone"));


            if(patientDao.addepatient(person) == false){
                throw new ServletException("Algo salio mal");
            }
        }catch (JsonException e) {
            throw new IOException("Error parsing JSON request");
        }
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Date birth =null;
        StringBuffer jb = new StringBuffer();
        String line = null;
        PatientDao patientDao = new PatientDao();

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

            if(patientDao.deletepatient(jsonObject.getInt("idPerson")) == false){
                throw new ServletException("Algo salio mal");
            }
        }catch (JsonException e) {
            throw new IOException("Error parsing JSON request");
        }
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Date birth =null;
        StringBuffer jb = new StringBuffer();
        String line = null;
        PatientDao patientDao = new PatientDao();
        PersonEntity person = new PersonEntity();

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
            person.setIdPerson(jsonObject.getInt("idPerson"));
            person.setDpi(jsonObject.getInt("dpi"));
            person.setName(jsonObject.getString("name"));
            person.setSurname(jsonObject.getString("lastname"));
            person.setAddress(jsonObject.getString("address"));
            person.setPhone(jsonObject.getInt("phone"));
            try {
                birth = new SimpleDateFormat("dd/MM/yyyy").parse(jsonObject.getString("birthdate"));
            } catch (ParseException e) {
                throw new ServletException("Esta es excepcion de servlet");
            }
            person.setBirthdate(birth);
            person.setContactphone(jsonObject.getInt("contact_phone"));


            if(patientDao.updatepatient(person) == false){
                throw new ServletException("Algo salio mal");
            }
        }catch (JsonException e) {
            throw new IOException("Error parsing JSON request");
        }
    }


        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PatientDao dao = new PatientDao();
        List<PersonEntity> listPatient = dao.listAll();
        List <String> json = new LinkedList<>();
        Gson gson = new Gson();
        try (PrintWriter out = response.getWriter()){
            if(listPatient !=null) {
                for(PersonEntity emp : listPatient) {
                    json.add(gson.toJson(emp));
                }
                out.println(json);
            }else {
                out.println("error");
            }
        }
    }
}
