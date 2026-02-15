package tqlslib.core;

import mindustry.mod.ClassMap;
import tqlslib.api.ContentRegistry;

public class ContentRegistryCore implements ContentRegistry {
    private static final String PREFIX = "tqls-";
    
    @Override
    public void registerClass(String name, Class<?> clazz) {
        ClassMap.classes.put(PREFIX + name, clazz);
    }
    
    @Override
    public Class<?> getClass(String name) {
        return ClassMap.classes.get(PREFIX + name);
    }
    
    @Override
    public boolean hasClass(String name) {
        return ClassMap.classes.containsKey(PREFIX + name);
    }
    
    @Override
    public String getPrefix() {
        return PREFIX;
    }
}
