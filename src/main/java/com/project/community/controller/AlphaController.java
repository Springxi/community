package com.project.community.controller;


import com.project.community.service.AlphaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@Controller
@RequestMapping("/alpha")
public class AlphaController {
    @Autowired
    private AlphaService alphaService;


    @RequestMapping("/hello")
    @ResponseBody
    public String sayHello(){
        return "Hello Spring Boot!";
    }

    @RequestMapping("/data")
    @ResponseBody
    public String getData(){
        return alphaService.find();
    }



    /**
     * 获取请求，响应的数据（底层的）
     *
     *  通过respond 对象可以直接向浏览器输出任何数据，不依赖返回值
     *  但是这样写很麻烦
     */
    @RequestMapping("http")
    public void http(HttpServletRequest request, HttpServletResponse response){
        //----------------获取请求数据---------------------------------------
        System.out.println(request.getMethod());
        System.out.println(request.getServletPath());
        //消息头
        Enumeration <String> enumeration = request.getHeaderNames(); //获得的是迭代器
        while ( enumeration.hasMoreElements()){
            String name = enumeration.nextElement();
            String value = request.getHeader(name);
            System.out.println(name+" : "+value);
        }
        //消息体 （业务数据，含参数）
        System.out.println(request.getParameter("code"));

        //----------------返回响应数据---------------------------------------
        response.setContentType("text/html ; charset=utf-8"); //设置返回类型，返回网页类型的文本
        try (PrintWriter printWritet = response.getWriter()){
            printWritet.write("<h1>学习使我快乐！</h1>");
        }catch (IOException e){
            e.printStackTrace();
        }

    }


    /**
     * Get 请求
     *  students?current=1&limit=20    分页条件，当前第几页，每页显示多少条
     */
    @RequestMapping(path = "/students",method = RequestMethod.GET)
    @ResponseBody
    public String getStudent(
            @RequestParam(name="current",required = false,defaultValue = "1") int current,
            @RequestParam(name="limit",required = false,defaultValue = "10") int limit
    ){
        System.out.println(current);
        System.out.println(limit);
        return "some students";
    }


    @RequestMapping(path = "/student/{id}",method = RequestMethod.GET)
    @ResponseBody
    public String getStudent( @PathVariable("id") int id ){
        System.out.println(id);
        return "a student";
    }


    /**
     * Post 请求
     *  为什么不是用GET提交而使用POST？ * 直接显示在访问地址，隐秘性不好 * 地址长度有限，大量数据不合适
     *
     */

    @RequestMapping(path = "/student",method = RequestMethod.POST)
    @ResponseBody
    public String saveStudent(String name, int age){
        System.out.println(name);
        System.out.println(age);
        return "success";
    }


    /**
     * 响应动态的HTML数据，2种方式 （不加@ResponseBody ,修改返回值）
     *   ModelAndView        Model
     */
    @RequestMapping(path = "/teacher",method = RequestMethod.GET)
    public ModelAndView getTeacher(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("name","张三");
        modelAndView.addObject("age",12);
        modelAndView.setViewName("/demo/view"); //放在 templates下，不用写templates，也不用写文件格式，默认格式html
        return modelAndView;
    }

    @RequestMapping(path = "/school",method = RequestMethod.GET)
    public String getSchool (Model model){ //String 类型返回的是 Model 的路径
        model.addAttribute("name","北京大学");
        model.addAttribute("age",120);
        return "/demo/view";
        //把model装到model中，将view直接返回，返回值给了servelet,并且servelet持有model的引用。
    }






}
