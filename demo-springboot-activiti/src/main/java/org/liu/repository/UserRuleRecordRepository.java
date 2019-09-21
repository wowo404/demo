package org.liu.repository;

import org.liu.pojo.UserRuleRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 数据持久层，简单增删改查用JpaRepository<T, ID extends Serializable>
 * 复杂查询使用JpaSpecificationExecutor<T>
 * @author liuzhsh
 */
public interface UserRuleRecordRepository extends JpaRepository<UserRuleRecord, Long>, JpaSpecificationExecutor<UserRuleRecord>{

}
