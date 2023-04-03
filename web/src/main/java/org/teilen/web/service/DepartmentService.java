package org.teilen.web.service;

import org.teilen.web.model.Department;
import org.teilen.web.model.Organization;
import org.teilen.web.model.User;

import java.util.List;

/**
 * @author gentjan kolicaj
 */
public interface DepartmentService {

    public abstract List<Department> getAll() throws Exception;

    public abstract List<Department> getAllById(List<Long> ids) throws Exception;

    public abstract Department getById(Long id) throws Exception;

    public abstract Department create(Department entity) throws Exception;

    public abstract List<Department> createAll(List<Department> entities) throws Exception;

    public abstract Department edit(Department entity) throws Exception;

    public abstract List<Department> editAll(List<Department> entities) throws Exception;

    public abstract void deleteById(Long id) throws Exception;

    public abstract void delete(Department entity) throws Exception;

    public abstract void deleteAll(List<Department> entities) throws Exception;

    public abstract boolean existById(Long id) throws Exception;

    public abstract List<Department> getDepartmentByName(String name) throws Exception;

    public abstract List<Department> getDepartmentByCreatorId(Long creatorId) throws Exception;

    //==============================
    public abstract Department changeName(Department department, String name) throws Exception;

    public abstract Department changeDescription(Department department, String description) throws Exception;

    public abstract Department createDepartment(User user, Organization organization, String name, String description) throws Exception;


}
