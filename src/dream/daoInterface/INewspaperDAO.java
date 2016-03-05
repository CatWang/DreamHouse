package dream.daoInterface;

import dream.entity.Newspaper;

import java.util.LinkedList;

/**
 * Created by lenovo on 2015/6/16.
 */
public interface INewspaperDAO {
    public boolean insert(Newspaper newspaper) throws Exception;
    public LinkedList<Newspaper> getAll() throws Exception;
    public boolean update(Newspaper newspaper) throws Exception;
    public boolean delete(Newspaper newspaper) throws Exception;
    public boolean find(Newspaper newspaper) throws Exception;
}
