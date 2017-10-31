package com.teeny.wms.dto;

import java.io.Serializable;

/**
 * Class description:
 *
 * @author zp
 * @version 1.0
 * @see AccountSetDTO
 * @since 2017/8/14
 */
public class AccountSetDTO implements Serializable {

    private String databaseName;
    private String accountSetName;

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public String getAccountSetName() {
        return accountSetName;
    }

    public void setAccountSetName(String accountSetName) {
        this.accountSetName = accountSetName;
    }
}
