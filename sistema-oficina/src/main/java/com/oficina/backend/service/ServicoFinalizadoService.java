package com.oficina.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.oficina.backend.model.Servico;
import com.oficina.backend.model.ServicoFinalizado;
import com.oficina.backend.repository.ServicoRepository;
import com.oficina.backend.repository.ServicoFinalizadoRepository;

import java.time.*;
import java.util.List;
import java.util.Optional;

@Service
public class ServicoFinalizadoService {

    @Autowired
    private ServicoFinalizadoRepository servicoFinalizadoRepository;

    @Autowired
    private ServicoRepository servicoRepository;

    public List<ServicoFinalizado> buscarServicosFinalizados(String termo, LocalDate inicio, LocalDate fim, String periodo) {
        LocalDateTime dataInicio = null;
        LocalDateTime dataFim = null;

        // Converte LocalDate para LocalDateTime
        if (inicio != null) dataInicio = inicio.atStartOfDay(); // 00:00:00
        if (fim != null) dataFim = fim.atTime(LocalTime.MAX);  // 23:59:59.999

        // Filtro por período (ignora dataInicio/dataFim se período for passado)
        if (periodo != null && !periodo.trim().isEmpty()) {
            LocalDate hoje = LocalDate.now();

            switch (periodo.toLowerCase()) {
                case "semana":
                    dataInicio = hoje.with(DayOfWeek.MONDAY).atStartOfDay();
                    dataFim = hoje.with(DayOfWeek.SUNDAY).atTime(LocalTime.MAX);
                    break;
                case "mes":
                    dataInicio = hoje.withDayOfMonth(1).atStartOfDay();
                    dataFim = hoje.withDayOfMonth(hoje.lengthOfMonth()).atTime(LocalTime.MAX);
                    break;
                case "ano":
                    dataInicio = hoje.withDayOfYear(1).atStartOfDay();
                    dataFim = hoje.withDayOfYear(hoje.lengthOfYear()).atTime(LocalTime.MAX);
                    break;
            }
        }

        // Se termo estiver vazio, trata como null
        if (termo != null && termo.trim().isEmpty()) {
            termo = null;
        }

        return servicoFinalizadoRepository.buscarComFiltros(termo, dataInicio, dataFim);
    }

    public boolean finalizarServico(Long id) {
        Optional<Servico> optionalServico = servicoRepository.findById(id);
        if (optionalServico.isEmpty()) return false;

        Servico servico = optionalServico.get();
        ServicoFinalizado finalizado = new ServicoFinalizado();

        finalizado.setDescricao(servico.getDescricao());
        finalizado.setPreco(servico.getPreco());
        finalizado.setDataInicio(servico.getData()); // deve ser LocalDateTime
        finalizado.setDataFinalizacao(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
        finalizado.setCpfCliente(servico.getCliente().getCpf());
        finalizado.setNomeCliente(servico.getCliente().getNome());
        finalizado.setObservacoes("");

        servicoFinalizadoRepository.save(finalizado);
        servicoRepository.deleteById(id);

        return true;
    }

    public Optional<ServicoFinalizado> buscarPorId(Long id) {
        return servicoFinalizadoRepository.findById(id);
    }
}
