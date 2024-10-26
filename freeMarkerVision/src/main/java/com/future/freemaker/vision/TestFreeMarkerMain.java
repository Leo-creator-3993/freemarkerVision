package com.future.freemaker.vision;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.Test;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class TestFreeMarkerMain {

    @Test
    public void test0() {
        System.out.println("hi");
    }

    @Test
    public void testGenDoc() throws IOException, TemplateException {
        //创建FreeMarker配置对象
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_0);
        cfg.setDirectoryForTemplateLoading(new File("src/main/resources/templates"));
        cfg.setDefaultEncoding("UTF-8");

        //加载模版
        Template template = cfg.getTemplate("contract.ftl");

        //创建数据模型
        Map<String, Object> dataModel = new HashMap<>();
        Map<String, String> contract = new HashMap<>();
        contract.put("contractId", "CN20241022001");
        contract.put("customerName", "Z.shan");
        contract.put("contractDate", "2024-10-22");
        contract.put("content", "经协商, 乙方同意按月支付甲方的房租租金 800 元整, 合同自签订之日起立即生效, 有效期为1年.");
        contract.put("signerName", "L.si");
        dataModel.put("contract", contract);

        //生成合同文件
        File outputFile = new File("src/main/resources/out/contract.doc");
        outputFile.getParentFile().mkdirs();
        Writer out = new OutputStreamWriter(new FileOutputStream(outputFile), "UTF-8");
        template.process(dataModel, out);
        out.close();

        System.out.println("合同已生成 ==> " + outputFile.getAbsolutePath());
    }
}
