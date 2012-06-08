weibobak
========
这个小程序可以备份收藏微博的原始json格式的数据,也可以保存成易读的文本格式，还可以根据自己的需要扩展保存任何想要的格式(比如excel、html等)  


## get start ##
点击deploy.bat打包，然后进入target\weibobak.dir\weibobak这个目录，里面的结构为：  
>  - weibobak  
- bakdir  
- lib  
- logs  
- run.bat  
- weibobak.properties  

在weibobak.properties中填入用户名和密码，然后点击run.bat,运行完成之后会在bakdir目录下生成备份文件