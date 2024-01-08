package cn.rl520.cloud.system.controller;

import cn.echase.cloud.common.core.domain.model.RemoteClientDto;
import cn.echase.cloud.common.core.web.page.TableDataInfo;
import cn.echase.cloud.common.result.aop.EcareResponseResult;
import cn.rl520.cloud.system.service.impl.SysClientServiceImpl;
import cn.rl520.cloud.system.vo.SysClientVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 系统授权表 前端控制器
 * </p>
 *
 * @author wwb
 * @since 2024-01-03
 */
@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
@EcareResponseResult
public class SysClientController {

    private final SysClientServiceImpl sysClientService;

    @RequestMapping(value = "saveClient",method = RequestMethod.POST)
    public void saveClient(@RequestBody RemoteClientDto remoteClientDto){
        sysClientService.saveClient(remoteClientDto);
    }

    @RequestMapping(value = "getClients",method = RequestMethod.POST)
    public List<SysClientVo> getClients(@RequestBody RemoteClientDto remoteClientDto){
        return sysClientService.getClients(remoteClientDto);
    }

    @RequestMapping(value = "getClientPage",method = RequestMethod.POST)
    public TableDataInfo getClientPage(@RequestBody RemoteClientDto remoteClientDto){
        return sysClientService.getClientPage(remoteClientDto);
    }

    @RequestMapping(value = "getClientVo",method = RequestMethod.POST)
    public SysClientVo getClientVo(@RequestBody RemoteClientDto remoteClientDto){
        return sysClientService.getClientVo(remoteClientDto);
    }

}
