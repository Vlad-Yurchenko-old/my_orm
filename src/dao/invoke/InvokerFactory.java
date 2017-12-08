package dao.invoke;

import dao.invoke.type.IntInvoker;
import dao.invoke.type.ObjectInvoker;
import dao.invoke.type.StringInvoker;

import java.util.HashMap;
import java.util.Map;

public class InvokerFactory {

    static Map<Class<?>, Invoker> invokers = new HashMap<>();

    static {
        invokers.put(int.class, new IntInvoker());
        invokers.put(Integer.class, new IntInvoker());
        invokers.put(String.class, new StringInvoker());
        invokers.put(Object.class, new ObjectInvoker());
    }

    public static Invoker getInvoker(Class<?> keyClass){
        return invokers.get(keyClass);
    }

}
