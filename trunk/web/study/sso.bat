cls
rem please set the env JAVA_HOME before run this bat file
rem delete alia tomcat if it is existed
keytool -delete -alias tomcatsso -keystore "%JAVA_HOME%/jre/lib/security/cacerts" -storepass changeit
keytool -delete -alias tomcatsso -storepass changeit
rem ���ϵͳ�п��ܴ��ڵ�����Ϊtomcatsso ��ͬ��֤��
rem list all alias in the cacerts
keytool -list -keystore "%JAVA_HOME%/jre/lib/security/cacerts" -storepass changeit
rem �г�ϵͳ֤��ֿ��д���֤�������б� 
rem generator a key
keytool -genkey -keyalg RSA -alias tomcatsso -dname "cn=jerome-PC" -storepass changeit
rem ָ��ʹ��RSA�㷨�����ɱ���Ϊtomcatsso��֤�飬��������Ϊchangeit��֤���DNΪ"cn=linly" �����DN����ͬ��ǰ������������һ��Ŷ���м�
rem export the key
keytool -export -alias tomcatsso -file "%java_home%/jre/lib/security/tomcatsso.crt" -storepass changeit
rem ��keystore�е�������Ϊtomcatsso��֤�飬�����ļ�tomcatsso.crt
rem import into trust cacerts 
keytool -import -alias tomcatsso -file "%java_home%/jre/lib/security/tomcatsso.crt" -keystore "%java_home%/jre/lib/security/cacerts" -storepass changeit
rem ��tomcatsso.crt����jre�Ŀ�����֤��ֿ⡣ע�⣬��װJDK��������jreĿ¼��һ����jdk���£�һ���Ƕ�����jre�������Ŀ¼����ͬTomcatʹ�õ�jreĿ¼һ�£��������Tomcat��HTTPSͨѶ���Ҳ���֤����
rem list all alias in the cacerts
keytool -list -keystore "%JAVA_HOME%/jre/lib/security/cacerts" -storepass changeit
rem �г�jre������֤��ֿ���֤����������֤��ǰ�ĵ����Ƿ�ɹ����������ɹ���Ӧ�����б������ҵ�tomcatsso�������������ͼ