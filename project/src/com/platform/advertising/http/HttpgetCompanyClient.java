package com.platform.advertising.http;

import java.util.ArrayList;

import sxp.android.framework.util.StringUtil;

import com.platform.advertising.ui.data.CompanyData;
import com.platform.advertising.ui.find.data.Company;

/**
 * 
 * @author shanxiaoping 获取公司
 */
public class HttpgetCompanyClient extends HttpAdvertisingClient {

	@Override
	protected String getAdress() {
		// TODO Auto-generated method stub
		return "getCompanyList";
	}

	@Override
	protected String[] getContentPramasKeys() {
		// TODO Auto-generated method stub
		return new String[] { "pageNumber", "pageSize",
//		 "districtId",
//		 "provinceId",
//		 "cityId",
//		 "keyword"
		};
	}

	private ArrayList<CompanyData> list;

	@Override
	protected void parserMessage(String data) {
		// TODO Auto-generated method stub
		super.parserMessage(data);
		if (!StringUtil.isJsonEmpty(data)) {
			list = CompanyData.getList(data);
		}
	}

	public ArrayList<CompanyData> getList() {
		return list;
	}

	public void setList(ArrayList<CompanyData> list) {
		this.list = list;
	}
	
}
