package common.usingtool;

import common.SqlHelper.DataService;
import com.domain.User;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

public class UploadFile {
       public String getUploadFilePath(HttpServletRequest request) {
        DiskFileItemFactory factory = new DiskFileItemFactory();  //生成多个上传的对象
        String upLoadFile = "";

        //临时文件路径
        String origPath = request.getSession().getServletContext().getRealPath("");
        String tempFilePath = origPath + "\\upLoadTemp";
        File f = new File(tempFilePath);  //临时文件
        if (!f.exists()) {
            f.mkdirs();
        }
        factory.setRepository(f);//设置缓存路径
        ServletFileUpload fileUpload = new ServletFileUpload(factory);//封装传递的对象，返回list集合
        fileUpload.setHeaderEncoding("utf-8");
        try {
            List<FileItem> fileItems = fileUpload.parseRequest(request);//解析request成FileItem，存放在List中
            for (FileItem item : fileItems) {
                String fielName;
                String fileName;
                if (item.isFormField()) {//返回true是文本控件   返回false是文件类型
                    //获得字段值
                    fielName = item.getFieldName();
                    //获得值
                    String value = item.getString("utf-8");
                    System.out.println("字段名称：" + fielName + "---- 字段值：" + value);
                } else {//文件类型

                    fielName = item.getName();
                    int i = fielName.lastIndexOf(".");
                    String ext = fielName.substring(i + 1, fielName.length());
                    if (!(ext.equals("png") || ext.equals("jpg") || ext.equals("JPEG") || ext.equals("PNG"))) {
                        return "4";
                    }

                    int lastIndex = fielName.lastIndexOf("/");
                    int lastIndex2 = fielName.lastIndexOf("\\");
                    if (lastIndex == -1 && lastIndex2 == -1) {
                        fileName = fielName;
                    } else {
                        if (lastIndex != -1) {
                            fileName = fielName.substring(fielName.lastIndexOf("/"), fielName.length() - 1);//截取文件名
                            fileName = fileName.replace("/", "");
                        } else {
                            fileName = fielName.substring(fielName.lastIndexOf("\\"), fielName.length() - 1);//截取文件名
                            fileName = fileName.replace("\\", "");
                        }
                    }
                    fileName = UUID.randomUUID().toString() + fileName;
                    String fmPath = "/attachment/" + fileName;
                    System.out.println("文件名称：" + fileName);
                    InputStream is = item.getInputStream();
                    byte[] buffer = new byte[1024];
                    int len = -1;
                    upLoadFile = origPath + "shangCheng\\img";
                    File realFiles = new File(upLoadFile);  //正式上传文件
                    if (realFiles.exists()) {
                        System.out.println(upLoadFile + "----已存在");
                    } else {
                        realFiles.mkdirs();
                    }
                    upLoadFile = upLoadFile + "\\" + fileName;
                    System.out.println(upLoadFile);
                    OutputStream out = new FileOutputStream(upLoadFile);
                    while ((len = is.read(buffer)) > 0) {
                        out.write(buffer, 0, len);
                    }
                    out.close();
                    upLoadFile = "img/" + fileName;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return upLoadFile;
    }

    public String uploadCourse(HttpServletRequest request, User usr) {
        String csewareId = "";
        DiskFileItemFactory factory = new DiskFileItemFactory();  //生成多个上传的对象
        String upLoadFile = "";
        String realname = "";
        String result = "-1";

        //临时文件路径
        String origPath = request.getSession().getServletContext().getRealPath("");
        String tempFilePath = origPath + "\\upLoadTemp";
        File f = new File(tempFilePath);  //临时文件
        if (!f.exists()) {
            f.mkdirs();
        }
        factory.setRepository(f);//设置缓存路径
        ServletFileUpload fileUpload = new ServletFileUpload(factory);//封装传递的对象，返回list集合
        fileUpload.setHeaderEncoding("utf-8");
        try {
            List<FileItem> fileItems = fileUpload.parseRequest(request);//解析request成FileItem，存放在List中
            for (FileItem item : fileItems) {
                String fielName;
                String fileName;
                if (item.isFormField()) {//返回true是文本控件   返回false是文件类型
                    //获得字段值
                    fielName = item.getFieldName();
                    //获得值
                    String value = item.getString("utf-8");
                    if (fielName.equals("id")) {
                        csewareId = value;
                    }
                    System.out.println("字段名称：" + fielName + "---- 字段值：" + value);
                } else {//文件类型

                    fielName = item.getName();
                    int i = fielName.lastIndexOf(".");
                    String ext = fielName.substring(i + 1, fielName.length());
                    if (!(ext.equals("pdf") || ext.equals("PDF"))) {
                        return "4";
                    }

                    int lastIndex = fielName.lastIndexOf("/");
                    int lastIndex2 = fielName.lastIndexOf("\\");
                    if (lastIndex == -1 && lastIndex2 == -1) {
                        fileName = fielName;
                    } else {
                        if (lastIndex != -1) {
                            fileName = fielName.substring(fielName.lastIndexOf("/"), fielName.length() - 1);//截取文件名
                            fileName = fileName.replace("/", "");
                        } else {
                            fileName = fielName.substring(fielName.lastIndexOf("\\"), fielName.length() - 1);//截取文件名
                            fileName = fileName.replace("\\", "");
                        }
                    }
                    realname = fileName;
                    fileName = UUID.randomUUID().toString() + fileName;
                    System.out.println("文件名称：" + fileName);
                    InputStream is = item.getInputStream();
                    byte[] buffer = new byte[1024];
                    int len = -1;
                    upLoadFile = origPath + "Courseware\\files";
                    File realFiles = new File(upLoadFile);  //正式上传文件
                    if (realFiles.exists()) {
                        // System.out.println(upLoadFile + "----已存在");
                    } else {
                        realFiles.mkdirs();
                    }
                    upLoadFile = upLoadFile + "\\" + fileName;
                    System.out.println(upLoadFile);
                    OutputStream out = new FileOutputStream(upLoadFile);
                    while ((len = is.read(buffer)) > 0) {
                        out.write(buffer, 0, len);
                    }
                    out.close();
                    upLoadFile = "files/" + fileName;
                }
            }

            String sql = "INSERT INTO csewarefile(id,cSid,csFileName,csFilePath,createtime) value(uuid(),?,?,?,now())";
            int execute = DataService.execute(sql, new String[]{csewareId, realname, upLoadFile});
            result = String.valueOf(execute);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }



    public String picUpload(HttpServletRequest request) throws Exception {
        DiskFileItemFactory factory = new DiskFileItemFactory();  //生成多个上传的对象
        String upLoadFile = "";

        //临时文件路径
        String origPath = request.getSession().getServletContext().getRealPath("");
        String tempFilePath = origPath + "\\upLoadTemp";
        File f = new File(tempFilePath);  //临时文件
        if (!f.exists()) {
            f.mkdirs();
        }
        factory.setRepository(f);//设置缓存路径
        ServletFileUpload fileUpload = new ServletFileUpload(factory);//封装传递的对象，返回list集合
        fileUpload.setHeaderEncoding("utf-8");
        try {
            String fileName;
            String sql = "INSERT INTO sys_ine_product(";
            String val = "value(";
            List<FileItem> fileItems = fileUpload.parseRequest(request);//解析request成FileItem，存放在List中
            for (FileItem item : fileItems) {
                if (item.isFormField()) {
                    String fieldName = item.getFieldName();//字段名称
                    String value = item.getString("utf-8");
                    sql = sql + fieldName + ",";
                    if (fieldName.equals("id")) {
                        val = val + "uuid(),";
                    } else {
                        val = val + "'" + value + "',";
                    }
                } else {//文件类型
                    String fielName = item.getName();
                    sql = sql + item.getFieldName() + ",";
                    int i = fielName.lastIndexOf(".");
                    String ext = fielName.substring(i + 1, fielName.length());
                    if (!(ext.equals("png") || ext.equals("jpg") || ext.equals("JPEG") || ext.equals("PNG"))) {
                        return "4";
                    }
                    fileName = UUID.randomUUID().toString() + "." + ext;
                    InputStream is = item.getInputStream();
                    byte[] buffer = new byte[1024];
                    int len = -1;
                    upLoadFile = origPath + "\\attachment";
                    File realFiles = new File(upLoadFile);  //正式上传文件
                    if (realFiles.exists()) {
                    } else {
                        realFiles.mkdirs();
                    }
                    upLoadFile = upLoadFile + "\\" + fileName;

                    val = val + "'" + "/attachment/" + fileName + "',";
                    OutputStream out = new FileOutputStream(upLoadFile);
                    while ((len = is.read(buffer)) > 0) {
                        out.write(buffer, 0, len);
                    }
                    sql = sql + "createtime) ";
                    val = val + "now())";
                    sql = sql + val;
                    out.close();
                    int execute = DataService.execute(sql, new String[]{});
                    String result = String.valueOf(execute);
                    return result;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return upLoadFile;
    }
}
