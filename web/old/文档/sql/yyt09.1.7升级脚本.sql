--调整字段的长度与productsExtend_dts表一致
IF EXISTS(SELECT * FROM dbo.syscolumns WHERE name = 'zycf' and id = OBJECT_ID('productsExtend'))
	ALTER TABLE productsExtend ALTER COLUMN zycf VARCHAR(400) NULL
GO
IF EXISTS(SELECT * FROM dbo.syscolumns WHERE name = 'gnzz' and id = OBJECT_ID('productsExtend'))
	ALTER TABLE productsExtend ALTER COLUMN gnzz VARCHAR(400) NULL
GO
IF EXISTS(SELECT * FROM dbo.syscolumns WHERE name = 'yfyl' and id = OBJECT_ID('productsExtend'))
	ALTER TABLE productsExtend ALTER COLUMN yfyl VARCHAR(400) NULL
GO
IF EXISTS(SELECT * FROM dbo.syscolumns WHERE name = 'cu' and id = OBJECT_ID('productsExtend'))
	ALTER TABLE productsExtend ALTER COLUMN cu VARCHAR(100) NULL
GO
IF EXISTS(SELECT * FROM dbo.syscolumns WHERE name = 'ku' and id = OBJECT_ID('productsExtend'))
	ALTER TABLE productsExtend ALTER COLUMN ku VARCHAR(100) NULL
GO
IF EXISTS(SELECT * FROM dbo.syscolumns WHERE name = 'gu' and id = OBJECT_ID('productsExtend'))
	ALTER TABLE productsExtend ALTER COLUMN gu VARCHAR(100) NULL
GO
IF EXISTS(SELECT * FROM dbo.syscolumns WHERE name = 'mzu' and id = OBJECT_ID('productsExtend'))
	ALTER TABLE productsExtend ALTER COLUMN mzu VARCHAR(100) NULL
GO
IF EXISTS(SELECT * FROM dbo.syscolumns WHERE name = 'cu1' and id = OBJECT_ID('productsExtend'))
	ALTER TABLE productsExtend ALTER COLUMN cu1 VARCHAR(100) NULL
GO
IF EXISTS(SELECT * FROM dbo.syscolumns WHERE name = 'ku1' and id = OBJECT_ID('productsExtend'))
	ALTER TABLE productsExtend ALTER COLUMN ku1 VARCHAR(100) NULL
GO
IF EXISTS(SELECT * FROM dbo.syscolumns WHERE name = 'gu1' and id = OBJECT_ID('productsExtend'))
	ALTER TABLE productsExtend ALTER COLUMN gu1 VARCHAR(100) NULL
GO
IF EXISTS(SELECT * FROM dbo.syscolumns WHERE name = 'mzu1' and id = OBJECT_ID('productsExtend'))
	ALTER TABLE productsExtend ALTER COLUMN mzu1 VARCHAR(100) NULL
GO
IF EXISTS(SELECT * FROM dbo.syscolumns WHERE name = 'cu2' and id = OBJECT_ID('productsExtend'))
	ALTER TABLE productsExtend ALTER COLUMN cu2 VARCHAR(100) NULL
GO
IF EXISTS(SELECT * FROM dbo.syscolumns WHERE name = 'ku2' and id = OBJECT_ID('productsExtend'))
	ALTER TABLE productsExtend ALTER COLUMN ku2 VARCHAR(100) NULL
GO
IF EXISTS(SELECT * FROM dbo.syscolumns WHERE name = 'gu2' and id = OBJECT_ID('productsExtend'))
	ALTER TABLE productsExtend ALTER COLUMN gu2 VARCHAR(100) NULL
GO
IF EXISTS(SELECT * FROM dbo.syscolumns WHERE name = 'mzu2' and id = OBJECT_ID('productsExtend'))
	ALTER TABLE productsExtend ALTER COLUMN gu2 VARCHAR(100) NULL
GO
IF EXISTS(SELECT * FROM dbo.syscolumns WHERE name = 'mzu2' and id = OBJECT_ID('productsExtend'))
	ALTER TABLE productsExtend ALTER COLUMN mzu2 VARCHAR(100) NULL
GO
IF EXISTS(SELECT * FROM dbo.syscolumns WHERE name = 'ku3' and id = OBJECT_ID('productsExtend'))
	ALTER TABLE productsExtend ALTER COLUMN ku3 VARCHAR(100) NULL
GO
IF EXISTS(SELECT * FROM dbo.syscolumns WHERE name = 'gu3' and id = OBJECT_ID('productsExtend'))
	ALTER TABLE productsExtend ALTER COLUMN gu3 VARCHAR(100) NULL
GO
IF EXISTS(SELECT * FROM dbo.syscolumns WHERE name = 'mzu3' and id = OBJECT_ID('productsExtend'))
	ALTER TABLE productsExtend ALTER COLUMN mzu3 VARCHAR(100) NULL
GO


 --yypeng-2017-03-16 09:56:15- 存在零售单过账没有写PRODUCTDETAIL的情况，这里做个日志检测表
if not exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[LostDetailLog]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
CREATE TABLE [dbo].[LostDetailLog](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[BillID] [int] NOT NULL,
	[BillGuid] [uniqueidentifier] NOT NULL,
	[BillType] [int] NOT NULL,	
	[y_id] [int] NOT NULL,
	[AuditTime] [datetime] NOT NULL,
	[BillNumber] [varchar](30) NOT NULL,
	[BillDate] [datetime] NULL,
	[ErrMsg] [varchar](200) NOT NULL,
 CONSTRAINT [PK_LostDetailLog] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)
) ON [PRIMARY]

GO
--阴历对照表增加公历字符串列
If not exists (select 1 from dbo.syscolumns where id = object_id(N'[dbo].[tbLunarData]') and name = 'SolarDateStr')
BEGIN 
  alter table dbo.tbLunarData add SolarDateStr varchar(50) NOT NULL DEFAULT '' 
end
GO

--阴历对照表删除公历varbinary列
If exists (select 1 from dbo.syscolumns where id = object_id(N'[dbo].[tbLunarData]') and name = 'SolarDate')
BEGIN  
  ALTER TABLE tbLunarData DROP COLUMN SolarDate
  TRUNCATE TABLE tbLunarData
end
GO

--XXX.2017-04-01 处理部分单据未成功下传的，在总部还原单据的传输状态
if not exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[DownBillLog]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
CREATE TABLE [dbo].[DownBillLog](
--	[ID] [int] IDENTITY(1,1) NOT NULL,
	[BillGuid] [uniqueidentifier] NOT NULL,
	[YGuid] [uniqueidentifier] NOT NULL,
	[BillType] [int] NOT NULL,
	[y_id] [int] NOT NULL
/* CONSTRAINT [PK_UpDownBillLog] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)*/
) ON [PRIMARY]
GO

 --yypeng-2017-04-05 15:47:49- 存在订单审核后没有生成拣货单的情况
if not exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[LostGVTPICKLog]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
CREATE TABLE [dbo].[LostGVTPICKLog](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[BillID] [int] NOT NULL,
	[BillGuid] [uniqueidentifier] NOT NULL,
	[BillType] [int] NOT NULL,	
	[y_id] [int] NOT NULL,
	[AuditTime] [datetime] NOT NULL,
	[BillNumber] [varchar](30) NOT NULL,
	[BillDate] [datetime] NULL,
	[ErrMsg] [varchar](200) NOT NULL
 CONSTRAINT [PK_LostGVTPICKLog] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)
) ON [PRIMARY]

GO


