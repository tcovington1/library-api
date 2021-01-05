package com.devbooks.libraryapis.publisher;

import com.devbooks.libraryapis.exception.LibraryResourceAlreadyExistsException;
import com.devbooks.libraryapis.exception.LibraryResourceNotFoundException;
import com.devbooks.libraryapis.utils.LibraryApiUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PublisherService {

    private PublisherRepository publisherRepository;

    public PublisherService(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    public void addPublisher(Publisher publisherToBeAdded, String traceId)
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
           throw new LibraryResourceAlreadyExistsException(traceId, "Publisher already exists. See line 28 of PublisherService.");
       }

       publisherToBeAdded.setPublisherId(addedPublisher.getPublisherId());
    }

    public Publisher getPublisher(Integer publisherId, String traceId) throws LibraryResourceNotFoundException {
        Optional<PublisherEntity> publisherEntity = publisherRepository.findById(publisherId);
        Publisher publisher = null;

        if( publisherEntity.isPresent()) {
            PublisherEntity pe = publisherEntity.get();
            publisher = createPublisherFromEntity(pe);
        } else {
            throw new LibraryResourceNotFoundException(traceId, "Publisher Id: " + publisherId + " was not found");
        }

        return publisher;
    }

    public void updatePublisher(Publisher publisherToBeUpdated, String traceId) throws LibraryResourceNotFoundException {
        Optional<PublisherEntity> publisherEntity = publisherRepository.findById(publisherToBeUpdated.getPublisherId());
        Publisher publisher = null;

        if( publisherEntity.isPresent()) {

            PublisherEntity pe = publisherEntity.get();

            if(LibraryApiUtils.doesStringValueExist(publisherToBeUpdated.getEmailId())) {
                pe.setEmailId(publisherToBeUpdated.getEmailId());
            }

            if(LibraryApiUtils.doesStringValueExist(publisherToBeUpdated.getPhoneNumber())) {
                pe.setPhoneNumber(publisherToBeUpdated.getPhoneNumber());
            }
            publisherRepository.save(pe);
            publisherToBeUpdated = createPublisherFromEntity(pe);
        } else {
            throw new LibraryResourceNotFoundException(traceId, "Publisher Id: " + publisherToBeUpdated.getPublisherId() + " was not found");
        }
    }

    public void deletePublisher(Integer publisherId, String traceId) throws LibraryResourceNotFoundException {

        try {
            publisherRepository.deleteById(publisherId);
        } catch (EmptyResultDataAccessException e) {
            throw new LibraryResourceNotFoundException(traceId, "Publisher Id: " + publisherId + " was not found");

        }
    }

    public List<Publisher> searchPublisher(String s, String name) {
        List<PublisherEntity> publisherEntities = null;

        if(LibraryApiUtils.doesStringValueExist(name)) {
            publisherEntities = publisherRepository.findByNameContaining(name);
        }
        if(publisherEntities != null && publisherEntities.size() > 0 ) {
            return createPublisherFromSearchResponse(publisherEntities);
        } else {
            return Collections.emptyList();
        }
    }

    private List<Publisher> createPublisherFromSearchResponse(List<PublisherEntity> publisherEntities) {
        return publisherEntities.stream()
                .map(pe -> createPublisherFromEntity(pe))
                .collect(Collectors.toList());
    }

    private Publisher createPublisherFromEntity(PublisherEntity pe) {

        return new Publisher(pe.getPublisherId(), pe.getName(), pe.getEmailId(), pe.getPhoneNumber());
    }

}
