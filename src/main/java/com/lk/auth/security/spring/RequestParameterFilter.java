package com.lk.auth.security.spring;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.regex.Pattern;


/**
 * request参数过滤器，过滤js攻击，sql注入，去首尾空格
 * @author lihanchen
 */
public class RequestParameterFilter extends HttpServlet implements Filter {

	private static final long serialVersionUID = 197510723001468145L;
	public RequestParameterFilter() {
	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		request.setCharacterEncoding("utf-8");
		HashMap<String, Object> mapRequest = new HashMap<String, Object>(request.getParameterMap());
		HashMap<String, String[]> mapParams = new HashMap<String, String[]>();
		Enumeration<String> enu = request.getParameterNames();

		if (mapRequest.size() > 0 && processParameters(mapRequest, enu, mapParams)) {
			ParameterRequestWrapper wrapRequest = new ParameterRequestWrapper(request, mapParams);
			chain.doFilter(wrapRequest, res);
		} else {
			chain.doFilter(req, res);
		}
	}

	private static Pattern SCRIPT_PATTERN = Pattern.compile("script", Pattern.CASE_INSENSITIVE);
	public boolean processParameters(HashMap<String, Object> mapRequest, Enumeration<String> enu, HashMap<String, String[]> mapParams) {
		if (mapRequest != null && enu != null) {
			while (enu.hasMoreElements()) {
				String key = enu.nextElement();
				Object value = mapRequest.get(key);
				if (value instanceof String[]) {
					// 遍历参数
					String[] values = (String[]) value;
					for (int i = 0, len = values.length; i < len; i++) {
						// 过滤参数值带script字符
						values[i] = SCRIPT_PATTERN.matcher(values[i]).replaceAll("&#x73;cript");
						values[i] = values[i].replace("<", "&lt;");
						values[i] = values[i].replace(">", "&gt;");
						values[i] = sqlValidate(values[i]);//过滤sql关键字
						values[i] = values[i].trim(); // 去除字符头尾空格
					}
					mapParams.put(key, values);
				}
			}
		}
		return true;
	}
	
	private static String sqlValidate(String str) {
        String badStr = "from |and |where |exec |execute |insert |select |delete |update |create |drop |chr|truncate|to_date(|decode(|" +
                "alter |char |declare |sitename |like '|like '%|grant |use |order by |count *|group_concat |xp_cmdshell |" +  
                "mid |master |lock table |column_name |net user |information_schema.columns |table_schema |union |ascii ";
        String[] badStrs = badStr.split("\\|");
        boolean hasSql = false;
        String temp = str.toLowerCase();//统一转为小写
        for (int i = 0; i < badStrs.length; i++) {  
            if (temp.indexOf(badStrs[i]) >= 0) {  
            	hasSql = true;
            	temp = temp.replace(badStrs[i], "");
            }  
        }
        if(hasSql) return temp;
        return str;  
    }  
	
	public void init(FilterConfig config) throws ServletException {
	}
	
	public void destroy() {
	}
}