--yypeng-2017-04-12 11:52:07- 增加新自定义商品类别扩展表
if not exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[ProductCategory]') 
and OBJECTPROPERTY(id, N'IsUserTable') = 1)
begin
CREATE TABLE [dbo].[ProductCategory](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[P_id] [int] NOT NULL DEFAULT ((0)),
	[PComent1] [varchar](500) NOT NULL DEFAULT (''),
	[PComent2] [varchar](200) NOT NULL DEFAULT (''),
	[PComent3] [varchar](500) NOT NULL DEFAULT (''),
	[PComent4] [varchar](200) NOT NULL DEFAULT (''),
	[PComent5] [varchar](200) NOT NULL DEFAULT (''),
	[PComent6] [varchar](200) NOT NULL DEFAULT (''),
	[PComent7] [varchar](200) NOT NULL DEFAULT (''),
	[PComent8] [varchar](200) NOT NULL DEFAULT (''),
	[PComent9] [varchar](200) NOT NULL DEFAULT (''),
	[PComent10] [varchar](200) NOT NULL DEFAULT (''),
	[PComent11] [varchar](200) NOT NULL DEFAULT (''),
	[PComent12] [varchar](200) NOT NULL DEFAULT (''),
	[PComent13] [varchar](200) NOT NULL DEFAULT (''),
	[PComent14] [varchar](200) NOT NULL DEFAULT (''),
	[PComent15] [varchar](200) NOT NULL DEFAULT (''),
	[PComent16] [varchar](200) NOT NULL DEFAULT (''),
	[PComent17] [varchar](200) NOT NULL DEFAULT (''),
	[PComent18] [varchar](200) NOT NULL DEFAULT (''),
	[PComent19] [varchar](200) NOT NULL DEFAULT (''),
	[PComent20] [varchar](200) NOT NULL DEFAULT (''),
	[PComent21] [varchar](200) NOT NULL DEFAULT (''),
	[PComent22] [varchar](200) NOT NULL DEFAULT (''),
	[PComent23] [varchar](200) NOT NULL DEFAULT (''),
	[PComent24] [varchar](200) NOT NULL DEFAULT (''),
	[PComent25] [varchar](200) NOT NULL DEFAULT (''),
	[PComent26] [varchar](200) NOT NULL DEFAULT (''),
	[PComent27] [varchar](200) NOT NULL DEFAULT (''),
	[PComent28] [varchar](200) NOT NULL DEFAULT (''),
	[PComent29] [varchar](200) NOT NULL DEFAULT (''),
	[PComent30] [varchar](200) NOT NULL DEFAULT (''),
	[PComent31] [varchar](200) NOT NULL DEFAULT (''),
	[PComent32] [varchar](200) NOT NULL DEFAULT (''),
	[PComent33] [varchar](200) NOT NULL DEFAULT (''),
	[PComent34] [varchar](200) NOT NULL DEFAULT (''),
	[PComent35] [varchar](200) NOT NULL DEFAULT (''),
	[PComent36] [varchar](200) NOT NULL DEFAULT (''),
	[PComent37] [varchar](200) NOT NULL DEFAULT (''),
	[PComent38] [varchar](200) NOT NULL DEFAULT (''),
	[PComent39] [varchar](200) NOT NULL DEFAULT (''),
	[PComent40] [varchar](200) NOT NULL DEFAULT (''),
	[PComent41] [varchar](200) NOT NULL DEFAULT (''),
	[PComent42] [varchar](200) NOT NULL DEFAULT (''),
	[PComent43] [varchar](200) NOT NULL DEFAULT (''),
	[PComent44] [varchar](200) NOT NULL DEFAULT (''),
	[PComent45] [varchar](200) NOT NULL DEFAULT (''),
	[PComent46] [varchar](200) NOT NULL DEFAULT (''),
	[PComent47] [varchar](200) NOT NULL DEFAULT (''),
	[PComent48] [varchar](200) NOT NULL DEFAULT (''),
	[PComent49] [varchar](200) NOT NULL DEFAULT (''),
	[PComent50] [varchar](200) NOT NULL DEFAULT (''),
 CONSTRAINT [PK_ProductCategory] PRIMARY KEY CLUSTERED
(
	[ID] ASC
)
) ON [PRIMARY]
-- 
CREATE UNIQUE NONCLUSTERED INDEX [PK_PID] ON [dbo].[ProductCategory] --非聚集唯一索引列，不允许有相同PID录入
(
	[P_id] ASC
)
ON [PRIMARY]


end

GO


--yypeng-2017-04-12 11:52:07- 增加新自定义往来单位类别扩展表
if not exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[ClientCategory]') 
and OBJECTPROPERTY(id, N'IsUserTable') = 1)
begin
CREATE TABLE [dbo].[ClientCategory](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[C_id] [int] NOT NULL DEFAULT ((0)),
	[CComent1] [varchar](500) NOT NULL DEFAULT (''),
	[CComent2] [varchar](200) NOT NULL DEFAULT (''),
	[CComent3] [varchar](500) NOT NULL DEFAULT (''),
	[CComent4] [varchar](200) NOT NULL DEFAULT (''),
	[CComent5] [varchar](200) NOT NULL DEFAULT (''),
	[CComent6] [varchar](200) NOT NULL DEFAULT (''),
	[CComent7] [varchar](200) NOT NULL DEFAULT (''),
	[CComent8] [varchar](200) NOT NULL DEFAULT (''),
	[CComent9] [varchar](200) NOT NULL DEFAULT (''),
	[CComent10] [varchar](200) NOT NULL DEFAULT (''),
	[CComent11] [varchar](200) NOT NULL DEFAULT (''),
	[CComent12] [varchar](200) NOT NULL DEFAULT (''),
	[CComent13] [varchar](200) NOT NULL DEFAULT (''),
	[CComent14] [varchar](200) NOT NULL DEFAULT (''),
	[CComent15] [varchar](200) NOT NULL DEFAULT (''),
	[CComent16] [varchar](200) NOT NULL DEFAULT (''),
	[CComent17] [varchar](200) NOT NULL DEFAULT (''),
	[CComent18] [varchar](200) NOT NULL DEFAULT (''),
	[CComent19] [varchar](200) NOT NULL DEFAULT (''),
	[CComent20] [varchar](200) NOT NULL DEFAULT (''),
	[CComent21] [varchar](200) NOT NULL DEFAULT (''),
	[CComent22] [varchar](200) NOT NULL DEFAULT (''),
	[CComent23] [varchar](200) NOT NULL DEFAULT (''),
	[CComent24] [varchar](200) NOT NULL DEFAULT (''),
	[CComent25] [varchar](200) NOT NULL DEFAULT (''),
	[CComent26] [varchar](200) NOT NULL DEFAULT (''),
	[CComent27] [varchar](200) NOT NULL DEFAULT (''),
	[CComent28] [varchar](200) NOT NULL DEFAULT (''),
	[CComent29] [varchar](200) NOT NULL DEFAULT (''),
	[CComent30] [varchar](200) NOT NULL DEFAULT (''),
	[CComent31] [varchar](200) NOT NULL DEFAULT (''),
	[CComent32] [varchar](200) NOT NULL DEFAULT (''),
	[CComent33] [varchar](200) NOT NULL DEFAULT (''),
	[CComent34] [varchar](200) NOT NULL DEFAULT (''),
	[CComent35] [varchar](200) NOT NULL DEFAULT (''),
	[CComent36] [varchar](200) NOT NULL DEFAULT (''),
	[CComent37] [varchar](200) NOT NULL DEFAULT (''),
	[CComent38] [varchar](200) NOT NULL DEFAULT (''),
	[CComent39] [varchar](200) NOT NULL DEFAULT (''),
	[CComent40] [varchar](200) NOT NULL DEFAULT (''),
	[CComent41] [varchar](200) NOT NULL DEFAULT (''),
	[CComent42] [varchar](200) NOT NULL DEFAULT (''),
	[CComent43] [varchar](200) NOT NULL DEFAULT (''),
	[CComent44] [varchar](200) NOT NULL DEFAULT (''),
	[CComent45] [varchar](200) NOT NULL DEFAULT (''),
	[CComent46] [varchar](200) NOT NULL DEFAULT (''),
	[CComent47] [varchar](200) NOT NULL DEFAULT (''),
	[CComent48] [varchar](200) NOT NULL DEFAULT (''),
	[CComent49] [varchar](200) NOT NULL DEFAULT (''),
	[CComent50] [varchar](200) NOT NULL DEFAULT (''),
 CONSTRAINT [PK_ClientCategory] PRIMARY KEY CLUSTERED
 
(
	[ID] ASC
)
) ON [PRIMARY]
-- 
CREATE UNIQUE NONCLUSTERED INDEX [PK_CID] ON [dbo].[ClientCategory] --非聚集唯一索引列，不允许有相同PID录入
(
	[C_id] ASC
)
ON [PRIMARY]


end

GO


--yypeng-2017-04-12 11:52:07- 增加新自定义机构类别扩展表
if not exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[CompanyCategory]') 
and OBJECTPROPERTY(id, N'IsUserTable') = 1)
begin
CREATE TABLE [dbo].[CompanyCategory](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[Y_id] [int] NOT NULL DEFAULT ((0)),
	[YComent1] [varchar](500) NOT NULL DEFAULT (''),
	[YComent2] [varchar](200) NOT NULL DEFAULT (''),
	[YComent3] [varchar](500) NOT NULL DEFAULT (''),
	[YComent4] [varchar](200) NOT NULL DEFAULT (''),
	[YComent5] [varchar](200) NOT NULL DEFAULT (''),
	[YComent6] [varchar](200) NOT NULL DEFAULT (''),
	[YComent7] [varchar](200) NOT NULL DEFAULT (''),
	[YComent8] [varchar](200) NOT NULL DEFAULT (''),
	[YComent9] [varchar](200) NOT NULL DEFAULT (''),
	[YComent10] [varchar](200) NOT NULL DEFAULT (''),
	[YComent11] [varchar](200) NOT NULL DEFAULT (''),
	[YComent12] [varchar](200) NOT NULL DEFAULT (''),
	[YComent13] [varchar](200) NOT NULL DEFAULT (''),
	[YComent14] [varchar](200) NOT NULL DEFAULT (''),
	[YComent15] [varchar](200) NOT NULL DEFAULT (''),
	[YComent16] [varchar](200) NOT NULL DEFAULT (''),
	[YComent17] [varchar](200) NOT NULL DEFAULT (''),
	[YComent18] [varchar](200) NOT NULL DEFAULT (''),
	[YComent19] [varchar](200) NOT NULL DEFAULT (''),
	[YComent20] [varchar](200) NOT NULL DEFAULT (''),
	[YComent21] [varchar](200) NOT NULL DEFAULT (''),
	[YComent22] [varchar](200) NOT NULL DEFAULT (''),
	[YComent23] [varchar](200) NOT NULL DEFAULT (''),
	[YComent24] [varchar](200) NOT NULL DEFAULT (''),
	[YComent25] [varchar](200) NOT NULL DEFAULT (''),
	[YComent26] [varchar](200) NOT NULL DEFAULT (''),
	[YComent27] [varchar](200) NOT NULL DEFAULT (''),
	[YComent28] [varchar](200) NOT NULL DEFAULT (''),
	[YComent29] [varchar](200) NOT NULL DEFAULT (''),
	[YComent30] [varchar](200) NOT NULL DEFAULT (''),
	[YComent31] [varchar](200) NOT NULL DEFAULT (''),
	[YComent32] [varchar](200) NOT NULL DEFAULT (''),
	[YComent33] [varchar](200) NOT NULL DEFAULT (''),
	[YComent34] [varchar](200) NOT NULL DEFAULT (''),
	[YComent35] [varchar](200) NOT NULL DEFAULT (''),
	[YComent36] [varchar](200) NOT NULL DEFAULT (''),
	[YComent37] [varchar](200) NOT NULL DEFAULT (''),
	[YComent38] [varchar](200) NOT NULL DEFAULT (''),
	[YComent39] [varchar](200) NOT NULL DEFAULT (''),
	[YComent40] [varchar](200) NOT NULL DEFAULT (''),
	[YComent41] [varchar](200) NOT NULL DEFAULT (''),
	[YComent42] [varchar](200) NOT NULL DEFAULT (''),
	[YComent43] [varchar](200) NOT NULL DEFAULT (''),
	[YComent44] [varchar](200) NOT NULL DEFAULT (''),
	[YComent45] [varchar](200) NOT NULL DEFAULT (''),
	[YComent46] [varchar](200) NOT NULL DEFAULT (''),
	[YComent47] [varchar](200) NOT NULL DEFAULT (''),
	[YComent48] [varchar](200) NOT NULL DEFAULT (''),
	[YComent49] [varchar](200) NOT NULL DEFAULT (''),
	[YComent50] [varchar](200) NOT NULL DEFAULT (''),
 CONSTRAINT [PK_CompanyCategory] PRIMARY KEY CLUSTERED
(
	[ID] ASC
)
) ON [PRIMARY]
-- 
CREATE UNIQUE NONCLUSTERED INDEX [PK_YID] ON [dbo].[CompanyCategory] --非聚集唯一索引列，不允许有相同PID录入
(
	[Y_id] ASC
)
ON [PRIMARY]

