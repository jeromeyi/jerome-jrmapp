cls
rem please set the env JAVA_HOME before run this bat file
rem delete alia tomcat if it is existed
keytool -delete -alias tomcatsso -keystore "%JAVA_HOME%/jre/lib/security/cacerts" -storepass changeit
keytool -delete -alias tomcatsso -storepass changeit
rem（注释： 清除系统中可能存在的名字为tomcatsso 的同名证书） 
rem list all alias in the cacerts
keytool -list -keystore "%JAVA_HOME%/jre/lib/security/cacerts" -storepass changeit
rem（注释： 列出系统证书仓库中存在证书名称列表） 
rem generator a key
keytool -genkey -keyalg RSA -alias tomcatsso -dname "cn=jerome-PC" -storepass changeit
rem（注释：指定使用RSA算法，生成别名为tomcatsso的证书，存贮口令为changeit，证书的DN为"cn=linly" ，这个DN必须同当前主机完整名称一致哦，切记！！！）
rem export the key
keytool -export -alias tomcatsso -file "%java_home%/jre/lib/security/tomcatsso.crt" -storepass changeit
rem（注释： 从keystore中导出别名为tomcatsso的证书，生成文件tomcatsso.crt）
rem import into trust cacerts 
keytool -import -alias tomcatsso -file "%java_home%/jre/lib/security/tomcatsso.crt" -keystore "%java_home%/jre/lib/security/cacerts" -storepass changeit
rem（注释：将tomcatsso.crt导入jre的可信任证书仓库。注意，安装JDK是有两个jre目录，一个在jdk底下，一个是独立的jre，这里的目录必须同Tomcat使用的jre目录一致，否则后面Tomcat的HTTPS通讯就找不到证书了） 
rem list all alias in the cacerts
keytool -list -keystore "%JAVA_HOME%/jre/lib/security/cacerts" -storepass changeit
rem（注释：列出jre可信任证书仓库中证书名单，验证先前的导入是否成功，如果导入成功，应该在列表中能找到tomcatsso这个别名，如下图）