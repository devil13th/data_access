package com.thd.dataaccess.custom.impl;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

import com.thd.dataaccess.custom.CustomImportProgramAbsImpl;
import com.thd.dataaccess.service.DBService;
import com.thd.dataaccess.util.ListUtil;
import com.thd.dataaccess.util.StringUtil;
import com.thd.dataaccess.util.TreeUtil;

public class HrInitProgram extends CustomImportProgramAbsImpl{
	
	private String id;
	public HrInitProgram(DBService sourceService,DBService targetService,JdbcTemplate sourceTemplate,JdbcTemplate targetTemplate,String id){
		super(sourceService,targetService,sourceTemplate,targetTemplate);
		this.id = id;
	}
	
	public void runImport(){
		this.ipt(this.id);
	};
	private void ipt(String id){
		String dataSql = "select PROGRAM_ID,PROGRAM_CODE from HR_EXAM_PROGRAM where PROGRAM_ID = '" + id + "'" ;
		List<Map<String,Object>> l = this.getTargetService().query(dataSql);
		if(ListUtil.isNotEmpty(l)){
			Map<String,Object> m = l.get(0);
			String programCode = m.get("PROGRAM_CODE") == null ? "" : m.get("PROGRAM_CODE").toString();
			if(StringUtil.isNotEmpty(programCode)){//已有值
				//已有值不作处理了就
				System.out.println("success1 " + id);
			}else{//没有值
				String parentIdSql = "select t.kcid as ID,t1.kcid as PID from KNOWLEDGECATEGORY t left join  KNOWLEDGECATEGORY t1 on t.parentid = t1.kcid where t.kcid = " + id;
				List<Map<String,Object>> parent_l = this.getSourceService().query(parentIdSql);
				if(ListUtil.isNotEmpty(parent_l)){//有父节点
					Map<String,Object> parent_m = parent_l.get(0);
					String pid = parent_m.get("PID") == null ? "" : parent_m.get("PID").toString();
					String code = this.getCode(pid);
					if(StringUtil.isNotEmpty(code)){
						String updateCodeSql = "update HR_EXAM_PROGRAM set program_code = '" + code + "' where program_id='" + id + "'";
						this.getTargetService().execute(updateCodeSql);
						System.out.println("success2 " + id);
					}else{
						//递归父节点
						ipt(pid);
					}
				}else{//无父节点
					System.out.println("未找到父节点 [" + id +"]"); 
				}
			}
		}else{
			System.out.println("未找到节点 [" + id +"]"); 
		}
	}
	
	
	
	private String getCode(String id){
		String sql = "select PROGRAM_ID,PROGRAM_CODE from HR_EXAM_PROGRAM where PROGRAM_ID = '" + id + "'" ;
		List<Map<String,Object>> l = this.getTargetService().query(sql);
		if(l!=null && l.size() > 0){
			Map<String,Object> m = l.get(0);
			String programId = m.get("PROGRAM_ID").toString();
			String programCode = m.get("PROGRAM_CODE") == null ? "" : m.get("PROGRAM_CODE").toString();
			
			if(!"".equals(programCode)){
				String codeSql = " select max(PROGRAM_CODE) as CODE from HR_EXAM_PROGRAM where PROGRAM_CODE like '" + programCode +".%' and PROGRAM_CODE not like '" + programCode +".%.%'";
				Object obj = this.getTargetTemplate().queryForObject(codeSql,String.class);
				String maxCode = obj == null ? "" : obj.toString();
				int ct = 1;
				if(StringUtil.isNotEmpty(maxCode)){
					ct = TreeUtil.getCount(maxCode) + 1;
				}
				String newCode = programCode + "." + TreeUtil.createCode("00000", ct);
				return newCode;
			}else{
				return "";
			}
			
		}else{
			return "";
		}
	}
	
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
