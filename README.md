### MappingSearch
> 一个用于Mapping路径映射搜索的IDEA插件

平常开发调试时，总是要根据请求的url来找到对应的接口，如果项目接口比较多，文件命名不规范的话，也是一件麻烦，恶心的事。

MappingSearch 可以根据请求路径来找到对应接口，免去了手动查找，和全局查找。

MappingSearch快捷键：Ctrl + Alt + N

**安装步骤：**
1. 点击File->Settings->Plugins->设置->Install Plugin from Disk
2. 选中MappingSearch.jar
3. 重启IDEA，在Help菜单下有个“MappingSearch映射搜索”选项表示安装成功

###Change-log
 v1.0.0
   - 支持绝对路径搜索（http://localhost:8080/xxx -> /xxx），目前不支持带ContentPath
   - 路径支持Ant表达式
   - 支持指定当前Model类搜索
   - 直接过滤指定请求类型


