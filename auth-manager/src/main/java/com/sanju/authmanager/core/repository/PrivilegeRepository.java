package com.sanju.authmanager.core.repository;

import com.sanju.authmanager.core.entity.PrivilegeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivilegeRepository extends JpaRepository<PrivilegeEntity,Long> {
}
