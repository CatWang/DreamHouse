package dream.daoInterface;

import dream.entity.Lease;
import dream.entity.MutipleInt;

import java.util.LinkedList;

/**
 * Created by lenovo on 2015/6/17.
 */
public interface IBranchLeaseDAO  {
    public LinkedList<Lease> findLeaseByBranchNo(String branchNo);
    public LinkedList<MutipleInt> totalLease() throws Exception;
}
