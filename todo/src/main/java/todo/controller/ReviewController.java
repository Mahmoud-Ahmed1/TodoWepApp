package todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import todo.model.Review;
import todo.model.Todo;
import todo.service.ReviewService;


//Review controller
@RestController
@RequestMapping("/reviews")
public class ReviewController {
	@Autowired
	private final ReviewService reviewService;
	
	   public ReviewController(ReviewService reviewService) {
	        this.reviewService = reviewService;
	    }
	   
	   
	   /* @PostMapping("/newreview")
	    public ResponseEntity<Void> addReview(@RequestBody Review review) {
	        reviewService.addReview(review);
	        return ResponseEntity.status(HttpStatus.CREATED).build();
	    }*/
	   
	    @PostMapping("/create")
	    public ResponseEntity<Review> create(@RequestBody Review review) {
	        Review savedReview = reviewService.createReview(review);
	        return new ResponseEntity<>(savedReview, HttpStatus.CREATED);
	    }
	    
	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
	        reviewService.deleteReview(id);
	        return ResponseEntity.noContent().build();
	    }
}
