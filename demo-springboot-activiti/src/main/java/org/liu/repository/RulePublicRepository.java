package org.liu.repository;

import org.liu.pojo.RulePublic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RulePublicRepository extends JpaRepository<RulePublic, Long>, JpaSpecificationExecutor<RulePublic> {

}
