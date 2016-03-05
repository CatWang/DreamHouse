package dream.daoInterface;

import dream.entity.Lease;

import java.util.LinkedList;

/**
 * Created by lenovo on 2015/6/16.
 */
public interface ILeaseDAO {
    public boolean insert(Lease lease) throws Exception;
    public LinkedList<Lease> getAll() throws Exception;
    public boolean update(Lease lease) throws Exception;
    public boolean delete(Lease lease) throws Exception;
    public boolean find(Lease lease) throws Exception;
    public LinkedList<Lease> match(String propNo, String fName, String lName) throws Exception;
}
