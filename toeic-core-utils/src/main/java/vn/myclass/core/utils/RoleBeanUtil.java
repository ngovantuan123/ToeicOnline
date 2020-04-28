package vn.myclass.core.utils;

import vn.myclass.core.dto.RoleDTO;
import vn.myclass.core.persistence.entity.RoleEntity;

public class RoleBeanUtil {
    public static RoleDTO entity2Dto(RoleEntity entity)
    {
        RoleDTO dto=new RoleDTO();
        dto.setRoleid(entity.getRoleid());
        dto.setName(entity.getName());
        return dto;

    }
    public static RoleEntity dto2Entity(RoleDTO dto)
    {
        RoleEntity entity=new RoleEntity();
        entity.setRoleid(dto.getRoleid());
        entity.setName(dto.getName());
        return entity;

    }
}
