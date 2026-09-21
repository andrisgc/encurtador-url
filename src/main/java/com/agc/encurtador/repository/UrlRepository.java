package com.agc.encurtador.repository;

import com.agc.encurtador.model.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// A annotation @Repository indica que a interface é responsável pelo gerenciamento dos dados da entidade Url.
// Extendendo a classe JpaRepository, a interface já herda os principais métodos CRUD.
@Repository
public interface UrlRepository extends JpaRepository<Url, Long> {

    // O Spring cria a consulta SQL automaticamente ao ler o método.
    Optional<Url> findByCodigoCurto(String codigoCurto);
}
