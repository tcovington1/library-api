package com.devbooks.libraryapis.publisher;

import com.devbooks.libraryapis.exception.LibraryResourceAlreadyExistsException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class PublisherService {

    private PublisherRepository publisherRepository;

    public PublisherService(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    public Publisher addPublisher(Publisher publisherToBeAdded)
            throws LibraryResourceAlreadyExistsException {

        PublisherEntity publisherEntity = new PublisherEntity(
                publisherToBeAdded.getName(),
                publisherToBeAdded.getEmailId(),
                publisherToBeAdded.getPhoneNumber()
        );
        PublisherEntity addedPublisher = null;

       try {
           addedPublisher = publisherRepository.save(publisherEntity);
       } catch (DataIntegrityViolationException e) {
           throw new LibraryResourceAlreadyExistsException("Publisher already exists. See line 28 of PublisherService.");
       }

       publisherToBeAdded.setPublisherId(addedPublisher.getPublisherId());
       return publisherToBeAdded;
    }
}
