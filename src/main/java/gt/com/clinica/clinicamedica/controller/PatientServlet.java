package gt.com.clinica.clinicamedica.controller;

import com.google.gson.Gson;
import gt.com.clinica.clinicamedica.dao.EmployeeDao;
import gt.com.clinica.clinicamedica.dao.PatientDao;
import gt.com.clinica.clinicamedica.entity.EmployeeEntity;
import gt.com.clinica.clinicamedica.entity.PersonEntity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        PersonEntity patient = new PersonEntity();
        PatientDao dao = new PatientDao();
        patient.setDpi(Integer.parseInt(request.getParameter("dpi")));
        patient.setName(request.getParameter("name"));
        patient.setSurname(request.getParameter("surname"));
        patient.setAddress(request.getParameter("address"));
        patient.setPhone(Integer.parseInt(request.getParameter("phone")));
        try {
            birth = new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("birthdate"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        patient.setBirthdate(birth);
        patient.setContactphone(Integer.parseInt(request.getParameter("contactphone")));
        try (PrintWriter out = response.getWriter()) {


            if ("create".equals(request.getParameter("btn_create"))) {
                if (dao.addepatient(patient) > 0) {
                    out.println("success");
                }
            }
            if ("update".equals(request.getParameter("btn_update"))) {
                if (dao.updatepatient(patient) > 0) {
                    out.println("success");
                }
            }
            if ("delete".equals(request.getParameter("btn_delete"))) {
                if (dao.deletepatient(patient.getDpi()) > 0) {
                    out.println("success");
                }
            }
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
                    out.println(gson.toJson(emp));
                }
            }else {
                out.println("error");
            }
        }
    }
}
