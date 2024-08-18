package com.kafei.test;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kafei.mapper.UserMapper;
import com.kafei.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
@SpringBootTest
public class SpringBootMybatisPlusTest {
    @Autowired
    private UserMapper userMapper;
    @Test
    public void test(){
        //创建page对象，设置分页参数，current参数也页码，size参数是每页的页容量
        Page<User> page = new Page<>(3, 1);
        //查询，并按照分压参数，将查询到的结果封装到page对象中
        userMapper.selectPage(page,null);
        //根据查询到的结果，获取分页参数
        Long current = page.getCurrent();//页码
        long size = page.getSize();//每页的页容量
        List<User> users = page.getRecords();//当前页的数据
        long total = page.getTotal();//总条数
        System.out.println(current);
        System.out.println(size);
        System.out.println(total);
        System.out.println(users);
    }
    @Test
    public void test01(){
         C c = new D();
         c.show();
    }
    class A{
        private String name;
        public int age;

        public A(String name) {
            this.name = name;
        }
    }
    class B extends A{
        public B(int age,String name) {
            super(name);
            this.age = age;
        }
    }
    interface C{
         String name = "张三";
        void show();
    }
    class D implements C{

        @Override
        public void show() {

        }
    }
    class E implements C{
        @Override
        public void show() {

        }
    }
    abstract class G{
    }
    class H extends G{
    }
    class I extends G{
    }


//    @Test
//    public void test1(){
//        Page<User> page = new Page<>(2, 1);
//        userMapper.queryByAge(page,21);
//        Long current = page.getCurrent();//页码
//        long size = page.getSize();//每页的页容量
//        List<User> users = page.getRecords();//当前页的数据
//        long total = page.getTotal();//总条数
//        System.out.println(current);
//        System.out.println(size);
//        System.out.println(total);
//        System.out.println(users);
//
//    }
//    @Test
//    public void test2(){
//        QueryWrapper<User> wrapper = new QueryWrapper<>();
//        wrapper.eq("id",5);
//        User user1 = userMapper.selectOne(wrapper);
//        User user2 = userMapper.selectOne(wrapper);
//        user1.setAge(22);//通过乐观锁只会修改这一条数据
//        //为什么呢？因为查询的这两条数据它们的version版本都是1
////        而执行第一条修改语句后，version版本变成了2，但执行第二条修改语句时，user1中的version版本还是1，所以执行第二条修改语句就会失败
//        user2.setAge(23);
//        userMapper.updateById(user1);
//        userMapper.updateById(user2);
//    }
}
