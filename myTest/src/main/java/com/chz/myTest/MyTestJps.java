package com.chz.myTest;

import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;

import java.util.List;

public class MyTestJps
{
    public static void main(String[] args)
    {
        List<VirtualMachineDescriptor> vms = VirtualMachine.list();
        for (VirtualMachineDescriptor vm : vms) {
            System.out.println(String.format("id=%s, displayName=%s", vm.id(), vm.displayName()));
        }
    }
}
