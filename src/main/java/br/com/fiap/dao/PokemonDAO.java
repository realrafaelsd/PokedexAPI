package br.com.fiap.dao;

import br.com.fiap.to.PokemonTO;

import java.sql.Date;
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
                    pokemon.setCategoria(rs.getString("categoria"));
                    pokemon.setDataDeCaptura(rs.getDate("data_de_captura").toLocalDate());
                    pokemon.setPeso(rs.getDouble("peso"));
                    pokemon.setAltura(rs.getDouble("altura"));

                    pokemons.add(pokemon);
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
                pokemon.setCategoria(rs.getString("categoria"));
                pokemon.setDataDeCaptura(rs.getDate("data_de_captura").toLocalDate());
                pokemon.setPeso(rs.getDouble("peso"));
                pokemon.setAltura(rs.getDouble("altura"));
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

    public PokemonTO save(PokemonTO pokemon) {
        String sql = "insert into ddd_pokemons (nome, categoria, data_de_captura, peso, altura) values(?, ?, ?, ?, ?)";
        try(PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql))
        {
            ps.setString(1, pokemon.getNome());
            ps.setString(2 ,pokemon.getCategoria());
            ps.setDate(3,  Date.valueOf(pokemon.getDataDeCaptura()));
            ps.setDouble(4 ,pokemon.getPeso());
            ps.setDouble(5 ,pokemon.getAltura());
            if (ps.executeUpdate() > 0) {
                return pokemon;
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao salvar: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }

    public boolean delete(Long codigo){
        String sql = "delete from ddd_pokemons where codigo = ?";
        try(PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql))
        {
            ps.setLong(1 ,codigo);
            return ps.executeUpdate() > 0;

        }catch (SQLException e){
            System.out.println("Erro ao excluir: "+e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return false;
    }
    public PokemonTO update(PokemonTO pokemon) {
        String sql = "update ddd_pokemons set nome = ?, categoria = ?, data_de_captura = ?,peso = ?, altura = ? where codigo = ?";
        try(PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql))
        {
            ps.setString(1, pokemon.getNome());
            ps.setString(4 ,pokemon.getCategoria());
            ps.setDate(5, Date.valueOf(pokemon.getDataDeCaptura()));
            ps.setDouble(3 ,pokemon.getPeso());
            ps.setDouble(2 ,pokemon.getAltura());
            if (ps.executeUpdate() > 0) {
                return pokemon;
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao excluir: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }


}
