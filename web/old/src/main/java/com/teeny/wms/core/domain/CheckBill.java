package com.teeny.wms.core.domain;

/**
 * Created by lilei on 2017/7/15.
 * 销售订单,生成复核单时写入数据
 */
public class CheckBill {

    /*销售订单id*/
    private int billId;
    /*销售订单单号*/
    private String billNumber;
    /*优先级*/
    private String FirstStates;
    /*客户id,=pda_clients.c_id    */
    private int cid;
    /*暂存区--拣货单上的	*/
    private String tempStore;
    /*PDA回写状态,回写至药易通销售订单上*/
    private int billStates;
    /*交换状态 0提供 1pda已读取 2pda已回写*/
    private int pdaStates;
    /*提供时间*/
    private  String pdaInTime;
    /*读取时间*/
    private  String pdaReTime;
    /*回写时间*/
    private  String pdaWrTime;

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public String getBillNumber() {
        return billNumber;
    }

    public void setBillNumber(String billNumber) {
        this.billNumber = billNumber;
    }

    public String getFirstStates() {
        return FirstStates;
    }

    public void setFirstStates(String firstStates) {
        FirstStates = firstStates;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getTempStore() {
        return tempStore;
    }

    public void setTempStore(String tempStore) {
        this.tempStore = tempStore;
    }

    public int getBillStates() {
        return billStates;
    }

    public void setBillStates(int billStates) {
        this.billStates = billStates;
    }

    public int getPdaStates() {
        return pdaStates;
    }

    public void setPdaStates(int pdaStates) {
        this.pdaStates = pdaStates;
    }

    public String getPdaInTime() {
        return pdaInTime;
    }

    public void setPdaInTime(String pdaInTime) {
        this.pdaInTime = pdaInTime;
    }

    public String getPdaReTime() {
        return pdaReTime;
    }

    public void setPdaReTime(String pdaReTime) {
        this.pdaReTime = pdaReTime;
    }

    public String getPdaWrTime() {
        return pdaWrTime;
    }

    public void setPdaWrTime(String pdaWrTime) {
        this.pdaWrTime = pdaWrTime;
    }
}
