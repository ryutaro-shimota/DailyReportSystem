package com.techacademy.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "authentication")

public class Authentication {
    /** 権限の列挙型*/
    public static enum Role {
        管理者, 一般
    }

    /** 社員番号。主キー。*/
    @Id
    private String code;

    /** パスワード。255桁。null不許可 */
    @Column(length = 255, nullable = false)
    private String password;

    /** 権限。10桁。null不許可。列挙型（文字列） */
    @Column(length = 10, nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    /** 従業員テーブルのID。外部キー。null不許可 */
    @OneToOne
    @JoinColumn(name="employee_id", referencedColumnName="id", nullable = false)
    private Employee employee;

}
