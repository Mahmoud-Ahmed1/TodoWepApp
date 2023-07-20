package todo.service;





import todo.model.Review;

public interface ReviewService {
    Review createReview(Review review);
    void deleteReview(Long id);
}
