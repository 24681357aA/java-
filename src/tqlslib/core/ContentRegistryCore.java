package tqlslib.core;

import mindustry.mod.ClassMap;
import tqlslib.api.ContentRegistry;
import tqlslib.content.registry.ClassRegistry;

public class ContentRegistryCore implements ContentRegistry {
    
    @Override
    public void registerClass(String name, Class<?> clazz) {
        ClassRegistry.register(name, clazz);
        ClassMap.classes.put(ClassRegistry.getPrefix() + name, clazz);
    }
    
    @Override
    public Class<?> getClass(String name) {
        Class<?> clazz = ClassRegistry.get(name);
        if (clazz == null) {
            clazz = ClassMap.classes.get(ClassRegistry.getPrefix() + name);
        }
        return clazz;
    }
    
    @Override
    public boolean hasClass(String name) {
        return ClassRegistry.has(name) || ClassMap.classes.containsKey(ClassRegistry.getPrefix() + name);
    }
    
    @Override
    public String getPrefix() {
        return ClassRegistry.getPrefix();
    }
}
