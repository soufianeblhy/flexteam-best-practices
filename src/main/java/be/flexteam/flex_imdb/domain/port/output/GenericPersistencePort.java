package be.flexteam.flex_imdb.domain.port.output;

import be.flexteam.flex_imdb.domain.exception.DeleteException;
import be.flexteam.flex_imdb.domain.exception.NotFoundException;
import be.flexteam.flex_imdb.domain.exception.SaveException;
import be.flexteam.flex_imdb.domain.model.Identifier;

import java.util.List;

public interface GenericPersistencePort<T extends Identifier<I>, I> {

    List<T> getAll();

    T get(I id) throws NotFoundException;

    T save(T domainModel) throws SaveException;

    T update(T domainModel, boolean partial) throws NotFoundException, SaveException;

    void delete(I id) throws DeleteException;

}