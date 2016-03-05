package dream.factory;

import dream.daoInterface.*;
import dream.proxy.*;

/**
 * Created by lenovo on 2015/6/15.
 */
public class DAOFactory {
    public static IBranchDAO getIBranchDAOInstance() {
        return new BranchDAOProxy();
    }
    public static IStaffDAO getIStaffDAOInstance() {
        return new StaffDAOProxy();
    }
    public static IAdDAO getIAdDAOInstance() {
        return new AdDAOProxy();
    }
    public static IClientDAO getIClientDAOInstance() {
        return new ClientDAOProxy();
    }
    public static ILeaseDAO getILeaseDAOInstance() {
        return new LeaseDAOProxy();
    }
    public static INewspaperDAO getINewspaperDAOInstance() {
        return new NewspaperDAOProxy();
    }
    public static IOwnerDAO getIOwnerDAOInstance() {
        return new OwnerDAOProxy();
    }
    public static IPropertyDAO getIPropertyDAOInstance() {
        return new PropertyOAOProxy();
    }
    public static IViewingDAO getIViewingDAOInstance() {
        return new ViewingDAOProxy();
    }
    public static IBranchLeaseDAO getIBranchLeaseDAOInstance() {
        return new BranchLeaseDAOProxy();
    }
}
