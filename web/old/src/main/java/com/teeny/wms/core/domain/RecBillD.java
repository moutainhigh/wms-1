package com.teeny.wms.core.domain;

import java.io.Serializable;

/**
 * Class description:
 *
 * @author zp
 * @version 1.0
 * @see RecBillD
 * @since 2017/9/4
 */
public class RecBillD implements Serializable {
    public int bill_id;
    public int p_id;
    public String MakeDate;
    public String Validdate;
    public String Batchno;
    public float Yqty;
    public float EligibleQty;
    public float TaxPrice;
    public float TaxTotal;
    public float CostPrice;
    public float CostTotal;
    public int S_id;
    public int Location_id;
    public int Supplier_id;
    public int DealStates;
    public int pdastates;
    public int original_id;
    public int rownumber;

    public float retailQty;
    public float WholeQty;

    public RecBillD() {
    }

    public RecBillD(RecBillD d) {
        this.bill_id = d.bill_id;
        this.p_id = d.p_id;
        this.MakeDate = d.MakeDate;
        this.Validdate = d.Validdate;
        this.Batchno = d.Batchno;
        this.Yqty = d.Yqty;
        this.EligibleQty = d.EligibleQty;
        this.TaxPrice = d.TaxPrice;
        this.TaxTotal = d.TaxTotal;
        this.CostPrice = d.CostPrice;
        this.CostTotal = d.CostTotal;
        this.S_id = d.S_id;
        this.Location_id = d.Location_id;
        this.Supplier_id = d.Supplier_id;
        this.DealStates = d.DealStates;
        this.pdastates = d.pdastates;
        this.original_id = d.original_id;
        this.rownumber = d.rownumber;
        this.retailQty = d.retailQty;
        this.WholeQty = d.WholeQty;
    }
}
