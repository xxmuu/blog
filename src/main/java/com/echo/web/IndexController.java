package com.echo.web;

import com.echo.exception.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class IndexController {
    @GetMapping("/{id}/{name}")
    public String index(@PathVariable Integer id,@PathVariable String name){
        /*int a = 2/0;*/
        /*if (true){
            throw new NotFoundException("资源没找到！");
        }*/
        return "index";
    }
}
