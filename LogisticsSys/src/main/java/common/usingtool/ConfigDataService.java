package common.usingtool;


import com.domain.DbInfo;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ConfigDataService {

    private static final String RESP = "config/Resource.xml";
    private static final String DB_RESP = "config/DbResource.xml";

    public static Map<String, String> confMap = new HashMap<String, String>();
    public static Map<String, DbInfo> dbConfMap = new HashMap<String, DbInfo>();

    static {
        read();
        dbInfoRead();
    }

    public static void reLoadConfigFile() {
        read();
        dbInfoRead();
    }

    //加载资源配置文件   /config/Resource.xml
    private static void read() {
        confMap.clear();
        try {
            SAXReader reader = new SAXReader();
            String path = getPath();
            File file = new File(path);
            if (!file.exists()) {
            } else {
                InputStream in = new FileInputStream(file);
                Document doc = reader.read(in);
                List<Element> root = doc.getRootElement().elements();
                for (Element e : root) {
                    confMap.put(e.attributeValue("id"), e.getText());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //加载DB的配置文件    /config/DbResource.xml
    private static void dbInfoRead() {
        dbConfMap.clear();
        DbInfo obj;
        try {
            SAXReader bdReader = new SAXReader();
            String path = getDBConPath();
            File file = new File(path);
            if (!file.exists()) {
            } else {
                InputStream in = new FileInputStream(file);
                Document doc = bdReader.read(in);
                List<Element> root = doc.getRootElement().elements();
                for (Element e : root) {
                    obj = LoadDbDomain(e, new DbInfo());
                    dbConfMap.put(e.attributeValue("id"), obj);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static DbInfo LoadDbDomain(Element e, DbInfo dbInfoObj) {
        List<Element> dbeList = e.elements();
        for (Element singleNode : dbeList) {
            switch (singleNode.attributeValue("id")) {
                case "dbtype":
                    dbInfoObj.setDbtype(singleNode.getText());
                    continue;
                case "DriverName":
                    dbInfoObj.setDriverName(singleNode.getText());
                    continue;
                case "conStr":
                    dbInfoObj.setConStr(singleNode.getText());
                    continue;
                case "dbuid":
                    dbInfoObj.setDbuid(singleNode.getText());
                    continue;
                case "dbupwd":
                    dbInfoObj.setDbupwd(singleNode.getText());
                    continue;
            }
        }
        return dbInfoObj;
    }

    //控制台打印配置集合
    private static void printMap() {
        Iterator<Map.Entry<String, String>> confit = confMap.entrySet().iterator();
        while (confit.hasNext()) {
            System.out.println(confit.next().getValue());
        }
    }

    /**
     * 获取Resource.xml里的值
     *
     * @Param confKey 通过key值获取
     */
    public static String getValue(String confKey) {
        return confMap.get(confKey);
    }

    //重新加载配置文件
    public static void reLoadXml() {
        read();
        dbInfoRead();
    }

    /***
     * 获取文件路径
     * @return
     */
    private static String getPath() {
        String rootPath = FileOpera.getProRootPath();
        return rootPath + RESP;
    }

    private static String getDBConPath() {
        String rootPath = FileOpera.getProRootPath();
        return rootPath + DB_RESP;
    }

    @Deprecated
    @SuppressWarnings("all")
    private static void readNodeMap(Element root, String prefix, Boolean hasStateCode, String flag, Map<String, String> map) {
        if (root == null) return;
        // 获取属性
        List<Attribute> attrs = root.attributes();
        if (attrs != null && attrs.size() > 0) {
            if (flag.equals(attrs.get(0).getName())) {
                if (hasStateCode) {
                    map.put(attrs.get(0).getValue(), attrs.get(1).getValue());
                } else {
                    map.put(attrs.get(0).getValue(), attrs.get(0).getValue());
                }
            }
        }
        // 获取他的子节点
        List<Element> childNodes = root.elements();
        prefix += "\t";
        for (Element e : childNodes) {
            readNodeMap(e, prefix, hasStateCode, flag, map);
        }
    }
}
