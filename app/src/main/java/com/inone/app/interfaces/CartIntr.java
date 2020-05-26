package com.inone.app.interfaces;

public interface CartIntr {
    public void delItem(int position);
    public void addItem(int position, int qty);
    public void subItem(int position, int qty);
    public void editItem(int position);
}
