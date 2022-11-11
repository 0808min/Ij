package com.example.test.dept;

import com.example.test.util.ConnectionUtil;

import java.util.List;

public class DeptService {

    private DeptDao dao = new DeptDao();

    public List<Dept> getList() throws Exception{

        List<Dept> list = dao.selectAll(ConnectionUtil.getInstance().getConnection());

        return list;
    }

}
