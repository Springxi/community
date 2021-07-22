package com.project.community.dao;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository("alphaMyBatis")
@Primary //拥有更高优先级，优先装配
public class AlphaDaoMyBatisImpl implements AlphaDao {

    @Override
    public String select() {
        return "MyBatis";
    }
}
