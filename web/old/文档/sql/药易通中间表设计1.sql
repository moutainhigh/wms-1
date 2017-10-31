--目标是9系，8系，器械PDA程序通用，但各版本之间字段可能不通用，故建中间表
IF not exists (select 1 from dbo.sysobjects where id = object_id(N'[dbo].[pda_timestamp]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
begin
	CREATE TABLE [dbo].pda_timestamp (
       p_timestamp BIGINT NOT NULL,--商品资料上次取数时最大的timestamp
       e_timestamp BIGINT NOT NULL,--职员
       s_timestamp BIGINT NOT NULL,--仓库
       sa_timestamp BIGINT NOT NULL,--库区
       sc_timestamp BIGINT NOT NULL,--区域
       l_timestamp BIGINT NOT NULL,--库位
       c_timestamp BIGINT NOT NULL --往来单位
	) ON [PRIMARY]
end
GO

IF NOT EXISTS(SELECT 1 FROM pda_timestamp)
BEGIN
  INSERT INTO pda_timestamp
    (p_timestamp,e_timestamp,s_timestamp,sa_timestamp,sc_timestamp,l_timestamp,c_timestamp)
  SELECT 0,0,0,0,0,0,0
END
--商品资料
-- drop table pda_Products
IF not exists (select 1 from dbo.sysobjects where id = object_id(N'[dbo].[pda_Products]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
begin
	CREATE TABLE [dbo].pda_Products (
	    p_id INT NOT NULL DEFAULT 0,--商品id
		serial_number VARCHAR(26) NOT NULL DEFAULT '',--编码
		pinyin VARCHAR(80) NOT NULL DEFAULT '',--拼音
		name VARCHAR(80) NOT NULL DEFAULT '',--商品名称
		alias VARCHAR(80) NOT NULL DEFAULT '',--通用名
		specification VARCHAR(100) NOT NULL DEFAULT '',--规格
		medtype VARCHAR(50) NOT NULL DEFAULT '',--剂型
		permitcode VARCHAR(50) NOT NULL DEFAULT '',--批准文号
		PerCodevalid DATETIME NOT NULL DEFAULT 0,--批准文号有效期
		unit1Name VARCHAR(10) NOT NULL DEFAULT '',--基本单位
		WholeUnitName VARCHAR(10) NOT NULL DEFAULT '',--整货单位
		WholeRate numeric(18, 4)  NOT NULL DEFAULT '',--整货件包装数 0531
		BulidNo VARCHAR(500) NOT NULL DEFAULT '',--生产许可证
		RegisterNo VARCHAR(500) NOT NULL DEFAULT '',--注册证号
		Registervalid DATETIME NOT NULL DEFAULT 0,--注册证号有效期
		GMPNo VARCHAR(120) NOT NULL DEFAULT '',--GMP
		GMPvaliddate DATETIME NOT NULL DEFAULT 0,--GMP有效期
		Factory VARCHAR(80) NOT NULL DEFAULT '',--生产厂家
		StorageCon VARCHAR(60) NOT NULL DEFAULT '',--温度条件
		validmonth SMALLINT NOT NULL DEFAULT 0,--有效期月
        validday SMALLINT NOT NULL DEFAULT 0,--有效期天
		makearea VARCHAR(60) NOT NULL DEFAULT '',--产地
		PackStd VARCHAR(60) NOT NULL DEFAULT '',--装箱规格
		pack VARCHAR(100) NOT NULL DEFAULT '',--包装规格
		locationid INT NOT NULL DEFAULT 0,--默认货位,=pda_location.l_id
		WholeLoc INT NOT NULL DEFAULT 0,--默认整货货位,=pda_location.l_id
		SingleLoc INT NOT NULL DEFAULT 0,--默认零货货位,=pda_location.l_id
		Supplier_id INT NOT NULL DEFAULT 0,--供应商,=pda_clients.c_id
		ZT int not null DEFAULT 0,   --状态，默认0 ，读取后 1， 读取出错 -1  0531
	) ON [PRIMARY]
end
GO



--职员资料
-- drop table pda_employees
IF not exists (select 1 from dbo.sysobjects where id = object_id(N'[dbo].[pda_employees]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
begin
	CREATE TABLE [dbo].pda_employees (
	    e_id INT NOT NULL DEFAULT 0,--职员id
		serial_number VARCHAR(26) NOT NULL DEFAULT '',--编码
		password VARCHAR(30) NOT NULL DEFAULT '',--密码
		pinyin VARCHAR(80) NOT NULL DEFAULT '',--拼音
		name VARCHAR(80) NOT NULL DEFAULT '',--名称
		alias VARCHAR(30) NOT NULL DEFAULT '',--简名
		phone VARCHAR(60) NOT NULL DEFAULT '',--联系电话
		address VARCHAR(66) NOT NULL DEFAULT '',--联系地址
		ZT int not null DEFAULT 0,   --状态，默认0 ，读取后 1， 读取出错 -1  0531
	) ON [PRIMARY]
end
GO


--仓库资料
-- drop table pda_storages
IF not exists (select 1 from dbo.sysobjects where id = object_id(N'[dbo].[pda_storages]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
begin
	CREATE TABLE [dbo].pda_storages (
	    s_id INT NOT NULL DEFAULT 0,--仓库id
		serial_number VARCHAR(26) NOT NULL DEFAULT '',--编码
		pinyin VARCHAR(80) NOT NULL DEFAULT '',--拼音
		name VARCHAR(80) NOT NULL DEFAULT '',--名称
		alias VARCHAR(30) NOT NULL DEFAULT '',--简名
		WholeFlag INT NOT NULL DEFAULT 0,--整零库类型,0/-1未选择1整货库2零货库3零售拆零库
		storeCondition INT NOT NULL DEFAULT 0,--温度条件,0常温1阴冷2冷链
		qualityFlag INT NOT NULL DEFAULT 0,--质量类型,0合格库1不合格库2待退厂库
		ZT int not null DEFAULT 0,   --状态，默认0 ，读取后 1， 读取出错 -1  0531
	) ON [PRIMARY]
end
GO


--库区资料
-- drop table pda_stockArea
IF not exists (select 1 from dbo.sysobjects where id = object_id(N'[dbo].[pda_stockArea]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
begin
	CREATE TABLE [dbo].pda_stockArea (
	    sa_id INT NOT NULL DEFAULT 0,--库区id
	    s_id INT NOT NULL DEFAULT 0,--仓库id
		serial_number VARCHAR(60) NOT NULL DEFAULT '',--编码
		WholeFlag INT NOT NULL DEFAULT 0,--整零库类型,0/-1未选择1整货库2零货库3零售拆零库  0531
		storeCondition INT NOT NULL DEFAULT 0,--温度条件,0常温1阴冷2冷链	  0531
		qualityFlag INT NOT NULL DEFAULT 0,--质量类型,0合格库1不合格库2待退厂库  0531
		name VARCHAR(60) NOT NULL DEFAULT '',--名称
		ZT int not null DEFAULT 0,   --状态，默认0 ，读取后 1， 读取出错 -1  0531
	) ON [PRIMARY]
end
GO


--区域资料
-- drop table pda_Area
IF not exists (select 1 from dbo.sysobjects where id = object_id(N'[dbo].[pda_Area]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
begin
	CREATE TABLE [dbo].pda_Area (
	    sc_id INT NOT NULL DEFAULT 0,--区域id
	    s_id INT NOT NULL DEFAULT 0,--仓库id
		serial_number VARCHAR(60) NOT NULL DEFAULT '',--编码
		name VARCHAR(60) NOT NULL DEFAULT '',--名称
		ZT int not null DEFAULT 0,   --状态，默认0 ，读取后 1， 读取出错 -1  0531
	) ON [PRIMARY]
end
GO


--货位资料
-- drop table pda_location
IF not exists (select 1 from dbo.sysobjects where id = object_id(N'[dbo].[pda_location]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
begin
	CREATE TABLE [dbo].pda_location (
	    l_id INT NOT NULL DEFAULT 0,--货位id
	    s_id INT NOT NULL DEFAULT 0,--仓库id
		loc_code VARCHAR(30) NOT NULL DEFAULT '',--编码
		loc_name VARCHAR(30) NOT NULL DEFAULT '',--名称
	    sa_id INT NOT NULL DEFAULT 0,--库区id,=pda_stockArea.sa_id
	    sc_id INT NOT NULL DEFAULT 0,--区域id,=pda_Area.sc_id
	    ShelfName VARCHAR(50) NOT NULL DEFAULT '',--货架
	    roadwayCode VARCHAR(400) NOT NULL DEFAULT '',--过道
	    ZT int not null DEFAULT 0,   --状态，默认0 ，读取后 1， 读取出错 -1  0531
	) ON [PRIMARY]
end
GO


--往来单位资料
-- drop table pda_clients
IF not exists (select 1 from dbo.sysobjects where id = object_id(N'[dbo].[pda_clients]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
begin
	CREATE TABLE [dbo].pda_clients (
	    c_id INT NOT NULL DEFAULT 0,--往来单位id
		serial_number VARCHAR(26) NOT NULL DEFAULT '',--编码
		pinyin VARCHAR(80) NOT NULL DEFAULT '',--拼音
		name VARCHAR(80) NOT NULL DEFAULT '',--名称
		alias VARCHAR(30) NOT NULL DEFAULT '',--简名
		csflag CHAR(1) NOT NULL DEFAULT '',--单位类型 0客户1供应商2两者皆是
		contact_personal VARCHAR(20) NOT NULL DEFAULT '',--联系人
		phone_number VARCHAR(100) NOT NULL DEFAULT '',--联系电话
		address VARCHAR(128) NOT NULL DEFAULT '',--联系地址
		RoadName VARCHAR(30) NOT NULL DEFAULT '',--路线
		ZT int not null DEFAULT 0,   --状态，默认0 ，读取后 1， 读取出错 -1  0531
	) ON [PRIMARY]
end
GO

--上架确认单
-- drop table pda_PutOnBill
IF not exists (select 1 from dbo.sysobjects where id = object_id(N'[dbo].[pda_PutOnBill]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
begin
	CREATE TABLE [dbo].pda_PutOnBill(
	    billid INT NOT NULL DEFAULT 0,--上架确认单id
	    billnumber VARCHAR(30) NOT NULL DEFAULT '',--销售订单单号
	    e_id INT NOT NULL DEFAULT 0,--经手人id,=pda_employees.e_id
		billstates INT NOT NULL DEFAULT 10,--上架确认单状态 10未处理 13已审核
		pdastates INT NOT NULL DEFAULT 0,--交换状态 0提供 1pda已读取 2pda已回写
		pdaInTime DATETIME NOT NULL DEFAULT 0,--提供时间
		pdaReTime DATETIME NOT NULL DEFAULT 0,--读取时间
		pdaWrTime DATETIME NOT NULL DEFAULT 0,--回写时间
	) ON [PRIMARY]
end
GO


--上架确认单,pda回写时需要新增数据，不能修改提供的数据，同一批次上多个仓库或货位时，pda复制数据后只能修改仓库id及货位id\上架数量
-- drop table pda_PutOnBill_D
IF not exists (select 1 from dbo.sysobjects where id = object_id(N'[dbo].[pda_PutOnBill_D]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
begin
	CREATE TABLE [dbo].pda_PutOnBill_D(
	    smb_id INT NOT NULL DEFAULT 0,--上架确认单明细表id
	    bill_id INT NOT NULL DEFAULT 0,--上架确认单id,=pda_PutOnBill.billid
	    p_id INT NOT NULL DEFAULT 0,--商品id,=pda_Products.p_id
		MakeDate	DATETIME NOT NULL DEFAULT 0,--生产日期
		Validdate	DATETIME NOT NULL DEFAULT 0,--效期
		Batchno	VARCHAR(20) NOT NULL DEFAULT '',--批号
		EligibleQty	NUMERIC(18,4) NOT NULL DEFAULT 0,--上架数量
		TaxPrice	NUMERIC(18,4) NOT NULL DEFAULT 0,--含税单价
		TaxTotal	NUMERIC(18,4) NOT NULL DEFAULT 0,--含税金额
		CostPrice	NUMERIC(18,4) NOT NULL DEFAULT 0,--成本单价
		CostTotal	NUMERIC(18,4) NOT NULL DEFAULT 0,--成本金额
		S_id	INT NOT NULL DEFAULT 0,--仓库id,=pda_storages.s_id
		Location_id	INT NOT NULL DEFAULT 0,--货位id,=pda_location.l_id
		Supplier_id	INT NOT NULL DEFAULT 0,--供应商id,=pda_PutOnBill.c_id
		InstoreTime	DATETIME NOT NULL DEFAULT 0,--入库时间
		LineSort  int NOT NULL DEFAULT 0, --原始行号，复制行时，需要保留该数值不变，用于关联ERP原始明细 0531,复制的行smb_id=0
		DealStates  int NOT NULL DEFAULT 0,--处理状态，0 未处理, 1 PDA已经处理（上架） 0531
		pdastates INT NOT NULL DEFAULT 0,--状态 0提供数据 1pda回写数据
) ON [PRIMARY]
end
GO



--销售订单,生成复核单时写入数据
-- drop table pda_CheckBill
IF not exists (select 1 from dbo.sysobjects where id = object_id(N'[dbo].[pda_CheckBill]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
begin
	CREATE TABLE [dbo].pda_CheckBill(
	    billid INT NOT NULL DEFAULT 0,--销售订单id
	    billnumber VARCHAR(30) NOT NULL DEFAULT '',--销售订单单号
	    FirstStates VARCHAR(10) NOT NULL DEFAULT '',--优先级
	    c_id INT NOT NULL DEFAULT 0,--客户id,=pda_clients.c_id
	    TempStore VARCHAR(30) NOT NULL DEFAULT '',--暂存区--拣货单上的
		billstates INT NOT NULL DEFAULT 10,--PDA回写状态,回写至药易通销售订单上
		pdastates INT NOT NULL DEFAULT 0,--交换状态 0提供 1pda已读取 2pda已回写
		pdaInTime DATETIME NOT NULL DEFAULT 0,--提供时间
		pdaReTime DATETIME NOT NULL DEFAULT 0,--读取时间
		pdaWrTime DATETIME NOT NULL DEFAULT 0--回写时间
	) ON [PRIMARY]
end
GO

--仅用于查看商品明细，如果不参看商品明细，作废此表
--复核单,pda回写时只能修改复核数量、复核结论、差异原因
-- drop table pda_CheckBill_D
IF not exists (select 1 from dbo.sysobjects where id = object_id(N'[dbo].[pda_CheckBill_D]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
begin
	CREATE TABLE [dbo].pda_CheckBill_D(
	    smb_id INT NOT NULL DEFAULT 0,--复核单明细表id
	    bill_id INT NOT NULL DEFAULT 0,--复核单id,=pda_CheckBill.billid
	    p_id INT NOT NULL DEFAULT 0,--商品id,=pda_Products.p_id
		MakeDate	DATETIME NOT NULL DEFAULT 0,--生产日期
		Validdate	DATETIME NOT NULL DEFAULT 0,--效期
		Batchno	VARCHAR(20) NOT NULL DEFAULT '',--批号
		EligibleQty	NUMERIC(18,4) NOT NULL DEFAULT 0,--订单数量
		PickQty	NUMERIC(18,4) NOT NULL DEFAULT 0,--拣货数量
		CheckQty	NUMERIC(18,4) NOT NULL DEFAULT 0,--复核数量
		CheckState VARCHAR(200) NOT NULL DEFAULT '',--复核结论
		CheckReason VARCHAR(200) NOT NULL DEFAULT '',--差异原因
		TaxPrice	NUMERIC(18,4) NOT NULL DEFAULT 0,--含税单价
		TaxTotal	NUMERIC(18,4) NOT NULL DEFAULT 0,--含税金额
		CostPrice	NUMERIC(18,4) NOT NULL DEFAULT 0,--成本单价
		CostTotal	NUMERIC(18,4) NOT NULL DEFAULT 0,--成本金额
		S_id	INT NOT NULL DEFAULT 0,--仓库id,=pda_storages.s_id
		Location_id	INT NOT NULL DEFAULT 0,--货位id,=pda_location.l_id
		Supplier_id	INT NOT NULL DEFAULT 0,--供应商id,=pda_PutOnBill.c_id
		InstoreTime	DATETIME NOT NULL DEFAULT 0,--入库时间
		pdastates INT NOT NULL DEFAULT 0,--交换状态 0提供 1pda已回写
	) ON [PRIMARY]
end
GO

--复核单条码表
-- drop table pda_CheckBill_B
IF not exists (select 1 from dbo.sysobjects where id = object_id(N'[dbo].[pda_CheckBill_B]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
begin
	CREATE TABLE [dbo].pda_CheckBill_B(
	    smb_id INT NOT NULL DEFAULT 0,--复核单条码表id
	    bill_id INT NOT NULL DEFAULT 0,--复核单id,=pda_CheckBill.billid
	    PickType INT NOT NULL DEFAULT 0,--1整件 2拼箱 3打包
	    barcode VARCHAR(50) NOT NULL DEFAULT '',--条码
        EligibleQty	NUMERIC(18,4) NOT NULL DEFAULT 0,--数量
        PickNo VARCHAR(30) NOT NULL DEFAULT '',--拣货单号
        pickemp_id INT NOT NULL DEFAULT 0,--拣货人id,=pda_employees.e_id
        checkemp_id INT NOT NULL DEFAULT 0,--复核人id,=pda_employees.e_id
        DealStates  int NOT NULL DEFAULT 0,--处理状态，0 未处理, 1 PDA已扫到码 0531
        pdastates INT NOT NULL DEFAULT 0,--交换状态 0提供 1pda已回写
	) ON [PRIMARY]
end
GO




--配送抵达
-- drop table pda_SendBill
IF not exists (select 1 from dbo.sysobjects where id = object_id(N'[dbo].[pda_SendBill]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
begin
	CREATE TABLE [dbo].pda_SendBill(
	    billid INT NOT NULL DEFAULT 0,--配送单id
	    Gspbillid INT NOT NULL DEFAULT 0,--业务单据id
	    billnumber VARCHAR(30) NOT NULL DEFAULT '',--销售订单单号
	    sendnumber VARCHAR(30) NOT NULL DEFAULT '',--物流单号
	    FirstStates VARCHAR(10) NOT NULL DEFAULT '',--优先级
	    c_id INT NOT NULL DEFAULT 0,--客户id,=pda_clients.c_id
	    e_id INT NOT NULL DEFAULT 0,--业务员id,=pda_employees.c_id
		contact_personal VARCHAR(20) NOT NULL DEFAULT '',--联系人
		phone_number VARCHAR(100) NOT NULL DEFAULT '',--联系电话
	    sendemp varchar(30) NOT NULL DEFAULT 0,--送货人  0531 更改
	    sendemp_phone VARCHAR(100) NOT NULL DEFAULT '',--送货人电话
	    recemp varchar(30) NOT NULL DEFAULT 0,--收货人   0531 更改
	    recemp_phone VARCHAR(100) NOT NULL DEFAULT '',--收货人电话
	    Address varchar(150) not null DEFAULT '',  --地址  0531
	    ArTotal numeric(18, 4) not null DEFAULT 0,  --应收款  0531
	    Note  VARCHAR(200) NOT NULL DEFAULT '',--单据备注
		WholeQty NUMERIC(18,4) NOT NULL DEFAULT 0,--整货数量
		PartQty NUMERIC(18,4) NOT NULL DEFAULT 0,--拼臬数量
		PackQty NUMERIC(18,4) NOT NULL DEFAULT 0,--打包数量
		wddetail VARCHAR(50) NOT NULL DEFAULT '',--温度
		Returndetail VARCHAR(100) NOT NULL DEFAULT '',--回执
		pdaOutTime DATETIME NOT NULL DEFAULT 0,--业务员交接完成的时间,配送单审核时间
		isSpecial INT NOT NULL DEFAULT 0,--是否含特殊药品 0不含 1包含
		isCold INT NOT NULL DEFAULT 0,--是否含冷链药品 0不含 1包含
                isRX INT NOT NULL DEFAULT 0,--是否含处方药 0不含 1包含
		pdastates INT NOT NULL DEFAULT 0,--交换状态 0提供 1pda已读取 2pda已回写 3药易通已回写
		pdaInTime DATETIME NOT NULL DEFAULT 0,--提供时间
		pdaReTime DATETIME NOT NULL DEFAULT 0,--读取时间
		pdaWrTime DATETIME NOT NULL DEFAULT 0--回写时间
	) ON [PRIMARY]
end
GO


--动态盘点单
-- drop table pda_pdBill
IF not exists (select 1 from dbo.sysobjects where id = object_id(N'[dbo].[pda_pdBill]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
begin
	CREATE TABLE [dbo].pda_pdBill(
	    billid INT NOT NULL DEFAULT 0,--盘点单id
	    pdname VARCHAR(50) NOT NULL DEFAULT '',--盘点名称
	    s_id INT NOT NULL DEFAULT 0,--仓库id,=pda_storages.s_id
		billstates INT NOT NULL DEFAULT 10,--盘单状态 0:未完成 1：门店初盘 2：复盘 3：已完成 4：仓库初盘
		pdastates INT NOT NULL DEFAULT 0,--交换状态 0提供 1pda已读取 2pda已回写
		pdaInTime DATETIME NOT NULL DEFAULT 0,--提供时间
		pdaReTime DATETIME NOT NULL DEFAULT 0,--读取时间
		pdaWrTime DATETIME NOT NULL DEFAULT 0--回写时间
	) ON [PRIMARY]
end
GO

--动态盘点明细表
-- drop table pda_pdBill_D
IF not exists (select 1 from dbo.sysobjects where id = object_id(N'[dbo].[pda_pdBill_D]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
begin
	CREATE TABLE [dbo].pda_pdBill_D(
	    smb_id INT NOT NULL DEFAULT 0,--盘点单明细表id0    0606
	    bill_id INT NOT NULL DEFAULT 0,--盘点单id,=pda_pdBill.billid
	    p_id INT NOT NULL DEFAULT 0,--商品id,=pda_Products.p_id
		MakeDate	DATETIME NOT NULL DEFAULT 0,--生产日期
		Validdate	DATETIME NOT NULL DEFAULT 0,--效期
		Batchno	VARCHAR(20) NOT NULL DEFAULT '',--批号
		EligibleQty	NUMERIC(18,4) NOT NULL DEFAULT 0,--库存数量
		pdQty	NUMERIC(18,4) NOT NULL DEFAULT 0,--实盘数量
		S_id	INT NOT NULL DEFAULT 0,--仓库id,=pda_storages.s_id
		Location_id	INT NOT NULL DEFAULT 0,--货位id,=pda_location.l_id
		Supplier_id	INT NOT NULL DEFAULT 0,--供应商id,=pda_PutOnBill.c_id
		InstoreTime	DATETIME NOT NULL DEFAULT 0,--入库时间
		billstates INT NOT NULL DEFAULT 10,--盘单状态 1：初盘 2：复盘
		DealStates  int NOT NULL DEFAULT 0,--处理状态，0 未处理, 1 PDA已盘点 0531
		pdastates INT NOT NULL DEFAULT 0,--状态 0提供数据 1pda回写数据
	) ON [PRIMARY]
end
GO

--收货单
-- drop table pda_RecBill
IF not exists (select 1 from dbo.sysobjects where id = object_id(N'[dbo].[pda_RecBill]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
begin
	CREATE TABLE [dbo].pda_RecBill(
	    billid INT NOT NULL DEFAULT 0,--收货单id
	    billnumber VARCHAR(30) NOT NULL DEFAULT '',--采购订单单号
	    c_id INT NOT NULL DEFAULT 0,--往来单位id,=pda_clients.c_id
	    e_id INT NOT NULL DEFAULT 0,--经手人id,=pda_employees.e_id
		billstates INT NOT NULL DEFAULT 10,--状态 10未处理 13已审核
		pdastates INT NOT NULL DEFAULT 0,--交换状态 0提供 1pda已读取 2pda已回写
		pdaInTime DATETIME NOT NULL DEFAULT 0,--提供时间
		pdaReTime DATETIME NOT NULL DEFAULT 0,--读取时间
		pdaWrTime DATETIME NOT NULL DEFAULT 0,--回写时间
	) ON [PRIMARY]
end
GO


--收货单,pda回写时只能修改收货数量、生产日期、批号、效期
-- drop table pda_RecBill_D
IF not exists (select 1 from dbo.sysobjects where id = object_id(N'[dbo].[pda_RecBill_D]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
begin
	CREATE TABLE [dbo].pda_RecBill_D(
	    smb_id INT NOT NULL DEFAULT 0,--收货单明细表id
	    bill_id INT NOT NULL DEFAULT 0,--收货单id,=pda_RecBill.billid
	    p_id INT NOT NULL DEFAULT 0,--商品id,=pda_Products.p_id
		MakeDate	DATETIME NOT NULL DEFAULT 0,--生产日期
		Validdate	DATETIME NOT NULL DEFAULT 0,--效期
		Batchno	VARCHAR(20) NOT NULL DEFAULT '',--批号
		Yqty	NUMERIC(18,4) NOT NULL DEFAULT 0,--订单数量
		EligibleQty	NUMERIC(18,4) NOT NULL DEFAULT 0,--收货数量
		TaxPrice	NUMERIC(18,4) NOT NULL DEFAULT 0,--含税单价
		TaxTotal	NUMERIC(18,4) NOT NULL DEFAULT 0,--含税金额
		CostPrice	NUMERIC(18,4) NOT NULL DEFAULT 0,--成本单价
		CostTotal	NUMERIC(18,4) NOT NULL DEFAULT 0,--成本金额
		S_id	INT NOT NULL DEFAULT 0,--仓库id,=pda_storages.s_id
		Location_id	INT NOT NULL DEFAULT 0,--货位id,=pda_location.l_id
		Supplier_id	INT NOT NULL DEFAULT 0,--供应商id,=pda_clients.c_id
		DealStates  int NOT NULL DEFAULT 0,--处理状态，0 未处理, 1 已经处理
		pdastates INT NOT NULL DEFAULT 0,--状态 0提供数据 1pda回写数据
	) ON [PRIMARY]
end
GO

--调拨单
-- drop table pda_TranBill
IF not exists (select 1 from dbo.sysobjects where id = object_id(N'[dbo].[pda_TranBill]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
begin
	CREATE TABLE [dbo].pda_TranBill(
	    billid INT NOT NULL DEFAULT 0,--调拨单id
	    billnumber VARCHAR(30) NOT NULL DEFAULT '',--调拨单单号
	    e_id INT NOT NULL DEFAULT 0,--经手人id,=pda_employees.e_id
		sout_id	INT NOT NULL DEFAULT 0,--调出仓库id,=pda_storages.s_id
		sin_id	INT NOT NULL DEFAULT 0,--调入仓库id,=pda_storages.s_id
		billstates INT NOT NULL DEFAULT 10,--状态 2未处理 3已审核
		pdastates INT NOT NULL DEFAULT 0,--交换状态 0提供 1pda已读取 2pda已回写
		pdaInTime DATETIME NOT NULL DEFAULT 0,--提供时间
		pdaReTime DATETIME NOT NULL DEFAULT 0,--读取时间
		pdaWrTime DATETIME NOT NULL DEFAULT 0,--回写时间
	) ON [PRIMARY]
end
GO


--调拨单,pda回写时只能修改调入仓库、调入货位
-- drop table pda_TranBill_D
IF not exists (select 1 from dbo.sysobjects where id = object_id(N'[dbo].[pda_TranBill_D]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
begin
	CREATE TABLE [dbo].pda_TranBill_D(
	    smb_id INT NOT NULL DEFAULT 0,--调拨单明细表id
	    bill_id INT NOT NULL DEFAULT 0,--调拨单id,=pda_TranBill.billid
	    p_id INT NOT NULL DEFAULT 0,--商品id,=pda_Products.p_id
		MakeDate	DATETIME NOT NULL DEFAULT 0,--生产日期
		Validdate	DATETIME NOT NULL DEFAULT 0,--效期
		Batchno	VARCHAR(20) NOT NULL DEFAULT '',--批号
		quantity	NUMERIC(18,4) NOT NULL DEFAULT 0,--数量
		TaxPrice	NUMERIC(18,4) NOT NULL DEFAULT 0,--含税单价
		TaxTotal	NUMERIC(18,4) NOT NULL DEFAULT 0,--含税金额
		CostPrice	NUMERIC(18,4) NOT NULL DEFAULT 0,--成本单价
		CostTotal	NUMERIC(18,4) NOT NULL DEFAULT 0,--成本金额
		ss_id	INT NOT NULL DEFAULT 0,--调出仓库id,=pda_storages.s_id
		sd_id	INT NOT NULL DEFAULT 0,--调入仓库id,=pda_storages.s_id
		Location_id	INT NOT NULL DEFAULT 0,--调出货位id,=pda_location.l_id
		location_id2	INT NOT NULL DEFAULT 0,--调入货位id,=pda_location.l_id
		Supplier_id	INT NOT NULL DEFAULT 0,--供应商id,=pda_clients.c_id
		DealStates  int NOT NULL DEFAULT 0,--处理状态，0 未处理, 1 已经处理
		pdastates INT NOT NULL DEFAULT 0,--状态 0提供数据 1pda回写数据
	) ON [PRIMARY]
end
GO

--库存盘点单,pda回写时只能修改盘点数量
-- drop table pda_kcpdBill_D
IF not exists (select 1 from dbo.sysobjects where id = object_id(N'[dbo].[pda_kcpdBill_D]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
begin
	CREATE TABLE [dbo].pda_kcpdBill_D(
	    storehouse_id INT NOT NULL DEFAULT 0,--库存表id
	    p_id INT NOT NULL DEFAULT 0,--商品id,=pda_Products.p_id
		MakeDate	DATETIME NOT NULL DEFAULT 0,--生产日期
		Validdate	DATETIME NOT NULL DEFAULT 0,--效期
		Batchno	VARCHAR(20) NOT NULL DEFAULT '',--批号
		quantity	NUMERIC(18,4) NOT NULL DEFAULT 0,--库存数量
		pdqty    NUMERIC(18,4) NOT NULL DEFAULT 0,--盘点数量
		CostPrice	NUMERIC(18,4) NOT NULL DEFAULT 0,--成本单价
		CostTotal	NUMERIC(18,4) NOT NULL DEFAULT 0,--成本金额
		ss_id	INT NOT NULL DEFAULT 0,--仓库id,=pda_storages.s_id
		Location_id	INT NOT NULL DEFAULT 0,--货位id,=pda_location.l_id
		Supplier_id	INT NOT NULL DEFAULT 0,--供应商id,=pda_clients.c_id
		DealStates  int NOT NULL DEFAULT 0,--处理状态，0 未处理, 1 已经处理
		pdastates INT NOT NULL DEFAULT 0,--状态 0提供数据 1pda回写数据
	) ON [PRIMARY]
end
GO

-- 新增表
IF not exists (select 1 from dbo.sysobjects where id = object_id(N'[dbo].[oauth_access_token]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
begin
create table oauth_access_token (
	token_id VARCHAR(256),
	token VARBINARY(MAX),
	authentication_id VARCHAR(256),
	user_name VARCHAR(256),
	client_id VARCHAR(256),
	authentication VARBINARY(MAX),
	refresh_token VARCHAR(256)
) ON [PRIMARY]
	end
GO

IF not exists (select 1 from dbo.sysobjects where id = object_id(N'[dbo].[oauth_refresh_token]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
begin
create table oauth_refresh_token (
	token_id VARCHAR(256),
	token VARBINARY(MAX),
	authentication VARBINARY(MAX)
) ON [PRIMARY]
	end
GO


