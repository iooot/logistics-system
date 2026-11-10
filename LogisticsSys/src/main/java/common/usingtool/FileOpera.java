package common.usingtool;

import common.SqlHelper.DataService;
import org.apache.tomcat.util.http.fileupload.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;

public class FileOpera {

    private static String proRootPath = "";

    /**
     * 判断路径是否存在,如果不存在就创建（必须含有后缀的文件名）
     */
    public static boolean mkFileWithPath(String fileFullPath) {
        boolean boo = false;
        File file = new File(fileFullPath);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        try {
            file.createNewFile();
            boo = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return boo;
    }

    /**
     * 获取项目根路径
     */
    public static String getProRootPath() {
        proRootPath = proRootPath.trim();
        if (proRootPath == null || proRootPath == "") {
            proRootPath = FileOpera.class.getResource("/").getPath();
        }
        return proRootPath;
    }

    public static void insLog(String oprlog, String modual, String uid, String uname) {
        String logInsert = ConfigDataService.getValue("logInsert");
        DataService.execute(logInsert, new String[]{oprlog, modual, uid, uname});
    }


    public static String getFileMD5(String strPath) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        FileInputStream fis = new FileInputStream(strPath);
        byte[] buffer = new byte[1024];
        int len;
        while ((len = fis.read(buffer)) != -1) {
            md.update(buffer, 0, len);
        }
        fis.close();
        byte[] digest = md.digest();
        StringBuilder sb = new StringBuilder();
        for (byte b : digest) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }


}
