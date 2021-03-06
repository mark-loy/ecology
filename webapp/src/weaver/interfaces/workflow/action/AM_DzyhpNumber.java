package weaver.interfaces.workflow.action;

import weaver.conn.RecordSet;
import weaver.general.BaseBean;
import weaver.general.Util;
import weaver.soa.workflow.request.RequestInfo;

public class AM_DzyhpNumber extends BaseBean
  implements Action
{
  public String execute(RequestInfo request)
  {
    try
    {
      writeLog("低值易耗品入库，生成合同流水号--开始");
      RecordSet rs = new RecordSet();
      RecordSet rs1 = new RecordSet();
      RecordSet rs2 = new RecordSet();      
      String billid = request.getRequestid();      
      String htcode="";//入库流水号
      int htcount=0;//已入库流程数 
      String htcounts="";//已入库流程数
      String sql="select count(1)+1 as htcount from uf_am_artcles where htcode is not null and requestid!="+billid;
      rs.executeSql(sql);
      if (rs.next()) {
    	  htcounts=Util.null2String(rs.getString("htcount"));
    	  htcount =Integer.parseInt(Util.null2String(rs.getString("htcount")));
       }      
      if(htcount<10){
    	  htcode="00"+htcounts;
      }else if(htcount<100&&htcount>=10){
    	  htcode="0"+htcounts;
      }else{
    	  htcode=htcounts;
      }      
      sql="update uf_am_artcles set htcode='"+htcode+"' where requestid="+billid;
      rs1.executeSql(sql);
      sql="update uf_am_artcles_dt1 set assettypeno1=(select typeno from uf_am_articlestype where id=uf_am_artcles_dt1.assettype1)"
      +",assettypeno2=(select typeno from uf_am_articlestype where id=uf_am_artcles_dt1.assettype2)"      
      +",addressno=(select storecitynumber from uf_am_storecity where id=uf_am_artcles_dt1.address)"
      +",uuid=newid() where mainid=(select id from uf_am_artcles where requestid="+billid+")";
      rs2.executeSql(sql);
      writeLog("低值易耗品入库，生成合同流水号--成功");
    }
    catch (Exception e) {
      writeLog("低值易耗品入库，生成合同流水号--出错" + e);
      return "0";
    }
    return "1";
  }  
}