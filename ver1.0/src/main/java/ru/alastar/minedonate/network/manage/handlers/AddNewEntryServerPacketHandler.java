package ru.alastar.minedonate.network.manage.handlers;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.entity.player.EntityPlayerMP;
import ru.alastar.minedonate.MineDonate;
import ru.alastar.minedonate.network.manage.packets.AddNewEntryPacket;
import ru.alastar.minedonate.network.manage.packets.ManageResponsePacket;
import ru.alastar.minedonate.rtnl.Account;
import ru.alastar.minedonate.rtnl.Manager;
import ru.alastar.minedonate.rtnl.Shop;

public class AddNewEntryServerPacketHandler implements IMessageHandler < AddNewEntryPacket, IMessage > {
	
    public AddNewEntryServerPacketHandler ( ) {

    }

    @Override
    public IMessage onMessage ( AddNewEntryPacket message, MessageContext ctx ) {
    	
    	if ( ! MineDonate . checkShopExists ( message . shopId ) ) {
    		
			return new ManageResponsePacket ( ManageResponsePacket.ResponseType.OBJ, ManageResponsePacket.ResponseCode.ADD, ManageResponsePacket.ResponseStatus.ERROR_SHOP_NOTFOUND ) ;

    	}
    	
		EntityPlayerMP serverPlayer = ctx . getServerHandler ( ) . playerEntity ;
		
		Shop s = MineDonate . shops . get ( message . shopId ) ;
		
		Account acc = MineDonate . getAccount ( serverPlayer . getDisplayName ( ) . toLowerCase ( ) ) ;

		if ( acc . canEditShop ( s . owner ) ) {
			
			if ( s . isFreezed ) {

				return new ManageResponsePacket ( ManageResponsePacket.ResponseType.OBJ, ManageResponsePacket.ResponseCode.ADD, ManageResponsePacket.ResponseStatus.ERROR_SHOP_FREEZED ) ;

			}
			
			if ( message . name == null || message . name . length ( ) > 140 || message . cost < 1 ) {

				return new ManageResponsePacket ( ManageResponsePacket.ResponseType.OBJ, ManageResponsePacket.ResponseCode.ADD, ManageResponsePacket.ResponseStatus.ERROR_UNKNOWN ) ;

			}
			
			if ( ! MineDonate . checkCatExists ( s . sid, message . catId ) ) {
				
				return new ManageResponsePacket ( ManageResponsePacket.ResponseType.OBJ, ManageResponsePacket.ResponseCode.ADD, ManageResponsePacket.ResponseStatus.ERROR_CAT_NOTFOUND ) ;

			}
			
			switch ( s . cats [ message . catId ] . getCatType ( ) ) {
				
				case ITEMS :
					
					if ( acc . ms . currentItemStack == null ) {
						
						return new ManageResponsePacket ( ManageResponsePacket.ResponseType.OBJ, ManageResponsePacket.ResponseCode.ADD, ManageResponsePacket.ResponseStatus.ERROR_UNKNOWN ) ;

					}
					
					if ( message . limit < 0 && ! acc . canUnlimitedItems ( ) ) {
						
				        return new ManageResponsePacket ( ManageResponsePacket.ResponseType.OBJ, ManageResponsePacket.ResponseCode.ADD, ManageResponsePacket.ResponseStatus.ERROR_ACCESS_DENIED ) ;
		
					}
					
					Manager . addItemToShop ( acc, s, message . catId, message . limit, message . cost, message . name ) ;

				break ;
				
				case ENTITIES :
					
					if ( acc . ms . currentMob == null ) {
						
						return new ManageResponsePacket ( ManageResponsePacket.ResponseType.OBJ, ManageResponsePacket.ResponseCode.ADD, ManageResponsePacket.ResponseStatus.ERROR_UNKNOWN ) ;

					}
					
					if ( message . limit < 0 && ! acc . canUnlimitedEntities ( ) ) {
						
				        return new ManageResponsePacket ( ManageResponsePacket.ResponseType.OBJ, ManageResponsePacket.ResponseCode.ADD, ManageResponsePacket.ResponseStatus.ERROR_ACCESS_DENIED ) ;
		
					}
					
					Manager . addEntityToShop ( acc, s, message . catId, message . limit, message . cost, message . name ) ;

				break ;
				
			}
						
	        return new ManageResponsePacket ( ManageResponsePacket.ResponseType.OBJ, ManageResponsePacket.ResponseCode.ADD, ManageResponsePacket.ResponseStatus.OK ) ;

		} else {
		
	        return new ManageResponsePacket ( ManageResponsePacket.ResponseType.OBJ, ManageResponsePacket.ResponseCode.ADD, ManageResponsePacket.ResponseStatus.ERROR_ACCESS_DENIED ) ;

		}
		        
    }
    
}