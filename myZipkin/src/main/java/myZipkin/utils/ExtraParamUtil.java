package myZipkin.utils;


import myEntity.entity.ExtraParam;

public class ExtraParamUtil {

    private static ThreadLocal<ExtraParam> threadLocal = new ThreadLocal<>(){
        @Override
        protected ExtraParam initialValue() {
            return new ExtraParam();
        }
    };

    public static ExtraParam getExtraParam(){
        return threadLocal.get();
    }

}
