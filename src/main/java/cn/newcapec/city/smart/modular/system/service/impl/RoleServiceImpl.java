package cn.newcapec.city.smart.modular.system.service.impl;

import cn.newcapec.city.smart.core.core.node.ZTreeNode;
import cn.newcapec.city.smart.core.core.util.Convert;
import cn.newcapec.city.smart.modular.system.dao.RelationMapper;
import cn.newcapec.city.smart.modular.system.dao.RoleMapper;
import cn.newcapec.city.smart.modular.system.model.Relation;
import cn.newcapec.city.smart.modular.system.model.Role;
import cn.newcapec.city.smart.modular.system.service.IRoleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private RelationMapper relationMapper;

    @Override
    @Transactional(readOnly = false)
    public void setAuthority(String roleId, String ids) {

        // 删除该角色所有的权限
        this.roleMapper.deleteRolesById(roleId);

        // 添加新的权限
        for (String id :  Convert.toStrArray(",", ids)) {
            Relation relation = new Relation();
            relation.setRoleid(roleId);
            relation.setMenuid(id);
            this.relationMapper.insert(relation);
        }
    }

    @Override
    @Transactional(readOnly = false)
    public void delRoleById(String roleId) {
        //删除角色
        this.roleMapper.deleteById(roleId);

        // 删除该角色所有的权限
        this.roleMapper.deleteRolesById(roleId);
    }

    @Override
    public List<Map<String, Object>> selectRoles(String condition) {
        return this.baseMapper.selectRoles(condition);
    }

    @Override
    public int deleteRolesById(String roleId) {
        return this.baseMapper.deleteRolesById(roleId);
    }

    @Override
    public List<ZTreeNode> roleTreeList() {
        return this.baseMapper.roleTreeList();
    }

    @Override
    public List<ZTreeNode> roleTreeListByRoleId(String[] roleId) {
        return this.baseMapper.roleTreeListByRoleId(roleId);
    }

}
