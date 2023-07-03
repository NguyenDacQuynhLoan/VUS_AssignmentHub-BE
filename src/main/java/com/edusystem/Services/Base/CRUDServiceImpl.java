//package com.edusystem.Services.Base;
//
//import com.edusystem.Repositories.CRUDRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class CRUDServiceImpl<T> implements CRUDService<T>{
//    @Autowired
//    CRUDRepository<T> _repository;
//
//    @Override
//    public T createAsync(T model) {
//        return _repository.save(model);
//    }
//
//    @Override
//    public List<T> getAllAsync() {return  _repository.findAll();}
//
//    @Override
//    public T getByIdAsync(Long id) {
//        Optional<T> detectItem = _repository.findById(id);
//        if(detectItem.isPresent()){
//            return  detectItem.get();
//        }
//        return null;
//    }
//
//    @Override
//    public T updateAsync(T model,Long id) {
//        T isExisted = getByIdAsync(id);
//        if (isExisted != null){
//            return _repository.save(model);
//        }
//        return null;
//    }
//
//    @Override
//    public boolean DeleteItemAsync(Long id) {
//        T detectItem = getByIdAsync(id);
//        if (detectItem !=null){
//            _repository.deleteById(id);
//            return true;
//        }
//        return false;
//    }
//}
