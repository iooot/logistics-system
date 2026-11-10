package com.web.authorityAction.Controller;

public interface AuthorityService {
    String roleList(String content, String page);

    String addRole(String id, String val, String vkey, String dicName, String did, String bem);

    String delRole(String id);
}
