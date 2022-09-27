# Hibernate-Spring-

## 使用Hiberbate + Spring 將所負責部分進行改寫
負責部分為以下這些套件:coupontype 、 cart 、 memcoupon 、 orderlist、orderdetail
## Hiberbate
1.設定pom.xml()，在<dependencies> 中加⼊Hibernate Library相關<dependency>
2.設定Hibernate相關組態檔(hibernate.cfg.xml)
```
<session-factory>
		<property name="hibernate.connection.datasource">java:comp/env/jdbc/boardgame</property>
		<property name="hibernate.current_session_context_class">thread</property>
		
		<property name="hibernate.dialect">org.hibernate.dialect.MySQL8Dialect</property>
		<property name="hibernate.format_sql">true</property>
		<property name="hibernate.show_sql">true</property>
		
		<mapping class="com.coupontype.model.CouponTypeVO"/>
		<mapping class="com.memcoupon.model.MemCouponVO"/>
    ...略
</session-factory>
  ```
3. (純hibernate環境中)依版本加入HibernateUtil(spring環境下則不用)
4. 將VO 加上映射相關Annotation 以及Association(一對多，多對一)相關設定
5. DAO層，主要以hibernate的native SQL 進行改寫

## Spring
1. 設定pom.xml()，在<dependencies> 中加⼊Spring Library相關 <dependency>
2. Java組態 + Annotation組態
### 設定託管(IoC)
@Repository 於DAO層

@Service 於service層
### 設定注⼊(DI)
@Autowired 於service層
```
@Autowired
private XXXDao dao;
```

### 新建核⼼組態類別
```java=
@Configuration
@ComponentScan("com.*.model") // 掃描@Component @Controller @Service @Repository
@EnableTransactionManagement
public class SpringConfig {
	@Bean
	public DataSource dataSource() throws IllegalArgumentException, NamingException {
		JndiObjectFactoryBean bean = new JndiObjectFactoryBean();
		bean.setResourceRef(true);
		bean.setJndiName("jdbc/boardgame");
		bean.afterPropertiesSet();
		return (DataSource) bean.getObject();
	}

	@Bean
	public SessionFactory sessionFactory() throws IllegalArgumentException, NamingException {
		return new LocalSessionFactoryBuilder(dataSource())
				.scanPackages("com.*.model") // 掃描@Entity
				.addProperties(getHibernateProperties())
				.buildSessionFactory();
	}

	@Bean
	public TransactionManager transactionManager() throws IllegalArgumentException, NamingException {
		return new HibernateTransactionManager(sessionFactory());
	}

	private Properties getHibernateProperties() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.dialect", MySQL8Dialect.class.getName());
		properties.setProperty("hibernate.show_sql", "true");
		properties.setProperty("hibernate.format_sql", "true");
		properties.setProperty("hibernate.current_session_context_class", SpringSessionContext.class.getName());
		return properties;
	}

}
```
### 指定Spring組態類別

```
	<listener>
		<listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
	</listener>


	<context-param>
		<param-name>contextClass</param-name>
		<param-value>org.springframework.web.context.support.AnnotationConfigWebApplicationContext</param-value>
	</context-param>

	<context-param>
		<param-name>contextConfigLocation</param-name>
		<param-value>core.config.SpringConfig</param-value>
	</context-param>
```