end

GO


if not exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[CategoryTemplate]') 
and OBJECTPROPERTY(id, N'IsUserTable') = 1)
BEGIN
Create Table CategoryTemplate
(
  	[ID] [int] IDENTITY(1,1) NOT NULL,
  	[Sx_id] [int] NOT NULL DEFAULT ((0)),          --属性ID
	[BaseType] [int] NOT NULL DEFAULT ((0)),       --1,商品资料 2,往来单位 3,分支机构
	[TypeName] varchar(400) NOT NULL DEFAULT (''), --类别名称
	[Type] [int] NOT NULL DEFAULT ((0)),           --1,文本 2,下拉单选框 3,数字 4,日期 5,自定义类别
	[SysDefault] [int] NOT NULL DEFAULT ((0)),     --0,系统默认 1,非系统默认
	[IsUse] [int] NOT NULL DEFAULT ((0)),          --0,不启用 1,启用]
	[FieldName] varchar(20) NOT NULL DEFAULT ('')  --对应字段
 CONSTRAINT [PK_CategoryTemplate] PRIMARY KEY CLUSTERED
(
	[ID] ASC
)
) ON [PRIMARY]
END
GO

--自定义属性模板取值范围表
if not exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[CategoryValueRange]') 
and OBJECTPROPERTY(id, N'IsUserTable') = 1)
BEGIN
Create Table CategoryValueRange
(
  	[ID] [int] IDENTITY(1,1) NOT NULL,
  	[Sx_id] [int] NOT NULL DEFAULT ((0)),       --属性ID
  	[BaseType] [int] NOT NULL DEFAULT ((0)),    --1,商品资料 2,往来单位 3,分支机构
  	[ValueRange] varchar(400) NOT NULL DEFAULT ('') --取值范围
CONSTRAINT [PK_CategoryValueRange] PRIMARY KEY CLUSTERED
(
	[ID] ASC
)
) ON [PRIMARY]
END
GO

--XXX.2017-04-14自定义属性组的表结构，和以前的自定义类别表基本一致,增加一个字段存储属性关联表
if not exists (select * from dbo.syscolumns where id = object_id(N'[dbo].[customCategory]') and  name = 'Category_id')
begin
  alter table dbo.customCategory add
	[Category_id]  int  not NULL DEFAULT (0)
end
GO
/*
--XXX.2017-04-14自定义属性组的表结构，和以前的自定义类别表基本一致
if not exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[CategoryGroups]') 
and OBJECTPROPERTY(id, N'IsUserTable') = 1)
begin
CREATE TABLE [dbo].[CategoryGroups](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](80) NOT NULL,   --组名称
	[Category_id] int NOT NULL,      --关联的自定义类别id
	[class_id] [varchar](8) NOT NULL, --
	[parent_id] [int] NOT NULL,
	[baseType] [int] NOT NULL,   --1,商品资料 2,往来单位 3,分支机构
	[modifyDate] [timestamp] NOT NULL,
	[deleted] [int] NOT NULL,
	[pinyin] [varchar](30) NULL,
/*	[Child_Number] [int] NULL,
	[Child_Count] [int] NULL,
	[Typeid] [int] NOT NULL,*/
 CONSTRAINT [PK_CategoryGroups] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)
) ON [PRIMARY]
end
*/
GO

--XXX.2017-04-22 在单据表中增加一个字段用来保存自动发行的代金券的券号
if not exists (select * from dbo.syscolumns where id = object_id(N'[dbo].[billidx]') and  name = 'CashNo')
begin
  alter table dbo.billidx add
	[CashNo]  varchar(20)  not NULL DEFAULT ('')
end
GO

--ypeng-2017-05-03 给新自定义机构类别扩展表加时间戳，用以增量上下传
If not exists (select  * from dbo.syscolumns where id = object_id(N'[dbo].[ProductCategory]') and  name = 'ModifyDate')
  ALTER TABLE ProductCategory ADD [ModifyDate] [timestamp] NOT NULL 
GO

If not exists (select  * from dbo.syscolumns where id = object_id(N'[dbo].[ClientCategory]') and  name = 'ModifyDate')
  ALTER TABLE ClientCategory ADD [ModifyDate] [timestamp] NOT NULL 
GO

If not exists (select  * from dbo.syscolumns where id = object_id(N'[dbo].[CompanyCategory]') and  name = 'ModifyDate')
  ALTER TABLE CompanyCategory ADD [ModifyDate] [timestamp] NOT NULL 
GO

---XXX.2017-05-03 促销积分设置长度可以到千分位，这儿长度增加到4位
if exists( select 1 from syscolumns where name = 'Itgrate' and id = OBJECT_ID(N'CxDetail') and xtype = 108 and xscale = 2)
  ALTER TABLE CxDetail alter column [Itgrate] numeric(18,4) NOT NULL 
GO

-- Wsj.2017-05-03 商品资料增加仓储要求-独占货位
If not exists (select  * from dbo.syscolumns where id = object_id(N'[dbo].[products]') and  name = 'AloneLocation')
  ALTER TABLE products ADD [AloneLocation] [INT] NOT NULL DEFAULT (0)
GO

-- Wsj.2017-05-03 商品资料增加仓储要求-货位要求
If not exists (select  * from dbo.syscolumns where id = object_id(N'[dbo].[products]') and  name = 'LocationRequirement')
  ALTER TABLE products ADD [LocationRequirement] [INT] NOT NULL DEFAULT (0)
GO

-- Wsj.2017-05-03 商品资料增加仓储要求-默认采购单位
If not exists (select  * from dbo.syscolumns where id = object_id(N'[dbo].[products]') and  name = 'DefaultBuyUnit')
  ALTER TABLE products ADD [DefaultBuyUnit] [INT] NOT NULL DEFAULT (0)
GO

-- Wsj.2017-05-03 商品资料增加仓储要求-默认销售单位
If not exists (select  * from dbo.syscolumns where id = object_id(N'[dbo].[products]') and  name = 'DefaultSaleUnit')
  ALTER TABLE products ADD [DefaultSaleUnit] [INT] NOT NULL DEFAULT (0)
GO

-- Wsj.2017-05-03 商品资料增加仓储要求-控制销售按默认单位整数倍开票
If not exists (select  * from dbo.syscolumns where id = object_id(N'[dbo].[products]') and  name = 'SaleUnitMultiplex')
  ALTER TABLE products ADD [SaleUnitMultiplex] [INT] NOT NULL DEFAULT (0)
GO
--zjx.2017-05-12增加WMS区域规划资料表和字段begin
--区域表
if not exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[WMSRegion]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
BEGIN
Create Table WMSRegion
(
  	[ID] [int] IDENTITY(1,1) NOT NULL,--区域ID
  	[Store_KQ_ID] [int] NOT NULL DEFAULT ((0)),          --库区ID
	[serial_number] varchar(100)  NOT NULL DEFAULT '',       --区域编号
	[Name] varchar(400) NOT NULL DEFAULT (''), --区域名称
	[RegionCode] varchar(400) NOT NULL DEFAULT '',           --区域功能编号
	[RegionComent] varchar(400) NOT NULL DEFAULT '',          --区域功能说明
 CONSTRAINT [PK_WMSRegion] PRIMARY KEY CLUSTERED
(
	[ID] ASC
)
) ON [PRIMARY]
END
go
--存放剂型限定（区域和货位）
if not exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[WMSMedtype]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
BEGIN
Create Table WMSMedtype
(
  	[ID] [int] IDENTITY(1,1) NOT NULL,--存放剂型限定ID
  	[StoreQYHWID] [int] NOT NULL DEFAULT ((0)),          --区域和货位的ID
	[MedtypeID]   [int] NOT NULL DEFAULT ((0)),       --剂型ID
	[Quantity]    [numeric](10,4) NOT NULL DEFAULT (0),--存放数量
	[Flag] [int] NOT NULL DEFAULT ((0))       --区分是区域还是货位，1代表区域；2代表货位的存放剂型限定
 CONSTRAINT [PK_WMSMedtype] PRIMARY KEY CLUSTERED
(
	[ID] ASC
)
) ON [PRIMARY]
END
go
--增加库区表库区类型字段
If not exists (select 1 from dbo.syscolumns where id = object_id(N'[dbo].[stockArea]') and name = 'SAType')
BEGIN 
  alter table stockArea add SAType int  NOT NULL DEFAULT 0
