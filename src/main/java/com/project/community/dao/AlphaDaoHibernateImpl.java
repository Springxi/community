package com.project.community.dao;

import org.springframework.stereotype.Repository;

@Repository("alphaHibernate")  //数据访问 bean使用 @Repository
public class AlphaDaoHibernateImpl implements AlphaDao {
    @Override
    public String select() {
        return "Hibernate";
    }
}
