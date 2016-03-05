package dream.db;

import dream.daoInterface.IPropertyDAO;
import dream.entity.MutipleInt;
import dream.entity.Property;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * Created by lenovo on 2015/6/16.
 */
public class PropertyDAO implements IPropertyDAO {
    private Connection conn = null;
    private PreparedStatement pstmt = null;

    public PropertyDAO(Connection conn) {
        this.conn = conn;
    }
    @Override
    public boolean insert(Property property) throws Exception {
        boolean flag = false;
        try {
            if (!find(property)) {
                String sql = "INSERT INTO \"Property\" VALUES (?,?,?,?,?,?,?,?,?)";
                this.pstmt = this.conn.prepareStatement(sql);
                this.pstmt.setString(1, property.getPropNo());
                this.pstmt.setString(2, property.getStreet());
                this.pstmt.setString(3, property.getCity());
                this.pstmt.setString(4, property.getPostcode());
                this.pstmt.setString(5, property.getType());
                this.pstmt.setInt(6, property.getRoomHave());
                this.pstmt.setDouble(7, property.getRentFee());
                this.pstmt.setString(8, property.getOwnerNo());
                this.pstmt.setString(9, property.getStaffNo());
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
    public LinkedList<Property> getAll() throws Exception {
        LinkedList<Property> result = new LinkedList<>();
        try {
            String sql= "SELECT * FROM \"Property\"";
            this.pstmt = this.conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Property row = new Property();
                row.setPropNo(rs.getString(1));
                row.setStaffNo(rs.getString(2));
                row.setCity(rs.getString(3));
                row.setPostcode(rs.getString(4));
                row.setType(rs.getString(5));
                row.setRoomHave(rs.getInt(6));
                row.setRentFee(rs.getDouble(7));
                row.setOwnerNo(rs.getString(8));
                row.setStaffNo(rs.getString(9));
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
    public boolean update(Property property) throws Exception {
        boolean flag = false;
        try{
            if (find(property)) {
                String sql ="UPDATE \"Property\"" +
                        "SET \"street\"=?, \"city\"=?, \"postcode\"=?," +
                        "\"type\"=?, \"roomHave\"=?, \"rentFee\"=?,\"ownerNo\"=?," +
                        "\"staffNo\"=?" +
                        " WHERE \"propNo\"=?";
                this.pstmt = this.conn.prepareStatement(sql);
                this.pstmt.setString(1, property.getStaffNo());
                this.pstmt.setString(2, property.getCity());
                this.pstmt.setString(3, property.getPostcode());
                this.pstmt.setString(4, property.getType());
                this.pstmt.setInt(5, property.getRoomHave());
                this.pstmt.setDouble(6, property.getRentFee());
                this.pstmt.setString(7, property.getOwnerNo());
                this.pstmt.setString(8, property.getStaffNo());
                this.pstmt.setString(9, property.getStaffNo());
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
    public boolean delete(Property property) throws Exception {
        boolean flag = false;
        try {
            if (find(property)) {
                String sql = "DELETE FROM \"Property\" WHERE \"propNo\"=?" ;
                this.pstmt = this.conn.prepareStatement(sql);
                this.pstmt.setString(1, property.getPropNo());
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
    public boolean find(Property property) throws Exception {
        boolean flag = false;
        try {
            String sql = "SELECT * FROM \"Property\" WHERE \"propNo\"=?" ;
            this.pstmt = this.conn.prepareStatement(sql);
            this.pstmt.setString(1, property.getPropNo());
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
    public LinkedList<MutipleInt> rentBranch() throws Exception {
        LinkedList<MutipleInt> result = new LinkedList<>();
        try {
            String sql= "SELECT SUM(\"rentFee\"), \"branchNo\" FROM \"Property\" p, \"Staff\" s WHERE p.\"staffNo\" = s.\"staffNo\" GROUP BY \"branchNo\"";
            this.pstmt = this.conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                MutipleInt row = new MutipleInt();
                row.setRentFee(rs.getDouble(1));
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

    @Override
    public LinkedList<Property> propertyWant(int room, double rent) throws Exception {
        LinkedList<Property> result = new LinkedList<>();
        try {
            String sql= "SELECT * FROM \"Property\" WHERE \"roomHave\">=? AND \"rentFee\"<=?";
            this.pstmt = this.conn.prepareStatement(sql);
            this.pstmt.setInt(1, room);
            this.pstmt.setDouble(2, rent);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Property row = new Property();
                row.setPropNo(rs.getString(1));
                row.setStaffNo(rs.getString(2));
                row.setCity(rs.getString(3));
                row.setPostcode(rs.getString(4));
                row.setType(rs.getString(5));
                row.setRoomHave(rs.getInt(6));
                row.setRentFee(rs.getDouble(7));
                row.setOwnerNo(rs.getString(8));
                row.setStaffNo(rs.getString(9));
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
    public LinkedList<MutipleInt> staffProperty(String branchNo) throws Exception {
        LinkedList<MutipleInt> result = new LinkedList<>();
        try {
            String sql= "SELECT COUNT(\"propNo\"), s.\"staffNo\", \"branchNo\" FROM \"Property\" p, \"Staff\" s WHERE p.\"staffNo\" = s.\"staffNo\" AND \"branchNo\" = ? GROUP BY \"branchNo\", s.\"staffNo\"";
            this.pstmt = this.conn.prepareStatement(sql);
            this.pstmt.setString(1, branchNo);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                MutipleInt row = new MutipleInt();
                row.setInt1(rs.getInt(1));
                row.setS1(rs.getString(2));
                row.setS2(rs.getString(3));
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
    public LinkedList<Property> clientProperty(String clientNo) throws Exception {
        LinkedList<Property> result = new LinkedList<>();
        try {
            String sql= "SELECT * FROM \"Property\" p WHERE EXISTS(SELECT * FROM \"client\" c WHERE \"ClientNo\"=? AND p.\"type\" = c.\"type\" AND p.\"rentFee\" <= c.\"maxRent\")";

            this.pstmt = this.conn.prepareStatement(sql);
            this.pstmt.setString(1, clientNo);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Property row = new Property();
                row.setPropNo(rs.getString(1));
                row.setStaffNo(rs.getString(2));
                row.setCity(rs.getString(3));
                row.setPostcode(rs.getString(4));
                row.setType(rs.getString(5));
                row.setRoomHave(rs.getInt(6));
                row.setRentFee(rs.getDouble(7));
                row.setOwnerNo(rs.getString(8));
                row.setStaffNo(rs.getString(9));
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
    public LinkedList<Property> notRentForThree() throws Exception {
        LinkedList<Property> result = new LinkedList<>();
        try {
            String sql= "SELECT * FROM \"Property\" p WHERE NOT EXISTS (SELECT * FROM \"lease\" l WHERE p.\"propNo\"=l.\"propNo\" AND (\"abs\"(CURRENT_DATE-\"startDate\")<=90 OR \"abs\"(CURRENT_DATE-\"endDate\")<=90))";
            this.pstmt = this.conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Property row = new Property();
                row.setPropNo(rs.getString(1));
                row.setStaffNo(rs.getString(2));
                row.setCity(rs.getString(3));
                row.setPostcode(rs.getString(4));
                row.setType(rs.getString(5));
                row.setRoomHave(rs.getInt(6));
                row.setRentFee(rs.getDouble(7));
                row.setOwnerNo(rs.getString(8));
                row.setStaffNo(rs.getString(9));
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
