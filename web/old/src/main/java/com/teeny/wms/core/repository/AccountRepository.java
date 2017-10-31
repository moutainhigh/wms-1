package com.teeny.wms.core.repository;

import com.teeny.wms.dto.AccountSetDTO;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by bao on 2017/7/25.
 */
@Repository
public interface AccountRepository {

    @Select("SELECT db_name AS databaseName, account_name AS accountSetName FROM pda_account_set")
    List<AccountSetDTO> getAccountSet();
}
