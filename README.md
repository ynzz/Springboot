# Springboot
In this repository, I'll start a project to show how to build a Spring boot frame.

#1、springboot helloWord
##1.1、新建maven项目
pom.xml文件引入如下依赖：
	
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>1.5.9.RELEASE</version>
	</parent>

	<properties>
		<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
		<project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
		<java.version>1.8</java.version>
	</properties>

	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
	<dependencies>
	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
			</plugin>
		</plugins>
	</build>
##1.2、启动类
在src/java/main目录下创建启动类：Application.java

	@SpringBootApplication
	public class Application {
		public static void main(String[] args) {
			SpringApplication.run(Application.class, args);
		}
	}
##1.3、访问接口
创建src/java/main下创建Controller类：

	@RestController
	@RequestMapping("/customer")
	public class CustomerController {
		@RequestMapping(value = "/hello", method = RequestMethod.GET)
		public String sayHello(){
			return "hello springBoot!";
		}
	}
##1.4、启动的三种方式:
运行Application.java的main方法;
cmd到项目目录下，执行：mvn spring-boot:run;
cmd到项目目录下，执行：mvn install，完成后cd target，执行：java -jar xxx.jar
跳过单元测试： mvn -Dmaven.test.skip -U clean package

#2、springboot 配置文件
##2.1、默认配置
在src/java/resources下创建application.properties或者application.yml文件
springboot会自动读取配置内容
以application.yml为例：

	server:
	  port: 8082 #端口
	  context-path: /szl  #访问前缀
##2.2、多环境配置
配置多环境配置文件，不同的环境可用不同的配置：
application-dev.yml:开发环境
application-test.yml:测试环境
application-prod.yml:生产环境
在默认配置application.yml文件中配置如下内容进行选择加载：

	spring:
	  profiles:
	    active: dev
配置在application.yml文件中内容对各种环境都起作用
##2.3、读取配置文件内容
配置中的属性:

	szl:
		name: szl
###2.3.1、类中通过@Value注解：
	
	@Value("${szl.name}")
	private String name;
name的值为szl
###2.3.2、通过工具类读取：
pom.xml引入如下依赖：

	<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-configuration-processor</artifactId>
			<optional>true</optional>
		</dependency>
创建工具类：ResourceUtil.java

	@Configuration
	@ConfigurationProperties(prefix="szl")
	public class ResourceUtil {
		private String name;
		//getter and setter
	}
在类中注入ResourceUtil，通过resourceUtil.getName可用获取到属性值：szl

	@Autowired
	private ResourceUtil resourceUtil;

	resourceUtil.getName()//szl
还可以从指定的文件文件中读取：
在ResourceUtil上加上注解：@PropertySource(value="classpath:resource.properties")
###2.3.3、使用工具类2：
PropertiesListener：初始化加载配置文件

	public class PropertiesListener implements ApplicationListener<ApplicationStartedEvent> {

    	    private String propertyFileName;
	
	    public PropertiesListener(String propertyFileName) {
	        this.propertyFileName = propertyFileName;
	    }
	
	    @Override
	    public void onApplicationEvent(ApplicationStartedEvent event) {
	        PropertiesUtils.loadAllProperties(propertyFileName);
	    }
	}
