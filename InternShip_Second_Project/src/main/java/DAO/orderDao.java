package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.orderModel;
import model.product;



public class orderDao {

	  private Connection con;
	  private String query;
	  private PreparedStatement pst;
	  private ResultSet rs;
	  
	  
	  public  orderDao(Connection con)
	  {
		  this.con=con;
	  }
	  
	  public boolean insertOrder(orderModel model)
	  {
		  boolean result=false;
		  try {
			  
			  
			     query="insert into orders (p_id, u_id, o_quantity, o_date) values(?,?,?,?)";
			     pst=this.con.prepareStatement(query);
			     pst.setInt(1, model.getId());
			     pst.setInt(2, model.getUid());
			     pst.setInt(3, model.getQuantity());
			     pst.setString(4, model.getDate());
			     pst.executeUpdate();
			     result=true;
			     
		  }
		  catch(Exception e)
		  {
			    e.printStackTrace();
		  }
		  
		  return result;
		  
	  }

		public List<orderModel> userOrders(int idd){
		  
		  List<orderModel> list=new ArrayList<>();
		  
		    try {
		    	
		    	  query="select * from orders where u_id=? order by orders.o_id desc";
		    	  pst=this.con.prepareStatement(query);
		    	  pst.setInt(1, idd);
		    	   rs=pst.executeQuery();
		    	   while(rs.next())
		    	   {
		    		   orderModel orderss=new  orderModel();
		    		   productDao productDao=new productDao(this.con);
		    		   int pid=rs.getInt("p_id");
		    		   product productss= productDao.getSignleProduct(pid);
		    		   orderss.setOrderId(rs.getInt("o_id"));
		    		   orderss.setName(productss.getCategory());
		    		   orderss.setPrice(productss.getPrice()*rs.getInt("o_quantity"));
		    		   orderss.setQuantity(rs.getInt("o_quantity"));
		    		   orderss.setDate(rs.getString("o_date"));
		    		   list.add(orderss);
		    	   }
		    	  
		    }catch(Exception e)
		    {
		    	
		    }
		  return list;
		  
		  
	  }
	  public void cancelOrder(int id)
	  {
		  try {
			  
			  query="delete from orders where o_id=?";
			  pst=this.con.prepareStatement(query);
			  pst.setInt(1, id);
			  pst.execute();
			  
		  }
		  catch(Exception e)
		  {
			  e.printStackTrace();
		  }
	  }
}
