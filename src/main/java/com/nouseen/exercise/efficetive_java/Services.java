package com.nouseen.exercise.efficetive_java;

import com.nouseen.exercise.efficetive_java.Interface.Provider;
import com.nouseen.exercise.efficetive_java.Interface.Service;
import com.sun.org.apache.regexp.internal.RE;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by nouseen on 2017/12/20.
 */
public class Services {

    private Services() {
    }

    private static final Map<String, Provider> providers = new ConcurrentHashMap<>();

    public static final String DEFALT_PROVIDER_NAME = "<def>";

    public static void registerDefaultProvider(Provider provider) {
        registerDefaultProvider(DEFALT_PROVIDER_NAME, provider);
    }

    public static void registerDefaultProvider(String name, Provider provider) {
        providers.put(name, provider);
    }

    public static Service newInstance() {
        return newInstance(DEFALT_PROVIDER_NAME);
    }

    private static Service newInstance(String name) {
        Provider p = providers.get(name);
        if (p==null) {
            throw new IllegalArgumentException("No provider registered with name:" + name);
        }

        return p.newService();
    }
}
