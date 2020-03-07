package com.github.qingying0.community;


import com.github.qingying0.community.utils.MailClient;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class MailTest {

    @Autowired
    private MailClient mailClient;

    @Autowired
    private Configuration configuration;

    @Test
    public void testTextMail() {
        mailClient.sendMail("1134686967@qq.com",
                "test",
                "欢迎");
    }

    @Test
    public void testFreemarker() throws IOException, TemplateException {
        Template template = configuration.getTemplate("mail.ftl");
        Map param = new HashMap();
        param.put("email", "1134@qq.com");
        param.put("url", "localhost:8080/avtive");
        String html;
        StringWriter writer = new StringWriter();
        template.process(param, writer);
        html = writer.toString();
        System.out.println(html);
        mailClient.sendMail("1134686967@qq.com", "激活邮箱", html);
        System.out.println("finish send html ============================================================================");
    }
}
