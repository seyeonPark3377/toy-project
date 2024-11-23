package address.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class AddressDAO {
   private static AddressDAO instance = new AddressDAO();
   
   private AddressDAO( ) {}
   
   public static AddressDAO getInstance() {
      return instance;
   }
   
   
   private Connection getConnection() {
      Context context = null;
      DataSource dataSource = null;
      Connection connection = null;
      
      try {
         context = new InitialContext();
         dataSource = (DataSource) context.lookup("java:/comp/env/jdbc/oracle11g");
         connection = dataSource.getConnection();
      } catch (Exception e) {
         e.printStackTrace();
      }
      return connection;
   }
   
   
   public int insertAddress(AddressDTO dto) {
      int ri = 0;
      Connection connection = null;
      PreparedStatement pstmt = null;
      String query = "insert into b_address values(seq_addr.nextval,?,?,?,?,?,?,?,?)";
      
      try {
         connection = getConnection();
         pstmt = connection.prepareStatement(query);
         pstmt.setString(1, dto.getAddressOwner());
         pstmt.setString(2, dto.getAddressName());
         pstmt.setString(3, dto.getAddressPhone());
         pstmt.setString(4, dto.getAddressHome());
         pstmt.setString(5, dto.getAddressEmail());
         pstmt.setString(6, dto.getAddressGroup());
         pstmt.setString(7, dto.getAddressMemo());
         pstmt.setInt(8, dto.getAddressBookmark());
         pstmt.executeUpdate();
         ri = 1;
         
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         try {
            if (pstmt!=null) pstmt.close();
            if (connection!=null) connection.close();
         } catch (Exception e2) {
            e2.printStackTrace();
         }         
      }
      return ri;
   }
   
   // --------------------------------------------------- 여기 부터 다시 하기 --------------------------------------------------------------
   public int deleteAddress(int addressId) {
      int ri = 0;
      Connection connection = null;
      PreparedStatement pstmt = null;
      String query = "delete from b_address where address_id=?";
      
      try {
         connection = getConnection();
         pstmt = connection.prepareStatement(query);
         pstmt.setInt(1, addressId);
         ri = pstmt.executeUpdate();
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         try {
            if (pstmt!=null) pstmt.close();
            if (connection!=null) connection.close();
         } catch (Exception e2) {
            e2.printStackTrace();
         }         
      }
      return ri;   
   }
   
   public int updateAddress(AddressDTO dto) {
      int ri = 0;
      Connection connection = null;
      PreparedStatement pstmt = null;
      String query = "update b_address set address_name=?,address_phone=?,address_home=?,address_email=?,"+
                  "address_group=?,address_memo=?,address_bookmark=? where address_id=?";
      
      try {
         connection = getConnection();
         pstmt = connection.prepareStatement(query);
         pstmt.setString(1, dto.getAddressName());
         pstmt.setString(2, dto.getAddressPhone());
         pstmt.setString(3, dto.getAddressHome());
         pstmt.setString(4, dto.getAddressEmail());
         pstmt.setString(5, dto.getAddressGroup());
         pstmt.setString(6, dto.getAddressMemo());
         pstmt.setInt(7, dto.getAddressBookmark());
         pstmt.setInt(8, dto.getAddressId());
         ri = pstmt.executeUpdate();
         
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         try {
            if (pstmt!=null) pstmt.close();
            if (connection!=null) connection.close();
         } catch (Exception e2) {
            e2.printStackTrace();
         }         
      }
      return ri;
      
   }
   public List<String> getAddressGroups(String addressOwner) {
      
      Connection connection = null;
      PreparedStatement pstmt = null;
      ResultSet set = null;
      List<String> list = new ArrayList<>();
      String query = "select distinct address_group from b_address where address_owner=? order by address_group";
      
      try {
         connection = getConnection();
         pstmt = connection.prepareStatement(query);
         pstmt.setString(1, addressOwner);
         set = pstmt.executeQuery();
         
         while (set.next()) {
            list.add(set.getString("address_group"));
         }
         
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         try {
            if (set!=null) set.close();
            if (pstmt!=null) pstmt.close();
            if (connection!=null) connection.close();
         } catch (Exception e2) {
            e2.printStackTrace();
         }         
      }
      return list;
      

      
   }
   public List<AddressDTO> listAddress(String addressOwner, List<String> addressGroups, int addressBookmark) {
      Connection connection = null;
      PreparedStatement pstmt = null;
      ResultSet set = null;
      List<AddressDTO> list = new ArrayList<>();
      AddressDTO dto = null;
      String query;
      
      try {
         connection = getConnection();
         
         if (addressGroups.size()==0) {
            query = "select * from b_address where address_owner=?";
            if (addressBookmark==1) {
               query += " and address_bookmark=?";
               query += " order by address_name asc";
               pstmt = connection.prepareStatement(query);
               pstmt.setString(1, addressOwner);
               pstmt.setInt(2, addressBookmark);
            } else {

               query += " order by address_name asc";
               pstmt = connection.prepareStatement(query);
               pstmt.setString(1, addressOwner);
            }
            set = pstmt.executeQuery();
            
         } else {
            query = "select * from b_address where ( ";
            for (int i=1;i<=addressGroups.size();i++) {
               query +=" address_group=? ";
               if (i<addressGroups.size()) query += " or ";
            }
            query += ") and  address_owner=? ";
            if (addressBookmark==1) {
               query += " and address_bookmark=?";
               query += "order by address_name asc";
               pstmt = connection.prepareStatement(query);
               for (int i=1;i<=addressGroups.size();i++) pstmt.setString(i, addressGroups.get(i-1));   
               pstmt.setString(addressGroups.size()+1, addressOwner);            
               pstmt.setInt(addressGroups.size()+2, addressBookmark);
            } else {

               query += " order by address_name asc";
               pstmt = connection.prepareStatement(query);
               for (int i=1;i<=addressGroups.size();i++) pstmt.setString(i, addressGroups.get(i-1));   
               pstmt.setString(addressGroups.size()+1, addressOwner);      
            }
            
            set = pstmt.executeQuery();
         }
         
         System.out.println(query);
         while (set.next()) {
            dto = new AddressDTO();
            dto.setAddressId(set.getInt("address_id"));
            dto.setAddressOwner(set.getString("address_owner"));
            dto.setAddressName(set.getString("address_name"));
            dto.setAddressPhone(set.getString("address_phone"));
            dto.setAddressHome(set.getString("address_home"));
            dto.setAddressEmail(set.getString("address_email"));
            dto.setAddressGroup(set.getString("address_group"));
            dto.setAddressMemo(set.getString("address_memo"));
            dto.setAddressBookmark(set.getInt("address_bookmark"));
            list.add(dto);
         }
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         try {
            if (set!=null) set.close();
            if (pstmt!=null) pstmt.close();
            if (connection!=null) connection.close();
         } catch (Exception e2) {
            e2.printStackTrace();
         }         
      }
      return list;
   }
   
   public AddressDTO getAddress(int addressId) {
      Connection connection = null;
      PreparedStatement pstmt = null;
      ResultSet set = null;
      AddressDTO dto = null;
      String query = "select * from b_address where address_id=?";
      
      try {
         connection = getConnection();
         pstmt = connection.prepareStatement(query);
         pstmt.setInt(1, addressId);
         set = pstmt.executeQuery();
         
         if (set.next()) {
            dto = new AddressDTO();
            dto.setAddressId(set.getInt("address_id"));
            dto.setAddressOwner(set.getString("address_owner"));
            dto.setAddressName(set.getString("address_name"));
            dto.setAddressPhone(set.getString("address_phone"));
            dto.setAddressHome(set.getString("address_home"));
            dto.setAddressEmail(set.getString("address_email"));
            dto.setAddressGroup(set.getString("address_group"));
            dto.setAddressMemo(set.getString("address_memo"));
            dto.setAddressBookmark(set.getInt("address_bookmark"));
         }
         
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         try {
            if (set!=null) set.close();
            if (pstmt!=null) pstmt.close();
            if (connection!=null) connection.close();
         } catch (Exception e2) {
            e2.printStackTrace();
         }         
      }
      return dto;

   }   

}