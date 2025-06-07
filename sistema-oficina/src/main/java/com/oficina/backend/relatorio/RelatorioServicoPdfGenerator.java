package com.oficina.backend.relatorio;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.*;
import com.oficina.backend.model.ServicoFinalizado;

import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class RelatorioServicoPdfGenerator {
    private static final DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static ByteArrayInputStream gerarRelatorio(List<ServicoFinalizado> servicosFinalizados) {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, out);
            document.open();

            Font titulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
            Paragraph cabecalho = new Paragraph("Relatório de Serviços Finalizados", titulo);
            cabecalho.setAlignment(Element.ALIGN_CENTER);
            document.add(cabecalho);
            document.add(Chunk.NEWLINE);

            PdfPTable tabela = new PdfPTable(8); // 8 colunas
            tabela.setWidthPercentage(100);
            tabela.setWidths(new int[]{1, 3, 2, 2, 2, 3, 3, 4});

            Font cabecalhoFonte = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);

            String[] colunas = {"ID", "Descrição", "Preço", "Data Início", "Data Finalização", "CPF Cliente", "Nome Cliente", "Observações"};
            for (String col : colunas) {
                PdfPCell header = new PdfPCell(new Phrase(col, cabecalhoFonte));
                header.setHorizontalAlignment(Element.ALIGN_CENTER);
                header.setBackgroundColor(Color.LIGHT_GRAY);
                tabela.addCell(header);
            }

            for (ServicoFinalizado s : servicosFinalizados) {
                tabela.addCell(String.valueOf(s.getId()));
                tabela.addCell(s.getDescricao() != null ? s.getDescricao() : "");
                tabela.addCell(s.getPreco() != null ? String.format("R$ %.2f", s.getPreco()) : "");

                tabela.addCell(s.getDataInicio() != null ? s.getDataInicio().format(formatador) : "");
                tabela.addCell(s.getDataFinalizacao() != null ? s.getDataFinalizacao().format(formatador) : "");

                tabela.addCell(s.getCpfCliente() != null ? s.getCpfCliente() : "");
                tabela.addCell(s.getNomeCliente() != null ? s.getNomeCliente() : "");
                tabela.addCell(s.getObservacoes() != null ? s.getObservacoes() : "");
            }

            document.add(tabela);
            document.close();

        } catch (DocumentException e) {
            throw new RuntimeException("Erro ao gerar PDF: " + e.getMessage());
        }

        return new ByteArrayInputStream(out.toByteArray());
    }

    public static ByteArrayInputStream gerarRelatorioUnico(ServicoFinalizado servico) {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, out);
            document.open();

            Font titulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
            Paragraph cabecalho = new Paragraph("Relatório de Serviço Finalizado", titulo);
            cabecalho.setAlignment(Element.ALIGN_CENTER);
            document.add(cabecalho);
            document.add(Chunk.NEWLINE);

            Font fonteNormal = FontFactory.getFont(FontFactory.HELVETICA, 12);

            document.add(new Paragraph("ID: " + servico.getId(), fonteNormal));
            document.add(new Paragraph("Descrição: " + servico.getDescricao(), fonteNormal));
            document.add(new Paragraph("Preço: " + (servico.getPreco() != null ? String.format("R$ %.2f", servico.getPreco()) : ""), fonteNormal));

            document.add(new Paragraph("Data de Início: " + (servico.getDataInicio() != null ? servico.getDataInicio().format(formatador) : ""), fonteNormal));
            document.add(new Paragraph("Data de Finalização: " + (servico.getDataFinalizacao() != null ? servico.getDataFinalizacao().format(formatador) : ""), fonteNormal));

            document.add(new Paragraph("Nome do Cliente: " + (servico.getNomeCliente() != null ? servico.getNomeCliente() : ""), fonteNormal));
            document.add(new Paragraph("CPF do Cliente: " + (servico.getCpfCliente() != null ? servico.getCpfCliente() : ""), fonteNormal));
            document.add(new Paragraph("Observações: " + (servico.getObservacoes() != null ? servico.getObservacoes() : ""), fonteNormal));

            document.close();

        } catch (DocumentException e) {
            throw new RuntimeException("Erro ao gerar PDF: " + e.getMessage());
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}
