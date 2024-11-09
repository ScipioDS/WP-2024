package com.webprog.demo1.service.implementaion;

import com.webprog.demo1.model.Manufacturer;
import com.webprog.demo1.repository.InMemoryManufacturerRepository;
import com.webprog.demo1.service.ManufacturerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {
    private final InMemoryManufacturerRepository manufacturerRepository;
    public ManufacturerServiceImpl(InMemoryManufacturerRepository manufacturerRepository){
        this.manufacturerRepository = manufacturerRepository;
    }
    @Override
    public List<Manufacturer> findAll() {
        return manufacturerRepository.findAll();
    }

    @Override
    public Optional<Manufacturer> findById(Long id) {
        return manufacturerRepository.findById(id);
    }

    @Override
    public Optional<Manufacturer> save(String name, String address) {
        return manufacturerRepository.save(name, address);
    }

    @Override
    public boolean deleteById(Long id) {
        return manufacturerRepository.deleteById(id);
    }
}
