package com.web.functionModule.forumAction.Controller;

import com.domain.User;

import javax.servlet.http.HttpServletRequest;

public interface ScInfoService {
    String bbsList(String content, String page, User usr);

    String setTop(String id);

    String noTop(String id);

}
