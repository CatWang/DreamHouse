package dream.db;

import dream.daoInterface.IAdDAO;
import dream.entity.Ad;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * Created by lenovo on 2015/6/16.
 */
public class AdDAO implements IAdDAO{
    private Connection conn = null;
    private PreparedStatement pstmt = null;

    public AdDAO(Connection conn) {
        this.conn = conn;
    }
    @Override
    public boolean insert(Ad ad) throws Exception {
        boolean flag = false;
        try {
            if (!find(ad)) {
                String sql = "INSERT INTO \"ad\" VALUES (?,?,?,?)";
                this.pstmt = this.conn.prepareStatement(sql);
                this.pstmt.setString(1, ad.getPropNo());
                this.pstmt.setDate(2, ad.getAdDate());
                this.pstmt.setString(3, ad.getNewsName());
                this.pstmt.setDouble(4, ad.getCost());
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
    public LinkedList<Ad> getAll() throws Exception {
        LinkedList<Ad> result = new LinkedList<>();
        try {
            String sql= "SELECT * FROM \"ad\"";
            this.pstmt = this.conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Ad row = new Ad();
                row.setPropNo(rs.getString(1));
                row.setAdDate(rs.getDate(2));
                row.setNewsName(rs.getString(3));
                row.setCost(rs.getDouble(4));
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
    public boolean update(Ad ad) throws Exception {
        boolean flag = false;
        try{
            if (find(ad)) {
                String sql ="UPDATE \"ad\"" +
                        "SET \"cost\"=?" +
                        " WHERE \"propNo\"=? AND \"newsName\"=? AND \"adDate\"=?";
                this.pstmt = this.conn.prepareStatement(sql);
                this.pstmt.setDouble(1, ad.getCost());
                this.pstmt.setString(2, ad.getPropNo());
                this.pstmt.setString(3, ad.getNewsName());
                this.pstmt.setDate(4, ad.getAdDate());
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
    public boolean delete(Ad ad) throws Exception {
        boolean flag = false;
        try {
            if (find(ad)) {
                String sql = "DELETE FROM \"ad\" WHERE \"propNo\"=?" +
                        "AND \"adDate\"=? AND \"newsName\"=?";
                this.pstmt = this.conn.prepareStatement(sql);
                this.pstmt.setString(1, ad.getPropNo());
                this.pstmt.setDate(2, ad.getAdDate());
                this.pstmt.setString(3, ad.getNewsName());
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
    public boolean find(Ad ad) throws Exception {
        boolean flag = false;
        try {
            String sql = "SELECT * FROM \"ad\" WHERE \"propNo\"=?" +
                    "AND \"adDate\"=? AND \"newsName\"=?";
            this.pstmt = this.conn.prepareStatement(sql);
            this.pstmt.setString(1, ad.getPropNo());
            this.pstmt.setDate(2, ad.getAdDate());
            this.pstmt.setString(3, ad.getNewsName());
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
