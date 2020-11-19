package gt.com.clinica.clinicamedica.controller;

import com.google.gson.Gson;
import gt.com.clinica.clinicamedica.dao.EmployeeDao;
import gt.com.clinica.clinicamedica.dao.LoginDao;
import gt.com.clinica.clinicamedica.entity.EmployeeEntity;
import gt.com.clinica.clinicamedica.entity.LoginEntity;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;

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

@WebServlet("/crudemployee")
public class EmployeeServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Date birth =null;
        StringBuffer jb = new StringBuffer();
        String line = null;
        EmployeeDao employeeDao = new EmployeeDao();
        EmployeeEntity employee = new EmployeeEntity();

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
            employee.setDpi(jsonObject.getInt("dpi"));
            employee.setName(jsonObject.getString("name"));
            employee.setSurname(jsonObject.getString("lastname"));
            employee.setAddress(jsonObject.getString("address"));
            employee.setPhone(jsonObject.getInt("phone"));
            try {
                birth = new SimpleDateFormat("dd/MM/yyyy").parse(jsonObject.getString("birthdate"));
            } catch (ParseException e) {
                throw new ServletException("Esta es excepcion de servlet");
            }
            employee.setBirthdate(birth);
            employee.setContactphone(jsonObject.getInt("contact_phone"));
            employee.setIdJob(jsonObject.getInt("idJob"));


            if(employeeDao.addemployee(employee) == false){
                throw new ServletException("Algo salio mal");
            }
        }catch (JsonException e) {
            throw new IOException("Error parsing JSON request");
        }
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException,IOException{
        Date birth =null;
        StringBuffer jb = new StringBuffer();
        String line = null;
        EmployeeDao employeeDao = new EmployeeDao();
        EmployeeEntity employee = new EmployeeEntity();

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
            employee.setIdEmpmloyee(jsonObject.getInt("idEmployee"));
            employee.setDpi(jsonObject.getInt("dpi"));
            employee.setName(jsonObject.getString("name"));
            employee.setSurname(jsonObject.getString("lastname"));
            employee.setAddress(jsonObject.getString("address"));
            employee.setPhone(jsonObject.getInt("phone"));
            try {
                birth = new SimpleDateFormat("dd/MM/yyyy").parse(jsonObject.getString("birthdate"));
            } catch (ParseException e) {
                throw new ServletException("Esta es excepcion de servlet");
            }
            employee.setBirthdate(birth);
            employee.setContactphone(jsonObject.getInt("contact_phone"));
            employee.setIdJob(jsonObject.getInt("idJob"));


            if(employeeDao.updateemployee(employee) == false){
                throw new ServletException("Algo salio mal");
            }
        }catch (JsonException e) {
            throw new IOException("Error parsing JSON request");
        }

    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException,IOException{
        StringBuffer jb = new StringBuffer();
        String line = null;
        EmployeeDao employeeDao = new EmployeeDao();
        EmployeeEntity employee = new EmployeeEntity();

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

            if(employeeDao.deleteemployee(jsonObject.getInt("idEmployee")) == false){
                throw new ServletException("Algo salio mal");
            }
        }catch (JsonException e) {
            throw new IOException("Error parsing JSON request");
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EmployeeDao dao = new EmployeeDao();
        List<EmployeeEntity> listEmployee = dao.listAll();
        List <String> json = new LinkedList<>();
        Gson gson = new Gson();
        try (PrintWriter out = response.getWriter()){
            if(listEmployee !=null) {
                for(EmployeeEntity emp : listEmployee) {
                    json.add(gson.toJson(emp));
                }
                out.println(json);
            }else {
                out.println("error");
            }
        }
    }
}
