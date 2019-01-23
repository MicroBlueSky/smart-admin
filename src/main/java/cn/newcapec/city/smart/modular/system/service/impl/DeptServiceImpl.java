package cn.newcapec.city.smart.modular.system.service.impl;

import cn.newcapec.city.smart.core.core.node.ZTreeNode;
import cn.newcapec.city.smart.modular.system.dao.DeptMapper;
import cn.newcapec.city.smart.modular.system.model.Dept;
import cn.newcapec.city.smart.modular.system.service.IDeptService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements IDeptService {

    @Resource
    private DeptMapper deptMapper;

    @Override
    public void deleteDept(String deptId) {
        Dept dept = deptMapper.selectById(deptId);

        Wrapper<Dept> wrapper = new EntityWrapper<>();
        wrapper = wrapper.like("pids", "%[" + dept.getId() + "]%");
        List<Dept> subDepts = deptMapper.selectList(wrapper);
        for (Dept temp : subDepts) {
            temp.deleteById();
        }

        dept.deleteById();
    }

    @Override
    public List<ZTreeNode> tree() {
        return this.baseMapper.tree();
    }

    @Override
    public List<Map<String, Object>> list(String condition) {
        return this.baseMapper.list(condition);
    }
}
