package dream.db;

import dream.daoInterface.IViewingDAO;
import dream.entity.Viewing;

import java.sql.*;
import java.util.LinkedList;

/**
 * Created by lenovo on 2015/6/16.
 */
public class ViewingDAO implements IViewingDAO {
    private Connection conn = null;
    private PreparedStatement pstmt = null;

    public ViewingDAO(Connection conn) {
        this.conn = conn;
    }
    @Override
    public boolean insert(Viewing viewing) throws Exception {
        boolean flag = false;
        try {
            if (!find(viewing)) {
                String sql = "INSERT INTO \"Viewing\" VALUES (?,?,?,?)";
                this.pstmt = this.conn.prepareStatement(sql);
                this.pstmt.setString(1, viewing.getClientNo());
                this.pstmt.setString(2, viewing.getPropNo());
                this.pstmt.setDate(3, viewing.getViewDate());
                this.pstmt.setString(4, viewing.getComment());
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
    public LinkedList<Viewing> getAll() throws Exception {
        LinkedList<Viewing> result = new LinkedList<>();
        try {
            String sql= "SELECT * FROM \"Viewing\"";
            this.pstmt = this.conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Viewing row = new Viewing();
                row.setClientNo(rs.getString(1));
                row.setPropNo(rs.getString(2));
                row.setViewDate(rs.getDate(3));
                row.setComment(rs.getString(4));
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
    public boolean update(Viewing viewing) throws Exception {
        boolean flag = false;
        try{
            if (find(viewing)) {
                String sql ="UPDATE \"Viewing\"" +
                        "SET \"comment\"=?" +
                        " WHERE \"ClientNo\"=? AND \"propNo\"=? AND \"viewDate\"=?";
                this.pstmt = this.conn.prepareStatement(sql);
                this.pstmt.setString(1, viewing.getComment());
                this.pstmt.setString(2, viewing.getClientNo());
                this.pstmt.setString(3, viewing.getPropNo());
                this.pstmt.setDate(4, viewing.getViewDate());
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
    public boolean delete(Viewing viewing) throws Exception {
        boolean flag = false;
        try {
            if (find(viewing)) {
                String sql = "DELETE FROM \"Viewing\" WHERE \"ClientNo\"=?" +
                        "AND \"propNo\"=? AND \"viewDate\"=?" ;
                this.pstmt = this.conn.prepareStatement(sql);
                this.pstmt.setString(1, viewing.getClientNo());
                this.pstmt.setString(2, viewing.getPropNo());
                this.pstmt.setDate(3, viewing.getViewDate());
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
    public boolean find(Viewing viewing) throws Exception {
        boolean flag = false;
        try {
            String sql = "SELECT * FROM \"Viewing\" WHERE \"ClientNo\"=?" +
                    "AND \"propNo\"=? AND \"viewDate\"=?";
            this.pstmt = this.conn.prepareStatement(sql);
            this.pstmt.setString(1, viewing.getClientNo());
            this.pstmt.setString(2, viewing.getPropNo());
            this.pstmt.setDate(3, viewing.getViewDate());
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
    public LinkedList<Viewing> detailComment(String propNo) throws Exception {
        LinkedList<Viewing> result = new LinkedList<>();
        try {
            String sql= "SELECT * FROM \"Viewing\" WHERE \"propNo\"=?";
            this.pstmt = this.conn.prepareStatement(sql);
            this.pstmt.setString(1, propNo);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Viewing row = new Viewing();
                row.setClientNo(rs.getString(1));
                row.setPropNo(rs.getString(2));
                row.setViewDate(rs.getDate(3));
                row.setComment(rs.getString(4));
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
