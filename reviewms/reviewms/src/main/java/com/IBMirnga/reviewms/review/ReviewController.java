package com.IBMirnga.reviewms.review;

import com.IBMirnga.reviewms.review.messaging.ReviewMessageProducer;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    private ReviewMessageProducer reviewMessageProducer;

    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews(@RequestParam Long companyId) {
        return new ResponseEntity<>(reviewService.getAllReviews(companyId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String > makeReview(@RequestParam Long companyId, @RequestBody Review review) {
        boolean isReviewSaved = reviewService.makeReview(companyId, review);
        if (isReviewSaved) {
            reviewMessageProducer.sendMessage(review);
            return new ResponseEntity<>("Review Added Successfully", HttpStatus.OK);
        }
            return new ResponseEntity<>("Review Not Added Successfully", HttpStatus.NOT_FOUND);

    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> getReview(@PathVariable Long reviewId) {
        return new ResponseEntity<>(reviewService.getReview(reviewId), HttpStatus.OK);
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<String > updateReview(@PathVariable Long reviewId,
                                               @RequestBody Review updatedReview) {
        boolean isReviewUpdated = reviewService.updateReview(reviewId, updatedReview);
        if (isReviewUpdated) {
            return new ResponseEntity<>("Review Updated Successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Review Not Updated Successfully", HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String > deleteReview(@PathVariable Long reviewId) {
        boolean isReviewDeleted = reviewService.deleteReview(reviewId);
        if (isReviewDeleted) {
            return new ResponseEntity<>("Review Deleted Successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Review Not Deleted Successfully", HttpStatus.NOT_FOUND);

    }

    @GetMapping("averageRating")
    public Double getAverageReview(@RequestParam Long companyId) {
        List<Review> reviewList = reviewService.getAllReviews(companyId);
        return reviewList.stream().mapToDouble(Review::getRating).average().orElse(0.0);
    }
}
