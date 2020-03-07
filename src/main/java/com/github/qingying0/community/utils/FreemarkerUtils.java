package com.github.qingying0.community.utils;

import com.github.qingying0.community.exception.CustomException;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.StringWriter;
import java.util.Map;

@Component
public class FreemarkerUtils {

    @Autowired
    private Configuration configuration;

    public String processHtml(Map param) {
        try {
            Template template = configuration.getTemplate("mail.ftl");
            String html;
            StringWriter writer = new StringWriter();
            template.process(param, writer);
            html = writer.toString();
            return html;
        } catch (Exception e) {
            throw new CustomException("发送激活邮件失败");
        }
    }
}
