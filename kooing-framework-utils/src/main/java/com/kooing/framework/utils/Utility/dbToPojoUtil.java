package com.kooing.framework.utils.Utility;

/**
 * @Author : kooing
 * @Date : 2017/10/16 - 20:40
 * @Desription :
 * @Modified by :
 */
import lombok.Data;

import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.Date;

@Data
public class dbToPojoUtil {

    private String packageOutPath = "com.user.entity";//指定实体生成所在包的路径
    private String authorName = "kooing";//作者名字
    private String tableName = "user1";//表名
    private String[] importPackage;//导入的包
    private String[] annotation;//注解
    private String implementsObject;//实现的接口
    private String extendsObject;//继承的对象
    private String sourceRoot = "/src/main/java/";
    private String classSuffix;//添加的类前缀
    private String classPrefix;//添加的类后缀
    private Map<String, String> colComment;//列在数据的comment
    private String[] colnames; // 列名数组
    private String colTypes; //列名类型数组
    private String dbComment;//是否注入数据库注释
    private int[] colSizes; //列名大小数组


    //数据库连接
    private static final String URL = "jdbc:mysql://localhost:3306/db_kooing_saas?useUnicode=true&characterEncoding=utf-8";
    private static final String NAME = "root";
    private static final String PASS = "";
    private static final String DRIVER = "com.mysql.jdbc.Driver";

