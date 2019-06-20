package com.qf.j1902.pojo;

import lombok.Data;

import java.util.ArrayList;

/**
 * Created by Administrator on 2019/5/31.
 */

public class TagType {
    private int tagid;
    private String tagType;
    private ArrayList<TagMiaoShu> tagMiaoShus;

    public TagType() {
    }

    public TagType(int tagid, String tagType, ArrayList<TagMiaoShu> tagMiaoShus) {
        this.tagid = tagid;
        this.tagType = tagType;
        this.tagMiaoShus = tagMiaoShus;
    }

    public ArrayList<TagMiaoShu> getTagMiaoShus() {
        return tagMiaoShus;
    }

    public void setTagMiaoShus(ArrayList<TagMiaoShu> tagMiaoShus) {
        this.tagMiaoShus = tagMiaoShus;
    }

    public int getTagid() {
        return tagid;
    }

    public void setTagid(int tagid) {
        this.tagid = tagid;
    }

    public String getTagType() {
        return tagType;
    }

    public void setTagType(String tagType) {
        this.tagType = tagType;
    }

    @Override
    public String toString() {
        return "TagType{" +
                "tagid=" + tagid +
                ", tagType='" + tagType + '\'' +
                ", tagMiaoShus=" + tagMiaoShus +
                '}';
    }
}
