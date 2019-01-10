package com.example.demo.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.OutputStream;

@RestController
public class FileController {

  @GetMapping("/download")
  public void download(HttpServletResponse response) {
    try (FileInputStream in = new FileInputStream("xxx.pdf")) {
      response.setContentType("application/pdf");
      response.setHeader("content-disposition", "attachment;filename=" + "clean code" + ".pdf");
      OutputStream out = response.getOutputStream();
      byte[] buffer = new byte[in.available()];
      in.read(buffer);
      out.write(buffer);
      out.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
