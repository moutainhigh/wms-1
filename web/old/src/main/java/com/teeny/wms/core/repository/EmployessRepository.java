package com.teeny.wms.core.repository;

import com.teeny.wms.core.domain.Employess;
import com.teeny.wms.dto.CommonDTO;
import com.teeny.wms.dto.EmployeesDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**mvn
 * Created by lilei on 2017/7/16.
 *
 */
@Repository
public interface EmployessRepository {

    @Select("SELECT e_id AS id, name FROM pda_employees")
    List<CommonDTO> findAll(String account);

    Employess findEmployerByUsername(@Param("username") String username, @Param("account") String account);

    public void addEmployer(Employess employess);
    public EmployeesDTO findByPinyin(@Param("username") String username);
}
