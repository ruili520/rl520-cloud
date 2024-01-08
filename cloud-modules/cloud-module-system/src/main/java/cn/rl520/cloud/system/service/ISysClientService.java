package cn.rl520.cloud.system.service;

import cn.echase.cloud.common.core.domain.model.RemoteClientDto;
import cn.echase.cloud.common.core.web.page.TableDataInfo;
import cn.rl520.cloud.system.vo.SysClientVo;

import java.util.List;

/**
 * <p>
 * 系统授权表 服务类
 * </p>
 *
 * @author wwb
 * @since 2024-01-03
 */
public interface ISysClientService {

    void saveClient(RemoteClientDto remoteClientDto);

    List<SysClientVo> getClients(RemoteClientDto remoteClientDto);

    TableDataInfo getClientPage(RemoteClientDto remoteClientDto);

    SysClientVo getClientVo(RemoteClientDto remoteClientDto);

}
