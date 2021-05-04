#依赖注入
##p命名空间（set注入）
需要在实体类中写set方法
xml头部添加xmlns:p="http://www.springframework.org/schema/p"
xml中使用实例
<bean id="user" class="com.pojo.User" p:name="小明" p:age="22" />

##c命名空间（构造器注入）
实体类中需要含有参数的构造方法
xml头部添加xmlns:c="http://www.springframework.org/schema/c"
xml中使用实例
bean id="user" class="com.pojo.User" c:name="小明" c:age="22" scope="prototype"


