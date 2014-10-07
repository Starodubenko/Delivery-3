package com.epam.star.dao;

import com.epam.star.entity.StatusPayCard;

public interface PayCardStatusDao extends Dao<StatusPayCard>{
    public StatusPayCard findByStatusName(String name);
}