PropertiesUtils：加载配置文件内容

	public class PropertiesUtils {
	
		public static Map<String, String> propertiesMap = new HashMap<>();
	
	    private static void processProperties(Properties props) throws BeansException {
	        propertiesMap = new HashMap<String, String>();
	        for (Object key : props.keySet()) {
	            String keyStr = key.toString();
	            try {
	                // PropertiesLoaderUtils的默认编码是ISO-8859-1,在这里转码一下
	                propertiesMap.put(keyStr, new String(props.getProperty(keyStr).getBytes("ISO-8859-1"), "utf-8"));
	            } catch (UnsupportedEncodingException e) {
	                e.printStackTrace();
	            } catch (java.lang.Exception e) {
	                e.printStackTrace();
	            }
	        }
	    }
	
	    public static void loadAllProperties(String propertyFileName) {
	        try {
	            Properties properties = PropertiesLoaderUtils.loadAllProperties(propertyFileName);
	            processProperties(properties);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	
	    public static String getProperty(String name) {
	        return propertiesMap.get(name).toString();
	    }
	
	    public static Map<String, String> getAllProperty() {
	        return propertiesMap;
	    }
	}
Application修改为：

	public static void main(String[] args) {
	 	SpringApplication application = new SpringApplication(Application.class);
      	application.addListeners(new PropertiesListener("application.properties"));//需要读取的配置文件
      	application.run(args);
	}
Constant:常量类，与配置文件属性对应：

	public interface Constant {
		interface Szl {
			String SZL_NAME = "szl.name";
		}
	}
使用：
 String name = PropertiesUtils.getProperty(Constant.Szl.SZL_NAME);
 
#3、springboot aop处理请求
##3.1、统一返回类型
新建ResultVo类，添加属性：
	
	/** 状态码*/
	private String code;
	/** 提示信息*/
	private String msg;
	/** 返回数据*/
	private Object data;	
	//getter and setter
返回的时候，设置code，msg，data，使返回类型统一
##3.2、统一日志
通过aop实现执行方法前或执行时和执行完毕打印信息
pom.xml引入依赖：

	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-aop</artifactId>
	</dependency>
创建统一处理类：LogAspect.java

	@Aspect
	@Component
	public class LogAspect {

		private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);
		
		@Pointcut("execution(* com.szl.controller.*.*(..))")//处理的切入点
		public void log(){
			
		}
		
		@Before("log()")//处理方法之前执行
		public void doBefore(JoinPoint joinPoint){
			ServletRequestAttributes attributes = 		(ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
			HttpServletRequest request = attributes.getRequest();
			//url
			logger.info("url={}", request.getRequestURI());
			//method
			logger.info("method={}", request.getMethod());
			//ip
			logger.info("ip={}", request.getRemoteAddr());
			//类方法
			logger.info("class_method={}", joinPoint.getSignature().getDeclaringTypeName() + "." + 			joinPoint.getSignature().getName());
			//参数
			logger.info("args={}", joinPoint.getArgs());
		}
		
		@After("log()") //处理完之后执行
		public void doAfter(){
			
		}
		
		@AfterReturning(pointcut = "log()", returning = "object") //返回信息
		public void doAfterReturning(Object object){
			if(object instanceof ResultVo){
				ResultVo resultVo = (ResultVo)object;
				if(resultVo.getData() != null){
					logger.info("response={}", resultVo.getData().toString());
				}
			}else{
				logger.info("response={}", object);
			}
		}
	}


#4、springboot 统一异常处理
##4.1、创建统一返回信息的枚举类
ResultEnum.java:

		public enum ResultEnum {
		UNKNOWN_ERROR("-1", "系统错误"),
		SUCCESS("0", "成功"),
		FAIL("1", "失败"),
		;
		private String code;
		private String msg;
		ResultEnum(String code, String msg){
			this.code = code;
			this.msg = msg;
		}
		//setter
	}
##4.2、创建自己的异常处理类
ThrowsException.java

	public class ThrowsException extends RuntimeException{
		private String code;
		public ThrowsException(ResultEnum resultEnum){
			super(resultEnum.getMsg());
			this.code = resultEnum.getCode();
		}
		//getter and setter
	}
##4.3、创建全局异常处理类
GloabException.java:

	@RestControllerAdvice
	public class GloabException {
		private static final Logger logger = LoggerFactory.getLogger(GloabException.class);
		@ExceptionHandler(value = Exception.class)
		public ResultVo handler(Exception e){
			if(e instanceof ThrowsException){
				ThrowsException throwsException = (ThrowsException) e;
				return ResultVo.get(throwsException.getCode(), throwsException.getMessage());
			}else{
				logger.error("【系统异常】{}", e);
				return ResultVo.get(ResultEnum.UNKNOWN_ERROR);
			}
		}
	}
##4.4、使用异常
在类中通过抛出异常的方式，可以返回想要返回给前端的信息，信息通过枚举类统一管理
throw ThrowsException(UNKNOWN_ERROR);


#5、springboot 单元测试
pom.xml文件引入依赖：

	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-test</artifactId>
		<scope>test</scope>
	</dependency>
##5.1、创建启动类
在src/test/java下创建启动类ApplicationTest.java
	
	@RunWith(SpringRunner.class)
	@SpringBootTest
	public class ApplicationTest {
	
		@Test
		public void contextLoads(){
			
		}
	}
##5.2、Service测试
创建测试类，通过@Test进行单元测试，利用断言判断测试结果

	@RunWith(SpringRunner.class)
	@SpringBootTest
	public class ServiceTest {
	
		@Autowired
		private CustomerService customerService;
		
		@Test
		public void getCustomerByMobileTest(){
			Customer customer = customerService.getCustomerByMobile("18829281460");
			Assert.assertEquals("信仰", customer.getCustomerName());
		}
	}
	
##5.3、接口测试
创建controller测试类，接口测试不同于Service测试，需要引入@AutoConfigureMockMvc，利用MockMvc进行测试

	@RunWith(SpringRunner.class)
	@SpringBootTest
	@AutoConfigureMockMvc
	public class ControllerTest {
	
		@Autowired
		private MockMvc mvc;
		
		@Test
		public void getCustomerByMobile() throws Exception{
			mvc.perform(MockMvcRequestBuilders.get("/customer/getCustomer?mobile=18829281460"))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.content().string("abc"));
		}
	}
##5.4、测试
可以运行每一个测试方法，或者运行一个测试类，执行类中的所有测试方法
项目打包的时候会执行所有单元测试：mvn clean package
跳过测试：mvn clean package -D maven.test.skip=true


#6、springboot 热部署：
6.1、热部署和热加载
java热部署与热加载联系：不重启服务器编译/部署项目；基于java的类加载器实现
java热部署与热加载区别：热部署在服务器运行时重新部署项目；热加载在运行时重新加载class
原理上的区别：热部署直接重新加载整个应用；热加载在运行时重新加载class
使用场景的区别：热部署更多的是在生产环境使用；热加载则更多的是在开发环境使用
6.2、热部署原理解析
java类的加载过程：
					        子类，自动加载				子类，自动加载			       交给其父类加载
初始化jvm-->产生启动类加载器------------>标准扩展类加载器------------>系统类加载器------------->加载class文件
类加载的五个阶段：
加载---->验证----->准备----->解析----->初始化
java类加载器特点：
由AppClass Loader（系统类加载器）开始加载指定的类
类加载器将加载任务交给其父，如果其父找不到，再由自己取加载
Bootstrap Loader（启动类加载器）是最顶级的类加载器
6.3、java类的热部署：
类的热加载：

配置Tomcat：
直接把项目web文件夹放到webapps里
在tomcat\conf\server.xml中的<host></host>内部添加<context/>标签，虚拟路径作为路径
在%tomcat_home%\conf\Catalina\localhost中添加一个xml，xml文件名作为路径
6.4、springboot 热部署
以maven的方式运行项目：mvn spring-boot:run
pom.xml文件的<plugin>引入如下依赖:

	<dependencies>
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>springloaded</artifactId>
			<version>1.2.6.RELEASE</version>
		</dependency>
	</dependencies>
以Application方式运行：
pom.xml添加依赖：

	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-devtools</artifactId>
		<optional>true</optional>
	</dependency>
修改配置文件，application.yml,添加如下设置：

	spring:
	  devtools: #热部署
	    restart:
	      enabled: true #热部署生效
	      additional-paths: src/main/java #设置重启的目录，添加那个目录的文件需要restart
	      exclude: WEB-INF/** #排除自动重启
	
	
#7、springboot发布方式
7.1、构建jar包：命令行运行springboot程序
maven install，将项目打包成xxx.jar文件
命令行cd到jar包所在的目录，运行：java -jar xxx.jar
7.2、构建war包，发布Tomcat
将pom.xml中<packaging>jar</packaging>改为war
引入Tomcat依赖：

	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-tomcat</artifactId>
	</dependency>
修改启动类Application，继承SpringBootServletInitializer
添加方法：

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(Application.class);
	}
重新构建项目run as： maven clean，maven install，构建成war，包括环境和jar包
将war包放到外部Tomcat的webapps里面，启动Tomcat，war包解压
访问的时候要带上解压的文件名作为路径

#8、springboot mybatis整合
##8.1、引入依赖
需要引入mybatis和mysql依赖

	<!-- mybatis -->
	<dependency>
		<groupId>org.mybatis.spring.boot</groupId>
		<artifactId>mybatis-spring-boot-starter</artifactId>
		<version>1.2.0</version>
	</dependency>

	<!-- mysql -->
	<dependency>
		<groupId>mysql</groupId>
		<artifactId>mysql-connector-java</artifactId>
	</dependency>

##8.2、相关配置
在配置文件配置数据源：

	spring:
	  datasource:
	    url: jdbc:mysql://localhost:3306/szl?useUnicode=true&characterEncoding=utf8&useSSL=true
	    username: root
	    password: root
mybatis的相关配置：

	 mybatis:
	  type-aliases-package: com.szl.entity
	  mapper-locations: classpath:mapper/*.xml

##8.3、创建文件
创建实体类和表对应
创建mapper.xml文件，用于操作sql
创建mapper.java,和mapper.xml对应
创建Service，需要执行的方法
创建ServiceImpl，类上使用@Service注解；通过注入mapper，调用方法操作数据库；方法上使用@Transaction注解开启事务
创建controller，注入Service，调用Service方法

##8.4、使用Druid数据源
pom引入依赖：

	<dependency>
		<groupId>com.alibaba</groupId>
		<artifactId>druid</artifactId>
		<version>1.0.19</version>
	</dependency>	

配置文件修改为：
 datasource:
    url: jdbc:mysql://localhost:3306/szl?useUnicode=true&characterEncoding=utf8&useSSL=true
    username: root
    password: root
    # 使用druid数据源
    type: com.alibaba.druid.pool.DruidDataSource
    driver-class-name: com.mysql.jdbc.Driver
    maxActive: 20
    initialSize: 1
    maxWait: 60000
    minIdle: 1
    timeBetweenEvictionRunsMillis: 60000
    minEvictableIdleTimeMillis: 300000
    validationQuery: select 'x'
    testWhileIdle: true
    testOnBorrow: false
    testOnReturn: false
    poolPreparedStatements: true
    maxOpenPreparedStatements: 20

