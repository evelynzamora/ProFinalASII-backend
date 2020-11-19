package gt.com.clinica.clinicamedica.controller;
import gt.com.clinica.clinicamedica.dao.LoginDao;
import gt.com.clinica.clinicamedica.entity.LoginEntity;

import javax.json.JsonException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;

@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
@WebServlet("/login")
public class LoginController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

            StringBuffer jb = new StringBuffer();
            String line = null;
            LoginDao login = new LoginDao();
            LoginEntity user = new LoginEntity();

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
                String name = jsonObject.getString("name");
                String pass = jsonObject.getString("pass");
                user.setName(name);
                user.setPass(pass);

                if(login.login(user) == false){
                    throw new ServletException("Algo salio mal");
                }
            }catch (JsonException e) {
                throw new IOException("Error parsing JSON request");
            }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
