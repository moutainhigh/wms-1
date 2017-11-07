package com.teeny.wms.web.repository;

import com.teeny.wms.app.model.StringMapEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface SystemMapper {

    @Select("SELECT db_name AS key, account_name AS value FROM pda_account_set")
    List<StringMapEntity> getAccountSets();
}
