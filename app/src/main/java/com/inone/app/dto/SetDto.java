package com.inone.app.dto;

public class SetDto {
    private String title;
    private String desc;
    private int icon;


    public SetDto() {
    }

    public SetDto(String title, int icon,String desc) {
        this.title = title;
        this.icon = icon;
        this.desc = desc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