end
GO
--增加库区表质量类型字段
If not exists (select 1 from dbo.syscolumns where id = object_id(N'[dbo].[stockArea]') and name = 'SAQuantityType')
BEGIN 
  alter table stockArea add SAQuantityType int  NOT NULL DEFAULT 0
end
go
--增加库区表拣货方式字段
If not exists (select 1 from dbo.syscolumns where id = object_id(N'[dbo].[stockArea]') and name = 'SAPickType')
BEGIN 
  alter table stockArea add SAPickType int  NOT NULL DEFAULT 0
end
go
--增加库区表打印方式字段
If not exists (select 1 from dbo.syscolumns where id = object_id(N'[dbo].[stockArea]') and name = 'SAPrintType')
BEGIN 
  alter table stockArea add SAPrintType int  NOT NULL DEFAULT 0
end
go
--增加库区时间戳
If not exists (select  * from dbo.syscolumns where id = object_id(N'[dbo].[stockArea]') and  name = 'ModifyDate')
  ALTER TABLE stockArea ADD [ModifyDate] [timestamp] NOT NULL 
GO
--增加库区deleted
If not exists (select  * from dbo.syscolumns where id = object_id(N'[dbo].[stockArea]') and  name = 'deleted')
  ALTER TABLE stockArea ADD deleted  int  NOT NULL DEFAULT (0)
GO
--增加货位所属区域
If not exists (select 1 from dbo.syscolumns where id = object_id(N'[dbo].[location]') and name = 'Regionid')
BEGIN 
  alter table location add Regionid int  NOT NULL DEFAULT 0
end
go
--增加货位货架类型
If not exists (select 1 from dbo.syscolumns where id = object_id(N'[dbo].[location]') and name = 'LocationType')
BEGIN 
  alter table location add LocationType int  NOT NULL DEFAULT 0
end
go
--增加货位挑选难易程度
If not exists (select 1 from dbo.syscolumns where id = object_id(N'[dbo].[location]') and name = 'PickEasy')
BEGIN 
  alter table location add PickEasy int  NOT NULL DEFAULT 0
end
go
--增加货位可存货品规数
If not exists (select 1 from dbo.syscolumns where id = object_id(N'[dbo].[location]') and name = 'stockpilePgNum')
BEGIN 
  alter table location add stockpilePgNum [numeric](10,4) NOT NULL DEFAULT (0)
end
go
--增加货位已存货品规数
If not exists (select 1 from dbo.syscolumns where id = object_id(N'[dbo].[location]') and name = 'alreadyPgNum')
BEGIN 
  alter table location add alreadyPgNum  [numeric](10,4) NOT NULL DEFAULT (0)
end
go
--增加货位巷道号
If not exists (select 1 from dbo.syscolumns where id = object_id(N'[dbo].[location]') and name = 'roadwayCode')
BEGIN 
  alter table location add roadwayCode  varchar(400) NOT NULL DEFAULT ''
end
go
--增加货位起始排
If not exists (select 1 from dbo.syscolumns where id = object_id(N'[dbo].[location]') and name = 'beginrow')
BEGIN 
  alter table location add beginrow  [numeric](10,4) NOT NULL DEFAULT (0)
end
go
--增加货位起始层
If not exists (select 1 from dbo.syscolumns where id = object_id(N'[dbo].[location]') and name = 'begintier')
BEGIN 
  alter table location add begintier  [numeric](10,4) NOT NULL DEFAULT (0)
end
go
--增加货位起始列
If not exists (select 1 from dbo.syscolumns where id = object_id(N'[dbo].[location]') and name = 'begincol')
BEGIN 
  alter table location add begincol  [numeric](10,4) NOT NULL DEFAULT (0)
end
go
--增加货位终止排
If not exists (select 1 from dbo.syscolumns where id = object_id(N'[dbo].[location]') and name = 'endrow')
BEGIN 
  alter table location add endrow  [numeric](10,4) NOT NULL DEFAULT (0)
end
go
--增加货位终止层
If not exists (select 1 from dbo.syscolumns where id = object_id(N'[dbo].[location]') and name = 'endtier')
BEGIN 
  alter table location add endtier  [numeric](10,4) NOT NULL DEFAULT (0)
end
go
--增加货位终止列
If not exists (select 1 from dbo.syscolumns where id = object_id(N'[dbo].[location]') and name = 'endcol')
BEGIN 
  alter table location add endcol  [numeric](10,4) NOT NULL DEFAULT (0)
end
go
--增加区域时间戳
If not exists (select  * from dbo.syscolumns where id = object_id(N'[dbo].[WMSRegion]') and  name = 'ModifyDate')
  ALTER TABLE WMSRegion ADD [ModifyDate] [timestamp] NOT NULL 
GO
--增加区域deleted
If not exists (select  * from dbo.syscolumns where id = object_id(N'[dbo].[WMSRegion]') and  name = 'deleted')
  ALTER TABLE WMSRegion ADD deleted  int  NOT NULL DEFAULT (0)
GO
--增加WMS区域和剂型剂型辅助表时间戳
If not exists (select  * from dbo.syscolumns where id = object_id(N'[dbo].[WMSMedtype]') and  name = 'ModifyDate')
  ALTER TABLE WMSMedtype ADD [ModifyDate] [timestamp] NOT NULL 
GO
--增加WMS区域和剂型剂型辅助表deleted
If not exists (select  * from dbo.syscolumns where id = object_id(N'[dbo].[WMSMedtype]') and  name = 'deleted')
  ALTER TABLE WMSMedtype ADD deleted  int  NOT NULL  DEFAULT (0)
--zjx.2017-05-12增加WMS区域规划资料表和字段end
GO
--zjx增加WMS暂存区资料表
if not exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[WMSHold]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
BEGIN
	Create Table WMSHold
	(
  		[ID]    int IDENTITY(1,1) NOT NULL,--暂存区ID
  		[S_id]  int NOT NULL DEFAULT ((0)),--仓库ID
		[Code]  varchar(100) NOT NULL DEFAULT (''),--暂存区编号
		[name]  varchar(100) NOT NULL DEFAULT (''),--暂存区名称
		[PinYin] varchar(100) NOT NULL DEFAULT (''),--暂存区拼音码
		[billtype] int NOT NULL DEFAULT ((0)),       --0代表销售出库，1门店配送，2采购退货，3其它出库
		[CyFs]     int NOT NULL DEFAULT ((0)),    --0代表自提，1代表企业配送，2代表委运输
		[priority] int NOT NULL DEFAULT ((0)),    --0代表一般，1代表补货，2代表优先，3代表急救
		[LockStatus] int  NOT NULL DEFAULT ((0)), --0代码解锁，1代表锁定
		[Deleted]    int  NOT NULL DEFAULT ((0)), --0代表正常，1代表删除, 2代表停用
	    [ModifyDate] [timestamp] NOT NULL ,--时间戳
	    [RoadID]     int  NOT NULL DEFAULT ((0)) --配送线路
	 CONSTRAINT [PK_WMSHold] PRIMARY KEY CLUSTERED
	(
		[ID] ASC
	)
	) ON [PRIMARY]
END
go
--zjx增加WMS复核台资料表
if not exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[WMSCheckBaseInfo]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
BEGIN
	Create Table WMSCheckBaseInfo
	(
  		[ID]    int IDENTITY(1,1) NOT NULL,--复核台ID
  		[S_id]  int NOT NULL DEFAULT ((0)),--仓库ID
		[Code]  varchar(100) NOT NULL DEFAULT (''),--复核台编号
		[name]  varchar(100) NOT NULL DEFAULT (''),--复核台名称
		[PinYin] varchar(100) NOT NULL DEFAULT (''),--复核台拼音码
		[GroupCode] varchar(100) NOT NULL DEFAULT (''),--复核台分组编码
		[Deleted]    int  NOT NULL DEFAULT ((0)), --0代表正常，1代表删除, 2代表停用
	    [ModifyDate] [timestamp] NOT NULL ,--时间戳
	    [WorkNum] [numeric](10,4) NOT NULL DEFAULT (0),--工作量
	    [BillNum] [numeric](10,4) NOT NULL DEFAULT (0),--活动单据数
	    [IfHold]    int  NOT NULL DEFAULT ((0)), --是否虚拟暂存区 0代表否，1代表是
	    [IfUse]     int  NOT NULL DEFAULT ((0)), --是否可用 0代表否，1代表是
	    [ContainerNo] varchar(100) NOT NULL DEFAULT (''),--周转箱号
	    [PgNum] [numeric](10,4) NOT NULL DEFAULT (0),--品规数
	    [LockBillNum]  varchar(100) NOT NULL DEFAULT ('')--锁定单据编号
	 CONSTRAINT [PK_WMSCheckBaseInfo] PRIMARY KEY CLUSTERED
	(
		[ID] ASC
	)
	) ON [PRIMARY]
