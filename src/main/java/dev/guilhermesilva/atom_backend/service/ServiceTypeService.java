package dev.guilhermesilva.atom_backend.service;

import dev.guilhermesilva.atom_backend.dto.request.ServiceTypeRequest;
import dev.guilhermesilva.atom_backend.dto.response.ServiceTypeResponse;
import dev.guilhermesilva.atom_backend.entity.ServiceType;
import dev.guilhermesilva.atom_backend.exception.BusinessException;
import dev.guilhermesilva.atom_backend.exception.ResourceNotFoundException;
import dev.guilhermesilva.atom_backend.mapper.ServiceTypeMapper;
import dev.guilhermesilva.atom_backend.repository.ServiceTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceTypeService {

    private final ServiceTypeRepository serviceTypeRepository;
    private final ServiceTypeMapper serviceTypeMapper;

    @Transactional
    public ServiceTypeResponse create(ServiceTypeRequest request) {
        validateServiceTypeNameDoesNotExist(request.getName());

        ServiceType serviceType = serviceTypeMapper.toEntity(request);
        ServiceType savedServiceType = serviceTypeRepository.save(serviceType);

        return serviceTypeMapper.toResponse(savedServiceType);
    }

    @Transactional(readOnly = true)
    public List<ServiceTypeResponse> findAll() {
        return serviceTypeRepository.findAll()
                .stream()
                .map(serviceTypeMapper::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<ServiceTypeResponse> findAllActive() {
        return serviceTypeRepository.findByActiveTrue()
                .stream()
                .map(serviceTypeMapper::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public ServiceTypeResponse findById(Long id) {
        ServiceType serviceType = getServiceTypeById(id);

        return serviceTypeMapper.toResponse(serviceType);
    }

    @Transactional
    public ServiceTypeResponse update(Long id, ServiceTypeRequest request) {
        ServiceType serviceType = getServiceTypeById(id);

        validateServiceTypeNameDoesNotExistForAnotherService(request.getName(), id);

        serviceTypeMapper.updateEntityFromRequest(serviceType, request);

        ServiceType updatedServiceType = serviceTypeRepository.save(serviceType);

        return serviceTypeMapper.toResponse(updatedServiceType);
    }

    @Transactional
    public void deactivate(Long id) {
        ServiceType serviceType = getServiceTypeById(id);

        if (Boolean.FALSE.equals(serviceType.getActive())) {
            throw new BusinessException("Service type is already inactive");
        }

        serviceType.setActive(false);
        serviceTypeRepository.save(serviceType);
    }

    private ServiceType getServiceTypeById(Long id) {
        return serviceTypeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Service type not found with id: " + id));
    }

    private void validateServiceTypeNameDoesNotExist(String name) {
        boolean exists = serviceTypeRepository.existsByNameIgnoreCase(name);

        if (exists) {
            throw new BusinessException("Service type already exists with name: " + name);
        }
    }

    private void validateServiceTypeNameDoesNotExistForAnotherService(String name, Long id) {
        boolean exists = serviceTypeRepository.existsByNameIgnoreCaseAndIdNot(name, id);

        if (exists) {
            throw new BusinessException("Another service type already exists with name: " + name);
        }
    }
}