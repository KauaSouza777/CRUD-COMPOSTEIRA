package view;

import java.awt.*;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import controller.RegistroController;
import model.Estatisticas;
import model.RegistroDecomposicao;

public class TelaRegistro extends JFrame {

    private static final long serialVersionUID = 1L;

    private JTextField txtQtdLixo;
    private JTextField txtDescricao;
    private JTextField txtQtdAdubo;
    private JTextField txtQtdChorume;
    private JTextField txtTempoDias;

    private JButton btnCadastrar;
    private JButton btnExcluir;
    private JButton btnLimpar;

    private JTable tabela;
    private DefaultTableModel modeloTabela;

    private JLabel lblTotalLixo;
    private JLabel lblMediaLixo;
    private JLabel lblTotalAdubo;
    private JLabel lblMediaAdubo;
    private JLabel lblTotalChorume;
    private JLabel lblMediaChorume;
    private JLabel lblTotalDias;
    private JLabel lblMediaDias;

    private RegistroController controller;

    public TelaRegistro() {

        setTitle("Sistema de Decomposição");
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel painelCampos = new JPanel(new GridLayout(5, 2, 10, 10));
        painelCampos.setBorder(
                BorderFactory.createTitledBorder("Registro de Decomposição")
        );

        painelCampos.add(new JLabel("Quantidade de lixo orgânico:"));
        txtQtdLixo = new JTextField();
        painelCampos.add(txtQtdLixo);

        painelCampos.add(new JLabel("Descrição do lixo:"));
        txtDescricao = new JTextField();
        painelCampos.add(txtDescricao);

        painelCampos.add(new JLabel("Quantidade de adubo:"));
        txtQtdAdubo = new JTextField();
        painelCampos.add(txtQtdAdubo);

        painelCampos.add(new JLabel("Quantidade de chorume:"));
        txtQtdChorume = new JTextField();
        painelCampos.add(txtQtdChorume);

        painelCampos.add(new JLabel("Tempo de decomposição (dias):"));
        txtTempoDias = new JTextField();
        painelCampos.add(txtTempoDias);

        JPanel painelBotoes = new JPanel();

        btnCadastrar = new JButton("Cadastrar");
        btnExcluir = new JButton("Excluir");
        btnLimpar = new JButton("Limpar");

        painelBotoes.add(btnCadastrar);
        painelBotoes.add(btnExcluir);
        painelBotoes.add(btnLimpar);

        JPanel painelSuperior = new JPanel(new BorderLayout(10, 10));
        painelSuperior.add(painelCampos, BorderLayout.CENTER);
        painelSuperior.add(painelBotoes, BorderLayout.SOUTH);

        add(painelSuperior, BorderLayout.NORTH);

        modeloTabela = new DefaultTableModel(
                new Object[]{
                        "ID",
                        "Lixo Orgânico (kg)",
                        "Descrição",
                        "Adubo (kg)",
                        "Chorume (ml)",
                        "Dias"
                }, 0
        ) {
            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tabela = new JTable(modeloTabela);
        tabela.setSelectionMode(
                javax.swing.ListSelectionModel.SINGLE_SELECTION
        );

        JScrollPane scrollTabela = new JScrollPane(tabela);
        scrollTabela.setBorder(
                BorderFactory.createTitledBorder("Registros")
        );

        add(scrollTabela, BorderLayout.CENTER);

        JPanel painelEstatisticas = new JPanel(
                new GridLayout(2, 4, 10, 10)
        );

        painelEstatisticas.setBorder(
                BorderFactory.createTitledBorder("Estatísticas")
        );

        lblTotalLixo = new JLabel("Total lixo: 0");
        lblMediaLixo = new JLabel("Média lixo: 0");

        lblTotalAdubo = new JLabel("Total adubo: 0");
        lblMediaAdubo = new JLabel("Média adubo: 0");

        lblTotalChorume = new JLabel("Total chorume: 0");
        lblMediaChorume = new JLabel("Média chorume: 0");

        lblTotalDias = new JLabel("Total dias: 0");
        lblMediaDias = new JLabel("Média dias: 0");

        painelEstatisticas.add(lblTotalLixo);
        painelEstatisticas.add(lblMediaLixo);
        painelEstatisticas.add(lblTotalAdubo);
        painelEstatisticas.add(lblMediaAdubo);
        painelEstatisticas.add(lblTotalChorume);
        painelEstatisticas.add(lblMediaChorume);
        painelEstatisticas.add(lblTotalDias);
        painelEstatisticas.add(lblMediaDias);

        add(painelEstatisticas, BorderLayout.SOUTH);

        btnCadastrar.addActionListener(e -> controller.cadastrar());

        btnExcluir.addActionListener(e -> controller.excluir());

        btnLimpar.addActionListener(e -> limparCampos());

        tabela.getSelectionModel().addListSelectionListener(e -> {

            if (!e.getValueIsAdjusting()) {

                int linha = tabela.getSelectedRow();

                if (linha != -1) {

                    txtQtdLixo.setText(
                            tabela.getValueAt(linha, 1).toString()
                    );

                    txtDescricao.setText(
                            tabela.getValueAt(linha, 2).toString()
                    );

                    txtQtdAdubo.setText(
                            tabela.getValueAt(linha, 3).toString()
                    );

                    txtQtdChorume.setText(
                            tabela.getValueAt(linha, 4).toString()
                    );

                    txtTempoDias.setText(
                            tabela.getValueAt(linha, 5).toString()
                    );
                }
            }
        });

        controller = new RegistroController(this);
    }

    public JTextField getTxtQtdLixo() {
        return txtQtdLixo;
    }

    public JTextField getTxtDescricao() {
        return txtDescricao;
    }

    public JTextField getTxtQtdAdubo() {
        return txtQtdAdubo;
    }

    public JTextField getTxtQtdChorume() {
        return txtQtdChorume;
    }

    public JTextField getTxtTempoDias() {
        return txtTempoDias;
    }

    public int getIdSelecionado() {

        int linha = tabela.getSelectedRow();

        if (linha == -1) {
            return -1;
        }

        return Integer.parseInt(
                tabela.getValueAt(linha, 0).toString()
        );
    }

    public void preencherTabela(List<RegistroDecomposicao> lista) {

        modeloTabela.setRowCount(0);

        for (RegistroDecomposicao registro : lista) {

            modeloTabela.addRow(new Object[]{
                    registro.getId(),
                    registro.getQtdLixoOrganico(),
                    registro.getDescricaoLixo(),
                    registro.getQtdAdubo(),
                    registro.getQtdChorume(),
                    registro.getTempoDecomposicaoDias()
            });
        }
    }

    public void atualizarEstatisticas(Estatisticas est) {

        lblTotalLixo.setText(
                "Total lixo: " + est.getTotalLixo()
        );

        lblMediaLixo.setText(
                "Média lixo: " + est.getMediaLixo()
        );

        lblTotalAdubo.setText(
                "Total adubo: " + est.getTotalAdubo()
        );

        lblMediaAdubo.setText(
                "Média adubo: " + est.getMediaAdubo()
        );

        lblTotalChorume.setText(
                "Total chorume: " + est.getTotalChorume()
        );

        lblMediaChorume.setText(
                "Média chorume: " + est.getMediaChorume()
        );

        lblTotalDias.setText(
                "Total dias: " + est.getTotalDias()
        );

        lblMediaDias.setText(
                "Média dias: " + est.getMediaDias()
        );
    }

    public void limparCampos() {

        txtQtdLixo.setText("");
        txtDescricao.setText("");
        txtQtdAdubo.setText("");
        txtQtdChorume.setText("");
        txtTempoDias.setText("");

        tabela.clearSelection();

        txtQtdLixo.requestFocus();
    }

    public void mostrarMensagem(String mensagem) {

        JOptionPane.showMessageDialog(
                this,
                mensagem
        );
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            TelaRegistro view = new TelaRegistro();

            view.setVisible(true);
        });
    }
}