END
go
--zjx增加WMS复核台资料关联辅助区域表和暂存区表，GSP属性表值
if not exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[WMSCheckAHG]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
BEGIN
	Create Table WMSCheckAHG
	(
  		[ID]    int IDENTITY(1,1) NOT NULL,--自增列ID
  		[CheckID] int NOT NULL DEFAULT ((0)),--复核台id
  		[AreaHoldGspID]  int NOT NULL DEFAULT ((0)),--库区ID或者暂存区ID或者GSP属性id
		[Type]  int  NOT NULL DEFAULT ((0)),--区分类型，1代表库区，2代表暂存区，3代表GSP属性
		[Deleted]    int  NOT NULL DEFAULT ((0)), --0代表正常，1代表删除, 2代表停用
	    [ModifyDate] [timestamp] NOT NULL --时间戳
	 CONSTRAINT [PK_WMSCheckAHG] PRIMARY KEY CLUSTERED
	(
		[ID] ASC
	)
	) ON [PRIMARY]
END
go

--XXX.2017-05-17 gsp流程单据表体增加整货数量
If not exists (select  * from dbo.syscolumns where id = object_id(N'[dbo].[gspbilldetail]') and  name = 'WholeQty')
  ALTER TABLE gspbilldetail ADD [WholeQty] [numeric](25,8) NOT NULL DEFAULT (0)
GO

--XXX.2017-05-17 gsp流程单据表体增加零货数量
If not exists (select  * from dbo.syscolumns where id = object_id(N'[dbo].[gspbilldetail]') and  name = 'PartQty')
  ALTER TABLE gspbilldetail ADD [PartQty] [numeric](25,8) NOT NULL DEFAULT (0)
GO

--商品第一次采购入库时间记录表
if not exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[ProductFirstInStoreTime]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
CREATE TABLE [dbo].[ProductFirstInStoreTime] 
(
  [KeyId]       [int]			IDENTITY(1,1)  NOT NULL, 
  [P_Id]        [int]			DEFAULT (0)  NOT NULL,          --商品Id  
  [InStoreTime] [datetime]   	DEFAULT (GETDATE())  NOT NULL,  --入库时间
  [Y_Id]		[int]			DEFAULT (2)  NOT NULL           --机构Id
 CONSTRAINT [PK_ProductFirstInStoreTime] PRIMARY KEY CLUSTERED 
(
	[KeyId] ASC
)
) ON [PRIMARY]
GO

--XXX.2017-05-22 Gsp单据增加明细行，只统计商品明细
if not exists (select * from dbo.syscolumns where id = object_id(N'[dbo].[GSPbillidx]') and  name = 'detailcount')
begin
  alter table dbo.GSPbillidx add
	detailcount int  not  NULL DEFAULT (0)
end
GO

--XXX.2017-05-22 Gsp单据增加明细行，只统计商品明细
if not exists (select * from dbo.syscolumns where id = object_id(N'[dbo].[GSPbillidx]') and  name = 'PgQty')
begin
  alter table dbo.GSPbillidx add
	PgQty int  not  NULL DEFAULT (0)
end
GO

--zjx2017-05-19添加角色表
if not exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[WMSRole]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
CREATE TABLE [dbo].[WMSRole] 
(
  [Id]          [int]			IDENTITY(1,1)  NOT NULL, --角色自增列ID
  [Name]        varchar(100) NOT NULL DEFAULT ('')        --角色名称 
 CONSTRAINT [PK_WMSRole] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)
) ON [PRIMARY]
GO
--zjx2017-05-19添加职员角色表
if not exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[WMSEmpRole]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
CREATE TABLE [dbo].[WMSEmpRole] 
(
  [Id]          [int]			IDENTITY(1,1)  NOT NULL, --角色自增列ID
  [RoleId]      [int]           DEFAULT (0)  NOT NULL,    --角色ID;1代表收货员，2代表验收员，3代表拣货员，4复核员，5代表库管员 
  [Empid]       [int]           DEFAULT (0)  NOT NULL,    --职员ID  
  [WorkNo]      varchar(100)    DEFAULT ('') NOT NULL,--工号
  [PickType]    [int]           DEFAULT (0) NOT NULL, --拣货类型
  [Workfirst]   [int]           DEFAULT (0) NOT NULL, --工作优先
  [PickOrder]   [int]           DEFAULT (0) NOT NULL, --拣货顺序
  [Deleted]     [int]           DEFAULT (0) NOT NULL, --0代表正常，1代表删除
  [ModifyDate]  [timestamp] NOT NULL, --时间戳
  [Coment]      varchar(100)    DEFAULT ('') NOT NULL --备注
 CONSTRAINT [PK_WMSEmpRole] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)
) ON [PRIMARY]
GO
--zjx2017-05-19添加职员角色对应GSP属性表
if not exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[WMSEmpRoleGsp]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
CREATE TABLE [dbo].[WMSEmpRoleGsp] 
(
  [Id]          [int]			IDENTITY(1,1)  NOT NULL, --角色自增列ID
  [Bill_id]      [int]           DEFAULT (0)  NOT NULL,  --职员角色表自增列ID
  [RoleGSP]       [int]           DEFAULT (0)  NOT NULL,  --代表gsp属性ID
  [Deleted]     [int]           DEFAULT (0) NOT NULL, --0代表正常，1代表删除
  [ModifyDate] [timestamp] NOT NULL --时间戳
 CONSTRAINT [PK_WMSEmpRoleGsp] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)
) ON [PRIMARY]
GO
--zjx2017-05-19添加职员角色对应区域和库区表
if not exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[WMSEmpRoleAQ]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
CREATE TABLE [dbo].[WMSEmpRoleAQ] 
(
  [Id]          [int]			IDENTITY(1,1)  NOT NULL, --角色自增列ID
  [Bill_id]      [int]           DEFAULT (0)  NOT NULL,  --职员角色表自增列ID
  [RoleAQid]       [int]           DEFAULT (0)  NOT NULL,  --代表区域和库区ID
  [Deleted]     [int]           DEFAULT (0) NOT NULL, --0代表正常，1代表删除
  [ModifyDate] [timestamp] NOT NULL --时间戳
 CONSTRAINT [PK_WMSEmpRoleAQ] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)
) ON [PRIMARY]
GO
--zjx2017-05-23增加暂存区工作流量字段
If not exists (select 1 from dbo.syscolumns where id = object_id(N'[dbo].[WMSHold]') and name = 'jobNum')
BEGIN 
  alter table WMSHold add jobNum int  NOT NULL DEFAULT 0
end
GO
--zjx2017-05-23增加复核台工作流量字段
If not exists (select 1 from dbo.syscolumns where id = object_id(N'[dbo].[WMSCheckBaseInfo]') and name = 'jobNum')
BEGIN 
  alter table WMSCheckBaseInfo add jobNum int  NOT NULL DEFAULT 0
end
GO
--zjx2017-05-23添加标签类型表
if not exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[WMSTagType]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
CREATE TABLE [dbo].[WMSTagType] 
(
  [Id]          [int]			IDENTITY(1,1)  NOT NULL, --自增列ID
  [Code]        varchar(100)    DEFAULT ('') NOT NULL,  --编码
  [Name]        varchar(100)    DEFAULT ('') NOT NULL,  --名称
  [Coment]      varchar(100)    DEFAULT ('') NOT NULL,  --备注
  [Deleted]     [int]           DEFAULT (0) NOT NULL, --0代表正常，1代表删除
  [ModifyDate] [timestamp] NOT NULL --时间戳
 CONSTRAINT [PK_WMSTagType] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)
) ON [PRIMARY]
GO
--zjx2017-05-23增加货位表电子标签地址
If not exists (select 1 from dbo.syscolumns where id = object_id(N'[dbo].[location]') and name = 'ElecTronTagArea')
BEGIN 
  alter table location add ElecTronTagArea   varchar(100) DEFAULT ('') NOT NULL
end
go
--zjx2017-05-23增加货位表表控制器编号
If not exists (select 1 from dbo.syscolumns where id = object_id(N'[dbo].[location]') and name = 'ControlCode')
BEGIN 
  alter table location add ControlCode   varchar(100) DEFAULT ('') NOT NULL
end
go
--zjx2017-05-23增加货位表标签类型
If not exists (select 1 from dbo.syscolumns where id = object_id(N'[dbo].[location]') and name = 'TagTypeId')
BEGIN 
  alter table location add   TagTypeId  int    DEFAULT (0)  NOT NULL 
end
go
--zjx2017-05-24增加销售订单优先级字段
If not exists (select 1 from dbo.syscolumns where id = object_id(N'[dbo].[orderidx]') and name = 'PrioRity')
BEGIN 
  alter table orderidx add   PrioRity  int    DEFAULT (0)  NOT NULL 
end
go
--XXX.2017-06-02 拣货单增加复核台
if not exists (select * from dbo.syscolumns where id = object_id(N'[dbo].[GSPbillidx]') and  name = 'DeskId')
begin
  alter table dbo.GSPbillidx add
	DeskId int  not  NULL DEFAULT (0)
