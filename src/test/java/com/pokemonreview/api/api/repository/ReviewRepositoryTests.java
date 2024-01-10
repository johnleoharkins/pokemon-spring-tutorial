package com.pokemonreview.api.api.repository;

import com.pokemonreview.api.models.Pokemon;
import com.pokemonreview.api.models.Review;
import com.pokemonreview.api.repository.ReviewRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ReviewRepositoryTests {
    private ReviewRepository reviewRepository;

    @Autowired
    public ReviewRepositoryTests(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Test
    public void ReviewRepository_SaveAll_ReturnSavedReview() {
        Review review = Review.builder().title("title").content("content").stars(5).build();
        Review savedReview = reviewRepository.save(review);
        Assertions.assertThat(savedReview).isNotNull();
        Assertions.assertThat(savedReview.getId()).isGreaterThan(0);
    }

    @Test
    public void ReviewRepository_GetAll_ReturnMoreThanOneSavedReview() {
        Review review = Review.builder().title("title").content("content").stars(5).build();
        Review review2 = Review.builder().title("title").content("content").stars(5).build();
        reviewRepository.save(review);
        reviewRepository.save(review2);

        List<Review> reviewList = reviewRepository.findAll();

        Assertions.assertThat(reviewList).isNotNull();
        Assertions.assertThat(reviewList.size()).isEqualTo(2);
    }

    @Test
    public void ReviewRepository_FindById_ReturnSavedReview() {
        Review review = Review.builder().title("title").content("content").stars(5).build();
        reviewRepository.save(review);
        Review reviewReturn = reviewRepository.findById((long) review.getId()).get();
        Assertions.assertThat(reviewReturn).isNotNull();
        Assertions.assertThat(reviewReturn.getId()).isGreaterThan(0);
    }

    @Test
    public void ReviewRepository_UpdateReview_ReturnReview() {
        Review review = Review.builder().title("title").content("content").stars(5).build();
        reviewRepository.save(review);
        Review reviewSave = reviewRepository.findById((long) review.getId()).get();
        reviewSave.setTitle("new title");
        reviewSave.setContent("new content");
        reviewSave.setStars(1);
        Review updatedReview = reviewRepository.save(reviewSave);
        Assertions.assertThat(updatedReview.getTitle()).isNotNull();
        Assertions.assertThat(updatedReview.getContent()).isNotNull();
        Assertions.assertThat(updatedReview.getStars()).isGreaterThan(0);
    }

    @Test
    public void PokemonRepository_ReviewDelete_ReturnReviewIsEmpty() {
        Review review = Review.builder().title("title").content("content").stars(5).build();
        reviewRepository.save(review);
        reviewRepository.deleteById((long) review.getId());
        Optional<Review> reviewReturn = reviewRepository.findById((long) review.getId());

        Assertions.assertThat(reviewReturn).isEmpty();
    }
}
