package dream.db;

import dream.daoInterface.IBranchLeaseDAO;
import dream.entity.Lease;
import dream.entity.MutipleInt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * Created by lenovo on 2015/6/17.
 */
public class BranchLeaseDAO implements IBranchLeaseDAO{
    private Connection conn = null;
    private PreparedStatement pstmt = null;

    public BranchLeaseDAO(Connection conn) {
        this.conn = conn;
    }
    @Override
    public LinkedList<Lease> findLeaseByBranchNo(String branchNo) {
        LinkedList<Lease> result = new LinkedList<>();
        try {
            String sql= "SELECT * FROM \"lease\" l WHERE EXISTS(SELECT * FROM \"client\" c WHERE l.\"clientNo\"=c.\"ClientNo\" AND c.\"branchNo\" = ?)";
            this.pstmt = this.conn.prepareStatement(sql);
            this.pstmt.setString(1, branchNo);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Lease row = new Lease();
                row.setLeaseNo(rs.getString(1));
                row.setClientNo(rs.getString(2));
                row.setPropNo(rs.getString(3));
                row.setPayMethod(rs.getString(4));
                row.setDepositPaid(rs.getBoolean(5));
                row.setStartDate(rs.getDate(6));
                row.setEndDate(rs.getDate(7));
                result.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (this.pstmt != null) {
                try {
                    this.pstmt.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    @Override
    public LinkedList<MutipleInt> totalLease() throws Exception {
        LinkedList<MutipleInt> result = new LinkedList<>();
        try {
            String sql= "SELECT COUNT(\"leaseNo\"), \"branchNo\" FROM  \"lease\" l , \"client\" c WHERE l.\"clientNo\" = c.\"ClientNo\" AND \"endDate\" - \"startDate\" < 365  GROUP BY \"branchNo\"";
            this.pstmt = this.conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                MutipleInt row = new MutipleInt();
                row.setInt1(rs.getInt(1));
                row.setS1(rs.getString(2));
                result.add(row);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            if (this.pstmt != null) {
                try {
                    this.pstmt.close();
                } catch (Exception e) {
                    throw e;
                }
            }
        }
        return result;
    }
}
