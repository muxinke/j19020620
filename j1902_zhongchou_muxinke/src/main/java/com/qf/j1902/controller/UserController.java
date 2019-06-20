package com.qf.j1902.controller;

import com.alibaba.druid.sql.ast.statement.SQLIfStatement;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.qf.j1902.pojo.ProjectInfo;
import com.qf.j1902.pojo.UserInfo;
import com.qf.j1902.service.ProjectInfoService;
import com.qf.j1902.service.UserInfoService;
import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import jdk.nashorn.internal.ir.ReturnNode;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.ibatis.annotations.Param;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.support.SimpleTriggerContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2019/5/27.
 */
@Controller
public class UserController {
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private ProjectInfoService projectInfoService;
    //退出系统
    @GetMapping("backIndex")
    public String backIndex(HttpSession session){
        session.invalidate();
        return "redirect:index.html";
    }
    //跳转用户登录界面
    @GetMapping("/login")
    public  String login(){
        return "login";
    }
    //跳转注册界面
    @GetMapping("/reg")
    public String ref(){
        return "reg";
    }
    //用户登录转后台或者前台
    @PostMapping ("denglu")
    public  String denglu(@RequestParam(value = "zhanghu",defaultValue = "") String zhanghu,
                          @RequestParam(value = "password",defaultValue = "") String password,
                          @RequestParam(value = "jstype",defaultValue = "") String jstype, Model model, HttpServletRequest request){
      /*  System.out.println(zhanghu+"1......");
        System.out.println(password+".........2");
        System.out.println(jstype+".........3");*/
        if("".equals(zhanghu)||"".equals(password)){
            model.addAttribute("tishi","请输入登录账户和登录密码");
            return  "/login";
        }else {
            UserInfo userInfo = new UserInfo();
            userInfo.setZhanghu(zhanghu);
            userInfo.setPassword(password);
            if("user".equals(jstype)){
                ArrayList<UserInfo> all = userInfoService.findByjsType(jstype);
                if(all.contains(userInfo)){
                    request.getSession().setAttribute("zhanghu",zhanghu);
                    model.addAttribute("zhanghu",zhanghu);
                    return  "redirect:main";
                }

            }else if("member".equals(jstype)){
                ArrayList<UserInfo> all = userInfoService.findByjsType(jstype);
                if(all.contains(userInfo)){
                    request.getSession().setAttribute("zhanghu",zhanghu);
                    return  "redirect:member";
                }
            }
        }
        model.addAttribute("tishi","用户不存在或者密码不正确");
        return  "/login";
    }
    //注册控制
    @PostMapping("zhuce")
    public String zhuce(@RequestParam(value = "zhanghu",defaultValue = "") String zhanghu,
                        @RequestParam(value = "password",defaultValue = "") String password,
                        @RequestParam(value = "email",defaultValue = "")String email,
                        @RequestParam(value = "jstype",defaultValue = "") String jstype, Model model, HttpServletRequest request) {
        if ("".equals(zhanghu) || "".equals(password) || "".equals(email)) {
            model.addAttribute("tishi", "请输入全部信息");
        }
            if (jstype.equals("member")) {
                ArrayList<UserInfo> all = userInfoService.findByjsType(jstype);
                for(UserInfo userInfo:all){
                    if(zhanghu.equals(userInfo.getZhanghu())||email.equals(userInfo.getEmail())){
                        model.addAttribute("tishi","用户名或邮箱已注册");
                        return "reg";
                    }
                }
                boolean insert = userInfoService.insert(zhanghu, password, email, jstype);
                if (insert) {
                    return "login";
                }
            } else {
                System.out.println("还没有注册管理");
            }
            return "reg";
        }
        //进后台
    @GetMapping("main")
    public  String main(){
        return "main";
    }
    //近前台
    @GetMapping("member")
    public  String member(Model model,HttpSession session){
        String zhanghu  =(String ) session.getAttribute("zhanghu");
        //根据账户查找当前用户状态
        UserInfo userInfo=userInfoService.selectByZhanghu(zhanghu);
        if(userInfo!=null){
            String status=userInfo.getStatus();
            if("0".equals(status)){
                model.addAttribute("identifyTishi","未实名认证");
            }else if("1".equals(status)){
                model.addAttribute("identifyTishi","认证审核中");
            }else if("2".equals(status)){
                model.addAttribute("identifyTishi","认证已通过");
            }else if("3".equals(status)){
                model.addAttribute("identifyTishi","认证驳回,请重新提交认证");
            }
        }


        return "member";
    }
    /*//退出系统
    @GetMapping("index")
    public String index(){
        return "redirect:index.html";
    }*/
    //用户维护
    @GetMapping("user")
    public  String user(Model model){
        //查询所有用户信息,并存到request情求中
        ArrayList<UserInfo> all = userInfoService.findAll();
        model.addAttribute("userinfos",all);
        return "user";
    }
    //assignRole跳转
    @GetMapping("assignRole")
    public  String assignRole(){
        return  "assignRole";
    }
    //edit跳转
    @GetMapping("edit")
    public String edit(@RequestParam("id")int id, Model model, HttpSession session){
        //根据id查询当前用户
        UserInfo userInfo = userInfoService.selectByid(id);
        session.setAttribute("uid",id);
        System.out.println(userInfo);
        model.addAttribute("userinfo",userInfo);
        return "edit";
    }
    //修改成功
    @PostMapping("editSuccess")
    public String editSuccess(@RequestParam("zhanghu")String zhanghu,
                              @RequestParam(value = "username",defaultValue = "")String username,
                              @RequestParam("email")String email,HttpSession session){
        Integer id = (Integer) session.getAttribute("uid");
        boolean update = userInfoService.update(zhanghu, username, email,id);
        return "redirect:user";
    }
    //新增用户功能
    @GetMapping("add")
    public String add(){
        return "add";
    }
    //新增用户成功
    @PostMapping("addSuccess")
    public String addSuccess(@RequestParam("zhanghu")String zhanghu,
                             @RequestParam(value = "username")String username,
                             @RequestParam("email")String email,Model model){
        if("".equals(zhanghu)||"".equals(email)){
            model.addAttribute("tishi","请输入账号和邮箱信息");
            return "add";
        }
        String password="123456";
        boolean insert = userInfoService.addUser(zhanghu, password, username, email);
        return "redirect:user";
    }
    //单个删除用户的方法
    @PostMapping("delete")
    @ResponseBody
    public String  delete(@RequestParam("id")int id){
       /* boolean delete=userInfoService.delete(id);*/
        return "1";
    }
    //模糊查询,按照条件查询
    @PostMapping("query")
    @ResponseBody
    public ArrayList<UserInfo> query(@RequestParam("query")String query){
        System.out.println(query);
        ArrayList<UserInfo> query1 = userInfoService.query(query);
        System.out.println(query1);
        return query1;
    }
    //角色维护
    @GetMapping("role")
    public  String role(){
        return "role";
    }
    //角色维护
    @GetMapping("permission")
    public  String permission(){
        return "permission";
    }

