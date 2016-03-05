package dream.db;

import dream.daoInterface.IStaffDAO;
import dream.entity.MutipleInt;
import dream.entity.Staff;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * Created by lenovo on 2015/6/16.
 */
public class StaffDAO implements IStaffDAO{
    private Connection conn = null;
    private PreparedStatement pstmt = null;

    public StaffDAO(Connection conn) {
        this.conn = conn;
    }
    @Override
    public boolean insert(Staff staff) throws Exception {
        boolean flag = false;
        try {
            String sql = "SELECT * FROM \"Staff\" WHERE \"staffNo\"=?";
            this.pstmt = this.conn.prepareStatement(sql);
            this.pstmt.setString(1, staff.getStaffNo());
            ResultSet rs = this.pstmt.executeQuery();
            if (!rs.next()) {
                sql = "INSERT INTO \"Staff\" VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                this.pstmt = this.conn.prepareStatement(sql);
                this.pstmt.setString(1, staff.getStaffNo());
                this.pstmt.setString(2, staff.getfName());
                this.pstmt.setString(3, staff.getlName());
                this.pstmt.setString(4, staff.getStreet());
                this.pstmt.setString(5, staff.getCity());
                this.pstmt.setString(6, staff.getPostcode());
                this.pstmt.setDouble(7, staff.getSalary());
                this.pstmt.setString(8, staff.getSuperNo());
                this.pstmt.setDate(9, staff.getStartDate());
                this.pstmt.setDouble(10, staff.getBonus());
                this.pstmt.setString(11, staff.getBranchNo());
                this.pstmt.setString(12, staff.getPostcode());
                this.pstmt.setString(13, staff.getGender());
                this.pstmt.setDate(14, staff.getDOB());
                this.pstmt.executeUpdate();
                flag = true;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (this.pstmt != null && (!pstmt.isClosed())) {
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
    public LinkedList<Staff> getAll() throws Exception {
        LinkedList<Staff> result = new LinkedList<>();
        try {
            String sql= "SELECT * FROM \"Staff\"";
            this.pstmt = this.conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Staff row = new Staff();
                row.setStaffNo(rs.getString(1));
                row.setfName(rs.getString(2));
                row.setlName(rs.getString(3));
                row.setStreet(rs.getString(4));
                row.setCity(rs.getString(5));
                row.setPostcode(rs.getString(6));
                row.setSalary(rs.getDouble(7));
                row.setSuperNo(rs.getString(8));
                row.setStartDate(rs.getDate(9));
                row.setBonus(rs.getDouble(10));
                row.setBranchNo(rs.getString(11));
                row.setPosition(rs.getString(12));
                row.setGender(rs.getString(13));
                row.setDOB(rs.getDate(14));
                result.add(row);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            if (this.pstmt != null && (!pstmt.isClosed())) {
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
    public boolean update(Staff staff) throws Exception {
        boolean flag = false;
        try {
            String sql = "SELECT * FROM \"Staff\" WHERE \"staffNo\"=?";
            this.pstmt = conn.prepareStatement(sql);
            this.pstmt.setString(1, staff.getStaffNo());
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                sql = "UPDATE \"Staff\" SET \"fName\"=?,\"lName\"=?,\"street\"=?," +
                        "\"city\"=?,\"postcode\"=?, \"salary\"=?, \"superNo\"=?," +
                        "\"startDate\"=?, \"bonus\"=?, \"branchNo\"=?, \"position\"=?," +
                        " \"gender\"=?, \"DOB\"=? WHERE \"staffNo\"=?";
                this.pstmt = conn.prepareStatement(sql);
                this.pstmt.setString(1, staff.getfName());
                this.pstmt.setString(2, staff.getlName());
                this.pstmt.setString(3, staff.getStreet());
                this.pstmt.setString(4, staff.getCity());
                this.pstmt.setString(5, staff.getPostcode());
                this.pstmt.setDouble(6, staff.getSalary());
                this.pstmt.setString(7, staff.getSuperNo());
                this.pstmt.setDate(8, staff.getStartDate());
                this.pstmt.setDouble(9, staff.getBonus());
                this.pstmt.setString(10, staff.getBranchNo());
                this.pstmt.setString(11, staff.getPosition());
                this.pstmt.setString(12, staff.getGender());
                this.pstmt.setDate(13, staff.getDOB());
                this.pstmt.setString(14, staff.getStaffNo());
                pstmt.executeUpdate();
                flag = true;
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            if (this.pstmt != null && (!pstmt.isClosed())) {
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
    public boolean delete(Staff staff) throws Exception {
        boolean flag = false;
        try {
            if (find(staff)) {
                String sql = "DELETE FROM \"Staff\" WHERE \"staffNo\"=?";
                this.pstmt = this.conn.prepareStatement(sql);
                this.pstmt.setString(1, staff.getStaffNo());
                this.pstmt.executeUpdate();
                flag = true;
            }
        }catch (Exception e) {
            throw e;
        }finally {
            if ((this.pstmt != null) && (!pstmt.isClosed())) {
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
    public boolean find(Staff staff) throws Exception {
        boolean flag = false;
        try {
            String sql = "SELECT * FROM \"Staff\" WHERE \"staffNo\"=?";
            this.pstmt = this.conn.prepareStatement(sql);
            this.pstmt.setString(1, staff.getStaffNo());
            ResultSet rs = this.pstmt.executeQuery();
            if (rs.next()) {
                flag = true;
            }
        }catch (Exception e) {
            throw e;
        }finally {
            if ((this.pstmt != null) && (!pstmt.isClosed())){
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
    public LinkedList<Staff> getStaffByBranch(String branchNo) throws Exception {
        LinkedList<Staff> result = new LinkedList<>();
        try {
            System.out.println("Staff get by brachNo1");
            String sql= "SELECT * FROM \"Staff\" WHERE \"branchNo\"=? ORDER BY \"fName\",\"lName\"";
            this.pstmt = this.conn.prepareStatement(sql);
            this.pstmt.setString(1, branchNo);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println("Staff get by brachNo2");
                Staff row = new Staff();
                row.setStaffNo(rs.getString(1));
                row.setfName(rs.getString(2));
                row.setlName(rs.getString(3));
                row.setStreet(rs.getString(4));
                row.setCity(rs.getString(5));
                row.setPostcode(rs.getString(6));
                row.setSalary(rs.getDouble(7));
                row.setSuperNo(rs.getString(8));
                row.setStartDate(rs.getDate(9));
                row.setBonus(rs.getDouble(10));
                row.setBranchNo(rs.getString(11));
                row.setPosition(rs.getString(12));
                row.setGender(rs.getString(13));
                row.setDOB(rs.getDate(14));
                result.add(row);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            if (this.pstmt != null && (!pstmt.isClosed())) {
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
    public LinkedList<MutipleInt> getStaffSalaries() throws Exception {
        LinkedList<MutipleInt> result = new LinkedList<>();
        try {
            String sql= "SELECT COUNT(\"staffNo\"),SUM(\"salary\") FROM \"Staff\"";
            this.pstmt = this.conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                MutipleInt row = new MutipleInt();
                row.setInt1(rs.getInt(1));
                row.setInt2(rs.getInt(2));
                result.add(row);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            if (this.pstmt != null && (!pstmt.isClosed())) {
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
    public LinkedList<Staff> supervisedBy(String fName, String lName) throws Exception {
        LinkedList<Staff> result = new LinkedList<>();
        try {
            String sql= "SELECT * FROM \"Staff\" WHERE \"superNo\"=(SELECT \"superNo\" FROM \"Staff\" WHERE \"fName\"=? AND \"lName\"=?)";
            this.pstmt = this.conn.prepareStatement(sql);
            this.pstmt.setString(1, fName);
            this.pstmt.setString(2, lName);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Staff row = new Staff();
                row.setStaffNo(rs.getString(1));
                row.setfName(rs.getString(2));
                row.setlName(rs.getString(3));
                row.setStreet(rs.getString(4));
                row.setCity(rs.getString(5));
                row.setPostcode(rs.getString(6));
                row.setSalary(rs.getDouble(7));
                row.setSuperNo(rs.getString(8));
                row.setStartDate(rs.getDate(9));
                row.setBonus(rs.getDouble(10));
                row.setBranchNo(rs.getString(11));
                row.setPosition(rs.getString(12));
                row.setGender(rs.getString(13));
                row.setDOB(rs.getDate(14));
                result.add(row);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            if (this.pstmt != null && (!pstmt.isClosed())) {
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
