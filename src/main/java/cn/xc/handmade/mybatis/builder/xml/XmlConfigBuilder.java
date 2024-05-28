package cn.xc.handmade.mybatis.builder.xml;

import cn.xc.handmade.mybatis.builder.BaseBuilder;
import cn.xc.handmade.mybatis.io.Resources;
import cn.xc.handmade.mybatis.mapping.MappedStatement;
import cn.xc.handmade.mybatis.mapping.SqlCommandType;
import cn.xc.handmade.mybatis.session.Configuration;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.InputSource;

import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XmlConfigBuilder extends BaseBuilder {

    private Element root;

    public XmlConfigBuilder(Reader reader) {
        // 创建Configuration对象，初始化父类
        super(new Configuration());
        // dom4j 处理 xml
        SAXReader saxReader = new SAXReader();
        try {
            Document document = saxReader.read(new InputSource(reader));
            root = document.getRootElement();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    public Configuration parse() {
        try {
            // 解析映射器
            mapperElement(root.element("mappers"));
        } catch (Exception e) {
            throw new RuntimeException("Error parsing SQL Mapper Configuration. Cause: " + e, e);
        }
        return configuration;
    }

    private void mapperElement(Element mappers) throws Exception {
        List<Element> mapperList = mappers.elements("mapper");
        for (Element e : mapperList) {
            String resource = e.attributeValue("resource");
            // 解析对应的mapper.xml文件
            Reader reader = Resources.getResourceAsReader(resource);
            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(new InputSource(reader));
            Element root = document.getRootElement();
            //命名空间
            String namespace = root.attributeValue("namespace");

            // SELECT
            List<Element> selectNodes = root.elements("select");
            for (Element node : selectNodes) { // 对每个 <Select> 进行处理
                String id = node.attributeValue("id");
                String msId = namespace + "." + id;
                String parameterType = node.attributeValue("parameterType");
                String resultType = node.attributeValue("resultType");
                String sql = node.getTextTrim();

                // ? 匹配
                Map<Integer, String> parameter = new HashMap<>();
                Pattern pattern = Pattern.compile("(#\\{(.*?)})");
                Matcher matcher = pattern.matcher(sql);
                for (int i = 1; matcher.find(); i++) {
                    String g1 = matcher.group(1);
                    String g2 = matcher.group(2);
                    parameter.put(i, g2);
                    sql = sql.replace(g1, "?");
                }

                String nodeName = node.getName();
                SqlCommandType sqlCommandType = SqlCommandType.valueOf(nodeName.toUpperCase(Locale.ENGLISH));
                MappedStatement mappedStatement = new MappedStatement.Builder(configuration, msId, sqlCommandType, parameterType, resultType, sql, parameter).build();
                configuration.addMappedStatement(mappedStatement);
            }
            // 注册Mapper映射器
            configuration.addMapper(Resources.classForName(namespace));
        }
    }


}