    //业务审核  实名认证
    @GetMapping("auth_cert")
    public String cert(Model model){
        //查询10条记录,显示在第一页根据status查询待审批的信息
        ArrayList<UserInfo> userInfos = userInfoService.selectByStatus("1", 0, 10);
        model.addAttribute("indentify1",userInfos);
        return "auth_cert";
    }
    //业务审核  广告审核
    @GetMapping("auth_adv")
    public String adv(){
        return "auth_adv";
    }
    //业务审核 项目审核
    @GetMapping("auth_project")
    public String project(Model model){
        //从数据库中查找状态为2的项目
        ArrayList<ProjectInfo> projectInfos=projectInfoService.queryByzhuangtai("2");
        model.addAttribute("projectInfos",projectInfos);
        return "auth_project";
    }
    //实名认证页面
    @GetMapping("accttype")
    public String accttype(){
        return "accttype";
    }
    //用户类型选择存储
    @PostMapping("userType")
    @ResponseBody
    public void userType(@RequestParam("userType")String type,HttpSession session){
        session.setAttribute("userType",type);
        System.out.println((String)session.getAttribute("userType"));
    }
    //返回和跳转进入apply
    @GetMapping("apply")
    public String apply(Model model){
        return "apply";
    }
    //apply跳转进入apply1,提交数据
    @PostMapping("apply-1")
    public String apply11(@RequestParam(value = "realname",defaultValue = "")String username,
                          @RequestParam(value = "cardnum",defaultValue = "")String idcard,
                          @RequestParam(value = "tel",defaultValue = "")String phoneNumber,HttpSession session){
        System.out.println(username);
        System.out.println(idcard);
        System.out.println(phoneNumber);
        if("".equals(username)||"".equals(idcard)||"".equals(phoneNumber)){
            return "redirect:apply";
        }else {
            session.setAttribute("username",username);
            session.setAttribute("idcard",idcard);
            session.setAttribute("phoneNumber",phoneNumber);
        }
        return "redirect:apply-1";
        }
        //apply1跳转进apply2,保存图片
        @PostMapping("apply-2")
        public String apply2(MultipartFile filebox, HttpServletRequest request){
            String filePath = request.getRealPath("/uploadImg");
            String originalFilename = filebox.getOriginalFilename();
            String newFileName=null;
            if (filebox != null && originalFilename != null && originalFilename.length() > 0) {
                newFileName = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
                File newFile = new File(filePath,newFileName);
                try {
                    newFile.mkdirs();
                    filebox.transferTo(newFile);
                    request.getSession().setAttribute("imgsrc","uploadImg/"+newFileName);
                } catch (Exception e) {
                }
            }
            return "apply-2";
        }
        //apply2返回apply1
    @GetMapping("apply-1")
        public String apply1(){
            return "apply-1";
    }
    /*//2跳转至3 输入接收验证码的邮箱
    @PostMapping("apply-3")
    public String apply33(@RequestParam("exampleInputEmail1")String email){
        //判断邮箱是否符合
        return "redirect:apply-3";
    }*/
    @GetMapping("apply-3")
    public String apply3(){
        System.out.println("33333");
        return "apply-3";
    }

