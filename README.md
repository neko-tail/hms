## 部署

### Windows

安装 [Scoop](https://github.com/ScoopInstaller/Scoop#installation)（在非管理员的 PowerShell 窗口中运行）

```powershell
Set-ExecutionPolicy -ExecutionPolicy RemoteSigned -Scope CurrentUser
Invoke-RestMethod -Uri https://get.scoop.sh | Invoke-Expression
```

使用 Scoop 安装基础环境（openjdk 在 java bucket 下，需要先添加 java）

```powershell
scoop install sudo
sudo scoop install 7zip git openssh --global
scoop bucket add java
scoop install java/openjdk maven mariadb redis
```

启动 MariaDB 和 Redis 服务（前台运行，各占用一个终端窗口）

下面的命令启动的 MariaDB 服务可以使用 root 账号登录，密码为空

```powershell
mysqld --standalone
redis-server
```

## 运行

使用 Git 克隆项目

```powershell
git clone https://github.com/neko-tail/hms.git
cd hms
```

可使用 `sql/hms.sql` 初始化数据库

```powershell
mysql -u root -p < sql/hms.sql
```

打包并运行，访问 http://localhost:8080/swagger-ui/index.html 查看 API 文档

```powershell
mvn clean package
java -jar target/hms.jar
```
