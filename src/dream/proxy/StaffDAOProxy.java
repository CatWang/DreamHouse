package dream.proxy;

import dream.daoInterface.IStaffDAO;
import dream.db.DatabaseConnection;
import dream.db.StaffDAO;
import dream.entity.MutipleInt;
import dream.entity.Staff;

import java.util.LinkedList;

/**
 * Created by lenovo on 2015/6/16.
 */
public class StaffDAOProxy implements IStaffDAO{
    private DatabaseConnection dbc = null;
    private IStaffDAO dao = null;

    public StaffDAOProxy(){
        try {
            this.dbc = new DatabaseConnection();
        }catch (Exception e) {
            e.printStackTrace();
        }
        this.dao = new StaffDAO(this.dbc.getConnection());
    }
    @Override
    public boolean insert(Staff staff) throws Exception {
        boolean flag = false;
        try {
            if (this.dao.insert(staff)) {
                flag = this.dao.find(staff);
            }
        }catch (Exception e) {
            throw e;
        }
        return flag;
    }

    @Override
    public LinkedList<Staff> getAll() throws Exception {

        return this.dao.getAll();
    }

    @Override
    public boolean update(Staff staff) throws Exception {
        boolean flag = false;
        try {
            if (this.dao.update(staff)){
                flag = true;
            }
        }catch (Exception e) {
            throw e;
        }
        return flag;
    }

    @Override
    public boolean delete(Staff staff) throws Exception {
        boolean flag = false;
        try {
            if (this.dao.delete(staff)) {
                flag = !(this.dao.find(staff));
            }
        }catch (Exception e) {
            throw e;
        }
        return flag;
    }

    @Override
    public boolean find(Staff staff) throws Exception {
        return this.dao.find(staff);
    }

    @Override
    public LinkedList<Staff> getStaffByBranch(String branchNo) throws Exception {
        return this.dao.getStaffByBranch(branchNo);
    }

    @Override
    public LinkedList<MutipleInt> getStaffSalaries() throws Exception {
        return this.dao.getStaffSalaries();
    }

    @Override
    public LinkedList<Staff> supervisedBy(String fName, String lName) throws Exception {
        return this.dao.supervisedBy(fName, lName);
    }

}
