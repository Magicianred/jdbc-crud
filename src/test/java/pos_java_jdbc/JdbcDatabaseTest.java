package pos_java_jdbc;

import org.junit.Test;

import dao.UserPosDAO;
import model.Userposjava;

public class JdbcDatabaseTest {
    @Test
    public void initDatabase() {
        UserPosDAO userPosDAO = new UserPosDAO();
        Userposjava userposjava = new Userposjava();

        userposjava.setId(6L);
        userposjava.setName("Paulo");
        userposjava.setEmail("paulo@teste.com");

        userPosDAO.save(userposjava);
    }
}