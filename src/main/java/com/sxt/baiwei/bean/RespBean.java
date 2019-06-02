package com.sxt.baiwei.bean;


public class RespBean {
    private int state;
    private String msg;
    private Object object;

    public static RespBean ok(String msg,Object object) {
        return new RespBean(200, msg, object);
    }

    public static RespBean ok(String msg) {
        return new RespBean(200, msg,null);
    }

    public static RespBean error(String msg,Object object) {
        return new RespBean(500, msg, object);
    }

    public static RespBean error(String msg) {
        return new RespBean(500, msg,null);
    }


    private RespBean() {
    }

    private RespBean(int state, String msg, Object object) {
        this.state = state;
        this.msg = msg;
        this.object = object;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
