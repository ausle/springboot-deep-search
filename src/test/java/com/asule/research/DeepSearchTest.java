package com.asule.research;


import com.asule.research.dao.SysUserDao;
import com.asule.research.entity.SysUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

// 关于怎么写测试方法：
@RunWith(SpringRunner.class) //指定测试运行器，需结合junit来使用
@SpringBootTest //加载完整的spring应用上下文
// 测试类名最好以Test结尾，这个不做强制要求。
public class DeepSearchTest {


    @Autowired
    private SysUserDao sysUserDao;

    // 测试方法需要是public
    @Test
    public void test1() {
        SysUser sysUser = sysUserDao.selectById(2);
        System.out.println(sysUser.getEmail());
    }
}
