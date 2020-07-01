package com.jw.cloud.entry;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "`ac_menu`")
public class AcMenu {
    /**
     * id
     */
    @Id
    @Column(name = "`id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 父类ID
     */
    @Column(name = "`parent_id`")
    private Long parentId;

    /**
     * 所有父类编号
     */
    @Column(name = "`parent_ids`")
    private String parentIds;

    /**
     * 菜单名称
     */
    @Column(name = "`name`")
    private String name;

    /**
     * href链接名称
     */
    @Column(name = "`href`")
    private String href;

    /**
     * 排序
     */
    @Column(name = "`sort`")
    private Integer sort;

    /**
     * 是否展示
     */
    @Column(name = "`is_show`")
    private String isShow;
    /**
     * 权限
     */
    @Column(name = "`permission`")
    private String permission;

}