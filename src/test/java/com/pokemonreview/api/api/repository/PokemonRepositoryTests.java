package com.pokemonreview.api.api.repository;

import com.pokemonreview.api.exceptions.DuplicatePokemonException;
import com.pokemonreview.api.models.Pokemon;
import com.pokemonreview.api.repository.PokemonRepository;

import org.assertj.core.api.Assertions; // look up later
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class PokemonRepositoryTests {

    @Autowired
    private PokemonRepository pokemonRepository;

    @Test
    public void PokemonRepository_SaveAll_ReturnSavedPokemon() {
        // Arrange
        Pokemon pokemon = Pokemon.builder().name("pikachu").type("electric").build();

        // Act
        Pokemon savedPokemon = pokemonRepository.save(pokemon);

        // Assert
        Assertions.assertThat(savedPokemon).isNotNull();
        Assertions.assertThat(savedPokemon.getId()).isGreaterThan(0);

    }

    @Test
    public void PokemonRepository_GetAll_ReturnsMoreThanOnePokemon() {
        Pokemon pokemon = Pokemon.builder().name("pikachu").type("electric").build();
        Pokemon pokemon2 = Pokemon.builder().name("pikachu").type("electric").build();

        pokemonRepository.save(pokemon);
        pokemonRepository.save(pokemon2);

        List<Pokemon> pokemonList = pokemonRepository.findAll();

        Assertions.assertThat(pokemonList).isNotNull();
        Assertions.assertThat(pokemonList.size()).isEqualTo(2);


//        Pokemon pokemon3 = Pokemon.builder().name("zapados").type("electric").build();
//
//        //bad john!
//        ArrayList<Pokemon> pokemonArrayList = new ArrayList<>();
//        pokemonArrayList.add(pokemon);
//        pokemonArrayList.add(pokemon2);

//        Throwable thrown = Assertions.catchThrowable(() -> {
//            pokemonRepository.saveAll(pokemonArrayList);
//        });
//        Assertions.assertThat(thrown).isInstanceOf(DuplicatePokemonException.class);
//
//        pokemonArrayList.remove(pokemon2);
//        pokemonArrayList.add(pokemon3);

//        pokemonRepository.saveAll(pokemonArrayList);
//        List<Pokemon> pokemonList = pokemonRepository.findAll();
//        System.out.println(pokemonList.size());
//        Assertions.assertThat(pokemonList).isNotNull();
//        Assertions.assertThat(pokemonList.size()).isEqualTo(2);

    }

}
