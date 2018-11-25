package com.szl.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.szl.vo.ResultVo;

/**
 * @author sunzl
 * @date 2018年11月25日
 * 上传文件
 */

@RestController
@RequestMapping("/upload")
public class UploadController {

	@RequestMapping("/upload")
	public ResultVo upload(MultipartFile file) throws IllegalStateException, IOException{
		ResultVo resultVo = new ResultVo();
		file.transferTo(new File("E:/study/java/codes/Springboot/files/" + file.getOriginalFilename()));
		resultVo.setCode("0");
		resultVo.setMsg("成功");
		return resultVo;
	}
}