    /*
     * 构造函数
     */
    public dbToPojoUtil() {
        Properties properties = new Properties();
        try {
            //读取属性文件a.propertiesD:\ideaWorkSpace\dbToPojoUtil\src\main\resources\properties\dbToPojoUtil.properties
            InputStream in = new BufferedInputStream(this.getClass().getResourceAsStream("/properties/dbToPojoUtil.properties"));
            properties.load(in);     ///加载属性列表

            packageOutPath = properties.getProperty("packageOutPath");
            authorName = properties.getProperty("authorName");
            tableName = properties.getProperty("tableName");
            sourceRoot = properties.getProperty("sourceRoot");
            extendsObject = properties.getProperty("extendsObject");
            implementsObject = properties.getProperty("implementsObject");
            classSuffix = properties.getProperty("classSuffix");
            classPrefix = properties.getProperty("classPrefix");
            String importPackageTemp = properties.getProperty("importPackage");
            String annotationTemp = properties.getProperty("annotation");
            dbComment = properties.getProperty("dbComment");
            importPackage = importPackageTemp.split(",");
            annotation = annotationTemp.split(",");

            in.close();

        } catch (Exception e) {
            System.out.println(e);
        }
        //创建连接
        Connection con;
        //查要生成实体类的表
        String sql = "select * from " + tableName;
        PreparedStatement pStemt = null;
        try {
            try {
                Class.forName(DRIVER);
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }
            con = DriverManager.getConnection(URL, NAME, PASS);
            pStemt = con.prepareStatement(sql);
            ResultSetMetaData rsmd = pStemt.getMetaData();
            int size = rsmd.getColumnCount();    //统计列
            colTypes = new String();
            colSizes = new int[size];
            colnames = new String[size];
            if (!dbComment.equals("")) {
                colComment = getComment(tableName);
            }

            for (int i = 0; i < size; i++) {
                colnames[i] = initcap(rsmd.getColumnName(i + 1));
                colTypes = rsmd.getColumnTypeName(i + 1);
                colSizes[i] = rsmd.getColumnDisplaySize(i + 1);
            }

            String content = parse(colnames, colSizes);

            try {
                File directory = new File("");

                String outputPath = directory.getAbsolutePath() + sourceRoot + this.packageOutPath.replace(".", "/") + "/" + toUpperCase(initcap(tableName)) + ".java";
                FileWriter fw = new FileWriter(outputPath);
                PrintWriter pw = new PrintWriter(fw);
                pw.println(content);
                pw.flush();
                pw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
//			try {
//				con.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
        }
    }

    /**
     * 功能：生成实体类主体代码
     *
     * @param colnames
     * @param colSizes
     * @return
     */
    private String parse(String[] colnames, int[] colSizes) {
        StringBuffer sb = new StringBuffer();
        //包名

        sb.append("package " + this.packageOutPath + ";\r\n\r\n");
        //导入包
        for (String onePackage : this.importPackage) {
            sb.append("import " + onePackage + ";" + "\r\n");
        }
        //注释部分
        sb.append("\r\n");
        sb.append("/**\r\n");
        sb.append("* " + "@Author\t:" + this.authorName + "\r\n");
        sb.append("* " + "@Date\t:" + new Date() + "\r\n");
        sb.append("* " + "@Desription\t:" + this.tableName + " 实体类\r\n");
        sb.append("* " + "@Modified\t:" + "\r\n");
        sb.append("*/ \r\n");
        //注解
        for (String oneAnnotion : this.annotation) {
            sb.append(oneAnnotion + "\r\n");
        }
        //实体部分
        sb.append("public class " + extendAndImplements(toUpperCase(initcap(tableName))) + "{\r\n");
        processAllAttrs(sb);//属性
        sb.append("}\r\n");

        //System.out.println(sb.toString());
        return sb.toString();
    }

    /**
     * 功能：生成所有属性
     *
     * @param sb
     */
    private void processAllAttrs(StringBuffer sb) {

        for (int i = 0; i < colnames.length; i++) {
            sb.append("\t/** " + colComment.get(colnames[i]) + "*/\r\n");
            sb.append("\tprivate " + "String " + colnames[i] + ";\r\n");
        }

    }


    /**
     * 功能：把下划线去掉同时变大写字母
     *
     * @param str
     * @return
     */
    private String initcap(String str) {

        char[] ch = str.toCharArray();
        for (int i = 1; i < str.length(); i++) {
            if (ch[i] == '_') {
                if (ch[i + 1] >= 'a' && ch[i + 1] <= 'z') {
                    ch[i + 1] = (char) (ch[i + 1] - 32);
                }
            }
        }
        return new String(ch).replaceAll("_", "");
    }

    /**
     * @return :
     * @Author : kooing
     * @Date : 2017/10/16 15:31
     * @Desription : 首字母大写
     */
    private String toUpperCase(String str) {

        char[] ch = str.toCharArray();
        if (ch[0] >= 'a' && ch[0] <= 'z') {
            ch[0] = (char) (ch[0] - 32);
        }

        String stringTemp = new String(ch).replaceAll("_", "");
        return classPrefix + stringTemp + classSuffix;
    }

    /**
     * @return :
     * @Author : kooing
     * @Date : 2017/10/16 17:51
     * @Desription : 添加注释
     */
    private Map<String, String> getComment(String tableName) {
        Map<String, String> map = new HashMap<String, String>();
        String URL = "jdbc:mysql://localhost:3306/information_schema?useUnicode=true&characterEncoding=utf-8";
        String sql = "SELECT COLUMN_NAME, COLUMN_COMMENT " +
                "FROM" +
                "`COLUMNS` " +
                "WHERE table_name='" + tableName + "'";
        try {
            Class.forName(DRIVER);
            Connection con = DriverManager.getConnection(URL, NAME, PASS);
            PreparedStatement statement = con.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            for (; rs.next(); ) {
                map.put(initcap(rs.getString("COLUMN_NAME")), rs.getString("COLUMN_COMMENT"));
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return map;
    }

    /**
     * @return :
     * @Author : kooing
     * @Date : 2017/10/16 15:14
     * @Desription : 处理了继承，和实现接口
     */
    private String extendAndImplements(String classString) {
        if (extendsObject.equals("")) {
            if (implementsObject.equals("")) {

            } else {
                classString += " implements " + implementsObject;
            }
        } else if (implementsObject.equals("")) {
            classString += " extends " + extendsObject;
        } else {
            classString += " extends " + extendsObject + " implements " + implementsObject;
        }
        return classString;
    }

    /**
     * 出口
     *
     * @param args
     */
    public static void main(String[] args) {
        new dbToPojoUtil();

    }
}