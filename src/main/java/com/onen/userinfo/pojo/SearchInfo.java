package com.onen.userinfo.pojo;

import lombok.Data;

@Data
public class SearchInfo {
    private String name;
    private String address;
    private String email;
    private int cpage;
    private int psize;
    private int skipCount;
    private int isadmin;
}
