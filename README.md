本项目是一个简单的图片网站,基于原宜立方商城项目改编 

实现用户的上传图片 收藏图片功能

采用的服务有：
SpringBoot
Mybatis-plus作为持久层框架
fastDFS：对图片进行保存
redis：储存用户的token进行自动登录 同时在权限系统中
solr：搜索功能


简单介绍一下各个服务

后台管理服务：
manager 后台管理系统 
主要管理主页的内容,用户的信息,管理员的权限

authority 权限系统 采用shiro框架 

gateway 网关 目前只是与authority配合实现对所有后台服务的api拦截

eurekaServer 注册中心

网页功能：

userspace： 用户个人空间 显示收藏提交的图片

index：  提供首页信息（轮播图以及推荐图片)以及用户的信息的更改

solr: 搜索功能 原商城项目中使用activemq实现搜索库和数据库的同步，但并未采用 没有实现同步功能

imgdetail：图片详情页面 评论功能还未实现

sso：单点登录 

至于其他几个服务废置了 
