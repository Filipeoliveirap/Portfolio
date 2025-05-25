package com.oficina.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.oficina.backend.model.Servico;
import com.oficina.backend.model.ServicoFinalizado;
import com.oficina.backend.repository.ServicoRepository;
import com.oficina.backend.repository.ServicoFinalizadoRepository;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ServicoFinalizadoService {

    @Autowired
    private ServicoFinalizadoRepository servicoFinalizadoRepository;

    public List<ServicoFinalizado> buscarServicosFinalizados(String termo, String inicio, String fim, String periodo) {
        LocalDate dataInicio = null;
        LocalDate dataFim = null;

        if (inicio != null && !inicio.isEmpty()) {
            dataInicio = LocalDate.parse(inicio);
        }

        if (fim != null && !fim.isEmpty()) {
            dataFim = LocalDate.parse(fim);
            dataFim = dataFim.plusDays(1);
        }

        if (periodo != null && !periodo.isEmpty()) {
            LocalDate hoje = LocalDate.now();

            switch (periodo.toLowerCase()) {
                case "semana":
                    dataInicio = hoje.with(DayOfWeek.MONDAY);
                    dataFim = hoje.with(DayOfWeek.SUNDAY);
                    break;
                case "mes":
                    dataInicio = hoje.withDayOfMonth(1);
                    dataFim = hoje.withDayOfMonth(hoje.lengthOfMonth());
                    break;
                case "ano":
                    dataInicio = hoje.withDayOfYear(1);
                    dataFim = hoje.withDayOfYear(hoje.lengthOfYear());
                    break;
            }
        }

        if (termo != null && termo.isEmpty()) {
            termo = null; // para garantir que vazio seja tratado como null na query
        }

        return servicoFinalizadoRepository.buscarComFiltros(termo, dataInicio, dataFim);
    }


    @Autowired
    private ServicoRepository servicoRepository;

    public boolean finalizarServico(Long id) {
        Optional<Servico> optionalServico = servicoRepository.findById(id);

        if (optionalServico.isEmpty()) {
            return false;
        }

        Servico servico = optionalServico.get();

        ServicoFinalizado finalizado = new ServicoFinalizado();
        finalizado.setDescricao(servico.getDescricao());
        finalizado.setPreco(servico.getPreco());

        finalizado.setDataInicio(servico.getData());
        finalizado.setDataFinalizacao(LocalDate.now());

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