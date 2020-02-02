package cn.tycoding.service;

import cn.tycoding.pojo.User;

import java.util.List;
import java.util.TreeMap;

/**
 * @author tycoding
 * @date 18-4-7下午9:09
 */
public interface IExcelExportService extends BaseService<User> {

    List<TreeMap<String, Object>> export(Integer limit);

}
