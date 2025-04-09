package com.example.springbootdemo.service.impl;

import com.example.springbootdemo.mapper.ArticleMapper;
import com.example.springbootdemo.pojo.Article;
import com.example.springbootdemo.pojo.PageBean;
import com.example.springbootdemo.service.ArticleService;
import com.example.springbootdemo.utils.ThreadLocalUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;
    @Override
    //发布文章
    public void add(Article article) {
        //补充属性值
        article.setCreateTime(LocalDateTime.now());
        article.setUpdateTime(LocalDateTime.now());

        Map<String,Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        article.setCreateUser(id);
        articleMapper.add(article);
    }

    @Override
    //文章列表，分页查询
    public PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state) {
        //创建PageBean对象
        PageBean<Article> pb = new PageBean<>();

        //开启分页查询 调用MyBatis插件Pagehelper
        PageHelper.startPage(pageNum,pageSize);//会自动拼接到SQL语句中

        //调用mapper
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        //只能查询当前已登录用户的id的文章
        List<Article> as = articleMapper.list(userId,categoryId,state);
        //Page中提供了方法，可以获取PageHelper分页查询后得到的总记录条数和当前页数据
        Page<Article> p = (Page<Article>) as;

        //把数据填充到PageBean对象中
        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());
        return pb;
    }

    @Override
    //根据ID查询文章
    public Article findById(Integer id) {
        Article article = articleMapper.findByid(id);
        return article;
    }

    @Override
    //更新文章
    public void update(Article article) {
        article.setUpdateTime(LocalDateTime.now());
        articleMapper.update(article);
    }
    //删除文章
    @Override
    public void deleteById(Integer id) {
        articleMapper.deleteById(id);
    }



}
