package dream.proxy;

import dream.daoInterface.ILeaseDAO;
import dream.db.DatabaseConnection;
import dream.db.LeaseDAO;
import dream.entity.Lease;

import java.util.LinkedList;

/**
 * Created by lenovo on 2015/6/17.
 */
public class LeaseDAOProxy implements ILeaseDAO {
    private DatabaseConnection dbc = null;
    private ILeaseDAO dao = null;

    public LeaseDAOProxy(){
        try {
            this.dbc = new DatabaseConnection();
        }catch (Exception e) {
            e.printStackTrace();
        }
        this.dao = new LeaseDAO(this.dbc.getConnection());
    }
    @Override
    public boolean insert(Lease lease) throws Exception {
        boolean flag = false;
        try {
            if (this.dao.insert(lease)) {
                flag = this.dao.find(lease);
            }
        }catch (Exception e) {
            throw e;
        }
        return flag;
    }

    @Override
    public LinkedList<Lease> getAll() throws Exception {

        return this.dao.getAll();
    }

    @Override
    public boolean update(Lease lease) throws Exception {
        boolean flag = false;
        try {
            if (this.dao.update(lease)){
                flag = true;
            }
        }catch (Exception e) {
            throw e;
        }
        return flag;
    }

    @Override
    public boolean delete(Lease lease) throws Exception {
        boolean flag = false;
        try {
            if (this.dao.delete(lease)) {
                flag = !(this.dao.find(lease));
            }
        }catch (Exception e) {
            throw e;
        }
        return flag;
    }

    @Override
    public boolean find(Lease lease) throws Exception {
        return this.dao.find(lease);
    }

    @Override
    public LinkedList<Lease> match(String propNo, String fName, String lName) throws Exception {
        return this.dao.match(propNo, fName, lName);
    }

}
