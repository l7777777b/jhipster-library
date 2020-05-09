package com.jhtest.library.service;

import com.jhtest.library.domain.BorrowedBook;
import com.jhtest.library.repository.BorrowedBookRepository;
import com.jhtest.library.service.dto.BorrowedBookDTO;
import com.jhtest.library.service.mapper.BorrowedBookMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link BorrowedBook}.
 */
@Service
@Transactional
public class BorrowedBookService {

    private final Logger log = LoggerFactory.getLogger(BorrowedBookService.class);

    private final BorrowedBookRepository borrowedBookRepository;

    private final BorrowedBookMapper borrowedBookMapper;

    public BorrowedBookService(BorrowedBookRepository borrowedBookRepository, BorrowedBookMapper borrowedBookMapper) {
        this.borrowedBookRepository = borrowedBookRepository;
        this.borrowedBookMapper = borrowedBookMapper;
    }

    /**
     * Save a borrowedBook.
     *
     * @param borrowedBookDTO the entity to save.
     * @return the persisted entity.
     */
    public BorrowedBookDTO save(BorrowedBookDTO borrowedBookDTO) {
        log.debug("Request to save BorrowedBook : {}", borrowedBookDTO);
        BorrowedBook borrowedBook = borrowedBookMapper.toEntity(borrowedBookDTO);
        borrowedBook = borrowedBookRepository.save(borrowedBook);
        return borrowedBookMapper.toDto(borrowedBook);
    }

    /**
     * Get all the borrowedBooks.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<BorrowedBookDTO> findAll(Pageable pageable) {
        log.debug("Request to get all BorrowedBooks");
        return borrowedBookRepository.findAll(pageable)
            .map(borrowedBookMapper::toDto);
    }

    /**
     * Get one borrowedBook by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<BorrowedBookDTO> findOne(Long id) {
        log.debug("Request to get BorrowedBook : {}", id);
        return borrowedBookRepository.findById(id)
            .map(borrowedBookMapper::toDto);
    }

    /**
     * Delete the borrowedBook by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete BorrowedBook : {}", id);
        borrowedBookRepository.deleteById(id);
    }
}
