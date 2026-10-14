package br.com.fiap.dao;

import br.com.fiap.to.PokemonTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PokemonDAO {
    public ArrayList<PokemonTO> findAll(){
        ArrayList<PokemonTO> pokemons = new ArrayList<>();

        String sql = "select * from ddd_pokemons order by codigo";

        try(PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            if (rs != null){
                while (rs.next()){
                    PokemonTO pokemon = new PokemonTO();
                    pokemon.setCodigo(rs.getLong("codigo"));
                    pokemon.setNome(rs.getString("nome"));
                    pokemon.setAltura(rs.getDouble("altura"));
                    pokemon.setPeso(rs.getDouble("peso"));
                    pokemon.setCategoria(rs.getString("categoria"));
                    pokemon.setDataDeCaptura(rs.getDate("data_de_captura").toLocalDate());
                }

            }else {
                return null;
            }

        } catch (SQLException e) {
            System.out.println("Erro ao salvar: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }

        return pokemons;
    }

    public PokemonTO findByCodigo(Long codigo) {
        PokemonTO pokemon = new PokemonTO();
        String sql = "select * from ddd_pokemons where codigo = ?";
        try(PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql))
        {
            ps.setLong(1, codigo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                pokemon.setCodigo(rs.getLong("codigo"));
                pokemon.setNome(rs.getString("nome"));
                pokemon.setAltura(rs.getDouble("altura"));
                pokemon.setPeso(rs.getDouble("peso"));
                pokemon.setCategoria(rs.getString("categoria"));
                pokemon.setDataDeCaptura(rs.getDate("data_de_captura").toLocalDate());
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return pokemon;
    }
}
