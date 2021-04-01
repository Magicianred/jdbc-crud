package pos_java_jdbc;

import java.util.List;

import org.junit.Test;

import dao.UserPosDAO;
import model.Userposjava;

public class JdbcDatabaseTest {
    @Test
    public void initDatabase() {
        UserPosDAO userPosDAO = new UserPosDAO();
        Userposjava userposjava = new Userposjava();

        userposjava.setId(5L);
        userposjava.setName("Pedro");
        userposjava.setEmail("pedro@teste.com");

        userPosDAO.save(userposjava);
    }

    @Test
    public void initList() {
        UserPosDAO dao = new UserPosDAO();

        try {
            List<Userposjava> list = dao.list();

            for (Userposjava userposjava : list) {
                System.out.println(userposjava);
                System.out.println("----------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void initSearch() {
        UserPosDAO dao = new UserPosDAO();

        try {
            Userposjava userposjava = dao.search(1L);

            System.out.println(userposjava);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}