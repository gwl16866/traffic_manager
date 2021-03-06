package com.hy.traffic.saftyEdu.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author gwl
 * @since 2020-07-25
 */
@CrossOrigin
@RestController
@RequestMapping("/saftyEdu")
public class SaftyeduController {
    @Autowired
    SaftyeduServiceImpl saftyeduService;
    @Autowired
    TeachinfoServiceImpl teachinfoService;

    //查询
    @CrossOrigin
    @RequestMapping("/selectSaftyEdu")
    public ResultData selectSaftyEdu(Saftyedu saftyedu, Integer learnType) {
        System.out.println(learnType + "=====================" + learnType);

        List<Saftyedu> saftyeduList = saftyeduService.selectSaftyEdu(learnType);

        for (Saftyedu a : saftyeduList) {
            //查询参训学员人数
            a.setCount(saftyeduService.selectStudentCount(a.getId()));
            //修改培训状态
            Date date = new Date();
            if (date.compareTo(a.getStartTime()) > 0 && date.compareTo(a.getEndTime()) < 0) {

            } else {
                a.setStatus(2);
                saftyeduService.updateStatus(a.getId(), a.getStatus());
            }
        }

        ResultData resultData = new ResultData();
        resultData.setData(saftyeduList);
        return resultData;
    }

    //查询参训学员
    @CrossOrigin
    @RequestMapping("/selectStudent")
    public ResultData selectStudent(Integer id) {
        List<Studentinfo> studentinfoList = saftyeduService.selectStudent(id);

        ResultData resultData = new ResultData();
        resultData.setData(studentinfoList);
        return resultData;
    }

    //在安全教育中删除学员
    @CrossOrigin
    @RequestMapping("/deleteStudent")
    public Integer deleteStudent(Integer studentid, Integer saftyid) {
        System.out.println(saftyid + studentid);
        try {
            saftyeduService.deleteStudent(studentid, saftyid);
        } catch (Exception e) {
            return 0;
        }
        return 1;
    }

    //查询所有学员  未被当前活动添加过的学员
    @CrossOrigin
    @RequestMapping("/selectAllStudent")
    public ResultData selectAllStudent(Integer saftyid) {
        List<Studentinfo> saftyeduList = saftyeduService.selectAllStudent(saftyid);
        ResultData resultData = new ResultData();
        resultData.setData(saftyeduList);
        return resultData;
    }

    //查询所有学员
    @CrossOrigin
    @RequestMapping("/selectAllStu")
    public ResultData selectAllStu() {
        List<Studentinfo> saftyeduList = saftyeduService.selectAllStu();
        ResultData resultData = new ResultData();
        resultData.setData(saftyeduList);
        return resultData;
    }

    //批量添加学员
    @CrossOrigin
    @RequestMapping("/batchAddStudent")
    public Integer batchAddStudent(Integer saftyid, Integer[] batchList) {
        try {
            for (int i = 0; i < batchList.length; i++) {
                saftyeduService.batchAddStudent(saftyid, batchList[i]);
            }
        } catch (Exception e) {
            return 0;
        }
        return 1;
    }

    //查询所有课程
    @CrossOrigin
    @RequestMapping("/treeList")
    public List<Tree> treeList() {

        List<Tree> treeList = saftyeduService.queryclassDetail();

        return treeList;
    }

    //添加培训
    @CrossOrigin
    @RequestMapping("/addSaftyEdu")
    public Integer addSaftyEdu(@RequestBody Saftyedutwo addEdu) {
        try {
            StringBuilder sr = new StringBuilder();
            for (int i = 0; i < addEdu.getLession().length; i++) {
                sr.append(addEdu.getLession()[i]);
                if (i < addEdu.getLession().length - 1) {
                    sr.append(",");
                }
            }
            //添加培训
            saftyeduService.addSaftyEdu(addEdu.getTheme(), addEdu.getStartTime(), addEdu.getEndTime(), sr.toString(), addEdu.getManager(), addEdu.getTestPeople(), addEdu.getLearnType(), addEdu.getLearnTime());
            //查询最大Id
            Integer maxId = saftyeduService.selectMaxId();
            //添加参训人员
            for (int i = 0; i < addEdu.getStudent().length; i++) {
                saftyeduService.batchAddStudent(maxId, addEdu.getStudent()[i]);
            }
        } catch (Exception e) {
            return 0;
        }
        return 1;
    }

