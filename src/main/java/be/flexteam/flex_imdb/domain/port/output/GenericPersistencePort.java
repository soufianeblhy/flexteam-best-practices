package be.flexteam.flex_imdb.domain.port.output;

import be.flexteam.flex_imdb.domain.exception.DeleteException;
import be.flexteam.flex_imdb.domain.exception.NotFoundException;
import be.flexteam.flex_imdb.domain.exception.SaveException;
import be.flexteam.flex_imdb.domain.model.Identifier;

import java.util.List;

/**
 * Persistence using for the CRUD operations
 *
 * @param <T> the domain model to use for the CRUD operations
 * @param <I> the identifier type for the domain model
 */
public interface GenericPersistencePort<T extends Identifier<I>, I> {

    /**
     * Retrieve all instances
     * @return
     */
    List<T> getAll();

    /**
     * Retrieve instance from the identifier
     * @param id the identifier
     * @return the instance
     * @throws NotFoundException the instance cannot be found
     */
    T get(I id) throws NotFoundException;

    /**
     * Save the instance in the persistence
     * @param domainModel the instance
     * @return the saved instance (can be changed)
     * @throws SaveException the instance cannot be saved
     */
    T save(T domainModel);

    /**
     * Update the instance in the persistence
     * @param domainModel the instance
     * @param partial true if you want to update only no null field
     *                or false if you want to update all fields (null or not null)
     * @return the updated instance
     */
    T update(T domainModel, boolean partial);


    /**
     * Delete the instance in the persistence
     * @param id the instance
     */
    void delete(I id) throws DeleteException;

}