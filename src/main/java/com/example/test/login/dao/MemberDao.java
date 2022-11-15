package com.example.test.login.dao;

import com.example.test.login.domain.Member;

import java.sql.Connection;
import java.sql.SQLException;

public interface MemberDao {

    // 회원가입
    int insertMember(Connection conn, Member member) throws SQLException;
    // 회원 존재여부 확인
    Member selectByUidPw(Connection conn, String uid, String pw) throws SQLException;

    int updateUUIDBYIdx(Connection conn, String uuid, int idx) throws SQLException;

    Member selectBYUUID(Connection conn, String uuid) throws SQLException;

}
