package com.lms.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lms.repository.OrderRepository;
import com.lms.model.Order;
import com.lms.repository.BooksRepository;
import com.lms.model.Books;
import com.lms.service.BooksService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

//mark class as Controller
@RestController
@RequestMapping("/api")
@Api(value = "searchController", description = "This enpoints returns books, makesBooking, cancellation")
public class BooksController {
//autowire the BooksService class
	@Autowired
	BooksService booksService;
	
	 @Autowired
	    private BooksRepository bookRepository;
	
	@Autowired
    private OrderRepository orderRepository;

//creating a get mapping that retrieves all the books detail from the database 
	@GetMapping("/book")
	@ApiOperation(value = "to get total number of books in library")
	private List<Books> getAllBooks() {
		return booksService.getAllBooks();
	}
	
	 @RequestMapping(value = "/getBookingDetails", method = RequestMethod.GET,
	            produces = "application/json")
	    @ApiOperation(value = "to get total number of booking made", response = List.class)
	    public List<Order> getBookingDetails() {
	        List<Order> li = new ArrayList<Order>();
	        orderRepository.findAll().forEach(li::add);
	        return li;
	    }
	 
	 @RequestMapping(value = "/count", method = RequestMethod.GET, produces = "application/json")
	    @ApiOperation(value = "to get count of books", response = Long.class)
	    public long countNoofBooks() {
	        return bookRepository.count();
	    }

//creating a get mapping that retrieves the detail of a specific book
	@GetMapping("/book/{bookid}")
	private Books getBooks(@PathVariable("bookid") int bookid) {
		return booksService.getBooksById(bookid);
	}

//creating a delete mapping that deletes a specified book
	@DeleteMapping("/book/{bookid}")
	@ApiOperation(value = "to delete book from library", response = String.class)
	private void deleteBook(@PathVariable("bookid") int bookid) {
		booksService.delete(bookid);
	}

//creating post mapping that post the book detail in the database
	@PostMapping("/addbooks")
	 @ApiOperation(value = "to add new book in library", response = String.class)
	private int saveBook(@RequestBody Books books) {
		booksService.saveOrUpdate(books);
		return books.getAvailable();
	}

//creating put mapping that updates the book detail 
	@PutMapping("/books")
	private Books update(@RequestBody Books books) {
		booksService.saveOrUpdate(books);
		return books;
	}
	
	 @RequestMapping(value = "/makeBooking", method = RequestMethod.POST,
	            produces = "application/json")
	    @ApiOperation(value = "to make booking from library", response = String.class)
	    public void makeBooking(@RequestBody Order orderDetails) {
	        orderRepository.save(orderDetails);


	    }

	    @RequestMapping(value = "/cancelBooking", method = RequestMethod.POST,
	            produces = "application/json")
	    @ApiOperation(value = "to cancel booking from library", response = String.class)
	    public void cancelBooking(@RequestBody String orderDetails) {
	        System.out.println(orderDetails.split(":")[0]);
	        orderRepository.deleteByOrderId(orderDetails);


	    }
}
