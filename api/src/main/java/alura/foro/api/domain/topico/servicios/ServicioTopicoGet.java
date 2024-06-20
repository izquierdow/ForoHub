package alura.foro.api.domain.topico.servicios;

import alura.foro.api.domain.topico.DtoListadoTopico;
import alura.foro.api.domain.topico.Topico;
import alura.foro.api.domain.topico.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ServicioTopicoGet {
    @Autowired
    private TopicoRepository topicoRepository;

    public Page<DtoListadoTopico> listarTopicos(Pageable pages) {
        return topicoRepository.findByActivoTrue(pages).map(DtoListadoTopico::new);
    }

    public Topico listarTopico(Long id) {
        return topicoRepository.getById(id);
    }
}