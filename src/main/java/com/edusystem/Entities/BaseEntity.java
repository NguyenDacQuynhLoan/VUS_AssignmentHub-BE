//package com.edusystem.Entities;
//
//import javax.persistence.*;
//import java.lang.reflect.ParameterizedType;
//import java.lang.reflect.Type;
//
//public  class BaseEntity<T> {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId (Long className) {
//
//    }
//
//    public BaseEntity() {
//        ParameterizedType detectedClass = (ParameterizedType) getClass().getGenericSuperclass();
//        Type[] typeArgs = detectedClass.getActualTypeArguments();
//        if(typeArgs.length > 0){
//            Class<?> genericClass = (Class<?>) typeArgs[0];
//            String className = genericClass.getSimpleName();
//            this.className = className;
//        }
//    }
//}
