package org.liu.repository;

import org.liu.pojo.Rule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RuleRepository extends JpaRepository<Rule, Long>, JpaSpecificationExecutor<Rule> {

}
