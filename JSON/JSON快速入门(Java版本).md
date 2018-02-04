# JSON快速入门(Java版本)

> 内容from
>
> [B站 JSON快速入门(Java版本)](https://www.bilibili.com/video/av14529781/?from=search&seid=534989056866267539)
>
> [蚂蚁课堂-JSON快速入门(Java版本)](http://www.itmayiedu.com/front/couinfo/49/206)

1、	什么是JSON
2、	介绍JSON应用场景
3、	如何封装JSON及常用框架介绍
4、	通过Map转JSON
5、	通过JavaBean转JSON
6、	Java如何解析JSON
7、	GSON框架使用
8、	经典面试题JSON与XML区别？



# 1. 什么是 JSON？

> JSON(JavaScript Object Notation) 是一种轻量级的数据交换格式。它基于ECMAScript的一个子集。 JSON采用完全独立于语言的文本格式，但是也使用了类似于C语言家族的习惯（包括C、C++、C#、Java、JavaScript、Perl、Python等）。这些特性使JSON成为理想的数据交换语言。 易于人阅读和编写，同时也易于机器解析和生成(一般用于提升网络传输速率)。

## 1.1 JSON 语法

### 1.1.1 JSON 语法规则

JSON 语法是 JavaScript 对象表示语法的子集。

- 数据在键值对中
- 数据由逗号分隔
- 花括号保存对象
- 方括号保存数组

### 1.1.2 JSON 名称/值对

JSON 数据的书写格式是：名称/值对。

名称/值对组合中的名称写在前面（在双引号中），值对写在后面(同样在双引号中)，中间用冒号隔开：

```json
" userName ":"余胜军"
```

这很容易理解，等价于这条Java语句：

```java
userName ="余胜军"
```

### 1.1.3 JSON 值

JSON 值可以是：

- 数字 （整数或浮点数）
- 字符串（在双引号中）
- 逻辑值（true 或 false）
- 数组（在方括号中）
- 对象（在花括号中）
- null



> **JSON 例子**

```json
{ "errorCode": "0","errorMsg": "调用接口成功","data": [{"userName": "余胜军","position": "蚂蚁课堂创始人","webAddres": "www.itmayiedu.com"   },  {  "userName": "周安旭",  "position": "蚂蚁课堂合伙人",   "webAddres": "www.itmayiedu.com"  }    ]}
```

> **在线JSON格式化工具**

http://www.sojson.com/

# 2. JSON 应用场景

在远程过程调用（Remote Procedure Call，缩写为**RPC**）时,或者提供给外部访问接口，一般数据交互格式通常为JSON 

例如提供移动APP接口、前端ajax异步访问数据、项目合作时提供外部访问接口等。

演示一些网站采用 JSON 传输

某网站注册用户是否存在

https://www.chexiangfu.com.cn/pay/recover/account.htm

![](http://omi0o6pp2.bkt.clouddn.com/blog/180203/gDm6ABeJia.png)



![](http://omi0o6pp2.bkt.clouddn.com/blog/180203/jh1id83l6J.png)

# 3. 如何封装和解析 json

封装json、比较流行的框架fastjson、gson、Jackson

使用框架 fastjson 的步骤

- 新建一个 mavenJava 工程
- 添加依赖

```xml
<!--添加阿里巴巴解析json工具类-->
<dependency>
  <groupId>com.alibaba</groupId>
  <artifactId>fastjson</artifactId>
  <version>1.1.26</version>
</dependency>

<!--添加谷歌公司解析json工具类-->
<!-- https://mvnrepository.com/artifact/com.google.code.gson/gson -->
<dependency>
  <groupId>com.google.code.gson</groupId>
  <artifactId>gson</artifactId>
  <version>2.8.2</version>
</dependency>
```



> RootEntity.java	实体类根对象

```java
import java.util.List;

public class RootEntity {
    private String errorCode;
    private String errorMsg;
    private List<UserEntity> data;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public List<UserEntity> getData() {
        return data;
    }

    public void setData(List<UserEntity> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RootEntity{" +
                "errorCode='" + errorCode + '\'' +
                ", errorMsg='" + errorMsg + '\'' +
                ", data=" + data +
                '}';
    }
}
```



> UserEntity.java	实体类用户对象

```java
public class UserEntity {

    private String position;
    private String userName;
    private String webAddres;

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getWebAddres() {
        return webAddres;
    }

    public void setWebAddres(String webAddres) {
        this.webAddres = webAddres;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "position='" + position + '\'' +
                ", userName='" + userName + '\'' +
                ", webAddres='" + webAddres + '\'' +
                '}';
    }
}
```

> JSONTest.java   json封装与解析的测试类

```java
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JSONTest {

    public static void main(String[] args) {
//        setJSON();		通过传统方式自己拼接字符串JSON	
//        setFastJSON();	通过fastJSON封装JSON
//        setMapToJSON();	通过map转换成json
//        setBeanToJSON();	通过实体类转换JSON

//        analysisJson();	解析JSON
//        jsonToBean();		通过JSON转实体类解析JSON

        gsonTest();			//gson五分钟快速入门
    }

    /**
     * 通过传统方式自己拼接字符串JSON
     */
    public static void setJSON(){
        String str = "{ \"errorCode\": \"0\",\"errorMsg\": \"调用接口成功\",\"data\": [{\"userName\": \"余胜军\",\"position\": \"蚂蚁课堂创始人\",\"webAddres\": \"www.itmayiedu.com\"   },  {  \"userName\": \"周安旭\",  \"position\": \"蚂蚁课堂合伙人\",   \"webAddres\": \"www.itmayiedu.com\"  }    ]}";
        System.out.println(str);
    }

    /**
     * 通过fastJSON封装JSON
     */
    public static void setFastJSON(){
        JSONObject root = new JSONObject();
        root.put("errorCode",0);
        root.put("errorMsg","调用接口成功");

        JSONArray datArr = new  JSONArray();
        JSONObject userYushengjun = new JSONObject();
        userYushengjun.put("userName", "余胜军");
        userYushengjun.put("position", "蚂蚁课堂创始人");
        userYushengjun.put("webAddres", "www.itmayiedu.com");
        datArr.add(userYushengjun);
        root.put("data",datArr);
        System.out.println(root.toJSONString());
    }

    /**
     * 通过map转换json
     */
    public static void setMapToJSON(){
        HashMap<String,Object> root = new HashMap<String, Object>();
        root.put("errorCode",0);
        root.put("errorMsg","调用接口成功");

        List<Map<String, String>> dataArr = new ArrayList<Map<String, String>>();

        Map<String,String> userYushengjun = new HashMap<String, String>();
        userYushengjun.put("userName", "余胜军");
        userYushengjun.put("position", "蚂蚁课堂创始人");
        userYushengjun.put("webAddres", "www.itmayiedu.com");
        Map<String,String> itmayiedu = new HashMap<String, String>();
        itmayiedu.put("userName", "余胜军1");
        itmayiedu.put("position", "蚂蚁课堂创始人1");
        itmayiedu.put("webAddres", "www.itmayiedu.com");

        dataArr.add(itmayiedu);
        dataArr.add(userYushengjun);

        root.put("data",dataArr);
        System.out.println(new JSONObject().toJSONString(root));
    }

    /**
     * 通过实体类转换JSON
     */
    public static void setBeanToJSON() {

        RootEntity rootEntity = new RootEntity();
        rootEntity.setErrorCode("0");
        rootEntity.setErrorMsg("调用接口成功");

        List<UserEntity> data = new ArrayList<UserEntity>();

        UserEntity userEntity = new UserEntity();
        userEntity.setPosition("蚂蚁课堂创始人");
        userEntity.setUserName("余胜军");
        userEntity.setWebAddres("www.itmayiedu.com");
        data.add(userEntity);

        rootEntity.setData(data);
        System.out.println(new JSONObject().toJSONString(rootEntity));
    }

    /**
     * 解析JSON
     */
    public static void analysisJson() {
        String jsonStr = "{ \"errorCode\": \"0\",\"errorMsg\": \"调用接口成功\",\"data\": [{\"userName\": \"余胜军\",\"position\": \"蚂蚁课堂创始人\",\"webAddres\": \"www.itmayiedu.com\"   },  {  \"userName\": \"周安旭\",  \"position\": \"蚂蚁课堂合伙人\",   \"webAddres\": \"www.itmayiedu.com\"  }    ]}";
        //将json字符串转换成json
        JSONObject root = new JSONObject().parseObject(jsonStr);
        String errorCode = root.getString("errorCode");
        String errorMsg = root.getString("errorMsg");
        System.out.println("errorCode:"+errorCode+",errorMsg:"+errorMsg);
        JSONArray dataArr = root.getJSONArray("data");
        for (int i = 0; i < dataArr.size(); i++) {
            JSONObject dataBean = (JSONObject) dataArr.get(i);
            String position = dataBean.getString("position");
            String userName = dataBean.getString("userName");
            String webAddres = dataBean.getString("webAddres");
            System.out.println("position:"+position+",userName:"+userName+",webAddres:"+webAddres);
        }
    }

    /**
     * 通过JSON转实体类
     */
    public static void jsonToBean(){
        String jsonStr = "{ \"errorCode\": \"0\",\"errorMsg\": \"调用接口成功\",\"data\": [{\"userName\": \"余胜军\",\"position\": \"蚂蚁课堂创始人\",\"webAddres\": \"www.itmayiedu.com\"   },  {  \"userName\": \"周安旭\",  \"position\": \"蚂蚁课堂合伙人\",   \"webAddres\": \"www.itmayiedu.com\"  }    ]}";
        //将json字符串转换成实体类
        RootEntity RootEntity = new JSONObject().parseObject(jsonStr,RootEntity.class);
        System.out.println(RootEntity.toString());
    }

    /**
     * gson五分钟快速入门
     */
    public static void gsonTest(){
        Gson gson = new Gson();

        RootEntity rootEntity = new RootEntity();
        rootEntity.setErrorCode("0");
        rootEntity.setErrorMsg("调用接口成功");

        List<UserEntity> data = new ArrayList<UserEntity>();

        UserEntity userEntity = new UserEntity();
        userEntity.setPosition("蚂蚁课堂创始人");
        userEntity.setUserName("余胜军");
        userEntity.setWebAddres("www.itmayiedu.com");
        data.add(userEntity);

        rootEntity.setData(data);

        //实体类转换成json
        System.out.println(gson.toJson(rootEntity));

        //json转换成实体类
        String json = gson.toJson(rootEntity);
        RootEntity rty = gson.fromJson(json,RootEntity.class);
        System.out.println(rty.toString());
    }

}
```

# 4. JSON与 XML 面试题解析

## 4.1 什么是JSON、XML?

XML:记语言 (Extensible Markup Language, XML) ，用于标记电子文件使其具有结构性的标记语言，可以用来标记数据、定义数据类型，是一种允许用户对自己的标记语言进行定义的源语言。 XML使用DTD(document type definition)文档类型定义来组织数据;格式统一，跨平台和语言，早已成为业界公认的标准。

XML是标准通用标记语言 (SGML) 的子集，非常适合 Web 传输。XML 提供统一的方法来描述和交换独立于应用程序或供应商的结构化数据。

JSON(JavaScript Object Notation)一种轻量级的数据交换格式，具有良好的可读和便于快速编写的特性。可在不同平台之间进行数据交换。JSON采用兼容性很高的、完全独立于语言文本格式，同时也具备类似于C语言的习惯(包括C, C++, C#, Java, JavaScript, Perl, Python等)体系的行为。这些特性使JSON成为理想的数据交换语言。

JSON基于JavaScript Programming Language , Standard ECMA-262 3rd Edition - December 1999 的一个子集。

**一句话总结:JSON和XML都是数据交换格式，JSON轻量级的数据交换格式、XML标记电子文件使其具有结构性的标记语言。**

## 4.2 JSON与XML区别是什么？ 有什么共同点？

> 详细参考地址: http://www.cnblogs.com/SanMaoSpace/p/3139186.html

1. **XML的优缺点**
   - **XML的优点**
     - 格式统一，符合标准；
     - 容易与其他系统进行远程交互，数据共享比较方便。
   - **XML的缺点**
     - XML文件庞大，文件格式复杂，传输占带宽；
     - 服务器端和客户端都需要花费大量代码来解析XML，导致服务器端和客户端代码变得异常复杂且不易维护；
     - 客户端不同浏览器之间解析XML的方式不一致，需要重复编写很多代码；
     - 服务器端和客户端解析XML花费较多的资源和时间。
2. **JSON的优缺点**
   - **JSON的优点：**
     - 数据格式比较简单，易于读写，格式都是压缩的，占用带宽小；
     - 易于解析，客户端JavaScript可以简单的通过eval()进行JSON数据的读取；
     - 支持多种语言，包括ActionScript, C, C#, ColdFusion, Java, JavaScript, Perl, PHP, Python, Ruby等服务器端语言，便于服务器端的解析；
     - 在PHP世界，已经有PHP-JSON和JSON-PHP出现了，偏于PHP序列化后的程序直接调用，PHP服务器端的对象、数组等能直接生成JSON格式，便于客户端的访问提取；
     - 因为JSON格式能直接为服务器端代码使用，大大简化了服务器端和客户端的代码开发量，且完成任务不变，并且易于维护。
   - **JSON的缺点**
     - 没有XML格式这么推广的深入人心和喜用广泛，没有XML那么通用性；
     - JSON格式目前在Web Service中推广还属于初级阶段。

> 一句话总结：XML 它是用于 RPC 远程调用数据交换格式，因为 XML 文件格式复杂，比较占宽带，不易于维护，服务端与客户端解析 xml 花费较多的资源和时间。



## 4.3 为什么用JSON不用XML？ 

> json 是轻量级，xml 是重量级。
>
> xml 是重量级的，文件格式复杂，所以在远程调用时，比较占宽带，不易于维护，服务器与客户端解析xml会花费较多的资源和时间。
>
> json 是轻量级的，文件格式都是压缩的，占用带宽小，易于维护，解析也比较快。



## 4.4 JSON、XML解析有那些方式？

JSON 解析方式(阿里巴巴 fastjson、谷歌 gson、jackJson)

XML 解析方式(dom、sax、pul)

## 4.5 说一下JSON、XML你是在哪里用的？

这个就更具大家实际开发经验来说。

例如:一般现在移动APP接口都采用JSON，因为json占宽带小。

例如：我们公司微信开发,微信接口都是JSON格式的， 微信事件推送是XML。

JSON和XML都是在远程调用或者是和某公司合作定义数据交换格式。







