package cn.tycoding.controller;

import cn.tycoding.pojo.User;
import cn.tycoding.service.UserService;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * 用户的控制层
 *
 * @author tycoding
 * @date 18-4-7下午9:00
 */
@Controller
@RequestMapping("/load")
public class upDownloadController {
    //注入service
    @Autowired
    private UserService userService;

    /**
     * 上传
     */
    @RequestMapping(value = "/upload")
    public String login(MultipartFile image, HttpServletRequest request, Model model) {
        //获取文件在服务器上的储存位置
        String path = request.getSession().getServletContext().getRealPath("resources/upload");
        File filePath = new File(path);
        System.out.println("文件保存路径：" + path);
        if (!filePath.exists() && !filePath.isDirectory()) {
            System.out.println("目录不存在，创建目录：" + filePath);
            filePath.mkdir();
        }

        //获取原始文件名称
        String originalFileName = image.getOriginalFilename();
        System.out.println("原始文件名称：" + originalFileName);

        //获取文件类型，以最后一个`.`作为标识
        String type = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
        System.out.println("文件类型：" + type);

        //设置文件新名字
        String fileName = System.currentTimeMillis() + "." + type;
        System.out.println("文件新名称：" + fileName);
        //在指定路径创建一个文件
        File targetFile = new File(path, originalFileName);

        //将文件保存到服务器指定位置
        try {
            image.transferTo(targetFile);
            model.addAttribute("message", "保存数据成功");
            userService.saveUpload("resources/upload/" + originalFileName);
            return "index";
        } catch (IOException e) {
            System.out.println("保存文件错误...");
            e.printStackTrace();
        }
        return "view/error";
    }

    /**
     * 下载
     */
    @RequestMapping(value = "/download")
    public ResponseEntity<byte[]> download(HttpServletRequest request, String fileName) throws Exception {
        try {
            //下载路径
            String path = request.getServletContext().getRealPath("/WEB-INF/");

            File file = new File(path + File.separator + fileName);
            HttpHeaders headers = new HttpHeaders();
            //解决文件名中文乱码问题
            String downloadFileName = new String(fileName.getBytes("UTF-8"), "iso-8859-1");
            //告诉浏览器以"attachment"方式打开文件
            headers.setContentDispositionFormData("attachment", downloadFileName);
            //设置请求头的媒体格式类型为 application/octet-stream(二进制流数据)
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("文件下载出错...");
            return null;
        }
    }
}
