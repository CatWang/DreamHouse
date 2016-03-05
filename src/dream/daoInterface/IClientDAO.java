

/**
 * Created by lenovo on 2015/6/16.
 */

    package dream.daoInterface;

    import dream.entity.Client;
    import dream.entity.MutipleInt;

    import java.util.LinkedList;

    public interface IClientDAO {
        public boolean insert(Client client) throws Exception;
        public LinkedList<Client> getAll() throws Exception;
        public boolean update(Client client) throws Exception;
        public boolean delete(Client client) throws Exception;
        public boolean find(Client client) throws Exception;
        public LinkedList<Client> clientMatch() throws Exception;
        public LinkedList<MutipleInt> notComment() throws Exception;
    }


