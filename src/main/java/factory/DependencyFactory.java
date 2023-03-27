package factory;

import java.lang.reflect.Field;

public class DependencyFactory {
	public static <T> T create(Class<T> clazz) throws Exception {
		@SuppressWarnings("deprecation")
		T instance = clazz.newInstance();
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(Inject.class)) {
                field.setAccessible(true);
                field.set(instance, create(field.getType()));
            }
        }
        return instance;
	}
}
