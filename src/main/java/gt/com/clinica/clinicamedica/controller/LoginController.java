package gt.com.clinica.clinicamedica.controller;

import com.google.gson.Gson;
import com.sun.java.swing.plaf.motif.MotifPasswordFieldUI;
import gt.com.clinica.clinicamedica.dao.LoginDao;
import gt.com.clinica.clinicamedica.entity.EmployeeEntity;
import gt.com.clinica.clinicamedica.entity.LoginEntity;
import sun.awt.windows.WPrinterJob;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ClientInfoStatus;
import java.util.LinkedList;
import java.util.List;

@WebServlet("/login")
public class LoginController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List <String> json = new LinkedList<>();
        Gson gson = new Gson();
        LoginDao login = new LoginDao();
        List<String> data = new LinkedList<>();
        LoginEntity user = new LoginEntity();
        System.out.println(request.getParameter("name"));
        System.out.println(request.getParameter("pass"));
        user.setName(request.getParameter("name"));
        user.setPass(request.getParameter("pass"));
        data = login.dinamicMenu(user);
        System.out.println(data.get(0));
        try (PrintWriter out = response.getWriter()){
            if(data.get(0) !="null"){
                if(data !=null) {
                    for(String nav : data) {
                        json.add(gson.toJson(nav));
                    }
                    out.println(json);
                }else {
                    out.println("error");
                }
            }else {
                out.println("No existe el usuario");
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
