package dream.action;

import com.opensymphony.xwork2.Action;
import dream.entity.Branch;

/**
 * Created by lenovo on 2015/6/17.
 */
public class FindBranchWithKeyAction implements Action{
    private Branch branch;

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    @Override
    public String execute() throws Exception {
        return null;
    }
}
