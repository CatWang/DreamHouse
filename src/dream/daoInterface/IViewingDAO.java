package dream.daoInterface;

import dream.entity.Viewing;

import java.util.LinkedList;

/**
 * Created by lenovo on 2015/6/16.
 */
public interface IViewingDAO {
    public boolean insert(Viewing viewing) throws Exception;
    public LinkedList<Viewing> getAll() throws Exception;
    public boolean update(Viewing viewing) throws Exception;
    public boolean delete(Viewing viewing) throws Exception;
    public boolean find(Viewing viewing) throws Exception;
    public LinkedList<Viewing> detailComment(String propNo) throws Exception;
}
