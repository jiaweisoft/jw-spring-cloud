package com.jw.cloud.entry;

import lombok.Data;

@Data
public class AcMenu {
    private Long id;
    private Long parentId;
    private String parentIds;
    private String name;
    private String href;
    private Integer sort;
    private String isShow;
    private String permission;
}