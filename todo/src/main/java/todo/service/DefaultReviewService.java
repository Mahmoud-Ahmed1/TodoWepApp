package todo.service;

import org.springframework.stereotype.Service;

import todo.model.Review;
import todo.model.Todo;
import todo.repository.ReviewRepository;

@Service
public  class DefaultReviewService implements ReviewService{

	private final ReviewRepository reviewRepository;
	
	
    public DefaultReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }
    /*@Override
    public void addReview(Review review) {
        // Add review logic
        reviewRepository.save(review);
    }*/
    @Override
    public Review createReview(Review review)  {

        if (review.getComment().length() <= 1) {
            throw new IllegalArgumentException("Task should not be empty ");
        }
        
        // Save the user to the database
        return reviewRepository.save(review);
        
    }
    
    @Override
    public void deleteReview(Long id) {
        // Delete review logic
        reviewRepository.deleteById(id);
    }
}
