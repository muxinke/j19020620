package com.qf.j1902.pojo;

import lombok.Data;

import javax.swing.text.html.HTML;
import java.util.Date;

/**
 * Created by Administrator on 2019/6/1.
 */
public class ProjectInfo {
   /* `pid` int(11) NOT NULL AUTO_INCREMENT,
  `pType` varchar(20) DEFAULT NULL,
  `tag` varchar(100) DEFAULT NULL,
  `pname` varchar(255) DEFAULT NULL,
  `pjianjie` varchar(255) DEFAULT NULL,
  `jine` varchar(20) DEFAULT NULL,
  `days` varchar(10) DEFAULT NULL,
  `img1` varchar(50) DEFAULT NULL,
  `img2` varchar(50) DEFAULT NULL,
  `myself` varchar(100) DEFAULT NULL,
  `xqmyself` varchar(255) DEFAULT NULL,
  `number` varchar(15) DEFAULT NULL,
  `kefunumber` varchar(15) DEFAULT NULL,
  `zhuangtai` varchar(10) DEFAULT '0',
            `id` int(11) DEFAULT NULL,*/
    private int pid;
    private String pType;
    private String tag;
    private String pname;
    private String pjianjie;
    private String jine;
    private String days;
    private String touimg;
    private String pimg;
    private String myself;
    private String xqmyself;
    private String number;
    private String kefunumber;
    private String zhuangtai;
    private int id;
    private String sendTime;
    private String stopTime;
    private String yijian;
    public ProjectInfo() {
    }

    public ProjectInfo(String pType, String tag, String pname, String pjianjie, String jine, String days, String touimg, String pimg, String myself, String xqmyself, String number, String kefunumber, String zhuangtai, int id, String sendTime, String stopTime) {
        this.pType = pType;
        this.tag = tag;
        this.pname = pname;
        this.pjianjie = pjianjie;
        this.jine = jine;
        this.days = days;
        this.touimg = touimg;
        this.pimg = pimg;
        this.myself = myself;
        this.xqmyself = xqmyself;
        this.number = number;
        this.kefunumber = kefunumber;
        this.zhuangtai = zhuangtai;
        this.id = id;
        this.sendTime = sendTime;
        this.stopTime = stopTime;
    }

    public String getYijian() {
        return yijian;
    }

    public void setYijian(String yijian) {
        this.yijian = yijian;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getpType() {
        return pType;
    }

    public void setpType(String pType) {
        this.pType = pType;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPjianjie() {
        return pjianjie;
    }

    public void setPjianjie(String pjianjie) {
        this.pjianjie = pjianjie;
    }

    public String getJine() {
        return jine;
    }

    public void setJine(String jine) {
        this.jine = jine;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getTouimg() {
        return touimg;
    }

    public void setTouimg(String touimg) {
        this.touimg = touimg;
    }

    public String getPimg() {
        return pimg;
    }

    public void setPimg(String pimg) {
        this.pimg = pimg;
    }

    public String getMyself() {
        return myself;
    }

    public void setMyself(String myself) {
        this.myself = myself;
    }

    public String getXqmyself() {
        return xqmyself;
    }

    public void setXqmyself(String xqmyself) {
        this.xqmyself = xqmyself;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getKefunumber() {
        return kefunumber;
    }

    public void setKefunumber(String kefunumber) {
        this.kefunumber = kefunumber;
    }

    public String getZhuangtai() {
        return zhuangtai;
    }

    public void setZhuangtai(String zhuangtai) {
        this.zhuangtai = zhuangtai;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getStopTime() {
        return stopTime;
    }

    public void setStopTime(String stopTime) {
        this.stopTime = stopTime;
    }
}
