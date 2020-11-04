package gt.com.clinica.clinicamedica.controller;

import com.google.gson.Gson;
import gt.com.clinica.clinicamedica.dao.EmployeeDao;
import gt.com.clinica.clinicamedica.entity.EmployeeEntity;

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

@WebServlet("/crudemployee")
public class EmployeeServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Date birth =null;
        EmployeeEntity employee = new EmployeeEntity();
        EmployeeDao dao = new EmployeeDao();
        employee.setIdEmpmloyee(Integer.parseInt(request.getParameter("idEmployee")));
        employee.setDpi(Integer.parseInt(request.getParameter("dpi")));
        employee.setName(request.getParameter("name"));
        employee.setSurname(request.getParameter("surname"));
        employee.setAddress(request.getParameter("address"));
        employee.setPhone(Integer.parseInt(request.getParameter("phone")));
        try {
            birth = new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("birthdate"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        employee.setBirthdate(birth);
        employee.setContactphone(Integer.parseInt(request.getParameter("contactphone")));
        employee.setIdJob(Integer.parseInt(request.getParameter("idJob")));
        try (PrintWriter out = response.getWriter()) {


            if ("create".equals(request.getParameter("btn_create"))) {
                if (dao.addemployee(employee) > 0) {
                    out.println("success");
                }
            }
            if ("update".equals(request.getParameter("btn_update"))) {
                if (dao.updateemployee(employee) > 0) {
                    out.println("success");
                }
            }
            if ("delete".equals(request.getParameter("btn_delete"))) {
                if (dao.deleteemployee(employee.getIdEmpmloyee()) > 0) {
                    out.println("success");
                }
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
