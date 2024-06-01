package com.chz.myYaml;

import com.chz.myYaml.entity.Properties;
import com.chz.myYaml.utils.YamlUtils;

import java.io.IOException;
import java.io.InputStream;

public class MyYamlTest
{

    public static void main(String[] args) throws IOException
    {
//        try(InputStream in = MyYamlTest.class.getClassLoader().getResourceAsStream("test.yaml"))
//        {
//            JSONObject jsonObject = YamlUtils.yamlToJSON(in);
//            System.out.println(JSON.toJSONString(jsonObject, JSONWriter.Feature.PrettyFormat));
//        }
        try(InputStream in = MyYamlTest.class.getClassLoader().getResourceAsStream("test.yaml"))
        {
            Properties properties = YamlUtils.yamlToProperties(in);
            System.out.println(YamlUtils.printProperties(properties));
        }
    }

}