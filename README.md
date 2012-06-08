weibobak
========
这个小程序可以备份收藏微博的原始json格式的数据,也可以保存成易读的文本格式，还可以根据自己的需要扩展保存任何想要的格式(比如excel、html等我就不一一实现了)。  

本程序用于学习交流

## **Get Started** ##
点击deploy.bat打包，然后进入target\weibobak.dir\weibobak这个目录，里面的结构为：  
>  - weibobak  
- bakdir  
- lib  
- logs  
- run.bat  
- weibobak.properties  

在weibobak.properties中填入用户名和密码，然后点击run.bat,运行完成之后会在bakdir目录下生成备份文件

*note:由于我的app是没有经过新浪审核的，这个app key只是测试用的，只有我在页面上授权的10个用户才能使用。所以如果运行失败请在weibobak.properties中填入可用的client_ID和client_SERCRET就行了*

<br/><br/>
## **工程结构的说明** ##
>  - weibobak  
- bakdir --存放数据备份文件的目录
- lib  --所有依赖的jar包,包含本程序
- bin --运行脚本目录
- logs  --日志输出目录
- deploy.bat --打包脚本*（打包后的东西全在target目录里）*
- eclipse.bat --生成eclipse工程
- src/main/java --源代码
- src/main/resources --配置文件目录  
> > - weibobak.properties --本程序的配置文件  
> > - config.properties --微博sdk的配置文件  