    @Autowired
    private ISaftyeduService iSaftyeduService;

    @Autowired
    private StudentinfoMapper studentinfoMapper;

    @Autowired
    private SaftyeduMapper saftyeduMapper;

    //默认查询所有并且分页
    @RequestMapping("/querySaftyedu")
    public PageJson querySaftyedu(Integer currpage, Integer pagesize,PageJson pageJson) {

        PageJson pageJson2 = iSaftyeduService.querySaftyedu(currpage, pagesize,pageJson);

        return pageJson2;
    }

    //根据日期进行条件查询并分页
    @RequestMapping("/querySaftyeduMonth")
    public PageJson querySaftyeduMonth(String yuefen,Integer currpage, Integer pagesize,PageJson pageJson) throws ParseException {

         PageJson pageJson2=iSaftyeduService.querySaftyeduMonth(yuefen, currpage,  pagesize, pageJson);

        return pageJson2;
    }
    //根据said查数据
    @RequestMapping("/queryStudentByid")
    public PageJson queryStudentByid(Integer said,Integer currpage, Integer pagesize,PageJson pageJson) {

        return iSaftyeduService.querySaftyduStudentinfoByid(said, currpage,  pagesize, pageJson);
    }

    @RequestMapping("/queryByid")
    public PageJson queryByid(Integer said,String select ,String neirong,Integer currpage, Integer pagesize,PageJson pageJson){
        Page page = PageHelper.startPage(currpage, pagesize, true);
        pageJson.setData(studentinfoMapper.queryByid(said,select,neirong));
        pageJson.setCount(page.getTotal());
        return pageJson;
    }

    @RequestMapping("/xiazaipdf")
    public String xiazaipdf(Integer[] arr, Integer said, HttpServletRequest request) throws ParseException {

System.out.println(said);

        Saftyedu saftyedu= saftyeduMapper.querySaftyeduByid(said);

        System.out.println(saftyedu.getStartTime()+"****"+saftyedu.getEndTime());

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");



        Date year= dateFormat.parse(saftyedu.getStartTime());
        System.out.println(year+"********************");
        String month=   "";
        String datatime= "";


        for (int i=0;i<arr.length;i++){
            System.out.println(arr[i]);
           Studentinfo studentinfo= studentinfoMapper.querystuByid(arr[i]);




            Map paraMap = new HashMap();

            paraMap.put("id", studentinfo.getId());

            paraMap.put("realName", studentinfo.getRealName());

            paraMap.put("cardId",studentinfo.getCardId());

            paraMap.put("year", year);

            paraMap.put("month", month);

            paraMap.put("theme", saftyedu.getTheme());

            paraMap.put("datatime", datatime);

            try {

//2 读入pdf表单

                PdfReader reader = new PdfReader("src/main/java/com/hy/traffic/saftyEdu/entity/prove.pdf");

//3 根据表单生成一个新的pdf

                PdfStamper ps = new PdfStamper(reader, new FileOutputStream("C:\\Users\\HAO\\Downloads\\"+studentinfo.getRealName()+"证明.pdf"));

//            System.out.println( request.getServletContext().getRealPath("/pdf"));


//4 获取pdf表单

                AcroFields s = ps.getAcroFields();

//5给表单添加中文字体 这里采用系统字体。不设置的话，中文可能无法显示

                BaseFont bf = BaseFont.createFont("C:/WINDOWS/Fonts/SIMSUN.TTC,1", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);

                s.addSubstitutionFont(bf);

//6遍历pdf表单表格，同时给表格赋值

                Map fieldMap = s.getFields();


                Set set = fieldMap.entrySet();


                Iterator iterator = set.iterator();

                while (iterator.hasNext()) {

                    Map.Entry entry = (Map.Entry) iterator.next();

                    String key = (String) entry.getKey();

                    if (paraMap.get(key) != null) {


                        s.setField(key, "" + paraMap.get(key));

                    }

                }

                ps.setFormFlattening(true); // 这句不能少
                ps.getWriter();

                ps.close();

                reader.close();

            } catch (IOException e) {

// TODO 自动生成的 catch 块

                e.printStackTrace();

            } catch (DocumentException e) {

// TODO 自动生成的 catch 块

                e.printStackTrace();

            } catch (Exception e) {
                e.printStackTrace();
            }

        }


        return "下载成功！";
    }


