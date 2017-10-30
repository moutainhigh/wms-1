package com.teeny.wms.model;

/**
 * Class description:
 *
 * @author zp
 * @version 1.0
 * @see AccountSetEntity
 * @since 2017/8/14
 */

public class AccountSetEntity {

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

    @Override
    public String toString() {
        return accountSetName;
    }
}
