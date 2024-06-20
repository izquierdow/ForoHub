package alura.foro.api.domain.topico.servicios;

import alura.foro.api.domain.topico.Topico;
import alura.foro.api.domain.topico.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioTopicoDelete {
    @Autowired
    private TopicoRepository topicoRepository;

    public void desactivarTopico(Long id) {
        Topico topico = topicoRepository.getReferenceById(id);
        topico.desactivarTopico();
    }
}
