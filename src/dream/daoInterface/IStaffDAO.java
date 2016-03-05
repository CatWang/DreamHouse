package dream.daoInterface;

import dream.entity.MutipleInt;
import dream.entity.Staff;

import java.util.LinkedList;

/**
 * Created by lenovo on 2015/6/16.
 */
public interface IStaffDAO {
    public boolean insert(Staff staff) throws Exception;
    public LinkedList<Staff> getAll() throws Exception;
    public boolean update(Staff staff) throws Exception;
    public boolean delete(Staff staff) throws Exception;
    public boolean find(Staff staff) throws Exception;
    public LinkedList<Staff> getStaffByBranch(String branchNo) throws Exception;
    public LinkedList<MutipleInt> getStaffSalaries() throws Exception;
    public LinkedList<Staff> supervisedBy(String fName, String lName) throws Exception;
}
