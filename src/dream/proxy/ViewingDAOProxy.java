package dream.proxy;

import dream.daoInterface.IViewingDAO;
import dream.db.DatabaseConnection;
import dream.db.ViewingDAO;
import dream.entity.Viewing;

import java.util.LinkedList;

/**
 * Created by lenovo on 2015/6/17.
 */
public class ViewingDAOProxy implements IViewingDAO {
    private DatabaseConnection dbc = null;
    private IViewingDAO dao = null;

    public ViewingDAOProxy(){
        try {
            this.dbc = new DatabaseConnection();
        }catch (Exception e) {
            e.printStackTrace();
        }
        this.dao = new ViewingDAO(this.dbc.getConnection());
    }
    @Override
    public boolean insert(Viewing viewing) throws Exception {
        boolean flag = false;
        try {
            if (this.dao.insert(viewing)) {
                flag = this.dao.find(viewing);
            }
        }catch (Exception e) {
            throw e;
        }
        return flag;
    }

    @Override
    public LinkedList<Viewing> getAll() throws Exception {

        return this.dao.getAll();
    }

    @Override
    public boolean update(Viewing viewing) throws Exception {
        boolean flag = false;
        try {
            if (this.dao.update(viewing)){
                flag = true;
            }
        }catch (Exception e) {
            throw e;
        }
        return flag;
    }

    @Override
    public boolean delete(Viewing viewing) throws Exception {
        boolean flag = false;
        try {
            if (this.dao.delete(viewing)) {
                flag = !(this.dao.find(viewing));
            }
        }catch (Exception e) {
            throw e;
        }
        return flag;
    }

    @Override
    public boolean find(Viewing viewing) throws Exception {
        return this.dao.find(viewing);
    }

    @Override
    public LinkedList<Viewing> detailComment(String propNo) throws Exception {
        return this.dao.detailComment(propNo);
    }

}
