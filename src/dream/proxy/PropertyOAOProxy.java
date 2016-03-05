package dream.proxy;

import dream.daoInterface.IPropertyDAO;
import dream.db.DatabaseConnection;
import dream.db.PropertyDAO;
import dream.entity.MutipleInt;
import dream.entity.Property;

import java.util.LinkedList;

/**
 * Created by lenovo on 2015/6/17.
 */
public class PropertyOAOProxy implements IPropertyDAO {
    private DatabaseConnection dbc = null;
    private IPropertyDAO dao = null;

    public PropertyOAOProxy(){
        try {
            this.dbc = new DatabaseConnection();
        }catch (Exception e) {
            e.printStackTrace();
        }
        this.dao = new PropertyDAO(this.dbc.getConnection());
    }
    @Override
    public boolean insert(Property property) throws Exception {
        boolean flag = false;
        try {
            if (this.dao.insert(property)) {
                flag = this.dao.find(property);
            }
        }catch (Exception e) {
            throw e;
        }
        return flag;
    }

    @Override
    public LinkedList<Property> getAll() throws Exception {

        return this.dao.getAll();
    }

    @Override
    public boolean update(Property property) throws Exception {
        boolean flag = false;
        try {
            if (this.dao.update(property)){
                flag = true;
            }
        }catch (Exception e) {
            throw e;
        }
        return flag;
    }

    @Override
    public boolean delete(Property property) throws Exception {
        boolean flag = false;
        try {
            if (this.dao.delete(property)) {
                flag = !(this.dao.find(property));
            }
        }catch (Exception e) {
            throw e;
        }
        return flag;
    }

    @Override
    public boolean find(Property property) throws Exception {
        return this.dao.find(property);
    }

    @Override
    public LinkedList<MutipleInt> rentBranch() throws Exception {
        return this.dao.rentBranch();
    }

    @Override
    public LinkedList<Property> propertyWant(int room, double rent) throws Exception {
        return this.dao.propertyWant(room, rent);
    }

    @Override
    public LinkedList<MutipleInt> staffProperty(String branchNo) throws Exception {
        return this.dao.staffProperty(branchNo);
    }

    @Override
    public LinkedList<Property> clientProperty(String clientNo) throws Exception {
        return this.dao.clientProperty(clientNo);
    }

    @Override
    public LinkedList<Property> notRentForThree() throws Exception {
        return this.dao.notRentForThree();
    }

}
