package cn.newcapec.city.smart.modular.system.service.impl;

import cn.newcapec.city.smart.modular.system.dao.NoticeMapper;
import cn.newcapec.city.smart.modular.system.model.Notice;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import cn.newcapec.city.smart.modular.system.service.INoticeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 通知表 服务实现类
 * </p>
 *
 * @author stylefeng123
 * @since 2018-02-22
 */
@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements INoticeService {

    @Override
    public List<Map<String, Object>> list(String condition) {
        return this.baseMapper.list(condition);
    }
}
