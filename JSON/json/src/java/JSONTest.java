import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JSONTest {

    public static void main(String[] args) {
//        setJSON();
//        setFastJSON();
//        setMapToJSON();
//        setBeanToJSON();

//        analysisJson();
//        jsonToBean();

        gsonTest();
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
