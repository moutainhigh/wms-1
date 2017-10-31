package com.teeny.wms.core.domain;

/**
 * Created by lilei on 2017/7/15.
 * 配送抵达
 */
public class SendBill {

    /*配送单id*/
    private int billId;
    /*业务单据id*/
    private int gspBillId;
    /*销售订单单号*/
    private String billNumber;
    /*物流单号*/
    private String sendNumber;
    /*优先级*/
    private String firstStates;
    /*客户id,=pda_clients.c_id*/
    private int cid;
    /*业务员id,=pda_employees.c_id */
    private int eid;
    /*联系人*/
    private String contactPersonal;
    /*联系电话*/
    private String phoneNumber;
    /*送货人  0531 更改*/
    private String sendemp;
    /*送货人电话*/
    private String sendempPhone;
    /*收货人*/
    private String recemp;
    /*收货人电话*/
    private String recempPhone;
    /*地址  0531	 */
    private String address;
    /*应收款*/
    private float arTotal;
    /*单据备注*/
    private String note;
    /*整货数量*/
    private float wholeQty;
    /*拼臬数量*/
    private float partQty;
    /*打包数量*/
    private float packQty;
    /*温度*/
    private float wddetail;
    /*回执*/
    private String returnDetail;
    /*业务员交接完成的时间,配送单审核时间*/
    private String pdaOutTime;
    /*是否含特殊药品 0不含 1包含 */
    private int isSpecial;
    /*是否含冷链药品 0不含 1包含*/
    private int isCold;
    /*是否含处方药 0不含 1包含*/
    private  int isRX;
    /*交换状态 0提供 1pda已读取 2pda已回写 3药易通已回写*/
    private int pdastates;
    /*提供时间*/
    private String pdaInTime;
    /*读取时间*/
    private String pdaReTime;
    /*回写时间*/
    private String pdaWrTime;


    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public int getGspBillId() {
        return gspBillId;
    }

    public void setGspBillId(int gspBillId) {
        this.gspBillId = gspBillId;
    }

    public String getBillNumber() {
        return billNumber;
    }

    public void setBillNumber(String billNumber) {
        this.billNumber = billNumber;
    }

    public String getSendNumber() {
        return sendNumber;
    }

    public void setSendNumber(String sendNumber) {
        this.sendNumber = sendNumber;
    }

    public String getFirstStates() {
        return firstStates;
    }

    public void setFirstStates(String firstStates) {
        this.firstStates = firstStates;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public String getContactPersonal() {
        return contactPersonal;
    }

    public void setContactPersonal(String contactPersonal) {
        this.contactPersonal = contactPersonal;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSendemp() {
        return sendemp;
    }

    public void setSendemp(String sendemp) {
        this.sendemp = sendemp;
    }

    public String getSendempPhone() {
        return sendempPhone;
    }

    public void setSendempPhone(String sendempPhone) {
        this.sendempPhone = sendempPhone;
    }

    public String getRecemp() {
        return recemp;
    }

    public void setRecemp(String recemp) {
        this.recemp = recemp;
    }

    public String getRecempPhone() {
        return recempPhone;
    }

    public void setRecempPhone(String recempPhone) {
        this.recempPhone = recempPhone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public float getArTotal() {
        return arTotal;
    }

    public void setArTotal(float arTotal) {
        this.arTotal = arTotal;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public float getWholeQty() {
        return wholeQty;
    }

    public void setWholeQty(float wholeQty) {
        this.wholeQty = wholeQty;
    }

    public float getPartQty() {
        return partQty;
    }

    public void setPartQty(float partQty) {
        this.partQty = partQty;
    }

    public float getPackQty() {
        return packQty;
    }

    public void setPackQty(float packQty) {
        this.packQty = packQty;
    }

    public float getWddetail() {
        return wddetail;
    }

    public void setWddetail(float wddetail) {
        this.wddetail = wddetail;
    }

    public String getReturnDetail() {
        return returnDetail;
    }

    public void setReturnDetail(String returnDetail) {
        this.returnDetail = returnDetail;
    }

    public String getPdaOutTime() {
        return pdaOutTime;
    }

    public void setPdaOutTime(String pdaOutTime) {
        this.pdaOutTime = pdaOutTime;
    }

    public int getIsSpecial() {
        return isSpecial;
    }

    public void setIsSpecial(int isSpecial) {
        this.isSpecial = isSpecial;
    }

    public int getIsCold() {
        return isCold;
    }

    public void setIsCold(int isCold) {
        this.isCold = isCold;
    }

    public int getIsRX() {
        return isRX;
    }

    public void setIsRX(int isRX) {
        this.isRX = isRX;
    }

    public int getPdastates() {
        return pdastates;
    }

    public void setPdastates(int pdastates) {
        this.pdastates = pdastates;
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
