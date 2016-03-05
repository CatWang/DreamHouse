package dream.db;

import dream.daoInterface.IBranchDAO;
import dream.entity.Branch;
import dream.entity.MutipleInt;
import dream.entity.cityBranch;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * Created by lenovo on 2015/6/15.
 */
public class BranchDAO implements IBranchDAO {
    private Connection conn = null;
    private PreparedStatement pstmt = null;

    public BranchDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public boolean insertBranch(Branch branch) throws Exception {
        boolean flag = false;
        try {
            System.out.println("start Branch");
            String sql = "SELECT * FROM \"Branch\" WHERE \"branchNo\"=?";
            this.pstmt = this.conn.prepareStatement(sql);
            this.pstmt.setString(1, branch.getBranchNo());
            ResultSet rs = this.pstmt.executeQuery();
            if (!rs.next()) {
                System.out.println("find Branch");
                sql = "INSERT INTO \"Branch\" VALUES (?,?,?,?,?,?)";
                this.pstmt = this.conn.prepareStatement(sql);
                this.pstmt.setString(1, branch.getBranchNo());
                this.pstmt.setString(2, branch.getStreet());
                this.pstmt.setString(3, branch.getCity());
                this.pstmt.setString(4, branch.getPostcode());
                this.pstmt.setString(5, branch.getTeleNo());
                this.pstmt.setString(6, branch.getManagerNo());
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
    public LinkedList<Branch> getAllBranch() throws Exception {
        LinkedList<Branch> result = new LinkedList<>();
        try {
            String sql= "SELECT * FROM \"Branch\"";
            this.pstmt = this.conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Branch row = new Branch();
                row.setBranchNo(rs.getString(1));
                row.setStreet(rs.getString(2));
                row.setCity(rs.getString(3));
                row.setPostcode(rs.getString(4));
                row.setTeleNo(rs.getString(5));
                row.setManagerNo(rs.getString(6));
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
    public boolean updateBranch(Branch branch) throws Exception {
        boolean flag = false;
        try {
            String sql = "SELECT * FROM \"Branch\" WHERE \"branchNo\"=?";
            this.pstmt = conn.prepareStatement(sql);
            this.pstmt.setString(1, branch.getBranchNo());
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                sql = "UPDATE \"Branch\" SET \"street\"=?,\"city\"=?,\"postcode\"=?," +
                        "\"teleNo\"=?,\"managerNo\"=? " +
                        "WHERE \"branchNo\" =?";
                this.pstmt = conn.prepareStatement(sql);
                this.pstmt.setString(1, branch.getStreet());
                this.pstmt.setString(2, branch.getCity());
                this.pstmt.setString(3, branch.getPostcode());
                this.pstmt.setString(4, branch.getTeleNo());
                this.pstmt.setString(5, branch.getManagerNo());
                this.pstmt.setString(6, branch.getBranchNo());
                pstmt.executeUpdate();
                flag = true;
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
        return flag;
    }

    @Override
    public boolean deleteBranch(Branch branch) throws Exception {
        boolean flag = false;
        try {
            String sql = "SELECT * FROM \"Branch\" WHERE \"branchNo\"=?";
            this.pstmt = this.conn.prepareStatement(sql);
            this.pstmt.setString(1, branch.getBranchNo());
            ResultSet rs = this.pstmt.executeQuery();
            if (rs.next()) {
                sql = "DELETE FROM \"Branch\" WHERE \"branchNo\"=?";
                this.pstmt = this.conn.prepareStatement(sql);
                this.pstmt.setString(1, branch.getBranchNo());
                this.pstmt.executeUpdate();
                flag = true;
            }
        }catch (Exception e) {
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
    public boolean findBranch(Branch branch) throws Exception {
        boolean flag = false;
        try {
            String sql = "SELECT * FROM \"Branch\" WHERE \"branchNo\"=?";
            this.pstmt = this.conn.prepareStatement(sql);
            this.pstmt.setString(1, branch.getBranchNo());
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
    public LinkedList<Branch> queryBranch(String key) throws Exception {
        LinkedList<Branch> result = new LinkedList<>();
        try {
            String sql = "SELECT * FROM \"Branch\"" +
                    "WHERE \"branchNo\" LIKE ? OR \"street\" LIKE ?" +
                    "OR \"city\" LIKE ? OR \"postcode\" LIKE ? OR \"teleNo\" LIKE ?" +
                    "OR \"managerNo\" LIKE ?";
            this.pstmt = this.conn.prepareStatement(sql);
            this.pstmt.setString(1,"%"+key+"%");
            this.pstmt.setString(2,"%"+key+"%");
            this.pstmt.setString(3,"%"+key+"%");
            this.pstmt.setString(4,"%"+key+"%");
            this.pstmt.setString(5,"%"+key+"%");
            this.pstmt.setString(6,"%"+key+"%");
            ResultSet rs = this.pstmt.executeQuery();
            while (rs.next()) {
                Branch row = new Branch();
                row.setBranchNo(rs.getString(1));
                row.setStreet(rs.getString(2));
                row.setCity(rs.getString(3));
                row.setPostcode(rs.getString(4));
                row.setTeleNo(rs.getString(5));
                row.setManagerNo(rs.getString(6));
                result.add(row);
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
        return result;
    }

    @Override
    public LinkedList<cityBranch> cityBranch() throws Exception {
        LinkedList<cityBranch> result = new LinkedList<>();
        try {
            System.out.print("aa");
            String sql= "SELECT \"city\",COUNT(*) AS \"branchNum\" FROM \"Branch\" GROUP BY \"city\"";
            this.pstmt = this.conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println("bb");
                cityBranch row = new cityBranch();
                row.setCity(rs.getString(1));
                row.setBranchNum(rs.getInt(2));
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
    public LinkedList<MutipleInt> branchManager() throws Exception {
        LinkedList<MutipleInt> result = new LinkedList<>();
        try {
            String sql= "SELECT \"fName\",\"lName\",b.\"branchNo\",b.\"city\" FROM \"Branch\" b , \"Staff\" s WHERE b.\"managerNo\" = s.\"staffNo\" ORDER BY \"city\"";
            this.pstmt = this.conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                MutipleInt row = new MutipleInt();
                row.setS1(rs.getString(1));
                row.setS2(rs.getString(2));
                row.setS3(rs.getString(3));
                row.setS4(rs.getString(4));
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


