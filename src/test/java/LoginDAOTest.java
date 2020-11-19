import gt.com.clinica.clinicamedica.dao.LoginDao;
import gt.com.clinica.clinicamedica.entity.LoginEntity;

import static org.junit.Assert.assertFalse;
import org.junit.Test;


public class LoginDAOTest {
    @Test
    public void whenSelectUser_thenReturnFalse()  {
        LoginDao login = new LoginDao();
        LoginEntity user = new LoginEntity();
        user.setName("Dan");
        user.setPass("1234");
        assertFalse(login.login(user));
    }




    @Test
    public void whenIntegrationSelectUser_thenReturnTrue(){

    }
}
