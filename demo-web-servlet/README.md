# 要点
- 教程：http://www.javaboy.org/2020/0703/springsecurity-httpservletrequest-login.html
## 基于tomcat配置文件登录
- 此项目演示了servlet自带的登录及退出api
- tomcat配置文件修改：${tomcat_home}/conf/tomcat-users.xml，增加配置如下
```xml
<tomcat-users>
    <role rolename="admin"/>
    <user username="justing" password="123456" roles="admin"/>
</tomcat-users>
```
## 基于数据库登录
- 修改${tomcat_home}/conf/tomcat-server.xml文件，如下
```xml
<Realm className="org.apache.catalina.realm.LockOutRealm">
  <Realm className="org.apache.catalina.realm.JDBCRealm" debug="99"
        driverName="com.mysql.jdbc.Driver"
        connectionURL="jdbc:mysql://localhost:3306/basiclogin"
        connectionName="root" connectionPassword="123"
        userTable="user" userNameCol="username"    
        userCredCol="password"
        userRoleTable="role" roleNameCol="role_name" />
</Realm>
```
- **注意：要把mysql的jar包放入${tomcat_home}/lib目录下**
- 还有web.xml中的配置