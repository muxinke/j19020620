package com.qf.j1902.pojo;

import lombok.Data;

/**
 * Created by Administrator on 2019/6/2.
 */
@Data
public class HuiBao {
   /* `hid` int(11) NOT NULL AUTO_INCREMENT,
  `htype` varchar(5) DEFAULT NULL,
  `money` varchar(10) DEFAULT NULL,
  `neirong` varchar(255) DEFAULT NULL,
  `himg` varchar(100) DEFAULT NULL,
  `hcount` varchar(5) DEFAULT NULL,
  `isxiangou` varchar(10) DEFAULT NULL,
  `yunfei` varchar(10) DEFAULT NULL,
  `fapiao` varchar(2) DEFAULT NULL,
  `htime` varchar(10) DEFAULT NULL,
  `pid` int(11) DEFAULT NULL,*/
    private  int hid;
    private  String htype;
    private String money;
    private String neirong;
    private  String himg;
    private String hcount;
    private  String isxiangou;
    private  String xiangoucount;
    private String yunfei;
    private String fapiao;
    private String htime;
    private  int pid;

}
