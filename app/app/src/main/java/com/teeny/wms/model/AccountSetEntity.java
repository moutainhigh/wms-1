package com.teeny.wms.model;

import com.google.gson.annotations.SerializedName;

/**
 * Class description:
 *
 * @author zp
 * @version 1.0
 * @see AccountSetEntity
 * @since 2017/8/14
 */

public class AccountSetEntity {

    @SerializedName(value = "databaseName", alternate = {"key"})
    private String databaseName;
    @SerializedName(value = "accountSetName", alternate = {"value"})
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
