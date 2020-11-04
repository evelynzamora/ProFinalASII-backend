package gt.com.clinica.clinicamedica.controller;

import com.google.gson.Gson;
import gt.com.clinica.clinicamedica.dao.EmployeeDao;
import gt.com.clinica.clinicamedica.dao.MedicineDao;
import gt.com.clinica.clinicamedica.entity.EmployeeEntity;
import gt.com.clinica.clinicamedica.entity.MedicineEntity;

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

@WebServlet("/crudmedicine")
public class MedicineServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Date exp =null;
        MedicineEntity medicine = new MedicineEntity();
        MedicineDao dao = new MedicineDao();

        medicine.setIdMedicine(Integer.parseInt(request.getParameter("idMedicine")));
        medicine.setName(request.getParameter("name"));
        medicine.setLab(request.getParameter("lab"));
        medicine.setAdminway(request.getParameter("adminway"));
        medicine.setDescription(request.getParameter("description"));
        try {
            exp = new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("expirationdate"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        medicine.setExpirationdate(exp);
        medicine.setLots(Integer.parseInt(request.getParameter("lots")));
        try (PrintWriter out = response.getWriter()) {
            if ("create".equals(request.getParameter("btn_create"))) {
                if (dao.addmedicine(medicine) > 0) {
                    out.println("success");
                }
            }
            if ("update".equals(request.getParameter("btn_update"))) {
                if (dao.updatemedicine(medicine) > 0) {
                    out.println("success");
                }
            }
            if ("delete".equals(request.getParameter("btn_delete"))) {
                if (dao.deletemedicine(medicine.getIdMedicine()) > 0) {
                    out.println("success");
                }
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