    //发送邮箱验证码
    @RequestMapping(value = "sendyzm",method = RequestMethod.POST)
    @ResponseBody
    public int yxyz(HttpServletRequest request,@RequestParam(defaultValue ="a" ) String exam){
       request.getSession().setAttribute("exam",exam);
        String regEx1 ="^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";
        Pattern p = Pattern.compile(regEx1);
        Matcher m = p.matcher(exam);
        if(m.matches()) {
            try {
                HtmlEmail htmlEmail = new HtmlEmail();
                htmlEmail.setHostName("smtp.qq.com");
                htmlEmail.setCharset("utf-8");
                htmlEmail.addTo(exam);
                htmlEmail.setFrom("415429746@qq.com", "众筹系统");
                htmlEmail.setAuthentication("415429746@qq.com", "ypaexypocwgabjeb");
                htmlEmail.setSubject("实名认证验证码");
                int a = (int) ((Math.random() * 9 + 1) * 100000);
                String aa = String.valueOf(a);
                HttpSession session = request.getSession();
                session.setAttribute("SessionKey", aa);
                htmlEmail.setMsg("尊贵的会员：您的验证码为" + "<h3>" + aa + "</h3>");
                htmlEmail.send();
                System.out.println("已发送");
                return 200;
            } catch (EmailException e) {
                e.printStackTrace();
            }
        }
        return 400;
    }
    //重新发送验证码
    @RequestMapping(value = "sendyzm2",method = RequestMethod.POST)
    @ResponseBody
    public int yxyz2(HttpServletRequest request){
        String exam = (String) request.getSession().getAttribute("exam");
        String regEx1 ="^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";
        Pattern p = Pattern.compile(regEx1);
        Matcher m = p.matcher(exam);
        if(m.matches()) {
            try {
                HtmlEmail htmlEmail = new HtmlEmail();
                htmlEmail.setHostName("smtp.qq.com");
                htmlEmail.setCharset("utf-8");
                htmlEmail.addTo(exam);
                htmlEmail.setFrom("415429746@qq.com", "众筹系统");
                htmlEmail.setAuthentication("415429746@qq.com", "ypaexypocwgabjeb");
                htmlEmail.setSubject("实名认证验证码");
                int a = (int) ((Math.random() * 9 + 1) * 100000);
                String aa = String.valueOf(a);
                HttpSession session = request.getSession();
                session.setAttribute("SessionKey", aa);
                htmlEmail.setMsg("尊贵的会员：您的验证码为" + "<h3>" + aa + "</h3>");

                htmlEmail.send();
                System.out.println("已发送");
                return 200;
            } catch (EmailException e) {
                e.printStackTrace();
            }
        }
        return 400;
    }
    //实名认证完成,提交全部数据
    @PostMapping("identify")
    public String identify(@RequestParam(value = "yanzhengma",defaultValue = "")String yanzhengma, HttpSession session,Model model){
        String  SessionKey= (String)session.getAttribute("SessionKey");
        if("".equals(yanzhengma)){
            model.addAttribute("tishi","未输入验证码,请输入邮箱收到的验证码");
            return "apply-3";
        }else if(!yanzhengma.equals(SessionKey)){
            model.addAttribute("tishi","验证码输入错误,请重新发送验证码");
            session.removeAttribute("SessionKey");
            return "apply-3";
        }
        String zhanghu=(String)session.getAttribute("zhanghu");
        String  zhType= (String)session.getAttribute("userType");
       String  username= (String)session.getAttribute("username");
       String  idcard= (String)session.getAttribute("idcard");
       String  phoneNumber= (String)session.getAttribute("phoneNumber");
       String  userImg= (String)session.getAttribute("imgsrc");
        System.out.println(userImg);
        //验证成功,将所有数据保存到数据库
        if(zhType!=null&&username!=null&&idcard!=null&&phoneNumber!=null&&userImg!=null&&zhanghu !=null){
            String status="1";
            UserInfo userInfo = new UserInfo();
            userInfo.setUsername(username);
            userInfo.setPhoneNumber(phoneNumber);
            userInfo.setUserImg(userImg);
            userInfo.setStatus(status);
            userInfo.setZhType(zhType);
            userInfo.setZhanghu(zhanghu);
            userInfo.setIdcard(idcard);
            try {
                boolean b = userInfoService.updateUserByIndentify(userInfo);
            }catch (Exception e){

            }
            //model.addAttribute("identifyTishi","认证审核中");
        }

        return "redirect:member";
    }
    //认证通过
    @PostMapping("indentifySyccess")
    public  String indentifySyccess(@RequestParam("zhanghu")String zhanghu, @RequestParam("result")String result,
                                 @RequestParam(value = "idea",defaultValue = "")String idea,Model model){
       if("true".equals(result)){
           UserInfo userInfo = new UserInfo();
           userInfo.setZhanghu(zhanghu);
           boolean b=userInfoService.updateUserByzhanghu(zhanghu,"2",idea);
       }else if("false".equals(result) && "".equals(idea)){
           model.addAttribute("ideatishi","请描述审批未通过原因");
           return "forward:submitIndentify";
       }else if("false".equals(result)){
           boolean b = userInfoService.updateUserByzhanghu(zhanghu, "3", idea);
       }
        return "redirect:auth_cert";
    }
    //提交认证操作,同意还是不同意
    @GetMapping("submitIndentify")
    public String submitIndentify(@RequestParam("zhanghu")String zhanghu,Model model){
        UserInfo userInfo = userInfoService.selectByZhanghu(zhanghu);
        model.addAttribute("single_userinfo",userInfo);
        return "single";
    }
    @PostMapping("submitIndentify")
    public String submitIndentify1(@RequestParam("zhanghu")String zhanghu,Model model){
        UserInfo userInfo = userInfoService.selectByZhanghu(zhanghu);
        model.addAttribute("single_userinfo",userInfo);
        return "single";
    }
    //用户查看驳回意见
    @GetMapping("lookinfo")
    public String lookinfo(HttpSession session,Model model){
        String zhanghu=(String)session.getAttribute("zhanghu");
        if(zhanghu !=null){
            UserInfo userInfo = userInfoService.selectByZhanghu(zhanghu);
            model.addAttribute("info", userInfo.getSuggestion());
        }
        return "lookinfo";
    }
    //项目标签
    @GetMapping("tag")
    public String tag(){
        return "tag";
    }
    //操作项目同意还是不同意
    @GetMapping("project_xq")
    public String project_xq(@RequestParam(value = "pid")int pid,Model model){
        //根据pid查出该项目的信息
        ProjectInfo projectInfo=projectInfoService.selectBypid(pid);
        model.addAttribute("projectInfo_xq",projectInfo);
        return "project_xq";
    }
    //项目审核通过
    @GetMapping("project_tongyi")
    public String project_tongyi(@RequestParam("pid")int pid){
        projectInfoService.update_shenpiBypid(pid,"3");
        return "redirect:auth_project";
    }
    //项目审核拒绝
    @GetMapping("project_butongyi")
    public String project_butongyi(@RequestParam("pid")int pid,
                                   @RequestParam(value = "yijian",defaultValue = "")String yijian){
        if("".equals(yijian)){
            return "forward:project_xq";
        }
        ProjectInfo projectInfo = new ProjectInfo();
        projectInfo.setPid(pid);
        projectInfo.setYijian(yijian);
        projectInfo.setZhuangtai("4");
        projectInfoService.add_shenpi(projectInfo);
        return "redirect:auth_project";
    }
    //参数管理
    @GetMapping("param")
    public String param(){
        return  "param";
    }
}
