# openejb-hibernate-mysql
tutorial  openejb-hibernate-mysql

나는 openejb와 hibernate 그리고 mysql을 이용하고 싶었다.
remoteEJB를 이용해서 원격호출을 하고 
JPA를 이용해서 mysql에 데이터를 삽입,수정,조회를 하고 싶었다.

이 간단한 예제는 내가 처음에 openEJB,hibernate,mysql 관련 설정을 하면서 알게된 
persistence.xml 설정 관련 내용이다.

처음에 나는 persistence.xml 에 db정보가 적혀있으면 openejb에서 읽어서 그 DB에 접근할것이라고 예상했다.
하지만 현 버전에서 

    <persistence-unit name="nknkmysql" transaction-type="JTA">
        <provider>org.hibernate.ejb.HibernatePersistence</provider>
        <jta-data-source>nknkMySql</jta-data-source>
        <non-jta-data-source>nknkMysqlUnmanaged</non-jta-data-source>
        <class>test.jpa.Member</class>
        <properties>
  
            <property name="hibernate.connection.url" value="jdbc:mysql://localhost:3306/nknk" />
            <property name="hibernate.connection.driver_class" value="com.mysql.jdbc.Driver" />
            <property name="hibernate.dialect" value="org.hibernate.dialect.MySQLInnoDBDialect" />
            <property name="hibernate.connection.username" value="root" />
            <property name="hibernate.connection.password" value="xxxxxx" />
  
            <property name="hibernate.show_sql" value="false" />
            <property name="hibernate.format_sql" value="true" />
            <property name="hibernate.flushMode" value="FLUSH_AUTO" />
            <property name="hibernate.hbm2ddl.auto" value="none" />
        </properties>
    </persistence-unit>


db연결문자열은 있으나 마나하다.
영향을 받지 않는다.

openejb의 설치경로에

/home/jijs/apache-openejb-4.7.2/conf  폴더에 openejb.xml 파일을 열어 아래 코드를 추가한다.
    
    <Resource id="nknkMySql" type="DataSource">
      JdbcDriver  com.mysql.jdbc.Driver
      JdbcUrl   jdbc:mysql://localhost:3306/nknk
      UserName   root
      Password  xxxxxx
      JtaManaged true
    </Resource>

필요 없는 것은 삭제하고 정리하면


    <persistence-unit name="nknkmysql" transaction-type="JTA">
        <provider>org.hibernate.ejb.HibernatePersistence</provider>
        <jta-data-source>nknkMySql</jta-data-source>
        <non-jta-data-source>nknkMysqlUnmanaged</non-jta-data-source>
        <properties>
            <property name="hibernate.show_sql" value="false" />
            <property name="hibernate.format_sql" value="true" />
            <property name="hibernate.flushMode" value="FLUSH_AUTO" />
            <property name="hibernate.hbm2ddl.auto" value="none" />
        </properties>
    </persistence-unit>
결국 중요한 것은

 <jta-data-source>nknkMySql</jta-data-source>
 이것이 되겠다.
 
 Junit으로 테스트를 하면 연결이 안된다. 
 junit은 실행하고 있는 openEJB로 움직이는 것이 아니기 때문이다.
그래서 Junit으로 단위 테스트를 할 때는 소스에 db정보를 박아줘야한다.

        final Properties p = new Properties();
        p.put("movieDatabase", "new://Resource?type=DataSource");
        p.put("movieDatabase.JdbcDriver", "com.mysql.jdbc.Driver");
        p.put("movieDatabase.JdbcUrl", "jdbc:mysql://localhost:3306/nknk");
        p.put("movieDatabase.username", "root");
        p.put("movieDatabase.password", "xxxxxx");
        ejbContainer = EJBContainer.createEJBContainer(p);

더자세한 것은 소스를 보면서 참고 바랍니다.
