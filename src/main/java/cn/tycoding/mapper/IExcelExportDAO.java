package cn.tycoding.mapper;

import java.util.List;
import java.util.TreeMap;

/**
 * @author tycoding
 * @date 18-4-7下午9:10
 */
public interface IExcelExportDAO {


    public List<TreeMap<String, Object>> export(Integer limit);

}
