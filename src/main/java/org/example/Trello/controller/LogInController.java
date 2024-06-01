package org.example.Trello.controller;

import org.example.Trello.dao.UserDetailsDao;
import org.example.Trello.model.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.Random;

@Controller
public class LogInController {

    private UserDetailsDao userDetailsDao;
    @Autowired
    public void setUserDetailsDao(UserDetailsDao userDetailsDao){this.userDetailsDao=userDetailsDao;}

    @RequestMapping("/login")
    public String login(Model model){
        model.addAttribute("user",new UserDetails());
        return "login";
    }
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String checkLogin(@ModelAttribute("user") UserDetails user,
                                   HttpSession httpSession){
        user = userDetailsDao.loadByUsername(user.getUsername(),user.getPassword());
        String path = "/";
        if (user==null){
            //todo Validadores
            return "login";
        }
        httpSession.setAttribute("user", user);
        if(httpSession.getAttribute("path")!=null){
               path  = (String) httpSession.getAttribute("path");
        }
        httpSession.removeAttribute("path");

        return "redirect:" + path;
    }

    @RequestMapping("/signin")
    public String signin(Model model){
        model.addAttribute("user", new UserDetails());
        return "signin";
    }

    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    public String registerUser(@ModelAttribute("user") UserDetails user, Model model){
        System.out.println(user);

        UserDetails usuario = userDetailsDao.loadByUsername(user.getUsername(),user.getPassword());
        //todo Añadir validador
        if (usuario != null) {
            model.addAttribute("error", "El usuario ya existe");
            return "signin";
        }

        //Lógica de confirmación de correo

        int codigo = new Random().nextInt(999999);

        String mensaje = "Por favor añade en la página web el siguiente número para que tu correo pueda confirmarse de forma adecuada: "
                + "\n" + codigo + "\n";
        boolean enviado = new EmailService().sendEmail(user.getEmail(),
                "Verificación de Correo Electrónico", mensaje);

        if (!enviado) {
            model.addAttribute("error", "Error al enviar el correo electrónico." +
                    " Por favor, inténtelo de nuevo.");
            return "signin";
        }
        //todo Continuar con la confirmación de correo electrónico

        userDetailsDao.addUser(user);
        return "redirect:/login";
    }
}
