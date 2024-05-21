package myJFinal.demo;

import com.jfinal.config.*;
import com.jfinal.template.Engine;
import com.jfinal.server.undertow.UndertowServer;

public class DemoConfig extends JFinalConfig {

    /**
     * 注意：用于启动的 main 方法可以在任意 java 类中创建，在此仅为方便演示
     * 才将 main 方法放在了 DemoConfig 中
     * <p>
     * 开发项目时，建议新建一个 App.java 或者 Start.java 这样的专用
     * 启动入口类放置用于启动的 main 方法
     */
    public static void main(String[] args) {
        UndertowServer.start(DemoConfig.class, 80, true);
    }

    public void configConstant(Constants me) {
        me.setDevMode(true);
    }

    public void configRoute(Routes me) {
        // jfinal 4.9.03 版新增了路由扫描功能，不必手动添加路由
        // me.add("/hello", HelloController.class);

        // 使用路由扫描，参数 "demo." 表示只扫描 demo 包及其子包下的路由
        me.scan("myJFinal.demo.");
    }

    public void configEngine(Engine me) {
    }

    public void configPlugin(Plugins me) {
    }

    public void configInterceptor(Interceptors me) {
    }

    public void configHandler(Handlers me) {
    }
}
