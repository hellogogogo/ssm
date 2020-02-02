package cn.tycoding.service.impl;

import cn.tycoding.mapper.IExcelExportDAO;
import cn.tycoding.pojo.User;
import cn.tycoding.service.IExcelExportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.TreeMap;

/**
 * @author tycoding
 * @date 18-4-7下午9:09
 */
@Service
public class ExcelExportImpl implements IExcelExportService {

    //注入
    @Autowired
    private IExcelExportDAO excelExportDAO;

    @Override
    public List<TreeMap<String, Object>> export(Integer limit) {
        return excelExportDAO.export(limit);
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User findById(Long id) {
        return null;
    }

    @Override
    public void create(User user) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void update(User user) {

    }
}
