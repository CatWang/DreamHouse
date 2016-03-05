package dream.daoInterface;

import dream.entity.MutipleInt;
import dream.entity.Owner;

import java.util.LinkedList;

/**
 * Created by lenovo on 2015/6/16.
 */
public interface IOwnerDAO {
    public boolean insert(Owner owner) throws Exception;
    public LinkedList<Owner> getAll() throws Exception;
    public boolean update(Owner owner) throws Exception;
    public boolean delete(Owner owner) throws Exception;
    public boolean find(Owner owner) throws Exception;
    public LinkedList<MutipleInt> forGivenProp(String propNo) throws Exception;
}
