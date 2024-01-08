package cn.rl520.cloud.generator.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.echase.cloud.common.core.utils.JsonUtils;
import cn.echase.cloud.common.core.web.page.TableDataInfo;
import cn.echase.cloud.common.mybatis.plus.helper.DataBaseHelper;
import cn.echase.cloud.common.result.aop.EcareResponseResult;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.io.IoUtil;
import cn.rl520.cloud.generator.domain.GenTable;
import cn.rl520.cloud.generator.domain.GenTableColumn;
import cn.rl520.cloud.generator.service.IGenTableService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 代码生成 操作处理
 *
 * @author wenbo
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/gen")
@EcareResponseResult
public class GenController {

    private final IGenTableService genTableService;

    /**
     * 查询代码生成列表
     */
    @PostMapping("/list")
    public TableDataInfo genList(GenTable genTable) {
        return genTableService.selectPageGenTableList(genTable);
    }

    /**
     * 修改代码生成业务
     *
     * @param tableId 表ID
     */
    @GetMapping(value = "/{tableId}")
    public Map<String, Object> getInfo(@PathVariable Long tableId) {
        GenTable table = genTableService.selectGenTableById(tableId);
        List<GenTable> tables = genTableService.selectGenTableAll();
        List<GenTableColumn> list = genTableService.selectGenTableColumnListByTableId(tableId);
        Map<String, Object> map = new HashMap<>();
        map.put("info", table);
        map.put("rows", list);
        map.put("tables", tables);
        return map;
    }

    /**
     * 查询数据库列表
     */
    @GetMapping("/db/list")
    public TableDataInfo dataList(GenTable genTable) {
        System.out.println(JsonUtils.toJsonString(genTable));
        return genTableService.selectPageDbTableList(genTable);
    }

    /**
     * 查询数据表字段列表
     *
     * @param tableId 表ID
     */
    @GetMapping(value = "/column/{tableId}")
    public TableDataInfo columnList(@PathVariable("tableId") Long tableId) {
        TableDataInfo dataInfo = new TableDataInfo();
        List<GenTableColumn> list = genTableService.selectGenTableColumnListByTableId(tableId);
        dataInfo.setData(list);
        dataInfo.setTotal(list.size());
        return dataInfo;
    }

    /**
     * 导入表结构（保存）
     *
     * @param tables 表名串
     */
    @PostMapping("/importTable")
    public void importTableSave(String tables, String dataName) {
        String[] tableNames = Convert.toStrArray(tables);
        // 查询表信息
        List<GenTable> tableList = genTableService.selectDbTableListByNames(tableNames, dataName);
        genTableService.importGenTable(tableList, dataName);
    }

    /**
     * 修改保存代码生成业务
     */
    @PutMapping
    public void editSave(@Validated @RequestBody GenTable genTable) {
        genTableService.validateEdit(genTable);
        genTableService.updateGenTable(genTable);
    }

    /**
     * 删除代码生成
     *
     * @param tableIds 表ID串
     */
    @DeleteMapping("/{tableIds}")
    public void remove(@PathVariable Long[] tableIds) {
        genTableService.deleteGenTableByIds(tableIds);
    }

    /**
     * 预览代码
     *
     * @param tableId 表ID
     */
    @GetMapping("/preview/{tableId}")
    public Map<String, String> preview(@PathVariable("tableId") Long tableId) throws IOException {
        Map<String, String> dataMap = genTableService.previewCode(tableId);
        return dataMap;
    }

    /**
     * 生成代码（下载方式）
     *
     * @param tableId 表ID
     */
    @GetMapping("/download/{tableId}")
    public void download(HttpServletResponse response, @PathVariable("tableId") Long tableId) throws IOException {
        byte[] data = genTableService.downloadCode(tableId);
        genCode(response, data);
    }

    /**
     * 生成代码（自定义路径）
     *
     * @param tableId 表ID
     */
    @SaCheckPermission("tool:gen:code")
    @GetMapping("/genCode/{tableId}")
    public void genCode(@PathVariable("tableId") Long tableId) {
        genTableService.generatorCode(tableId);
    }

    /**
     * 同步数据库
     *
     * @param tableId 表ID
     */
    @GetMapping("/synchDb/{tableId}")
    public void synchDb(@PathVariable("tableId") Long tableId) {
        genTableService.synchDb(tableId);
    }

    /**
     * 批量生成代码
     *
     * @param tableIdStr 表ID串
     */
    @GetMapping("/batchGenCode")
    public void batchGenCode(HttpServletResponse response, String tableIdStr) throws IOException {
        String[] tableIds = Convert.toStrArray(tableIdStr);
        byte[] data = genTableService.downloadCode(tableIds);
        genCode(response, data);
    }

    /**
     * 生成zip文件
     */
    private void genCode(HttpServletResponse response, byte[] data) throws IOException {
        response.reset();
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Expose-Headers", "Content-Disposition");
        response.setHeader("Content-Disposition", "attachment; filename=\"ruoyi.zip\"");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");
        IoUtil.write(response.getOutputStream(), false, data);
    }

    /**
     * 查询数据源名称列表
     */
    @GetMapping(value = "/getDataNames")
    public Object getCurrentDataSourceNameList(){
        return DataBaseHelper.getDataSourceNameList();
    }
}
