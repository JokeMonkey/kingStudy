package com.jcy.agent;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * 描述: ASM 实现字节码增强技术
 *
 * @date :2022/9/29
 * @description :
 * @author: moji
 */
public class BaseVisitor extends ClassVisitor implements Opcodes {

    public BaseVisitor(ClassVisitor cv) {
        //ASM9 表示你使用的是ASM的api
        super(Opcodes.ASM9, cv);
    }


    /**
     * 访问类基本信息
     *
     * @param version
     * @param access
     * @param name
     * @param signature
     * @param superName
     * @param interfaces
     */
    @Override
    public void visit(int version,
                      int access,
                      String name,
                      String signature,
                      String superName,
                      String[] interfaces){
        this.cv.visit(version, access, name, signature, superName, interfaces);
    }


    /**
     * 访问方法基本信息
     *
     * @param access
     * @param name
     * @param descriptor
     * @param signature
     * @param exceptions
     * @return
     */
    @Override
    public MethodVisitor visitMethod(
            int access,
            String name,
            String descriptor,
            String signature,
            String[] exceptions) {

        MethodVisitor mv = this.cv.visitMethod(access, name, descriptor, signature, exceptions);

        if (mv != null && !name.equals("<init>")) {
            mv = new BaseVisitor.MyMethodVisitor((MethodVisitor)mv);
        }
        return (MethodVisitor)mv;
    }


    /**
     * 自定义方法访问对象
     *
     */
    class MyMethodVisitor extends MethodVisitor implements Opcodes{
        MyMethodVisitor(MethodVisitor mv){
            super(Opcodes.ASM9, mv);
        }
        long startTime = 0L;
        /**此方法会在方法执行之前执行*/
        @Override
        public void visitCode(){
            super.visitCode();
            this.mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out",
                    "Ljava/io/PrintStream;");
            startTime = System.currentTimeMillis();
            this.mv.visitLdcInsn("-----start-----" + startTime + "--------");
            this.mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream",
                    "println", "(Ljava/lang/String;)V", false);

        }

        /**对应方法体本身*/
        @Override
        public void visitInsn(int opcode) {
            //在方法return或异常之前，添加一个end输出
            if ((opcode >= Opcodes.IRETURN && opcode <= Opcodes.RETURN) || opcode == Opcodes.ATHROW) {
                this.mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out",
                        "Ljava/io/PrintStream;");
                this.mv.visitLdcInsn("-------end---总耗时---" + (System.currentTimeMillis() - startTime) + "-------");
                this.mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream",
                        "println", "(Ljava/lang/String;)V", false);
            }
            this.mv.visitInsn(opcode);
        }
    }

}
