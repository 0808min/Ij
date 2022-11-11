package com.example.test.dept;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeptDao {

    public List<Dept> selectAll(Connection conn) {

        String sql = "Select + from dept";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Dept> result = null;

        try {
            pstmt = conn.prepareStatement(sql);

            rs = pstmt.executeQuery();

            result = new ArrayList<>();

            while (rs.next()) {
                result.add(new Dept(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}
