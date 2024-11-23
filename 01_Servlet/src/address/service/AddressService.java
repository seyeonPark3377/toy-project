package address.service;

import java.util.Enumeration;
import java.util.List;

import address.model.AddressDTO;


public interface AddressService {
	
	public List<AddressDTO> listAddress(String addressOwner, List<String> addressGroups, int addressBookmark);

	public List<String> getAddressGroupFromRequest(Enumeration<String> names);

	public List<String> getTotalAddressGroups(String addressOwner);

	public List<AddressGroupCheck> getSelectedAddressGroups(List<String> totalGroups, List<String> selectedGroups);

	public int updateAddress(String addressName, String addressPhone, String addressHome, String addressEmail,
			String addressGroup, String addressMemo, int addressBookmark, int addressId);

	public int deleteAddress(int addressId);

	public AddressDTO getAddress(int addressId);

	public int insertAddress(String addressOwner, String addressName, String addressPhone, String addressHome,
			String addressEmail, String addressGroup, String addressMemo, int addressBookmark);
	
}
