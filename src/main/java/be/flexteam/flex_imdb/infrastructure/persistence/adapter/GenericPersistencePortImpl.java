package be.flexteam.flex_imdb.infrastructure.persistence.adapter;

import be.flexteam.flex_imdb.domain.exception.DeleteException;
import be.flexteam.flex_imdb.domain.exception.NotFoundException;
import be.flexteam.flex_imdb.domain.exception.SaveException;
import be.flexteam.flex_imdb.domain.model.Identifier;
import be.flexteam.flex_imdb.domain.port.output.GenericPersistencePort;
import jakarta.persistence.OptimisticLockException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Objects;


@RequiredArgsConstructor
public abstract class GenericPersistencePortImpl<D,J extends JpaRepository<D,I>,T extends Identifier<I>,I> implements GenericPersistencePort<T,I> {

    protected final J jpaRepository;

    protected abstract D map(T model);
    protected abstract T map(D entity);
    protected abstract void partialUpdate(T model, D entity);
    protected abstract I getId(D entity);

    @Override
    public List<T> getAll() {
        return jpaRepository.findAll().stream().map(this::map).toList();
    }

    @Override
    public T get(I id) {
        try {
            return jpaRepository.findById(id).map(this::map).orElseThrow(() -> new NotFoundException("id=" + id + " is not found in DB"));
        }catch (IllegalArgumentException e){
            throw new NotFoundException("id is null",e);
        }
    }

    @Override
    public T save(T domainModel) {
        try {
            return map(jpaRepository.save(map(domainModel)));
        } catch (IllegalArgumentException|OptimisticLockException e) {
            throw new SaveException("id=" + domainModel.getId() + " cannot be saved", e);
        }
    }

    @Override
    public T update(T domainModel, boolean partial) {
        Objects.requireNonNull(domainModel,"model cannot be null");

        D entityToUpdate;
        try {
            if (!jpaRepository.existsById(domainModel.getId())) {
                throw new NotFoundException("id=" + domainModel.getId() + " doesn't exist");
            }

            if (partial) {
                entityToUpdate = jpaRepository.findById(domainModel.getId()).orElseThrow(()-> new IllegalStateException("The entity must be found"));
                partialUpdate(domainModel, entityToUpdate);
            } else {
                entityToUpdate = map(domainModel);
            }
        }catch (IllegalArgumentException e){
            throw new NotFoundException("Movie with no id cannot be null during the update");
        }

        try {
            Objects.requireNonNull(getId(entityToUpdate),"The id has been lost before the update and must require");
            return map(jpaRepository.save(entityToUpdate));
        } catch (IllegalArgumentException|OptimisticLockException e) {
            throw new SaveException("id=" + domainModel.getId() + " cannot be updated", e);
        }
    }

    @Override
    public void delete(I id) {
        try {
            jpaRepository.deleteById(id);
        }catch (IllegalArgumentException e){
            throw new DeleteException("id is null",e);
        }
    }

}