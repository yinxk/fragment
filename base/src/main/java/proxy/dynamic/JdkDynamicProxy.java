package proxy.dynamic;

import java.lang.reflect.Proxy;

public class JdkDynamicProxy {

    private Object target;

    public JdkDynamicProxy(Object target) {
        this.target = target;
    }

    public Object getProxyInstance() {
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), (proxy, method, args) -> {
            try {
                System.out.println("开始事务");
                Object re = method.invoke(target, args);
                System.out.println("提交事务");
                return re;
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("由于: " + e.getMessage() + ", 回滚事务");
            }
            return null;
        });
    }
}
