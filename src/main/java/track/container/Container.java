package track.container;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.lang.reflect.Field;
import java.lang.Object;
import java.util.stream.Collectors;
import track.container.config.Bean;
import track.container.beans.Car;
import track.container.config.Property;
import track.container.config.ValueType;

/**
 * Основной класс контейнера
 * У него определено 2 публичных метода, можете дописывать свои методы и конструкторы
 */
public class Container {

    private Map<String, Object> objByName = new HashMap<>();
    private Map<String, Object> objByClassName = new HashMap<>();

    private void addObject(Bean bean, List<Bean> beans) throws Exception {
        if (!objByName.containsKey(bean.getId())) {
            Class curClass = Class.forName(bean.getClassName());
            Object obj = curClass.newInstance();
            Iterator it = bean.getProperties().entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
                Field field = curClass.getDeclaredField(pair.getKey().toString());
                Property property = (Property) pair.getValue();
                if (property.getType().equals(ValueType.VAL)) {
                    field.setAccessible(true);
                    field.set(obj, Integer.parseInt(property.getValue()));
                } else {
                    if (!objByName.containsKey(property.getValue())) {
                        List<Bean> result = beans.stream()
                                .filter(item -> item.getId().equals(property.getValue()))
                                .collect(Collectors.toList());
                        for (Bean newBean : result) {
                            addObject(newBean, beans);
                        }
                    }
                    field.setAccessible(true);
                    field.set(obj, objByName.get(property.getValue()));
                }
                objByName.put(bean.getId(), obj);
                objByClassName.put(bean.getClassName(), obj);
            }
        }
    }

    // Реализуйте этот конструктор, используется в тестах!
    public Container(List<Bean> beans) throws Exception {
        for (Bean bean: beans) {
            addObject(bean, beans);
        }
    }

    public static void main(String[] args) throws Exception {

    }

    /**
     *  Вернуть объект по имени бина из конфига
     *  Например, Car car = (Car) container.getById("carBean")
     */
    public Object getById(String id) {
        return objByName.get(id);
    }

    /**
     * Вернуть объект по имени класса
     * Например, Car car = (Car) container.getByClass("track.container.beans.Car")
     */
    public Object getByClass(String className) {
        return objByClassName.get(className);
    }
}
