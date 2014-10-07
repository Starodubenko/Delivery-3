package com.epam.star.dao;

import com.epam.star.dao.H2dao.DaoManager;

public interface DaoCommand {
    public Object execute(DaoManager daoManager);
}
