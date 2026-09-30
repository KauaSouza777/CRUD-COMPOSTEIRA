package controller;

import java.util.List;

import view.TelaRegistro;
import dao.RegistroDAO;
import model.Estatisticas;
import model.RegistroDecomposicao;

public class RegistroController {

    private TelaRegistro view;
    private RegistroDAO dao;

    public RegistroController(TelaRegistro view) {
        this.view = view;
        this.dao = new RegistroDAO();

        listarRegistros();
        atualizarEstatisticas();
    }

    // CADASTRAR

    public void cadastrar() {

        try {

            double qtdLixo = Double.parseDouble(
                    view.getTxtQtdLixo().getText()
            );

            String descricao = view.getTxtDescricao().getText();

            double qtdAdubo = Double.parseDouble(
                    view.getTxtQtdAdubo().getText()
            );

            double qtdChorume = Double.parseDouble(
                    view.getTxtQtdChorume().getText()
            );

            int tempoDias = Integer.parseInt(
                    view.getTxtTempoDias().getText()
            );

            if (descricao.trim().isEmpty()) {
                view.mostrarMensagem(
                    "Digite a descrição do lixo."
                );
                return;
            }

            RegistroDecomposicao registro =
                    new RegistroDecomposicao(
                            qtdLixo,
                            descricao,
                            qtdAdubo,
                            qtdChorume,
                            tempoDias
                    );

            boolean sucesso = dao.salvar(registro);

            if (sucesso) {

                view.mostrarMensagem(
                    "Registro cadastrado com sucesso!"
                );

                listarRegistros();

                atualizarEstatisticas();

                view.limparCampos();

            } else {

                view.mostrarMensagem(
                    "Erro ao cadastrar o registro."
                );
            }

        } catch (NumberFormatException e) {

            view.mostrarMensagem(
                "Digite valores numéricos válidos."
            );
        }
    }

    // EXCLUIR

    public void excluir() {

        try {

            int id = view.getIdSelecionado();

            if (id == -1) {

                view.mostrarMensagem(
                    "Selecione um registro para excluir."
                );

                return;
            }

            boolean sucesso = dao.excluir(id);

            if (sucesso) {

                view.mostrarMensagem(
                    "Registro excluído com sucesso!"
                );
                
                listarRegistros();
              
                atualizarEstatisticas();
          
                view.limparCampos();

            } else {

                view.mostrarMensagem(
                    "Não foi possível excluir o registro."
                );
            }

        } catch (Exception e) {

            view.mostrarMensagem(
                "Erro ao excluir o registro."
            );
        }
    }

    // LISTAR

    public void listarRegistros() {

        List<RegistroDecomposicao> lista =
                dao.listarTodos();

        view.preencherTabela(lista);
    }

    // ESTATÍSTICAS

    public void atualizarEstatisticas() {

        Estatisticas est =
                dao.obterEstatisticas();

        view.atualizarEstatisticas(est);
    }
}