end
GO
--XXX.2017-06-02 拣货单增加暂存区
if not exists (select * from dbo.syscolumns where id = object_id(N'[dbo].[GSPbillidx]') and  name = 'HoldId')
begin
  alter table dbo.GSPbillidx add
	HoldId int  not  NULL DEFAULT (0)
end
GO
--XXX.2017-06-05 拣货单库区字段sa_id
if not exists (select * from dbo.syscolumns where id = object_id(N'[dbo].[GSPbillidx]') and  name = 'sa_id')
begin
  alter table dbo.GSPbillidx add
	sa_id int  not  NULL DEFAULT (0)
end
GO
--XXX.2017-06-02 拣货单gsp属性id,用来分配复核台判定,修改为双人审核勾选项
if not exists (select * from dbo.syscolumns where id = object_id(N'[dbo].[GSPbillidx]') and  name = 'doubleCheck')
begin
  alter table dbo.GSPbillidx add
	doubleCheck int  not  NULL DEFAULT (0)
end
GO

--商品资料增加本位码字段
If not exists (select 1 from dbo.syscolumns where id = object_id(N'[dbo].[products]') and name = 'StandardCode')
BEGIN 
  alter table products add StandardCode VARCHAR(60) DEFAULT ('') NOT NULL 
end
GO
--商品资料增加云编码字段
If not exists (select 1 from dbo.syscolumns where id = object_id(N'[dbo].[products]') and name = 'CloudCode')
BEGIN 
  alter table products add CloudCode VARCHAR(60) DEFAULT ('') NOT NULL 
end
GO
--往来单位增加云编码字段
If not exists (select 1 from dbo.syscolumns where id = object_id(N'[dbo].[clients]') and name = 'CloudCode')
BEGIN 
  alter table clients add CloudCode VARCHAR(60) DEFAULT ('') NOT NULL 
end
go
--zjx2017-06-05增加拣货单优先级字段
If not exists (select 1 from dbo.syscolumns where id = object_id(N'[dbo].[GSPbillidx]') and name = 'PrioRity')
BEGIN 
  alter table GSPbillidx add   PrioRity  int    DEFAULT (1)  NOT NULL 
end
go
--zjx2017-06-07增加暂存区表虚拟暂存区
If not exists (select 1 from dbo.syscolumns where id = object_id(N'[dbo].[WMSHold]') and name = 'ifHold')
BEGIN 
  alter table WMSHold add   ifHold  int    DEFAULT (0)  NOT NULL --是否虚拟暂存区 0代表否，1代表是
end
go
--zjx2017-06-07增加复核台双人审核
If not exists (select 1 from dbo.syscolumns where id = object_id(N'[dbo].[WMSCheckBaseInfo]') and name = 'DoubleCheck')
BEGIN 
  alter table WMSCheckBaseInfo add   DoubleCheck  int    DEFAULT (0)  NOT NULL --双人审核 0代表否，1代表是
end
go
--zjx2017-06-07增加暂存区增加最大单据数
If not exists (select 1 from dbo.syscolumns where id = object_id(N'[dbo].[WMSHold]') and name = 'MaxBillNum')
BEGIN 
  alter table WMSHold add   MaxBillNum  int    DEFAULT (0)  NOT NULL 
end
go
--zjx2017-06-07增加机构配送路线
If not exists (select 1 from dbo.syscolumns where id = object_id(N'[dbo].[company]') and name = 'RoadID')
BEGIN 
  alter table company add   RoadID  int  DEFAULT (0)  NOT NULL 
end
go
--XXX.2017-06-07  记录wms 错误日志
if not exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[WMSBillLog]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
BEGIN
	Create Table WMSBillLog
	(
  		[ID]    int IDENTITY(1,1) NOT NULL,
  		[billid]  int NOT NULL DEFAULT (0), --单据id
		[billtype]  int NOT NULL DEFAULT (0),--单据类型
		[BillNumber]  varchar(100) NOT NULL DEFAULT (''),--单据编号
		[ActionDate] datetime NOT NULL DEFAULT (0),--时间
		[Comment] varchar(100) NOT NULL DEFAULT (''),--备注
	 CONSTRAINT [PK_WMSBillLog] PRIMARY KEY CLUSTERED
	(
		[ID] ASC
	)
	) ON [PRIMARY]
END
GO
--云平台用户注册信息表
if not exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[CloudUser]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
CREATE TABLE [dbo].[CloudUser] 
(
  [Id]              [int]			IDENTITY(1,1)NOT NULL,  
  [cloudNo]         varchar(60)     DEFAULT ('') NOT NULL,  --云编码
  [clientName]      varchar(100)    DEFAULT ('') NOT NULL,  --单位名称
  [alias]           varchar(60)     DEFAULT ('') NOT NULL,  --简称
  [regAddress]      varchar(200)    DEFAULT ('') NOT NULL,  --注册地址
  [stockAddress]    varchar(200)    DEFAULT ('') NOT NULL,  --仓库地址
  [conPerson]       varchar(60)     DEFAULT ('') NOT NULL,  --企业负责人
  [legalPerson]     varchar(60)     DEFAULT ('') NOT NULL,  --法人
  [tel]             varchar(60)     DEFAULT ('') NOT NULL,  --联系电话
  [creditCode]      varchar(60)     DEFAULT ('') NOT NULL,  --社会信用代码
  [businessType]    varchar(60)     DEFAULT ('') NOT NULL,  --企业类型
  [taxNumber]       varchar(60)     DEFAULT ('') NOT NULL,  --税号
  [bank]            varchar(200)    DEFAULT ('') NOT NULL,  --开户行账号
  [custompro1]      varchar(200)    DEFAULT ('') NOT NULL,  --属性1
  [custompro2]      varchar(200)    DEFAULT ('') NOT NULL,  --属性2
  [custompro3]      varchar(200)    DEFAULT ('') NOT NULL,  --属性3
  [custompro4]      varchar(200)    DEFAULT ('') NOT NULL,  --属性4
  [custompro5]      varchar(200)    DEFAULT ('') NOT NULL   --属性5
 CONSTRAINT [PK_CloudUser] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)
) ON [PRIMARY]
GO
--zjx.2017-06-08  OtherStorehouse 增加md_oshid 字段，用来标识上传时候的对照关系,这个的主要作用是传输完成后也可以实现数据的排错和反查
if not exists (select * from dbo.syscolumns where id = object_id(N'[dbo].[OtherStorehouse]') and  name = 'md_oshid')
begin
  alter table dbo.OtherStorehouse add
	md_oshid int not  NULL DEFAULT (0)
end
GO


if not exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[DRProductMap]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
CREATE TABLE [dbo].DRProductMap (
  DRid INT IDENTITY(1,1) NOT NULL,
  p_id	INT NOT NULL,
  DR065 VARCHAR(3) NULL DEFAULT(''),        --费用类别 
  AKA080  VARCHAR(60) NULL,     --药品编号
  AKA060  VARCHAR(60) NULL,     --药品编码
  AKA061  VARCHAR(100) NULL,     --名称
  AKA062  VARCHAR(50) NULL,     --英文名称
  AKA063  VARCHAR(3) NULL,     --收货类别
  AKA081  VARCHAR(3) NULL,     --药品种类
  AKA064  VARCHAR(3) NULL,     --处方药标识
  AKA065  VARCHAR(3) NULL,     --报销类别
  AKA066  VARCHAR(30) NULL,     --助记码1
  AKA082  VARCHAR(30) NULL,     --助记码2
  AKA068  NUMERIC(10,4) NULL,     --最高价格
  AKA067  VARCHAR(20) NULL,     --单位
  AKC069  NUMERIC(10,4) NULL,     --自付比例
  AKA070  VARCHAR(50) NULL,     --剂型
  AKA071  NUMERIC(5,2) NULL,     --每次用量
  AKA072  VARCHAR(50) NULL,     --使用频次
  AKA073  VARCHAR(50) NULL,     --用法
  AKA074  VARCHAR(50) NULL,     --规格
  AKA083  NUMERIC(10,4) NULL,     --限定天数
  AKA021  VARCHAR(3) NULL,    --审批类型
  AAE011  VARCHAR(20) NULL,     --经办人
  AAE036  DATETIME NULL,  --经办日期
  AAE030  DATETIME NULL,  --开始日期
  AAE031  DATETIME NULL,  --终止日期
  SPECID  NUMERIC(10,4) NULL,     --医院特殊用药信息ID
  AKA077  VARCHAR(50) NULL,     --商品名
  AKA078  VARCHAR(20) NULL,     --商品名助记码
  AKA079  VARCHAR(100) NULL,    --药厂名称
  AKC321  VARCHAR(3) NULL,    --自制药品标志
  AAE013  VARCHAR(100) NULL,     --备注
  AAE101  VARCHAR(3) NULL,     --中心使用标志
  AKA032  VARCHAR(1) NULL,     --新旧目录标志
  AAE035  DATETIME NULL,  --变更日期
  AKA088  NUMERIC(10,4) NULL,     --最小单位零售价
  AKA076  VARCHAR(100) NULL,     --商品名
  AKA033  VARCHAR(3) NULL,     --民族用药
  AKA075  VARCHAR(40) NULL,     --通用名编码
	CONSTRAINT [PK_DRProductMap] PRIMARY KEY  CLUSTERED 
	(
		DRid
	)  ON [PRIMARY]   
) 
GO


