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

//新增账套表
IF not exists (select 1 from dbo.sysobjects where id = object_id(N'[dbo].[pda_account_set]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
begin
CREATE TABLE [dbo].pda_account_set (
id INT PRIMARY KEY ,
db_name VARCHAR(64) NOT NULL ,
account_name VARCHAR(64) NOT NULL
) ON [PRIMARY]
end
GO