    @RequestMapping("/genjustuxiazaipdf")
    public String genjustuxiazaipdf(Integer[] arr, HttpServletRequest request) {


        for (int i=0;i<arr.length;i++){

             List<Saftyedu> saftyeduList= studentinfoMapper.queryStudentinfoBystuid(arr[i]);

            for(Saftyedu saftyedu:saftyeduList){
                Saftyedu saftyedu2= saftyeduMapper.querySaftyeduByid(saftyedu.getId());

//                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
                SimpleDateFormat dateFormat2 = new SimpleDateFormat("MM");
                SimpleDateFormat dateFormat3 = new SimpleDateFormat("yyyy-MM-dd");
                String  year= null;
                String  month= null;
                String  datatime= null;
                try {
                    year = dateFormat.format(saftyedu2.getStartTime());
                    month = dateFormat2.format(saftyedu2.getStartTime());
                    datatime = dateFormat3.format(saftyedu2.getEndTime());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Studentinfo studentinfo= studentinfoMapper.querystuByid(arr[i]);




                Map paraMap = new HashMap();

                paraMap.put("id", studentinfo.getId());

                paraMap.put("realName", studentinfo.getRealName());

                paraMap.put("cardId",studentinfo.getCardId());

                paraMap.put("year", year);

                paraMap.put("month", month);

                paraMap.put("theme", saftyedu.getTheme());

                paraMap.put("datatime", datatime);

                try {

//2 读入pdf表单

                    PdfReader reader = new PdfReader("src/main/java/com/hy/traffic/saftyEdu/entity/prove.pdf");

//3 根据表单生成一个新的pdf

                    PdfStamper ps = new PdfStamper(reader, new FileOutputStream("C:\\Users\\HAO\\Downloads\\"+studentinfo.getRealName()+"证明.pdf"));

//            System.out.println( request.getServletContext().getRealPath("/pdf"));


//4 获取pdf表单

                    AcroFields s = ps.getAcroFields();

//5给表单添加中文字体 这里采用系统字体。不设置的话，中文可能无法显示

                    BaseFont bf = BaseFont.createFont("C:/WINDOWS/Fonts/SIMSUN.TTC,1", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);

                    s.addSubstitutionFont(bf);

//6遍历pdf表单表格，同时给表格赋值

                    Map fieldMap = s.getFields();


                    Set set = fieldMap.entrySet();


                    Iterator iterator = set.iterator();

                    while (iterator.hasNext()) {

                        Map.Entry entry = (Map.Entry) iterator.next();

                        String key = (String) entry.getKey();

                        if (paraMap.get(key) != null) {


                            s.setField(key, "" + paraMap.get(key));

                        }

                    }

                    ps.setFormFlattening(true); // 这句不能少
                    ps.getWriter();

                    ps.close();

                    reader.close();

                } catch (IOException e) {

// TODO 自动生成的 catch 块

                    e.printStackTrace();

                } catch (DocumentException e) {

// TODO 自动生成的 catch 块

                    e.printStackTrace();

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        }


        return "下载成功！";
    }





    //删除培训
    @CrossOrigin
    @RequestMapping("/deleteSaftyedu")
    public Integer deleteSaftyedu(Integer id) {
        try {
            saftyeduService.deleteSaftyedu(id);
        } catch (Exception e) {
            return 0;
        }
        return 1;
    }

    //查看课程
    @CrossOrigin
    @RequestMapping("/classDetailList")
    public List<ClassDetail> classDetailList(Integer id) {
        Saftyedu saftyedu = saftyeduService.selectlession(id);
        List<ClassDetail> classDetailList = saftyeduService.classDetailList(saftyedu.getLession());
        return classDetailList;
    }
}
