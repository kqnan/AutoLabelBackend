package me.kqn.autolabel.mapper;

import me.kqn.autolabel.entity.ProjectsProject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author kurt_kong
 * @since 2023-05-18
 */
@Mapper
public interface ProjectsProjectMapper extends BaseMapper<ProjectsProject> {
    @Select("SELECT *\n" +
            "FROM projects_project\n" +
            "WHERE id >= #{startID}\n" +
            "ORDER BY #{ordering} DESC\n" +
            " LIMIT #{limit};")
    List<ProjectsProject> getCreated_atDESC(@Param("limit")  Integer limit, @Param("startID") Integer startID,@Param("ordering")String ordering);

    @Select("SELECT MAX(id) FROM projects_project;")
    Integer maxID();

    @Select("SELECT *\n" +
            "FROM projects_project\n" +
            "WHERE id >= #{startID}\n" +
            "ORDER BY #{ordering} ASC\n" +
            " LIMIT #{limit};")
    List<ProjectsProject> getCreated_atASC(@Param("limit")  Integer limit, @Param("startID")Integer   startID,@Param("ordering")String ordering);

}
