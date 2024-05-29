package com.chz.myJavaAgent;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

public class MyJavaAgentAgentmain
{
    public static void agentmain(String agentArgs, Instrumentation inst)
    {
        System.out.println("agentmain");
        inst.addTransformer(new CusDefinedClass(), true);
    }

    static class CusDefinedClass implements ClassFileTransformer {
        @Override
        public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
            System.out.println(String.format("tid=%s, agentmain transform: %s", Thread.currentThread().getId(), className));
            return classfileBuffer;
        }
    }
}