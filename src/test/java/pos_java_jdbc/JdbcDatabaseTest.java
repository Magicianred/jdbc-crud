package pos_java_jdbc;

import java.util.List;

import org.junit.Test;

import dao.UserPosDAO;
import model.Telephone;
import model.Userposjava;

public class JdbcDatabaseTest {
    @Test
    public void initDatabase() {
        UserPosDAO userPosDAO = new UserPosDAO();
        Userposjava userposjava = new Userposjava();

        userposjava.setName("New user2");
        userposjava.setEmail("new2@teste.com");

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
            // Objeto recebe todos os dados do resultado referente ao ID 1
            Userposjava userposjava = dao.search(1L);

            System.out.println(userposjava);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void initUpdate() {
        try {
            UserPosDAO dao = new UserPosDAO();

            Userposjava databaseObject = dao.search(5L);

            databaseObject.setName("Nome modificado com UPDATE");

            dao.update(databaseObject);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void initDelete() {
        try {
            UserPosDAO dao = new UserPosDAO();

            dao.delete(4L);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void insertTelephone() {
        Telephone telephone = new Telephone();

        telephone.setNumber("(88) 97777-7777");
        telephone.setType("Celular");
        telephone.setUser(10L);

        UserPosDAO dao = new UserPosDAO();
        dao.saveTelephone(telephone);
    }
}