package dream.db;

import dream.daoInterface.INewspaperDAO;
import dream.entity.Newspaper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * Created by lenovo on 2015/6/16.
 */
public class NewspaperDAO implements INewspaperDAO{
    private Connection conn = null;
    private PreparedStatement pstmt = null;

    public NewspaperDAO(Connection conn) {
        this.conn = conn;
    }
    @Override
    public boolean insert(Newspaper newspaper) throws Exception {
        boolean flag = false;
        try {
            if (!find(newspaper)) {
                String sql = "INSERT INTO \"Newspaper\" VALUES (?,?,?,?,?,?,?)";
                this.pstmt = this.conn.prepareStatement(sql);
                this.pstmt.setString(1, newspaper.getNewsName());
                this.pstmt.setString(2, newspaper.getStreet());
                this.pstmt.setString(3, newspaper.getCity());
                this.pstmt.setString(4, newspaper.getPostcode());
                this.pstmt.setString(5, newspaper.getTeleNo());
                this.pstmt.setString(6, newspaper.getfName());
                this.pstmt.setString(7, newspaper.getlName());
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
    public LinkedList<Newspaper> getAll() throws Exception {
        LinkedList<Newspaper> result = new LinkedList<>();
        try {
            String sql= "SELECT * FROM \"Newspaper\"";
            this.pstmt = this.conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Newspaper row = new Newspaper();
                row.setNewsName(rs.getString(1));
                row.setStreet(rs.getString(2));
                row.setCity(rs.getString(3));
                row.setPostcode(rs.getString(4));
                row.setTeleNo(rs.getString(5));
                row.setfName(rs.getString(6));
                row.setlName(rs.getString(7));
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
    public boolean update(Newspaper newspaper) throws Exception {
        boolean flag = false;
        try{
            if (find(newspaper)) {
                String sql ="UPDATE \"Newspaper\"" +
                        "SET \"street\"=?, \"city\"=?, \"postcode\"=?," +
                        "\"teleNo\"=?, \"fName\"=?, \"lName\"=?" +
                        " WHERE \"newsName\"=?";
                this.pstmt = this.conn.prepareStatement(sql);
                this.pstmt.setString(1, newspaper.getStreet());
                this.pstmt.setString(2, newspaper.getCity());
                this.pstmt.setString(3, newspaper.getPostcode());
                this.pstmt.setString(4, newspaper.getTeleNo());
                this.pstmt.setString(5, newspaper.getfName());
                this.pstmt.setString(6, newspaper.getlName());
                this.pstmt.setString(7, newspaper.getNewsName());
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
    public boolean delete(Newspaper newspaper) throws Exception {
        boolean flag = false;
        try {
            if (find(newspaper)) {
                String sql = "DELETE FROM \"Newspaper\" WHERE \"newsName\"=?";
                this.pstmt = this.conn.prepareStatement(sql);
                this.pstmt.setString(1, newspaper.getNewsName());
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
    public boolean find(Newspaper newspaper) throws Exception {
        boolean flag = false;
        try {
            String sql = "SELECT * FROM \"Newspaper\" WHERE \"newsName\"=?" ;
            this.pstmt = this.conn.prepareStatement(sql);
            this.pstmt.setString(1, newspaper.getNewsName());
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
}
