package org.example.Trello.controller;

import org.bouncycastle.math.raw.Mod;
import org.example.Trello.dao.TableroDao;
import org.example.Trello.model.Tablero;
import org.example.Trello.model.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMethod;

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
            httpSession.setAttribute("path", "/tablero/list");
            return "redirect:../login";
        }
        List<Tablero> tableroList = tableroDao.getTableros(user.getUsername());
        model.addAttribute("tableroList",tableroList);
        return "/tablero/list";
    }

    @RequestMapping("/add")
    public String addTablero(Model model, HttpSession httpSession){
        UserDetails user = (UserDetails) httpSession.getAttribute("user");
        if(user==null){
            httpSession.setAttribute("path", "/tablero/add");
            return "redirect:../login";
        }
        model.addAttribute("tablero", new Tablero());
        return "tablero/add";

    }
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String processAddSubmit(@ModelAttribute("tablero") Tablero tablero,
                                   HttpSession httpSession) {
        UserDetails userDetails = (UserDetails) httpSession.getAttribute("user");
        tablero.setUsuario(userDetails.getUsername());
        System.out.println(tablero);
        tableroDao.addTablero(tablero);
        return "redirect:list";
    }

    @RequestMapping(value = "/delete/{id_tablero}")
    public String processAndDelete(@PathVariable int id_tablero, Model model,
                                   HttpSession httpSession){
        UserDetails user = (UserDetails) httpSession.getAttribute("user");
        if(user==null){
            httpSession.setAttribute("path", "/tablero/list");
            return "login";
        }
        tableroDao.deleteTablero(id_tablero);
        return "redirect:../list";
    }

}
