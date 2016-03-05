package dream.proxy;

import dream.daoInterface.IClientDAO;
import dream.db.ClientDAO;
import dream.db.DatabaseConnection;
import dream.entity.Client;
import dream.entity.MutipleInt;

import java.util.LinkedList;

/**
 * Created by lenovo on 2015/6/16.
 */
public class ClientDAOProxy implements IClientDAO{
    private DatabaseConnection dbc = null;
    private IClientDAO dao = null;

    public ClientDAOProxy(){
        try {
            this.dbc = new DatabaseConnection();
        }catch (Exception e) {
            e.printStackTrace();
        }
        this.dao = new ClientDAO(this.dbc.getConnection());
    }
    @Override
    public boolean insert(Client client) throws Exception {
        boolean flag = false;
        try {
            if (this.dao.insert(client)) {
                flag = this.dao.find(client);
            }
        }catch (Exception e) {
            throw e;
        }
        return flag;
    }

    @Override
    public LinkedList<Client> getAll() throws Exception {

        return this.dao.getAll();
    }

    @Override
    public boolean update(Client client) throws Exception {
        boolean flag = false;
        try {
            if (this.dao.update(client)){
                flag = true;
            }
        }catch (Exception e) {
            throw e;
        }
        return flag;
    }

    @Override
    public boolean delete(Client client) throws Exception {
        boolean flag = false;
        try {
            if (this.dao.delete(client)) {
                flag = !(this.dao.find(client));
            }
        }catch (Exception e) {
            throw e;
        }
        return flag;
    }

    @Override
    public boolean find(Client client) throws Exception {
        return this.dao.find(client);
    }

    @Override
    public LinkedList<Client> clientMatch() throws Exception {
        return this.dao.clientMatch();
    }

    @Override
    public LinkedList<MutipleInt> notComment() throws Exception {
        return this.dao.notComment();
    }

}
