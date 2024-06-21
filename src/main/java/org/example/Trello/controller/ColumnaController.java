package org.example.Trello.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.example.Trello.dao.ColumnaDao;
import org.example.Trello.model.Columna;
import org.example.Trello.model.Tablero;
import org.example.Trello.model.Tarjeta;
import org.example.Trello.model.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/columna")
public class ColumnaController {
    private ColumnaDao columnaDao;
     @Autowired

    public void setColumnaDao(ColumnaDao columnaDao){this.columnaDao=columnaDao;}
    
    @RequestMapping("/vercolumnas/{id_tablero}")
    public String listColumnas(Model model, HttpSession httpSession,
                                @PathVariable("id_tablero") int id_tablero){
        UserDetails user = (UserDetails) httpSession.getAttribute("user");
        if(user==null){
            httpSession.setAttribute("path", "/columna/vercolumnas/"+id_tablero);
            return "redirect:../../login";
        }
        Tablero tablero = columnaDao.getTablero(id_tablero);
        List<Columna> columnaList = columnaDao.getColumnas(id_tablero);
        for(Columna columna : columnaList){
            List<Tarjeta> tarjetas = columnaDao.getTarjetas(columna.getIdColumna());
            columna.setTarjetas(tarjetas);
        }   
        model.addAttribute("columnaList",columnaList);
        model.addAttribute("tablero",tablero);
        return "/columna/vercolumnas";
    }
    @RequestMapping("/add/{id_tablero}")
    public String addColumna(Model model, HttpSession httpSession,
                            @PathVariable("id_tablero") int id_tablero){
        UserDetails user = (UserDetails) httpSession.getAttribute("user");
        if(user==null){
            httpSession.setAttribute("path", "/columna/add"+ id_tablero);
            return "redirect:../../login";
        }
        httpSession.setAttribute("id_tablero", id_tablero);
        model.addAttribute("columna", new Columna());
        return "columna/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String processAddSubmit(@ModelAttribute("columna") Columna columna,
                                   HttpSession httpSession) {
        
        int id_tablero = (int) httpSession.getAttribute("id_tablero");
        httpSession.removeAttribute("id_tablero");
        columna.setIdTablero(id_tablero);
        columnaDao.addColumna(columna);
        return "redirect:/columna/vercolumnas/" + id_tablero;
    }
    @RequestMapping(value = "/delete/{id_tablero}/{id_columna}")
    public String processAndDelete(@PathVariable int id_columna,
                                   @PathVariable int id_tablero,
                                   Model model,
                                   HttpSession httpSession){
        UserDetails user = (UserDetails) httpSession.getAttribute("user");
        if(user==null){
            httpSession.setAttribute("path", "/columna/vercolumnas/"+id_tablero);
            return "login";
        }
        columnaDao.deleteColumna(id_columna);
        return "redirect:../../vercolumnas/" + id_tablero;
    }
}
