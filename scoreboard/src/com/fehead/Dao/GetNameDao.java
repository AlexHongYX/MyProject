package com.fehead.Dao;

import com.fehead.Util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class GetNameDao {

    public String getNameById(String id){
        Connection conn = DBUtil.getConnection();
        String sql = "SELECT ath_name from athletes where ath_id = ?";

        try {
            PreparedStatement ppst = conn.prepareStatement(sql);
            ppst.setString(1,id);
            ResultSet rs = ppst.executeQuery();

            if(rs.next()){
                return rs.getString("ath_name");
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
