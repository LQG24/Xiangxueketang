package com.example.xiangxueketang.lesson2;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class Deserialize {

    static class Response<T> {
        T data;
        int code;
        String message;

        @Override
        public String toString() {
            return "Response{" +
                    "data=" + data +
                    ", code=" + code +
                    ", message='" + message + '\'' +
                    '}';
        }

        public Response(T data, int code, String message) {

            this.data = data;
            this.code = code;
            this.message = message;
        }
    }

    static class Data {
        String result;

        public Data(String result) {
            this.result = result;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "result=" + result +
                    '}';
        }
    }

    static class TypeReference<T>{
        Type mType;
        public TypeReference() {
            Type genericSuperclass = getClass().getGenericSuperclass();
            ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
            //泛型类型有多个，所以是数组类型
            Type[] actualTypeArguments=parameterizedType.getActualTypeArguments();
            mType = actualTypeArguments[0];
        }

        public Type getType(){
            return mType;
        }
    }

    public static void main(String[] args) {
        Response<Data> dataResponse = new Response(new Data("数据"), 1, "成功");
        Gson gson = new Gson();

        String json = gson.toJson(dataResponse);
        System.out.println(json);
//        Response<Data> response = gson.fromJson(json,Response.class);
        /**
         *泛型类型被擦除:com.google.gson.internal.LinkedTreeMap
         * cannot be cast to com.example.xiangxueketang.lesson2.Deserialize$Data
         */
//        System.out.println(response.data.getClass());

        /**
         * 成功获取到类型com.example.xiangxueketang.Deserialize$Data
         * */
//        Type type =new TypeToken<Response<Data>>(){}.getType();
//        Response<Data> response = gson.fromJson(json,type);
//        System.out.println(response.data.getClass());

        Type type = new TypeReference<Response<Data>>(){}.getType();
        Response<Data> dataResponse1 = gson.fromJson(json,type);
        System.out.println(dataResponse1.data.getClass());
    }
}
