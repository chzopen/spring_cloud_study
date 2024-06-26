package com.chz.myJavaAgent;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

public class MyJavaAgentPremain
{
    public static void premain(String agentArgs, Instrumentation inst)
    {
        System.out.println("premain : " + agentArgs);
        inst.addTransformer(new CustomClassTransformer(), true);
    }

    static class CustomClassTransformer implements ClassFileTransformer
    {
        @Override
        public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
            System.out.println("premain transform: " + className);
            return classfileBuffer;
        }
    }

}