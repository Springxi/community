package com.project.community.dao;

import com.project.community.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DiscussPostMapper {
    //分页查询数据，查询的可能是多条信息，因此返回的是一个集合

    //userID针对个性化，搜索我查找的帖子，首页不会传Id，因此其实实现的是一个动态的sql
    //考虑到未来支持分页的可能性，mysql的分页十分简洁，加一个limit + 起始行行号offset  +  分页展示多少条数据limit

    List<DiscussPost> selectDiscussPosts (int userId, int offset, int limit);



    //为了显示页码（查询的总条数/分页展示数据的条数limit）,因此定义函数查询总的数据条数。同样动态sql
    //参数之前加注解 @Param("") 起别名，如果只有一个参数，并且在<if>中使用，则必须要有别名
    // 即：若需要动态的拼一个条件（动态sql），且需要用的这个参数，并且方法有且只有一个参数，则必须要起别名！
    int selectDiscussPostRows(@Param("userId") int userId);

}
