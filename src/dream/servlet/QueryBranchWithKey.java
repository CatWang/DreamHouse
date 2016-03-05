package dream.servlet;

import dream.daoInterface.IBranchDAO;
import dream.entity.Branch;
import dream.factory.DAOFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;

/**
 * Created by lenovo on 2015/6/17.
 */
public class QueryBranchWithKey extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = "branch_result.jsp";
        String key = req.getParameter("key");
        LinkedList<Branch> result = new LinkedList<>();
        try {
            DAOFactory factory = new DAOFactory();
            IBranchDAO branchDAO = factory.getIBranchDAOInstance();
            try {
                result = branchDAO.queryBranch(key);
            }catch (Exception e) {
                e.printStackTrace();
            }
        }catch (Exception e) {
            throw e;
        }
        req.setAttribute("result", result);
        req.getRequestDispatcher(path).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
