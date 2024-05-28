package com.chz.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class AjaxResult {

    private String code;
    private String message;
    private Map<String, Object> map = new HashMap<>();

    public Object get(String key){
        return map.get(key);
    }

    public static AjaxResult success(int id){
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.map.put("data", id);
        return ajaxResult;
    }

    public static AjaxResult error(){
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.message = "error";
        return ajaxResult;
    }

}
