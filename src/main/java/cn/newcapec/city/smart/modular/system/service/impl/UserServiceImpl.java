package cn.newcapec.city.smart.modular.system.service.impl;

import cn.newcapec.city.smart.core.core.datascope.DataScope;
import cn.newcapec.city.smart.modular.system.dao.UserMapper;
import cn.newcapec.city.smart.modular.system.model.User;
import cn.newcapec.city.smart.modular.system.service.IUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 管理员表 服务实现类
 * </p>
 *
 * @author stylefeng123
 * @since 2018-02-22
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public int setStatus(String userId, String status) {
        return this.baseMapper.setStatus(userId, status);
    }

    @Override
    public int changePwd(String userId, String pwd) {
        return this.baseMapper.changePwd(userId, pwd);
    }

    @Override
    public List<Map<String, Object>> selectUsers(DataScope dataScope, String name, String beginTime, String endTime, String deptid) {
        return this.baseMapper.selectUsers(dataScope, name, beginTime, endTime, deptid);
    }

    @Override
    public int setRoles(String userId, String roleIds) {
        return this.baseMapper.setRoles(userId, roleIds);
    }

    @Override
    public User getByAccount(String account) {
        return this.baseMapper.getByAccount(account);
    }
}
