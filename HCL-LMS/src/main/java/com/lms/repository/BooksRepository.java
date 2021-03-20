package com.lms.repository;
import org.springframework.data.repository.CrudRepository;

import com.lms.model.Books;
public interface BooksRepository extends CrudRepository<Books, Integer>
{
}
