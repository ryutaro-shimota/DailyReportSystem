package com.techacademy.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;


import lombok.Data;

@Data
@Entity
@Table(name = "report")
public class Report {

    /** ID。主キー。自動生成 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** 日報の日付。null不許可 */
    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate reportDate;

    /** タイトル。サイズ:255。null不許可 */
    @Column(length = 255, nullable = false)
    private String title;

    /** 内容。null不許可 */
    @Column(nullable = false)
    @Type(type="text")
    private String content;

    /** 従業員テーブルのID。外部キー。null不許可 */
    @ManyToOne
    @JoinColumn(name="employee_id", referencedColumnName="id", nullable = false)
    private Employee employee;

    /** 登録日時。null不許可 */
    @Column(nullable = false)
    private LocalDateTime createdAt;

    /** 更新日時。null不許可 */
    @Column(nullable = false)
    private LocalDateTime updatedAt;

}

