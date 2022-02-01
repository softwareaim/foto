package com.foto.PruebaFoto.repositorio;

import com.foto.PruebaFoto.entidad.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUsuarioDao extends JpaRepository<Usuario,Long> {

}
