package com.example.administrator.demoxiao2.clazz;

/**
 * Created by Administrator on 2018/3/2.
 */

public class Mybean {
    private int Image;
    private String Text;
    private Class Fragment;

    public Mybean(int image, String text, Class fragment) {
        Image = image;
        Text = text;
        Fragment = fragment;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }

    public Class getFragment() {
        return Fragment;
    }

    public void setFragment(Class fragment) {
        Fragment = fragment;
    }
}
