package com.rynkbit.bananaorm;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class TableFactory {
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS ";

    /**
     * Returns a string representing a SQLite-Query that drops
     * a table which corresponds to the class
     * @param entityClass
     * @return
     */
    public String dropTableQuery(Class<? extends Entity> entityClass) {
        List<Field> fields = Arrays.asList(entityClass.getDeclaredFields());
        Method[] methods = entityClass.getDeclaredMethods();
        StringBuilder stringBuilder = new StringBuilder();

        for (Field field: fields) {
            if(field.getType() == List.class && hasInterface(getGenericType(field), IdEntity.class)){
                Method getter = getGetter(field, methods);
                Method setter = getSetter(field, methods);

                if(getter != null && setter != null) {
                    stringBuilder.append(DROP_TABLE)
                            .append(entityClass.getSimpleName().toUpperCase())
                            .append("_")
                            .append(getGenericType(field).getSimpleName().toUpperCase())
                            .append(";");
                }
            }
        }

        stringBuilder.append(DROP_TABLE)
                .append(entityClass.getSimpleName().toUpperCase())
                .append(";");

        return stringBuilder.toString();
    }

    /**
     * Returns a string representing a SQLite-Query to create a
     * table that maps the entities properties to the table
     * @param clazz
     * @return
     */
    public String createTableQuery(Class<? extends Entity> clazz){
        StringBuilder builder = new StringBuilder();
        List<String> associationTables = new LinkedList<>();
        Field fields[] = clazz.getDeclaredFields();
        Method methods[] = clazz.getDeclaredMethods();

        builder.append("CREATE TABLE ")
                .append(clazz.getSimpleName().toUpperCase())
                .append("(");

        List<String> fieldStrings = new LinkedList<>();

        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            Method getter = getGetter(field, methods);
            Method setter = getSetter(field, methods);

            if(getter != null && setter != null){
                if (field.getName().toLowerCase().equals("id")){
                    fieldStrings.add(field.getName() + " INTEGER PRIMARY KEY AUTOINCREMENT");
                } else {
                    String datatype = null;
                    if(field.getType() == Integer.class ||
                            field.getType() == int.class){
                        datatype = "INTEGER";
                    } else if(field.getType() == Float.class ||
                            field.getType() == Double.class ||
                            field.getType() == float.class ||
                            field.getType() == double.class){
                        datatype = "REAL";
                    } else if(field.getType() == String.class ||
                            field.getType() == Date.class){
                        datatype = "TEXT";
                    } else if(field.getType() == IdEntity.class){
                        datatype = "INTEGER";
                    } else if(field.getType() == List.class){
                        Type type = ((ParameterizedType)field.getGenericType()).getActualTypeArguments()[0];
                        Class<?> typeClass = (Class<?>)type;

                        if(hasInterface(typeClass, IdEntity.class)){
                            Class<? extends IdEntity> argumentClass = (Class<? extends IdEntity>) type;

                            if(hasInterface(argumentClass, IdEntity.class) && hasInterface(clazz, IdEntity.class)){
                                associationTables.add(createAssociationTable((Class<IdEntity>)clazz, (Class<IdEntity>) argumentClass));
                            }
                        }
                    }

                    if(datatype != null){
                        fieldStrings.add(field.getName() + " " + datatype);
                    }
                }

            }
        }

        for (int i = 0; i < fieldStrings.size(); i++) {
            String fieldString = fieldStrings.get(i);
            builder.append(fieldString);

            if(i < fieldStrings.size() - 1){
                builder.append(",");
            }
        }

        builder.append(");");

        for (String associationTable :
                associationTables) {
            builder.append(associationTable);
        }

        return builder.toString();
    }

    private Class<?> getGenericType(Field field){
        if(field.getType() != List.class){
            return field.getType();
        }else{
            Type type = ((ParameterizedType)field.getGenericType()).getActualTypeArguments()[0];

            return (Class<?>)type;
        }
    }

    private boolean hasInterface(Class<?> clazz, Class<?> interfaceClass){
        for (Class<?> c :
            clazz.getInterfaces()){
            if(c == interfaceClass)
                return true;
        }
        return false;
    }

    private String createAssociationTable(Class<IdEntity> clazz, Class<IdEntity> argumentClass){
        StringBuilder assocTable = new StringBuilder();
        assocTable.append("CREATE TABLE ")
                .append(clazz.getSimpleName().toUpperCase())
                .append("_")
                .append(argumentClass.getSimpleName().toUpperCase())
                .append("(")
                .append(clazz.getSimpleName())
                .append("Id INTEGER,")
                .append(argumentClass.getSimpleName())
                .append("Id INTEGER")
                .append(");");
        return assocTable.toString();
    }

    private Method getMethod(String prefix, Field field, Method[] methods){
        for (Method m: methods){
            String fieldName = field.getName().substring(0, 1).toUpperCase();
            fieldName += field.getName().substring(1);

            if(m.getName().equals(prefix + fieldName)){
                return m;
            }
        }

        return null;
    }

    private Method getSetter(Field field, Method[] methods) {
        return getMethod("set", field, methods);
    }

    private Method getGetter(Field field, Method[] methods){
        return getMethod("get", field, methods);
    }
}
