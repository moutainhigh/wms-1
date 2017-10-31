ALTER TABLE pda_CheckBill ADD s_id INT;
ALTER TABLE pda_RecBill ADD s_id INT;
ALTER TABLE pda_PutOnBill ADD s_id INT;
ALTER TABLE pda_CheckBill ADD remaek VARCHAR(200) NULL ;
ALTER TABLE pda_CheckBill ADD diff_reamrk VARCHAR(200) NULL ;
ALTER TABLE pda_pdBill ADD a_id INT;
ALTER TABLE pda_pdBill ADD sa_id INT;
ALTER TABLE pda_TranBill ADD sa_id INT;
ALTER TABLE pda_TranBill ADD as_outid INT;
ALTER TABLE pda_Products ADD barcode VARCHAR(50);

ALTER TABLE pda_RecBill_D ADD original_id INT;
ALTER TABLE pda_PutOnBill_D ADD original_id INT;
ALTER TABLE pda_TranBill_D ADD original_id INT;
ALTER TABLE pda_pdBill_D ADD original_id INT;

ALTER TABLE pda_PutOnBill ADD sa_id INT;  -- 库区id

ALTER TABLE pda_CheckBill ADD recipient_id INT;  -- 增加接收人id

-- 2017.9.6
ALTER TABLE  pda_PutOnBill_D ADD wctime DATETIME ;-- 完成时间
ALTER TABLE pda_CheckBill_B  ADD wctime DATETIME  ;-- 完成时间
ALTER TABLE pda_pdBill_D  ADD wctime DATETIME; -- 完成时间
ALTER TABLE pda_RecBill_D  ADD wctime DATETIME; -- 完成时间
ALTER TABLE pda_TranBill_D  ADD wctime DATETIME; -- 完成时间
ALTER TABLE pda_kcpdBill_D  ADD wctime DATETIME; -- 完成时间
ALTER TABLE pda_mdRecBill_D  ADD wctime DATETIME; -- 完成时间

ALTER TABLE  pda_PutOnBill_D ADD loginid int;  -- 操作人id
ALTER TABLE pda_CheckBill_B  ADD loginid int;  -- 操作人id
ALTER TABLE pda_pdBill_D  ADD loginid int;     -- 操作人id
ALTER TABLE pda_RecBill_D  ADD loginid int;    -- 操作人id
ALTER TABLE pda_TranBill_D  ADD loginid int;   -- 操作人id
ALTER TABLE pda_kcpdBill_D  ADD loginid int;   -- 操作人id
ALTER TABLE pda_mdRecBill_D  ADD loginid int;  -- 操作人id


ALTER TABLE  pda_RecBill_D ADD WholeQty  NUMERIC(18,4);   -- 整货数量
ALTER TABLE  pda_RecBill_D ADD retailQty  NUMERIC(18,4);  -- 零货数量



# 2017.9.7
ALTER TABLE pda_RecBill_D  ADD rownumber int;  -- 添加序号

# 导入数据初盘状态应该为1、复盘为2
UPDATE pda_pdBill_D SET pda_pdBill_D.billstates=1