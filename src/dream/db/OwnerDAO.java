package dream.db;

import dream.daoInterface.IOwnerDAO;
import dream.entity.MutipleInt;
import dream.entity.Owner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * Created by lenovo on 2015/6/16.
 */
public class OwnerDAO implements IOwnerDAO {
    private Connection conn = null;
    private PreparedStatement pstmt = null;

    public OwnerDAO(Connection conn) {
        this.conn = conn;
    }
    @Override
    public boolean insert(Owner owner) throws Exception {
        boolean flag = false;
        try {
            if (!find(owner)) {
                String sql = "INSERT INTO \"owner\" VALUES (?,?,?,?,?,?,?,?,?,?,?)";
                this.pstmt = this.conn.prepareStatement(sql);
                this.pstmt.setString(1, owner.getOwnerNo());
                this.pstmt.setString(2, owner.getfName());
                this.pstmt.setString(3, owner.getlName());
                this.pstmt.setString(4, owner.getStreet());
                this.pstmt.setString(5, owner.getCity());
                this.pstmt.setString(6, owner.getPostcode());
                this.pstmt.setString(7, owner.getTeleNo());
                this.pstmt.setString(8, owner.getEmail());
                this.pstmt.setString(9, owner.getPassword());
                this.pstmt.setString(10, owner.getType());
                this.pstmt.setString(11, owner.getbName());
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
    public LinkedList<Owner> getAll() throws Exception {
        LinkedList<Owner> result = new LinkedList<>();
        try {
            String sql= "SELECT * FROM \"Owner\"";
            this.pstmt = this.conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Owner row = new Owner();
                row.setOwnerNo(rs.getString(1));
                row.setfName(rs.getString(2));
                row.setlName(rs.getString(3));
                row.setStreet(rs.getString(4));
                row.setCity(rs.getString(5));
                row.setPostcode(rs.getString(6));
                row.setTeleNo(rs.getString(7));
                row.setEmail(rs.getString(8));
                row.setPassword(rs.getString(9));
                row.setType(rs.getString(10));
                row.setbName(rs.getString(11));
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
    public boolean update(Owner owner) throws Exception {
        boolean flag = false;
        try{
            if (find(owner)) {
                String sql ="UPDATE \"owner\"" +
                        "SET \"fName\"=?, \"lName\"=?, \"street\"=?," +
                        "\"city\"=?, \"postcode\"=?, \"teleNo\"=?," +
                        "\"email\"=?, \"password\"=?, \"type\"=?, \"bName\"=?" +
                        " WHERE \"ownerNo\"=?";
                this.pstmt = this.conn.prepareStatement(sql);
                this.pstmt.setString(1, owner.getfName());
                this.pstmt.setString(2, owner.getlName());
                this.pstmt.setString(3, owner.getStreet());
                this.pstmt.setString(4, owner.getCity());
                this.pstmt.setString(5, owner.getPostcode());
                this.pstmt.setString(6, owner.getTeleNo());
                this.pstmt.setString(7, owner.getEmail());
                this.pstmt.setString(8, owner.getPassword());
                this.pstmt.setString(9, owner.getType());
                this.pstmt.setString(10, owner.getbName());
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
    public boolean delete(Owner owner) throws Exception {
        boolean flag = false;
        try {
            if (find(owner)) {
                String sql = "DELETE FROM \"owner\" WHERE \"ownerNo\"=?" ;
                this.pstmt = this.conn.prepareStatement(sql);
                this.pstmt.setString(1, owner.getOwnerNo());
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
    public boolean find(Owner owner) throws Exception {
        boolean flag = false;
        try {
            String sql = "SELECT * FROM \"owner\" WHERE \"ownerNo\"=?" ;
            this.pstmt = this.conn.prepareStatement(sql);
            this.pstmt.setString(1, owner.getOwnerNo());
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
    public LinkedList<MutipleInt> forGivenProp(String propNo) throws Exception {
        LinkedList<MutipleInt> result = new LinkedList<>();
        try {
            String sql= "SELECT \"fName\", \"lName\", \"teleNo\" FROM \"owner\" o WHERE EXISTS(SELECT * FROM \"Property\" p WHERE p.\"ownerNo\"=o.\"ownerNo\" AND \"propNo\"=?)";
            this.pstmt = this.conn.prepareStatement(sql);
            this.pstmt.setString(1, propNo);
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
