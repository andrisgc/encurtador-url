package com.agc.encurtador.service;

import com.agc.encurtador.model.Url;
import com.agc.encurtador.repository.UrlRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

// A annotation @Service dita que a classe contém as regras de negócio e prepara a classe para a camada Controller.
@Service
public class UrlService {

    private final UrlRepository urlRepository;

    // Caracteres permitidos para gerar o código curto.
    private static final String caracteresPermitidos = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int tamanhoCodigo = 6;

    // Injeção de dependência pelo construtor.
    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    private String gerarCodigoCurto() {
        Random random = new Random();
        StringBuilder codigo;

        do {
            codigo = new StringBuilder();
            for (int i = 0; i < tamanhoCodigo; i++) {
                int posicaoSorteada = random.nextInt(caracteresPermitidos.length());
                codigo.append(caracteresPermitidos.charAt(posicaoSorteada));
            }
        } while (urlRepository.findByCodigoCurto(codigo.toString()).isPresent());

        return codigo.toString();
    }

    public Url encurtarUrl(String urlOriginal) {
        String codigoGerado = gerarCodigoCurto();

        Url novaUrl = new Url(urlOriginal, codigoGerado);

        // Salva no DB e retorna a URL nova.
        return urlRepository.save(novaUrl);
    }

    public Optional<Url> buscaUrl(String codigoCurto) {
        Optional<Url> urlEncontrada = urlRepository.findByCodigoCurto(codigoCurto);

        if (urlEncontrada.isPresent()) {
            Url url = urlEncontrada.get();
            url.setContadorCliques(url.getContadorCliques() + 1);
            urlRepository.save(url);
        }

        return urlEncontrada;
    }
}
