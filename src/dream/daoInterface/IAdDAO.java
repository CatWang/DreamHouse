package dream.daoInterface;

import dream.entity.Ad;

import java.util.LinkedList;

/**
 * Created by lenovo on 2015/6/16.
 */
public interface IAdDAO {
    public boolean insert(Ad ad) throws Exception;
    public LinkedList<Ad> getAll() throws Exception;
    public boolean update(Ad ad) throws Exception;
    public boolean delete(Ad ad) throws Exception;
    public boolean find(Ad ad) throws Exception;
}
