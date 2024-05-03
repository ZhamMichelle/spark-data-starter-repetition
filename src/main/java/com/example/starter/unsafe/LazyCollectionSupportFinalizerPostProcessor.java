package com.example.starter.unsafe;

import com.example.sparkdatastarterrepetition.ForeignKeyName;
import com.example.sparkdatastarterrepetition.Source;
import com.example.starter.LazySparkList;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;

/**
 * @author zhamilya on 5/1/24
 */
@RequiredArgsConstructor
public class LazyCollectionSupportFinalizerPostProcessor implements FinalizerPostProcessor {
    private final ConfigurableApplicationContext context;

    @SneakyThrows
    @Override
    public Object postFinalize(Object retVal) {
        if (Collection.class.isAssignableFrom(retVal.getClass())) {
            for (Object model : (List) retVal) {
                Field idField = model.getClass().getDeclaredField("id");
                idField.setAccessible(true);

                Field[] fields = model.getClass().getDeclaredFields();
                for (Field field : fields) {
                    if (List.class.isAssignableFrom(field.getType())) {
                        LazySparkList sparkList = context.getBean(LazySparkList.class);
                        sparkList.setOwnerId(idField.getLong(model));
                        String columnName = field.getAnnotation(ForeignKeyName.class).value();
                        sparkList.setForeignKeyName(columnName);
                        Class<?> embeddedModel = getEmbeddedModel(field);
                        sparkList.setModelClass(embeddedModel);
                        String pathToData = embeddedModel.getAnnotation(Source.class).value();
                        sparkList.setPathToSource(pathToData);

                        field.setAccessible(true);
                        field.set(model, sparkList);
                    }
                }
            }
        }
        return retVal;
    }

    private Class<?> getEmbeddedModel(Field field) {
        ParameterizedType genericType = (ParameterizedType) field.getGenericType();
        Class<?> embeddedModel = (Class<?>) genericType.getActualTypeArguments()[0];
        return embeddedModel;
    }
}
