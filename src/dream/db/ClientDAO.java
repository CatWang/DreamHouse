package dream.db;

import dream.daoInterface.IClientDAO;
import dream.entity.Client;
import dream.entity.MutipleInt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * Created by lenovo on 2015/6/16.
 */
public class ClientDAO implements IClientDAO{
    private Connection conn = null;
    private PreparedStatement pstmt = null;

    public ClientDAO(Connection conn) {
        this.conn = conn;
    }
    @Override
    public boolean insert(Client client) throws Exception {
        boolean flag = false;
        try {
            if (!find(client)) {
                String sql = "INSERT INTO \"Client\" VALUES (?,?,?,?,?,?,?,?,?,?)";
                this.pstmt = this.conn.prepareStatement(sql);
                this.pstmt.setString(1, client.getClientNo());
                this.pstmt.setString(2, client.getfName());
                this.pstmt.setString(3, client.getlName());
                this.pstmt.setString(4, client.getTeleNo());
                this.pstmt.setString(5, client.getEmail());
                this.pstmt.setString(6, client.getType());
                this.pstmt.setDouble(7, client.getMaxRent());
                this.pstmt.setString(8, client.getStaffNo());
                this.pstmt.setDate(9, client.getJoinDate());
                this.pstmt.setString(10, client.getBranchNo());
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
    public LinkedList<Client> getAll() throws Exception {
        LinkedList<Client> result = new LinkedList<>();
        try {
            String sql= "SELECT * FROM \"client\"";
            this.pstmt = this.conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Client row = new Client();
                row.setClientNo(rs.getString(1));
                row.setfName(rs.getString(2));
                row.setlName(rs.getString(3));
                row.setTeleNo(rs.getString(4));
                row.setEmail(rs.getString(5));
                row.setType(rs.getString(6));
                row.setMaxRent(rs.getDouble(7));
                row.setStaffNo(rs.getString(8));
                row.setJoinDate(rs.getDate(9));
                row.setBranchNo(rs.getString(10));
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
    public boolean update(Client client) throws Exception {
        boolean flag = false;
        try{
            if (find(client)) {
                String sql ="UPDATE \"client\"" +
                        "SET \"fName\"=?, \"lName\"=?," +
                        "\"teleNo\"=?, \"email\"=?, \"type\"=?, \"maxRent\"=?," +
                        "\"staffNo\"=?, \"joinDate\"=?, \"branchNo\"=?" +
                        "WHERE \"ClientNo\"=?";
                this.pstmt = this.conn.prepareStatement(sql);
                this.pstmt.setString(1, client.getfName());
                this.pstmt.setString(2, client.getlName());
                this.pstmt.setString(3, client.getTeleNo());
                this.pstmt.setString(4, client.getEmail());
                this.pstmt.setString(5, client.getType());
                this.pstmt.setDouble(6, client.getMaxRent());
                this.pstmt.setString(7, client.getStaffNo());
                this.pstmt.setDate(8, client.getJoinDate());
                this.pstmt.setString(9, client.getBranchNo());
                this.pstmt.setString(10, client.getClientNo());
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
    public boolean delete(Client client) throws Exception {
        boolean flag = false;
        try {
            if (find(client)) {
                String sql = "DELETE FROM \"client\" WHERE \"ClientNo\"=?";
                this.pstmt = this.conn.prepareStatement(sql);
                this.pstmt.setString(1, client.getClientNo());
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
    public boolean find(Client client) throws Exception {
        boolean flag = false;
        try {
            String sql = "SELECT * FROM \"client\" WHERE \"ClientNo\"=?";
            this.pstmt = this.conn.prepareStatement(sql);
            this.pstmt.setString(1, client.getClientNo());
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
    public LinkedList<Client> clientMatch() throws Exception {
        LinkedList<Client> result = new LinkedList<>();
        try {
            String sql= "SELECT * FROM \"client\" c WHERE EXISTS(SELECT * FROM \"Property\" p WHERE c.\"type\"=p.\"type\" AND c.\"maxRent\" > p.\"rentFee\")";
            this.pstmt = this.conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Client row = new Client();
                row.setClientNo(rs.getString(1));
                row.setfName(rs.getString(2));
                row.setlName(rs.getString(3));
                row.setTeleNo(rs.getString(4));
                row.setEmail(rs.getString(5));
                row.setType(rs.getString(6));
                row.setMaxRent(rs.getDouble(7));
                row.setStaffNo(rs.getString(8));
                row.setJoinDate(rs.getDate(9));
                row.setBranchNo(rs.getString(10));
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
    public LinkedList<MutipleInt> notComment() throws Exception {
        LinkedList<MutipleInt> result = new LinkedList<>();
        try {
            String sql= "SELECT \"fName\", \"lName\", \"teleNo\" FROM \"client\" c WHERE EXISTS(SELECT * FROM \"Viewing\" v WHERE \"comment\"ISNULL AND c.\"ClientNo\"=v.\"ClientNo\")";
            this.pstmt = this.conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                MutipleInt row = new MutipleInt();
                row.setS1(rs.getString(1));
                row.setS2(rs.getString(2));
                row.setS3(rs.getString(3));
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
