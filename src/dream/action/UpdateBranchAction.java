package dream.action;

import com.opensymphony.xwork2.Action;
import dream.daoInterface.IBranchDAO;
import dream.entity.Branch;
import dream.factory.DAOFactory;

/**
 * Created by lenovo on 2015/6/16.
 */
public class UpdateBranchAction implements Action {

    private Branch branch;

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    @Override
    public String execute() throws Exception {
        DAOFactory factory = new DAOFactory();
        IBranchDAO branchDAO = factory.getIBranchDAOInstance();
        boolean flag = branchDAO.updateBranch(branch);
        if(flag) {
                return SUCCESS;
        }
        return INPUT;
    }
}
