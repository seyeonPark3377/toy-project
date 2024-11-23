package address.service;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import address.model.AddressDAO;
import address.model.AddressDTO;

public class AddressServiceImpl implements AddressService {
	
	private AddressDAO addressDao = AddressDAO.getInstance();
	@Override
	public int insertAddress(String addressOwner, String addressName, String addressPhone, String addressHome, String addressEmail, String addressGroup, String addressMemo, int addressBookmark) {
		AddressDTO addressDto = new AddressDTO();
		addressDto.setAddressOwner(addressOwner);
		addressDto.setAddressName(addressName);
		addressDto.setAddressPhone(addressPhone);
		addressDto.setAddressHome(addressHome);
		addressDto.setAddressEmail(addressEmail);
		addressDto.setAddressGroup(addressGroup);
		addressDto.setAddressMemo(addressMemo);
		addressDto.setAddressBookmark(addressBookmark);
		
		return addressDao.insertAddress(addressDto);
	}
	@Override
	public int updateAddress(String addressName, String addressPhone, String addressHome, String addressEmail, String addressGroup, String addressMemo, int addressBookmark, int addressId) {
		AddressDTO addressDto = new AddressDTO();
		addressDto.setAddressName(addressName);
		addressDto.setAddressPhone(addressPhone);
		addressDto.setAddressHome(addressHome);
		addressDto.setAddressEmail(addressEmail);
		addressDto.setAddressGroup(addressGroup);
		addressDto.setAddressMemo(addressMemo);
		addressDto.setAddressBookmark(addressBookmark);
		addressDto.setAddressId(addressId);
		
		return addressDao.updateAddress(addressDto);
	}
	@Override
	public int deleteAddress(int addressId) {
		return addressDao.deleteAddress(addressId);
	}
	@Override
	public List<AddressDTO> listAddress(String addressOwner, List<String> addressGroups, int addressBookmark) {
		return addressDao.listAddress(addressOwner, addressGroups, addressBookmark);
	}
	@Override
	public List<String> getTotalAddressGroups(String addressOwner){
		return addressDao.getAddressGroups(addressOwner);
	}
	@Override
	public AddressDTO getAddress(int addressId) {
		return addressDao.getAddress(addressId);
	}
	@Override
	public List<String> getAddressGroupFromRequest(Enumeration<String> names) {
		List<String> addressGroups = new ArrayList<>();
		while (names.hasMoreElements()) {
			String name = (String)names.nextElement();
			if (!name.equals("addressBookmark")) addressGroups.add(name);
		}
		return addressGroups;
	}
	@Override
	public List<AddressGroupCheck> getSelectedAddressGroups(
			List<String> totalGroups, List<String> selectedGroups) {
		List<AddressGroupCheck> list = new ArrayList<>();
		for (int i=0;i<totalGroups.size();i++) {
			AddressGroupCheck agc = new AddressGroupCheck();
			agc.setAddressGroup(totalGroups.get(i));
			agc.setSelected(selectedGroups.contains(totalGroups.get(i)));
			list.add(agc);
		}
		return list;
	}
	
	
	
}
