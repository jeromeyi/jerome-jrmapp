cls
rem please set the env JAVA_HOME before run this bat file
rem delete alia tomcat if it is existed
keytool -delete -alias tomcatsso -keystore "%JAVA_HOME%/jre/lib/security/cacerts" -storepass changeit
keytool -delete -alias tomcatsso -storepass changeit
rem list all alias in the cacerts
keytool -list -keystore "%JAVA_HOME%/jre/lib/security/cacerts" -storepass changeit

rem generator a key
keytool -genkey -keyalg RSA -alias tomcatsso -dname "cn=jerome-PC" -storepass changeit
keytool -export -alias tomcatsso -file "%java_home%/jre/lib/security/tomcatsso.crt" -storepass changeit
keytool -import -alias tomcatsso -file "%java_home%/jre/lib/security/tomcatsso.crt" -keystore "%java_home%/jre/lib/security/cacerts" -storepass changeit
rem list all alias in the cacerts
keytool -list -keystore "%JAVA_HOME%/jre/lib/security/cacerts" -storepass changeit