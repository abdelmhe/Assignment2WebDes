
package com.abdelmhe.sheridancollege.demo.dao;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DatabaseAccess extends JpaRepository<Team, Long> {


    @Query("SELECT t FROM Team t WHERE lower(t.teamName) LIKE lower(concat('%',?1,'%')) OR lower(t.continent) LIKE lower(concat('%',?1,'%'))")
    public List<Team> search(String keyword);


}
