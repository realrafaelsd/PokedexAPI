package br.com.fiap.bo;

import br.com.fiap.dao.PokemonDAO;
import br.com.fiap.to.PokemonTO;

import java.util.ArrayList;

public class PokemonBO {
    private PokemonBO pokemonBO;

    public ArrayList<PokemonTO> findAll(){
        PokemonDAO pokemonDAO = new PokemonDAO();
        //aqui se implementa as regras de negocio

        return pokemonDAO.findAll();
    }

    public PokemonTO save (PokemonTO pokemon){
        PokemonDAO pokemonDAO = new PokemonDAO();
        //regras de negocio
        return pokemonDAO.save(pokemon);

    }

    public PokemonTO findByCodigo(Long codigo){
        PokemonDAO pokemonDAO = new PokemonDAO();
        //regras de negocio
        return pokemonDAO.findByCodigo(codigo);
    }

    public boolean delete(Long codigo){
        PokemonDAO pokemonDAO = new PokemonDAO();
        //regras de negocio
        return pokemonDAO.delete(codigo);
    }

    public PokemonTO update (PokemonTO pokemon) {
        PokemonDAO pokemonDAO = new PokemonDAO();
        //regras de negocio
        return pokemonDAO.update(pokemon);
    }

}
