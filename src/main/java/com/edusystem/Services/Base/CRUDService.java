package com.edusystem.Services.Base;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CRUDService<T>{
    /**
     *  Create item by model
     * @param model T model
     * @return created new model
     */
    public T createAsync(T model);

    /**
     *  Get all items of T
     * @return all items of T
     */
    public List<T> getAllAsync();

    /**
     *  Get item by Id
     * @param id item Id
     * @return found item
     */
    public T getByIdAsync(Long id);

    /**
     *  Update item
     * @param id item Id
     * @return updated model
     */
    public T updateAsync(T model,Long id);

    /**
     *  Delete item
     * @param id item Id
     */
    public boolean DeleteItemAsync(Long id);
}
