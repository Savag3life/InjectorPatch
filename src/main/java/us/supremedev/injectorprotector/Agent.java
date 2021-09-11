package us.supremedev.injectorprotector;

import us.supremedev.injectorprotector.impl.ClassPatcher;

import java.lang.instrument.Instrumentation;

public class Agent {

    public static void agentmain(String agent, Instrumentation instrumentation) { }

    public static void premain(String args, Instrumentation instrumentation){
        instrumentation.addTransformer(new ClassPatcher());
        System.out.println("Injection flagger added. Listening...");
    }
}
