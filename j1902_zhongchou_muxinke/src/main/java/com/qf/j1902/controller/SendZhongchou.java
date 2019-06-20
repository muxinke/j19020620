package com.qf.j1902.controller;

import com.qf.j1902.pojo.*;
import com.qf.j1902.service.*;

import com.sun.jdi.IntegerType;
import org.springframework.beans.NotWritablePropertyException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2019/5/31.
 */
@Controller
public class SendZhongchou {
    @Autowired
    private TagService tagService;
    @Autowired
    private ProjectTypeService projectTypeService;
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private HuiBaoService huiBaoService;
    @GetMapping("minecrowdfunding")
    public String minecrowdfunding(HttpSession session, Model model){
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
        return "minecrowdfunding";
    }
    //发起众筹
    @GetMapping("start")
    public String start(){
        return "start";
    }
    //校验是否进行了实名认证
    @PostMapping("jiaoyan")
    @ResponseBody
    public String  jiaoyan(HttpSession session){
        String zhanghu = (String) session.getAttribute("zhanghu");
        UserInfo userInfo = userInfoService.selectByZhanghu(zhanghu);
        String status=userInfo.getStatus();
        return status;
    }
    //阅读服务协议跳转step-1
    @GetMapping("start-step-1")
    public String start_step_1(Model model){
        //查找数据库项目类型
        ArrayList<ProjectType> findall = projectTypeService.findall();
        model.addAttribute("pTypes",findall);
        ArrayList<TagType> all = tagService.findAll();
        model.addAttribute("TagTypes",all);
        return "start-step-1";
    }
    //项目信息填写 第一步申请提交
    @PostMapping("step_1_submit")
    public String step_1_submit(@RequestParam(value = "inlineRadioOptions",defaultValue = "")String pType,
                                @RequestParam(value = "tag",defaultValue = "")String tag,
                                @RequestParam(value = "pname",defaultValue = "")String pname ,
                                @RequestParam(value = "pjianjie",defaultValue = "")String pjianjie,
                                @RequestParam(value = "jine",defaultValue = "")String jine,
                                @RequestParam(value = "days",defaultValue = "")String days,
                                @RequestParam(value = "myself",defaultValue = "")String myself,
                                @RequestParam(value = "xqmyself",defaultValue = "")String xqmyself,
                                @RequestParam(value = "number",defaultValue = "")String number,
                                @RequestParam(value = "kefunumber",defaultValue = "")String kefunumber,
                                MultipartFile img1,
                                MultipartFile img2,
                                HttpSession session,
                                HttpServletRequest request){
        if("".equals(pType)||("".equals(tag)||("".equals(pname))||"".equals(pjianjie))||("".equals(jine))||("".equals(days))||("".equals(myself))||("".equals(xqmyself))
                ||("".equals(number))||("".equals(kefunumber))){
            return "redirect:start-step-1";
        }
        //判断筹资金额
        String regex = "[1-9]\\d{2,9}";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(jine);
        if(m.matches()){
            int len = jine.length();
            System.out.println(len);
            if(len==10){
                String first = jine.substring(0,1);
                int i = Integer.parseInt(first);
                if(i>1){
                    return "redirect:start-step-1";
                }
            }
            int jin_i = Integer.parseInt(jine);
            System.out.println(jin_i);
            if(jin_i<100||jin_i>1000000000){
                return "redirect:start-step-1";
            }
        }else{
            return "redirect:start-step-1";
        }
        //判断筹资天数
        String regex2 = "[1-9]\\d{1}";
        Pattern pattern  = Pattern.compile(regex2);
        Matcher matcher = pattern.matcher(days);
        if(!matcher.matches()){
            return "redirect:start-step-1";
        }
        //判断手机号
        String regex3 = "1[3,4,5,7,8][0-9]{9}";
        Pattern p3 = Pattern.compile(regex3);
        Matcher m3= p3.matcher(number);
        if(!m3.matches()){
            return "redirect:start-step-1";
        }
        //获取img1的文件并上传到数据库
        String img1name =img1.getOriginalFilename();
        String img2name =img2.getOriginalFilename();
        if("".equals(img1name)||("".equals(img2name))){
            return "redirect:start-step-1";
        }
        String img1upload = request.getRealPath("img1upload");
        String img2upload = request.getRealPath("img2upload");
        File file1=null;
        File file2=null;
        String img1NewFilename=null;
        if(img1!=null&&img1name!=null&&img1name.length()>0){
           img1NewFilename = UUID.randomUUID()+img1name.substring(img1name.lastIndexOf("."));
            file1 = new File(img1upload,img1NewFilename);
        }
        String img2NewFilename=null;
        if(img2!=null&&img2name!=null&&img2name.length()>0){
            img2NewFilename = UUID.randomUUID()+img2name.substring(img2name.lastIndexOf("."));
            file2 = new File(img2upload,img2NewFilename);
        }
        try {
            file1.mkdirs();
            file2.mkdirs();
            img1.transferTo(file1);
            img2.transferTo(file2);
        }catch (Exception e){
            e.printStackTrace();
        }
        String touimg = "/img1upload/"+img1NewFilename;
        String pimg = "/img2upload/"+ img2NewFilename;
        //转换众筹日期
        //获取当前日期
        int days_i=Integer.parseInt(days);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Calendar calendar = Calendar.getInstance();
        Date time1 = calendar.getTime();
        String sendTime = sdf.format(time1);
        calendar.add(Calendar.DAY_OF_MONTH,days_i);
        Date time = calendar.getTime();
        String stopTime = sdf.format(time);
        //根据账户查找用户id
        String zhanghu=null;
        if(session.getAttribute("zhanghu")!=null){
          zhanghu = (String) session.getAttribute("zhanghu");
        }
        UserInfo userInfo = userInfoService.selectByZhanghu(zhanghu);
        int id= userInfo.getId();
        //判断项目数据库中有没有该账户id和状态为0的记录 ,如果有就去修改  如果没有就去添加
        ProjectInfo projectInfo_select =projectInfoService.select_jilu(id,"0");
        if(projectInfo_select!=null){
            int pid=projectInfo_select.getPid();
            ProjectInfo projectInfo = new ProjectInfo(pType,tag,pname,pjianjie,jine,days,touimg,pimg,myself,xqmyself,number,kefunumber,"0",id,sendTime,stopTime);
            projectInfo.setPid(pid);
            projectInfoService.update_jilu(projectInfo);
        }else{
            //将所有信息全部写到数据库中
            ProjectInfo projectInfo = new ProjectInfo(pType,tag,pname,pjianjie,jine,days,touimg,pimg,myself,xqmyself,number,kefunumber,"0",id,sendTime,stopTime);
            boolean add = projectInfoService.add(projectInfo);
        }
        return "redirect:start-step-2";
    }
    //第二步进入
    @GetMapping("start-step-2")
    public String start__step_2(){
        return "start-step-2";
    }
    //第二步设置回报  提交
    @PostMapping("huibaosubmit")
    public String huibaosubmit(@RequestParam(value = "htype",defaultValue = "")String htype,
                               @RequestParam(value ="money",defaultValue = "")String money,
                               @RequestParam(value ="neirong",defaultValue = "")String neirong,
                               MultipartFile himg,
                                @RequestParam(value ="hcount",defaultValue = "")String hcount,
                               @RequestParam(value ="isxiangou",defaultValue = "")String isxiangou,
                               @RequestParam(value ="xiangoucount",defaultValue = "1")String xiangoucount,
                               @RequestParam(value ="yunfei",defaultValue = "")String yunfei,
                               @RequestParam(value ="fapiao",defaultValue = "")String fapiao,
                               @RequestParam(value ="htime",defaultValue = "")String htime,Model model,HttpServletRequest request,HttpSession session){
        if("".equals(htype)||"".equals(money)||"".equals(neirong)||"".equals(hcount)||"".equals(isxiangou)||"".equals(xiangoucount)||"".equals(yunfei)
                ||"".equals(fapiao)||"".equals(htime)||"".equals(himg.getOriginalFilename())){

        }else{
            //将数据存入数据库,并写给前台
            //保存上传照片
            String upload=request.getRealPath("huibaoimg");
            String newFileName = UUID.randomUUID()+himg.getOriginalFilename().substring(himg.getOriginalFilename().lastIndexOf("."));
            File file = new File(upload,newFileName);
            try{
                file.mkdirs();
                himg.transferTo(file);
            }catch (Exception e){
                e.printStackTrace();
            }
            String himg_in="/huibaoimg/"+newFileName;

            //将所有数据写入数据库中
                //根据账户id和第一次提交的状态0查找 第一步存入的记录   找出项目id
            String zhanghu=null;
            if(session.getAttribute("zhanghu")!=null){
                zhanghu = (String) session.getAttribute("zhanghu");
            }
            UserInfo userInfo = userInfoService.selectByZhanghu(zhanghu);
            int id= userInfo.getId();
            //根据id和状态0查找刚才的项目记录
            ProjectInfo projectInfo_select =projectInfoService.select_jilu(id,"0");
            int pid=projectInfo_select.getPid();
            session.setAttribute("pid",pid);
            //将数据存入回报数据库
            HuiBao huiBao = new HuiBao();
            huiBao.setHtype(htype);
            huiBao.setMoney(money);
            huiBao.setNeirong(neirong);
            huiBao.setHimg(himg_in);
            huiBao.setHcount(hcount);
            huiBao.setIsxiangou(isxiangou);
            huiBao.setYunfei(yunfei);
            huiBao.setFapiao(fapiao);
            huiBao.setHtime(htime);
            huiBao.setPid(pid);
            huiBao.setXiangoucount(xiangoucount);
            huiBaoService.add(huiBao);
            //根据项目id查找所有的回报
            ArrayList<HuiBao> huiBaos  = huiBaoService.query(pid);
            ArrayList<HuiBao> huiBaos1 = new ArrayList<>();
            for(HuiBao huiBao_xx : huiBaos){
                String hcount_xx=huiBao_xx.getHcount();
                String isxiangou_xx=huiBao_xx.getIsxiangou();
                String yunfei_xx=huiBao_xx.getYunfei();
                if("0".equals(hcount_xx)){
                    huiBao_xx.setHcount("无限制");
                }
                if("0".equals(isxiangou_xx)){
                        huiBao_xx.setIsxiangou("不限购");
                }else{
                    huiBao_xx.setIsxiangou(huiBao_xx.getXiangoucount());
                }
                if("0".equals(yunfei_xx)){
                    huiBao_xx.setYunfei("包邮");
                }else{
                    huiBao_xx.setYunfei("¥"+yunfei_xx);
                }
                huiBaos1.add(huiBao_xx);
            }

            model.addAttribute("huibaos",huiBaos1);
        }
        return "start-step-2";
    }
    @GetMapping("start-step-3")
    public String start_step_(){
        return "start-step-3";
    }
    //保存草稿
    @PostMapping("caogao")
    @ResponseBody
    public int caogao(@RequestParam("yifubaozhanghu")String yifubaozhanghu,
                      @RequestParam("farenidcard")String farenidcard,HttpSession session){
        if("".equals(yifubaozhanghu)||"".equals(farenidcard)){
            return 2;
        }
        String regex="\\d{15}|\\d{18}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(farenidcard);
        if(!matcher.matches()){
            return 3;
        }
        Integer pidxx = (Integer)session.getAttribute("pid");
        System.out.println(pidxx);
        if(pidxx!=null){
            int  pid = pidxx;
            projectInfoService.add_caogao(yifubaozhanghu,farenidcard,pid,"1");
        }
        return 1;
    }
    @GetMapping("start-step-4")
    public String start_step_4(HttpSession session){
        Integer pidxx=(Integer)session.getAttribute("pid");
        if(pidxx!=null){
            int pid=pidxx;
            projectInfoService.update_zhuangtai_2("2",pid);
        }
        return "start-step-4";
    }
    @PostMapping("step-submit")
    @ResponseBody
    public  int step_submit(@RequestParam("yifubaozhanghu")String yifubaozhanghu,
                            @RequestParam("farenidcard")String farenidcard,HttpSession session){
        if("".equals(yifubaozhanghu)||"".equals(farenidcard)){
            return 2;
        }
        String regex="\\d{15}|\\d{18}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(farenidcard);
        if(!matcher.matches()){
            return 3;
        }
        Integer pidxx = (Integer)session.getAttribute("pid");
        System.out.println(pidxx);
        if(pidxx!=null){
            int  pid = pidxx;
            projectInfoService.add_caogao(yifubaozhanghu,farenidcard,pid,"2");
        }
        return 1;
    }
}
