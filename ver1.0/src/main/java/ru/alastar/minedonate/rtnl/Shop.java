package ru.alastar.minedonate.rtnl;

import ru.alastar.minedonate.merch.categories.MerchCategory;

public class Shop {

	public int sid ;
	
	public MerchCategory [ ] cats ;
	
	public String owner ;
	public String name ;
	
	public boolean isFreezed ;
	public String freezer ;
	public String freezReason ;
	
	public boolean canVisibleFreezedText ;
	
	public Shop ( int _sid, MerchCategory [ ] _cats, String _owner, String _name, boolean _isFreezed, String _freezer, String _freezReason, boolean _canVisibleFreezedText ) {
		
		sid = _sid ;
		
		cats = _cats ;
		
		owner = _owner ;
		name = _name ;
		
		isFreezed = _isFreezed ;
		freezer = _freezer ;
		freezReason = _freezReason ;
		
		canVisibleFreezedText = _canVisibleFreezedText ;
		
	}

}
