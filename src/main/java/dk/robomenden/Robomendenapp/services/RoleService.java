package dk.robomenden.Robomendenapp.services;

import dk.robomenden.Robomendenapp.models.Role;
import dk.robomenden.Robomendenapp.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    //Метод для получения сущности по ID
    //A method to retrieve an entity by ID
    public Role findById(int id) {
        return roleRepository.findById(id).get();
    }

    //Метод для редактирования сущности
    //Method for editing an entity
    @Transactional
    public void edit(int id, Role role) {
        role.setId(id);
        roleRepository.save(role);
    }

    //Метод для поиска сущности по полю name
    //A method to search for an entity by the name field
    public Role findByRoleName(String role_name) {
        return roleRepository.findByRoleName(role_name);
    }

    public List<Role> findAll() {
        return roleRepository.findAll();
    }
}