-- drop table DRBillidx
if not exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[DRBillidx]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
CREATE TABLE [dbo].DRBillidx (
	DRbillid INT IDENTITY(1,1) NOT NULL,
	Billid INT NULL,--单据id
	YBMoney numeric(18,4) not null default 0,--医保账户
	TCMoney numeric(18,4) not null default 0,--统筹
	CashMoney numeric(18,4) not null default 0,--现金
	YBType int not null default 0,--11普通门诊 13门诊慢性病
	Billtype int not null default 0,--单据类型
	Billstates int not null default 0,--单据状态
	BillTotal numeric(18,4) not null default 0,--单据金额
	SettleFlag varchar(10) not null default '',--结算状态  
	billnumber varchar(30) not null default '',--单据编号
	e_name varchar(30) not null default '',--姓名
	EmpNum varchar(20) not null default '',--个人编号
	DR01Num varchar(20) not null default '',--登记流水号
	DR06Num varchar(20) not null default '',--处方流水号
	DR10Num varchar(20) not null default '',--结算流水号
	InvoiceNo VARCHAR(50) NOT NULL DEFAULT '',--发票号
	billdate datetime not null default getdate(),--时间
	CONSTRAINT [PK_DRBillidx] PRIMARY KEY  CLUSTERED 
	(
		DRbillid
	)  ON [PRIMARY]   
) 
GO

