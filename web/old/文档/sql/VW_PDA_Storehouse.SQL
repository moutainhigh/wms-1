SET QUOTED_IDENTIFIER OFF 
GO
SET ANSI_NULLS ON 
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[VW_PDA_Storehouse]') and OBJECTPROPERTY(id, N'IsView') = 1)
drop view [dbo].[VW_PDA_Storehouse]
GO



CREATE VIEW dbo.[VW_PDA_Storehouse]
AS
SELECT 
  ISNULL(P.[Serial_number] ,'') AS [Code],  --商品编码
  ISNULL(P.[Name]          ,'') AS [Pname],  --商品名称 
  ISNULL(P.[Alias]         ,'') AS [Alias],   --商品通用名
  ISNULL(P.[Standard]      ,'') AS [Standard],  --商品通用名规格  
  ISNULL(P.[Makearea]      ,'') AS [Makearea],   --产地
  p.factory ,                                     --生产厂家
  ISNULL(P.[comment]       ,'') AS [comment],	--商品备注    
  ISNULL(P.[Permitcode]    ,'') AS [Permitcode],  --批准文号        
  ISNULL(C.[Name]          ,'') AS [Cname],   --供应商
  sh.location_id,			                  --货位id    
  ISNULL(L.[Loc_name]      ,'') AS [Locname],  --货位名称
  sh.s_id,                                     --仓库id
  ISNULL(S.[Name]          ,'') AS [Sname],--仓库名称
  isnull(s.[serial_number], '') as [scode],   --仓库编码
  sh.p_id,                                    --商品ID
  ISNULL(U1.[Name]         ,'') AS [Name1],   --单位名称
  ISNULL(M.mt_name       ,'') AS [MedName],  --剂型
  sh.y_id,                                   --机构id 
  ISNULL(Y.[name]          ,'') AS Yname,   --机构名称
  sh.quantity,                               --数量
  sh.batchno,                                --批次号
  sh.validdate,                             --有效期至
  sh. makedate,                              --生产日期
  sh.storehouse_id                          --库存表唯一id
  
     
FROM 
  StoreHouse SH
  LEFT JOIN (select * from price where unittype=1)   pic ON SH.[p_id]=pic.[p_id]
  LEFT JOIN Products P  ON SH.[P_ID]=P.[Product_ID]
  LEFT JOIN Clients  C  ON SH.[Supplier_ID]=C.[Client_ID]
  LEFT JOIN Storages S  ON SH.[S_ID]=S.[Storage_ID]
  LEFT JOIN Location L  ON SH.[Location_ID]=L.[Loc_ID]
  LEFT JOIN Unit     U1 ON P.[Unit1_ID]=U1.[Unit_ID]
  LEFT JOIN Unit     U2 ON P.[Unit2_ID]=U2.[Unit_ID]
  LEFT JOIN Unit     U3 ON P.[Unit3_ID]=U3.[Unit_ID]
  LEFT JOIN Unit     U4 ON P.[Unit4_ID]=U4.[Unit_ID]
  left   join medtype m on p.medtype = m.mt_id
  left join company  y on sh.y_id = y.company_id
	 
 
GO
SET QUOTED_IDENTIFIER OFF 
GO
SET ANSI_NULLS ON 
GO

