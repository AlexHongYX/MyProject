package com.fehead.service;

import com.fehead.Dao.GetNameDao;

public class GetNameService {

    public String getNameServiceById(String id){
        GetNameDao getNameDao = new GetNameDao();
        return getNameDao.getNameById(id);
    }
}
