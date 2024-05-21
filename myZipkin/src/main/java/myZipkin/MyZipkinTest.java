package myZipkin;

import myEntity.entity.ExtraParam;

public class MyZipkinTest {

    public static void main(String[] args) {
        ExtraParam extraParam = new ExtraParam();
        extraParam.setAppId(1L);
        System.out.println(extraParam.getAppId());
    }
}
