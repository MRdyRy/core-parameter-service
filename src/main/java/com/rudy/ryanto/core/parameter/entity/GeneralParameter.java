package com.rudy.ryanto.core.parameter.entity;

import com.rudy.ryanto.core.parameter.util.GeneralParamConstant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Entity(name = "GENERAL_PARAMETER")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GeneralParameter {

    @Column(name = "KEY",nullable = false)
    private String key;
    @Column(name = "FEATURE_NAME",nullable = false)
    private String featureName;
    @Column(name = "VALUES_",nullable = false)
    private String values;
    @Column(name = "STATUS",nullable = false)
    private GeneralParamConstant.STATUS status;
    @Column(name = "DESCRIPTION")
    private String description;

    @Temporal(TemporalType.DATE)
    @Column(name = "EFECTIVE_DATE")
    private Date efectiveDate;
}
