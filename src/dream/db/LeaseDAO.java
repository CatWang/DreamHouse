package dream.db;

import dream.daoInterface.ILeaseDAO;
import dream.entity.Lease;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * Created by lenovo on 2015/6/16.
 */
public class LeaseDAO implements ILeaseDAO{
    private Connection conn = null;
    private PreparedStatement pstmt = null;

    public LeaseDAO(Connection conn) {
        this.conn = conn;
    }
    @Override
    public boolean insert(Lease lease) throws Exception {
        boolean flag = false;
        try {
            if (!find(lease)) {
                String sql = "INSERT INTO \"lease\" VALUES (?,?,?,?,?,?,?)";
                this.pstmt = this.conn.prepareStatement(sql);
                this.pstmt.setString(1, lease.getLeaseNo());
                this.pstmt.setString(2, lease.getClientNo());
                this.pstmt.setString(3, lease.getPropNo());
                this.pstmt.setString(4, lease.getPayMethod());
                this.pstmt.setBoolean(5, lease.isDepositPaid());
                this.pstmt.setDate(6, lease.getStartDate());
                this.pstmt.setDate(7, lease.getEndDate());
                this.pstmt.executeUpdate();
                flag = true;
            }
        } catch (Exception e) {
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
        return flag;

    }

    @Override
    public LinkedList<Lease> getAll() throws Exception {
        LinkedList<Lease> result = new LinkedList<>();
        try {
            String sql= "SELECT * FROM \"lease\"";
            this.pstmt = this.conn.prepareStatement(sql);
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

    @Override
    public boolean update(Lease lease) throws Exception {
        boolean flag = false;
        try{
            if (find(lease)) {
                String sql ="UPDATE \"lease\"" +
                        "SET \"clientNo\"=?, \"propNo\"=?, \"payMethod\"=?," +
                        "\"depositPaid\"=?, \"startDate\"=?, \"endDate\"=?" +
                        " WHERE \"leaseNo\"=?";
                this.pstmt = this.conn.prepareStatement(sql);
                this.pstmt.setString(1, lease.getClientNo());
                this.pstmt.setString(2, lease.getPropNo());
                this.pstmt.setString(3, lease.getPayMethod());
                this.pstmt.setBoolean(4, lease.isDepositPaid());
                this.pstmt.setDate(5, lease.getStartDate());
                this.pstmt.setDate(6, lease.getEndDate());
                this.pstmt.setString(7, lease.getLeaseNo());
                this.pstmt.executeUpdate();
                flag = true;
            }
        }catch (Exception e) {
            throw e;
        }finally {
            if (this.pstmt != null) {
                try {
                    this.pstmt.close();
                }catch (Exception e) {
                    throw e;
                }
            }
        }
        return flag;
    }

    @Override
    public boolean delete(Lease lease) throws Exception {
        boolean flag = false;
        try {
            if (find(lease)) {
                String sql = "DELETE FROM \"lease\" WHERE \"leaseNo\"=?" ;
                this.pstmt = this.conn.prepareStatement(sql);
                this.pstmt.setString(1, lease.getLeaseNo());
                this.pstmt.executeUpdate();
                flag = true;
            }
        }catch (Exception e) {
            throw e;
        }finally {
            if (this.pstmt != null) {
                try {
                    this.pstmt.close();
                }catch (Exception e) {
                    throw e;
                }
            }
        }
        return flag;
    }

    @Override
    public boolean find(Lease lease) throws Exception {
        boolean flag = false;
        try {
            String sql = "SELECT * FROM \"lease\" WHERE \"leaseNo\"=?" ;
            this.pstmt = this.conn.prepareStatement(sql);
            this.pstmt.setString(1, lease.getLeaseNo());
            ResultSet rs = this.pstmt.executeQuery();
            if (rs.next()) {
                flag = true;
            }
        }catch (Exception e) {
            throw e;
        }finally {
            if (this.pstmt != null) {
                try {
                    this.pstmt.close();
                } catch (Exception e) {
                    throw e;
                }
            }
        }
        return flag;
    }

    @Override
    public LinkedList<Lease> match(String propNo, String fName, String lName) throws Exception {
        LinkedList<Lease> result = new LinkedList<>();
        try {
            String sql= "SELECT * FROM \"lease\" WHERE \"propNo\"=? AND \"clientNo\"=(SELECT \"clientNo\" FROM \"client\" WHERE \"fName\"=? AND \"lName\"=?)";
            this.pstmt = this.conn.prepareStatement(sql);
            this.pstmt.setString(1, propNo);
            this.pstmt.setString(2, fName);
            this.pstmt.setString(3, lName);
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

