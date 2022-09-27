# Hibernate-Spring-
## 專案負責部分pdf介紹[]([https://](https://drive.google.com/file/d/1o_waNAihJx0Qv4za-GUu9IJDhaYL5xXG/view?usp=sharing))
## 使用Hiberbate + Spring 將所負責部分進行改寫
負責部分為以下套件:coupontype 、 cart 、 memcoupon 、 orderlist、orderdetail
## Hiberbate
### 1.設定pom.xml()，在<dependencies> 中加⼊Hibernate Library相關<dependency>
### 2.設定Hibernate相關組態檔(hibernate.cfg.xml)
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
### 3. (純hibernate環境中)依版本加入HibernateUtil(spring環境下則不用)
### 4. 將VO 加上映射相關Annotation 以及Association(一對多，多對一)相關設定
```
@Entity
@Table(name = "memcoupon", catalog = "boardgame")
public class MemCouponVO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer coupNo;
	@Column
	private Integer memID;
	@Column
	private Integer coupTypeNo;
	@Column (insertable = false)
	private Integer coupStatus;
	
	
	@ManyToOne
	@JoinColumn(name = "coupTypeNo", insertable = false, updatable = false)
	private CouponTypeVO couponTypeVO;
```
### 5. DAO層，主要以hibernate的native SQL 進行改寫

## Spring
### 1. 設定pom.xml()，在<dependencies> 中加⼊Spring Library相關 <dependency>
### 2. Java組態 + Annotation組態

2-1. **設定託管(IoC)**

@Repository 寫於DAO層

@Service 寫於service層

2-2. **設定注⼊(DI)**

@Autowired 寫於service層
```
@Autowired
private XXXDao dao;
```

2-3. **新建核⼼組態類別**
```java=
@Configuration
@ComponentScan("com.*.model") // 掃描@Component @Controller @Service @Repository
@EnableTransactionManagement
public class SpringConfig {
	//Spring-JNDI⽀援
	@Bean
	public DataSource dataSource() throws IllegalArgumentException, NamingException {
		JndiObjectFactoryBean bean = new JndiObjectFactoryBean();
		bean.setResourceRef(true);
		bean.setJndiName("jdbc/boardgame");
		bean.afterPropertiesSet();
		return (DataSource) bean.getObject();
	}
}
```
2-4. **指定Spring組態類別**
到部署描述檔設定(webapp/WEB-INF/web.xml)
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
### 3. 目前須使⽤Web環境的IoC容器物件，來取得Service物件，因Spring無法管控sevlet物件
```
public class SpringUtil {
	public static <T> T getBean(ServletContext sc, Class<T> clazz) {
		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(sc);
		return context.getBean(clazz);
	}
}


Servlet程式部分改寫(片段):
ICartService cartSvc = SpringUtil.getBean(getServletContext(), ICartService.class);
```
### 4. 使用Spring-Hibernate的支援


4-1. **設定pom.xml()，在<dependencies> 加入spring-orm程式庫 <dependency>**

4-2. **將Hibernate的組態設定，整合⾄Spring的組態設定中**

```java=
@Configuration
@ComponentScan("com.*.model") // 掃描@Component @Controller @Service @Repository
@EnableTransactionManagement
public class SpringConfig {
	//Spring-JNDI⽀援
	@Bean
	public DataSource dataSource() throws IllegalArgumentException, NamingException {
		JndiObjectFactoryBean bean = new JndiObjectFactoryBean();
		bean.setResourceRef(true);
		bean.setJndiName("jdbc/boardgame");
		bean.afterPropertiesSet();
		return (DataSource) bean.getObject();
	}
	//Spring-Hibernate⽀援:接管SessionFactory
	@Bean
	public SessionFactory sessionFactory() throws IllegalArgumentException, NamingException {
		return new LocalSessionFactoryBuilder(dataSource())
				.scanPackages("com.*.model") // 掃描@Entity
				.addProperties(getHibernateProperties())
				.buildSessionFactory();
	}
	//Spring-Hibernate⽀援:接管交易控制
	@Bean
	public TransactionManager transactionManager() throws IllegalArgumentException, NamingException {
		return new HibernateTransactionManager(sessionFactory());
	}
	//Spring-Hibernate⽀援:基本設定
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
###　5.解決lazy loading問題
到部署描述檔設定(webapp/WEB-INF/web.xml)
```
<!-- 	解決在jsp上顯示entity中的資料中的lazy loading-->
	<filter>
		<filter-name>HibernateFilter</filter-name>
		<filter-class>org.springframework.orm.hibernate5.support.OpenSessionInViewFilter</filter-class>
	</filter>
	<filter-mapping>
		<filter-name>HibernateFilter</filter-name>
		<url-pattern>/*</url-pattern>
	</filter-mapping>
```
### 6. 將連線統一管理
於DAO層改寫
```
@Repository
public class CouponTypeDAO implements CouponTypeDAO_interface{

	@PersistenceContext
	private Session session;
	
	@Override
	public void insert(CouponTypeVO couponTypeVO) {
		session.persist(couponTypeVO);
	}
```
### 7. 在Service層做交易控制
```
	@Transactional
	@Override
	public void updateQuantity(Integer coupTypeNo) {
		...略		
	}
```


![image](https://user-images.githubusercontent.com/108620186/192571435-0f808cb7-ff6c-4657-847b-6f4f2bcb3f31.png)
--- 
![image](https://user-images.githubusercontent.com/108620186/192571705-25836f8b-b124-4fff-9a80-edcef3476c7f.png)
--- 
![image](https://user-images.githubusercontent.com/108620186/192572036-6057542d-be02-481e-a77d-b3699538ad87.png)
--- 
![image](https://user-images.githubusercontent.com/108620186/192572252-bf529c3c-f9d0-42a5-8c4d-02a9559f66d9.png)

