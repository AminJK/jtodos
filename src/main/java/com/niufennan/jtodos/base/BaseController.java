package com.niufennan.jtodos.base;

import java.util.HashMap;
import java.util.Map;

public abstract class BaseController {
    public Map<String,Object> result(){
        return result(200,"","");
    }
    public Map<String,Object> result(Object data){
        return result(200,"",data);
    }
    public Map<String,Object> result(int code,Object data){
        return result(code,"",data);
    }
    public Map<String,Object> result(int code,String msg,Object data){
        Map<String,Object> resutl=new HashMap<String,Object>();
        resutl.put("code",code);
        resutl.put("msg",msg);
        resutl.put("data",data);
        return resutl;
    }
}
