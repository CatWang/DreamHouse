package dream.daoInterface;

import dream.entity.MutipleInt;
import dream.entity.Property;

import java.util.LinkedList;

/**
 * Created by lenovo on 2015/6/16.
 */
public interface IPropertyDAO {
    public boolean insert(Property property) throws Exception;
    public LinkedList<Property> getAll() throws Exception;
    public boolean update(Property property) throws Exception;
    public boolean delete(Property property) throws Exception;
    public boolean find(Property property) throws Exception;
    public LinkedList<MutipleInt> rentBranch() throws Exception;
    public LinkedList<Property> propertyWant(int room, double rent) throws Exception;
    public LinkedList<MutipleInt> staffProperty(String branchNo) throws Exception;
    public LinkedList<Property> clientProperty(String clientNo) throws Exception;
    public LinkedList<Property> notRentForThree() throws Exception;
}
