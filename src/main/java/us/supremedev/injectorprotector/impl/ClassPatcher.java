package us.supremedev.injectorprotector.impl;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.*;

import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;

public class ClassPatcher implements ClassFileTransformer {

    @Override
    public byte[] transform(ClassLoader loader,
                            String className,
                            Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain,
                            byte[] classfileBuffer) {
        try {
            if (className.contains("L10")) {
                System.out.println("Blocked class loading for `" + className + "` - Class flagged as injected.");
                System.out.println("Source: " + protectionDomain.getCodeSource().getLocation().toURI().getPath());
                return new byte[] { };
            }

            if (className.equals("javassist/ws/a")) {
                System.out.println("Blocked jar loading for `" + className + "` - Class is known injector malware.");
                System.out.println("Source: " + protectionDomain.getCodeSource().getLocation().toURI().getPath());

                ClassReader classReader = new ClassReader(classfileBuffer);
                ClassNode classNode = new ClassNode();
                classReader.accept(classNode, 0);

                for (MethodNode methodNode : classNode.methods) {
                    methodNode.instructions.clear();
                    methodNode.localVariables.clear();
                    methodNode.tryCatchBlocks.clear();
                }

                ClassWriter classWriter = new ClassWriter(2);
                classNode.accept(classWriter);
                return classWriter.toByteArray();
            }
        } catch (Throwable er) {
            System.out.println("Error occurred while reading class for details... " + er.getMessage());
            return new byte[] { };
        }
        return classfileBuffer;
    }
}
