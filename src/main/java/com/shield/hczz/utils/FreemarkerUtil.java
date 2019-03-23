package com.shield.hczz.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

import com.shield.frame.common.BaseTO;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * <b>功能：Free</b><br>
 * @version 1.0
 */
public class FreemarkerUtil {

    /**
     * 初始化freemarker实例
     */
    public static void process(File modelPath, File doc, BaseTO obj, String model, String url)
        throws IOException, TemplateException {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_0); // setting
                                                                            // specifies
                                                                            // the
                                                                            // FreeMarker
                                                                            // version
                                                                            // number
        cfg.setDefaultEncoding("UTF-8");
        cfg.setDirectoryForTemplateLoading(modelPath);
        // 1.向root中放入模版中所需信息
        Map<String, Object> root = new HashMap<String, Object>();
        root.put("ob", obj);
        // 2.将模版进行指定文件的输出
        Template t = cfg.getTemplate(model, "UTF-8");
        OutputStream out = new FileOutputStream(doc);
        t.process(root, new OutputStreamWriter(out, "UTF-8"));
    }
}