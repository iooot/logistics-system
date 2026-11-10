package com.web.upLoadAction;

import common.usingtool.UploadFile;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class Upload {


    @PostMapping("/fileupload")
    public String fileupload(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        UploadFile uploadFile = new UploadFile();
        String upload = uploadFile.getUploadFilePath(request);
        return upload;
    }
}