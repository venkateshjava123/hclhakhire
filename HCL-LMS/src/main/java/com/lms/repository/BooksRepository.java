package com.lms.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.lms.model.Books;
@Repository
public interface BooksRepository extends CrudRepository<Books, Integer>
{
}
