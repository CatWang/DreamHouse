package dream.proxy;

import dream.daoInterface.IBranchLeaseDAO;
import dream.db.BranchLeaseDAO;
import dream.db.DatabaseConnection;
import dream.entity.Lease;
import dream.entity.MutipleInt;

import java.util.LinkedList;

/**
 * Created by lenovo on 2015/6/17.
 */
public class BranchLeaseDAOProxy implements IBranchLeaseDAO {
    private DatabaseConnection dbc = null;
    private IBranchLeaseDAO dao = null;

    public BranchLeaseDAOProxy() {
        try {
            this.dbc = new DatabaseConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.dao = new BranchLeaseDAO(this.dbc.getConnection());
    }

    @Override
    public LinkedList<Lease> findLeaseByBranchNo(String branchNo) {
        return this.dao.findLeaseByBranchNo(branchNo);
    }

    @Override
    public LinkedList<MutipleInt> totalLease() throws Exception {
        return this.dao.totalLease();
    }
}
