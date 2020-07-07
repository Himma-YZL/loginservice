package com.api.login.apilogin.shiro.mapper;

import com.api.login.apilogin.shiro.entity.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yangzhilong
 * @since 2020-07-07
 */
public interface PermissionMapper extends BaseMapper<Permission> {

    @Select("SELECT * from permission where id in (select permission_id from role_permission where role_id = #{roleId})")
    List<Permission> getPermissionByRoleId(Long roleId);
}
