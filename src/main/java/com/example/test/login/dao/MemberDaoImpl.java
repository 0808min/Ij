package com.example.test.login.dao;

import com.example.test.login.domain.Member;
import lombok.Cleanup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberDaoImpl implements MemberDao {
    @Override
    public int insertMember(Connection conn, Member member) throws SQLException {

        String sql = "insert into member (uid, pw) values (?,?)";
        @Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, member.getUid());
        pstmt.setString(2, member.getPw());
        int result = pstmt.executeUpdate();

        return result;
    }

    @Override
    public Member selectByUidPw(Connection conn, String uid, String pw) throws SQLException {
        String sql = "Select * from member where uid=? and pw=?";
        @Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, uid);
        pstmt.setString(2, pw);
        @Cleanup ResultSet rs = pstmt.executeQuery();

        Member member = null;
        if(rs.next()) {
            member = Member.builder()
                    .idx(rs.getInt("idx"))
                    .uid(rs.getString("uid"))
                    .pw(rs.getString("pw"))
                    .uuid(rs.getString("uuid"))
                    .build();
        }

        return member;
    }

    @Override
    public int updateUUIDBYIdx(Connection conn, String uuid, int idx) throws SQLException {

        String sql = "update member set uuid=? where idx=?";
        @Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, uuid);
        pstmt.setInt(2, idx);
        int result = pstmt.executeUpdate();

        return result;
    }
}
