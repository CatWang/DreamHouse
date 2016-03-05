package dream.daoInterface;

import dream.entity.Branch;
import dream.entity.MutipleInt;
import dream.entity.cityBranch;

import java.util.LinkedList;

/**
 * Created by lenovo on 2015/6/15.
 */
public interface IBranchDAO {
    public boolean insertBranch(Branch branch) throws Exception;
    public LinkedList<Branch> getAllBranch() throws Exception;
    public boolean updateBranch(Branch branch) throws Exception;
    public boolean deleteBranch(Branch branch) throws Exception;
    public boolean findBranch(Branch branch) throws Exception;
    public LinkedList<Branch> queryBranch(String key) throws Exception;
    public LinkedList<cityBranch> cityBranch() throws Exception;
    public LinkedList<MutipleInt> branchManager() throws Exception;
}
