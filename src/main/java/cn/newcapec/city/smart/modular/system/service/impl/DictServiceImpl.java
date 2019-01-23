package cn.newcapec.city.smart.modular.system.service.impl;

import cn.newcapec.city.smart.core.common.constant.factory.MutiStrFactory;
import cn.newcapec.city.smart.core.common.exception.BizExceptionEnum;
import cn.newcapec.city.smart.core.core.exception.GunsException;
import cn.newcapec.city.smart.modular.system.dao.DictMapper;
import cn.newcapec.city.smart.modular.system.model.Dict;
import cn.newcapec.city.smart.modular.system.service.IDictService;
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
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements IDictService {

    @Resource
    private DictMapper dictMapper;

    @Override
    public void addDict(String dictCode,String dictName,String dictTips, String dictValues) {
        //判断有没有该字典
        List<Dict> dicts = dictMapper.selectList(new EntityWrapper<Dict>().eq("code", dictCode).and().eq("pid", '0'));
        if (dicts != null && dicts.size() > 0) {
            throw new GunsException(BizExceptionEnum.DICT_EXISTED);
        }

        //解析dictValues
        List<Map<String, String>> items = MutiStrFactory.parseKeyValue(dictValues);

        //添加字典
        Dict dict = new Dict();
        dict.setName(dictName);
        dict.setCode(dictCode);
        dict.setTips(dictTips);
        dict.setNum("0");
        dict.setPid("0");
        this.dictMapper.insert(dict);

        //添加字典条目
        for (Map<String, String> item : items) {
            String code = item.get(MutiStrFactory.MUTI_STR_CODE);
            String name = item.get(MutiStrFactory.MUTI_STR_NAME);
            String num = item.get(MutiStrFactory.MUTI_STR_NUM);
            Dict itemDict = new Dict();
            itemDict.setPid(dict.getId());
            itemDict.setCode(code);
            itemDict.setName(name);

            try {
                itemDict.setNum(num);
            } catch (NumberFormatException e) {
                throw new GunsException(BizExceptionEnum.DICT_MUST_BE_NUMBER);
            }
            this.dictMapper.insert(itemDict);
        }
    }

    @Override
    public void editDict(String dictId,String dictCode, String dictName,String dictTips, String dicts) {
        //删除之前的字典
        this.delteDict(dictId);

        //重新添加新的字典
        this.addDict(dictCode,dictName,dictTips, dicts);
    }

    @Override
    public void delteDict(String dictId) {
        //删除这个字典的子词典
        Wrapper<Dict> dictEntityWrapper = new EntityWrapper<>();
        dictEntityWrapper = dictEntityWrapper.eq("pid", dictId);
        dictMapper.delete(dictEntityWrapper);

        //删除这个词典
        dictMapper.deleteById(dictId);
    }

    @Override
    public List<Dict> selectByCode(String code) {
        return this.baseMapper.selectByCode(code);
    }

    @Override
    public List<Dict> selectByParentCode(String code) {
        return this.baseMapper.selectByParentCode(code);
    }


    @Override
    public List<Map<String, Object>> list(String conditiion) {
        return this.baseMapper.list(conditiion);
    }
}
