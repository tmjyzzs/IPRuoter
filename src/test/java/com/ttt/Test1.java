package com.ttt;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.junit.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

/**
 * Description TODO
 * DATA 2023-12-06
 *
 * @Author ttt
 */
public class Test1 {

    @Test
    public void test() throws IOException {
        System.out.println("1111111");
        Properties prop = new Properties();

        prop.put("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        Velocity.init(prop);

        //创建Velocity容器
        VelocityContext context = new VelocityContext();
        context.put("name", "zhangsan");

        //加载模板
        Template tpl = Velocity.getTemplate("vms/demo1.vm", "UTF-8");

        FileWriter fw  = new FileWriter("D:\\ttt\\demo1.html");
        //合并数据到模板
        tpl.merge(context, fw);

        //释放资源
        fw.close();

    }
}
