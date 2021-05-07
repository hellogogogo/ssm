package cn.tycoding.controller.pdf.ItextExportPDF;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

@Slf4j
public class FreemarkerUtils {
    private FreemarkerUtils() {

    }

/**
 * 使用传入的Map对象，渲染指定的freemarker模板
 *
 * @param globalMap 数据绑定
 * @return html字符串
 */
public static String loadFtlHtml(File templateFile,String fileName, Map globalMap) throws Exception {
    if (StringUtils.isBlank(fileName) || globalMap == null) {
        throw new IllegalArgumentException("使用数据渲染模板时文件路径异常");
    }

    Configuration cfg = new Configuration(Configuration.VERSION_2_3_28);

    try (StringWriter stringWriter = new StringWriter()) {
        cfg.setDirectoryForTemplateLoading(templateFile);
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setClassicCompatible(true);

        Template temp = cfg.getTemplate(fileName);
        temp.process(globalMap, stringWriter);

        return stringWriter.toString();
    } catch (IOException | TemplateException e) {
        log.error("使用数据渲染模板时异常", e);
        throw new Exception("使用数据渲染模板异常", e);
    }
}
}
