package com.tmallshopping;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.api.ShellCallback;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TmallshoppingApplicationTests {

    @Test
    public void contextLoads() throws Exception{
        // warnings 为用于放置生成过程中警告信息的集合对象
        List<String> warnings = new ArrayList<>();
        // 指定是否覆盖重名文件
        boolean overwrite = true;
        // 加载配置文件
        File configFile = new File(MyBatisGenerator.class.getClassLoader().getResource("generatorConfig.xml").toURI());
        // 配置解析类
        ConfigurationParser cp = new ConfigurationParser(warnings);
        // 配置解析类解析配置文件并生成 Configuration 配置对象
        Configuration config = cp.parseConfiguration(configFile);
        // ShellCallback 负责如何处理重复文件
        ShellCallback callback = new DefaultShellCallback(overwrite);
        // 逆向工程对象
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        // 执行逆向文件生成操作
        myBatisGenerator.generate(null);
        // 打印提示信息
        System.out.println("MyBatis 逆向工程执行成功，刷新项目查看文件！");
    }

}
