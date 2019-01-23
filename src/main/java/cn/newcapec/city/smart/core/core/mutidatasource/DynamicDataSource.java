package cn.newcapec.city.smart.core.core.mutidatasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.logging.Logger;

/**
 * 动态数据源
 *
 * @author fengshuonan
 * @date 2017年3月5日 上午9:11:49
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

	@Override
	protected Object determineCurrentLookupKey() {
		return DataSourceContextHolder.getDataSourceType();
	}

	@Override
	public Logger getParentLogger() {
		return null;
	}
}