-- drop table DRYDBillidx
if not exists (select 1 from dbo.sysobjects where id = object_id(N'[dbo].[DRYDBillidx]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
CREATE TABLE [dbo].DRYDBillidx (
	DRYDbillid INT IDENTITY(1,1) NOT NULL,
    OperatorNo INT NOT NULL DEFAULT 0,    --操作员编号,=employees.emp_id
    BATCHNO    VARCHAR(30) NOT NULL DEFAULT '',    --业务周期号
    SignStatus   INT NOT NULL DEFAULT '',    --状态 0签到 1签退
    SignInDate   DATETIME NOT NULL DEFAULT GETDATE(),    --签到时间
    SignOutDate  DATETIME NOT NULL DEFAULT GETDATE(),    --签退时间
    SignInNo     VARCHAR(80) NOT NULL DEFAULT '',    --签到流水号
    SignOutNo    VARCHAR(80) NOT NULL DEFAULT '',    --签退流水号
	CONSTRAINT [PK_DRYDBillidx] PRIMARY KEY  CLUSTERED 
	(
		DRYDbillid
	)  ON [PRIMARY]   
) 
GO



If not exists (select 1 from dbo.syscolumns where id = object_id(N'[dbo].[DRBillidx]') and  name = 'OperatorNo')
begin
  alter table dbo.DRBillidx add
	OperatorNo INT NOT NULL DEFAULT (0)
END

If not exists (select 1 from dbo.syscolumns where id = object_id(N'[dbo].[DRBillidx]') and  name = 'BATCHNO')
begin
  alter table dbo.DRBillidx add
	BATCHNO VARCHAR(30) NOT NULL DEFAULT ('')
END

GO 

If not exists (select 1 from dbo.syscolumns where id = object_id(N'[dbo].[DRBillidx]') and  name = 'TCAreaNo')
begin
  alter table dbo.DRBillidx add
	TCAreaNo VARCHAR(10) NOT NULL DEFAULT ('')
END

GO 

If not exists (select 1 from dbo.syscolumns where id = object_id(N'[dbo].[DRBillidx]') and  name = 'DR01Num')
begin
  alter table dbo.DRBillidx ALTER COLUMN
	DR01Num VARCHAR(50)
END

GO 

If not exists (select 1 from dbo.syscolumns where id = object_id(N'[dbo].[DRBillidx]') and  name = 'DR06Num')
begin
  alter table dbo.DRBillidx ALTER COLUMN
	DR06Num VARCHAR(50)
END

GO 

If not exists (select 1 from dbo.syscolumns where id = object_id(N'[dbo].[DRBillidx]') and  name = 'DR10Num')
begin
  alter table dbo.DRBillidx ALTER COLUMN
	DR10Num VARCHAR(50)
END

GO 


if not exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[DRBillidxTemp]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
CREATE TABLE [dbo].DRBillidxTemp (
	DRbillid INT IDENTITY(1,1) NOT NULL,
	OperatorNo INT NOT NULL DEFAULT (0),--操作员编号
	BATCHNO VARCHAR(30) NOT NULL DEFAULT (''),--业务周期号
	SendSerialNo VARCHAR(50) NOT NULL DEFAULT (''),--发送方流水号
	DR10Num varchar(20) not null default '',--接收方流水号,结算流水号
	EmpNum varchar(20) not null default '',--个人编号
	DRRecZYNo VARCHAR(50) NOT NULL DEFAULT '',--住院号
	InvoiceNo VARCHAR(50) NOT NULL DEFAULT '',--单据号,发票号
	Billtype int not null default 0,--单据类型
	SettleFlag varchar(10) not null default '',--结算状态
	TCAreaNo VARCHAR(10) NOT NULL DEFAULT '',--统筹区号
	BillTotal numeric(18,4) not null default 0,--单据金额
	CashMoney numeric(18,4) not null default 0,--现金
	YBMoney numeric(18,4) not null default 0,--医保账户
	TCMoney numeric(18,4) not null default 0,--统筹
	DRZYNo VARCHAR(50) NOT NULL DEFAULT '',--上传住院号
	billdate datetime not null default getdate(),--时间
	CONSTRAINT [PK_DRBillidxTemp] PRIMARY KEY  CLUSTERED 
	(
		DRbillid
	)  ON [PRIMARY]   
) 
GO

if not exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[DRYDEmpinfo]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
CREATE TABLE [dbo].DRYDEmpinfo (
	DRbillid INT IDENTITY(1,1) NOT NULL,
	EmpNum varchar(20) not null default '',--个人编号
	AAC003 varchar(20) not null default '',--姓名
    AKC020 varchar(20) not null default '',--医保卡号
    YAB003 varchar(10) not null default '',--参保地统筹区编码
	billdate datetime not null default getdate(),--时间
	CONSTRAINT [PK_DRYDEmpinfo] PRIMARY KEY  CLUSTERED 
	(
		DRbillid
	)  ON [PRIMARY]   
) 
GO

If not exists (select 1 from dbo.syscolumns where id = object_id(N'[dbo].[DRBillidx]') and  name = 'CurYBMoney')
begin
  alter table dbo.DRBillidx add
	CurYBMoney NUMERIC(18,4) NOT NULL DEFAULT (0)
END

GO 

if not exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[DRQRCode]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
CREATE TABLE [dbo].DRQRCode (
	QRbillid INT IDENTITY(1,1) NOT NULL,
	billid INT NOT NULL,--零售单id
	QRCode varchar(2000) not null default '',--二维码内容
	billdate datetime not null default getdate(),--时间
	CONSTRAINT [PK_DRQRCode] PRIMARY KEY  CLUSTERED 
	(
		QRbillid
	)  ON [PRIMARY]   
) 
GO

If not exists (select 1 from dbo.syscolumns where id = object_id(N'[dbo].[DRBillidx]') and  name = 'DRDDNo')
begin
  alter table dbo.DRBillidx add
	DRDDNo VARCHAR(30) NOT NULL DEFAULT ('')
END

GO 

If not exists (select 1 from dbo.syscolumns where id = object_id(N'[dbo].[DRYDEmpinfo]') and  name = 'AKC300')
begin
  alter table dbo.DRYDEmpinfo add
	AKC300 VARCHAR(3) NOT NULL DEFAULT ('')
END

GO 


-- drop table DRTCArea
if not exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[DRTCArea]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
CREATE TABLE [dbo].DRTCArea (
	billid INT IDENTITY(1,1) NOT NULL,
	TCAreaNo varchar(10) NOT NULL,--统筹区编号
	TCAreaName varchar(40) not null default '',--统筹区名称
	TCSF VARCHAR(10) NOT NULL DEFAULT '',--统筹区省份
	CONSTRAINT [PK_DRTCArea] PRIMARY KEY  CLUSTERED 
	(
		TCSF,TCAreaNo
	)  ON [PRIMARY]   
) 
GO

-- drop table GSTransNum
if not exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[GSTransNum]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
CREATE TABLE [dbo].GSTransNum (
	billid INT IDENTITY(1,1) NOT NULL,
	GSDDNo VARCHAR(30) NOT NULL DEFAULT '',--定点编号
	TransNum INT not null default 0,--流水号
	ReadDate01 DATETIME NOT NULL DEFAULT '1900-01-01',--上次药品目录下载时间
	ReadDate02 DATETIME NOT NULL DEFAULT '1900-01-01',--诊疗项目信息
	ReadDate03 DATETIME NOT NULL DEFAULT '1900-01-01',--医疗服务设施信息
	ReadDate04 DATETIME NOT NULL DEFAULT '1900-01-01',--费用类别信息
	ReadDate05 DATETIME NOT NULL DEFAULT '1900-01-01',--病种信息
	NoticeDate DATETIME NOT NULL DEFAULT '1900-01-01',--通知信息
	TGSP_id INT NOT NULL DEFAULT 0,--取消对照处理截止对照表id
	CONSTRAINT [PK_GSTransNum] PRIMARY KEY  CLUSTERED 
	(
		GSDDNo
	)  ON [PRIMARY]   
) 
GO

if not exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[GSProducts]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
CREATE TABLE [dbo].GSProducts (
	GSP_id INT IDENTITY(1,1) NOT NULL,
	serial_number VARCHAR(20) NOT NULL DEFAULT '',--药品编码
    name VARCHAR(100) NOT NULL DEFAULT '',--通用中文名称
    EngName VARCHAR(300) NOT NULL DEFAULT '',--英文名称
    SFLB VARCHAR(3) NOT NULL DEFAULT '',--收费类别
    OTC VARCHAR(3) NOT NULL DEFAULT '',--处方药标志
    SFXMDJ VARCHAR(3) NOT NULL DEFAULT '',--收费项目等级
    pinyin VARCHAR(50) NOT NULL DEFAULT '',--拼音助记码
    MedTypeUnit VARCHAR(20) NOT NULL DEFAULT '',--药品剂量单位
    YNZJBS VARCHAR(3) NOT NULL DEFAULT '',--院内制剂标志
    GSDDNo VARCHAR(20) NOT NULL DEFAULT '',--定点医疗机构编号
    IsSP VARCHAR(3) NOT NULL DEFAULT '',--是否需要审批标志
    MedType VARCHAR(3) NOT NULL DEFAULT '',--剂型
    SYPC VARCHAR(100) NOT NULL DEFAULT '',--使用频次
    YF VARCHAR(200) NOT NULL DEFAULT '',--用法
    WBZJM VARCHAR(50) NOT NULL DEFAULT '',--五笔助记码
    UnitName VARCHAR(20) NOT NULL DEFAULT '',--单位
    specification VARCHAR(100) NOT NULL DEFAULT '',--规格
    price VARCHAR(14) NOT NULL DEFAULT '',--参考价格
    FactoryName VARCHAR(100) NOT NULL DEFAULT '',--药厂名称
    permitcode VARCHAR(100) NOT NULL DEFAULT '',--国药准字
    BeginDate VARCHAR(14) NOT NULL DEFAULT '',--开始时间
    EndDate VARCHAR(14) NOT NULL DEFAULT '',--终止时间
    Memo VARCHAR(100) NOT NULL DEFAULT '',--备注
    ZDYCode VARCHAR(10) NOT NULL DEFAULT '',--自定义码
    GJCode VARCHAR(20) NOT NULL DEFAULT '',--国家目录编码
    XZSYFW VARCHAR(300) NOT NULL DEFAULT '',--限制使用范围
    ZXYSDJ VARCHAR(3) NOT NULL DEFAULT '',--最小医师等级
    ZXYYDJ VARCHAR(3) NOT NULL DEFAULT '',--最小医院等级
    GSSYBZ VARCHAR(3) NOT NULL DEFAULT '',--工伤使用标志
    SYSYBZ VARCHAR(3) NOT NULL DEFAULT '',--生育使用标志
    YLSYBZ VARCHAR(3) NOT NULL DEFAULT '',--基本医疗使用标志
    JMSYBZ VARCHAR(3) NOT NULL DEFAULT '',--居民使用标识
    LXSYBZ VARCHAR(3) NOT NULL DEFAULT '',--离休使用标识
    YbDate VARCHAR(14) NOT NULL DEFAULT '',--社保经办时间
    YXBZ VARCHAR(3) NOT NULL DEFAULT '',--有效标识
    TCAreaNo VARCHAR(6) NOT NULL DEFAULT '',--统筹区编码
    CONSTRAINT [PK_GSProducts] PRIMARY KEY CLUSTERED 
	(
		GSP_id
	)  ON [PRIMARY]   
) 
GO

-- DROP TABLE GSProductMap
if not exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[GSProductMap]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
CREATE TABLE [dbo].GSProductMap (
    billid INT IDENTITY(1,1) NOT NULL,--自增长id 
    p_id INT NOT NULL,--药易通p_id
	GSP_id INT NOT NULL,--GSProducts.GSP_id
	GSDDNo VARCHAR(30) NOT NULL,--定点编号
	GSSFLB VARCHAR(3) NOT NULL,--用户对的收费类别
	IsUp BIT NOT NULL DEFAULT 0,--是否成功上传医保中心
	IsDel INT NOT NULL DEFAULT 0,--1 取消对照 2同一商品再次上传时存在多条上传记录,自动删除标识 3取消对照过渡标识 4同一商品上传时存在多条未传记录,自动删除标识 5已上传过渡标识
	serial_number VARCHAR(20) NOT NULL,--社保中心编码
	serial_number_y VARCHAR(26) NOT NULL,--药易通编码
	name_Y VARCHAR(100) NOT NULL,--药易通名称
	UnitName VARCHAR(20) NOT NULL,--社保中心单位
	specification VARCHAR(100) NOT NULL,--社保中心规格
    CONSTRAINT [PK_GSProductMap] PRIMARY KEY  CLUSTERED 
	(
		billid
	)  ON [PRIMARY]   
) 
GO


-- DROP TABLE GSEmployeesMap
if not exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[GSEmployeesMap]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
CREATE TABLE [dbo].GSEmployeesMap (
    emp_id INT NOT NULL,--医师编号
	name VARCHAR(50) NOT NULL,--医师姓名
	IdCard VARCHAR(20) NOT NULL,--医师身份证号码
	DeptName VARCHAR(40) NOT NULL,--科室名称
	OTC VARCHAR(3) NOT NULL,--医保处方权
	XZZW VARCHAR(3) NOT NULL,--行政职务	
	XSZW VARCHAR(20) NOT NULL,--学术职务
	Phone VARCHAR(20) NOT NULL,--联系电话
	ZYLB VARCHAR(3) NOT NULL,--执业类别
	ZYNo VARCHAR(20) NOT NULL,--执业证书编码
	ZYDate VARCHAR(8) NOT NULL,--执业证书注册日期
	LCZY VARCHAR(500) NOT NULL,--临床专业
	Memo VARCHAR(500) NOT NULL,--备注
	YSJB VARCHAR(3) NOT NULL,--医师级别
	IsUp BIT NOT NULL DEFAULT 0,--是否上传
    CONSTRAINT [PK_GSEmployeesMap] PRIMARY KEY  CLUSTERED 
	(
		emp_id
	)  ON [PRIMARY]   
) 
GO


-- DROP TABLE GSNotice
if not exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[GSNotice]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
CREATE TABLE [dbo].GSNotice (
    billid INT IDENTITY(1,1) NOT NULL,
	billnumber VARCHAR(16) NOT NULL,--通知编号
	Title VARCHAR(100) NOT NULL,--通知主题
	Memo VARCHAR(1000) NOT NULL,--通知详细内容
	BeginDate DATETIME NOT NULL,--通知开始日期	
	EndDate DATETIME NOT NULL,--通知终止日期
	BillDate DATETIME NOT NULL,--通知操作时间
    CONSTRAINT [PK_GSNotice] PRIMARY KEY  CLUSTERED 
	(
		billid
	)  ON [PRIMARY]   
) 
GO

-- DROP TABLE GSOTCStr
if not exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[GSOTCStr]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
CREATE TABLE [dbo].GSOTCStr (
    billid INT NOT NULL,
	OTCStr TEXT NOT NULL,--结算处方入参
	OTCEmp VARCHAR(20) NOT NULL,--处方医师
	GSLive VARCHAR(3) NOT NULL,--联机标识 0脱机 1联机
    CONSTRAINT [PK_GSOTCStr] PRIMARY KEY  CLUSTERED 
	(
		billid
	)  ON [PRIMARY]   
) 
GO
--zjx--2017-06-13--修改字段的长度
alter table location alter column alreadyPgNum [numeric](25,8)   not null
go
alter table location alter column stockpilePgNum [numeric](25,8)   not null
go
alter table location alter column beginrow [numeric](25,8)   not null
go
alter table location alter column begintier [numeric](25,8)   not null
go
alter table location alter column begincol [numeric](25,8)   not null
go
alter table location alter column endrow [numeric](25,8)   not null
go
alter table location alter column endtier [numeric](25,8)   not null
go
alter table location alter column endcol [numeric](25,8)   not null
go
alter table WMSMedtype alter column Quantity [numeric](25,8)   not null
go
alter table WMSCheckBaseInfo alter column WorkNum [numeric](25,8)   not null
go
alter table WMSCheckBaseInfo alter column BillNum [numeric](25,8)   not null
go
alter table WMSCheckBaseInfo alter column PgNum [numeric](25,8)   not null
go

--XXX.2017-06-12 拣货、验收流程增加pda标识
If not exists (select 1 from dbo.syscolumns where id = object_id(N'[dbo].[GSPbillidx]') and name = 'IsPDA')
BEGIN 
  alter table GSPbillidx add  IsPDA  int DEFAULT (0)  NOT NULL 
end
GO
--XXX.2017-06-13  整货标签表
if not exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[GSPWholeTag]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
CREATE TABLE [dbo].GSPWholeTag (
	id INT IDENTITY(1,1) NOT NULL,
    billid INT NOT NULL,			--单据id
    smb_id INT NOT NULL,			--单据明细id
    billtype INT NOT NULL,			--类型  0整件，1拼箱 
    qty numeric(25,8) NOT NULL,			--数量（标签包含数量）
    y_id INT NOT NULL,
    idex int NOT NULL,				--序号
    Tag  varchar(100) not null default(''),       --'ZHT'+ 时间'170613' + 默认8位来计数'00000000' --标签
    CONSTRAINT [PK_GSPWholeTag] PRIMARY KEY  CLUSTERED 
	(
		id
	)  ON [PRIMARY]   
) 
GO

