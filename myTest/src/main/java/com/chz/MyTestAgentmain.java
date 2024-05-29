package com.chz;

import com.sun.tools.attach.*;

import java.io.IOException;
import java.util.List;

public class MyTestAgentmain
{
    public static void main(String[] args) throws IOException, AttachNotSupportedException, AgentLoadException, AgentInitializationException, InterruptedException
    {
        List<VirtualMachineDescriptor> vms = VirtualMachine.list();
        for (VirtualMachineDescriptor vm : vms) {
            System.out.println(String.format("thread=%s, id=%s, displayName=%s", Thread.currentThread().getId(), vm.id(), vm.displayName()));
            if ("com.chz.MyTestAgentmain".equals(vm.displayName())) {
                VirtualMachine machine = VirtualMachine.attach(vm.id());
                machine.loadAgent("C:/git/chzopen/spring_cloud_study-master/myJavaAgent/target/myJavaAgent-1.0-SNAPSHOT.jar");
            }
        }
        Thread.sleep(1000L);
        System.out.println("这里结束了");
    }
}