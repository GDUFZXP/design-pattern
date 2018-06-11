package com.orig.design.util;

import java.io.FileOutputStream;
import java.io.IOException;  
  
import com.orig.design.proxy.UserRoleService;
import net.sf.cglib.core.DefaultGeneratorStrategy;
import net.sf.cglib.core.GeneratorStrategy;
import net.sf.cglib.proxy.Enhancer;
import sun.misc.ProxyGenerator;
  
/**
 * 代理类的生成工具 =
 */  
public class ProxyGeneratorUtils {  
  
    /** 
     * 保存JDK动态代理类的字节码
     */
    public static void saveJDKProxyClass(String path, Class claz) {
        // 获取代理类的字节码
        String clazName = claz.getSimpleName();
        byte[] classFile = ProxyGenerator.generateProxyClass(clazName, claz.getInterfaces());
        saveFile(path, classFile);
    }

    /**
     * 保存cglib动态代理类的字节码文件
     * @param path
     * @throws Exception
     */
    public static void saveCglibProxyClass(String path,Enhancer enhancer) throws Exception {
        byte[] classFile = DefaultGeneratorStrategy.INSTANCE.generate(enhancer);
        saveFile(path, classFile);
    }

    private static void saveFile(String path, byte[] classFile) {
        FileOutputStream out = null;

        try {
            out = new FileOutputStream(path);
            out.write(classFile);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}