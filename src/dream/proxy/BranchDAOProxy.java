package dream.proxy;

import dream.daoInterface.IBranchDAO;
import dream.db.BranchDAO;
import dream.db.DatabaseConnection;
import dream.entity.Branch;
import dream.entity.MutipleInt;
import dream.entity.cityBranch;

import java.util.LinkedList;

/**
 * Created by lenovo on 2015/6/15.
 */
public class BranchDAOProxy implements IBranchDAO{
    private DatabaseConnection dbc = null;
    private IBranchDAO dao = null;

    public BranchDAOProxy() {

        this.dbc = new DatabaseConnection();
        this.dao = new BranchDAO(this.dbc.getConnection());
    }

    public boolean insertBranch(Branch branch) throws Exception {
        boolean flag = false;
        try{
            flag = this.dao.insertBranch(branch);
        }catch (Exception e) {
            throw e;
        }
        return flag;
    }

    @Override
    public LinkedList<Branch> getAllBranch() throws Exception {
        return this.dao.getAllBranch();
    }

    @Override
    public boolean updateBranch(Branch branch) throws Exception {
        boolean flag = false;
        try {
            flag = this.dao.updateBranch(branch);
        }catch (Exception e) {
            throw e;
        }
        return flag;
    }

    @Override
    public boolean deleteBranch(Branch branch) throws Exception {
        boolean flag = false;
        try {
            flag = this.dao.deleteBranch(branch);
        }catch (Exception e) {
            throw e;
        }
        return flag;
    }

    @Override
    public boolean findBranch(Branch branch) throws Exception {
        boolean flag = false;
        try{
            flag = this.dao.findBranch(branch);
        }catch (Exception e) {
            throw e;
        }
        return flag;
    }

    @Override
    public LinkedList<Branch> queryBranch(String key) throws Exception {
        LinkedList<Branch> result = null;
        try {
            result = this.dao.queryBranch(key);
        }catch (Exception e) {
            throw e;
        }
        return result;
    }

    @Override
    public LinkedList<cityBranch> cityBranch() throws Exception {
        return this.dao.cityBranch();
    }

    @Override
    public LinkedList<MutipleInt> branchManager() throws Exception {
        return this.dao.branchManager();
    }

}
