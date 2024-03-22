package be.flexteam.flex_imdb.domain.service;

import be.flexteam.flex_imdb.domain.exception.DeleteException;
import be.flexteam.flex_imdb.domain.exception.NotFoundException;
import be.flexteam.flex_imdb.domain.exception.SaveException;
import be.flexteam.flex_imdb.domain.model.Identifier;
import be.flexteam.flex_imdb.domain.port.input.GenericServicePort;
import be.flexteam.flex_imdb.domain.port.output.GenericPersistencePort;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Objects;


@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class GenericServiceImpl<T extends Identifier<I>,I> implements GenericServicePort<T,I> {

    private final GenericPersistencePort<T,I> genericPersistencePort;

    @Override
    public List<T> getAll() {
        return genericPersistencePort.getAll();
    }

    @Override
    public T get(I id) throws NotFoundException {
        return genericPersistencePort.get(Objects.requireNonNull(id,"id cannot be null"));
    }

    @Override
    public T save(T domainModel) throws SaveException {
        return genericPersistencePort.save(Objects.requireNonNull(domainModel,"model cannot be null"));
    }

    @Override
    public T update(T domainModel, boolean partial) throws SaveException, NotFoundException {
        return genericPersistencePort.update(Objects.requireNonNull(domainModel,"model cannot be null"), partial);
    }

    @Override
    public void delete(I id) throws DeleteException {
        genericPersistencePort.delete(Objects.requireNonNull(id,"id cannot be null"));
    }

}