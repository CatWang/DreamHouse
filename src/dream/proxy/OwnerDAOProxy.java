package dream.proxy;

import dream.daoInterface.IOwnerDAO;
import dream.db.DatabaseConnection;
import dream.db.OwnerDAO;
import dream.entity.MutipleInt;
import dream.entity.Owner;

import java.util.LinkedList;

/**
 * Created by lenovo on 2015/6/17.
 */
public class OwnerDAOProxy implements IOwnerDAO {
    private DatabaseConnection dbc = null;
    private IOwnerDAO dao = null;

    public OwnerDAOProxy(){
        try {
            this.dbc = new DatabaseConnection();
        }catch (Exception e) {
            e.printStackTrace();
        }
        this.dao = new OwnerDAO(this.dbc.getConnection());
    }
    @Override
    public boolean insert(Owner owner) throws Exception {
        boolean flag = false;
        try {
            if (this.dao.insert(owner)) {
                flag = this.dao.find(owner);
            }
        }catch (Exception e) {
            throw e;
        }
        return flag;
    }

    @Override
    public LinkedList<Owner> getAll() throws Exception {

        return this.dao.getAll();
    }

    @Override
    public boolean update(Owner owner) throws Exception {
        boolean flag = false;
        try {
            if (this.dao.update(owner)){
                flag = true;
            }
        }catch (Exception e) {
            throw e;
        }
        return flag;
    }

    @Override
    public boolean delete(Owner owner) throws Exception {
        boolean flag = false;
        try {
            if (this.dao.delete(owner)) {
                flag = !(this.dao.find(owner));
            }
        }catch (Exception e) {
            throw e;
        }
        return flag;
    }

    @Override
    public boolean find(Owner owner) throws Exception {
        return this.dao.find(owner);
    }

    @Override
    public LinkedList<MutipleInt> forGivenProp(String propNo) throws Exception {
        return this.dao.forGivenProp(propNo);
    }

}
