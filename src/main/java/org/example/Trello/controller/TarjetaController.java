package org.example.Trello.controller;

import javax.servlet.http.HttpSession;

import org.example.Trello.dao.TarjetaDao;
import org.example.Trello.model.Columna;
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
@RequestMapping("/tarjeta")
public class TarjetaController {

    private TarjetaDao tarjetaDao;

    @Autowired
    public void setTarjetaDao(TarjetaDao tarjetaDao) {
        this.tarjetaDao = tarjetaDao;
    }

    @RequestMapping("/add/{columna}")
    public String addTarjeta(Model model, HttpSession httpSession, @PathVariable("columna") int id_columna) {
        UserDetails user = (UserDetails) httpSession.getAttribute("user");
        if (user == null) {
            httpSession.setAttribute("path", "/tarjeta/add/" + id_columna);
            return "redirect:/login";
        }
        httpSession.setAttribute("id_columna", id_columna);
        model.addAttribute("tarjeta", new Tarjeta());
        return "tarjeta/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String processAddSubmit(@ModelAttribute("tarjeta") Tarjeta tarjeta, HttpSession httpSession) {
        Integer id_columna = (Integer) httpSession.getAttribute("id_columna");
        if (id_columna != null) {
            httpSession.removeAttribute("id_columna");
            int id_tablero = tarjetaDao.getTablero(id_columna).getIdTablero();
            tarjeta.setIdColumna(id_columna);
            tarjetaDao.addTarjeta(tarjeta);
            return "redirect:/columna/vercolumnas/"+ id_tablero;
        } else {
            return "redirect:/error"; 
        }
    }

    @RequestMapping(value = "/delete/{id_columna}/{id_tarjeta}")
    public String processDelete(@PathVariable int id_columna, @PathVariable int id_tarjeta, HttpSession httpSession) {
        UserDetails user = (UserDetails) httpSession.getAttribute("user");
        if (user == null) {
            httpSession.setAttribute("path", "/tarjeta/delete/" + id_columna + "/" + id_tarjeta);
            return "redirect:/login";
        }
        int id_tablero = tarjetaDao.getTablero(id_columna).getIdTablero();
        tarjetaDao.deleteTarjeta(id_tarjeta);
        return "redirect:/columna/vercolumnas/"+id_tablero;
    }

    @RequestMapping("/update/{id_columna}/{id_tarjeta}")
    public String updateTarjeta(Model model, HttpSession httpSession, 
                                @PathVariable("id_columna") int id_columna,
                                @PathVariable("id_tarjeta") int id_tarjeta) {
        UserDetails user = (UserDetails) httpSession.getAttribute("user");
        if (user == null) {
            httpSession.setAttribute("path", "/tarjeta/update/" + id_columna+"/"+id_tarjeta);
            return "redirect:/login";
        }
        httpSession.setAttribute("id_columna", id_columna);
        httpSession.setAttribute("id_tarjeta", id_tarjeta);
        model.addAttribute("tarjeta", new Tarjeta());
        return "tarjeta/add";
    }

    @RequestMapping(value = "/update")
    public String processUpdate(@ModelAttribute("tarjeta") Tarjeta tarjeta,
                                @ModelAttribute("id_columna") int id_columna,
                                @ModelAttribute("id_tarjeta") int id_tarjeta,
                                HttpSession httpSession) {

        UserDetails user = (UserDetails) httpSession.getAttribute("user");
        if (user == null) {
            httpSession.setAttribute("path", "/tarjeta/update/" + id_columna + "/" + id_tarjeta);
            return "redirect:/login";
        }
        int id_tablero = tarjetaDao.getTablero(id_columna).getIdTablero();
        tarjetaDao.updateTarjeta(id_tarjeta);
        return "redirect:/columna/vercolumnas/"+id_tablero;
    }
}
