package org.example.Trello.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.example.Trello.dao.ColumnaDao;
import org.example.Trello.dao.TableroDao;
import org.example.Trello.model.Columna;
import org.example.Trello.model.Tablero;
import org.example.Trello.model.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/columna")
public class ColumnaController {
    private ColumnaDao columnaDao;
     @Autowired

    public void setColumnaDao(ColumnaDao columnaDao){this.columnaDao=columnaDao;}
    
    @RequestMapping("/vercolumnas/{id_tablero}")
    public String listTableros(Model model, HttpSession httpSession,
                                @PathVariable("id_tablero") int id_tablero){
        UserDetails user = (UserDetails) httpSession.getAttribute("user");
        if(user==null){
            httpSession.setAttribute("path", "/columna/vercolumnas/"+id_tablero);
            return "redirect:../../login";
        }
        List<Columna> columnaList = columnaDao.getColumnas(id_tablero);
        System.out.println(columnaList);
        model.addAttribute("columnaList",columnaList);
        return "/columna/vercolumnas/"+id_tablero;
    }
}
