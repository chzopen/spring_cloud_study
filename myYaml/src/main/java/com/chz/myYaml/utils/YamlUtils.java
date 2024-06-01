package com.chz.myYaml.utils;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.chz.myYaml.entity.Properties;
import org.yaml.snakeyaml.Yaml;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Set;

public class YamlUtils {

    public static JSONObject yamlToJSON(InputStream in) throws IOException
    {
        try( BufferedReader br = new BufferedReader(new InputStreamReader(in)); )
        {
            Yaml yaml = new Yaml();
            Map<String, Object> objectMap = yaml.load(br);
            return JSONObject.from(objectMap);
        }
    }

    public static Properties yamlToProperties(InputStream in) throws IOException
    {
        JSONObject json = yamlToJSON(in);
        Properties properties = new Properties();
        String prefix = "";
        buildProperties(properties, prefix, json);
        return properties;
    }

    public static String printProperties(Properties properties)
    {
        StringBuilder sb = new StringBuilder();
        Set<Map.Entry<String, Object>> entries = properties.entrySet();
        for( Map.Entry<String, Object> entry : entries )
        {
            sb.append(entry.getKey()).append("=").append(entry.getValue()).append("\r\n");
        }
        return sb.toString();
    }

    public static <K, V> void buildProperties(Properties properties, String prefix, Object object)
    {
        if( object instanceof JSONObject )
        {
            JSONObject json = (JSONObject)object;
            if( json.size() > 0 )
            {
                for( Map.Entry<String, Object> entry : json.entrySet())
                {
                    String key = entry.getKey();
                    Object value = entry.getValue();
                    if( value instanceof String ||
                            value instanceof Number )
                    {
                        properties.put(buildKey(prefix, key), entry.getValue());
                    }
                    else
                    {
                        buildProperties(properties, buildKey(prefix, key), entry.getValue() );
                    }
                }
            }
            else
            {
                properties.put(prefix, "{}");
            }
        }
        else if( object instanceof JSONArray)
        {
            JSONArray jsonArray = (JSONArray)object;
            if( jsonArray.size() > 0 )
            {
                for( int i=0; i< jsonArray.size(); i++ ){
                    String indexString = "["+i+"]";
                    buildProperties(properties, buildKey(prefix, indexString), jsonArray.get(i));
                }
            }
            else
            {
                properties.put(prefix, "[]");
            }
        }
        else
        {
            properties.put(prefix, object);
        }
    }

    public static String buildKey(String prefix, String key)
    {
        if( prefix.isBlank() ){
            return key;
        }
        else if( key.startsWith("[") )
        {
            return prefix + key;
        }
        else
        {
            return prefix + "." + key;
        }
    }

}
