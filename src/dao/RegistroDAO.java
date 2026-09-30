package dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Estatisticas;
import model.RegistroDecomposicao;
import util.ConexaoBanco;

public class RegistroDAO {

    public boolean salvar(RegistroDecomposicao registro) {
        String sql = "INSERT INTO registros_decomposicao (qtd_lixo_organico, descricao_lixo, qtd_adubo, qtd_chorume, tempo_decomposicao_dias) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConexaoBanco.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, registro.getQtdLixoOrganico());
            stmt.setString(2, registro.getDescricaoLixo());
            stmt.setDouble(3, registro.getQtdAdubo());
            stmt.setDouble(4, registro.getQtdChorume());
            stmt.setInt(5, registro.getTempoDecomposicaoDias());

            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean excluir(int id) {
        String sql = "DELETE FROM registros_decomposicao WHERE id = ?";

        try (Connection conn = ConexaoBanco.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<RegistroDecomposicao> listarTodos() {
        List<RegistroDecomposicao> lista = new ArrayList<>();
        String sql = "SELECT * FROM registros_decomposicao ORDER BY id DESC";

        try (Connection conn = ConexaoBanco.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                RegistroDecomposicao reg = new RegistroDecomposicao(
                    rs.getInt("id"),
                    rs.getDouble("qtd_lixo_organico"),
                    rs.getString("descricao_lixo"),
                    rs.getDouble("qtd_adubo"),
                    rs.getDouble("qtd_chorume"),
                    rs.getInt("tempo_decomposicao_dias")
                );
                lista.add(reg);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public Estatisticas obterEstatisticas() {
        Estatisticas est = new Estatisticas();
        String sql = "SELECT " +
                     "SUM(qtd_lixo_organico) AS total_lixo, AVG(qtd_lixo_organico) AS media_lixo, " +
                     "SUM(qtd_adubo) AS total_adubo, AVG(qtd_adubo) AS media_adubo, " +
                     "SUM(qtd_chorume) AS total_chorume, AVG(qtd_chorume) AS media_chorume, " +
                     "SUM(tempo_decomposicao_dias) AS total_dias, AVG(tempo_decomposicao_dias) AS media_dias " +
                     "FROM registros_decomposicao";

        try (Connection conn = ConexaoBanco.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                est.setTotalLixo(rs.getDouble("total_lixo"));
                est.setMediaLixo(rs.getDouble("media_lixo"));
                est.setTotalAdubo(rs.getDouble("total_adubo"));
                est.setMediaAdubo(rs.getDouble("media_adubo"));
                est.setTotalChorume(rs.getDouble("total_chorume"));
                est.setMediaChorume(rs.getDouble("media_chorume"));
                est.setTotalDias(rs.getInt("total_dias"));
                est.setMediaDias(rs.getDouble("media_dias"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return est;
    }
}