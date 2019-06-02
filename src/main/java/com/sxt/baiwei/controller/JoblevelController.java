package com.sxt.baiwei.controller;

import com.sxt.baiwei.Utils.PoiUtils;
import com.sxt.baiwei.bean.JObLevel;
import com.sxt.baiwei.bean.RespBean;
import com.sxt.baiwei.service.JobLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/system/basic/jl")
public class JoblevelController {

    @Autowired
    JobLevelService jobLevelService;

    SimpleDateFormat dateFormat = new SimpleDateFormat("/yyyy/MM/dd/");


//    接收上传的文件
    @PostMapping("/upload2")
    public RespBean uploadFile2(MultipartFile file, HttpServletRequest request) throws IOException {

        String format = dateFormat.format(new Date());
       String realPath= request.getServletContext().getRealPath("/upload")+format;
        File file1 = new File(realPath);
        if (!file1.exists()) {
            file1.mkdirs();
        }
        String oldName = file.getOriginalFilename();
        String newName = UUID.randomUUID().toString() + oldName.substring(oldName.lastIndexOf("."));
        file.transferTo(new File(file1, newName));
        String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/upload" + format + newName;
        System.out.println(url);

        return RespBean.ok("上传成功");
    }


    //    解析上传的文件
    @PostMapping("/upload")
    public RespBean uploadFile(MultipartFile file, HttpServletRequest request) throws IOException {
        List<JObLevel> list = PoiUtils.parse2List(file);
        if (jobLevelService.addJobLevelList(list)>=1) {

            return RespBean.ok("上传成功");
        }
        return RespBean.error("上传失败");
    }



    //    导出excel的请求
    @GetMapping("/exportExcel")
    public ResponseEntity<byte[]> exportExcle() throws IOException {

        return PoiUtils.exportJobLevel(jobLevelService.getAllJoblevel());
    }

//    获取展示的全部数据
    @GetMapping("/")
    public List<JObLevel> getAllJoblevel() {
       return jobLevelService.getAllJoblevel();
    }

//    添加数据
    @PostMapping("/")
    public RespBean addJobLevel(@RequestBody JObLevel jObLevel) {

        if (jobLevelService.addJobLevel(jObLevel)==1) {
            return RespBean.ok("添加成功");
        }

        return RespBean.error("添加失败");

    }

    //    删除支撑数据
    @DeleteMapping("/{id}")
    public RespBean deleJobLevel(@PathVariable Integer id) {
        if (jobLevelService.deleJobLevel(id) == 1) {
            return RespBean.ok("删除成功");
        }
        return RespBean.error("删除失败");
    }

    //    更新数据
    @PutMapping("/")
//    将传入的字符串转换为对象
    public RespBean updateJonLevel(@RequestBody JObLevel jObLevel) {
        if (jobLevelService.updatebLevel(jObLevel) == 1) {
            return RespBean.ok("更新成功");
        }
        return RespBean.error("更新失败");

    }
}


