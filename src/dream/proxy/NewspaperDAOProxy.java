package dream.proxy;

import dream.daoInterface.INewspaperDAO;
import dream.db.DatabaseConnection;
import dream.db.NewspaperDAO;
import dream.entity.Newspaper;

import java.util.LinkedList;

/**
 * Created by lenovo on 2015/6/17.
 */
public class NewspaperDAOProxy implements INewspaperDAO {
    private DatabaseConnection dbc = null;
    private INewspaperDAO dao = null;

    public NewspaperDAOProxy(){
        try {
            this.dbc = new DatabaseConnection();
        }catch (Exception e) {
            e.printStackTrace();
        }
        this.dao = new NewspaperDAO(this.dbc.getConnection());
    }
    @Override
    public boolean insert(Newspaper newspaper) throws Exception {
        boolean flag = false;
        try {
            if (this.dao.insert(newspaper)) {
                flag = this.dao.find(newspaper);
            }
        }catch (Exception e) {
            throw e;
        }
        return flag;
    }

    @Override
    public LinkedList<Newspaper> getAll() throws Exception {

        return this.dao.getAll();
    }

    @Override
    public boolean update(Newspaper newspaper) throws Exception {
        boolean flag = false;
        try {
            if (this.dao.update(newspaper)){
                flag = true;
            }
        }catch (Exception e) {
            throw e;
        }
        return flag;
    }

    @Override
    public boolean delete(Newspaper newspaper) throws Exception {
        boolean flag = false;
        try {
            if (this.dao.delete(newspaper)) {
                flag = !(this.dao.find(newspaper));
            }
        }catch (Exception e) {
            throw e;
        }
        return flag;
    }

    @Override
    public boolean find(Newspaper newspaper) throws Exception {
        return this.dao.find(newspaper);
    }

}
