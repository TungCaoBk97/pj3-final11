package application.repository;

import application.model.Permission;
import application.model.Role;
import application.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PermissionRepository extends JpaRepository<Permission, Long> {

    @Query("select p from Permission p " +
            "inner join p.rolePermissions rp " +
            "inner join rp.role r " +
            "inner join r.userRoles ur " +
            "inner join ur.user u " +
            "where u = ?1")
    List<Permission> findAllByUser(User user);

    @Query("select p from Permission p " +
            "inner join p.rolePermissions rp " +
            "inner join rp.role r " +
            "inner join r.userRoles ur " +
            "inner join ur.user u " +
            "where u.username = ?1")
    List<Permission> findAllByUsername(String username);

    @Query("select p from Permission p " +
            "inner join p.rolePermissions rp " +
            "inner join rp.role r " +
            "where r.id = ?1")
    List<Permission> findPermissionByRole(long roleId);

    Permission findByName(String name);
}
