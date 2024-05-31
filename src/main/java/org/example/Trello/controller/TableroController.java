package org.example.Trello.controller;

import org.example.Trello.dao.TableroDao;
import org.example.Trello.model.Tablero;
import org.example.Trello.model.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/tablero")
public class TableroController {
    private TableroDao tableroDao;

    @Autowired
    public void setTableroDao(TableroDao tableroDao){this.tableroDao=tableroDao;}

    @RequestMapping("/list")
    public String listTableros(Model model, HttpSession httpSession){
        UserDetails user = (UserDetails) httpSession.getAttribute("user");
        if(user==null){
            return "redirect:../login";
        }
        List<Tablero> tableroList = tableroDao.getTableros();
        model.addAttribute("tableroList",tableroList);
        return "/tablero/list";
    }
}
