package cn.rl520.cloud.system.service.impl;

import cn.echase.cloud.common.core.domain.model.RemoteClientDto;
import cn.echase.cloud.common.core.utils.DozerUtil;
import cn.echase.cloud.common.core.utils.StringUtils;
import cn.echase.cloud.common.core.web.page.TableDataInfo;
import cn.echase.cloud.common.core.web.service.BaseService;
import cn.rl520.cloud.system.domain.SysClient;
import cn.rl520.cloud.system.mapper.SysClientMapper;
import cn.rl520.cloud.system.service.ISysClientService;
import cn.rl520.cloud.system.vo.SysClientVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.f4b6a3.ulid.UlidCreator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 系统授权表 服务实现类
 * </p>
 *
 * @author wwb
 * @since 2024-01-03
 */
@Service
@RequiredArgsConstructor
public class SysClientServiceImpl extends BaseService implements ISysClientService {

    private final SysClientMapper sysClientMapper;

    @Override
    public void saveClient(RemoteClientDto remoteClientDto) {
        SysClient sysClient = new SysClient();
        DozerUtil.map(remoteClientDto,sysClient);
        if (StringUtils.isNotEmpty(remoteClientDto.getClientId())){
            QueryWrapper<SysClient> wrapper =  WrapperBuild(null);
            wrapper.eq("client_id",sysClient.getClientId());
            sysClientMapper.update(sysClient,wrapper);
        }else {
            sysClient.setClientId(UlidCreator.getUlid().toLowerCase());
            sysClientMapper.insert(sysClient);
        }
    }

    @Override
    public List<SysClientVo> getClients(RemoteClientDto remoteClientDto) {
        QueryWrapper<SysClient> wrapper =  WrapperBuild(null);
        List<SysClientVo> remoteClientVos = sysClientMapper.selectVoList(wrapper);
        return remoteClientVos;
    }

    @Override
    public TableDataInfo getClientPage(RemoteClientDto remoteClientDto) {
        QueryWrapper<SysClient> wrapper =  WrapperBuild(null);
        TableDataInfo tableDataInfo = getDataTable(sysClientMapper.selectVoPage(
                new Page<>(remoteClientDto.getCurrent(),remoteClientDto.getSize())
                ,wrapper, SysClientVo.class));
        return tableDataInfo;
    }

    @Override
    public SysClientVo getClientVo(RemoteClientDto remoteClientDto) {
        QueryWrapper<SysClient> wrapper =  WrapperBuild(null);
        wrapper.eq("client_id",remoteClientDto.getClientId());
        return sysClientMapper.selectVoOne(wrapper, SysClientVo.class);
    }


}
