package com.example.lr14.services;

import com.example.lr14.entities.Manager;
import com.example.lr14.repositories.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ManagerService {
    private final ManagerRepository repository;

    @Autowired
    public ManagerService(ManagerRepository repository) {
        this.repository = repository;
    }
    public Manager getById(Integer id) {
        return repository.findById(id).orElse(null);
    }
    public List<Manager> getAllManagers() {
        return repository.findAll();
    }
    public List<Manager> getAllManagers(String manager_name, String manager_phone_num, String manager_email) {
        return repository.findAll().stream()
                .filter(o -> manager_name.isBlank()|| o.getManager_name().contains(manager_name))
                .filter(o -> manager_phone_num.isBlank()|| o.getManager_phone_num().contains(manager_phone_num))
                .filter(o -> manager_email.isBlank()|| o.getManager_email().contains(manager_email))
                .collect(Collectors.toList());
    }

    public void add(Manager manager) {
        repository.save(manager);
    }
    public void delete(Manager manager) {
        repository.delete(manager);
    }
    public void update(Manager exist, Manager updated) {
        if (!updated.getManager_name().isBlank()) exist.setManager_name(updated.getManager_name());
        if (!updated.getManager_phone_num().isBlank()) exist.setManager_phone_num(updated.getManager_phone_num());
        if (!updated.getManager_email().isBlank()) exist.setManager_email(updated.getManager_email());
        repository.save(exist);
    }
}
