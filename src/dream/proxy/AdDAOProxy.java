package dream.proxy;

import dream.daoInterface.IAdDAO;
import dream.db.AdDAO;
import dream.db.DatabaseConnection;
import dream.entity.Ad;

import java.util.LinkedList;

/**
 * Created by lenovo on 2015/6/17.
 */
public class AdDAOProxy implements IAdDAO{
    private DatabaseConnection dbc = null;
    private IAdDAO dao = null;

    public AdDAOProxy(){
        try {
            this.dbc = new DatabaseConnection();
        }catch (Exception e) {
            e.printStackTrace();
        }
        this.dao = new AdDAO(this.dbc.getConnection());
    }
    @Override
    public boolean insert(Ad ad) throws Exception {
        boolean flag = false;
        try {
            if (this.dao.insert(ad)) {
                flag = this.dao.find(ad);
            }
        }catch (Exception e) {
            throw e;
        }
        return flag;
    }

    @Override
    public LinkedList<Ad> getAll() throws Exception {

        return this.dao.getAll();
    }

    @Override
    public boolean update(Ad ad) throws Exception {
        boolean flag = false;
        try {
            if (this.dao.update(ad)){
                flag = true;
            }
        }catch (Exception e) {
            throw e;
        }
        return flag;
    }

    @Override
    public boolean delete(Ad ad) throws Exception {
        boolean flag = false;
        try {
            if (this.dao.delete(ad)) {
                flag = !(this.dao.find(ad));
            }
        }catch (Exception e) {
            throw e;
        }
        return flag;
    }

    @Override
    public boolean find(Ad ad) throws Exception {
        return this.dao.find(ad);
    }

}
