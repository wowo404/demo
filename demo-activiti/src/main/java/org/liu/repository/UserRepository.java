package org.liu.repository;

import java.util.List;

import org.liu.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * 数据持久层，简单增删改查用JpaRepository<T, ID extends Serializable>
 * 复杂查询使用JpaSpecificationExecutor<T>
 * @author liuzhsh
 */
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

	User findByEmail(String email);

	/**
	 * 可以通过?占位符，也可以通过:的形式，:需要配合@Param
	 * @param userName
	 * @return
	 */
	@Query(value = "select * from user u where u.user_name = ?1", nativeQuery = true)
	List<User> listByCustomSql(String userName);
	
	@Query("select u from User u where u.userName = :userName")
	List<User> listByHql(@Param("userName") String userName);

}
