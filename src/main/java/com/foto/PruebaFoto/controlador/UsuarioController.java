package com.foto.PruebaFoto.controlador;

import com.foto.PruebaFoto.entidad.Usuario;
import com.foto.PruebaFoto.repositorio.IUsuarioDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class UsuarioController {

    @Autowired
    private IUsuarioDao usuarioDao;

    @GetMapping("/")
    public String form(Model model){
        model.addAttribute("usuarios",new Usuario());
        return "form";
    }

    @PostMapping("/")
    public String guardar(@RequestParam(name = "file",required = false) MultipartFile foto,
                          Usuario usuario, RedirectAttributes flash){

        if(!foto.isEmpty()){ //si la foto no viene vacia q la guarde
            String ruta = "C://Temp//uploads"; // ruta relativa donde voy a guardar la foto
            Path path = Paths.get(ruta);
            if (!Files.exists(path)) {
                File directorio = new File("C://Temp//uploads");
                if (directorio.mkdirs()) {
                    System.out.println("Directorio creado");
                } else {
                    System.out.println("Error al crear directorio");
                }
            } else {
                System.out.println("existe");
            }

            try {
                byte[] bytes = foto.getBytes(); //alamceno los bytes de la foto
                Path rutaAbsolita = Paths.get(ruta+"//"+ foto.getOriginalFilename());//ruta abs concatenada con el nombre de la foto
                
                Files.write(rutaAbsolita,bytes); // guardo la foto
                usuario.setFoto(foto.getOriginalFilename());
            }catch (Exception e){
                e.getStackTrace();
            }
        }
        usuarioDao.save(usuario);
        flash.addFlashAttribute("success","Foto subida!!");

        return "redirect:/";
    }

    @GetMapping("/listar")
    public String listar(Model model){
        model.addAttribute("usuarios",usuarioDao.findAll());
        return"listar";
    }

}
