package ru.alastar.minedonate.merch.categories;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import ru.alastar.minedonate.MineDonate;
import ru.alastar.minedonate.merch.Merch;
import ru.alastar.minedonate.merch.info.ShopInfo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UsersShops extends MerchCategory {

	boolean enabled = MineDonate.cfg.userShops ;
	public Map<Integer, ShopInfo> map = new HashMap<>();
	
	public UsersShops ( ) {
		
		super ( 0, 4, null ) ;
		
	}

    public ShopInfo getShop ( int sid ) {
    
    	return map . get ( sid ) ;
    	
    }
    
    @Override
    public void addMerch ( Merch m ) {
    	
    	super . addMerch ( m ) ;
    	
        map . put ( ( ( ShopInfo ) m ) . shopId, ( ShopInfo ) m ) ;

    }
    @Override
    public void loadMerchFromDB(ResultSet rs) {
        try {        	
            while (rs.next()) {
                final ShopInfo info = new ShopInfo(rs.getInt("id"), rs.getInt("id"), rs.getInt("rating"), MineDonate.getNameFromUUID(UUID.fromString(rs.getString("UUID"))), rs.getString("name"), rs.getBoolean("isFreezed"), rs.getString("freezer"), rs.getString("freezReason"), true, rs.getString("moneyType"));
                this.addMerch(info);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        MinecraftServer.getServer().logInfo("Loaded " + m_Merch.size() + " users shops");
    }

    @Override
    public Merch constructMerch() {
        return new ShopInfo();
    }

    @Override
    public String getDatabaseTable ( ) {
        return MineDonate.cfg.dbShops;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
    
    @Override
    public void setEnabled ( boolean _enabled ) {
    	
    	enabled = _enabled ;
    	
    }
    
    @Override
    public void giveMerch(EntityPlayerMP player, Merch merch, int amount) {

    }
    
	@Override
	public String getMoneyType ( ) {
		
		return null ;
		
	}

	@Override
    public Type getCatType ( ) {
    	
    	return Type . SHOPS ;
    	
    }
    
}