package com.rudy.ryanto.core.parameter.repository;

import com.rudy.ryanto.core.parameter.entity.GeneralParameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GeneralParamRepository extends JpaRepository<GeneralParameter,String> {

    List<GeneralParameter> findByKeyAndFeatureName(String key, String feature);
}
