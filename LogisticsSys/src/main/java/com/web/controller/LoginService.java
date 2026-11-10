package com.web.controller;

import javax.servlet.http.HttpSession;

public interface LoginService {

    Boolean authentication(HttpSession session, String uname, String pwd);
}
