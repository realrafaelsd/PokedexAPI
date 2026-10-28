package br.com.fiap.resource;


import br.com.fiap.bo.PokemonBO;
import br.com.fiap.to.PokemonTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //dizendo para o Spring que essa classe funcionara como Controller (MVC)
@RequestMapping("/pokedex") //definindo outro endpoint
public class PokemonResource {

    private PokemonBO pokemonBO = new PokemonBO();
    @GetMapping
    //sempre que minha aplicacao receber uma requisisão do metodo get,
    // será executado a lista para a exibição dos pokemons presentes
    public ResponseEntity<List<PokemonTO>> findAll(){
        List<PokemonTO> pokemons = pokemonBO.findAll();
        if (pokemons != null) {
            return ResponseEntity.status(HttpStatus.OK).body(pokemons); //configurando o retorno com status ok -200- e
            // a lista de pokemons encontrados
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(pokemons); //configurando o retorno com status Not_found-404- e
            // a lista de pokemons encontrados

        }
    }
    @GetMapping("/{codigo}")
    public ResponseEntity<?> findByCodigo(@PathVariable Long codigo){
        PokemonTO pokemon = pokemonBO.findByCodigo(codigo);
        if (pokemon != null) {
            return ResponseEntity.status(HttpStatus.OK).body(pokemon);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pokemon não encontrado");

        }
    }
    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid PokemonTO pokemon) {
        //requestBody é o corpo da requisição, ou seja, o arquivo JSON
        //Valid irá 'ligar' o bean validation
        try {
            PokemonTO resultado = pokemonBO.save(pokemon);
            return ResponseEntity.status(HttpStatus.CREATED).body(pokemon);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao salvar pokemon");
        }
    }
    @DeleteMapping("/{codigo}") // Anotação para apagar o registro de acordo com o codigo
    public ResponseEntity<String> delete (@PathVariable Long codigo) {
        if (pokemonBO.delete(codigo)){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Pokemon deletado com sucesso!");
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pokemon não encontrado");

        }
    }

    @PutMapping ("/{codigo}")//Anotação para inserir algo
    public ResponseEntity<?> update (@PathVariable Long codigo,@RequestBody @Valid PokemonTO pokemon){ //a primeira anotação refere-se que o codigo virá
        //da URL e a segunda que o pokemon seguirá o corpo da requisição
        try {
            pokemon.setCodigo(codigo);
            PokemonTO resultado = pokemonBO.update((pokemon));
            return ResponseEntity.status(HttpStatus.CREATED).body(pokemon);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao atualizar o pokemon");
        }



    }


}
