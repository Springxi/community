package com.project.community.controller;

import com.project.community.entity.DiscussPost;
import com.project.community.entity.Page;
import com.project.community.entity.User;
import com.project.community.service.DiscussPostService;
import com.project.community.service.UserService;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.rmi.MarshalledObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller

public class HomeController {
    @Autowired
    private UserService userService;

    @Autowired
    private DiscussPostService discussPostService;

    @RequestMapping(path = "/index",method = RequestMethod.GET)
    public String getIndexPage(Model model, Page page){
        //通过DiscussPosts 找到对应的 DiscussPost 数组
        page.setPath("/index");
        page.setRows(discussPostService.findDiscussPostRows(0));
        List<DiscussPost> list = discussPostService.findDiscussPosts(0,page.getOffset(),page.getLimit());
        //List<DiscussPost> list = discussPostService.findDiscussPosts(0,0,10);

        //查到的数据只是DiscussPost，而我们需要对应的名字，因此新建一个list，
        // 针对每一个DiscussPost，根据id查到User，组装数据 <User,DiscussPost>
        List<Map<String,Object>> discussPosts = new ArrayList<>();
        if (list != null){
            for (DiscussPost post : list){
                Map<String,Object> map = new HashMap<>();
                map.put("post",post);
                User user = userService.findUserById(post.getUserId());
                if (user == null) {
                    System.out.println(post);
                    System.out.println(post.getUserId());
                }
                map.put("user",user);
                discussPosts.add(map);
            }
        }
        model.addAttribute("discussPosts",discussPosts);
        return "/index";
    }
}
