package com.agc.encurtador.controller;

import com.agc.encurtador.model.Url;
import com.agc.encurtador.service.UrlService;
import org.apache.coyote.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Map;
import java.util.Optional;

// A annotation @RestController dita que esta classe será responsável pelas requisições web.
@RestController
public class UrlController {

    private final UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    // A annotation @PostMapping cria uma rota que receberá requisições POST. Quando enviar a requisição POST para o endereço /encurtar, o método que será chamado é encurtarUrl.
    // A annotation @RequestBody recebe o JSON enviado e o transforma em um objeto Java.
    @PostMapping("/encurtar")
    public ResponseEntity<Url> encurtarUrl(@RequestBody Map<String, String> request) {
        String urlOriginal = request.get("urlOriginal");
        Url novaUrl = urlService.encurtarUrl(urlOriginal);

        return ResponseEntity.status(HttpStatus.CREATED).body(novaUrl);
    }

    // A annotation @GetMapping cria uma rota que receberá requisições GET. O {} indica texto dinâmico.
    // A annotation @PathVariable serve para atribuir o texto dinâmico da URL a uma variável, no nosso caso codigoCurto.
    @GetMapping("/{codigoCurto:[a-zA-Z0-9]+}")
    public ResponseEntity<Void> redirecionar(@PathVariable String codigoCurto) {
        if (codigoCurto == null || codigoCurto.trim().isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Optional<Url> urlEncontrada = urlService.buscaUrl(codigoCurto);

        if (urlEncontrada.isPresent()) {
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(URI.create(urlEncontrada.get().getUrlOriginal()));
            return new ResponseEntity<>(headers, HttpStatus.FOUND);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
