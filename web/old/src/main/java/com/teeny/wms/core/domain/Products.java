package com.teeny.wms.core.domain;

/**
 * Created by lilei on 2017/7/15.
 *
 */
public class Products {

    /*商品id*/
    private int pid;
    /*拼音*/
    private String pinyin;
    /*商品名称*/
    private String name;
    /*通用名*/
    private String alias;
    /*规格*/
    private String standard;
    /*剂型*/
    private String medtype;
    /*批准文号*/
    private String permitCode;
    /*批准文号有效期*/
    private String perCodeValid;
    /*基本单位*/
    private String unit1Name;
    /*整货件包装数 0531*/
    private float wholeRate;
    /*整货单位*/
    private String wholeUnitName;
    /*编码*/
    private String serialNumber;
    /*生产许可证*/
    private String bulidNo;
    /*注册证号*/
    private String registerNo;
    /*注册证号有效期*/
    private String registerValid;
    /*GMP*/
    private String GMPNo;
    /*GMP有效期*/
    private String GMPvaliddate;
    /*生产厂家*/
    private String factory;
    /*温度条件*/
    private String storageCon;
    /*有效期月*/
    private int validMonth;
    /*有效期天*/
    private int validDay;
    /*产地*/
    private String makeArea;
    /*装箱规格*/
    private String packStd;
    /*默认货位,=pda_location.l_id*/
    private int locationId;
    /*包装规格*/
    private String pack;
    /*默认整货货位,=pda_location.l_id*/
    private int wholeLoc;
    /*默认零货货位,=pda_location.l_id*/
    private int singleLoc;
    /*供应商,=pda_clients.c_id*/
    private int supplierId;
    /*状态，默认0 ，读取后 1， 读取出错 -1  0531*/
    private int ZT;

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public String getMedtype() {
        return medtype;
    }

    public void setMedtype(String medtype) {
        this.medtype = medtype;
    }

    public String getPermitCode() {
        return permitCode;
    }

    public void setPermitCode(String permitCode) {
        this.permitCode = permitCode;
    }

    public String getPerCodeValid() {
        return perCodeValid;
    }

    public void setPerCodeValid(String perCodeValid) {
        this.perCodeValid = perCodeValid;
    }

    public String getUnit1Name() {
        return unit1Name;
    }

    public void setUnit1Name(String unit1Name) {
        this.unit1Name = unit1Name;
    }

    public float getWholeRate() {
        return wholeRate;
    }

    public void setWholeRate(float wholeRate) {
        this.wholeRate = wholeRate;
    }

    public String getWholeUnitName() {
        return wholeUnitName;
    }

    public void setWholeUnitName(String wholeUnitName) {
        this.wholeUnitName = wholeUnitName;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getBulidNo() {
        return bulidNo;
    }

    public void setBulidNo(String bulidNo) {
        this.bulidNo = bulidNo;
    }

    public String getRegisterNo() {
        return registerNo;
    }

    public void setRegisterNo(String registerNo) {
        this.registerNo = registerNo;
    }

    public String getRegisterValid() {
        return registerValid;
    }

    public void setRegisterValid(String registerValid) {
        this.registerValid = registerValid;
    }

    public String getGMPNo() {
        return GMPNo;
    }

    public void setGMPNo(String GMPNo) {
        this.GMPNo = GMPNo;
    }

    public String getGMPvaliddate() {
        return GMPvaliddate;
    }

    public void setGMPvaliddate(String GMPvaliddate) {
        this.GMPvaliddate = GMPvaliddate;
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

    public String getStorageCon() {
        return storageCon;
    }

    public void setStorageCon(String storageCon) {
        this.storageCon = storageCon;
    }

    public int getValidMonth() {
        return validMonth;
    }

    public void setValidMonth(int validMonth) {
        this.validMonth = validMonth;
    }

    public int getValidDay() {
        return validDay;
    }

    public void setValidDay(int validDay) {
        this.validDay = validDay;
    }

    public String getMakeArea() {
        return makeArea;
    }

    public void setMakeArea(String makeArea) {
        this.makeArea = makeArea;
    }

    public String getPackStd() {
        return packStd;
    }

    public void setPackStd(String packStd) {
        this.packStd = packStd;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public String getPack() {
        return pack;
    }

    public void setPack(String pack) {
        this.pack = pack;
    }

    public int getWholeLoc() {
        return wholeLoc;
    }

    public void setWholeLoc(int wholeLoc) {
        this.wholeLoc = wholeLoc;
    }

    public int getSingleLoc() {
        return singleLoc;
    }

    public void setSingleLoc(int singleLoc) {
        this.singleLoc = singleLoc;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public int getZT() {
        return ZT;
    }

    public void setZT(int ZT) {
        this.ZT = ZT;
    }
}
